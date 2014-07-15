package com.github.digin.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.digin.android.NavDrawerItem;
import com.github.digin.android.R;
import com.github.digin.android.adapters.NavDrawerAdapter;
import com.github.digin.android.constants.ParseKeys;
import com.github.digin.android.fragments.BoundedMapFragment;


public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        getFragmentManager().beginTransaction().add(R.id.content_frame, new BoundedMapFragment(), BoundedMapFragment.class.getName()).commit();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new NavDrawerAdapter(this, R.layout.drawer_item, NavDrawerItem.getItems()));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getFragmentManager().beginTransaction().replace(R.id.content_frame,
                        ((NavDrawerItem)view.getTag()).getFragment(),
                        ((NavDrawerItem)view.getTag()).toString()).commit();
                mDrawerLayout.closeDrawers();
            }
        });



    }

}
