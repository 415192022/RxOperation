package com.li.pro.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.li.fragmentutils.base.BaseSwipActivity;

import rxop.li.com.rxoperation.R;

public class ItemDetailActivity extends BaseSwipActivity {
    @Override
    public int bindLayout() {
        return R.layout.activity_item_detail;
    }

    @Override
    public void doBusiness(Bundle savedInstanceState) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public String setToolBarTitle() {
        return "RxOp";
    }

    @Override
    public boolean isSetTransparentBar() {
        return true;
    }

    @Override
    public boolean isShowBackArrow() {
        return true;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }

    @Override
    public String setActionBarCenterTitle() {
        return null;
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }

    @Override
    public boolean isSetSwipBack() {
        return false;
    }

}
