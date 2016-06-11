package com.example.bp.ebookmanager.dataprovider;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Created by bp on 08.05.16.
 */
public abstract class WebDataProvderStrategy implements DataProviderStrategy {
    private WebView headlessWebView;

    public WebDataProvderStrategy(Context context) {
        headlessWebView = new WebView(context);
        headlessWebView.setWebViewClient(new WebViewClient() {

        });
    }

    @Override
    public List<Book> getBooks() {
        String pageSource = downloadPageSource();
        return extractData(pageSource);
    }

    @Override
    public boolean testAccess() {

        return false;
    }

    private String downloadPageSource() {
        String pageUrl = getPageUrl();
        return "";
    }

    protected abstract String getPageUrl();

    protected abstract String getAccessTestUrl();

    protected abstract List<Book> extractData(String pageSource);

    private interface Callbacks {
        void onActionRequired();
    }
}
