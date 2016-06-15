package com.example.bp.ebookmanager.model.formats;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public class PdfDetails implements EbookSpecificData {

    public static final String FORMAT_NAME = "PDF";

    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitEbookSpecificData(this);
    }
}
