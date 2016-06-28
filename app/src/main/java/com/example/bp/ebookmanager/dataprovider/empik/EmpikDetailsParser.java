package com.example.bp.ebookmanager.dataprovider.empik;

import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.BookDetailsImpl;
import com.example.bp.ebookmanager.model.BookDetailsParser;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;
import com.example.bp.ebookmanager.model.formats.Mp3Details;

import java.util.ArrayList;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class EmpikDetailsParser implements BookDetailsParser {

    private String source;

    @Override
    public BookDetails parse(String source) {
        this.source = source;
        this.source = cropSource();
        BookDetailsImpl details = new BookDetailsImpl();
        HTMLScraper scraper = new HTMLScraper(this.source);

        scraper.evaluateXPathExpression("//td[@class=\"productDetailsLabel\"]");
        ArrayList<String> keys = scraper.getFirstChildList();
        for (int i = 0; i < keys.size(); i++)
            keys.set(i, keys.get(i).trim());
        scraper.reset(this.source);
        scraper.evaluateXPathExpression("//td[@class=\"productDetailsValue\"]/span");
        ArrayList<String> values = scraper.getFirstChildList();

        int index = keys.indexOf("Wydawnictwo:") - 1; //-1 because author has another class and wasn't found  by xpath expression
        if (index >= 0) {
            Publisher publisher = Publisher.named(values.get(index));
            details.setPublisher(publisher);
        }

        index = keys.indexOf("Tłumacz:") - 1;
        if (index >= 0) {
            Person translator = Person.named(values.get(index));
            details.setTranslator(translator);
        }

        index = keys.indexOf("Lektor:") - 1;
        if (index >= 0) {
            Person narrator = Person.named(values.get(index));
            Mp3Details mp3Details = new Mp3Details();
            mp3Details.setNarrator(narrator);
            details.getFormats().add(mp3Details);
        }

        return details;
    }

    private String cropSource() {
        int start = source.indexOf("<table class=\"contentPacketText prodDetails\"");
        String tagEnd = "</table>";
        int end = source.indexOf(tagEnd, start) + tagEnd.length();
        return source.substring(start, end);
    }
}
