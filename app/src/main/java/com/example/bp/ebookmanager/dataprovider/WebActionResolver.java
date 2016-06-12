package com.example.bp.ebookmanager.dataprovider;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class WebActionResolver {

    private WebClient headlessWebClient;
    private WebClient webClient;

    public WebActionResolver(WebClient webClient) {
        this.webClient = webClient;
        if (webClient.isHeadless())
            headlessWebClient = webClient;
    }

    public void resolve(final WebActionContext action, final Callbacks callbacks) {
        webClient.setCallbacks(new WebClient.Callbacks() {
            @Override
            public void onPageFinished(String url, String source) {
                action.processRecievedData(url, source);
                if(action.isActionCompleted()) {
                    callbacks.onActionCompleted();
                    webClient.close();
                }
                else if (action.isUserActionNeeded() && webClient.isHeadless())
                    callbacks.onUserActionRequired();
                else if (!action.isUserActionNeeded()) {
                    if (!webClient.isHeadless())
                        switchToHeadlessIfPossible();
                    webClient.loadUrl(action.getTargetSiteURL());
                }
            }
        });
        webClient.loadUrl(action.getTargetSiteURL());
    }

    private void switchToHeadlessIfPossible() {
        if (headlessWebClient != null) {
            webClient.close();
            webClient = headlessWebClient;
        }
    }

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public interface Callbacks {
        void onActionCompleted();
        void onUserActionRequired();
    }
}
