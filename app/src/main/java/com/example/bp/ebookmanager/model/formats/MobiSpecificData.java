package com.example.bp.ebookmanager.model.formats;

/**
 * Created by bp on 07.05.16.
 */
public class MobiSpecificData implements EbookSpecificData {

    public static final String FORMAT_NAME = "MOBI";

    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitEbookSpecificData(this);
    }
}
