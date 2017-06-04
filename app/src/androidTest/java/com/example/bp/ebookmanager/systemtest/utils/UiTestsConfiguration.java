package com.example.bp.ebookmanager.systemtest.utils;

import com.example.bp.ebookmanager.AndroidDataStore;
import com.example.bp.ebookmanager.config.Configuration;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebActionResolver;
import com.example.bp.ebookmanager.dataprovider.WebClient;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.utils.FakeDataStore;
import com.example.bp.ebookmanager.utils.FakeWebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ebook Manager
 * Created by Bartek on 2017-06-03.
 */
public class UiTestsConfiguration implements Configuration {

    private TestBookDataProvider testBookDataProvider = new TestBookDataProvider();

    @Override
    public WebClientFactory getWebClientFactory() {
        return new WebClientFactory() {
            @Override
            public WebClient getHeadlessClient() {
                return new FakeWebClient();
            }

            @Override
            public WebClient getVisualClient() {
                return new FakeWebClient();
            }
        };
    }

    @Override
    public UserActionEnabler getUserActionEnabler() {
        return new UserActionEnabler() {
            @Override
            public void enableWebUserAction(WebActionResolver resolver) {

            }
        };
    }

    @Override
    public List<BookDataProvider> getDataProviders() {
        ArrayList<BookDataProvider> providers = new ArrayList<>();
        providers.add(testBookDataProvider);
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

    public TestBookDataProvider getTestBookDataProvider() {
        return testBookDataProvider;
    }
}
