package com.example.bp.ebookmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bp.ebookmanager.android.config.AndroidConfiguration;
import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.model.Book;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private SyncOptionsDialogMediator syncDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initializeConfigIfNeeded();
        initializeRealm();

        setSupportActionBar(toolbar);

        setBookListAsInitialFragment();

        syncDialog = new SyncOptionsDialogMediator(this);
    }

    private void initializeConfigIfNeeded() {
        if (ConfigManager.get() == null)
            ConfigManager.set(new AndroidConfiguration(this));
    }


    private void initializeRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("ebookmanager.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void setBookListAsInitialFragment() {
        BookListFragment fragment = new BookListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFragment, fragment);
        transaction.commit();
    }

    public void showBookDetails(Book book, boolean synced) {
        DetailsFragment fragment = new DetailsFragment();
        fragment.setBook(book, synced);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFragment, fragment);
        transaction.addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.sync_options_button) {
            syncDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public BookDataProvider getProviderForSync() {
        return syncDialog.getProvider();
    }
}
