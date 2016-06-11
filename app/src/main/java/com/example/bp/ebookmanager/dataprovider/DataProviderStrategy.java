package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Created by bp on 08.05.16.
 */
public interface DataProviderStrategy {
    boolean testAccess();
    AccessConditions getAccessConditions();
    List<Book> getBooks();

    class AccessConditions {
        public String accessUrl;
        public String targetUrl;
    }
}
