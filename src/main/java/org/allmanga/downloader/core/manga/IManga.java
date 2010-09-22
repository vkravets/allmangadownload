/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.manga;

import java.util.Collection;

/**
 *
 * @author Sly
 */
public interface IManga {

    public void setName(String name);

    public String getName();
    public String getAuthor();
    public int getYear();
    public Collection<String> getMangaGenre();
    public String getDescription();
    public String getCover();
    public Collection<String> getTranslates();

    public String getURL();
    public Collection<String> getChapters();
    public Collection<String> getChapter(String name);


}
