package com.github.digin.android.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.digin.android.ImageCacheEntry;
import com.github.digin.android.R;
import com.github.digin.android.bitmap.BitmapCacheHost;
import com.github.digin.android.bitmap.CachedAsyncBitmapLoader;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Participant;

import java.util.List;

/**
 * Created by david on 7/16/14.
 */
public class ChefListAdapter extends BaseAdapter {

    LayoutInflater inflater;

    Context context;

    List<Chef> mChefs;

    public ChefListAdapter(Context context, List<Chef> chefs) {
        mChefs = chefs;
        this.context = context;
    }

    private LayoutInflater getLayoutInflater() {
        if(inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater;
    }

    @Override
    public int getCount() {
        return mChefs.size();
    }

    @Override
    public Chef getItem(int position) {
        return mChefs.get(position);
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
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.imageView);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        Chef chef = getItem(position);
        holder.title.setText(chef.getName() + ": " + chef.getCook());
        holder.dish.setText(chef.getDish());
        holder.farm.setText(chef.getFarm());

        return rowView;
    }

    static class ViewHolder {
        public TextView title;
        public TextView dish;
        public TextView farm;

        public ImageView image;
    }
}
