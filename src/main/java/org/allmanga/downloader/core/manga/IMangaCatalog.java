/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.manga;

import org.allmanga.downloader.core.manga.share.InfoItem;

import java.util.Collection;

 /**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 27, 2010
 * Time: 12:35:39 AM
 */
public interface IMangaCatalog {
    public Collection<InfoItem> getMangaList();
    public Collection<InfoItem> getTranslatesCatalog();
    public Collection<InfoItem> getGenreCatalog();

    public String getMangaCatalogURL();
    public String getTranslatesCatalogURL();
    public String getGenreCatalogURL();
}
