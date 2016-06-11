package com.example.bp.ebookmanager;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebLoginActivity extends AppCompatActivity {
    public static final String GET_HTML_JS_FUNCTION = "(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();";
    private WebView webView;
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("WebLoginActivity", "Finished - " + url);
            view.evaluateJavascript(GET_HTML_JS_FUNCTION, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String html) {
//                    Log.d("HTML", html);
                    // code here
                }
            });
            CookieManager cookieManager = CookieManager.getInstance();
            String cookie = cookieManager.getCookie("www.empik.com");
            testHttpConnection(cookie);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.d("WebLoginActivity", "onLoad - " + url);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_login);
        setTitle(getString(R.string.action_required));

        webView = (WebView) findViewById(R.id.webView);
        assert webView != null;
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String cookie = cookieManager.getCookie("www.empik.com");
        testHttpConnection(cookie);
        webView.loadUrl("https://m.empik.com/twoje-konto/biblioteka-lista?activeTab=audiobook");
    }

    private void testHttpConnection(String cookie) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("https://m.empik.com/twoje-konto/biblioteka-lista?activeTab=audiobook");
            urlConnection = (HttpURLConnection) url.openConnection();
            if (cookie != null)
                urlConnection.setRequestProperty("Cookie", cookie);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.d("testHttpConnection", urlConnection.getURL().toString());
//            if (!url.getHost().equals(urlConnection.getURL().getHost())) {
//                 we were redirected! Kick the user out to the browser to sign on?
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }
}
