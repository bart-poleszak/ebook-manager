package com.example.bp.ebookmanager.android.dataprovider;

import android.os.AsyncTask;

/**
 * Created by Bartek on 2017-05-28.
 */

public class AsyncTaskHeadlessWebClientActionsRunner implements HeadlessWebClient.HeadlessWebClientActionsRunner {

    private HeadlessWebClient.HeadlessWebClientAsyncActions actions;
    private WebClientAsyncTask asyncTask;

    @Override
    public void setActions(HeadlessWebClient.HeadlessWebClientAsyncActions actions) {
        this.actions = actions;
    }

    @Override
    public void run(UrlWrapper url) {
        asyncTask.execute(url);
    }

    private class WebClientAsyncTask extends AsyncTask<UrlWrapper, Void, String> {

        @Override
        protected String doInBackground(UrlWrapper... urlWrappers) {
            return actions.doInBackground(urlWrappers[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            actions.onPostExecute(result);
            super.onPostExecute(result);
        }
    }
}
