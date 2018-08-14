package com.me.localvideotutorial;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterHomeList extends ArrayAdapter<ModelTutorialItem> {
    ArrayList<ModelTutorialItem> listItems = new ArrayList<ModelTutorialItem>();
    private Context context;
    private int layoutResourceId;
    private boolean isClicked = false;
    private int selectedPosition;
    private int checkedColor;

    public AdapterHomeList(Context argContext, int argLayoutResourceId, ArrayList<ModelTutorialItem> argListItems) {
        super(argContext, argLayoutResourceId, argListItems);
        this.context = argContext;
        this.layoutResourceId = argLayoutResourceId;
        this.listItems = argListItems;
    }

    @Override
    public View getView(int argPosition, View argConvertView, ViewGroup argParentGroup) {
        View rootRowView = argConvertView;
        RowViewHolder rowViewHolder;
        ModelTutorialItem item = this.listItems.get(argPosition);
        if (rootRowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootRowView = inflater.inflate(R.layout.lay_row_tutorial_list, argParentGroup, false);
            rowViewHolder = new RowViewHolder(rootRowView);
            rootRowView.setTag(rowViewHolder);
        } else {
            rowViewHolder = (RowViewHolder) rootRowView.getTag();
        }
        rowViewHolder.sysTextViewTitle.setText(item.getTitle() + "");
        return rootRowView;
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
        private TextView sysTextViewTitle;

        public RowViewHolder(View argRootView) {
            sysTextViewTitle = (TextView) argRootView.findViewById(R.id.sysTextViewTitle);
        }
    }

    public static float getDpToPixel(Context argContext, float argDp) {
        Resources resources = argContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = argDp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static int getSpToPx(Context argContext, float argSp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, argSp, argContext.getResources().getDisplayMetrics());
    }
}
