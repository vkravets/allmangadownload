/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import org.allmanga.downloader.core.manga.MangaCatalog;
import org.apache.log4j.Logger;

import javax.script.ScriptException;
import java.io.FileNotFoundException;

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
        MangaCatalog mangaCatalog = script.getManga();
        System.out.println(mangaCatalog.getAuthor());
    }

}
