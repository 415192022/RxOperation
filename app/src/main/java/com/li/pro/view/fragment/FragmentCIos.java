package com.li.pro.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.li.fragmentutils.base.BaseLazyFragment;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class FragmentCIos extends BaseLazyFragment {
    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_c_ios;
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
