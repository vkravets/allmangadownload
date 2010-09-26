/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import org.allmanga.downloader.core.manga.share.InfoItem;
import org.allmanga.downloader.core.manga.MangaCatalog;
import org.allmanga.downloader.core.manga.MangaCatalogImpl;
import org.allmanga.downloader.core.manga.share.PageType;
import org.apache.log4j.Logger;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.Collection;

/**
 *
 * @author Sly
 */
public class ScriptImpl implements IScript {

    private Logger LOG = Logger.getLogger(ScriptImpl.class);

    private String scriptName;
    private String scriptPath;
    private String engineName;

    public ScriptImpl(String scriptName, String scriptPath, String engineName) {
        this.scriptName = scriptName;
        this.scriptPath = scriptPath;
        this.engineName = engineName;
    }

    public String getName() {
        return scriptName;
    }

    public String getPath() {
        return scriptPath;
    }

    public String getEngineName() {
        return engineName;
    }

    public MangaCatalog getManga() {
        MangaCatalog result = null;

        ScriptEngineInfo engineInfo =  ScriptEngineManager.getEngineByName(engineName);
        if (engineInfo != null) {
            try {
                result = (MangaCatalog) ScriptEngineManager.runScript(scriptPath, engineInfo);
            } catch (FileNotFoundException ex) {
                // TODO: Message Box, readable message
                LOG.error(ex.getMessage());
                System.out.println(ex.getMessage());
            } catch (ScriptException ex) {
                // TODO: Message Box, readable message
                LOG.error(ex.getMessage());
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }


    public static void main(String[] argv) {
        IScript script = new ScriptImpl("Manga24", "scripts\\manga24.py", "jython");
        MangaCatalogImpl mangaCatalog = new MangaCatalogImpl(script.getManga());

        mangaCatalog.parsePage(mangaCatalog.getGenreCatalogURL(), PageType.GENRES);
        for (InfoItem info : mangaCatalog.getGenreCatalog()) {
            System.out.println(info.getName() + " -> " + info.getUrl());
        }

        mangaCatalog.parsePage(mangaCatalog.getTranslatesCatalogURL(), PageType.TRANSLATORS);
        for (InfoItem info : mangaCatalog.getTranslatesCatalog()) {
            System.out.println(info.getName() + " -> " + info.getUrl());
        }

        mangaCatalog.parsePage(mangaCatalog.getMangaCatalogURL(), PageType.MANGA_LIST);
        Collection<InfoItem> mangaList = mangaCatalog.getMangaList();
        for (InfoItem info : mangaList) {
            System.out.println(info.getUrl());
            mangaCatalog.parsePage(info.getUrl(), PageType.MANGA_INFO);
            System.out.println(mangaCatalog.getAuthor());
            System.out.println(mangaCatalog.getCover());
            for (InfoItem genre : mangaCatalog.getMangaGenre()) {
                System.out.println(genre.getName());
            }
            System.out.println(mangaCatalog.getDescription());
            break;
        }
        
    }

}
