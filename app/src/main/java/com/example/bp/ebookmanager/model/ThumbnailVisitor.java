package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 25.06.16.
 */
public interface ThumbnailVisitor {
    void getFilledBy(RawThumbnail thumbnail);
    void getFilledBy(URLThumbnail thumbnail);
}
