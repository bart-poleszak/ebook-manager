package com.example.bp.ebookmanager.android.config;

import android.content.Context;

import com.example.bp.ebookmanager.AndroidDataStore;
import com.example.bp.ebookmanager.android.RealmDataStore;
import com.example.bp.ebookmanager.android.dataprovider.AndroidUserActionEnabler;
import com.example.bp.ebookmanager.android.dataprovider.AndroidWebClientFactory;
import com.example.bp.ebookmanager.config.Configuration;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.BookDataProviderImpl;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.dataprovider.empik.AudiobookEmpikWebDataProviderFactory;
import com.example.bp.ebookmanager.dataprovider.empik.EbookEmpikWebDataProviderFactory;
import com.example.bp.ebookmanager.dataprovider.realm.RealmDataProviderStrategy;
import com.example.bp.ebookmanager.dataprovider.woblink.WoblinkWebDataProviderFactory;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<BookDataProvider> getDataProviders() {
        ArrayList<BookDataProvider> providers = new ArrayList<>();
        providers.add(WoblinkWebDataProviderFactory.instance().createBookDataProvider());
        providers.add(AudiobookEmpikWebDataProviderFactory.instance().createBookDataProvider());
        providers.add(EbookEmpikWebDataProviderFactory.instance().createBookDataProvider());
        return providers;
    }

    @Override
    public BookDataProvider getLocalDataProvider() {
        RealmDataProviderStrategy strategy = new RealmDataProviderStrategy();
        return new BookDataProviderImpl(strategy);
    }

    @Override
    public AndroidDataStore getDataStore() {
        return new RealmDataStore("ebookmanager.realm");
    }
}
