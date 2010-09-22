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

    private Collection<MangaInfo> mangaList;
    private Collection<String> genreCatalog;
    private Collection<String> translatesCatalog;


    public MangaCatalogImpl(MangaCatalog mangaCatalog) {
        super(mangaCatalog);
    }

    public Collection<MangaInfo> getMangaList() {
        if (mangaList == null) {
            mangaList = getMangaCatalog().getMangaList();
        }
        return mangaList;
    }

    public Collection<String> getGenreCatalog() {
        if (genreCatalog == null) {
            genreCatalog = getMangaCatalog().getGenreCatalog();
        }
        return genreCatalog;
    }

    public Collection<String> getTranslatesCatalog() {
        if (translatesCatalog == null) {
            translatesCatalog = getMangaCatalog().getTranslatesCatalog();
        }
        return translatesCatalog;
    }
}
