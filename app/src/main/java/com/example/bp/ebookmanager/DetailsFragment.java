package com.example.bp.ebookmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bp.ebookmanager.model.Book;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Ebook Manager
 * Created by bp on 13.06.16.
 */
public class DetailsFragment extends Fragment {

    @BindView(R.id.detailsListView) ListView listView;
    @BindView(R.id.detailsTitle) TextView title;
    @BindView(R.id.detailsAuthor) TextView author;
    @BindView(R.id.detailsThumbnail) ImageView thumbnail;

    private Book book;

    public DetailsFragment() {
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        fillBasicData();

        DetailsListAdapter adapter = new DetailsListAdapter(getContext());
        listView.setAdapter(adapter);
        return view;
    }

    private void fillBasicData() {
        title.setText(book.getTitle());
        author.setText(book.getAuthor().getName());
    }

}
