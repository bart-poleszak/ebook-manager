package com.example.bp.ebookmanager.realm;

import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.BookDetailsImpl;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;
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
    private RealmPerson translator;
    private RealmPublisher publisher;

    public Book toBook() {
        BookDetailsImpl bookDetails = new BookDetailsImpl();
        if (translator != null)
            bookDetails.setTranslator(translator.toPerson());
        if (publisher != null)
            bookDetails.setPublisher(publisher.toPublisher());

        Book result = new Book(bookDetails);
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

        Person author = book.getAuthor();
        RealmPerson realmPerson = createOrFindPerson(author);

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

    private void setTranslator(Person translator) {
        this.translator = createOrFindPerson(translator);
    }

    private RealmPerson createOrFindPerson(Person person) {
        Realm realm = Realm.getDefaultInstance();
        RealmPerson realmPerson = realm.where(RealmPerson.class)
                .equalTo("name", person.getName())
                .findFirst();
        if (realmPerson == null) {
            realmPerson = realm.createObject(RealmPerson.class);
            realmPerson.fromPerson(person);
        }
        return realmPerson;
    }

    private void setPublisher(Publisher publisher) {
        Realm realm = Realm.getDefaultInstance();
        this.publisher = realm.where(RealmPublisher.class)
                .equalTo("name", publisher.getName())
                .findFirst();
        if (this.publisher == null) {
            this.publisher = realm.createObject(RealmPublisher.class);
            this.publisher.fromPublisher(publisher);
        }
    }

    public void fillDetails(Book book) {
        Person translator = book.getTranslator();
        if (translator != null)
            setTranslator(translator);

        Publisher publisher = book.getPublisher();
        if (publisher != null)
            setPublisher(publisher);
    }
}
