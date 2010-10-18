package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.downloader.queue.listeners.DownloadingListener;
import org.allmanga.downloader.core.downloader.queue.listeners.DownloadingQueueItemListener;
import org.allmanga.downloader.core.downloader.queue.listeners.ListenerSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 17.10.2010
 * Time: 1:54:06
 * To change this template use File | Settings | File Templates.
 */
public class DownloadingQueue implements DownloadingQueueItemListener{

    // TODO: add sync thread support
    private List<DownloadingQueueItem<?>> queue;
    private ListenerSupport<DownloadingQueueItemListener<DownloadingQueueItem<?>>> listenerSupport;

    public DownloadingQueue() {
        queue = new ArrayList<DownloadingQueueItem<?>>();
        listenerSupport = new ListenerSupport<DownloadingQueueItemListener<DownloadingQueueItem<?>>>();
    }

    public void addItem(DownloadingQueueItem item) {
        queue.add(item);
    }

    public DownloadingQueueItem getWaitingItem() {
        if (!queue.isEmpty()) {
            for (DownloadingQueueItem item : queue) {
                if (item.getStatus().equals(DownloadingQueueItemStatus.WAITING)) {
                    return item;
                }
            }
        }
        return null;
    }

    public boolean processCurrentItem(){
        DownloadingQueueItem<?> downloadingQueueItem = getWaitingItem();
        if (downloadingQueueItem != null){
            onDownloadBegin(downloadingQueueItem);
            downloadingQueueItem.addDownloadingListener(new DownloadingListener<DownloadingQueueItem>() {
                @Override
                public void onDownloadingProgress(DownloadingQueueItem downloader) {
                    DownloadingQueue.this.onDownloadProgress(downloader);
                    if (downloader.getProgress() >= 100) {
                        downloader.setDoneStatus();
                        DownloadingQueue.this.onDownloadFinish(downloader);
                    }
                }
            });
            downloadingQueueItem.setStartingStatus();
            downloadingQueueItem.work();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDownloadBegin(DownloadingQueueItem item) {
        for (DownloadingQueueItemListener<DownloadingQueueItem<?>> listener:listenerSupport.getListeners()) {
            listener.onDownloadBegin(item);
        }
    }

    @Override
    public void onDownloadProgress(DownloadingQueueItem item) {
        for (DownloadingQueueItemListener<DownloadingQueueItem<?>> listener:listenerSupport.getListeners()) {
            listener.onDownloadProgress(item);
        }
    }

    @Override
    public void onDownloadFinish(DownloadingQueueItem item) {
        for (DownloadingQueueItemListener<DownloadingQueueItem<?>> listener:listenerSupport.getListeners()) {
            listener.onDownloadFinish(item);
        }
    }

    public void addDownloadingQueueItemListener(DownloadingQueueItemListener<DownloadingQueueItem<?>> listener) {
        listenerSupport.addListener(listener);
    }

    public void removeDownloadingQueueItemListener(DownloadingQueueItemListener<DownloadingQueueItem<?>> listener) {
        listenerSupport.removeListener(listener);
    }
}
