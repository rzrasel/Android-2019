package com.sm.solvelistviewprobmel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListView extends ArrayAdapter<ModelListData> {
    Context context;
    private int layoutResourceId;
    ArrayList<ModelListData> dataModelItems;

    public AdapterListView(Context argContext, int argLayoutResourceId, ArrayList<ModelListData> argDataModelItems) {
        super(argContext, argLayoutResourceId, argDataModelItems);
        this.context = argContext;
        this.layoutResourceId = argLayoutResourceId;
        this.dataModelItems = argDataModelItems;
    }

    @Override
    public int getCount() {
        return dataModelItems.size();
    }

    @Override
    public long getItemId(int argId) {
        return argId;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int argPosition, View argConvertView, ViewGroup argParent) {
        View rootView = argConvertView;
        RowViewHolder rowViewHolder;
        ModelListData dataModelItem = getItem(argPosition);
        final View result;
        if (argConvertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.lay_lst_data_list, argParent, false);
            rowViewHolder = new RowViewHolder(rootView);
            result = rootView;
            rootView.setTag(rowViewHolder);
        } else {
            rowViewHolder = (RowViewHolder) rootView.getTag();
            result = rootView;
        }
        Animation animation = AnimationUtils.loadAnimation(context, (argPosition > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = argPosition;
        rowViewHolder.sysListTitle.setText(dataModelItem.getTitle());
        return rootView;
    }

    private static class RowViewHolder {
        private TextView sysListTitle;

        public RowViewHolder(View argRootView) {
            sysListTitle = (TextView) argRootView.findViewById(R.id.sysListTitle);
        }
    }
}
