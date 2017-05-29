package com.example.bp.ebookmanager.dataprovider.mock;

import com.example.bp.ebookmanager.android.dataprovider.UrlWrapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bartek on 2017-05-28.
 */

public class FakeUrlWrapper implements UrlWrapper {
    final URL localhostUrl = new URL("http://localhost/");
    private final int responseCodeToReturn;
    private boolean throwIOExceptionOnConnect;

    public FakeUrlWrapper(int responseCodeToReturn) throws MalformedURLException {
        this.responseCodeToReturn = responseCodeToReturn;
    }

    public void setThrowIOExceptionOnConnect(boolean b) {
        throwIOExceptionOnConnect = true;
    }

    @Override
    public HttpURLConnection openConnection() throws IOException {
        return new HttpURLConnection(localhostUrl) {
            @Override
            public void disconnect() {

            }

            @Override
            public boolean usingProxy() {
                return false;
            }

            @Override
            public void connect() throws IOException {
                if (throwIOExceptionOnConnect) {
                    throw new IOException();
                }
            }

            @Override
            public int getResponseCode() throws IOException {
                return responseCodeToReturn;
            }

            @Override
            public URL getURL() {
                return localhostUrl;
            }

            @Override
            public void setRequestProperty(String field, String newValue) {
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream("something".getBytes());
            }
        };
    }

    @Override
    public String getHost() {
        return localhostUrl.getHost();
    }
}
