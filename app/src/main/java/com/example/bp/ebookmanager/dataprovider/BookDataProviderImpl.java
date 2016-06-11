package com.example.bp.ebookmanager.dataprovider;

import android.os.AsyncTask;

import com.example.bp.ebookmanager.model.Book;

import java.util.List;

/**
 * Created by bp on 08.05.16.
 */
public class BookDataProviderImpl implements BookDataProvider {
    private DataProviderStrategy strategy;
    private GetBookDataTask task = new GetBookDataTask();
    private Callbacks callbacks;

    public BookDataProviderImpl(DataProviderStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void getDataAsync(Callbacks callbacks) {
        this.callbacks = callbacks;
        task.execute();
    }

    @Override
    public void cancel() {
        task.cancel(true);
    }

    private class GetBookDataTask extends AsyncTask<Void, ProgressStatus, List<Book>> {
        private String actionUrl;
        private String targetUrl;

        @Override
        protected List<Book> doInBackground(Void... params) {
            return strategy.getBooks();
        }

        @Override
        protected void onProgressUpdate(ProgressStatus... values) {
            super.onProgressUpdate(values);
            ProgressStatus status = values[0];
            switch (status) {
                case ACTION_REQUIRED:
                    callbacks.webActionRequired(actionUrl, targetUrl);
                    break;
            }
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);
            callbacks.onNewDataAcquired(books);
        }
    }

}
