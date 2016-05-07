package com.example.bp.ebookmanager.model.formats;

/**
 * Created by bp on 07.05.16.
 */
public interface FormatSpecificData {
    String getFormatName();
    void acceptVisitor(Visitor visitor);

    interface Visitor {
        void visitMp3SpecificData(Mp3SpecificData data);
        void visitEpubSpecificData(EpubSpecificData data);
        void visitMobiSpecificData(MobiSpecificData data);
        void visitPdfSpecificData(PdfSpecificData data);
    }
}
