package com.li.pro.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.li.fragmentutils.base.BaseFragment;
import com.li.fragmentutils.base.BaseSwipActivity;
import com.li.fragmentutils.base.BaseSwipFragment;
import com.li.pro.view.fragment.FragmentActivityRxJavaList;
import com.li.pro.view.fragment.FragmentRxBaseOp;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class FragmentRxJava extends BaseSwipFragment {
    @Override
    public int ftagmentLayout() {
        return R.layout.layout_activit_rxjava;
    }

    @Override
    public void initView(View view) {
        loadRootFragment(R.id.fl_rxjava_left,new FragmentActivityRxJavaList());
        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxBaseOp());
    }
}
