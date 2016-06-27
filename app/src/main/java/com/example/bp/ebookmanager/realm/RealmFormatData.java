package com.example.bp.ebookmanager.realm;

import com.example.bp.ebookmanager.model.Book;
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

    public static String generateId(String formatName, Book book) {
        return book.getId() + formatName;
    }

    public void fromFormatSpecificData(FormatDetails data, Book book) {
        id = generateId(data.getFormatName(), book);
        formatName = data.getFormatName();
        sizeInMb = data.getSizeInMb();
    }

    public FormatDetails toFormatSpecificData() {
        FormatDetails result = FormatDetails.instanceForFormatName(formatName);
        result.setSizeInMb(sizeInMb);
        return result;
    }
}
