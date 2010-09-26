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
 * Mail to vladimir.kravets-ukr@hp.com
 */
public class MangaImpl implements IManga{

    private MangaCatalog mangaCatalog;

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

    public MangaImpl(MangaCatalog mangaCatalog) {
        this.mangaCatalog = mangaCatalog;
    }

    public String getAuthor() {
        if (author == null) {
            author = mangaCatalog.getAuthor();
        }
        return author;
    }

    public int getYear() {
        if (year == -1) {
            year = mangaCatalog.getYear();
        }
        return year;
    }

    public Collection<InfoItem> getMangaGenre() {
        if (mangaGenre == null) {
            mangaGenre = mangaCatalog.getMangaGenre();
        }
        return mangaGenre;
    }

    public String getDescription() {
        if (description == null) {
            description = mangaCatalog.getDescription();
        }
        return description;
    }

    public String getCover() {
        if (cover == null) {
            cover = mangaCatalog.getCover();
        }
        return cover;
    }

    public Collection<InfoItem> getMangaTranslates() {
        if (translates == null) {
            translates = mangaCatalog.getMangaTranslates();
        }
        return translates;
    }

    public Collection<ChapterInfo> getChapters() {
        if (chapters == null) {
            chapters = mangaCatalog.getChapters();
        }
        return chapters;
    }

    public Collection<String> getChapter(String name) {
        if (chapter == null) {
            chapter = new HashMap<String, Collection<String>>();
        }
        Collection<String> chapterCache = chapter.get(name);
        if (chapterCache == null) {
            chapterCache = mangaCatalog.getChapter(name);
            chapter.put(name, chapterCache);
        }
        return chapterCache;
    }

    public MangaCatalog getMangaCatalog() {
        return mangaCatalog;
    }

    public void parsePage(String url, PageType type) {
        mangaCatalog.parsePage(url, type);
    }

}
