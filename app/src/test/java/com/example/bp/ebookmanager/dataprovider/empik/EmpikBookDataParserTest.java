package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.android.config.AndroidConfiguration;
import com.example.bp.ebookmanager.android.dataprovider.HeadlessWebClient;
import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.config.Configuration;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebClient;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.formats.FormatDetails;
import com.example.bp.ebookmanager.model.formats.Mp3Details;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class EmpikBookDataParserTest {
    private String source = "  <div class=\"LibraryProductsList\">\n" +
            "  \n" +
            "<div class=\"ebookProductLibrary\">\n" +
            "    <div>\n" +
            "  <div class=\"productBox-450Pic\">\n" +
            "<a href=\"/metro-2034-glukhovsky-dmitry,p1085023335,ebooki-i-mp3-p\" class=\"productBox-450Pic\" title=\"Metro 2034 &nbsp;-&nbsp;Glukhovsky Dmitry\"  rel=\"nofollow\"><img src=\"https://ecsmedia.pl/c/metro-2034-d-iext33632227.jpg\"   alt=\"Metro 2034&nbsp;-&nbsp;Glukhovsky Dmitry\" /></a>  </div>\n" +
            "        <div>\n" +
            "<a href=\"/metro-2034-glukhovsky-dmitry,p1085023335,ebooki-i-mp3-p\" class=\"productTitle\" title=\"Metro 2034 &nbsp;-&nbsp;Glukhovsky Dmitry\" >      <span class=\"Title\">\n" +
            "             Metro 2034\n" +
            "\n" +
            "        </span>\n" +
            "</a><a href=\"/szukaj/produkt?author=Glukhovsky+Dmitry\" class=\"author11\" title=\"Glukhovsky Dmitry - wszystkie produkty\" >Glukhovsky Dmitry</a>            <span class=\"type\">    audiobook mp3\n" +
            "</span>\n" +
            "    <div class=\"efileFormat\">\n" +
            "      <span>\n" +
            "                  <span>Pobierz: <a href=\"/media?OrderId=10100215233679&LineItemId=10100323991515&UserId=91733075\" >MP3</a></span>\n" +
            "            </span>\n" +
            "          </div>\n" +
            "          <span class=\"readNow\"><a href=\"#\" id=\"p1085023335\" class=\"btnAddReview addReview\">Dodaj recenzję &rsaquo;</a></span>\n" +
            "        </div>\n" +
            "    </div>\n" +

            "  \n" +
            "<div class=\"ebookProductLibrary\">\n" +
            "    <div>\n" +
            "  <div class=\"productBox-450Pic\">\n" +
            "<a href=\"/klamca-cwiek-jakub,p1072746649,ebooki-i-mp3-p\" class=\"productBox-450Pic\" title=\"Kłamca. Tom 1 &nbsp;-&nbsp;Ćwiek Jakub\"  rel=\"nofollow\"><img src=\"https://ecsmedia.pl/c/klamca-tom-1-d-iext34472636.jpg\"   alt=\"Kłamca. Tom 1&nbsp;-&nbsp;Ćwiek Jakub\"></a>  </div>\n" +
            "        <div>\n" +
            "<a href=\"/klamca-cwiek-jakub,p1072746649,ebooki-i-mp3-p\" class=\"productTitle\" title=\"Kłamca. Tom 1 &nbsp;-&nbsp;Ćwiek Jakub\" >      <span class=\"Title\">\n" +
            "             Kłamca. Tom 1\n" +
            "\n" +
            "        </span>\n" +
            "</a><a href=\"/szukaj/produkt?author=%C4%86wiek+Jakub\" class=\"author11\" title=\"Ćwiek Jakub - wszystkie produkty\" >Ćwiek Jakub</a>            <span class=\"type\">    audiobook mp3\n" +
            "</span>\n" +
            "    <div class=\"efileFormat\">\n" +
            "      <span>\n" +
            "                  <span>Pobierz: <a href=\"/media?OrderId=10100210623581&LineItemId=10100314262907&UserId=91733075\" >MP3</a></span>\n" +
            "            </span>\n" +
            "          </div>\n" +
            "          <span class=\"readNow\"><a href=\"#\" id=\"p1072746649\" class=\"btnAddReview addReview\">Dodaj recenzję &rsaquo;</a></span>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "  </div>\n" +
            "<div class=\"ebookProductLibrary\">\n" +
            "    <div>\n" +
            "  <div class=\"productBox-450Pic\">\n" +
            "<a href=\"/gdybym-miala-brata-opowiesci-z-meekhanskiego-pogranicza-wegner-robert-m,p1117544377,ebooki-i-mp3-p\" class=\"productBox-450Pic\" title=\"Gdybym miała brata. Opowieści z meekhańskiego pogranicza &nbsp;-&nbsp;Wegner Robert M.\"  rel=\"nofollow\"><img src=\"https://ecsmedia.pl/c/gdybym-miala-brata-opowiesci-z-meekhanskiego-pogranicza-d-iext37243764.jpg\"   alt=\"Gdybym miała brata. Opowieści z meekhańskiego pogranicza&nbsp;-&nbsp;Wegner Robert M.\" /></a>  </div>\n" +
            "        <div>\n" +
            "<a href=\"/gdybym-miala-brata-opowiesci-z-meekhanskiego-pogranicza-wegner-robert-m,p1117544377,ebooki-i-mp3-p\" class=\"productTitle\" title=\"Gdybym miała brata. Opowieści z meekhańskiego pogranicza &nbsp;-&nbsp;Wegner Robert M.\" >      <span class=\"Title\">\n" +
            "             Gdybym miała brata. Opowieści z meekhańskiego  ...\n" +
            "\n" +
            "        </span>\n" +
            "</a><a href=\"/szukaj/produkt?author=Wegner+Robert+M.\" class=\"author11\" title=\"Wegner Robert M. - wszystkie produkty\" >Wegner Robert M.</a>            <span class=\"type\">    ebook\n" +
            "</span>\n" +
            "    <div class=\"efileFormat\">\n" +
            "              <span>\n" +
            "                <span id=\"transactionid\" data-transactionid=\"10100452954443\"></span>\n" +
            "                Pobierz:\n" +
            "                    <a href=\"http://ebook.empik.com/transakcja/10100271438551/pozycja/10100452954443/licencja/1/plik/1/format/epub\" >EPUB</a>\n" +
            "                    , \n" +
            "                    <a href=\"http://ebook.empik.com/transakcja/10100271438551/pozycja/10100452954443/licencja/1/plik/1/format/mobi\" >MOBI</a>\n" +
            "                    \n" +
            "              </span>\n" +
            "          </div>\n" +
            "          <span class=\"readNow\"><a href=\"#\" id=\"p1117544377\" class=\"btnAddReview addReview\">Dodaj recenzję &rsaquo;</a></span>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>" +
            "</div>\n" +
            "  <div class=\"dotLine\"></div>";

    @BeforeClass
    public static void initialize() {
        ConfigManager.set(new Configuration() {
            @Override
            public WebClientFactory getWebClientFactory() {
                return new WebClientFactory() {
                    @Override
                    public WebClient getHeadlessClient() {
                        return new HeadlessWebClient();
                    }

                    @Override
                    public WebClient getVisualClient() {
                        return null;
                    }
                };
            }

            @Override
            public UserActionEnabler getUserActionEnabler() {
                return null;
            }

            @Override
            public List<BookDataProvider> getDataProviders() {
                return null;
            }

            @Override
            public BookDataProvider getLocalDataProvider() {
                return null;
            }
        });
    }

    private List<Book> parseBooks() {
        EmpikBookDataParser parser = new EmpikBookDataParser();
        //when
        parser.parse(source);
        return parser.getBooks();
    }

    @Test
    public void parser_parsesAuthorNamesProperly() {
        List<Book> books = parseBooks();
        //then
        assertEquals("Metro 2034", books.get(0).getTitle());
        assertEquals("Kłamca. Tom 1", books.get(1).getTitle());
        assertEquals("Gdybym miała brata. Opowieści z meekhańskiego pogranicza", books.get(2).getTitle());
    }

    @Test
    public void parser_parsesBookNamesProperly() {
        List<Book> books = parseBooks();
        //then
        assertEquals("Glukhovsky Dmitry", books.get(0).getAuthor().getName());
        assertEquals("Ćwiek Jakub", books.get(1).getAuthor().getName());
        assertEquals("Wegner Robert M.", books.get(2).getAuthor().getName());
    }

    @Test
    public void parser_recognizesMp3FormatProperly() {
        List<Book> books = parseBooks();
        Book book = books.get(1);
        List<FormatDetails> formatDetailsList = book.getFormatDetailsList();
        //then
        assertEquals(1, formatDetailsList.size());
        assertTrue(formatDetailsList.get(0) instanceof Mp3Details);
    }
}
