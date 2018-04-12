package com.sm.ansar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterViewPager extends FragmentPagerAdapter {
    private int numOfTabs;

    public AdapterViewPager(FragmentManager argFragmentManager, int argNumOfTabs) {
        super(argFragmentManager);
        this.numOfTabs = argNumOfTabs;
    }

    public Fragment getItem(int argPosition) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (argPosition) {
            case 0:
                fragment = FragLocAddress.newInstance("Frame 1");
                break;
            case 1:
                fragment = FragPersonalInfo.newInstance("Frame 2");
                break;
            case 2:
                fragment = FragEducationalInfo.newInstance("Frame 3");
                break;
            case 3:
                fragment = FragPersonalImage.newInstance("Frame 4");
                break;
        }
        if (fragment != null) {
            //fragment.setArguments(bundle);
        }
        return fragment;
    }

    public int getCount() {
        return numOfTabs;
    }

    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }
}