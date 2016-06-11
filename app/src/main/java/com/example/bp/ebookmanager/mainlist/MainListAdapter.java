package com.example.bp.ebookmanager.mainlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bp.ebookmanager.R;
import com.example.bp.ebookmanager.model.formats.EpubSpecificData;
import com.example.bp.ebookmanager.model.formats.MobiSpecificData;
import com.example.bp.ebookmanager.model.formats.Mp3SpecificData;
import com.example.bp.ebookmanager.model.formats.PdfSpecificData;
import com.example.bp.ebookmanager.viewmodel.BookDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bp on 06.05.16.
 */
public class MainListAdapter extends BaseAdapter {
    private ArrayList<BookDetailsViewModel> data = new ArrayList<>();
    private Context context;

    public MainListAdapter(Context context) {
        this.context = context;
    }

    public void addItems(List<BookDetailsViewModel> items) {
        data.addAll(items);
        notifyDataSetChanged();
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
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.main_list_row, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        BookDetailsViewModel bookViewModel = (BookDetailsViewModel) getItem(position);
        holder.title.setText(bookViewModel.getTitle());
        holder.author.setText(bookViewModel.getAuthor());
        setVisibilityForFormat(holder.epubImageView, bookViewModel, EpubSpecificData.FORMAT_NAME);
        setVisibilityForFormat(holder.mobiImageView, bookViewModel, MobiSpecificData.FORMAT_NAME);
        setVisibilityForFormat(holder.pdfImageView, bookViewModel, PdfSpecificData.FORMAT_NAME);
        setVisibilityForFormat(holder.mp3ImageView, bookViewModel, Mp3SpecificData.FORMAT_NAME);
        return convertView;
    }

    private void setVisibilityForFormat(View view, BookDetailsViewModel viewModel, String formatName) {
        if (viewModel.getFormats().contains(formatName))
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

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
