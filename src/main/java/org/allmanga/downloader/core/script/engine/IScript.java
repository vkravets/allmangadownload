/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import org.allmanga.downloader.core.manga.MangaCatalog;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 27, 2010
 * Time: 12:35:39 AM
 */
public interface IScript {

    public String getName();
    public String getPath();
    public MangaCatalog getManga();

}
