package com.example.bp.ebookmanager.viewmodel;

import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.formats.Mp3SpecificData;
import com.example.bp.ebookmanager.viewmodel.BookDetailsViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class BookDetailsViewModelTest {
    Book authorNarratorBook;

    @Before
    public void initializeBook() {
        authorNarratorBook = new Book();
        authorNarratorBook.setTitle("The Title");
        Person author = new Person();
        author.setName("Aaaa Aaaaaaaa");
        authorNarratorBook.setAuthor(author);

        Mp3SpecificData mp3Data = new Mp3SpecificData();
        Person narrator = new Person();
        narrator.setName("Bbbb Bbbbbbbb");
        mp3Data.setNarrator(narrator);
        authorNarratorBook.getFormats().add(mp3Data);
    }

    @Test
    public void authorNarratorBook_hasThreeEntriesInViewModel() throws Exception {
        // given
        BookDetailsViewModel viewModel = new BookDetailsViewModel();
        // when
        viewModel.fill(authorNarratorBook);
        // then
        assertEquals(3, viewModel.getDetails().size());
    }
}