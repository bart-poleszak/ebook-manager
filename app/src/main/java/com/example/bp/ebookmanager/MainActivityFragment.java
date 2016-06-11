package com.example.bp.ebookmanager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.DataProviderStrategy;
import com.example.bp.ebookmanager.dataprovider.WebDataProvderStrategy;
import com.example.bp.ebookmanager.dataprovider.android.AndroidBookDataProvider;
import com.example.bp.ebookmanager.dataprovider.android.AndroidUserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.android.HeadlessWebClient;
import com.example.bp.ebookmanager.dataprovider.android.VisibleWebClient;
import com.example.bp.ebookmanager.dataprovider.mock.MockWebActionContext;
import com.example.bp.ebookmanager.mainlist.MainListAdapter;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.viewmodel.BookDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.listView) ListView listView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        final MainListAdapter adapter = new MainListAdapter(getContext());
        listView.setAdapter(adapter);
//        MultipleDataProvider dataProvider = new MultipleDataProvider();
//        dataProvider.addDataProvider(new AndroidBookDataProvider(new MockBookDataProviderStrategy()));
//        dataProvider.addDataProvider(new AndroidBookDataProvider(new MockBookDataProviderStrategy()));
        WebDataProvderStrategy strategy = new WebDataProvderStrategy();
        strategy.setWebClient(new HeadlessWebClient());
        strategy.setWebActionContext(new MockWebActionContext());
        BookDataProvider dataProvider = new AndroidBookDataProvider(strategy);
        dataProvider.requestBooks(new BookDataProvider.Callbacks() {
            @Override
            public void onNewDataAcquired(List<Book> data) {
                ArrayList<BookDetailsViewModel> viewModels = new ArrayList<>();
                for (Book book : data)
                    viewModels.add(new BookDetailsViewModel(book));

                adapter.addItems(viewModels);
            }

            @Override
            public void onDataAcquisitionFailed() {
                Log.d("MainActivityFragment", "Data acquisition failed");
            }

            @Override
            public void enableUserActions(DataProviderStrategy strategy) {
                AndroidUserActionEnabler userActionEnabler = new AndroidUserActionEnabler(getContext());
                strategy.enableUserAction(userActionEnabler);
            }

        });

        return view;
    }
}
