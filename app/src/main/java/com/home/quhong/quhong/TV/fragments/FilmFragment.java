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
public class FilmFragment extends BaseFragment {


    public FilmFragment() {
        // Required empty public constructor
    }

    @Override
    public String getFragmentTitle() {
        return "FILM";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false);
    }

}
