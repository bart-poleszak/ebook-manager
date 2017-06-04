package com.example.bp.ebookmanager.systemtest.utils;

import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.DataProviderStrategy;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.BookDetailsImpl;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Ebook Manager
 * Created by Bartek on 2017-06-03.
 */

public class TestBookDataProvider implements BookDataProvider {

    private List<Book> books;

    @Override
    public String getName() {
        return "UI test provider";
    }

    @Override
    public void requestBooks(Callbacks callbacks) {
        callbacks.onNewDataAcquired(books);
    }

    public void setLongList() {
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BookDetailsImpl details = new BookDetailsImpl();
            details.setPublisher(Publisher.named("a"));
            details.setTranslator(Person.named("a"));
            Book book = new Book(details);
            book.setAuthor(Person.named("aa aa"));
            book.setTitle("Aaaaaaaa aa " + i);
            book.setId("" + i);
            books.add(book);
        }
        this.books = books;
    }

    public void setSingleBook(String title, String author) {
        ArrayList<Book> books = new ArrayList<>();
        BookDetailsImpl details = new BookDetailsImpl();
        Book book = new Book(details);
        book.setAuthor(Person.named(author));
        book.setTitle(title);
        books.add(book);
        this.books = books;
    }
}
