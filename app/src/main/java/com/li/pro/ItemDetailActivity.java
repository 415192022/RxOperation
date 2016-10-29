package com.li.pro;

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
        return toolBarTitle;
    }

    @Override
    public boolean isSetTransparentBar() {
        return false;
    }

    @Override
    public boolean isSetSwipBack() {
        return false;
    }

    private String toolBarTitle="xxxx";
}
