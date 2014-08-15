package com.github.digin.android.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ListView;

import com.github.digin.android.NavDrawerController;
import com.github.digin.android.R;
import com.github.digin.android.fragments.BoundedMapFragment;

public class MainActivity extends Activity {


    NavDrawerController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new BoundedMapFragment(), BoundedMapFragment.class.getName()).commit();

        getActionBar().setLogo(R.drawable.white_logo_small);

        initDrawer();
    }

    private void initDrawer() {
        controller = new NavDrawerController(this, (ListView) findViewById(R.id.navlist), (DrawerLayout) findViewById(R.id.drawer_layout));
        controller.init();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        controller.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        controller.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (controller.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_diginpassport));
        getActionBar().setTitle(getString(R.string.app_name));
    }
}
