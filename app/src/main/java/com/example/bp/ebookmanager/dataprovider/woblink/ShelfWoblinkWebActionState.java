package com.example.bp.ebookmanager.dataprovider.woblink;

import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.WebActionState;
import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public class ShelfWoblinkWebActionState implements WebActionState {
    private String shelfUrl;
    private boolean actionCompleted = false;
    private String source;

    public void setShelfUrl(String shelfUrl) {
        this.shelfUrl = shelfUrl;
    }

    @Override
    public String getTargetSiteURL() {
        return "https://woblink.com" + shelfUrl;
    }

    @Override
    public void processRecievedData(String url, String source) {
        Log.d("WoblinkShelf", url);
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
