package com.example.bp.ebookmanager;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.bp.ebookmanager.dataprovider.BookDataProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class SyncOptionsDialogMediator {

    private final Context context;
    @BindView(R.id.sync_options_listView) ListView listView;
    @BindView(R.id.sync_options_done) Button button;

    private Dialog dialog;
    private SyncOptionsListAdapter adapter;

    public SyncOptionsDialogMediator(Context context) {
        this.context = context;
        createDialog();
        fillListView();
    }

    private void fillListView() {
        adapter = new SyncOptionsListAdapter(context);
        listView.setAdapter(adapter);
    }

    private void createDialog() {
        dialog = new Dialog(context);
        dialog.setTitle(R.string.select_to_sync);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sync_options_dialog, null);
        dialog.setContentView(view);
        ButterKnife.bind(this, view);
    }

    public void show() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public BookDataProvider getProvider() {
        return adapter.getProvider();
    }
}
