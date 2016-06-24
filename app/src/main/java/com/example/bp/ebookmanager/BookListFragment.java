package com.example.bp.ebookmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.BookDataProviderImpl;
import com.example.bp.ebookmanager.dataprovider.MultipleDataProvider;
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
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fillList();
                }
            });
        }

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
        adapter.clear();
        MainActivity activity = (MainActivity) getActivity();
        BookDataProvider dataProvider = activity.getProviderForSync();
        dataProvider.requestBooks(new BookDataProviderCallbacks());
    }

    private class BookDataProviderCallbacks implements BookDataProvider.Callbacks {
        @Override
        public void onNewDataAcquired(List<Book> data) {
            adapter.addItems(data);
        }

        @Override
        public void onDataAcquisitionFailed() {
            Toast toast = Toast.makeText(getContext(), "Data acquisition failed", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
