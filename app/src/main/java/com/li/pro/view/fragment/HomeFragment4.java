package com.li.pro.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.utils.ui.widget.XViewPager;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/10/29 0029.
 */

public class HomeFragment4 extends BaseLazyFragment {
    private XViewPager xvp_fragment_home;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_home4;
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public String setToolBarTitle() {
        return null;
    }

    @Override
    public boolean isHideActionBar() {
        return true;
    }

    @Override
    public boolean isShowBackArrow() {
        return false;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {

    }
}
