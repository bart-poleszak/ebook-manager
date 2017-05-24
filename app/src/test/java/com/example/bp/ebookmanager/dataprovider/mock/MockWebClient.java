package com.example.bp.ebookmanager.dataprovider.mock;

import com.example.bp.ebookmanager.dataprovider.WebClient;

/**
 * Created by Bartek on 2017-04-23.
 */

public class MockWebClient implements WebClient {
    @Override
    public void setCallbacks(Callbacks callbacks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUrl(String url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isHeadless() {
        return true;
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException();
    }
}
