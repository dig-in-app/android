package com.github.digin.android.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.digin.android.R;
import com.github.digin.android.fragments.LineupListFragment;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Participant;
import com.github.digin.android.models.Winery;

import java.util.List;

/**
 * Created by david on 8/16/14.
 */
public class ParticipantListAdapter<T extends Participant> extends ArrayAdapter<T> {

    LayoutInflater inflater;

    public ParticipantListAdapter(Activity activity, List<T> items) {
        super(activity, 0, items);
    }

    private LayoutInflater getLayoutInflater() {
        if (inflater == null)
            inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void sort(LineupListFragment.Sorting sorting) {
        super.sort(sorting.getComparator());
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            rowView = getLayoutInflater().inflate(R.layout.participant_item, parent, false);

            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) rowView.findViewById(R.id.titleView);
            viewHolder.location = (TextView) rowView.findViewById(R.id.tableLetter);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        T item = getItem(position);
        holder.title.setText(item.getName());

        holder.location.setText(getLocationForParticipant(item));

        return rowView;
    }

    private String getLocationForParticipant(T item) {
        return "" + item.getTent().charAt( item.getTent().length()-1 );
    }

    static class ViewHolder {
        public TextView title;
        public TextView location;
    }
}
