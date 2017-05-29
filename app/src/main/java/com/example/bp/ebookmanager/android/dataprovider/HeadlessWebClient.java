package com.example.bp.ebookmanager.android.dataprovider;

import android.util.Log;
import android.webkit.CookieManager;

import com.example.bp.ebookmanager.dataprovider.WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public class HeadlessWebClient implements WebClient {
    private Callbacks callbacks;
    private HeadlessWebClientActionsRunner runner;
    private CookieManager cookieManager;

    HeadlessWebClient(HeadlessWebClientActionsRunner runner, CookieManager cookieManager) {
        this.runner = runner;
        this.cookieManager = cookieManager;
        runner.setActions(new HeadlessWebClientAsyncActionsImpl());
    }

    @Override
    public void setCallbacks(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void loadUrl(String urlString) {
        try {
            UrlWrapper url = new JavaNetUrlWrapper(urlString);
            loadUrl(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            callbacks.onLoadingFailed(urlString);
        }
    }

    public void loadUrl(UrlWrapper url) {
        runner.run(url);
    }

    @Override
    public boolean isHeadless() {
        return true;
    }

    @Override
    public void close() {

    }

    public interface HeadlessWebClientActionsRunner {
        void setActions(HeadlessWebClientAsyncActions actions);
        void run(UrlWrapper url);
    }

    public interface HeadlessWebClientAsyncActions {
        String doInBackground(UrlWrapper url);
        void onPostExecute(String result);
    }

    public class HeadlessWebClientAsyncActionsImpl implements HeadlessWebClientAsyncActions {
        private HttpURLConnection connection;
        private UrlWrapper url;

        @Override
        public String doInBackground(UrlWrapper url) {
            String result = null;
            this.url = url;
            try {
                if (establishConnection()) {
                    BufferedReader reader = createReader();
                    result = getSource(reader);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
            return result;
        }

        private boolean establishConnection() throws IOException {
            connection = url.openConnection();
            setCookie();
            connection.connect();
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        }

        private void setCookie() {
            String cookie = cookieManager.getCookie(url.getHost());
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36");
            connection.setRequestProperty("Cookie", cookie);
        }

        private BufferedReader createReader() throws IOException {
            InputStream inputStream = connection.getInputStream();
            return new BufferedReader(new InputStreamReader(inputStream));
        }

        private String getSource(BufferedReader reader) throws IOException {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            return builder.toString();
        }

        @Override
        public void onPostExecute(String result) {
            Log.d("HeadlessWebClient", connection.getURL().toExternalForm());
            if (result != null) {
                callbacks.onPageFinished(connection.getURL().toExternalForm(), result);
            }
            else {
                callbacks.onLoadingFailed(connection.getURL().toExternalForm());
            }
        }
    }

//    private class HeadlessWebClientAsyncTask extends AsyncTask<UrlWrapper, Void, String> {
//
//        private HttpURLConnection connection;
//        private UrlWrapper url;
//
//        @Override
//        protected String doInBackground(UrlWrapper... params) {
//            String result = null;
//            url = params[0];
//            try {
//                if (establishConnection()) {
//                    BufferedReader reader = createReader();
//                    result = getSource(reader);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                connection.disconnect();
//            }
//            return result;
//        }
//
//        private boolean establishConnection() throws IOException {
//            connection = url.openConnection();
//            setCookie();
//            connection.connect();
//            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
//        }
//
//        private void setCookie() {
//            CookieManager cookieManager = CookieManager.getInstance();
//            String cookie = cookieManager.getCookie(url.getHost());
//            connection.setRequestProperty("User-Agent",
//                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36");
//            connection.setRequestProperty("Cookie", cookie);
//        }
//
//        private BufferedReader createReader() throws IOException {
//            InputStream inputStream = connection.getInputStream();
//            return new BufferedReader(new InputStreamReader(inputStream));
//        }
//
//        private String getSource(BufferedReader reader) throws IOException {
//            StringBuilder builder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                builder.append(line);
//                builder.append("\n");
//            }
//            return builder.toString();
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Log.d("HeadlessWebClient", connection.getURL().toExternalForm());
//            if (result != null) {
//                callbacks.onPageFinished(connection.getURL().toExternalForm(), result);
//            }
//            else {
//                callbacks.onLoadingFailed(connection.getURL().toExternalForm());
//            }
//            super.onPostExecute(result);
//        }
//    }
}
