from org.allmanga.downloader.core.manga import InfoItem
import org
import java

class Manga24:
    """Constructor"""
    def __init__(self):
        self.baseUrl = "http://manga24.ru/"
        self.url = None
        pass

    ### Manga methods ###

    # Parse the manga page and save needed info
    def parsePage(self, url):
        """ Perform parsing of mange page and save all needed information """
        if (self.url == url):
            return
        self.url = url

        parser = org.cyberneko.html.parsers.DOMParser()
        parser.parse(self.url)

        document = parser.getDocument()
        domReader = org.dom4j.io.DOMReader()
        self.doc = domReader.read(document)
        return

    # Return type: String
    def getAuthor(self):
        """ Get Author of manga from URL """
        name = self.doc.selectSingleNode("//DIV[@id='content']/P[1]")
        return name.getText()

    # Return type: int
    def getYear(self):
        """ Get year of manga from URL """
        return

    # Return type: Collection<String>
    def getMangaGenre(self):
        """ Get fenres of manga from URL """
        name = self.doc.selectNodes("//DIV[@id='content']/P[1]/A")
        collection = java.util.ArrayList()
        for item in name:
            info = InfoItem(item.getText(), self.baseUrl+item.attribute("href").getText())
            collection.add(info)
        return collection

    # Return type: String
    def getDescription(self):
        """ Get Description of manga from URL """
        return

    # Return type: String
    def getCover(self):
        """ Get path to cover of manga from URL """
        name = self.doc.selectSingleNode("//DIV[@id='content']/IMG[@id='imgright']")
        return self.baseUrl + name.attribute("src").getText()

    # Return type: Collection<String>
    def getTranslates(self):
        """ Get list of translates of manga from URL """
        return

    # Return type: Collection<String>
    def getChapters(self):
        return

    # Return type: Collection<String>
    def getChapter(self, name):
        return

    ### MangaCatalog Catalog methods ###

    # Return type: Collection<InfoItem>
    def getMangaList(self):
        name = self.doc.selectNodes("//DIV[@id='content']/P/DL/DD/A")
        collection = java.util.ArrayList()
        for item in name:
            info = InfoItem(item.getText(), self.baseUrl+item.attribute("href").getText())
            collection.add(info)
        return collection

    # Return type: Collection<InfoItem>
    def getTranslatesCatalog(self):
        name = self.doc.selectNodes("//DIV[@id='content']/UL/DL/DT/STRONG/A")
        collection = java.util.ArrayList()
        for item in name:
            info = InfoItem(item.getText(), self.baseUrl+item.attribute("href").getText())
            collection.add(info)
        return collection

    # Return type: Collection<InfoItem>
    def getGenreCatalog(self):
        name = self.doc.selectNodes("//DIV[@id='content']/P/CENTER/SMALL/*")
        collection = java.util.ArrayList()
        for item in name:
            info = InfoItem(item.getText(), self.baseUrl+item.attribute("href").getText())
            collection.add(info)
        return collection

    def getTranslatesCatalogURL(self):
        return self.baseUrl + "translators/"

    def getGenreCatalogURL(self):
        return self.baseUrl + "all/"

    def getMangaCatalogURL(self):
        return self.baseUrl + "all/"

def getManga():
    return Manga24()