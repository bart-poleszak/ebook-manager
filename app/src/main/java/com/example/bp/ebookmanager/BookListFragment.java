package com.example.bp.ebookmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.realm.RealmBook;
import com.example.bp.ebookmanager.realm.RealmPerson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class BookListFragment extends Fragment {

    @BindView(R.id.listView) ListView listView;
    @BindView(R.id.fab) FloatingActionButton fab;
    private BookListAdapter adapter;

    public BookListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        initializeListView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                syncList();
            }
        });

        if (adapter.isEmpty()) {
            fab.setEnabled(false);
            fillWithLocalData();
        }

        return view;
    }

    private void fillWithLocalData() {
        BookDataProvider localDataProvider = ConfigManager.get().getLocalDataProvider();
        localDataProvider.requestBooks(new BookDataProvider.Callbacks() {
            @Override
            public void onNewDataAcquired(List<Book> data) {
                adapter.updateItems(data);
                fab.setEnabled(true);
            }

            @Override
            public void onDataAcquisitionFailed() {
                dataAquisitionFailedToast();
            }
        });
    }

    private void initializeListView() {
        if (adapter == null)
            adapter = new BookListAdapter(getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) adapter.getItem(position);
                MainActivity activity = (MainActivity) getActivity();
                activity.showBookDetails(book);
            }
        });
    }

    private void syncList() {
        MainActivity activity = (MainActivity) getActivity();
        BookDataProvider dataProvider = activity.getProviderForSync();
        dataProvider.requestBooks(new BookDataProviderWithRealmUpdateCallbacks());
    }

    private class BookDataProviderWithRealmUpdateCallbacks implements BookDataProvider.Callbacks {
        @Override
        public void onNewDataAcquired(List<Book> data) {
            adapter.updateItems(data);
            updateRealm(data);
        }

        @Override
        public void onDataAcquisitionFailed() {
            dataAquisitionFailedToast();
        }

    }

    private void dataAquisitionFailedToast() {
        Toast toast = Toast.makeText(getContext(), "Data acquisition failed", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void updateRealm(List<Book> data) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (Book book : data)
            addBookToRealmIfNeeded(realm, book);
        realm.commitTransaction();
    }

    private void addBookToRealmIfNeeded(Realm realm, Book book) {
        RealmBook realmBook = realm.where(RealmBook.class)
                .equalTo("id", book.getId())
                .findFirst();
        if (realmBook == null) {
            realmBook = realm.createObject(RealmBook.class);
            realmBook.fromBook(book);
        }

    }
}
