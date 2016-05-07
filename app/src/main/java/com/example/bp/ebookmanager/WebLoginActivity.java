package com.example.bp.ebookmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebLoginActivity extends AppCompatActivity {
    private WebView webView;
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("WebLoginActivity", "Finished - " + url);
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
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        webView.loadUrl("https://m.empik.com/twoje-konto/biblioteka-lista?activeTab=audiobook");

    }
}
