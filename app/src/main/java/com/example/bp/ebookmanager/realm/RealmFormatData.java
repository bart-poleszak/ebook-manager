package com.example.bp.ebookmanager.realm;

import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.formats.AudiobookDetails;
import com.example.bp.ebookmanager.model.formats.EbookDetails;
import com.example.bp.ebookmanager.model.formats.FormatDetails;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Ebook Manager
 * Created by bp on 27.06.16.
 */
public class RealmFormatData extends RealmObject {

    @PrimaryKey
    private String id;
    private String formatName;
    private Double sizeInMb;
    private String downloadUrl;
    private RealmPerson narrator;

    public static String generateId(String formatName, Book book) {
        return book.getId() + formatName;
    }

    public void fromFormatSpecificData(FormatDetails data, Book book) {
        id = generateId(data.getFormatName(), book);
        formatName = data.getFormatName();
        sizeInMb = data.getSizeInMb();
        downloadUrl = data.getDownloadUrl();
        data.acceptVisitor(new ReadFormatVisitor());
    }

    public FormatDetails toFormatSpecificData() {
        FormatDetails result = FormatDetails.instanceForFormatName(formatName);
        result.setSizeInMb(sizeInMb);
        result.setDownloadUrl(downloadUrl);
        result.acceptVisitor(new FillFormatVisitor());
        return result;
    }

    private class ReadFormatVisitor implements FormatDetails.Visitor {

        @Override
        public void visitAudiobookSpecificData(AudiobookDetails data) {
            Person narrator = data.getNarrator();
            if (narrator != null)
                RealmFormatData.this.narrator = RealmPerson.createOrFind(narrator);

        }

        @Override
        public void visitEbookSpecificData(EbookDetails data) {

        }
    }

    private class FillFormatVisitor implements FormatDetails.Visitor {

        @Override
        public void visitAudiobookSpecificData(AudiobookDetails data) {
            if (narrator != null)
                data.setNarrator(narrator.toPerson());
        }

        @Override
        public void visitEbookSpecificData(EbookDetails data) {

        }
    }
}
