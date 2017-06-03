package com.example.bp.ebookmanager.utils;

import com.example.bp.ebookmanager.DataStore;
import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by Bartek on 2017-06-03.
 */

public class FakeDataStore implements DataStore {
    @Override
    public void storeThumbnail(Book book, byte[] thumbnail) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void update(List<Book> data) {

    }
}
