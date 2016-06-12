package com.example.bp.ebookmanager.dataprovider.mock;

import com.example.bp.ebookmanager.dataprovider.WebActionState;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.formats.PdfSpecificData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bp on 11.06.16.
 */
public class FinalMockWebActionState implements WebActionState {

    private boolean completed = false;

    @Override
    public String getTargetSiteURL() {
        return "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
    }

    @Override
    public void processRecievedData(String url, String source) {
        if (url.contains("11_czerwca"))
            completed = true;
    }

    @Override
    public boolean isActionCompleted() {
        return completed;
    }

    @Override
    public boolean isUserActionNeeded() {
        return true;
    }

    @Override
    public List<Book> getBooks() {
        ArrayList<Book> result = new ArrayList<>();
        Book book = new Book();
        book.setTitle("Zamokowana ksiazka z MockWebActionContext");
        Person dGlukhowsky = new Person();
        dGlukhowsky.setName("Dmitry Glukhovsky");
        book.setAuthor(dGlukhowsky);
        book.getFormats().add(new PdfSpecificData());
        result.add(book);
        return result;
    }
}
