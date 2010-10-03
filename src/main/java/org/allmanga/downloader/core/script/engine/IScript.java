/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import org.allmanga.downloader.core.manga.MangaCatalog;

/**
 *
 * @author Vladimir Kravets
 */
public interface IScript {

    public String getName();
    public String getPath();
    public MangaCatalog getManga();

}
