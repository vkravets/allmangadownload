package org.allmanga.downloader.core.downloader.queue;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 18.10.2010
 * Time: 12:00:44
 */
public enum DownloadingQueueItemStatus {
    WAITING,
    STARTING,
    DONE,
    ERROR,
    PAUSED,
    CANCELLED
}
