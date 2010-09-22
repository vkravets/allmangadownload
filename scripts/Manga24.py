class Manga24:
    """Constructor"""
    def __init__(self):
        pass

    ### Manga methods ###

    # Parse the manga page and save needed info
    def parsePage(self, url):
        """ Perform parsing of mange page and save all needed information """
        return

    # Return type: String
    def getAuthor(self):
        """ Get Author of manga from URL """
        return "Test author"

    # Return type: int
    def getYear(self):
        """ Get year of manga from URL """
        return

    # Return type: Collection<String>
    def getGenre(self):
        """ Get fenres of manga from URL """
        return

    # Return type: String
    def getDescription(self):
        """ Get Description of manga from URL """
        return

    # Return type: String
    def getCover(self):
        """ Get path to cover of manga from URL """
        return

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

    # Return type: Collection<MangaInfo>
    def getMangaList(self):
        return

    # Return type: Collection<String>
    def getTranslates(self):
        return

    # Return type: Collection<String>
    def getGenres(self):
        return

def getManga():
    return Manga24()