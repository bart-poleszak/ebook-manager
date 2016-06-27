package com.example.bp.ebookmanager.model.formats;

/**
 * Ebook Manager
 * Created by bp on 07.05.16.
 */
public abstract class EbookDetails extends FormatDetails {
    @Override
    void acceptVisitor(Visitor visitor) {
        visitor.visitEbookSpecificData(this);
    }
}
