/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.manga;

import org.allmanga.downloader.core.manga.share.ChapterInfo;
import org.allmanga.downloader.core.manga.share.InfoItem;
import org.allmanga.downloader.core.manga.share.PageType;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 27, 2010
 * Time: 12:35:39 AM
 */
public interface IManga {

    public void parsePage(String url, PageType type);
    public String getAuthor();
    public int getYear();
    public Collection<InfoItem> getMangaGenre();
    public String getDescription();
    public String getCover();
    public Collection<InfoItem> getMangaTranslates();

    public Collection<ChapterInfo> getChapters();
    public Collection<String> getChapter(String name);

}
