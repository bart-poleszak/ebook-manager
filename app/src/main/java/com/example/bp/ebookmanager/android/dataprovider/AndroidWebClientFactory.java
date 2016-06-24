package com.example.bp.ebookmanager.android.dataprovider;

import android.content.Context;

import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.dataprovider.WebClient;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class AndroidWebClientFactory implements WebClientFactory {
    private Context context;

    public AndroidWebClientFactory(Context context) {
        this.context = context;
    }

    @Override
    public WebClient getHeadlessClient() {
        return new HeadlessWebClient();
    }

    @Override
    public WebClient getVisualClient() {
        return new VisibleWebClient(context);
    }
}
