package org.allmanga.downloader.core.downloader.queue;

import org.allmanga.downloader.core.share.ListenerSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 10/16/10
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadingListenerSupport<T> extends ListenerSupport<DownloadingListener<T>> 
                                           implements DownloadingListener<T> {

    @Override
    public void onDownloadProgress(T downloader) {
        for (DownloadingListener<T> listener : getListeners()) {
            listener.onDownloadProgress(downloader);
        }
    }
}
