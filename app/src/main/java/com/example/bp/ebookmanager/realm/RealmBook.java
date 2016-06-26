package com.example.bp.ebookmanager.realm;

import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.NullBookDetails;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.RawThumbnail;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class RealmBook extends RealmObject {

    @PrimaryKey
    private String id;
    private String title;
    private RealmPerson author;
    private byte[] thumbnail;
    private RealmList<RealmString> formats = new RealmList<>();

    public Book toBook() {
        Book result = new Book(NullBookDetails.instance());
        result.setId(id);
        result.setTitle(title);
        result.setAuthor(author.toPerson());
        if (thumbnail != null)
            result.setThumbnail(new RawThumbnail(thumbnail));
        for (RealmString format : formats)
            result.getFormatNames().add(format.toString());

        return result;
    }

    public void fromBook(Book book) {
        id = book.getId();
        title = book.getTitle();

        Realm realm = Realm.getDefaultInstance();

        Person author = book.getAuthor();
        RealmPerson realmPerson = realm.where(RealmPerson.class)
                .equalTo("name", author.getName())
                .findFirst();
        if (realmPerson == null) {
            realmPerson = realm.createObject(RealmPerson.class);
            realmPerson.fromPerson(author);
        }

        for (String s : book.getFormatNames())
            formats.add(new RealmString(s));

        this.author = realmPerson;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}
