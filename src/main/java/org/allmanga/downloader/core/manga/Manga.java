package org.allmanga.downloader.core.manga;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 22, 2010
 * Time: 6:58:59 PM
 * Mail to vladimir.kravets-ukr@hp.com
 */
public class Manga implements IManga{

    private IManga manga;

    private String name;
    private String author;
    private int year = -1;
    private Collection<String> mangaGenre;
    private String description;
    private String cover;
    private Collection<String> translates;
    private String url;

    // return collection of chapters name
    private Collection<String> chapters;

    // return collection of images url in the chapter
    private HashMap<String, Collection<String>> chaptersCache;

    public Manga(IManga manga) {
        this.manga = manga;
    }

    public String getName() {
        if (name == null) {
            name = manga.getName();
        }
        return name;
    }

    public String getAuthor() {
        if (author == null) {
            author = manga.getAuthor();
        }
        return author;
    }

    public int getYear() {
        if (year == -1) {
            year = manga.getYear();
        }
        return year;
    }

    public Collection<String> getMangaGenre() {
        if (mangaGenre == null) {
            mangaGenre = manga.getMangaGenre();
        }
        return mangaGenre;
    }

    public String getDescription() {
        if (description == null) {
            description = manga.getDescription();
        }
        return description;
    }

    public String getCover() {
        if (cover == null) {
            cover = manga.getCover();
        }
        return cover;
    }

    public Collection<String> getTranslates() {
        if (translates == null) {
            translates = manga.getTranslates();
        }
        return translates;
    }

    public String getURL() {
        if (url == null) {
            url = manga.getURL();
        }
        return url;
    }

    public Collection<String> getChapters() {
        if (chapters == null) {
            chapters = manga.getChapters();
        }
        return chapters;
    }

    public Collection<String> getChapter(String name) {
        if (chaptersCache == null) {
            chaptersCache = new HashMap<String, Collection<String>>();
        }
        Collection<String> chapterCache = chaptersCache.get(name);
        if (chapterCache == null) {
            chapterCache = manga.getChapter(name);
            chaptersCache.put(name, chapterCache);
        }
        return chapterCache;
    }

    public void setName(String name) {
        manga.setName(name);
    }

    public IManga getManga() {
        return manga;
    }
}
