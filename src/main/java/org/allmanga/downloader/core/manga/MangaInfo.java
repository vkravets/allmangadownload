package org.allmanga.downloader.core.manga;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: Sep 22, 2010
 * Time: 9:12:19 PM
 * Mail to vladimir.kravets-ukr@hp.com
 */
public class MangaInfo {

    private String name;
    private String url;

    public MangaInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
