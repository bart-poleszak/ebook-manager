package com.example.bp.ebookmanager.dataprovider.empik;

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
        this.source = cropAndFixSource();
        BookDetailsImpl details = new BookDetailsImpl();
        HTMLScraper scraper = new HTMLScraper(this.source);

        scraper.evaluateXPathExpression("//tr[@class=\"row--text row--text attributeName\"]/td[1]");
        ArrayList<String> keys = scraper.getFirstChildList();
        for (int i = 0; i < keys.size(); i++)
            keys.set(i, keys.get(i).trim());
        scraper.reset();
        scraper.evaluateXPathExpression("//span[@class=\"attributeDetailsValue\"]");
        ArrayList<String> values = scraper.getFirstChildList();

        int index = keys.indexOf("Wydawnictwo:");
        if (index >= 0) {
            Publisher publisher = Publisher.named(values.get(index));
            details.setPublisher(publisher);
        }

        index = keys.indexOf("Tłumacz:");
        if (index >= 0) {
            Person translator = Person.named(values.get(index));
            details.setTranslator(translator);
        }

        index = keys.indexOf("Lektor:");
        if (index >= 0) {
            Person narrator = Person.named(values.get(index));
            Mp3Details mp3Details = new Mp3Details();
            mp3Details.setNarrator(narrator);
            details.getFormats().add(mp3Details);
        }

        return details;
    }

    private String cropAndFixSource() {
        int start = source.indexOf("<div data-title=\"Dane szczegółowe\">");
        String nextDiv = "<div class=\"productComments\"";
        int end = source.indexOf(nextDiv, start);
        return source.substring(start, end).replaceAll("&nbsp", " ");
    }
}
