package com.example.bp.ebookmanager.model;

import com.example.bp.ebookmanager.model.formats.FormatDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class NullBookDetails implements BookDetails {
    private static NullBookDetails inst;

    private NullBookDetails() {}

    public static NullBookDetails instance() {
        if (inst == null)
            inst = new NullBookDetails();
        return inst;
    }

    @Override
    public Person getTranslator() {
        return null;
    }

    @Override
    public Publisher getPublisher() {
        return null;
    }

    @Override
    public void setObserver(DetailsObserver observer) {

    }

    @Override
    public List<FormatDetails> getFormats() {
        return Collections.emptyList();
    }

    @Override
    public FormatDetails getFormat(String formatName) {
        return null;
    }

}
