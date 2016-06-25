package com.example.bp.ebookmanager.dataprovider.realm;

import com.example.bp.ebookmanager.dataprovider.DataProviderStrategy;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.realm.RealmBook;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Ebook Manager
 * Created by bp on 25.06.16.
 */
public class RealmDataProviderStrategy implements DataProviderStrategy {
    private static Realm realm = null;

    @Override
    public void gainAccess(Callbacks callback) {
        realm = Realm.getDefaultInstance();
        callback.onAccessGained();
    }

    @Override
    public List<Book> getBooks() {
        ArrayList<Book> result = new ArrayList<>();
        RealmQuery<RealmBook> query = realm.where(RealmBook.class);
        RealmResults<RealmBook> all = query.findAll();
        for (RealmBook realmBook : all)
            result.add(realmBook.toBook());
        return result;
    }
}
