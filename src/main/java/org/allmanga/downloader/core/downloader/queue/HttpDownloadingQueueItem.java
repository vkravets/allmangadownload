package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.downloader.WebDownloadFile;
import org.allmanga.downloader.core.downloader.queue.listeners.WebDownloadingListener;

import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 10/16/10
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpDownloadingQueueItem extends DownloadingQueueItem<URL>{

    private WebDownloadFile downloadFile;
    private WebDownloadingListener listener;

    public HttpDownloadingQueueItem(URL target, String name, String description) {
        super(target, name, description);
        downloadFile = new WebDownloadFile(getTarget());
        initListener();
    }

    private void initListener() {
        listener = new WebDownloadingListener() {
            @Override
            public void onDownloadingProgress(WebDownloadFile downloader) {
                HttpDownloadingQueueItem.this.setProgress(Math.round(downloader.getProgress()));
                HttpDownloadingQueueItem.this.onDownloadingProgress(HttpDownloadingQueueItem.this);
//                System.out.println(downloader);
            }

            @Override
            public void onChangeStatus(DownloadingQueueItemStatus status, WebDownloadFile downloader) {
                HttpDownloadingQueueItem.this.setStatus(status);
                HttpDownloadingQueueItem.this.onChangeStatus(status, HttpDownloadingQueueItem.this);
            }

            @Override
            public void onError(WebDownloadFile downloader) {
                HttpDownloadingQueueItem.this.setErrorDescription(downloader.getErrorDescription());
                HttpDownloadingQueueItem.this.onError(HttpDownloadingQueueItem.this);
            }
        };
        downloadFile.addDownloadingListener(listener);
    }

    @Override
    public void work() {
        downloadFile.download();
    }

    @Override
    public void setStatus(DownloadingQueueItemStatus status) {
        super.setStatus(status);
        downloadFile.removeDownloadingListener(listener);
        downloadFile.setStatus(status);
        downloadFile.addDownloadingListener(listener);
    }

    public WebDownloadFile getWebDownloadFile() {
        return downloadFile;
    }
    
}
