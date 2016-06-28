package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public interface BookDataParser {
    void parse(String source);

    List<Book> getBooks();
}
