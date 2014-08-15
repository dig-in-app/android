package com.github.digin.android.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.digin.android.R;
import com.github.digin.android.fragments.LineupListFragment;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;

import java.util.List;

/**
 * Created by david on 7/16/14.
 */
public class ChefListAdapter extends ArrayAdapter<Chef> {

    LayoutInflater inflater;

    public ChefListAdapter(Context context, List<Chef> chefs) {
        super(context, 0, chefs);

    }

    private LayoutInflater getLayoutInflater() {
        if(inflater == null)
            inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            rowView = getLayoutInflater().inflate(R.layout.list_item_l, parent, false);

            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) rowView.findViewById(R.id.titleView);
            viewHolder.dish = (TextView) rowView.findViewById(R.id.dishText);
            viewHolder.farm = (TextView) rowView.findViewById(R.id.farmText);
            viewHolder.chef = (TextView) rowView.findViewById(R.id.chefView);
            viewHolder.tentBadge = (ImageView) rowView
                    .findViewById(R.id.tentBadge);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        Chef chef = getItem(position);
        holder.title.setText(chef.getName());
        holder.chef.setText(Html.fromHtml("<b>Chef:</b> " + chef.getCook()));
        holder.dish.setText(Html.fromHtml("<b>Dish:</b> " + chef.getDish()));
        holder.farm.setText(Html.fromHtml("<b>Farm:</b> " + chef.getFarm()));

        holder.tentBadge.setImageResource(getBadgeForChef(chef));

        return rowView;
    }

    private int getBadgeForChef(Chef chef) {
        if(chef.getTent().contains("Tent")) {
            char tentNum = chef.getTent().charAt( chef.getTent().length() - 1);
            if(tentNum == '1') {
                return R.drawable.tent_one;
            } else if(tentNum == '2') {
                return R.drawable.tent_two;
            } else if(tentNum == '3') {
                return R.drawable.tent_badge; //TODO: Get image for tent 3.
            } else if(tentNum == '4') {
                return R.drawable.tent_four;
            } else if(tentNum == '5') {
                return R.drawable.tent_five;
            } else if(tentNum == '6') {
                return R.drawable.tent_six;
            } else {
                return R.drawable.tent_badge; //TODO: This image is too large.
            }
        } else if(chef.getTent().equals("Food Trucks")) {
            return R.drawable.truck_badge;
        } else {
            Logger.log(getClass(), "Chef has no tent: " + chef.getName() + " | " + chef.getTent());
            return R.drawable.tent_badge; //TODO: This image is too large.
        }

    }

    public void sort(LineupListFragment.Sorting sorting) {
        super.sort(sorting.getComparator());
        notifyDataSetChanged();
    }

    static class ViewHolder {
        public TextView title;
        public TextView dish;
        public TextView farm;
        public TextView chef;

        public ImageView tentBadge;
    }
}
