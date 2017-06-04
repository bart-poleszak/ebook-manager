package com.example.bp.ebookmanager;

import android.content.Context;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by Bartek on 2017-06-03.
 */

public interface AndroidDataStore {
    void initialize(Context context);
    void storeThumbnail(Book book, byte[] thumbnail);
    void update(Book book);
    void update(List<Book> data);
}
