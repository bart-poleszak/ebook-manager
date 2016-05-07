package com.example.bp.ebookmanager.model.formats;

/**
 * Created by bp on 07.05.16.
 */
public class PdfSpecificData implements EbookSpecificData {
    @Override
    public String getFormatName() {
        return "PDF";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitEbookSpecificData(this);
    }
}
