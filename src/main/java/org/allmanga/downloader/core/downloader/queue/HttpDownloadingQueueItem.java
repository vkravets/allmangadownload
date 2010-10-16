package org.allmanga.downloader.core.downloader.queue;

import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 10/16/10
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpDownloadingQueueItem extends DownloadingQueueItem<URL>{

    public HttpDownloadingQueueItem(URL target, String name, String description) {
        super(target, name, description);
    }

    @Override
    public void work() {
        WebDownloadFile downloadFile = new WebDownloadFile(getTarget());
        downloadFile.addDownloadingListener(new WebDownloadingListener() {
            @Override
            public void onDownloadProgress(WebDownloadFile downloader) {
                setProgress(Math.round(downloader.getProgress()));
                HttpDownloadingQueueItem.this.onDownloadProgress();
            }
        });
        downloadFile.download();
    }
}
