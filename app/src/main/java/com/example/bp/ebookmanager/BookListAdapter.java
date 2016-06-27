package com.example.bp.ebookmanager;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
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
import com.example.bp.ebookmanager.model.formats.FormatDetails;
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
    private ArrayList<Boolean> synced = new ArrayList<>();
    private HashMap<String, Integer> idIndexMap = new HashMap<>();
    private Context context;
    private ThumbnailDownloadedObserver observer;
    private boolean markNewAsSynched = false;

    public BookListAdapter(Context context) {
        this.context = context;
    }

    public void setObserver(ThumbnailDownloadedObserver observer) {
        this.observer = observer;
    }

    public void setMarkNewAsSynched(boolean markNewAsSynched) {
        this.markNewAsSynched = markNewAsSynched;
    }

    public void addItem(Book item) {
        data.add(item);
        synced.add(markNewAsSynched);
        idIndexMap.put(item.getId(), data.size() - 1);
        notifyDataSetChanged();
    }

    public void updateItems(List<Book> items) {
        for (Book book : items) {
            Integer index = idIndexMap.get(book.getId());
            if (index != null) {
                data.set(index, book);
                synced.set(index, markNewAsSynched);
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
        holder.position = position;
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
        if (synced.get(holder.position))
            holder.syncedImageView.setVisibility(View.VISIBLE);
        else
            holder.syncedImageView.setVisibility(View.INVISIBLE);
        if (book.getThumbnail() != null)
            book.getThumbnail().fill(holder.thumbnailVisitor);

        ArrayList<String> formatNames = new ArrayList<>();
        for (FormatDetails formatDetails : book.getFormatDetailsList())
            formatNames.add(formatDetails.getFormatName());
        setVisibilityForFormat(holder.epubImageView, formatNames, EpubDetails.FORMAT_NAME);
        setVisibilityForFormat(holder.mobiImageView, formatNames, MobiDetails.FORMAT_NAME);
        setVisibilityForFormat(holder.pdfImageView, formatNames, PdfDetails.FORMAT_NAME);
        setVisibilityForFormat(holder.mp3ImageView, formatNames, Mp3Details.FORMAT_NAME);
    }

    private void setVisibilityForFormat(View view, List<String> formatNames, String formatName) {
        if (formatNames.contains(formatName))
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.INVISIBLE);
    }

    class ViewHolder {
        @BindView(R.id.titleTextView) TextView title;
        @BindView(R.id.authorTextView) TextView author;
        @BindView(R.id.epubImageView) ImageView epubImageView;
        @BindView(R.id.mobiImageView) ImageView mobiImageView;
        @BindView(R.id.pdfImageView) ImageView pdfImageView;
        @BindView(R.id.mp3ImageView) ImageView mp3ImageView;
        @BindView(R.id.thumbnailImageView) ImageView thumbnailImageView;
        @BindView(R.id.syncedImageView) ImageView syncedImageView;

        AndroidThumbnailVisitor thumbnailVisitor;
        int position;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            thumbnailVisitor = new AndroidThumbnailVisitor(thumbnailImageView);
            thumbnailVisitor.setObserver(new AndroidThumbnailVisitor.DownloadObserver() {
                @Override
                public void onDownloaded(BitmapDrawable drawable) {
                    observer.onThumbnailDownloaded(drawable, data.get(position));
                }
            });
        }
    }

    public interface ThumbnailDownloadedObserver {
        void onThumbnailDownloaded(BitmapDrawable thumbnail, Book book);
    }
}
