package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 25.06.16.
 */
public class RawThumbnail implements Thumbnail {
    private byte[] data;

    public RawThumbnail(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public void fill(ThumbnailVisitor view) {
        view.getFilledBy(this);
    }
}
