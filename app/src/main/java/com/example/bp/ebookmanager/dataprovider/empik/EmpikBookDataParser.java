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

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class EmpikBookDataParser implements BookDataParser {

    private static final String LINE_ITEM = "&LineItemId";
    private static final String USER_ID = "&UserId";
    private static final String LINE_ITEM_PLACEHOLDER = "---LINEITEMID---";
    private static final String USER_ID_PLACEHOLDER = "---USERID---";
    private static final String EMPIK_COM = "http://empik.com";
    private ArrayList<Book> result;
    private ArrayList<WebBookDetails> detailsList;
    private String source;

    @Override
    public void parse(String source) {
        this.source = source;
        this.source = cropSource();
        fixSource();
        HTMLScraper scraper = new HTMLScraper(this.source);

        scraper.evaluateXPathExpression("//a[@class=\"author11\"]");
        ArrayList<String> authors = scraper.getFirstChildList();
        result = new ArrayList<>(authors.size());
        detailsList = new ArrayList<>(authors.size());

        scraper.reset(this.source);
        scraper.evaluateXPathExpression("//a[@class=\"productBox-450Pic\"]");
        ArrayList<String> detailsHrefs = scraper.getAttributeValueList("href");


        for (int i = 0; i < authors.size(); i++) {
            scraper.reset(this.source);
            scraper.evaluateXPathExpression("(//div[@class=\"efileFormat\"])[" + String.valueOf(i + 1) + "]//a");

            WebActionContext context = new BasicWebActionContext(EMPIK_COM + detailsHrefs.get(i));
            WebBookDetails details = EbookEmpikWebDataProviderFactory.instance().createBookDetails(context);

            ArrayList<String> formatNames = scraper.getFirstChildList();
            ArrayList<String> hrefs = scraper.getAttributeValueList("href");
            for (String formatName : formatNames) {
                FormatDetails format = FormatDetails.instanceForFormatName(formatName);
                if (!format.getFormatName().equals(Mp3Details.FORMAT_NAME)) {
                    format.setDownloadUrl(hrefs.get(i));
                    Log.d("Download URL", format.getDownloadUrl());
                }
                details.addFormat(format);
            }
            detailsList.add(details);
        }

        for (int i = 0; i < authors.size(); i++) {
            Book book = new Book(detailsList.get(i));
            result.add(book);
        }
        for (int i = 0; i < authors.size(); i++) {
            result.get(i).setAuthor(Person.named(authors.get(i)));
        }

        scraper.reset(this.source);
        scraper.evaluateXPathExpression("//a[@class=\"productTitle\"]");
        ArrayList<String> titles = scraper.getAttributeValueList("title");

        for (int i = 0; i < result.size(); i++) {
            String title = titles.get(i);
            title = title.replaceAll("&nbsp;", " ");
            title = title.substring(0, title.indexOf(authors.get(i)) - 3);
            result.get(i).setTitle(title.trim());
            result.get(i).setId("Empik" + title.trim());
        }

        scraper.reset(this.source);
        scraper.evaluateXPathExpression("//div[@class=\"productBox-450Pic\"]/a/img");
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
        source = source.replaceAll("\"></a>  </div>", " \"/></a>  </div>");
    }

    private String cropSource() {
        int start = source.indexOf("<div class=\"LibraryProductsList\">");
        int end = source.indexOf("<div class=\"dotLine\"></div>", start);
        return source.substring(start, end);
    }

    @Override
    public List<Book> getBooks() {
        return result;
    }
}
