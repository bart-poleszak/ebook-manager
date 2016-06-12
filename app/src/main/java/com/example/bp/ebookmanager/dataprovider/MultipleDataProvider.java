package com.example.bp.ebookmanager.dataprovider;

import java.util.ArrayList;

/**
 * Ebook Manager
 * Created by bp on 08.05.16.
 */
public class MultipleDataProvider implements BookDataProvider {

    ArrayList<BookDataProvider> providers = new ArrayList<>();

    @Override
    public void requestBooks(final Callbacks callbacks) {
        for (BookDataProvider provider : providers)
            provider.requestBooks(callbacks);
    }

    public void addDataProvider(BookDataProvider provider) {
        providers.add(provider);
    }
}
