package com.example.bp.ebookmanager.config;

import com.example.bp.ebookmanager.android.config.AndroidConfiguration;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.BookDataProviderImpl;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.dataprovider.empik.AudiobookEmpikWebDataProviderFactory;
import com.example.bp.ebookmanager.dataprovider.empik.EbookEmpikWebDataProviderFactory;
import com.example.bp.ebookmanager.dataprovider.mock.MockBookDataProviderStrategy;
import com.example.bp.ebookmanager.dataprovider.woblink.WoblinkWebDataProviderFactory;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.BookDetailsImpl;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;
import com.example.bp.ebookmanager.model.formats.FormatDetails;
import com.example.bp.ebookmanager.model.formats.Mp3Details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * ebook-manager
 * Created by bart-poleszak on 29.05.2017.
 */

public class TestConfiguration implements Configuration {
    AndroidConfiguration androidConfiguration;

    public TestConfiguration(AndroidConfiguration androidConfiguration) {
        this.androidConfiguration = androidConfiguration;
    }

    @Override
    public WebClientFactory getWebClientFactory() {
        return androidConfiguration.getWebClientFactory();
    }

    @Override
    public UserActionEnabler getUserActionEnabler() {
        return androidConfiguration.getUserActionEnabler();
    }

    @Override
    public List<BookDataProvider> getDataProviders() {
        ArrayList<BookDataProvider> providers = new ArrayList<>();
        providers.add(AudiobookEmpikWebDataProviderFactory.instance().createBookDataProvider());
        return providers;
    }

    @Override
    public BookDataProvider getLocalDataProvider() {
        return new BookDataProvider() {
            @Override
            public String getName() {
                return "fake";
            }

            @Override
            public void requestBooks(Callbacks callbacks) {
                BookDetails details = new BookDetails() {
                    @Override
                    public Person getTranslator() {
                        return null;
                    }

                    @Override
                    public Publisher getPublisher() {
                        return Publisher.named("Fake publisher");
                    }

                    @Override
                    public void setObserver(DetailsObserver observer) {

                    }

                    @Override
                    public List<FormatDetails> getFormats() {
                        ArrayList<FormatDetails> result = new ArrayList<>();
                        result.add(new Mp3Details());
                        return result;
                    }

                    @Override
                    public FormatDetails getFormat(String formatName) {
                        if (formatName.equals(Mp3Details.FORMAT_NAME))
                            return new Mp3Details();
                        return null;
                    }
                };
                Book book = new Book(details);
                book.setTitle("Metro 2034");
                book.setAuthor(Person.named("fake author"));
                book.setId("EmpikMetro 2034");
                ArrayList<Book> books = new ArrayList<>();
                books.add(book);
                callbacks.onNewDataAcquired(books);
            }
        };
    }
}
