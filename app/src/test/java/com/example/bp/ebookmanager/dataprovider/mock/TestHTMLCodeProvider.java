package com.example.bp.ebookmanager.dataprovider.mock;

/**
 * Created by Bartek on 2017-05-22.
 */

public class TestHTMLCodeProvider {

    private static final String LINE_ITEM = "&LineItemId";
    private static final String USER_ID = "&UserId";
    private static final String LINE_ITEM_PLACEHOLDER = "---LINEITEMID---";
    private static final String USER_ID_PLACEHOLDER = "---USERID---";

    private static final String EMPIK_LIBRARY_HEADER = "<div class=\"my-library-content search-content\">\n";
    private static final String EMPIK_LIBRARY_FOOTER = " </div>      <div class=\"digital-products-footer\">";

    private static final String METRO_2034_ITEM = "              <div class=\"search-list-item\" data-redirectprev=\"\">\n" +
            "                <span class=\"img\">\n" +
            "<a href=\"/metro-2034-glukhovsky-dmitry,p1085023335,ebooki-i-mp3-p\" title=\"Metro 2034 &nbsp;-&nbsp;Glukhovsky Dmitry\" >  \n" +
            "\n" +
            "<img src=\"https://ecsmedia.pl/c/metro-2034-p-iext33632227.jpg\"   alt=\"Metro 2034&nbsp;-&nbsp;Glukhovsky Dmitry\" /></a>                </span>\n" +
            "                <div class=\"name\">\n" +
            "<a href=\"/metro-2034-glukhovsky-dmitry,p1085023335,ebooki-i-mp3-p\" class=\"historyOrderBoxTitle\" title=\"Metro 2034 &nbsp;-&nbsp;Glukhovsky Dmitry\" >                      <strong>\n" +
            "                        Metro 2034\n" +
            "                      </strong>\n" +
            "</a>                    <div class=\"smartAuthorWrapper\">\n" +
            "  <a href=\"/szukaj/produkt?author=Glukhovsky+Dmitry\" class=\"smartAuthor\" title=\"Glukhovsky Dmitry - wszystkie produkty\" >Glukhovsky Dmitry </a>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                  <div class=\"action-buttons\">\n" +
            "                    <a class=\"download\" href=\"/media?OrderId=10100215233679&LineItemId=10100323991515&UserId=91733075\" title=\"Pobierz\">Pobierz</a>\n" +
            "                    <a class=\"review\" href=\"/metro-2034-glukhovsky-dmitry,p1085023335,ebooki-i-mp3-p?#reviewsTab\" title=\"Napisz recenzję\"><span class=\"icon fa-comment\"></span>Zrecenzuj</a>\n" +
            "                  </div>\n" +
            "              </div>\n";

    private static final String KLAMCA_ITEM = "              <div class=\"search-list-item\" data-redirectprev=\"\">\n" +
            "                <span class=\"img\">\n" +
            "<a href=\"/klamca-tom-1-cwiek-jakub,p1072746649,ebooki-i-mp3-p\" title=\"Kłamca. Tom 1 &nbsp;-&nbsp;Ćwiek Jakub\" >  \n" +
            "\n" +
            "<img src=\"https://ecsmedia.pl/c/klamca-tom-1-p-iext34472636.jpg\"   alt=\"Kłamca. Tom 1&nbsp;-&nbsp;Ćwiek Jakub\" /></a>                </span>\n" +
            "                <div class=\"name\">\n" +
            "<a href=\"/klamca-tom-1-cwiek-jakub,p1072746649,ebooki-i-mp3-p\" class=\"historyOrderBoxTitle\" title=\"Kłamca. Tom 1 &nbsp;-&nbsp;Ćwiek Jakub\" >                      <strong>\n" +
            "                        Kłamca. Tom 1\n" +
            "                      </strong>\n" +
            "</a>                    <div class=\"smartAuthorWrapper\">\n" +
            "  <a href=\"/szukaj/produkt?author=%C4%86wiek+Jakub\" class=\"smartAuthor\" title=\"Ćwiek Jakub - wszystkie produkty\" >Ćwiek Jakub </a>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                  <div class=\"action-buttons\">\n" +
            "                    <a class=\"download\" href=\"/media?OrderId=10100210623581&LineItemId=10100314262907&UserId=91733075\" title=\"Pobierz\">Pobierz</a>\n" +
            "                    <a class=\"review\" href=\"/klamca-tom-1-cwiek-jakub,p1072746649,ebooki-i-mp3-p?#reviewsTab\" title=\"Napisz recenzję\"><span class=\"icon fa-comment\"></span>Zrecenzuj</a>\n" +
            "                  </div>\n" +
            "              </div>\n";

    private static final String WEGNER_ITEM = "              <div class=\"search-list-item\" data-redirectprev=\"\">\n" +
            "                <span class=\"img\">\n" +
            "<a href=\"/gdybym-miala-brata-opowiesci-z-meekhanskiego-pogranicza-wegner-robert-m,p1117544377,ebooki-i-mp3-p\" title=\"Gdybym miała brata. Opowieści z meekhańskiego pogranicza &nbsp;-&nbsp;Wegner Robert M.\" >  \n" +
            "\n" +
            "<img src=\"https://ecsmedia.pl/c/gdybym-miala-brata-opowiesci-z-meekhanskiego-pogranicza-p-iext38617726.jpg\"   alt=\"Gdybym miała brata. Opowieści z meekhańskiego pogranicza&nbsp;-&nbsp;Wegner Robert M.\" /></a>                </span>\n" +
            "                <div class=\"name\">\n" +
            "<a href=\"/gdybym-miala-brata-opowiesci-z-meekhanskiego-pogranicza-wegner-robert-m,p1117544377,ebooki-i-mp3-p\" class=\"historyOrderBoxTitle\" title=\"Gdybym miała brata. Opowieści z meekhańskiego pogranicza &nbsp;-&nbsp;Wegner Robert M.\" >                      <strong>\n" +
            "                        Gdybym miała brata. Opowieści z meekhańskiego pogranicza\n" +
            "                      </strong>\n" +
            "</a>                    <div class=\"smartAuthorWrapper\">\n" +
            "  <a href=\"/szukaj/produkt?author=Wegner+Robert+M.\" class=\"smartAuthor\" title=\"Wegner Robert M. - wszystkie produkty\" >Wegner Robert M. </a>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                  <div class=\"action-buttons action-buttons__ebooks\">\n" +
            "                    <div class=\"select2-container select2-box\">\n" +
            "                      <button class=\"download select2-choice\" title=\"\">Pobierz\n" +
            "                      <span class=\"select2-arrow\"></span></button>\n" +
            "                    </div>\n" +
            "                    <div class=\"download-open\" ng-controller=\"KindleFormModalController as modalCtr\">\n" +
            "                            <a href=\"http://ebook.empik.com/transakcja/10100271438551/pozycja/10100452954443/licencja/1/plik/1/format/epub\">\n" +
            "                              epub\n" +
            "                            </a>\n" +
            "                            <a href=\"javascript:void(0)\" ng-cloak ng-click=\"modalCtr.sendTransactionIdAndDownloadLink(10100452954443, 'http://ebook.empik.com/transakcja/10100271438551/pozycja/10100452954443/licencja/1/plik/1/format/mobi')\">mobi</a>\n" +
            "                    </div>\n" +
            "                    <a class=\"review\" href=\"/gdybym-miala-brata-opowiesci-z-meekhanskiego-pogranicza-wegner-robert-m,p1117544377,ebooki-i-mp3-p?#reviewsTab\" title=\"\">Zrecenzuj<span class=\"icon fa-comment\"></span></a>\n" +
            "                  </div>\n" +
            "\n" +
            "              </div>\n";

    private static String cropSource(String source) {
        int start = source.indexOf("<div class=\"my-library-content search-content\">");
        int end = source.indexOf("<div class=\"digital-products-footer\">", start);
        return source.substring(start, end);
    }

    private static String fixSource(String source) {
        source = "<!DOCTYPE html [\n" +
                "    <!ENTITY nbsp \"&#160;\"> \n" +
                "    <!ENTITY raquo \"&#187;\"> \n" +
                "    <!ENTITY rsaquo \"&#8250;\"> \n" +
                "]>\n" + source;
        source = source.replaceAll(LINE_ITEM, LINE_ITEM_PLACEHOLDER);
        source = source.replaceAll(USER_ID, USER_ID_PLACEHOLDER);
        source = source.replaceAll("ng-cloak", "");
        return source;
    }

    public static String getEmpikLibrarySource() {
        return EMPIK_LIBRARY_HEADER +
                METRO_2034_ITEM +
                KLAMCA_ITEM +
                WEGNER_ITEM +
                EMPIK_LIBRARY_FOOTER;
    }

    public static String getCroppedEmpikAudiobookLibrarySource() {
        String result = EMPIK_LIBRARY_HEADER +
                METRO_2034_ITEM +
                KLAMCA_ITEM +
                EMPIK_LIBRARY_FOOTER;
        String cropped = cropSource(result);
        return fixSource(cropped);
    }

    public static String getCroppedEmpikEbookLibrarySource() {
        String result = EMPIK_LIBRARY_HEADER +
                WEGNER_ITEM +
                EMPIK_LIBRARY_FOOTER;
        String cropped = cropSource(result);
        return fixSource(cropped);
    }
}
