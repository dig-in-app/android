package com.github.digin.android.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.digin.android.NavDrawerItem;
import com.github.digin.android.R;

import java.util.List;

public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem> {

    public int getCurrentItem() {
        return currentItem;
    }

    private int currentItem = 0;

    public NavDrawerAdapter(Context context, int resource, List<NavDrawerItem> objects) {
        super(context, resource, R.id.text, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        TextView tv = (TextView) v.findViewById(R.id.text);

        if(position == currentItem)
            tv.setTypeface(Typeface.DEFAULT_BOLD);
        else
            tv.setTypeface(Typeface.DEFAULT);


        tv.setText(getItem(position).toString());

        v.setTag(getItem(position));

        return v;
    }

    public void setCurrentItem(int position) {
        currentItem = position;
        notifyDataSetChanged();
    }
}
