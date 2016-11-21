package com.li.pro.view.fragment.home;

import android.view.View;

import com.li.fragmentutils.base.BaseLazySwipFragment;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class FragmentWeb extends BaseLazySwipFragment {
    @Override
    public int ftagmentLayout() {
        return 0;
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
