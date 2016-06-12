package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 08.05.16.
 */
public class WebDataProvderStrategy implements DataProviderStrategy {
    private WebClient headlessWebClient;
    private WebClient webClient;
    private WebActionContext webActionContext;
    private Callbacks callbacks;

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
        if (webClient.isHeadless())
            headlessWebClient = webClient;
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
                    webClient.close();
                }
                else if (webActionContext.isUserActionNeeded() && webClient.isHeadless())
                    callbacks.onUserActionRequired();
                else if (!webActionContext.isUserActionNeeded()) {
                    if (!webClient.isHeadless())
                        switchToHeadlessIfPossible();
                    webClient.loadUrl(webActionContext.getTargetSiteURL());
                }
            }
        });
        webClient.loadUrl(webActionContext.getTargetSiteURL());
    }

    private void switchToHeadlessIfPossible() {
        if (headlessWebClient != null) {
            webClient.close();
            webClient = headlessWebClient;
        }
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
