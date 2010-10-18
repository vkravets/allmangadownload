package org.allmanga.downloader.core.downloader.queue;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 18.10.2010
 * Time: 18:38:30
 * To change this template use File | Settings | File Templates.
 */
public class ProcessQueueThread implements Runnable{

    private static ProcessQueueThread processQueueThread;
    private static boolean isExit = false;

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
}
