/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allmanga.downloader.core.script.engine;

import org.allmanga.downloader.core.manga.MangaCatalog;
import org.apache.log4j.Logger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 27, 2010
 * Time: 12:35:39 AM
 */

public class ScriptEngineManager {

    private static HashMap<String, ScriptEngineInfo> engines;
    private static javax.script.ScriptEngineManager scriptEngineManager;

    private static Logger LOG = Logger.getLogger(ScriptEngineManager.class);

    public static HashMap<String, ScriptEngineInfo> getEnginesList() {
        javax.script.ScriptEngineManager mgr = getEngineManager();
        List<ScriptEngineFactory> factories = mgr.getEngineFactories();
        HashMap<String, ScriptEngineInfo> engines = new HashMap<String, ScriptEngineInfo>();
        for (ScriptEngineFactory factory : factories) {
            ScriptEngineInfo engineInfo = new ScriptEngineInfo(factory.getEngineName(),
                                                               factory.getEngineVersion(),
                                                               factory.getLanguageName(),
                                                               factory.getLanguageVersion(),
                                                               factory.getNames(),
                                                               factory.getScriptEngine());
            engines.put(factory.getEngineName(), engineInfo);
        }
        return engines;
    }

    private static HashMap<String, ScriptEngineInfo> getEngines() {
        if (engines == null) {
            engines = getEnginesList();
        }
        return engines;

    }

    private static javax.script.ScriptEngineManager getEngineManager() {
        if (scriptEngineManager == null) {
            scriptEngineManager = new javax.script.ScriptEngineManager();
        }
        return scriptEngineManager;
    }


    public static ScriptEngineInfo getEngineByName(String name) {
        HashMap<String, ScriptEngineInfo> listEngines = getEngines();
        ScriptEngineInfo engine = listEngines.get(name);
        if (engine == null) {
            for (ScriptEngineInfo scriptEngine : listEngines.values()) {
                if (scriptEngine.getEngineAliases() != null) {
                    if (scriptEngine.getEngineAliases().contains(name)) {
                        return scriptEngine;
                    }
                }
            }
        }
        return engine;
    }

    public static Object runScript(String fullPathName, ScriptEngineInfo engineInfo) throws FileNotFoundException, ScriptException {
        Object data = null;
        ScriptEngine scriptEngine = engineInfo.getScriptEngine();
        File scriptFile = new File(fullPathName);
        InputStream stream = new FileInputStream(scriptFile);
        InputStreamReader reader = new InputStreamReader(stream);
        try {
            Object manga = scriptEngine.eval(reader);
		    if (manga == null) {
                manga = ((Invocable)scriptEngine).invokeFunction("getManga");
            }
            data = ((Invocable)scriptEngine).getInterface(manga, MangaCatalog.class);
        } catch (NoSuchMethodException e) {
            LOG.warn("Main function getManga() was not found in the " + scriptFile.getName() + "Please check that needed function exists and return correct data");
        }

        return data;
    }

}
