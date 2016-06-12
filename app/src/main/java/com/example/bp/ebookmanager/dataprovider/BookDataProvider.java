package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 08.05.16.
 */
public interface BookDataProvider {
    void requestBooks(Callbacks callbacks);

    interface Callbacks {
        void onNewDataAcquired(List<Book> data);
        void onDataAcquisitionFailed();
        void enableUserActions(DataProviderStrategy strategy);
    }
}
