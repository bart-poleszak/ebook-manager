package com.example.bp.ebookmanager;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by Bartek on 2017-06-03.
 */

public interface DataStore {
    void storeThumbnail(Book book, byte[] thumbnail);
    void update(Book book);
    void update(List<Book> data);
}
