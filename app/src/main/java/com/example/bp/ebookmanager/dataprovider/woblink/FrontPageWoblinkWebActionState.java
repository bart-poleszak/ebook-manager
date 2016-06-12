package com.example.bp.ebookmanager.dataprovider.woblink;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.WebActionState;
import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.Book;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Created by bp on 11.06.16.
 */
public class FrontPageWoblinkWebActionState implements WebActionState {
    private boolean logged = false;
    private String shelfUrl;

    public boolean isLogged() {
        return logged;
    }

    public String getShelfUrl() {
        return shelfUrl;
    }

    @Override
    public String getTargetSiteURL() {
        return "https://woblink.com/";
    }

    @Override
    public void processRecievedData(String url, String source) {
        Log.d("FrontPageWoblink", url);
        source = prepareSource(source);

        HTMLScraper scraper = new HTMLScraper(source);
        scraper.evaluateXPathExpression("//a[@data-ga-action='My_bookshelf']");
        if (scraper.evaluationSuccessful()) {
            shelfUrl = scraper.getAttributeValue("href");
            logged = true;
        }

//        try {
//            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(source)));
//            XPathExpression xpath = XPathFactory.newInstance().newXPath().compile("//a[@data-ga-action='My_bookshelf']/@href");
//            shelfUrl = (String) xpath.evaluate(doc, XPathConstants.STRING);
//            logged = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @NonNull
    private String prepareSource(String source) {
        int start = source.indexOf("<div class=\"top-bar-inside\">");
        if (start < 0)
            return source;
        String divString = "</div>";
        int end = source.indexOf(divString, start) + divString.length();
        source = source.substring(start, end);
        return source;
    }

    @Override
    public boolean isActionCompleted() {
        return false;
    }

    @Override
    public boolean isUserActionNeeded() {
        return false;
    }

    @Override
    public List<Book> getBooks() {
        throw new UnsupportedOperationException();
    }
}
