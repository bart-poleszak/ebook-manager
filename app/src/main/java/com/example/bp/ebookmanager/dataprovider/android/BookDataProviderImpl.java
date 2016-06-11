package com.example.bp.ebookmanager.dataprovider.android;

import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.DataProviderStrategy;

/**
 * Created by bp on 08.05.16.
 */
public class BookDataProviderImpl implements BookDataProvider {
    private DataProviderStrategy strategy;

    public BookDataProviderImpl(DataProviderStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void requestBooks(final Callbacks callbacks) {

        strategy.gainAccess(new DataProviderStrategy.Callbacks() {
            @Override
            public void onAccessGained() {
                callbacks.onNewDataAcquired(strategy.getBooks());
            }

            @Override
            public void onUserActionNeeded() {

            }
        });
    }

}
