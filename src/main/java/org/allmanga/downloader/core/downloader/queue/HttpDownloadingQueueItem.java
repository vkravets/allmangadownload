package org.allmanga.downloader.core.downloader.queue;

import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: Sly
 * Date: 10/16/10
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpDownloadingQueueItem extends DownloadingQueueItem<URL>{

    public HttpDownloadingQueueItem(URL target, String name, String description, boolean progressEnabled) {
        super(target, name, description, progressEnabled);
    }

    @Override
    public void work() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
