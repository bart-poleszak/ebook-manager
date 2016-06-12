package com.example.bp.ebookmanager.dataprovider.woblink;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.WebActionState;
import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
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
    public String getResult() {
        throw new UnsupportedOperationException();
    }
}
