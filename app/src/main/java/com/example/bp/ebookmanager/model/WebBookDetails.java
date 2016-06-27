package com.example.bp.ebookmanager.model;

import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.dataprovider.WebActionContext;
import com.example.bp.ebookmanager.dataprovider.WebActionResolver;
import com.example.bp.ebookmanager.model.formats.FormatDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 13.06.16.
 */
public class WebBookDetails implements BookDetails {
    private BookDetails downloadedData = NullBookDetails.instance();
    private DetailsObserver observer;
    private WebActionResolver resolver;
    private WebActionContext context;
    private BookDetailsParser parser;
    private ResolverCallbacks resolverCallbacks = new ResolverCallbacks();
    private ArrayList<FormatDetails> formatDetails = new ArrayList<>();

    public void setResolver(WebActionResolver resolver) {
        this.resolver = resolver;
    }

    public void setParser(BookDetailsParser parser) {
        this.parser = parser;
    }

    public void setContext(WebActionContext context) {
        this.context = context;
    }

    @Override
    public Person getTranslator() {
        requestDataIfNeeded();
        return downloadedData.getTranslator();
    }

    private void requestDataIfNeeded() {
        if (downloadedData == NullBookDetails.instance())
            requestData();
    }

    private void requestData() {
        if (!resolver.isWorking()) {
            resolver.resolve(context, resolverCallbacks);
        }
    }

    private void retryToResolve() {
        resolver.resolve(context, resolverCallbacks);
    }

    @Override
    public Publisher getPublisher() {
        requestDataIfNeeded();
        return downloadedData.getPublisher();
    }

    @Override
    public void setObserver(DetailsObserver observer) {
        this.observer = observer;
    }

    @Override
    public List<FormatDetails> getFormats() {
        if (downloadedData == NullBookDetails.instance())
            return formatDetails;
        return downloadedData.getFormats();
    }

    public void addFormatWithoutDataRequest(FormatDetails format) {
        formatDetails.add(format);
    }

    private class ResolverCallbacks implements WebActionResolver.Callbacks {
        @Override
        public void onActionCompleted(WebActionContext context) {
            downloadedData = parser.parse(context.getResult());
            if (observer != null)
                observer.onDetailsChanged();
        }

        @Override
        public void onUserActionRequired() {
            resolver.enableUserAction(ConfigManager.get().getUserActionEnabler());
            retryToResolve();
        }
    }
}
