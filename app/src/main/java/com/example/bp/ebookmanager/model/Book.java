package com.example.bp.ebookmanager.model;

import com.example.bp.ebookmanager.model.formats.FormatSpecificData;

import java.util.HashSet;
import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class Book {
    private String id;
    private String title;
    private Person author;
    private BookDetails details;
    private Thumbnail thumbnail;
    private HashSet<String> formats = new HashSet<>();

    public Book(BookDetails details) {
        this.details = details;
    }

    public Book() {
        this(NullBookDetails.instance());
    }

    public void setDetailsObserver(BookDetails.DetailsObserver detailsObserver) {
        details.setObserver(detailsObserver);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public HashSet<String> getFormatNames() {
        return formats;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Person getTranslator() {
        return details.getTranslator();
    }

    public Publisher getPublisher() {
        return details.getPublisher();
    }

    public List<FormatSpecificData> getFormatSpecificDataList() {
        return details.getFormatSpecificDataList();
    }
}
