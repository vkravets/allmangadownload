/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import org.allmanga.downloader.core.manga.MangaCatalog;
import org.allmanga.downloader.core.manga.MangaCatalogImpl;
import org.allmanga.downloader.core.manga.MangaImpl;
import org.allmanga.downloader.core.manga.share.ChapterInfo;
import org.allmanga.downloader.core.manga.share.InfoItem;
import org.apache.log4j.Logger;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 27, 2010
 * Time: 12:35:39 AM
 */
public class ScriptImpl implements IScript {

    private static Logger LOG = Logger.getLogger(ScriptImpl.class);

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
//        IScript script = new ScriptImpl("Manga24", "scripts\\manga24.py", "jython");
        IScript script = new ScriptImpl("Manga24", "scripts\\Manga24.groovy", "groovy");
        MangaCatalog catalog = script.getManga();
        MangaCatalogImpl mangaCatalog = new MangaCatalogImpl(catalog);

//        for (InfoItem info : mangaCatalog.getGenreCatalog()) {
//            System.out.println(info.getName() + " -> " + info.getUrl());
//        }

//        for (InfoItem info : mangaCatalog.getTranslatesCatalog()) {
//            System.out.println(info.getName() + " -> " + info.getUrl());
//        }

        Collection<InfoItem> mangaList = mangaCatalog.getMangaList();
        int i = 0;
        for (InfoItem info : mangaList) {
            System.out.println(info.getUrl());
//            MangaImpl manga = new MangaImpl(catalog, info);
//            System.out.println(manga.getAuthor());
//            System.out.println(manga.getCover());
//            for (InfoItem genre : manga.getMangaGenre()) {
//                System.out.println(genre.getName());
//            }
//            for (ChapterInfo chapterName : manga.getChapters()) {
//                System.out.println(chapterName.getName() + " [" + chapterName.getTranslator() + "] " + chapterName.getUrl());
//                System.out.println(manga.getChapter(chapterName.getName()));
//            }
//            System.out.println(manga.getDescription());
//            i++;
//            if (i >= 1) break;
        }
        
    }

}
