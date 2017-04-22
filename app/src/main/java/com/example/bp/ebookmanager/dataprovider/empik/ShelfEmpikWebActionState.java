package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.WebActionState;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class ShelfEmpikWebActionState implements WebActionState {
    private boolean userActionNeeded = false;
    private boolean actionCompleted = false;
    private String source;
    private String shelfName;

    public static final String MOBILE_SITE_SHELF_URL_PREFIX = "https://m.empik.com/twoje-konto/biblioteka-lista?activeTab=";

    public ShelfEmpikWebActionState(String shelfName) {
        this.shelfName = shelfName;
    }

    @Override
    public String getTargetSiteURL() {
        return "https://www.empik.com/twoje-konto/biblioteka-lista?activeTab=" + shelfName;
    }

    @Override
    public void processRecievedData(String url, String source) {
        this.source = source;
        userActionNeeded = false;
        actionCompleted = true;
    }

    @Override
    public boolean isActionCompleted() {
        return actionCompleted;
    }

    @Override
    public boolean isUserActionNeeded() {
        return userActionNeeded;
    }

    @Override
    public String getResult() {
        return source;
    }
}
