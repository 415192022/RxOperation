package com.li.pro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.fragmentutils.SwipeBackFragment;
import com.li.fragmentutils.base.BaseSwipFragment;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/10/29 0029.
 */

public  class HomeFragment extends BaseSwipFragment{
    @Override
    public int ftagmentLayout() {
        return R.layout.layout_base_fragment;
    }
    @Override
    public void initView(View view) {

    }
}
