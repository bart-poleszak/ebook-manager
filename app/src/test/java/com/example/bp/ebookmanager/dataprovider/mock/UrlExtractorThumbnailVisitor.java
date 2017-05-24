package com.example.bp.ebookmanager.dataprovider.mock;

import com.example.bp.ebookmanager.model.RawThumbnail;
import com.example.bp.ebookmanager.model.ThumbnailVisitor;
import com.example.bp.ebookmanager.model.URLThumbnail;

/**
 * Created by Bartek on 2017-05-22.
 */

public class UrlExtractorThumbnailVisitor implements ThumbnailVisitor {
    private String url;

    @Override
    public void getFilledBy(RawThumbnail thumbnail) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getFilledBy(URLThumbnail thumbnail) {
        url = thumbnail.getUrl();
    }

    public String getUrl() {
        return url;
    }
}
