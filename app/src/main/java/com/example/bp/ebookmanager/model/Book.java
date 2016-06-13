package com.example.bp.ebookmanager.model;

import com.example.bp.ebookmanager.model.formats.FormatSpecificData;

import java.util.HashSet;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class Book {
    private String title;
    private Person author;
    private BookDetails details;

    private HashSet<FormatSpecificData> formats = new HashSet<>();

    public Book(BookDetails details) {
        this.details = details;
    }

    public Book() {
        this(NullBookDetails.instance());
    }

    public void setDetailsObserver(BookDetails.DetailsObserver detailsObserver) {
        details.setObserver(detailsObserver);
    }

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

    public HashSet<FormatSpecificData> getFormats() {
        return formats;
    }

    public Person getTranslator() {
        return details.getTranslator();
    }

    public Publisher getPublisher() {
        return details.getPublisher();
    }
}
