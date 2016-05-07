package com.example.bp.ebookmanager.model.formats;

/**
 * Created by bp on 07.05.16.
 */
public class EpubSpecificData implements FormatSpecificData {
    @Override
    public String getFormatName() {
        return "EPUB";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitEpubSpecificData(this);
    }
}
