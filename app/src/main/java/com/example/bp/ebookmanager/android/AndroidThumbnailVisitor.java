package com.example.bp.ebookmanager.android;

import android.widget.ImageView;

import com.example.bp.ebookmanager.model.RawThumbnail;
import com.example.bp.ebookmanager.model.ThumbnailVisitor;
import com.example.bp.ebookmanager.model.URLThumbnail;
import com.squareup.picasso.Picasso;

/**
 * Ebook Manager
 * Created by bp on 25.06.16.
 */
public class AndroidThumbnailVisitor implements ThumbnailVisitor {
    private ImageView imageView;

    public AndroidThumbnailVisitor(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void getFilledBy(RawThumbnail thumbnail) {

    }

    @Override
    public void getFilledBy(URLThumbnail thumbnail) {
        Picasso.with(imageView.getContext())
                .load(thumbnail.getUrl())
                .into(imageView);
    }
}
