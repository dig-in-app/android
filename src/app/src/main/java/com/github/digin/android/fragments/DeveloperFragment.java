package com.github.digin.android.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.digin.android.R;
import com.github.digin.android.logging.AnalyticsHelper;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelperBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeveloperFragment extends Fragment {


    private FadingActionBarHelperBase mFadingHelper;

    public DeveloperFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_solid_diginpassport)
                .headerLayout(R.layout.fragment_developer)
                .contentLayout(R.layout.developer_content).lightActionBar(true);
        AnalyticsHelper.sendScreenView(getActivity(), DeveloperFragment.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = mFadingHelper.createView(inflater);
        view.setBackgroundColor(Color.GRAY);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button b = (Button) view.findViewById(R.id.acm_link);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://acm.cs.purdue.edu/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mFadingHelper.initActionBar(getActivity());
    }
}
