package com.example.bp.ebookmanager.realm;

import io.realm.RealmObject;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class RealmPublisher extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
