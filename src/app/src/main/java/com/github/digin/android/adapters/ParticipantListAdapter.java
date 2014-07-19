package com.github.digin.android.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.digin.android.R;
import com.github.digin.android.bitmap.BitmapCacheHost;
import com.github.digin.android.bitmap.CachedAsyncBitmapLoader;
import com.github.digin.android.models.Participant;

import java.util.List;

/**
 * Created by david on 7/16/14.
 */
public class ParticipantListAdapter extends ArrayAdapter<Participant> {

    public static int PROFILE_PICTURE_SMALL_SIZE;
    private final BitmapCacheHost host;

    LayoutInflater inflater;

    Context context;
    public ParticipantListAdapter(Activity context, int resource, int textViewResourceId, List<Participant> objects, BitmapCacheHost host) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.host = host;
    }

    private LayoutInflater getLayoutInflater() {
        if(inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // reuse views
        if (rowView == null || (rowView.getId() == ((position%2 == 1) ? R.id.listItemRight : R.id.listItemLeft))) {
            rowView = getLayoutInflater().inflate((position % 2 == 1) ? R.layout.list_item_l : R.layout.list_item_r, parent, false);

            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.textView);
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.imageView);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.image.setImageBitmap(null);

        holder.text.setText(getItem(position).toString());

        CachedAsyncBitmapLoader.loadBitmapAsCachedAsyncTask(getItem(position).getThumbnail(), holder.image, host, PROFILE_PICTURE_SMALL_SIZE);

        return rowView;
    }

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }
}
