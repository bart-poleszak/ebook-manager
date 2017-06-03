package com.example.bp.ebookmanager.dataprovider.utils;

import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;

/**
 * Created by Bartek on 2017-05-28.
 */

public class FakeCookieManager extends CookieManager {
    @Override
    public void setAcceptCookie(boolean b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean acceptCookie() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAcceptThirdPartyCookies(WebView webView, boolean b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean acceptThirdPartyCookies(WebView webView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCookie(String s, String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCookie(String s, String s1, ValueCallback<Boolean> valueCallback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getCookie(String s) {
        return "fake";
    }

    @Override
    public void removeSessionCookie() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAllCookie() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasCookies() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeExpiredCookie() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }
}
