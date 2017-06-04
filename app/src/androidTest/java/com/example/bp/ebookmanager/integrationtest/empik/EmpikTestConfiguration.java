package com.example.bp.ebookmanager.integrationtest.empik;

import android.content.Context;

import com.example.bp.ebookmanager.AndroidDataStore;
import com.example.bp.ebookmanager.android.config.AndroidConfiguration;
import com.example.bp.ebookmanager.android.config.AndroidConfigurationFactory;
import com.example.bp.ebookmanager.config.Configuration;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.dataprovider.WebDataProviderFactory;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.utils.FakeDataStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ebook-manager
 * Created by bart-poleszak on 29.05.2017.
 */

public class EmpikTestConfiguration implements Configuration {
    private final WebDataProviderFactory providerToTestFactory;
    private AndroidConfiguration androidConfiguration;

    public EmpikTestConfiguration(AndroidConfiguration androidConfiguration, WebDataProviderFactory providerToTestFactory) {
        this.androidConfiguration = androidConfiguration;
        this.providerToTestFactory = providerToTestFactory;
    }

    @Override
    public WebClientFactory getWebClientFactory() {
        return androidConfiguration.getWebClientFactory();
    }

    @Override
    public UserActionEnabler getUserActionEnabler() {
        return androidConfiguration.getUserActionEnabler();
    }

    @Override
    public List<BookDataProvider> getDataProviders() {
        ArrayList<BookDataProvider> providers = new ArrayList<>();
        providers.add(providerToTestFactory.createBookDataProvider());
        return providers;
    }

    @Override
    public BookDataProvider getLocalDataProvider() {
        return new BookDataProvider() {
            @Override
            public String getName() {
                return "fake";
            }

            @Override
            public void requestBooks(Callbacks callbacks) {
                callbacks.onNewDataAcquired(Collections.<Book>emptyList());
            }
        };
    }

    @Override
    public AndroidDataStore getDataStore() {
        return new FakeDataStore();
    }

    public static AndroidConfigurationFactory getFactory(final WebDataProviderFactory providerToTestFactory) {
        return new AndroidConfigurationFactory() {
            @Override
            public Configuration getConfiguration(Context context) {
                AndroidConfiguration androidConfiguration = new AndroidConfiguration(context);
                return new EmpikTestConfiguration(androidConfiguration, providerToTestFactory);
            }
        };
    }
}
