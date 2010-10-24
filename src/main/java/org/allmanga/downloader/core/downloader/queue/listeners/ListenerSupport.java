package org.allmanga.downloader.core.downloader.queue.listeners;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 18.10.2010
 * Time: 10:37:05
 */
public class ListenerSupport<T extends Listener> {

    private List<T> listenersList;

    public ListenerSupport() {
        listenersList = new ArrayList<T>();
    }

    public void addListener(T listener) {
        listenersList.add(listener);
    }

    public void removeListener(T listener) {
        listenersList.remove(listener);
    }

    public List<T> getListeners() {
        return listenersList;
    }
}
