package org.allmanga.downloader.core.manga;

import org.allmanga.downloader.core.manga.share.ChapterInfo;
import org.allmanga.downloader.core.manga.share.InfoItem;
import org.allmanga.downloader.core.manga.share.PageType;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 22, 2010
 * Time: 6:58:59 PM
 */
public class MangaImpl extends AbstractManga implements IManga{

    private InfoItem mangaInfo;

    private String author;
    private int year = -1;
    private Collection<InfoItem> mangaGenre;
    private String description;
    private String cover;
    private Collection<InfoItem> translates;

    // return collection of chapters name
    private Collection<ChapterInfo> chapters;

    // return collection of images url in the chapter
    private HashMap<String, Collection<String>> chapter;

    private boolean isAlreadyParsed = false;

    public MangaImpl(MangaCatalog mangaCatalog) {
        super(mangaCatalog);
    }

    public MangaImpl(MangaCatalog mangaCatalog, InfoItem info) {
        super(mangaCatalog);
        this.mangaInfo = info;
    }

    public MangaImpl(MangaCatalog mangaCatalog, String name, String url) {
        this(mangaCatalog, new InfoItem(name, url));
    }

    public String getAuthor() {
        if (author == null) {
            parseMangaInfo();
            author = getMangaCatalog().getAuthor();
        }
        return author;
    }

    public int getYear() {
        if (year == -1) {
            parseMangaInfo();
            year = getMangaCatalog().getYear();
        }
        return year;
    }

    public Collection<InfoItem> getMangaGenre() {
        if (mangaGenre == null) {
            parseMangaInfo();
            mangaGenre = getMangaCatalog().getMangaGenre();
        }
        return mangaGenre;
    }

    public String getDescription() {
        if (description == null) {
            parseMangaInfo();
            description = getMangaCatalog().getDescription();
        }
        return description;
    }

    public String getCover() {
        if (cover == null) {
            parseMangaInfo();
            cover = getMangaCatalog().getCover();
        }
        return cover;
    }

    public Collection<InfoItem> getMangaTranslates() {
        if (translates == null) {
            parseMangaInfo();
            translates = getMangaCatalog().getMangaTranslates();
        }
        return translates;
    }

    public Collection<ChapterInfo> getChapters() {
        if (chapters == null) {
            parseMangaInfo();
            chapters = getMangaCatalog().getChapters();
        }
        return chapters;
    }

    public Collection<String> getChapter(String name) {
        if (chapter == null) {
            chapter = new HashMap<String, Collection<String>>();
        }
        Collection<String> chapterCache = chapter.get(name);
        if (chapterCache == null) {
            chapterCache = getMangaCatalog().getChapter(name);
            chapter.put(name, chapterCache);
        }
        return chapterCache;
    }

    public void parsePage(String url, PageType type) {
        getMangaCatalog().parsePage(url, type);
    }


    public void parseMangaInfo() {
        this.parseMangaInfo(false);
    }

    public void parseMangaInfo(boolean forceRefresh) {
        boolean needToParse = forceRefresh || !isAlreadyParsed;
        if (needToParse) {
            parsePage(mangaInfo.getUrl(), PageType.MANGA_INFO);
            isAlreadyParsed = true;
        }
    }

}
