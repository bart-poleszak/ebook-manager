package com.example.bp.ebookmanager.viewmodel;

import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.formats.AudiobookSpecificData;
import com.example.bp.ebookmanager.model.formats.EbookSpecificData;
import com.example.bp.ebookmanager.model.formats.FormatSpecificData;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class BookDetailsViewModel {

    private static String NARRATOR = "Narrator!";
    private static String TITLE = "Title!";
    private static String AUTHOR = "Author!";
    private static String TRANSLATOR = "Translator!";
    private static String PUBLISHER = "Publisher!";

    private final Visitor specificFormatVisitor;
    private final LinkedHashMap<String, String> details = new LinkedHashMap<>();
    private final ArrayList<String> formats = new ArrayList<>();

    public BookDetailsViewModel(Book book) {
        specificFormatVisitor = new Visitor();
        fillBasicData(book);
    }

    public void fillWithDetails(Book book) {
        fillBasicData(book);
        if (book.getTranslator() != null)
            details.put(TRANSLATOR, book.getTranslator().getName());
        if (book.getPublisher() != null)
            details.put(PUBLISHER, book.getPublisher().getName());
    }

    public void fillBasicData(Book book) {
        details.put(TITLE, book.getTitle());
        if (book.getAuthor() != null)
            details.put(AUTHOR, book.getAuthor().getName());

        for (FormatSpecificData data : book.getFormats()) {
            formats.add(data.getFormatName());
            data.acceptVisitor(specificFormatVisitor);
        }
    }

    public LinkedHashMap<String, String> getDetails() {
        return details;
    }

    private String getDetailOrEmpty(String key) {
        String result = details.get(key);
        if (result != null)
            return result;
        return "";
    }

    public String getTitle() {
        return getDetailOrEmpty(TITLE);
    }

    public String getAuthor() {
        return getDetailOrEmpty(AUTHOR);
    }

    public ArrayList<String> getFormats() {
        return formats;
    }

    private class Visitor implements FormatSpecificData.Visitor {

        @Override
        public void visitAudiobookSpecificData(AudiobookSpecificData data) {
            if (data.getNarrator() != null)
                details.put(NARRATOR, data.getNarrator().getName());
        }

        @Override
        public void visitEbookSpecificData(EbookSpecificData data) {
        }
    }
}
