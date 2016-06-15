package com.example.bp.ebookmanager.dataprovider.woblink;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.BasicWebActionContext;
import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.formats.EpubDetails;
import com.example.bp.ebookmanager.model.formats.FormatSpecificData;
import com.example.bp.ebookmanager.model.formats.MobiDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class WoblinkBookDataParser implements BookDataParser {
    private ArrayList<Book> books;
    private HTMLScraper scraper;
    private String source;

    @Override
    public void parse(String source) {
        this.source = prepareSource(source);
        scraper = new HTMLScraper(this.source);
        createBooks();
        fillTitles();
        fillAuthors();
        fillAllFormatsData();
//        fillThumbnails();
    }

    private void createBooks() {
        scraper.evaluateXPathExpression("//p[@class=\"nw_profil_polka_ksiazka_opcje_tytul\"]/a");
        if (scraper.evaluationSuccessful()) {
            ArrayList<String> hrefs = scraper.getAttributeValueList("href");
            books = new ArrayList<>(hrefs.size());
            for (String href : hrefs) {
                Book book = new Book(createDetails("https://woblink.com" + href));
                books.add(book);
            }
        }

    }

    private BookDetails createDetails(String href) {
        BasicWebActionContext context = new BasicWebActionContext(href);
        return WoblinkWebDataProviderFactory.instance().createBookDetails(context);
    }

    private void fillTitles() {
        if (scraper.evaluationSuccessful()) {
            ArrayList<String> titles = scraper.getAttributeValueList("title");
            for (int i = 0; i < titles.size(); i++) {
                books.get(i).setTitle(titles.get(i));
            }
        }
    }

    private void fillAuthors() {
        scraper.reset(source);
        scraper.evaluateXPathExpression("//p[@class=\"nw_profil_polka_ksiazka_opcje_autor\"]/a");
        if (scraper.evaluationSuccessful()) {
            ArrayList<String> authors = scraper.getFirstChildList();
            for (int i = 0; i < authors.size(); i++) {
                Person author = Person.named(authors.get(i));
                books.get(i).setAuthor(author);
            }
        }
    }

    private void fillAllFormatsData() {
        fillFormatData("Pobierz plik w formacie EPUB", new FormatSpecificDataCreator() {
            @Override
            public String getName() {
                return EpubDetails.FORMAT_NAME;
            }
        });
        fillFormatData("Pobierz plik w formacie MOBI", new FormatSpecificDataCreator() {
            @Override
            public String getName() {
                return MobiDetails.FORMAT_NAME;
            }
        });
    }

    private void fillFormatData(String title, FormatSpecificDataCreator creator) {
        resetToFormatDataDiv();
        scraper.switchToChildrenWith("a", "title", title);
        ArrayList<String> hrefs = scraper.getAttributeValueList("href");
        for (int i = 0; i < hrefs.size(); i++) {
            String href = hrefs.get(i);
            if (href != null) {
                books.get(i).getFormatNames().add(creator.getName());
            }
        }
    }

    private void resetToFormatDataDiv() {
        scraper.reset(source);
        scraper.evaluateXPathExpression("//div[@class=\"nw_profil_polka_ksiazka_opcje_przyciski_inhalf\"]");
    }

    private void fillThumbnails() {
        scraper.reset(source);
        scraper.evaluateXPathExpression("//div[@class=\"nw_profil_polka_ksiazka_okladka nw_okladka\"]/img");
        if (scraper.evaluationSuccessful())
            for (String str : scraper.getAttributeValueList("src"))
                Log.d("Shelf", str);
    }

    @NonNull
    private String prepareSource(String source) {
        int start = source.indexOf("<div id=\"nw_profil_polka\"");
        int end = source.indexOf("<aside id=\"nw_paginacja\">", start);
        source = source.substring(start, end);
        source = repairImgTagsIfNeeded(source);
        source = source.replaceAll("<input\\b[^>]*>", "");
        return source;
    }

    @NonNull
    private String repairImgTagsIfNeeded(String source) {
        String imgTag = "<img";
        int imgIndex = source.indexOf(imgTag);
        int imgEndIndex = source.indexOf(">", imgIndex) + 1;
        if (imgEndIndex >= 1 && source.charAt(imgEndIndex - 2) == '/')
            return source;
        while (imgIndex >= 0) {
            String substring = source.substring(imgIndex, imgEndIndex);
            source = source.replace(substring, substring.replace(">", "/>"));
            imgIndex = source.indexOf(imgTag, imgEndIndex);
            imgEndIndex = source.indexOf(">", imgIndex) + 1;
        }
        return source;
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    private interface FormatSpecificDataCreator {
        String getName();
    }
}
