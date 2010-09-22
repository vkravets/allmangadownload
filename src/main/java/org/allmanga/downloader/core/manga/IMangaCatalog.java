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
public interface IMangaCatalog {

    public Collection<IManga> getMangaList();
    public Collection<String> getTranslatesCatalog();
    public Collection<String> getGenreCatalog();

}
