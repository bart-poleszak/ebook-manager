package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.AndroidDataStore;
import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.config.Configuration;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebClient;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.dataprovider.utils.MockWebClient;
import com.example.bp.ebookmanager.dataprovider.utils.TestHTMLCodeProvider;
import com.example.bp.ebookmanager.dataprovider.utils.UrlExtractorThumbnailVisitor;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.WebBookDetails;
import com.example.bp.ebookmanager.model.formats.FormatDetails;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class EmpikBookDataParserTest {

    private static String source;
    private static final int METRO_INDEX = 0;
    private static final int KLAMCA_INDEX = 1;
    private static final int WEGNER_INDEX = 2;

    @BeforeClass
    public static void initialize() {
        ConfigManager.set(new Configuration() {
            @Override
            public WebClientFactory getWebClientFactory() {
                return new WebClientFactory() {
                    @Override
                    public WebClient getHeadlessClient() {
                        return new MockWebClient();
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

            @Override
            public AndroidDataStore getDataStore() {
                return null;
            }
        });

        source = TestHTMLCodeProvider.getEmpikLibrarySource();
    }

    private List<Book> parseBooksWithoutFormatDetails() {
        EmpikBookDataParser parser = new EmpikBookDataParser(new EmpikFileFormatParser() {
            @Override
            public void parse(HTMLScraper scraper, ArrayList<WebBookDetails> detailsList) {
            }
        });
        parser.parse(source);
        return parser.getBooks();
    }

    private List<FormatDetails> getFormatDetailsForWegner() {
        EmpikBookDataParser parser = new EmpikBookDataParser(new EbookEmpikFileFormatParser());
        parser.parse(source);
        List<Book> books = parser.getBooks();
        //when
        Book book = books.get(WEGNER_INDEX);
        return book.getFormatDetailsList();
    }

    @Test
    public void parser_parsesBookNamesProperly() {
        List<Book> books = parseBooksWithoutFormatDetails();
        //then
        assertEquals("Metro 2034", books.get(METRO_INDEX).getTitle());
        assertEquals("Kłamca. Tom 1", books.get(KLAMCA_INDEX).getTitle());
        assertEquals("Gdybym miała brata. Opowieści z meekhańskiego pogranicza", books.get(WEGNER_INDEX).getTitle());
    }

    @Test
    public void parser_parsesAuthorNamesProperly() {
        List<Book> books = parseBooksWithoutFormatDetails();
        //then
        assertEquals("Glukhovsky Dmitry", books.get(METRO_INDEX).getAuthor().getName());
        assertEquals("Ćwiek Jakub", books.get(KLAMCA_INDEX).getAuthor().getName());
        assertEquals("Wegner Robert M.", books.get(WEGNER_INDEX).getAuthor().getName());
    }

    @Test
    public void parser_parsesThumbnailUrlProperly() {
        List<Book> books = parseBooksWithoutFormatDetails();
        UrlExtractorThumbnailVisitor visitor = new UrlExtractorThumbnailVisitor();
        //when
        books.get(METRO_INDEX).getThumbnail().fill(visitor);
        String metroThumbnailUrl = visitor.getUrl();
        books.get(KLAMCA_INDEX).getThumbnail().fill(visitor);
        String klamcaThumbnailUrl = visitor.getUrl();
        books.get(WEGNER_INDEX).getThumbnail().fill(visitor);
        String wegnerThumbnailUrl = visitor.getUrl();

        //then
        assertEquals("https://ecsmedia.pl/c/metro-2034-p-iext33632227.jpg", metroThumbnailUrl);
        assertEquals("https://ecsmedia.pl/c/klamca-tom-1-p-iext34472636.jpg", klamcaThumbnailUrl);
        assertEquals("https://ecsmedia.pl/c/gdybym-miala-brata-opowiesci-z-meekhanskiego-pogranicza-p-iext38617726.jpg", wegnerThumbnailUrl);
    }
}
