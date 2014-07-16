package com.github.digin.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.digin.android.R;

import java.util.List;

/**
 * Created by david on 7/16/14.
 */
public class ParticipantListAdapter extends ArrayAdapter {

    Context context;
    public ParticipantListAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate((position % 2 == 1) ? R.layout.list_item_l : R.layout.list_item_r, parent, false);

        TextView tv = (TextView) v.findViewById(R.id.textView);

        tv.setText(getItem(position).toString());

        v.setTag(getItem(position));

        return v;
    }
}
