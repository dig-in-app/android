package com.github.digin.android;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.digin.android.adapters.NavDrawerAdapter;
import com.github.digin.android.fragments.DeveloperFragment;
import com.github.digin.android.fragments.DiginAboutFragment;

/**
 * Created by david on 8/11/14.
 */
public class NavDrawerController implements AdapterView.OnItemClickListener {

    private final Activity mActivity;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView aboutDigin, aboutDevs;

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
        mDrawerList.setOnItemClickListener(this);

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
                if (oldTitle != null)
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

        // Set up about buttons on the bottom

        aboutDigin = (TextView) mDrawerLayout.findViewById(R.id.drawer_about_digin);
        aboutDigin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                aboutDigin();
            }
        });

        aboutDevs = (TextView) mDrawerLayout.findViewById(R.id.drawer_about_developers);
        aboutDevs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                aboutDevs();
            }
        });

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

    private void aboutDigin() {
        while (mActivity.getFragmentManager().getBackStackEntryCount() > 0)
            mActivity.getFragmentManager().popBackStackImmediate();

        mActivity.getFragmentManager().beginTransaction().addToBackStack(DiginAboutFragment.class.getName()).replace(R.id.content_frame,
                new DiginAboutFragment(),
                DiginAboutFragment.class.getName()).commit();
        mDrawerLayout.closeDrawers();


        ((NavDrawerAdapter) mDrawerList.getAdapter()).setCurrentItem(-1);
    }

    private void aboutDevs() {
        while (mActivity.getFragmentManager().getBackStackEntryCount() > 0)
            mActivity.getFragmentManager().popBackStackImmediate();

        mActivity.getFragmentManager().beginTransaction().addToBackStack(DeveloperFragment.class.getName()).replace(R.id.content_frame,
                new DeveloperFragment(),
                DeveloperFragment.class.getName()).commit();
        mDrawerLayout.closeDrawers();

        ((NavDrawerAdapter) mDrawerList.getAdapter()).setCurrentItem(-1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (((NavDrawerAdapter) mDrawerList.getAdapter()).getCurrentItem() != position) {


            ((NavDrawerAdapter) mDrawerList.getAdapter()).setCurrentItem(position);

            mActivity.getActionBar().setTitle(mActivity.getString(R.string.app_name));
            oldTitle = null;
            mActivity.getActionBar().setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.ab_solid_diginpassport));

            if (((NavDrawerItem) view.getTag()).getFragment() != null) {
                while (mActivity.getFragmentManager().getBackStackEntryCount() > 0)
                    mActivity.getFragmentManager().popBackStackImmediate();

                mActivity.getFragmentManager().beginTransaction().replace(R.id.content_frame,
                        ((NavDrawerItem) view.getTag()).getFragment(),
                        ((NavDrawerItem) view.getTag()).getFragment().getClass().getName()).commit();
                mDrawerLayout.closeDrawers();
            }
        } else {
            mDrawerLayout.closeDrawers();
        }
    }
}
