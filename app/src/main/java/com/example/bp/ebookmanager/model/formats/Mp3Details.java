package com.example.bp.ebookmanager.model.formats;

import com.example.bp.ebookmanager.model.Person;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class Mp3Details implements AudiobookSpecificData {

    public static final String FORMAT_NAME = "MP3";
    private Person narrator;
    private Integer lenght;

    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }

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

    @Override
    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    @Override
    public Integer getLenght() {
        return lenght;
    }
}
