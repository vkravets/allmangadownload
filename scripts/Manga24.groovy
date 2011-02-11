import org.allmanga.downloader.core.manga.MangaCatalog
import org.allmanga.downloader.core.manga.share.PageType
import org.allmanga.downloader.core.manga.share.InfoItem
import org.allmanga.downloader.core.manga.share.ChapterInfo
/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 11.02.11
 * Time: 15:43
 */
class Manga24 implements MangaCatalog {

    void parsePage(String url, PageType type) {
        println(url + " " + type)
    }

    String getAuthor() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    int getYear() {
        return 0  //To change body of implemented methods use File | Settings | File Templates.
    }

    Collection<InfoItem> getMangaGenre() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    String getDescription() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    String getCover() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    Collection<InfoItem> getMangaTranslates() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    Collection<ChapterInfo> getChapters() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    Collection<String> getChapter(String name) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    Collection<InfoItem> getMangaList() {
        ArrayList<InfoItem> mangaList = new ArrayList<InfoItem>()
        mangaList.add(new InfoItem('test', 'testURL'))
        return mangaList
    }

    Collection<InfoItem> getTranslatesCatalog() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    Collection<InfoItem> getGenreCatalog() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    String getMangaCatalogURL() {
        return "manga catalog url"
    }

    String getTranslatesCatalogURL() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    String getGenreCatalogURL() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

}

new Manga24();