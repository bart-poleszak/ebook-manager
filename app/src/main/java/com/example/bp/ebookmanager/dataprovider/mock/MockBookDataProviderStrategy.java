package com.example.bp.ebookmanager.dataprovider.mock;

import com.example.bp.ebookmanager.dataprovider.DataProviderStrategy;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.BookDetailsImpl;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;
import com.example.bp.ebookmanager.model.formats.EpubDetails;
import com.example.bp.ebookmanager.model.formats.MobiDetails;
import com.example.bp.ebookmanager.model.formats.Mp3Details;
import com.example.bp.ebookmanager.model.formats.PdfDetails;

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
        BookDetailsImpl details = new BookDetailsImpl();
        details.setPublisher(greg);

        Book book = new Book(details);
        book.setTitle("[mock] Ogniem i Mieczem");
        book.setAuthor(hSienkiewicz);

        book.getFormatNames().add(EpubDetails.FORMAT_NAME);
        book.getFormatNames().add(MobiDetails.FORMAT_NAME);

        result.add(book);

        book = new Book();
        book.setTitle("[mock] Potop");
        book.setAuthor(hSienkiewicz);

        book.getFormatNames().add(EpubDetails.FORMAT_NAME);
//        Person narrator = Person.named("Krzysztof Gosztyła");
//        mp3SpecificData.setNarrator(narrator);
        book.getFormatNames().add(Mp3Details.FORMAT_NAME);
        result.add(book);

        book = new Book();
        book.setTitle("[mock] Metro 2033");
        Person dGlukhowsky = Person.named("Dmitry Glukhovsky");
        book.setAuthor(dGlukhowsky);
        book.getFormatNames().add(PdfDetails.FORMAT_NAME);
        result.add(book);

        return result;
    }
}
