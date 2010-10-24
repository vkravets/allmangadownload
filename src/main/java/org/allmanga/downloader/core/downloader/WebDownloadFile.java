package org.allmanga.downloader.core.downloader;

import org.allmanga.downloader.core.downloader.queue.DownloadingQueueItemStatus;
import org.allmanga.downloader.core.downloader.queue.listeners.ListenerSupport;
import org.allmanga.downloader.core.downloader.queue.listeners.WebDownloadingListener;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 10/16/10
 * Time: 8:15 PM
 */

// This class downloads a file from a URL.
public class WebDownloadFile implements Runnable, WebDownloadingListener {

    private ListenerSupport<WebDownloadingListener> listenerSupport;

    // Max size of download buffer.
    private static final int MAX_BUFFER_SIZE = 1024;

    private URL url; // download URL
    private int size; // size of download in bytes
    private int downloaded; // number of bytes downloaded
    private DownloadingQueueItemStatus status; // current status of download
    private String errorDescription;

    // Constructor for Download.
    public WebDownloadFile(URL url) {
        this.url = url;
        size = -1;
        downloaded = 0;
        status = DownloadingQueueItemStatus.STARTING;
        listenerSupport = new ListenerSupport<WebDownloadingListener>();
    }

    // Get this download's URL.
    public String getUrl() {
        return url.toString();
    }

    // Get this download's size.
    public int getSize() {
        return size;
    }

    // Get this download's progress.
    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }

    // Get this download's status.
    public DownloadingQueueItemStatus getStatus() {
        return status;
    }

    public void setStatus(DownloadingQueueItemStatus status) {
        this.status = status;
        if (status.equals(DownloadingQueueItemStatus.ERROR)) {
            onError(this);
        }
        onChangeStatus(this.status, this);
    }

    // Start or resume downloading.
    public void download() {
        Thread thread = new Thread(this);
        thread.start();
    }

    // Get file name portion of URL.
    private String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    // Download file.
    public void run() {
        RandomAccessFile file = null;
        InputStream stream = null;

        try {
            // Open connection to URL.
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            // Specify what portion of file to download.
            connection.setRequestProperty("Range",
                    "bytes=" + downloaded + "-");

            // Connect to server.
            connection.connect();

            // Make sure response code is in the 200 range.
            if (connection.getResponseCode() / 100 != 2) {
                setStatus(DownloadingQueueItemStatus.ERROR);
                return;
            }

            // Check for valid content length.
            int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                setStatus(DownloadingQueueItemStatus.ERROR);
                return;
            }

      /* Set the size for this download if it
         hasn't been already set. */
            if (size == -1) {
                size = contentLength;
                onDownloadingProgress(this);
            }

            // Open file and seek to the end of it.
            file = new RandomAccessFile(getFileName(url), "rw");
            file.seek(downloaded);

            stream = connection.getInputStream();
            while (status == DownloadingQueueItemStatus.STARTING) {
        /* Size buffer according to how much of the
           file is left to download. */
                byte buffer[];
                if (size - downloaded > MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                } else {
                    buffer = new byte[size - downloaded];
                }

                // Read from server into buffer.
                int read = stream.read(buffer);
                if (read == -1)
                    break;

                // Write buffer to file.
                file.write(buffer, 0, read);
                downloaded += read;
                onDownloadingProgress(this);
            }

      /* Change status to complete if this point was
         reached because downloading has finished. */
            if (status == DownloadingQueueItemStatus.STARTING) {
                status = DownloadingQueueItemStatus.DONE;
                onChangeStatus(status, this);
            }
        } catch (UnknownHostException e) {
            errorDescription = "Unknown host: " + e.getMessage();
            setStatus(DownloadingQueueItemStatus.ERROR);
        } catch (Exception e) {
            errorDescription = e.getMessage();
            setStatus(DownloadingQueueItemStatus.ERROR);
        } finally {
            // Close file.
            if (file != null) {
                try {
                    file.close();
                } catch (Exception e) {}
            }

            // Close connection to server.
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {}
            }
        }
    }

    public void addDownloadingListener(WebDownloadingListener listener) {
        listenerSupport.addListener(listener);
    }

    public void removeDownloadingListener(WebDownloadingListener listener) {
        listenerSupport.removeListener(listener);
    }

    @Override
    public void onDownloadingProgress(WebDownloadFile downloader) {
        for (WebDownloadingListener listener : listenerSupport.getListeners()) {
            listener.onDownloadingProgress(downloader);
        }
    }

    @Override
    public void onChangeStatus(DownloadingQueueItemStatus status, WebDownloadFile downloader) {
        for (WebDownloadingListener listener : listenerSupport.getListeners()) {
            listener.onChangeStatus(status, downloader);
        }
    }

    @Override
    public void onError(WebDownloadFile downloader) {
        for (WebDownloadingListener listener : listenerSupport.getListeners()) {
            listener.onError(downloader);
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("[URL:{0}, Size:{1}, Downloaded:{2}, Status:{3}]", url, size, downloaded, status);
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
