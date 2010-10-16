package org.allmanga.downloader.core.downloader.queue;

/**
 * Created by IntelliJ IDEA.
 * User: Sly
 * Date: 10/16/10
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DownloadingQueueItem<T> {

    private String name;
    private String description;
    private T target;
    private boolean isProgressEnabled;
    private int progress = 0;

    // name
    public DownloadingQueueItem(T target, String name, String description) {
        this(target, name, description, true);
    }

    public DownloadingQueueItem(T target, String name, String description, boolean progressEnabled) {
        this.name = name;
        this.description = description;
        this.isProgressEnabled = progressEnabled;
        this.target = target;
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

    public abstract void work();
}
