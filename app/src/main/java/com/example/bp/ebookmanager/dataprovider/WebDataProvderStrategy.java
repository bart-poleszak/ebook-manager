package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.Publisher;
import com.example.bp.ebookmanager.model.formats.EpubSpecificData;
import com.example.bp.ebookmanager.model.formats.MobiSpecificData;
import com.example.bp.ebookmanager.model.formats.Mp3SpecificData;
import com.example.bp.ebookmanager.model.formats.PdfSpecificData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bp on 08.05.16.
 */
public class WebDataProvderStrategy implements DataProviderStrategy {
    private WebClient webClient;
    private WebActionContext webActionContext;

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public void setWebActionContext(WebActionContext webActionContext) {
        this.webActionContext = webActionContext;
    }

    @Override
    public void gainAccess(final Callbacks callback) {

        webClient.setCallbacks(new WebClient.Callbacks() {
            @Override
            public void onPageFinished(String url, String source) {
                webActionContext.processRecievedData(url, source);
                if(webActionContext.isActionCompleted()) {
                    callback.onAccessGained();
                    webClient.finishWork();
                }
                else if (webActionContext.isUserActionNeeded() && webClient.isHeadless())
                    callback.onUserActionNeeded();
            }
        });
        webClient.loadUrl(webActionContext.getTargetSiteURL());
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
