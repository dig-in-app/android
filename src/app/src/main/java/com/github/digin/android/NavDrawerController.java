package com.github.digin.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.digin.android.adapters.NavDrawerAdapter;

/**
 * Created by david on 8/11/14.
 */
public class NavDrawerController {

    private final Activity mActivity;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    //Used to temporarily change the title when the drawer is open. This holds the previous title.
    private String oldTitle;

    public NavDrawerController(final Activity activity, final ListView mDrawerList, DrawerLayout mDrawerLayout) {
        this.mDrawerLayout = mDrawerLayout;
        this.mDrawerList = mDrawerList;
        this.mActivity = activity;
    }

    public void init() {
        // Set the adapter for the list view
        mDrawerList.setAdapter(new NavDrawerAdapter(mActivity, R.layout.drawer_item, NavDrawerItem.getItems()));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((NavDrawerAdapter)mDrawerList.getAdapter()).setCurrentItem(position);

                mActivity.getActionBar().setTitle(mActivity.getString(R.string.app_name));
                oldTitle = null;
                mActivity.getActionBar().setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.ab_solid_diginpassport));

                if(((NavDrawerItem) view.getTag()).getFragment() != null) {
                    mActivity.getFragmentManager().popBackStackImmediate();
                    mActivity.getFragmentManager().beginTransaction().replace(R.id.content_frame,
                            ((NavDrawerItem) view.getTag()).getFragment(),
                            ((NavDrawerItem) view.getTag()).toString()).commit();
                    mDrawerLayout.closeDrawers();
                }
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(
                mActivity,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.action_close_drawer,  /* "open drawer" description */
                R.string.action_open_drawer  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if(oldTitle != null)
                    mActivity.getActionBar().setTitle(oldTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                oldTitle = mActivity.getActionBar().getTitle().toString();

                mActivity.getActionBar().setTitle(mActivity.getString(R.string.app_name));
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void syncState() {
        mDrawerToggle.syncState();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);
    }
}
