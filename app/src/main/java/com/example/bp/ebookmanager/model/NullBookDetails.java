package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class NullBookDetails implements BookDetails {
    private static NullBookDetails inst;

    private NullBookDetails() {}

    public static NullBookDetails instance() {
        if (inst == null)
            inst = new NullBookDetails();
        return inst;
    }

    @Override
    public Person getTranslator() {
        return null;
    }

    @Override
    public void setTranslator(Person translator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Publisher getPublisher() {
        return null;
    }

    @Override
    public void setPublisher(Publisher publisher) {
        throw new UnsupportedOperationException();
    }
}
