package com.example.bp.ebookmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bp.ebookmanager.android.AndroidThumbnailVisitor;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;
import com.example.bp.ebookmanager.model.ThumbnailVisitor;
import com.example.bp.ebookmanager.model.formats.FormatSpecificData;
import com.example.bp.ebookmanager.realm.RealmBook;

import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

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
    private DetailsListAdapter adapter;
    private Context ctx;
    private ThumbnailVisitor thumbnailVisitor;

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
        thumbnailVisitor = new AndroidThumbnailVisitor(thumbnail);

        fillBasicData();

        ctx = getContext();
        adapter = new DetailsListAdapter(ctx);
        listView.setAdapter(adapter);

        book.setDetailsObserver(new BookDetails.DetailsObserver() {
            @Override
            public void onDetailsChanged() {
                fillDetails();
            }
        });

        fillDetails();
        return view;
    }

    private void fillDetails() {
        adapter.clear();

        Publisher publisher = book.getPublisher();
        if (publisher != null)
            adapter.addRow(ctx.getString(R.string.publisher), publisher.getName());

        Person translator = book.getTranslator();
        if (translator != null)
            adapter.addRow(ctx.getString(R.string.translator), translator.getName());

        HashSet<String> formats = book.getFormatNames();
        StringBuilder builder = new StringBuilder();
        for (String format : formats) {
            builder.append(format);
            builder.append(", ");
        }
        adapter.addRow(ctx.getString(R.string.format), builder.substring(0, builder.length() - 2));

        for (FormatSpecificData formatData : book.getFormatSpecificDataList()) {
            String key = formatData.getFormatName() + " size";
            adapter.addRow(key, String.valueOf(formatData.getSizeInMb()) + " MB");
        }
        updateRealm();
    }

    private void updateRealm() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmBook realmBook = realm.where(RealmBook.class)
                .equalTo("id", book.getId())
                .findFirst();
        realmBook.fillDetails(book);
        realm.commitTransaction();
    }

    private void fillBasicData() {
        title.setText(book.getTitle());
        author.setText(book.getAuthor().getName());
        if (book.getThumbnail() != null)
            book.getThumbnail().fill(thumbnailVisitor);
    }

}
