package org.allmanga.downloader.core.downloader.queue.listeners;

import org.allmanga.downloader.core.downloader.queue.DownloadingQueueItemStatus;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 18.10.2010
 * Time: 10:41:14
 */
public interface DownloadingListener<T> extends Listener {
    public void onDownloadingProgress(T downloader);
    public void onChangeStatus(DownloadingQueueItemStatus status, T downloader);
    public void onError(T downloader);
}
