package com.example.bp.ebookmanager.utils;

import com.example.bp.ebookmanager.dataprovider.WebClient;

/**
 * Ebook Manager
 * Created by Bartek on 2017-06-03.
 */

public class FakeWebClient implements WebClient {
    @Override
    public void setCallbacks(Callbacks callbacks) {

    }

    @Override
    public void loadUrl(String url) {

    }

    @Override
    public boolean isHeadless() {
        return false;
    }

    @Override
    public void close() {

    }
}
