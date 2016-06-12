package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public interface BookDetails {
    Person getTranslator();

    void setTranslator(Person translator);

    Publisher getPublisher();

    void setPublisher(Publisher publisher);
}
