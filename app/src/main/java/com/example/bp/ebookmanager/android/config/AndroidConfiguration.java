package com.example.bp.ebookmanager.android.config;

import android.content.Context;

import com.example.bp.ebookmanager.config.Configuration;
import com.example.bp.ebookmanager.android.dataprovider.AndroidWebClientFactory;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.android.dataprovider.AndroidUserActionEnabler;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class AndroidConfiguration implements Configuration {
    private WebClientFactory webClientFactory;
    private UserActionEnabler userActionEnabler;

    public AndroidConfiguration(Context context) {
        webClientFactory = new AndroidWebClientFactory(context);
        userActionEnabler = new AndroidUserActionEnabler();
    }

    @Override
    public WebClientFactory getWebClientFactory() {
        return webClientFactory;
    }

    @Override
    public UserActionEnabler getUserActionEnabler() {
        return userActionEnabler;
    }
}
