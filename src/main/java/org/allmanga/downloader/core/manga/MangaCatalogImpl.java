package org.allmanga.downloader.core.manga;

import org.allmanga.downloader.core.manga.share.InfoItem;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 22, 2010
 * Time: 7:49:06 PM
 * Mail to vladimir.kravets-ukr@hp.com
 */
public class MangaCatalogImpl extends MangaImpl implements IMangaCatalog{

    private Collection<InfoItem> mangaList;
    private Collection<InfoItem> genreCatalog;
    private Collection<InfoItem> translatesCatalog;

    private String mangaCatalogURL;
    private String genreCatalogURL;
    private String translateCatalogURL;

    public MangaCatalogImpl(MangaCatalog mangaCatalog) {
        super(mangaCatalog);
    }

    public Collection<InfoItem> getMangaList() {
        if (mangaList == null) {
            mangaList = getMangaCatalog().getMangaList();
        }
        return mangaList;
    }

    public Collection<InfoItem> getGenreCatalog() {
        if (genreCatalog == null) {
            genreCatalog = getMangaCatalog().getGenreCatalog();
        }
        return genreCatalog;
    }

    public Collection<InfoItem> getTranslatesCatalog() {
        if (translatesCatalog == null) {
            translatesCatalog = getMangaCatalog().getTranslatesCatalog();
        }
        return translatesCatalog;
    }

    public String getMangaCatalogURL() {
        if (mangaCatalogURL == null) {
            mangaCatalogURL = getMangaCatalog().getMangaCatalogURL();
        }
        return mangaCatalogURL;
    }

    public String getTranslatesCatalogURL() {
        if (translateCatalogURL == null) {
            translateCatalogURL = getMangaCatalog().getTranslatesCatalogURL();
        }
        return translateCatalogURL;
    }

    public String getGenreCatalogURL() {
        if (genreCatalogURL == null) {
            genreCatalogURL = getMangaCatalog().getGenreCatalogURL();
        }
        return genreCatalogURL;
    }
}
