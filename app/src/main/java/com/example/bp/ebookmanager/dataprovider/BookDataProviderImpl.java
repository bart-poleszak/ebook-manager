package com.example.bp.ebookmanager.dataprovider;

/**
 * Ebook Manager
 * Created by bp on 08.05.16.
 */
public class BookDataProviderImpl implements BookDataProvider {
    private DataProviderStrategy strategy;

    public BookDataProviderImpl(DataProviderStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void requestBooks(final Callbacks callbacks) {

        try {
            strategy.gainAccess(new DataProviderStrategy.Callbacks() {
                @Override
                public void onAccessGained() {
                    callbacks.onNewDataAcquired(strategy.getBooks());
                }

                @Override
                public void onUserActionRequired() {
                    callbacks.enableUserActions(strategy);
                    strategy.retryToGainAccess();
                }
            });
        } catch (Exception e) {
            callbacks.onDataAcquisitionFailed();
        }
    }

}
