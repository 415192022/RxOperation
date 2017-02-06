package com.li.utils.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import rxop.li.com.rxoperation.R;


/**
 * Created by tlh on 2016/9/8 :)
 */
public class ShowcaseListBottomSheetDialog extends BottomSheetDialog {
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private RecyclerView recyclerView;

    public ShowcaseListBottomSheetDialog(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        //init recycler view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_fragment_filelist, null, false);
        setContentView(view);

        //set bottom sheet behaviour
        View sheetView = getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        assert sheetView != null;
        bottomSheetBehavior = BottomSheetBehavior.from(sheetView);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            recyclerView.scrollToPosition(0);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return;
        }
        super.onBackPressed();
    }
}
