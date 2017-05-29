package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 08.05.16.
 */
public class WebDataProviderStrategy implements DataProviderStrategy {
    private WebActionResolver resolver;
    private WebActionContext webActionContext;
    private BookDataParser parser;
    private WebActionResolver.Callbacks resolverCallbacks;

    public void setResolver(WebActionResolver resolver) {
        this.resolver = resolver;
    }

    public void setParser(BookDataParser parser) {
        this.parser = parser;
    }

    public void setWebActionContext(WebActionContext webActionContext) {
        this.webActionContext = webActionContext;
    }

    @Override
    public void gainAccess(final Callbacks callbacks) {
        resolverCallbacks = new WebActionResolver.Callbacks() {
            @Override
            public void onActionCompleted(WebActionContext context) {
                callbacks.onAccessGained();
            }

            @Override
            public void onUserActionRequired() {
                UserActionEnabler userActionEnabler = ConfigManager.get().getUserActionEnabler();
                resolver.enableUserAction(userActionEnabler);
                retryToGainAccess();
            }

            @Override
            public void onActionFailed() {
                callbacks.onFailure();
            }
        };
        resolver.resolve(webActionContext, resolverCallbacks);
    }

    private void retryToGainAccess() {
        resolver.resolve(webActionContext, resolverCallbacks);
    }

    @Override
    public List<Book> getBooks() {
        parser.parse(webActionContext.getResult());
        return parser.getBooks();
    }
}
