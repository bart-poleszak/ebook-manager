package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public interface BookDetails {
    Person getTranslator();

    Publisher getPublisher();

    void setObserver(DetailsObserver observer);

    interface DetailsObserver {
        void onDetailsChanged();
    }
}
