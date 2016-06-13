package com.example.bp.ebookmanager.dataprovider.woblink;

import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.BookDetailsImpl;
import com.example.bp.ebookmanager.model.BookDetailsParser;
import com.example.bp.ebookmanager.model.Publisher;

/**
 * Ebook Manager
 * Created by bp on 13.06.16.
 */
public class WoblinkBookDetailsParser implements BookDetailsParser {

    @Override
    public BookDetails parse(String source) {
        source = cropSource(source);
        HTMLScraper scraper = new HTMLScraper(source);
        BookDetailsImpl result = new BookDetailsImpl();
        scraper.evaluateXPathExpression("//a[@itemprop=\"publisher\"]");
        Publisher publisher = Publisher.named(scraper.getFirstChild());
        result.setPublisher(publisher);
        Log.d("WoblinkBDParser", "Publisher: " + publisher.getName());
        return result;
    }

    private String cropSource(String source) {
        int start = source.indexOf("<p class=\"nw_kartaproduktowa_ksiazka_info_wydawnictwo\">");
        String pEndTag = "</p>";
        int end = source.indexOf(pEndTag, start) + pEndTag.length();
        return source.substring(start, end);
    }
}
