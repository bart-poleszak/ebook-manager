package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bp on 08.05.16.
 */
public class MultipleDataProvider implements BookDataProvider {

    ArrayList<BookDataProvider> providers = new ArrayList<>();

    @Override
    public void getDataAsync(final Callbacks callbacks) {
        Callbacks internalCallbacks = new Callbacks() {
            @Override
            public void onNewDataAcquired(List<Book> data) {
                callbacks.onNewDataAcquired(data);
            }

            @Override
            public void onDataAcquisitionFailed() {

            }

            @Override
            public void webActionRequired(String actionUrl, String targetUrl) {

            }
        };
        for (BookDataProvider provider : providers)
            provider.getDataAsync(internalCallbacks);
    }

    @Override
    public void cancel() {
        for (BookDataProvider provider : providers) {
            provider.cancel();
        }
    }

    public void addDataProvider(BookDataProvider provider) {
        providers.add(provider);
    }
}
