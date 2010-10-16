package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.share.ListenerSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 17.10.2010
 * Time: 1:54:06
 * To change this template use File | Settings | File Templates.
 */
public class DownloadingQueue extends ListenerSupport<DownloadingQueueListener<DownloadingQueueItem>> {

    // TODO: add sync thread support
    private List<DownloadingQueueItem> queue;

    public DownloadingQueue() {
        queue = new ArrayList<DownloadingQueueItem>();
    }

    public void addItem(DownloadingQueueItem item) {
        queue.add(item);
    }

    public void removeFirst() {
        if (!queue.isEmpty()) {
            queue.remove(0);
        }
    }

    public DownloadingQueueItem getFirst() {
        if (!queue.isEmpty()) {
            return queue.get(0);
        }
        return null;
    }
}
