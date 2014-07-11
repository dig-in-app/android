package com.github.digin.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.digin.android.constants.ParseKeys;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Toast.makeText(
                this,
                ParseKeys.getClientKey(this),
                Toast.LENGTH_LONG
        ).show();

    }

}
