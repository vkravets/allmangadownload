package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.downloader.queue.listeners.ListenerSupport;
import org.allmanga.downloader.core.downloader.queue.listeners.ThreadListener;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 18.10.2010
 * Time: 18:38:30
 */
public class ProcessQueueThread implements Runnable{

    private static ProcessQueueThread processQueueThread;
    private static boolean isExit = false;
    private ListenerSupport<ThreadListener> listenerSupport;

    public void process() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        DownloadingQueue downloadingQueue = getDownloadingQueue();
        while (!isExit) {
            if (!downloadingQueue.processCurrentItem()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        }
        fireThreadEnd();
    }

    public static void setExit(boolean isExit) {
        ProcessQueueThread.isExit = isExit;
    }

    public static ProcessQueueThread getInstance() {
        if (processQueueThread == null) {
            processQueueThread = new ProcessQueueThread();
        }
        return processQueueThread;
    }

    public static DownloadingQueue getDownloadingQueue() {
        return DownloadingQueue.getInstance();
    }

    public void setExitFlag(boolean isExit) {
        ProcessQueueThread.isExit = isExit;
    }

    private void fireThreadEnd() {
        for (ThreadListener listener : listenerSupport.getListeners()) {
            listener.onThreadEnd();
        }
    }

    public void addThreadListener(ThreadListener threadListener) {
        listenerSupport.addListener(threadListener);
    }

    public void removeThreadListener(ThreadListener threadListener) {
        listenerSupport.removeListener(threadListener);
    }
}
