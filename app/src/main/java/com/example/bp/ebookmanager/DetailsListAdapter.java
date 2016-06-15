package com.example.bp.ebookmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Ebook Manager
 * Created by bp on 15.06.16.
 */
public class DetailsListAdapter extends BaseAdapter {

    private ArrayList<Data> data = new ArrayList<>();
    private Context context;

    public DetailsListAdapter(Context context) {
        this.context = context;
        addRow("Daaaa", "dadada");
    }

    public void addRow(String key, String value) {
        data.add(new Data(key, value));
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflateNewRow();
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Data item = (Data) getItem(position);
        holder.key.setText(item.key);
        holder.value.setText(item.value);
        return convertView;
    }

    private View inflateNewRow() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.details_list_row, null);
        convertView.setTag(new ViewHolder(convertView));
        return convertView;
    }

    private static class Data {
        public String key;
        public String value;

        public Data(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    static class ViewHolder {
        @BindView(R.id.detailKey) TextView key;
        @BindView(R.id.detailValue) TextView value;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
