package org.allmanga.downloader.core.downloader.queue.listeners;

import org.allmanga.downloader.core.downloader.queue.DownloadingQueueItem;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 18.10.2010
 * Time: 10:43:52
 * To change this template use File | Settings | File Templates.
 */
public interface DownloadingQueueItemListener<T extends DownloadingQueueItem> extends Listener{
    public void onDownloadBegin(T item);
    public void onDownloadProgress(T item);
    public void onDownloadFinish(T item);
    public void onError(T item);
}
