package com.example.bp.ebookmanager.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.bp.ebookmanager.model.RawThumbnail;
import com.example.bp.ebookmanager.model.ThumbnailVisitor;
import com.example.bp.ebookmanager.model.URLThumbnail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Ebook Manager
 * Created by bp on 25.06.16.
 */
public class AndroidThumbnailVisitor implements ThumbnailVisitor {
    private ImageView imageView;
    private DownloadObserver observer;

    public AndroidThumbnailVisitor(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setObserver(DownloadObserver observer) {
        this.observer = observer;
    }

    @Override
    public void getFilledBy(RawThumbnail thumbnail) {
        byte[] data = thumbnail.getData();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void getFilledBy(URLThumbnail thumbnail) {
        Picasso.with(imageView.getContext())
                .load(thumbnail.getUrl())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (observer != null) {
                            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                            observer.onDownloaded(drawable);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    public interface DownloadObserver {
        void onDownloaded(BitmapDrawable drawable);
    }
}
