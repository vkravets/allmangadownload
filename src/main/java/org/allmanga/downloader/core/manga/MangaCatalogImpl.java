package org.allmanga.downloader.core.manga;

import org.allmanga.downloader.core.manga.share.InfoItem;
import org.allmanga.downloader.core.manga.share.PageType;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 22, 2010
 * Time: 7:49:06 PM
 */
public class MangaCatalogImpl extends AbstractManga implements IMangaCatalog{

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
            parseMangaCatalogPage();
            mangaList = getMangaCatalog().getMangaList();
        }
        return mangaList;
    }

    public Collection<InfoItem> getGenreCatalog() {
        if (genreCatalog == null) {
            parseGenreCatalogPage();
            genreCatalog = getMangaCatalog().getGenreCatalog();
        }
        return genreCatalog;
    }

    public Collection<InfoItem> getTranslatesCatalog() {
        if (translatesCatalog == null) {
            parseTranslatesCatalogPage();
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

    public void parseMangaCatalogPage() {
        getMangaCatalog().parsePage(getMangaCatalogURL(), PageType.MANGA_LIST);
    }

    public void parseGenreCatalogPage() {
        getMangaCatalog().parsePage(getGenreCatalogURL(), PageType.GENRES);        
    }

    public void parseTranslatesCatalogPage() {
        getMangaCatalog().parsePage(getTranslatesCatalogURL(), PageType.TRANSLATORS);        
    }
}
