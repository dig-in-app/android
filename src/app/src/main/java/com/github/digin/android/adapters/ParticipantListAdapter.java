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
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Brewery;
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
            viewHolder.badge_image = (ImageView) rowView.findViewById(R.id.badge_image);
            viewHolder.badge_text = (ViewGroup) rowView.findViewById(R.id.badge_text);
            viewHolder.type_text = (TextView) rowView.findViewById(R.id.typeText);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        T item = getItem(position);
        holder.title.setText(item.getName());

        holder.type_text.setText( getTypeString(item) );

        int resid = getResIdForParticipant(getItem(position));
        if(resid > 0) {
            holder.badge_text.setVisibility(View.INVISIBLE);
            holder.badge_image.setVisibility(View.VISIBLE);
            holder.badge_image.setImageResource(resid);
        } else if( resid == 0 ) {
            holder.badge_image.setImageBitmap(null);
            holder.badge_image.setVisibility(View.INVISIBLE);
            holder.badge_text.setVisibility(View.VISIBLE);

            holder.location.setText(getLocationForParticipant(item));
        }

        return rowView;
    }

    private String getTypeString(T item) {
        if( item instanceof Chef ) return "Chef";
        else if( item instanceof Winery ) return "Winery";
        else if( item instanceof Brewery) return "Brewery";
        else return "Other";
    }

    private int getResIdForParticipant(Participant participant) {
        if (participant.getTent().contains("Tent")) {
            char tentNum = participant.getTent().charAt(participant.getTent().length() - 1);
            if (tentNum == '1') {
                return R.drawable.tent_one;
            } else if (tentNum == '2') {
                return R.drawable.tent_two;
            } else if (tentNum == '3') {
                return R.drawable.tent_badge; //TODO: Get image for tent 3.
            } else if (tentNum == '4') {
                return R.drawable.tent_four;
            } else if (tentNum == '5') {
                return R.drawable.tent_five;
            } else if (tentNum == '6') {
                return R.drawable.tent_six;
            } else {
                return R.drawable.tent_badge; //TODO: This image is too large.
            }
        } else if (participant.getTent().equals("Food Trucks")) {
            return R.drawable.truck_badge;
        } else if (participant.getTent().contains("Table")) {
            return 0; //TODO: This image is too large.
        } else return -1;

    }

    private String getLocationForParticipant(T item) {
        return "" + item.getTent().charAt( item.getTent().length()-1 );
    }

    static class ViewHolder {
        public TextView title;
        public TextView location;
        public ImageView badge_image;
        public ViewGroup badge_text;
        public TextView type_text;
    }
}
