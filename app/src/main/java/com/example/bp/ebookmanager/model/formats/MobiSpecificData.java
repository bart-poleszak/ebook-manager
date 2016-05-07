package com.example.bp.ebookmanager.model.formats;

/**
 * Created by bp on 07.05.16.
 */
public class MobiSpecificData implements FormatSpecificData {
    @Override
    public String getFormatName() {
        return "MOBI";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitMobiSpecificData(this);
    }
}
