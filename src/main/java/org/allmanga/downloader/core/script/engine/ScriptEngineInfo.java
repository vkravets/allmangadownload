/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import javax.script.ScriptEngine;
import java.util.Collection;

/**
 *
 * @author Sly
 */
class ScriptEngineInfo {

    private String engineName;
    private String engineVersion;
    private String languageName;
    private String languageVersion;
    private Collection<String> engineAliases;
    private ScriptEngine scriptEngine;

    public ScriptEngineInfo(String engineName, String engineVersion, String languageName, String languageVersion, Collection<String> engineAliases, ScriptEngine engine) {
        this.engineName = engineName;
        this.engineVersion = engineVersion;
        this.languageName = languageName;
        this.languageVersion = languageVersion;
        this.engineAliases = engineAliases;
        this.scriptEngine = engine;
    }

    public Collection<String> getEngineAliases() {
        return engineAliases;
    }

    public String getEngineName() {
        return engineName;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getLanguageVersion() {
        return languageVersion;
    }

    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    public void setScriptEngine(ScriptEngine scriptEngine) {
        this.scriptEngine = scriptEngine;
    }
}
