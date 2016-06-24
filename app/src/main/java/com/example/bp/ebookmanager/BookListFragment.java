package com.example.bp.ebookmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.BookDataProviderImpl;
import com.example.bp.ebookmanager.dataprovider.DataProviderStrategy;
import com.example.bp.ebookmanager.dataprovider.MultipleDataProvider;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.mock.MockBookDataProviderStrategy;
import com.example.bp.ebookmanager.dataprovider.woblink.WoblinkWebDataProviderFactory;
import com.example.bp.ebookmanager.mainlist.MainListAdapter;
import com.example.bp.ebookmanager.model.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class BookListFragment extends Fragment {

    @BindView(R.id.listView) ListView listView;
    private MainListAdapter adapter;

    public BookListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        initializeListView();
        fillList();

        return view;
    }

    private void initializeListView() {
        adapter = new MainListAdapter(getContext());
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

    private void fillList() {
        MultipleDataProvider dataProvider = new MultipleDataProvider();
        dataProvider.addDataProvider(new BookDataProviderImpl(new MockBookDataProviderStrategy()));

        WoblinkWebDataProviderFactory woblinkFactory = WoblinkWebDataProviderFactory.instance();
        dataProvider.addDataProvider(woblinkFactory.createBookDataProvider());
        dataProvider.requestBooks(new BookDataProviderCallbacks());
    }

    private class BookDataProviderCallbacks implements BookDataProvider.Callbacks {
        @Override
        public void onNewDataAcquired(List<Book> data) {
            adapter.addItems(data);
        }

        @Override
        public void onDataAcquisitionFailed() {
            Log.d("BookListFragment", "Data acquisition failed");
        }

    }
}
