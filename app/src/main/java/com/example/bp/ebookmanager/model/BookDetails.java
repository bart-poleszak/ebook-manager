package com.example.bp.ebookmanager.model;

import com.example.bp.ebookmanager.model.formats.FormatDetails;

import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public interface BookDetails {
    Person getTranslator();

    Publisher getPublisher();

    void setObserver(DetailsObserver observer);

    List<FormatDetails> getFormats();
    FormatDetails getFormat(String formatName);

    interface DetailsObserver {
        void onDetailsChanged();
        void onFailure();
    }
}
