package com.example.bp.ebookmanager.dataprovider;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class WebActionResolver {

    private WebClient headlessWebClient;
    private WebClient webClient;
    private final Object isWorkingLock = new Object();
    private boolean working = false;

    public WebActionResolver(WebClient webClient) {
        this.webClient = webClient;
        if (webClient.isHeadless())
            headlessWebClient = webClient;
    }

    public void resolve(final WebActionContext action, final Callbacks callbacks) {
        setWorking(true);
        webClient.setCallbacks(new WebClient.Callbacks() {
            @Override
            public void onPageFinished(String url, String source) {
                action.processRecievedData(url, source);
                if(action.isActionCompleted()) {
                    callbacks.onActionCompleted(action);
                    webClient.close();
                    setWorking(false);
                }
                else if (action.isUserActionNeeded() && webClient.isHeadless()) {
                    callbacks.onUserActionRequired();
                    setWorking(false);
                }
                else if (!action.isUserActionNeeded()) {
                    if (!webClient.isHeadless())
                        switchToHeadlessIfPossible();
                    webClient.loadUrl(action.getTargetSiteURL());
                }
            }
        });
        webClient.loadUrl(action.getTargetSiteURL());
    }

    public void setWorking(boolean working) {
        synchronized (isWorkingLock) {
            this.working = working;
        }
    }

    public boolean isWorking() {
        synchronized (isWorkingLock) {
            return working;
        }
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

    public void enableUserAction(UserActionEnabler enabler) {
        enabler.enableWebUserAction(this);
    }

    public interface Callbacks {
        void onActionCompleted(WebActionContext context);
        void onUserActionRequired();
    }
}
