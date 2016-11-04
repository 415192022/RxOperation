package com.li.pro.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.li.fragmentutils.base.BaseFragment;

import rxop.li.com.rxoperation.R;


public class FirstSwipeBackFragment extends BaseFragment {
    public static FirstSwipeBackFragment newInstance() {
        Bundle args = new Bundle();
        FirstSwipeBackFragment fragment = new FirstSwipeBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int ftagmentLayout() {
        return R.layout.fragment_swipe_back_first;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public String setToolBarTitle() {
        return "123";
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }

    @Override
    public boolean isShowBackArrow() {
        return true;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }
}
