package com.example.bp.ebookmanager.model.formats;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class Mp3Details extends AudiobookDetails {

    public static final String FORMAT_NAME = "MP3";
    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }
}
