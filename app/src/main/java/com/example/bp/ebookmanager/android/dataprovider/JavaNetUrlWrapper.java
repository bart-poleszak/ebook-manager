package com.example.bp.ebookmanager.android.dataprovider;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bartek on 2017-05-28.
 */

class JavaNetUrlWrapper implements UrlWrapper{
    final URL url;

    public JavaNetUrlWrapper(String urlString) throws MalformedURLException {
        url = new URL(urlString);
    }

    @Override
    public HttpURLConnection openConnection() {
        return null;
    }

    @Override
    public String getHost() {
        return null;
    }
}
