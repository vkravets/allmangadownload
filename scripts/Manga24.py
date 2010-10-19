from org.allmanga.downloader.core.manga.share import InfoItem
from org.allmanga.downloader.core.manga.share import ChapterInfo
from org.allmanga.downloader.core.manga.share import PageType

from net.sf.json import JSONSerializer

from java.util.regex import *
from java.lang import *

import org
import java

class Manga24:
    """Constructor"""
    def __init__(self):
        self.baseUrl = "http://manga24.ru/"
        self.doc = None
        self.url = None
        self.author = None
        self.year = None
        self.mangaGenre = None
        self.description = None
        self.cover = None
        self.mangaTranslators = None
        self.chapters = None
        self.chapter = None

        self.mangaList = None
        self.traslatorsCatalog = None
        self.genreCatalog = None
        pass

    ### Manga methods ###

    # Parse the manga page and save needed info
    def parsePage(self, url, type):
        """ Perform parsing of mange page and save all needed information """
        print "Parsing page %s [%s]" % (url, type)

        if (self.url == url):
            return
        self.url = url

        parser = org.cyberneko.html.parsers.DOMParser()
        parser.parse(self.url)

        document = parser.getDocument()
        domReader = org.dom4j.io.DOMReader()
        self.doc = domReader.read(document)

        if (type == PageType.MANGA_INFO):
            self.setMangaInfo()

        if (type == PageType.GENRES):
            self.setCatalogGenres()

        if (type == PageType.MANGA_LIST):
            self.setCatalogManga()

        if (type == PageType.TRANSLATORS):
            self.setCatalogTranslators()

        return

    # Return type: String
    def getAuthor(self):
        return self.author

    # Return type: int
    def getYear(self):
        return self.year

    # Return type: Collection<String>
    def getMangaGenre(self):
        return self.mangaGenre

    # Return type: String
    def getDescription(self):
        return self.description

    # Return type: String
    def getCover(self):
        return self.cover

    # Return type: Collection<String>
    def getMangaTranslates(self):
        """ Get list of translates of manga from URL """
        return

    # Return type: Collection<String>
    def getChapters(self):
        name = self.doc.selectNodes("//DIV[@class='chapters']/UL/LI")
        collection = java.util.ArrayList()
        for item in name:
            iterator = item.elementIterator("EM");
            chapterName = ""
            chapterURL = ""
            chapterTranslate = ""
            if iterator.hasNext():
                chapterNameElement = iterator.next()
                chapterName = chapterNameElement.getText()
            iterator = item.elementIterator("SPAN")
            if iterator.hasNext():
                chapterURLElement = iterator.next()
                chapterURLElement = chapterURLElement.elementIterator("A").next()
                chapterURL = chapterURLElement.attribute("href").getText()
                if (chapterURL.find(self.baseUrl) == -1):
                    chapterURL = self.baseUrl + chapterURL
            chapterInfo = ChapterInfo(chapterName.strip(), chapterTranslate.strip(), chapterURL.strip())
            collection.add(chapterInfo)
        self.chapters = collection
        return self.chapters

    # Return type: Collection<String>
    def getChapter(self, name):
        print "Getting chapter \"%s\" " % name
        chapterList = self.chapters
        for item in chapterList:
            if (item.getName() == name):
                return self.parseChapter(item.getUrl())
        return None

    ### MangaCatalog Catalog methods ###

    # Return type: Collection<InfoItem>
    def getMangaList(self):
        return self.mangaList

    # Return type: Collection<InfoItem>
    def getTranslatesCatalog(self):
        return self.traslatorsCatalog

    # Return type: Collection<InfoItem>
    def getGenreCatalog(self):
        return self.genreCatalog

    def getTranslatesCatalogURL(self):
        return self.baseUrl + "translators/"

    def getGenreCatalogURL(self):
        return self.baseUrl + "all/"

    def getMangaCatalogURL(self):
        return self.baseUrl + "all/"

    ### Some parsing stuff

    def parseChapter(self, url):
        # Get all links page
        self.parsePage(url, None)
        scriptElement = self.doc.selectSingleNode("//SCRIPT[3]")
        scriptText = scriptElement.getText()

        pattern = Pattern.compile(".*Reader.init\((\{.+\})\).*", Pattern.DOTALL)
        matcher = pattern.matcher(scriptText)
        collection = java.util.ArrayList()
        if matcher.matches():
            json = JSONSerializer.toJSON(matcher.group(1))
            imagesArray = json.get("images")
            imagesDir = json.get("dir")
            for imageItem in imagesArray:
                image = imagesDir + imageItem.get(0)
                collection.add(image)


        return collection

    def setMangaInfo(self):

        ## get author
        name = self.doc.selectSingleNode("//DIV[@id='content']/P[1]")
        # split by Genre - russian string "Жанр"
        string = name.getText().split(u"\u0416\u0430\u043d\u0440:")
        # split by Author - russian string "Автор"
        string = string[0].split(u"\u0410\u0432\u0442\u043e\u0440:")
        self.author = string[1].strip()

        ## get year
        self.year = 0

        ## Get genres of manga
        name = self.doc.selectNodes("//DIV[@id='content']/P[1]/A")
        collection = java.util.ArrayList()
        for item in name:
            info = InfoItem(item.getText(), self.baseUrl+item.attribute("href").getText())
            collection.add(info)
        self.mangaGenre = collection

        ## Get Description of manga
        name = self.doc.selectSingleNode("//DIV[@id='content']/P[2]")
        self.description = name.getText()

        ## Get path to cover of manga
        name = self.doc.selectSingleNode("//DIV[@id='content']/IMG[@id='imgright']")
        if name is not None:
            coverUrl = name.attribute("src").getText()
            if  (coverUrl.find(self.baseUrl) != -1):
                self.cover = coverUrl
            else:
                self.cover = self.baseUrl + coverUrl
        ### TODO: Other Info from manga page

        return

    def setCatalogGenres(self):
        name = self.doc.selectNodes("//DIV[@id='content']/P/CENTER/SMALL/*")
        collection = java.util.ArrayList()
        for item in name:
            info = InfoItem(item.getText(), self.baseUrl+item.attribute("href").getText())
            collection.add(info)
        self.genreCatalog = collection
        return

    def setCatalogManga(self):
        name = self.doc.selectNodes("//DIV[@id='content']/P/DL/DD/A")
        collection = java.util.ArrayList()
        for item in name:
            info = InfoItem(item.getText(), self.baseUrl+item.attribute("href").getText())
            collection.add(info)
        self.mangaList = collection
        return

    def setCatalogTranslators(self):
        name = self.doc.selectNodes("//DIV[@id='content']/UL/DL/DT/STRONG/A")
        collection = java.util.ArrayList()
        for item in name:
            text = item.getText().strip()
            if (len(text) > 0):
                info = InfoItem(text, self.baseUrl+item.attribute("href").getText())
                collection.add(info)
        self.traslatorsCatalog = collection
        return

def getManga():
    return Manga24()