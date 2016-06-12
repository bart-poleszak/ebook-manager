package com.example.bp.ebookmanager.dataprovider.woblink;

import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.BookDataProviderImpl;
import com.example.bp.ebookmanager.dataprovider.WebActionResolver;
import com.example.bp.ebookmanager.dataprovider.WebDataProviderStrategy;
import com.example.bp.ebookmanager.dataprovider.android.HeadlessWebClient;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class WoblinkDataProviderFactory {
    public static BookDataProvider create() {
        WebDataProviderStrategy strategy = new WebDataProviderStrategy();
        WebActionResolver resolver = new WebActionResolver(new HeadlessWebClient());
        strategy.setResolver(resolver);
        strategy.setParser(new WoblinkBookDataParser());
        strategy.setWebActionContext(new WoblinkWebActionContext());
        return new BookDataProviderImpl(strategy);
    }
}
