package com.example.bp.ebookmanager;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bp.ebookmanager.android.AndroidThumbnailVisitor;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;
import com.example.bp.ebookmanager.model.ThumbnailVisitor;
import com.example.bp.ebookmanager.model.formats.FormatDetails;
import com.example.bp.ebookmanager.realm.RealmBook;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.formatSpinner) Spinner formatSpinner;
    @BindView(R.id.downloadButton) ImageButton downloadButton;

    private Book book;
    private DetailsListAdapter adapter;
    private ArrayAdapter<String> spinnerAdapter;
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

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formatName = (String) formatSpinner.getSelectedItem();
                for (FormatDetails format : book.getFormatDetailsList()) {
                    if (format.getFormatName().equals(formatName)) {
                        downloadEbook(format);
                        return;
                    }
                }
                throw new RuntimeException("No such format");
            }
        });
        return view;
    }

    private void downloadEbook(FormatDetails format) {

        Uri uri = Uri.parse(format.getDownloadUrl());
        DownloadManager.Request request = new DownloadManager.Request(uri);

        CookieManager cookieManager = CookieManager.getInstance();
        String cookie = cookieManager.getCookie(uri.getHost());
        request.addRequestHeader("Cookie", cookie);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, book.getTitle() + "." + format.getFormatName());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        DownloadManager dm = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        dm.enqueue(request);
    }

    private void fillDetails() {
        adapter.clear();

        Publisher publisher = book.getPublisher();
        if (publisher != null)
            adapter.addRow(ctx.getString(R.string.publisher), publisher.getName());

        Person translator = book.getTranslator();
        if (translator != null)
            adapter.addRow(ctx.getString(R.string.translator), translator.getName());

        List<FormatDetails> formats = book.getFormatDetailsList();
        StringBuilder builder = new StringBuilder();
        for (FormatDetails format : formats) {
            builder.append(format.getFormatName());
            builder.append(", ");
        }
        if (formats.size() > 0)
            adapter.addRow(ctx.getString(R.string.format), builder.substring(0, builder.length() - 2));

        fillFormatDetails();
        updateRealm();
    }

    private void fillFormatDetails() {
        ArrayList<String> formatsForDownload = new ArrayList<>();
        for (FormatDetails formatData : book.getFormatDetailsList()) {
            String key = formatData.getFormatName() + " size";
            Double sizeInMb = formatData.getSizeInMb();
            if (sizeInMb != null)
                adapter.addRow(key, String.valueOf(sizeInMb) + " MB");

            if (formatData.getDownloadUrl() != null)
                formatsForDownload.add(formatData.getFormatName());
        }
        spinnerAdapter = new ArrayAdapter<>(ctx, R.layout.spinner_item, formatsForDownload);
        formatSpinner.setAdapter(spinnerAdapter);
        if (spinnerAdapter.getCount() == 0)
            downloadButton.setEnabled(false);
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
