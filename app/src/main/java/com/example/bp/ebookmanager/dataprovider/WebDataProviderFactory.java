package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.WebBookDetails;

/**
 * Ebook Manager
 * Created by bp on 13.06.16.
 */
public interface WebDataProviderFactory {
    BookDataProvider createBookDataProvider();
    WebBookDetails createBookDetails(WebActionContext context);
}
