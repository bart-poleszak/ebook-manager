package com.example.bp.ebookmanager.model.formats;

import com.example.bp.ebookmanager.model.Person;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class Mp3Details extends AudiobookSpecificData {

    public static final String FORMAT_NAME = "MP3";
    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }
}
