package com.li.pro.view.fragment.home;

import android.view.View;

import com.li.fragmentutils.base.BaseFragment;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class FragmentCExResource extends BaseFragment {

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_c_exresource;
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
