package com.example.bp.ebookmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bp.ebookmanager.R;
import com.example.bp.ebookmanager.android.AndroidThumbnailVisitor;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.ThumbnailVisitor;
import com.example.bp.ebookmanager.model.formats.EpubDetails;
import com.example.bp.ebookmanager.model.formats.MobiDetails;
import com.example.bp.ebookmanager.model.formats.Mp3Details;
import com.example.bp.ebookmanager.model.formats.PdfDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Ebook Manager
 * Created by bp on 06.05.16.
 */
public class BookListAdapter extends BaseAdapter {
    private ArrayList<Book> data = new ArrayList<>();
    private HashMap<String, Integer> idIndexMap = new HashMap<>();
    private Context context;

    public BookListAdapter(Context context) {
        this.context = context;
    }

    public void addItem(Book item) {
        data.add(item);
        idIndexMap.put(item.getId(), data.size() - 1);
        notifyDataSetChanged();
    }

    public void updateItems(List<Book> items) {
        for (Book book : items) {
            Integer index = idIndexMap.get(book.getId());
            if (index != null) {
                data.set(index, book);
                notifyDataSetChanged();
            } else
                addItem(book);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflateRow();
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Book book = (Book) getItem(position);
        fillRowContent(holder, book);
        return convertView;
    }

    @NonNull
    private View inflateRow() {
        View convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.main_list_row, null);
        convertView.setTag(new ViewHolder(convertView));
        return convertView;
    }

    private void fillRowContent(ViewHolder holder, Book book) {
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor().getName());
        if (book.getThumbnail() != null)
            book.getThumbnail().fill(holder.thumbnailVisitor);
        setVisibilityForFormat(holder.epubImageView, book, EpubDetails.FORMAT_NAME);
        setVisibilityForFormat(holder.mobiImageView, book, MobiDetails.FORMAT_NAME);
        setVisibilityForFormat(holder.pdfImageView, book, PdfDetails.FORMAT_NAME);
        setVisibilityForFormat(holder.mp3ImageView, book, Mp3Details.FORMAT_NAME);
    }

    private void setVisibilityForFormat(View view, Book book, String formatName) {
        if (book.getFormatNames().contains(formatName))
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.INVISIBLE);
    }

    static class ViewHolder {
        @BindView(R.id.titleTextView) TextView title;
        @BindView(R.id.authorTextView) TextView author;
        @BindView(R.id.epubImageView) ImageView epubImageView;
        @BindView(R.id.mobiImageView) ImageView mobiImageView;
        @BindView(R.id.pdfImageView) ImageView pdfImageView;
        @BindView(R.id.mp3ImageView) ImageView mp3ImageView;
        @BindView(R.id.thumbnailImageView) ImageView thumbnailImageView;

        ThumbnailVisitor thumbnailVisitor;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            thumbnailVisitor = new AndroidThumbnailVisitor(thumbnailImageView);
        }
    }
}
