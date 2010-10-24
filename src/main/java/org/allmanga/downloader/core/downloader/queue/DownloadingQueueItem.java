package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.downloader.queue.listeners.DownloadingListener;
import org.allmanga.downloader.core.downloader.queue.listeners.ListenerSupport;

import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 10/16/10
 * Time: 7:35 PM
 */
public abstract class DownloadingQueueItem<T> {

    private String name;
    private String description;
    private T target;
    private boolean isProgressEnabled;
    private int progress = 0;
    private String errorDescription;

    private ListenerSupport<DownloadingListener<DownloadingQueueItem>> listenerSupport;

    private DownloadingQueueItemStatus status; 

    public DownloadingQueueItem(T target, String name, String description) {
        this(target, name, description, true);
    }

    public DownloadingQueueItem(T target, String name, String description, boolean progressEnabled) {
        this.name = name;
        this.description = description;
        this.isProgressEnabled = progressEnabled;
        this.target = target;
        listenerSupport = new ListenerSupport<DownloadingListener<DownloadingQueueItem>>();
        this.status = DownloadingQueueItemStatus.WAITING;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isProgressEnabled() {
        return isProgressEnabled;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public T getTarget() {
        return target;
    }

    public abstract void work();

    public void setErrorDescription(String description) {
        this.errorDescription = description;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    protected void fireDownloadingProgress(DownloadingQueueItem downloader) {
        for (DownloadingListener<DownloadingQueueItem> listener : listenerSupport.getListeners()) {
            listener.onDownloadingProgress(downloader);
        }
    }

    protected void fireChangeStatus(DownloadingQueueItemStatus status, DownloadingQueueItem downloader) {
        for (DownloadingListener<DownloadingQueueItem> listener : listenerSupport.getListeners()) {
            listener.onChangeStatus(status, downloader);
        }
    }

    protected void fireError(DownloadingQueueItem downloader) {
        for (DownloadingListener<DownloadingQueueItem> listener : listenerSupport.getListeners()) {
            listener.onError(downloader);
        }
    }

    public void addDownloadingListener(DownloadingListener<DownloadingQueueItem> listener) {
        listenerSupport.addListener(listener);
    }

    public void removeDownloadingListener(DownloadingListener<DownloadingQueueItem> listener) {
        listenerSupport.removeListener(listener);        
    }

    public void setStatus(DownloadingQueueItemStatus status) {
        this.status = status;
    }

    public DownloadingQueueItemStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Downloading {0}. Name: {1}. Description: {2}. Progress: {3}", getTarget(), name, description, progress);
    }
}
