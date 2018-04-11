package com.sm.ansar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDashboard extends ArrayAdapter<ModelDashboard> {
    ArrayList<ModelDashboard> listItems = new ArrayList<ModelDashboard>();
    private Context context;
    private int layoutResourceId;
    private boolean isClicked = false;
    private int selectedPosition;
    private int checkedColor;

    public AdapterDashboard(Context argContext, int argLayoutResourceId, ArrayList<ModelDashboard> argListItems) {
        super(argContext, argLayoutResourceId, argListItems);
        this.context = argContext;
        this.layoutResourceId = argLayoutResourceId;
        this.listItems = argListItems;
    }

    @Override
    public View getView(int argPosition, View argConvertView, ViewGroup argParentGroup) {
        View rowView = argConvertView;
        RowViewHolder rowViewHolder;
        /*if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.grid_item_dashboard, argParentGroup, false);
            rowViewHolder = new RowViewHolder(rowView);
            rowView.setTag(rowViewHolder);
        } else {
            rowViewHolder = (RowViewHolder) rowView.getTag();
        }*/
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.grid_item_dashboard, argParentGroup, false);
        rowViewHolder = new RowViewHolder(rowView);
        ModelDashboard item = this.listItems.get(argPosition);
        //System.out.println("DEBUG: " + item.getCount());
        int itemCount = item.getCount();
        if (itemCount > 0) {
            rowViewHolder.sysRelaCircle.setVisibility(View.VISIBLE);
            rowViewHolder.sysTvCounter.setVisibility(View.VISIBLE);
            rowViewHolder.sysTvCounter.setText(itemCount + "");
        } else {
            rowViewHolder.sysRelaCircle.setVisibility(View.GONE);
            rowViewHolder.sysTvCounter.setVisibility(View.GONE);
            rowViewHolder.sysTvCounter.setText("");
        }
        rowViewHolder.sysIvImage.setImageDrawable(context.getResources().getDrawable(item.getResourceId()));
        rowViewHolder.sysTvTitle.setText(item.getTitle() + "");
        return rowView;
    }

    @Override
    public int getCount() {
        return this.listItems.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setSelectedPosition(int argPosition, boolean argIsClicked, int argCheckedColor) {

        this.selectedPosition = argPosition;
        this.isClicked = argIsClicked;
        this.checkedColor = argCheckedColor;

    }

    static class RowViewHolder {
        public RelativeLayout sysRelaCircle;
        public TextView sysTvCounter;
        public ImageView sysIvImage;
        public TextView sysTvTitle;

        public RowViewHolder(View argRootView) {
            sysRelaCircle = (RelativeLayout) argRootView.findViewById(R.id.sysRelaCircle);
            sysTvCounter = (TextView) argRootView.findViewById(R.id.sysTvCounter);
            sysIvImage = (ImageView) argRootView.findViewById(R.id.sysIvImage);
            sysTvTitle = (TextView) argRootView.findViewById(R.id.sysTvTitle);
        }
    }
}