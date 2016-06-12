package com.example.bp.ebookmanager.dataprovider;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public interface WebClient {
    void setCallbacks(WebClient.Callbacks callbacks);
    void loadUrl(String url);
    boolean isHeadless();
    void close();

    interface Callbacks {
        void onPageFinished(String url, String source);
    }
}
