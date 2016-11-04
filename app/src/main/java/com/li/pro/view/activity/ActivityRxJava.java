package com.li.pro.view.activity;

import android.os.Bundle;

import com.li.fragmentutils.base.BaseSwipActivity;
import com.li.pro.view.fragment.FragmentActivityRxJavaList;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class ActivityRxJava extends BaseSwipActivity {
    @Override
    public int bindLayout() {
        return R.layout.layout_activit_rxjava;
    }

    @Override
    public void doBusiness(Bundle savedInstanceState) {
        loadRootFragment(R.id.fl_rxjava_left,new FragmentActivityRxJavaList());
    }

    @Override
    public String setToolBarTitle() {
        return "RxJava";
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
        return true;
    }
}
