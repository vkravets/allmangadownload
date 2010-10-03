package org.allmanga.downloader.core.manga.share;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 23, 2010
 * Time: 9:31:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChapterInfo extends InfoItem{

    private String translator;

    public ChapterInfo(String name, String translator, String url) {
        super(name, url);
        this.translator = translator;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }
}
