package com.example.bp.ebookmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.dataprovider.BookDataProvider;
import com.example.bp.ebookmanager.dataprovider.MultipleDataProvider;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class SyncOptionsListAdapter extends BaseAdapter {

    private Context context;
    private List<BookDataProvider> providers;
    private boolean[] checks;

    public SyncOptionsListAdapter(Context context) {
        this.context = context;
        providers = ConfigManager.get().getDataProviders();
        checks = new boolean[providers.size()];
        Arrays.fill(checks, true);
    }

    @Override
    public int getCount() {
        return providers.size();
    }

    @Override
    public Object getItem(int position) {
        return providers.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflateRow();
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.checkBox.setText(providers.get(position).getName());
        holder.checkBox.setChecked(checks[position]);
        holder.position = position;
        return convertView;
    }

    private View inflateRow() {
        View convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.sync_options_row, null);
        ViewHolder viewHolder = new ViewHolder(convertView, checks);
        convertView.setTag(viewHolder);
        return convertView;
    }

    public BookDataProvider getProvider() {
        MultipleDataProvider result = new MultipleDataProvider();
        for (int i = 0; i < providers.size(); i++) {
            if (checks[i])
                result.addDataProvider(providers.get(i));
        }
        return result;
    }

    static class ViewHolder {
        @BindView(R.id.sync_options_checkBox) CheckBox checkBox;
        boolean checks[];
        int position;

        public ViewHolder(View view, boolean[] checks) {
            this.checks = checks;
            ButterKnife.bind(this, view);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ViewHolder.this.checks[position] = isChecked;
                }
            });
        }
    }
}
