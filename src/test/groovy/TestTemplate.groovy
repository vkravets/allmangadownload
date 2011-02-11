import org.allmanga.downloader.core.manga.share.InfoItem

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir Kravets
 * Date: 11.02.11
 * Time: 17:17
 */
class TestTemplate extends GroovyTestCase{
    void testSomething() {
        def manga24 = new Manga24Catalog()
        def listManga = manga24.getMangaList()
        assert listManga != null
    }
}
