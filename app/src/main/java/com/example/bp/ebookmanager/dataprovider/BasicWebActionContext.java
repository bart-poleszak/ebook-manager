package com.example.bp.ebookmanager.dataprovider;

/**
 * Ebook Manager
 * Created by bp on 13.06.16.
 */
public class BasicWebActionContext implements WebActionContext {

    private final String url;
    private String source;
    private boolean actionCompleted = false;

    public BasicWebActionContext(String url) {
        this.url = url;
    }

    @Override
    public String getTargetSiteURL() {
        return url;
    }

    @Override
    public void processRecievedData(String url, String source) {
        this.source = source;
        actionCompleted = true;
    }

    @Override
    public boolean isActionCompleted() {
        return actionCompleted;
    }

    @Override
    public boolean isUserActionNeeded() {
        return false;
    }

    @Override
    public String getResult() {
        return source;
    }
}
