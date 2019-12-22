package com.daffatahta.mobile2019UAS.fragments.currency;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daffatahta.mobile2019UAS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Currency extends Fragment {


    public Currency() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency, container, false);
    }

}
