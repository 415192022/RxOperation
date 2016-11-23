package com.li.utils.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;


/**
 * Created by tlh on 2016/9/8 :)
 */
public class XBottomSheetDialog extends BottomSheetDialog {
    private Context context;

    public XBottomSheetDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public XBottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
        this.context = context;
        init();
    }

    protected XBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        init();
    }

    private void init() {
    }

    public XBottomSheetDialog loadWeb(String url) {
        return this;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
