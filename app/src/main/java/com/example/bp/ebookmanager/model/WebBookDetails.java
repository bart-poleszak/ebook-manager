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
    private BookDetails internalData = NullBookDetails.instance();
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
        return internalData.getTranslator();
    }

    private void requestDataIfNeeded() {
        if (internalData == NullBookDetails.instance())
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
        return internalData.getPublisher();
    }

    @Override
    public void setObserver(DetailsObserver observer) {
        this.observer = observer;
    }

    @Override
    public List<FormatDetails> getFormats() {
        if (internalData == NullBookDetails.instance())
            return formatDetails;
        return internalData.getFormats();
    }

    @Override
    public FormatDetails getFormat(String formatName) {
        for (FormatDetails format : getFormats()) {
            if (format.getFormatName().equals(formatName))
                return format;
        }
        return null;
    }

    public void addFormat(FormatDetails format) {
        formatDetails.add(format);
    }

    private class ResolverCallbacks implements WebActionResolver.Callbacks {
        @Override
        public void onActionCompleted(WebActionContext context) {
            BookDetails newInternalData = parser.parse(context.getResult());
            mergeFormatDetails(newInternalData);
            internalData = newInternalData;
            if (observer != null)
                observer.onDetailsChanged();
        }

        @Override
        public void onUserActionRequired() {
            resolver.enableUserAction(ConfigManager.get().getUserActionEnabler());
            retryToResolve();
        }

        @Override
        public void onActionFailed() {
            if(observer != null)
                observer.onFailure();
        }
    }

    private void mergeFormatDetails(BookDetails newInternalData) {
        for (FormatDetails oldFormatDetails : formatDetails) {
            FormatDetails newFormatDetails = newInternalData.getFormat(oldFormatDetails.getFormatName());
            if (newFormatDetails != null)
                newFormatDetails.setDownloadUrl(oldFormatDetails.getDownloadUrl());
            else
                newInternalData.getFormats().add(oldFormatDetails);
        }
    }
}
