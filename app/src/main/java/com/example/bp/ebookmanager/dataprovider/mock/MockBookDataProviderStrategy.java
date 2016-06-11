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
    public void retryToGainAccess() {
        callbacks.onAccessGained();
    }

    @Override
    public List<Book> getBooks() {
        ArrayList<Book> result = new ArrayList<>();
        Person hSienkiewicz = new Person();
        hSienkiewicz.setName("Henryk Sienkiewicz");
        Publisher greg = new Publisher();
        greg.setName("GREG");

        Book book = new Book();
        book.setTitle("Ogniem i Mieczem");
        book.setAuthor(hSienkiewicz);
        book.setPublisher(greg);

        book.getFormats().add(new EpubSpecificData());
        book.getFormats().add(new MobiSpecificData());

        result.add(book);

        book = new Book();
        book.setTitle("Potop");
        book.setAuthor(hSienkiewicz);
        book.setPublisher(greg);

        book.getFormats().add(new EpubSpecificData());
        Mp3SpecificData mp3SpecificData = new Mp3SpecificData();
        mp3SpecificData.setLenght(23456);
        Person narrator = new Person();
        narrator.setName("Krzysztof Gosztyła");
        mp3SpecificData.setNarrator(narrator);
        book.getFormats().add(mp3SpecificData);
        result.add(book);

        book = new Book();
        book.setTitle("Metro 2033");
        Person dGlukhowsky = new Person();
        dGlukhowsky.setName("Dmitry Glukhovsky");
        book.setAuthor(dGlukhowsky);
        book.getFormats().add(new PdfSpecificData());
        result.add(book);

        return result;
    }

    @Override
    public void enableUserAction(UserActionEnabler visitor) {

    }
}
