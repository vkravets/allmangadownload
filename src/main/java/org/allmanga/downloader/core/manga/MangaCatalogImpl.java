package org.allmanga.downloader.core.manga;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 22, 2010
 * Time: 7:49:06 PM
 * Mail to vladimir.kravets-ukr@hp.com
 */
public class MangaCatalogImpl extends Manga implements IMangaCatalog{

    private Collection<IManga> mangaList;
    private Collection<String> genreCatalog;
    private Collection<String> translatesCatalog;


    public MangaCatalogImpl(MangaCatalog mangaCatalog) {
        super(mangaCatalog);
    }

    public Collection<IManga> getMangaList() {
        if (mangaList == null) {
            mangaList = getManga().getMangaList();
        }
        return mangaList;
    }

    public Collection<String> getGenreCatalog() {
        return null;
    }

    public Collection<String> getTranslatesCatalog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
