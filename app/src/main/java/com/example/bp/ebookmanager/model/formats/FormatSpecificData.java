package com.example.bp.ebookmanager.model.formats;

/**
 * Created by bp on 07.05.16.
 */
public interface FormatSpecificData {
    String getFormatName();
    void acceptVisitor(Visitor visitor);

    interface Visitor {
        void visitAudiobookSpecificData(AudiobookSpecificData data);
        void visitEbookSpecificData(EbookSpecificData data);
    }
}
