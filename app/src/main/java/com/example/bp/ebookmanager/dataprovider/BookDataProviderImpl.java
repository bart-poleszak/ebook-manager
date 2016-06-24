package com.example.bp.ebookmanager.dataprovider;

/**
 * Ebook Manager
 * Created by bp on 08.05.16.
 */
public class BookDataProviderImpl implements BookDataProvider {
    private DataProviderStrategy strategy;
    private String name = "Undefined";

    public BookDataProviderImpl(DataProviderStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void requestBooks(final Callbacks callbacks) {

        try {
            strategy.gainAccess(new DataProviderStrategy.Callbacks() {
                @Override
                public void onAccessGained() {
                    callbacks.onNewDataAcquired(strategy.getBooks());
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            callbacks.onDataAcquisitionFailed();
        }
    }
}
