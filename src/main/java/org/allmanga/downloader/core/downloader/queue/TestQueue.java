package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.downloader.queue.listeners.DownloadingQueueItemListener;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 18.10.2010
 * Time: 12:34:39
 */
public class TestQueue {

    public static void main(String[] argv ) {
        DownloadingQueue downloadingQueue = new DownloadingQueue();
        try {
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img.manga24.ru/airgear/287/00Credits.jpg"), "http://img1.manga24.ru/airgear/287/00Credits.jpg", "Chapter 1, Page 1"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img1.manga24.ru/airgear/287/00z.jpg"), "http://img1.manga24.ru/airgear/287/00z.jpg", "Chapter 1, Page 2"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img.manga24.ru/airgear/287/00Credits.jpg"), "http://img1.manga24.ru/airgear/287/00Credits.jpg", "Chapter 1, Page 1"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img1.manga24.ru/airgear/287/00z.jpg"), "http://img1.manga24.ru/airgear/287/00z.jpg", "Chapter 1, Page 2"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img.manga24.ru/airgear/287/00Credits.jpg"), "http://img1.manga24.ru/airgear/287/00Credits.jpg", "Chapter 1, Page 1"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img1.manga24.ru/airgear/287/00z.jpg"), "http://img1.manga24.ru/airgear/287/00z.jpg", "Chapter 1, Page 2"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img.manga24.ru/airgear/287/00Credits.jpg"), "http://img1.manga24.ru/airgear/287/00Credits.jpg", "Chapter 1, Page 1"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img1.manga24.ru/airgear/287/00z.jpg"), "http://img1.manga24.ru/airgear/287/00z.jpg", "Chapter 1, Page 2"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img.manga24.ru/airgear/287/00Credits.jpg"), "http://img1.manga24.ru/airgear/287/00Credits.jpg", "Chapter 1, Page 1"));
            downloadingQueue.addItem(new HttpDownloadingQueueItem(new URL("http://img1.manga24.ru/airgear/287/00z.jpg"), "http://img1.manga24.ru/airgear/287/00z.jpg", "Chapter 1, Page 2"));
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        downloadingQueue.addDownloadingQueueItemListener(new DownloadingQueueItemListener<DownloadingQueueItem<?>>() {

            @Override
            public void onDownloadBegin(DownloadingQueueItem<?> item) {
                HttpDownloadingQueueItem downloadItem = (HttpDownloadingQueueItem) item;
                System.out.println("Begin to download. " + downloadItem);
            }

            @Override
            public void onDownloadProgress(DownloadingQueueItem<?> item) {
                HttpDownloadingQueueItem downloadItem = (HttpDownloadingQueueItem) item;
//                System.out.println("Progress " + downloadItem.getProgress() +" "+ downloadItem);
            }

            @Override
            public void onDownloadFinish(DownloadingQueueItem<?> item) {
                HttpDownloadingQueueItem downloadItem = (HttpDownloadingQueueItem) item;
                System.out.println("Download was finished. " + downloadItem);
            }

            @Override
            public void onError(DownloadingQueueItem<?> item) {
                HttpDownloadingQueueItem downloadItem = (HttpDownloadingQueueItem) item;
                System.out.println("Error: " + downloadItem.getErrorDescription());
            }
        });
        while (downloadingQueue.processCurrentItem()) {
            
        };
    }
}
