package com.example.bp.ebookmanager.viewmodel;

import com.example.bp.ebookmanager.model.formats.EpubSpecificData;
import com.example.bp.ebookmanager.model.formats.FormatSpecificData;
import com.example.bp.ebookmanager.model.formats.MobiSpecificData;
import com.example.bp.ebookmanager.model.formats.Mp3SpecificData;
import com.example.bp.ebookmanager.model.formats.PdfSpecificData;

/**
 * Created by bp on 07.05.16.
 */
public class BookDetailsViewModel {

    private final Visitor specificFormatVisitor;

    public BookDetailsViewModel() {
        specificFormatVisitor = new Visitor();
    }

    public Visitor getSpecificFormatVisitor() {
        return specificFormatVisitor;
    }

    private class Visitor implements FormatSpecificData.Visitor {

        @Override
        public void visitMp3SpecificData(Mp3SpecificData data) {

        }

        @Override
        public void visitEpubSpecificData(EpubSpecificData data) {

        }

        @Override
        public void visitMobiSpecificData(MobiSpecificData data) {

        }

        @Override
        public void visitPdfSpecificData(PdfSpecificData data) {

        }
    }
}
