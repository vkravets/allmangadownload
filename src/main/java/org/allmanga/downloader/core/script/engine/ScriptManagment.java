/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import java.util.Collection;

/**
 *
 * @author Sly
 */
public class ScriptManagment {

    private Collection<IScript> scripts;
    private String pathToScripts;
    private String engineName;
    private String scriptExtension;

    public ScriptManagment(String pathToScripts, String engineName, String scriptExtension) {
        this.pathToScripts = pathToScripts;
        this.engineName = engineName;
        this.scriptExtension = scriptExtension;
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

    }

}
