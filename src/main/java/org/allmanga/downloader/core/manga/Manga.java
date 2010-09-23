package org.allmanga.downloader.core.manga;

import org.allmanga.downloader.core.manga.share.InfoItem;

/**
 * Created by IntelliJ IDEA.
 * User: Sly
 * Date: Sep 23, 2010
 * Time: 9:41:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Manga extends MangaImpl{

    private InfoItem mangaInfo;

    public Manga(MangaCatalog mangaCatalog, String name, String url) {
        super(mangaCatalog);
        mangaInfo = new InfoItem(name, url);
    }

    public Manga(MangaCatalog mangaCatalog, InfoItem mangaInfo) {
        super(mangaCatalog);
        this.mangaInfo = mangaInfo; 
    }
}
