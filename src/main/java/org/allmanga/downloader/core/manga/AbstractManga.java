package org.allmanga.downloader.core.manga;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 27, 2010
 * Time: 12:35:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractManga {

    private MangaCatalog mangaCatalog;

    public AbstractManga(MangaCatalog mangaCatalog) {
        this.mangaCatalog = mangaCatalog;
    }

    public MangaCatalog getMangaCatalog() {
        return mangaCatalog;
    }

    public void setMangaCatalog(MangaCatalog mangaCatalog) {
        this.mangaCatalog = mangaCatalog;
    }
}
