package com.example.bp.ebookmanager.realm;

import com.example.bp.ebookmanager.model.Publisher;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class RealmPublisher extends RealmObject {

    @PrimaryKey
    private String name;

    public void fromPublisher(Publisher publisher) {
        name = publisher.getName();
    }

    public Publisher toPublisher() {
        return Publisher.named(name);
    }
}
