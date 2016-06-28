package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.BookDataProviderImpl;
import com.example.bp.ebookmanager.dataprovider.WebActionContext;
import com.example.bp.ebookmanager.dataprovider.WebActionResolver;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.dataprovider.WebDataProviderFactory;
import com.example.bp.ebookmanager.dataprovider.WebDataProviderStrategy;
import com.example.bp.ebookmanager.model.WebBookDetails;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public abstract class EmpikWebDataProviderFactory implements WebDataProviderFactory {

    protected abstract String getShelfName();

    @Override
    public BookDataProvider createBookDataProvider() {
        WebClientFactory webClientFactory = ConfigManager.get().getWebClientFactory();
        WebDataProviderStrategy strategy = new WebDataProviderStrategy();
        WebActionResolver resolver = new WebActionResolver(webClientFactory.getHeadlessClient());
        strategy.setResolver(resolver);
        strategy.setParser(new EmpikBookDataParser());
        strategy.setWebActionContext(new EmpikWebActionContext(getShelfName()));
        BookDataProviderImpl bookDataProvider = new BookDataProviderImpl(strategy);
        bookDataProvider.setName("Empik - " + getShelfName());
        return bookDataProvider;
    }

    @Override
    public WebBookDetails createBookDetails(WebActionContext context) {
        WebClientFactory webClientFactory = ConfigManager.get().getWebClientFactory();
        WebBookDetails details = new WebBookDetails();
        details.setParser(new EmpikDetailsParser());
        details.setContext(context);
        details.setResolver(new WebActionResolver(webClientFactory.getHeadlessClient()));
        return details;
    }
}
