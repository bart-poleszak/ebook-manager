package com.example.bp.ebookmanager.realm;

import io.realm.RealmObject;

/**
 * Ebook Manager
 * Created by bp on 26.06.16.
 */
public class RealmString extends RealmObject {
    private String content;

    public RealmString() {
        this("");
    }

    public RealmString(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
