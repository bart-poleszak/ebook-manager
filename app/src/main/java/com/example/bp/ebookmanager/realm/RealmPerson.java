package com.example.bp.ebookmanager.realm;

import com.example.bp.ebookmanager.model.Person;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class RealmPerson extends RealmObject {

    @PrimaryKey
    private String name;

    public void fromPerson(Person person) {
        name = person.getName();
    }

    public Person toPerson() {
        return Person.named(name);
    }

    public static RealmPerson createOrFind(Person person) {
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
}
