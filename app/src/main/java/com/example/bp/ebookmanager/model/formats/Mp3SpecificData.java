package com.example.bp.ebookmanager.model.formats;

/**
 * Created by bp on 07.05.16.
 */
public class Mp3SpecificData implements FormatSpecificData {

    @Override
    public String getFormatName() {
        return "MP3";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitMp3SpecificData(this);
    }
}
