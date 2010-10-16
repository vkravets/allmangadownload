package org.allmanga.downloader.core.downloader.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sly
 * Date: 10/16/10
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadingListenerSupport<T extends DownloadingQueueItem> implements DownloadingListener<T> {

    private List<DownloadingListener<T>> listenersList;

    public DownloadingListenerSupport() {
        listenersList = new ArrayList<DownloadingListener<T>>();
    }

    public void addDownloadingListener(DownloadingListener<T> listener) {
        listenersList.add(listener);
    }

    public void removeDownloadingListener(DownloadingListener<T> listener) {
        listenersList.remove(listener);
    }

    @Override
    public void onDownloadBegin(T downloadItem) {
        for (DownloadingListener<T> listener : listenersList) {
            listener.onDownloadBegin(downloadItem);
        }
    }

    @Override
    public void onDownloadProgress(T downloadItem) {
        for (DownloadingListener<T> listener : listenersList) {
            listener.onDownloadProgress(downloadItem);
        }
    }

    @Override
    public void onDownloadFinish(T downloadItem) {
        for (DownloadingListener<T> listener : listenersList) {
            listener.onDownloadFinish(downloadItem);
        }
    }
}
