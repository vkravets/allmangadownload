package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.downloader.queue.listeners.DownloadingListener;
import org.allmanga.downloader.core.downloader.queue.listeners.DownloadingQueueItemListener;
import org.allmanga.downloader.core.downloader.queue.listeners.ListenerSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 17.10.2010
 * Time: 1:54:06
 * To change this template use File | Settings | File Templates.
 */
public class DownloadingQueue implements DownloadingQueueItemListener{

    private static DownloadingQueue instance;

    private static int MAX_RUN_TASK = 3;

    private List<DownloadingQueueItem<?>> queue;
    private ListenerSupport<DownloadingQueueItemListener<DownloadingQueueItem<?>>> listenerSupport;
    private boolean possibleRunTask;
    private int numOfCurrentTask = 0;

    public DownloadingQueue() {
        queue = Collections.synchronizedList(new ArrayList<DownloadingQueueItem<?>>());
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
        if (downloadingQueueItem != null ){
            if (!isPossibleToRunTask()) {
                return true;                
            }
            onDownloadBegin(downloadingQueueItem);
            downloadingQueueItem.addDownloadingListener(new DownloadingListener<DownloadingQueueItem>() {
                @Override
                public void onDownloadingProgress(DownloadingQueueItem downloader) {
                    DownloadingQueue.this.onDownloadProgress(downloader);
                }

                @Override
                public void onChangeStatus(DownloadingQueueItemStatus status, DownloadingQueueItem downloader) {
                    if (status.equals(DownloadingQueueItemStatus.DONE) || status.equals(DownloadingQueueItemStatus.ERROR)) {
                        DownloadingQueue.this.onDownloadFinish(downloader);
                    }
                }

                @Override
                public void onError(DownloadingQueueItem downloader) {
                    DownloadingQueue.this.onError(downloader);
                }
            });
            downloadingQueueItem.setStatus(DownloadingQueueItemStatus.STARTING);
            downloadingQueueItem.work();
            numOfCurrentTask++;
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
        numOfCurrentTask--;
    }

    @Override
    public void onError(DownloadingQueueItem item) {
        for (DownloadingQueueItemListener<DownloadingQueueItem<?>> listener:listenerSupport.getListeners()) {
            listener.onError(item);
        }
    }

    public void addDownloadingQueueItemListener(DownloadingQueueItemListener<DownloadingQueueItem<?>> listener) {
        listenerSupport.addListener(listener);
    }

    public void removeDownloadingQueueItemListener(DownloadingQueueItemListener<DownloadingQueueItem<?>> listener) {
        listenerSupport.removeListener(listener);
    }

    public boolean isPossibleToRunTask() {
        possibleRunTask = numOfCurrentTask < MAX_RUN_TASK;
        return possibleRunTask;
    }

    public static DownloadingQueue getInstance() {
        if (instance == null) {
            instance = new DownloadingQueue();
        }
        return instance;
    }
}
