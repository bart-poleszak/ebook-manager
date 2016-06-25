package com.example.bp.ebookmanager.model;

/**
 * Ebook Manager
 * Created by bp on 25.06.16.
 */
public class URLThumbnail implements Thumbnail{
    private String url;

    public URLThumbnail(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void fill(ThumbnailVisitor view) {
        view.getFilledBy(this);
    }
}
