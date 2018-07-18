package com.library.UIView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by Rz Rasel on 2016-12-05.
 */

public class ACVCustom extends AutoCompleteTextView {
    public ACVCustom(Context argContext) {
        super(argContext);
    }

    public ACVCustom(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
    }

    public ACVCustom(Context argContext, AttributeSet argAttrs, int argDefStyle) {
        super(argContext, argAttrs, argDefStyle);
    }

    @Override
    protected void performFiltering(final CharSequence argText, final int argKeyCode) {
        String filterText = "";
        super.performFiltering(filterText, argKeyCode);
    }
}