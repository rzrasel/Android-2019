package com.sm.ansar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragTest extends Fragment {
    //|------------------------------------------------------------|
    /*private FragmentEventListener fragmentEventListener;
    private UserSession userSession = new UserSession();*/
    //|------------------------------------------------------------|
    //|------------------------------------------------------------|
    public static FragTest newInstance(String title) {
        FragTest fragment = new FragTest();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    //|------------------------------------------------------------|

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //|------------------------------------------------------------|
        View rootView = inflater.inflate(R.layout.frag_test, container, false);
        //|------------------------------------------------------------|
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TextView idTextViewTest = (TextView) rootView.findViewById(R.id.idTextViewTest);
            idTextViewTest.setText(bundle.getString("title") + "");
        }
        //|------------------------------------------------------------|
        /*TextView idTextViewTest = (TextView) rootView.findViewById(R.id.idTextViewTest);
        idTextViewTest.setText(userSession.getUserMobileNo());
        //|------------------------------------------------------------|
        if (fragmentEventListener != null) {
            fragmentEventListener.SendToActivityListener("FragTest to activity");
        }*/
        //|------------------------------------------------------------|
        return rootView;
        //|------------------------------------------------------------|
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*try {
            //eventListener = (EventListener) context;
            fragmentEventListener = (FragmentEventListener) context;
        } catch (ClassCastException e) {
            LogWriter.Log(context.toString() + " must implement EventListener");
            //throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }*/
    }

    /*public interface EventListener {
        public void ValuePassingListener();
    }*/
}