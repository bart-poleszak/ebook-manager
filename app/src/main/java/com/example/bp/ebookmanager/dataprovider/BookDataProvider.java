package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Created by bp on 08.05.16.
 */
public interface BookDataProvider {
    void requestBooks(Callbacks callbacks);

    enum ProgressStatus {
        ACTION_REQUIRED
    }

    interface Callbacks {
        void onNewDataAcquired(List<Book> data);
        void onDataAcquisitionFailed();
        void enableUserActions(DataProviderStrategy strategy);

    }
}
