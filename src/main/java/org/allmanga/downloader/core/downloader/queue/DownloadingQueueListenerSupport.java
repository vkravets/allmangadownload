package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.share.ListenerSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 10/16/10
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadingQueueListenerSupport<T extends DownloadingQueueItem>
                                                        extends ListenerSupport<DownloadingQueueListener<T>>
                                                        implements DownloadingQueueListener<T> {

    @Override
    public void onDownloadBegin(T downloadItem) {
        for (DownloadingQueueListener<T> listener : getListeners()) {
            listener.onDownloadBegin(downloadItem);
        }
    }

    @Override
    public void onDownloadProgress(T downloadItem) {
        for (DownloadingQueueListener<T> listener : getListeners()) {
            listener.onDownloadProgress(downloadItem);
        }
    }

    @Override
    public void onDownloadFinish(T downloadItem) {
        for (DownloadingQueueListener<T> listener : getListeners()) {
            listener.onDownloadFinish(downloadItem);
        }
    }
}
