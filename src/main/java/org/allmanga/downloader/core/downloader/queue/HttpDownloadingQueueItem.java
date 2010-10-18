package org.allmanga.downloader.core.downloader.queue;

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

    public HttpDownloadingQueueItem(URL target, String name, String description) {
        super(target, name, description);
    }

    @Override
    public void work() {
        downloadFile = new WebDownloadFile(getTarget());
        downloadFile.addDownloadingListener(new WebDownloadingListener() {
            @Override
            public void onDownloadingProgress(WebDownloadFile downloader) {
                HttpDownloadingQueueItem.this.setProgress(Math.round(downloader.getProgress()));
                HttpDownloadingQueueItem.this.onDownloadingProgress(HttpDownloadingQueueItem.this);
//                System.out.println(downloader);
            }
        });
        downloadFile.download();
    }

    public WebDownloadFile getWebDownloadFile() {
        return downloadFile;
    }
}
