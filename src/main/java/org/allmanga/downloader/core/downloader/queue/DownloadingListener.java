package org.allmanga.downloader.core.downloader.queue;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 10/16/10
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DownloadingListener<T> {
    public void onDownloadProgress(T downloader);
}
