package com.li.pro.view.fragment.rxjava;

import android.view.View;

import com.li.fragmentutils.base.BaseFragment;
import com.li.fragmentutils.base.BaseSwipFragment;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public class FragmentRxcToSocredList extends BaseFragment {
    @Override
    public int ftagmentLayout() {
        return R.layout.layout_rx_tosortedlist;
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
        return false;
    }

    @Override
    public boolean isShowBackArrow() {
        return false;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }
}
