package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.WebActionContext;
import com.example.bp.ebookmanager.dataprovider.WebActionState;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class EmpikWebActionContext implements WebActionContext {
    public static final String AUDIOBOOK_SHELF_NAME = "audiobook";
    public static final String EBOOK_SHELF_NAME = "ebook";
    private ShelfEmpikWebActionState shelfState;
    private LoginEmpikWebActionState loginState;
    private WebActionState currentState;

    public EmpikWebActionContext(String shelfName) {
        shelfState = new ShelfEmpikWebActionState(shelfName);
        loginState = new LoginEmpikWebActionState();
        currentState = loginState;
    }

    @Override
    public String getTargetSiteURL() {
        return currentState.getTargetSiteURL();
    }

    @Override
    public void processRecievedData(String url, String source) {
        currentState.processRecievedData(url, source);

        if (url.startsWith(ShelfEmpikWebActionState.MOBILE_SITE_SHELF_URL_PREFIX))
            currentState = shelfState;
    }

    @Override
    public boolean isActionCompleted() {
        return currentState.isActionCompleted();
    }

    @Override
    public boolean isUserActionNeeded() {
        return currentState.isUserActionNeeded();
    }

    @Override
    public String getResult() {
        return currentState.getResult();
    }
}
