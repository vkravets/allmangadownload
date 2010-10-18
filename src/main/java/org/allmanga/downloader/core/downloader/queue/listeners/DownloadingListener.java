package org.allmanga.downloader.core.downloader.queue.listeners;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 18.10.2010
 * Time: 10:41:14
 * To change this template use File | Settings | File Templates.
 */
public interface DownloadingListener<T> extends Listener {
    public void onDownloadingProgress(T downloader);     
}
