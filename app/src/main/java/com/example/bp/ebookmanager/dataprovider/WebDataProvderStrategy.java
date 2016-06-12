package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Created by bp on 08.05.16.
 */
public class WebDataProvderStrategy implements DataProviderStrategy {
    private WebClient webClient;
    private WebActionContext webActionContext;
    private Callbacks callbacks;

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public void setWebActionContext(WebActionContext webActionContext) {
        this.webActionContext = webActionContext;
    }

    @Override
    public void gainAccess(final Callbacks callbacks) {
        this.callbacks = callbacks;
        webClient.setCallbacks(new WebClient.Callbacks() {
            @Override
            public void onPageFinished(String url, String source) {
                webActionContext.processRecievedData(url, source);
                if(webActionContext.isActionCompleted()) {
                    callbacks.onAccessGained();
                    webClient.finishWork();
                }
                else if (webActionContext.isUserActionNeeded() && webClient.isHeadless())
                    callbacks.onUserActionRequired();
                else if (!webActionContext.isUserActionNeeded())
                    webClient.loadUrl(webActionContext.getTargetSiteURL());
            }
        });
        webClient.loadUrl(webActionContext.getTargetSiteURL());
    }

    @Override
    public void retryToGainAccess() {
        gainAccess(callbacks);
    }

    @Override
    public List<Book> getBooks() {
        return webActionContext.getBooks();
    }

    @Override
    public void enableUserAction(UserActionEnabler visitor) {
        visitor.enableWebUserAction(this);
    }
}
