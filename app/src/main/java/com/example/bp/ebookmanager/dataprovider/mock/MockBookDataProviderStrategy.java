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
        book.setId("mock1");
        book.setTitle("[mock] Ogniem i Mieczem");
        book.setAuthor(hSienkiewicz);

        book.getFormatDetailsList().add(new EpubDetails());
        book.getFormatDetailsList().add(new MobiDetails());

        result.add(book);

        details = new BookDetailsImpl();
        details.setPublisher(greg);
        book = new Book(details);
        book.setId("mock2");
        book.setTitle("[mock] Potop");
        book.setAuthor(hSienkiewicz);

        book.getFormatDetailsList().add(new EpubDetails());
//        Person narrator = Person.named("Krzysztof Gosztyła");
//        mp3SpecificData.setNarrator(narrator);
        book.getFormatDetailsList().add(new Mp3Details());
        result.add(book);

        details = new BookDetailsImpl();
        details.setPublisher(greg);
        book = new Book(details);
        book.setId("mock3");
        book.setTitle("[mock] Lalka");
        Person dGlukhowsky = Person.named("Bolesław Prus");
        book.setAuthor(dGlukhowsky);
        book.getFormatDetailsList().add(new PdfDetails());
        result.add(book);

        return result;
    }
}
