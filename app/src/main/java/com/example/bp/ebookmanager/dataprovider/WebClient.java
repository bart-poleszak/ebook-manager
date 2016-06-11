package com.example.bp.ebookmanager.dataprovider;

/**
 * Created by bp on 11.06.16.
 */
public interface WebClient {
    void setCallbacks(WebClient.Callbacks callbacks);
    void loadUrl(String url);
    boolean isHeadless();

    interface Callbacks {
        void onPageFinished(String url, String source);
    }
}
