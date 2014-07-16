package com.github.digin.android.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.github.digin.android.R;

import java.util.Arrays;

import com.github.digin.android.adapters.*;

/**
 * Created by david on 7/15/14.
 */
public class ParticipantListFragment extends ListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(new ParticipantListAdapter(getActivity(), R.layout.drawer_item,R.id.text, Arrays.asList(
                "Hello", "This", "is", "some", "data", "that", "I", "am", "pretending",
                "represents", "chefs", "data", "that", "I", "am", "pretending", "represents",
                "chefs", "data", "that", "I", "am", "pretending", "represents", "chefs", "data",
                "that", "I", "am", "pretending", "represents", "chefs", "data", "that", "I", "am",
                "pretending", "represents", "chefs", "data", "that", "I", "am", "pretending",
                "represents", "chefs", "data", "that", "I", "am", "pretending", "represents",
                "chefs", "data", "that", "I", "am", "pretending", "represents", "chefs", "data",
                "that", "I", "am", "pretending", "represents", "chefs", "data", "that", "I", "am",
                "pretending", "represents", "chefs", "data", "that", "I", "am", "pretending",
                "represents", "chefs")));
    }
}
