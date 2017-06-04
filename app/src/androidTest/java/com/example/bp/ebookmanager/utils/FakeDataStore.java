package com.example.bp.ebookmanager.utils;

import android.content.Context;

import com.example.bp.ebookmanager.AndroidDataStore;
import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by Bartek on 2017-06-03.
 */

public class FakeDataStore implements AndroidDataStore {
    @Override
    public void initialize(Context context) {

    }

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
