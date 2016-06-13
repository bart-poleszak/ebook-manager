package com.example.bp.ebookmanager.dataprovider.mock;

import com.example.bp.ebookmanager.dataprovider.DataProviderStrategy;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;
import com.example.bp.ebookmanager.model.formats.EpubSpecificData;
import com.example.bp.ebookmanager.model.formats.MobiSpecificData;
import com.example.bp.ebookmanager.model.formats.Mp3SpecificData;
import com.example.bp.ebookmanager.model.formats.PdfSpecificData;

import java.util.ArrayList;
import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 08.05.16.
 */
public class MockBookDataProviderStrategy implements DataProviderStrategy {

    private Callbacks callbacks;

    @Override
    public void gainAccess(Callbacks callback) {
        callbacks = callback;
        callbacks.onAccessGained();
    }

    @Override
    public List<Book> getBooks() {
        ArrayList<Book> result = new ArrayList<>();
        Person hSienkiewicz = Person.named("Henryk Sienkiewicz");
        Publisher greg = Publisher.named("GREG");

        Book book = new Book();
        book.setTitle("[mock] Ogniem i Mieczem");
        book.setAuthor(hSienkiewicz);

        book.getFormats().add(new EpubSpecificData());
        book.getFormats().add(new MobiSpecificData());

        result.add(book);

        book = new Book();
        book.setTitle("[mock] Potop");
        book.setAuthor(hSienkiewicz);

        book.getFormats().add(new EpubSpecificData());
        Mp3SpecificData mp3SpecificData = new Mp3SpecificData();
        mp3SpecificData.setLenght(23456);
        Person narrator = Person.named("Krzysztof Gosztyła");
        mp3SpecificData.setNarrator(narrator);
        book.getFormats().add(mp3SpecificData);
        result.add(book);

        book = new Book();
        book.setTitle("[mock] Metro 2033");
        Person dGlukhowsky = Person.named("Dmitry Glukhovsky");
        book.setAuthor(dGlukhowsky);
        book.getFormats().add(new PdfSpecificData());
        result.add(book);

        return result;
    }

    @Override
    public void enableUserAction(UserActionEnabler visitor) {

    }
}
