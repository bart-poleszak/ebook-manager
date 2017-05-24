package com.example.bp.ebookmanager.dataprovider.empik;

import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.BasicWebActionContext;
import com.example.bp.ebookmanager.dataprovider.BookDataParser;
import com.example.bp.ebookmanager.dataprovider.WebActionContext;
import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.URLThumbnail;
import com.example.bp.ebookmanager.model.WebBookDetails;
import com.example.bp.ebookmanager.model.formats.FormatDetails;
import com.example.bp.ebookmanager.model.formats.Mp3Details;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class EmpikBookDataParser implements BookDataParser {

    public static final String LINE_ITEM = "&LineItemId";
    public static final String USER_ID = "&UserId";
    public static final String LINE_ITEM_PLACEHOLDER = "---LINEITEMID---";
    public static final String USER_ID_PLACEHOLDER = "---USERID---";
    public static final String EMPIK_COM = "http://empik.com";
    private final EmpikFileFormatParser fileFormatParser;
    private ArrayList<Book> result;
    private ArrayList<WebBookDetails> detailsList;
    private String source;

    public EmpikBookDataParser(EmpikFileFormatParser fileFormatParser) {
        this.fileFormatParser = fileFormatParser;
    }

    @Override
    public void parse(String source) {
        this.source = source;
        this.source = cropSource();
        fixSource();
        HTMLScraper scraper = new HTMLScraper(this.source);

        scraper.evaluateXPathExpression("//a[@class=\"smartAuthor\"]");
        ArrayList<String> authors = scraper.getFirstChildList();
        result = new ArrayList<>(authors.size());
        detailsList = new ArrayList<>(authors.size());

        scraper.reset();
        scraper.evaluateXPathExpression("//a[@class=\"historyOrderBoxTitle\"]");
        ArrayList<String> detailsHrefs = scraper.getAttributeValueList("href");

        for (String detailsHref : detailsHrefs) {
            WebActionContext context = new BasicWebActionContext(EMPIK_COM + detailsHref);
            WebBookDetails details = EbookEmpikWebDataProviderFactory.instance().createBookDetails(context);
            detailsList.add(details);
        }

        fileFormatParser.parse(scraper, detailsList);

        for (int i = 0; i < authors.size(); i++) {
            Book book = new Book(detailsList.get(i));
            result.add(book);
        }
        for (int i = 0; i < authors.size(); i++) {
            result.get(i).setAuthor(Person.named(authors.get(i).trim()));
        }

        scraper.reset();
        scraper.evaluateXPathExpression("//a[@class=\"historyOrderBoxTitle\"]/strong");
        ArrayList<String> titles = scraper.getFirstChildList();

        for (int i = 0; i < result.size(); i++) {
            String title = titles.get(i);
            result.get(i).setTitle(title.trim());
            result.get(i).setId("Empik" + title.trim());
        }

        scraper.reset();
        scraper.evaluateXPathExpression("//img");
        ArrayList<String> thumbnailUrls = scraper.getAttributeValueList("src");

        for (int i = 0; i < result.size(); i++) {
            result.get(i).setThumbnail(new URLThumbnail(thumbnailUrls.get(i)));
        }
    }

    private void fixSource() {
        this.source = "<!DOCTYPE html [\n" +
                "    <!ENTITY nbsp \"&#160;\"> \n" +
                "    <!ENTITY raquo \"&#187;\"> \n" +
                "    <!ENTITY rsaquo \"&#8250;\"> \n" +
                "]>\n" + this.source;
        source = source.replaceAll(LINE_ITEM, LINE_ITEM_PLACEHOLDER);
        source = source.replaceAll(USER_ID, USER_ID_PLACEHOLDER);
    }

    private String cropSource() {
        int start = source.indexOf("<div class=\"my-library-content search-content\">");
        int end = source.indexOf("<div class=\"digital-products-footer\">", start);
        return source.substring(start, end);
    }

    @Override
    public List<Book> getBooks() {
        return result;
    }
}
