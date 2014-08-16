package com.github.digin.android.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.digin.android.R;
import com.github.digin.android.listeners.OnSingleParticipantQueryListener;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.models.Participant;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelperBase;

/**
 * Created by david on 8/16/14.
 */
public abstract class ParticipantDetailsFragment<T extends Participant> extends Fragment implements OnSingleParticipantQueryListener<T> {

    private FadingActionBarHelperBase mFadingHelper;

    private T participant;
    private CharSequence mOldTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_solid_diginpassport)
                .headerLayout(getHeaderResource())
                .contentLayout(getContentResource()).lightActionBar(true);
    }

    public abstract int getHeaderResource();
    public abstract int getContentResource();
    public abstract void fillContent();

    public T getParticipant() {
        return participant;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        String participantId = getArguments().getString("ID");

        queryParticipant(participantId);

        AnalyticsHelper.sendScreenView(getActivity(), ParticipantDetailsFragment.class, getParticipant().getName());
        getActivity().invalidateOptionsMenu();
    }

    public abstract void queryParticipant(String id);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = mFadingHelper.createView(inflater);

        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tryFillData();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().invalidateOptionsMenu();
    }

    private void tryFillData() {
        if (getParticipant() != null && getView() != null) {

            mOldTitle = getActivity().getActionBar().getTitle();

            fillContent();

        }
    }

    @Override
    public void onComplete(T participant) {
        this.participant = participant;
        tryFillData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFadingHelper.initActionBar(getActivity());
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!isVisible()) {
            getActivity().getActionBar().setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ab_solid_diginpassport));
            getActivity().getActionBar().setTitle(mOldTitle);
        }
    }

}
