package com.example.bp.ebookmanager.dataprovider.woblink;

import com.example.bp.ebookmanager.dataprovider.WebActionContext;
import com.example.bp.ebookmanager.dataprovider.WebActionState;
import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public class WoblinkWebActionContext implements WebActionContext{
    private FrontPageWoblinkWebActionState frontPageState = new FrontPageWoblinkWebActionState();
    private LoginWoblinkWebActionState loginState = new LoginWoblinkWebActionState();
    private ShelfWoblinkWebActionState shelfState = new ShelfWoblinkWebActionState();
    private WebActionState currentState = frontPageState;

    @Override
    public String getTargetSiteURL() {
        return currentState.getTargetSiteURL();
    }

    @Override
    public void processRecievedData(String url, String source) {
        if (url.equals(frontPageState.getTargetSiteURL()))
            currentState = frontPageState;
        currentState.processRecievedData(url, source);
        if (currentState == frontPageState) {
            if (frontPageState.isLogged()) {
                shelfState.setShelfUrl(frontPageState.getShelfUrl());
                currentState = shelfState;
            }
            else
                currentState = loginState;
        }
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
