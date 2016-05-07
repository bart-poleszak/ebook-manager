package com.example.bp.ebookmanager.mainlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bp.ebookmanager.R;

/**
 * Created by bp on 06.05.16.
 */
public class MainListAdapter extends BaseAdapter {
    private String[] data = {"AAA", "BBB", "CCC"};
    private Context context;

    public MainListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.main_list_row, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.mainListTextView);
        textView.setText((String) getItem(position));
        return convertView;
    }
}
