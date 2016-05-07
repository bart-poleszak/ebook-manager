package com.example.bp.ebookmanager.model;

import com.example.bp.ebookmanager.model.formats.FormatSpecificData;

import java.util.HashSet;

/**
 * Created by bp on 07.05.16.
 */
public class Book {
    private String title;
    private Person author;
    private Person translator;
    private Publisher publisher;
    private HashSet<FormatSpecificData> formats;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Person getTranslator() {
        return translator;
    }

    public void setTranslator(Person translator) {
        this.translator = translator;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
