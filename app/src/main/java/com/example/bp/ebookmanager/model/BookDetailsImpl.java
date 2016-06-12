package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class BookDetailsImpl implements BookDetails {
    private Person translator;
    private Publisher publisher;

    @Override
    public Person getTranslator() {
        return translator;
    }

    @Override
    public void setTranslator(Person translator) {
        this.translator = translator;
    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
