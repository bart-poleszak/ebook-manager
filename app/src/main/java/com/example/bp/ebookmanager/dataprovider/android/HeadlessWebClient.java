package com.example.bp.ebookmanager.dataprovider.android;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.webkit.CookieManager;

import com.example.bp.ebookmanager.dataprovider.WebClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public class HeadlessWebClient implements WebClient {
    private Callbacks callbacks;

    @Override
    public void setCallbacks(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void loadUrl(String url) {
        HeadlessWebClientAsyncTask task = new HeadlessWebClientAsyncTask();
        task.execute(url);
    }

    @Override
    public boolean isHeadless() {
        return true;
    }

    @Override
    public void close() {

    }

    private class HeadlessWebClientAsyncTask extends AsyncTask<String, Void, String> {

        private HttpURLConnection connection;
        private URL url;
        private BufferedReader reader;

        @Override
        protected String doInBackground(String... params) {
            try {
                establishConnection(params[0]);
                setCookie();
                createReader();
                return getSource();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
            return null;
        }

        private void establishConnection(String param) throws IOException {
            url = new URL(param);
            connection = (HttpURLConnection) url.openConnection();
        }

        private void setCookie() {
            CookieManager cookieManager = CookieManager.getInstance();
            String cookie = cookieManager.getCookie(url.getHost());
            connection.setRequestProperty("Cookie", cookie);
        }

        private void createReader() throws IOException {
            InputStream inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        private String getSource() throws IOException {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            callbacks.onPageFinished(url.toExternalForm(), result);
            super.onPostExecute(result);
        }
    }
}
