package com.example.bp.ebookmanager.dataprovider.android;

import com.example.bp.ebookmanager.dataprovider.WebClient;

/**
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
        callbacks.onPageFinished("aaa", "bbb");
    }

    @Override
    public boolean isHeadless() {
        return true;
    }

    @Override
    public void finishWork() {

    }
}
