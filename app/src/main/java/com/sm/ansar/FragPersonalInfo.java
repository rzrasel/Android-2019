package com.sm.ansar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragPersonalInfo extends Fragment {
    public static FragPersonalInfo newInstance(String title) {
        FragPersonalInfo fragment = new FragPersonalInfo();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater argInflater, ViewGroup argContainer, Bundle argSavedInstanceState) {
        //|------------------------------------------------------------|
        View rootView = argInflater.inflate(R.layout.frag_personal_info, argContainer, false);
        //|------------------------------------------------------------|
        return rootView;
        //|------------------------------------------------------------|
    }
}
