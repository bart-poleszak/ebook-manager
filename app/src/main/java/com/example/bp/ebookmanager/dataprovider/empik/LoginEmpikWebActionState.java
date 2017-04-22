package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.WebActionState;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class LoginEmpikWebActionState implements WebActionState {
    private boolean userActionNeeded = true;
    private static final String LOGIN_PATH = "https://m.empik.com/logowanie";

    @Override
    public String getTargetSiteURL() {
        return "https://m.empik.com/twoje-konto/biblioteka-lista?activeTab=audiobook";
    }

    @Override
    public void processRecievedData(String url, String source) {
        userActionNeeded = url.startsWith(LOGIN_PATH);
    }

    @Override
    public boolean isActionCompleted() {
        return false;
    }

    @Override
    public boolean isUserActionNeeded() {
        return userActionNeeded;
    }

    @Override
    public String getResult() {
        throw new UnsupportedOperationException();
    }
}
