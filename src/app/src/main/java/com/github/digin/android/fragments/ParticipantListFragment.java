package com.github.digin.android.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.github.digin.android.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.digin.android.adapters.*;
import com.github.digin.android.bitmap.BitmapCacheHost;
import com.github.digin.android.factories.ChefFactory;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Participant;
import com.github.digin.android.models.TemporaryParticipantPlaceholder;

/**
 * Created by david on 7/15/14.
 */
public class ParticipantListFragment extends ListFragment {

    List<String> words = Arrays.asList(
            "Hello", "This", "is", "some", "data", "that", "I", "am", "pretending",
            "represents", "chefs", "data", "that", "I", "am", "pretending", "represents",
            "chefs", "data", "that", "I", "am", "pretending", "represents", "chefs", "data",
            "that", "I", "am", "pretending", "represents", "chefs", "data", "that", "I", "am",
            "pretending", "represents", "chefs", "data", "that", "I", "am", "pretending",
            "represents", "chefs", "data", "that", "I", "am", "pretending", "represents",
            "chefs", "data", "that", "I", "am", "pretending", "represents", "chefs", "data",
            "that", "I", "am", "pretending", "represents", "chefs", "data", "that", "I", "am",
            "pretending", "represents", "chefs", "data", "that", "I", "am", "pretending",
            "represents", "chefs");

    List<Participant> people = new ArrayList<Participant>();

    public ParticipantListFragment() {
        for(String word : words) {
            TemporaryParticipantPlaceholder temp = new TemporaryParticipantPlaceholder(word);
            people.add((Participant) temp);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(new ParticipantListAdapter(getActivity(), 0, R.id.text, people, (BitmapCacheHost) getActivity().getApplication()));
    }

}
