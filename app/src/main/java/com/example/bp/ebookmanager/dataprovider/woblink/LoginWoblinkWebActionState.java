package com.example.bp.ebookmanager.dataprovider.woblink;

import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.WebActionState;
import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public class LoginWoblinkWebActionState implements WebActionState {
    @Override
    public String getTargetSiteURL() {
        return "https://woblink.com/login";
    }

    @Override
    public void processRecievedData(String url, String source) {
        Log.d("WoblinkLogin", url);
    }

    @Override
    public boolean isActionCompleted() {
        return false;
    }

    @Override
    public boolean isUserActionNeeded() {
        return true;
    }

    @Override
    public String getResult() {
        throw new UnsupportedOperationException();
    }
}
