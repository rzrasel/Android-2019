package com.library.UIView;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Rz Rasel on 2016-12-05.
 */

public class ACVTextChangedListener implements TextWatcher {
    public static final String TAG = "ACVTextChangedListener";
    private Context context;

    public ACVTextChangedListener(Context argContext) {
        this.context = argContext;
    }

    @Override
    public void afterTextChanged(Editable argEditable) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeTextChanged(CharSequence argCharSequence, int argStart, int argCount, int argAfter) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {
        /*try {
            // if you want to see in the logcat what the user types
            Log.e(TAG, "User input: " + userInput);
            MainActivity mainActivity = ((MainActivity) context);
            // update the adapater
            mainActivity.myAdapter.notifyDataSetChanged();
            // get suggestions from the database
            MyObject[] myObjs = mainActivity.databaseH.read(userInput.toString());
            // update the adapter
            mainActivity.myAdapter = new AutocompleteCustomArrayAdapter(mainActivity, R.layout.list_view_row_item, myObjs);
            mainActivity.myAutoComplete.setAdapter(mainActivity.myAdapter);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}