/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Vladimir Kravets
 */
public class ScriptManagement {

    private static Logger LOG = Logger.getLogger(ScriptManagement.class);

    private Collection<IScript> scripts;
    private String pathToScripts;
    private String engineName;
    private String scriptExtension;

    public ScriptManagement(String pathToScripts, String engineName, String scriptExtension) {
        this.pathToScripts = pathToScripts;
        this.engineName = engineName;
        this.scriptExtension = scriptExtension;
        this.scripts = new ArrayList<IScript>();
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getPathToScripts() {
        return pathToScripts;
    }

    public void setPathToScripts(String pathToScripts) {
        this.pathToScripts = pathToScripts;
    }

    public String getScriptExtension() {
        return scriptExtension;
    }

    public void setScriptExtension(String scriptExtension) {
        this.scriptExtension = scriptExtension;
    }

    public Collection<IScript> getScripts() {
        return scripts;
    }

    public void setScripts(Collection<IScript> scripts) {
        this.scripts = scripts;
    }

    public void reload() {
        File dir = new File(pathToScripts);
        if (dir.exists()) {
            FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return !name.endsWith(scriptExtension);
                }
            };
            String[] scriptFiles = dir.list(filter);

            for (String script : scriptFiles) {
                File scriptFile = new File(script);
                IScript scriptItem = new ScriptImpl(scriptFile.getName(), scriptFile.getAbsolutePath(), engineName);
                scripts.add(scriptItem);                    
            }
        } else {
            LOG.warn("Directory "+pathToScripts+" was not found. Stop scanning of scripts.");
        }

    }

}
