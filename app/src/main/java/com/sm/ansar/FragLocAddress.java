package com.sm.ansar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragLocAddress extends Fragment {
    public static FragLocAddress newInstance(String title) {
        FragLocAddress fragment = new FragLocAddress();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater argInflater, ViewGroup argContainer, Bundle argSavedInstanceState) {
        //|------------------------------------------------------------|
        View rootView = argInflater.inflate(R.layout.frag_loc_address, argContainer, false);
        //|------------------------------------------------------------|
        return rootView;
        //|------------------------------------------------------------|
    }
}
//https://android-dev-examples.blogspot.com/2014/10/android-shape-with-shadow.html