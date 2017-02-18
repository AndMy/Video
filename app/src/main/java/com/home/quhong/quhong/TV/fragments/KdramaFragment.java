package com.home.quhong.quhong.TV.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KdramaFragment extends BaseFragment {


    public KdramaFragment() {
        // Required empty public constructor
    }

    @Override
    public String getFragmentTitle() {
        return "KDRAMA";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_karama, container, false);
    }

}
