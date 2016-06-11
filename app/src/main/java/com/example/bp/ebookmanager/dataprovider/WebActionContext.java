package com.example.bp.ebookmanager.dataprovider;

/**
 * Created by bp on 11.06.16.
 */
public interface WebActionContext {
    String getTargetSiteURL();

    void processRecievedData(String url, String source);

    boolean isActionCompleted();

    boolean isUserActionNeeded();
}
