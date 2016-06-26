package com.example.bp.ebookmanager.model.formats;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class EpubDetails extends EbookSpecificData {

    public static final String FORMAT_NAME = "EPUB";

    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }
}
