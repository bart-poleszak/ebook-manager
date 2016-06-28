package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 08.05.16.
 */
public class MultipleDataProvider implements BookDataProvider {

    private ArrayList<BookDataProvider> providers = new ArrayList<>();
    private int currentlyExecuted = 0;

    @Override
    public String getName() {
        return "Multiple sources";
    }

    @Override
    public void requestBooks(final Callbacks callbacks) {
        if (currentlyExecuted > 0)
            throw new RuntimeException("Called request on MultipleDataProvider before it has finished working");
        Callbacks internalCallbacks = new Callbacks() {
            @Override
            public void onNewDataAcquired(List<Book> data) {
                callbacks.onNewDataAcquired(data);
                next();
            }

            @Override
            public void onDataAcquisitionFailed() {
                callbacks.onDataAcquisitionFailed();
                next();
            }

            private void next() {
                currentlyExecuted++;
                if (providers.size() > currentlyExecuted)
                    providers.get(currentlyExecuted).requestBooks(this);
            }

        };
        currentlyExecuted = 0;
        if (providers.size() > 0) {
            providers.get(0).requestBooks(internalCallbacks);
        }
    }

    public void addDataProvider(BookDataProvider provider) {
        providers.add(provider);
    }
}
