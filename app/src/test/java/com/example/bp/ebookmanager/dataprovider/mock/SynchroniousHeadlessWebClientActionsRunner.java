package com.example.bp.ebookmanager.dataprovider.mock;

import com.example.bp.ebookmanager.android.dataprovider.HeadlessWebClient;
import com.example.bp.ebookmanager.android.dataprovider.UrlWrapper;

/**
 * Created by Bartek on 2017-05-28.
 */

public class SynchroniousHeadlessWebClientActionsRunner implements HeadlessWebClient.HeadlessWebClientActionsRunner {

    private HeadlessWebClient.HeadlessWebClientAsyncActions actions;

    @Override
    public void setActions(HeadlessWebClient.HeadlessWebClientAsyncActions actions) {
        this.actions = actions;
    }

    @Override
    public void run(UrlWrapper url) {
        String result = actions.doInBackground(url);
        actions.onPostExecute(result);
    }
}
