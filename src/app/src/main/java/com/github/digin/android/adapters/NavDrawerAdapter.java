package com.github.digin.android.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.digin.android.NavDrawerItem;
import com.github.digin.android.R;

import java.util.List;

public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem> {

    public NavDrawerAdapter(Context context, int resource, List<NavDrawerItem> objects) {
        super(context, resource, R.id.text, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        TextView tv = (TextView) v.findViewById(R.id.text);

        tv.setText(getItem(position).toString());

        v.setTag(getItem(position));

        return v;
    }
}
