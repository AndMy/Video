package com.home.quhong.quhong.TV.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.home.quhong.quhong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends BaseFragment {

    @BindView(R.id.bt_beranda)
    Button mBtBeranda;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public String getFragmentTitle() {
        return "BERANDA";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.bt_beranda)
    public void onClick() {
        Toast.makeText(getContext(), "点击有效", Toast.LENGTH_SHORT).show();
    }
}
