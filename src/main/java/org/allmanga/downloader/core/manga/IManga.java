/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.manga;

import org.allmanga.downloader.core.manga.share.ChapterInfo;
import org.allmanga.downloader.core.manga.share.InfoItem;

import java.util.Collection;

/**
 *
 * @author Sly
 */
public interface IManga {

    public void parsePage(String url);
    public String getAuthor();
    public int getYear();
    public Collection<InfoItem> getMangaGenre();
    public String getDescription();
    public String getCover();
    public Collection<InfoItem> getTranslates();

    public Collection<ChapterInfo> getChapters();
    public Collection<String> getChapter(String name);

}
