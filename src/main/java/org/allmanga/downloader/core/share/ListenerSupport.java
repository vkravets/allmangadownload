package org.allmanga.downloader.core.share;

import java.util.ArrayList;
import java.util.List;

public class ListenerSupport<T> {
    
    private  List<T> listenersList;

    public ListenerSupport() {
        listenersList = new ArrayList<T>();
    }

    public void addDownloadingListener(T listener) {
        listenersList.add(listener);
    }

    public void removeDownloadingListener(T listener) {
        listenersList.remove(listener);
    }

    public List<T> getListeners() {
        return listenersList;
    }
}