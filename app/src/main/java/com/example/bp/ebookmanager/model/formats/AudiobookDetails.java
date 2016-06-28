package com.example.bp.ebookmanager.model.formats;

import com.example.bp.ebookmanager.model.Person;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public abstract class AudiobookDetails extends FormatDetails {
    private Person narrator;

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitAudiobookSpecificData(this);
    }

    public void setNarrator(Person narrator) {
        this.narrator = narrator;
    }

    public Person getNarrator() {
        return narrator;
    }
}
