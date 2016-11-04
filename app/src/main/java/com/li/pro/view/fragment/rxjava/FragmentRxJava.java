package com.li.pro.view.fragment.rxjava;

import android.view.View;

import com.li.fragmentutils.base.BaseSwipFragment;

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
        loadRootFragment(R.id.fl_rxjava_left, new FragmentRxJavaList());
        loadMultipleRootFragment(R.id.fl_rxjava_right, 0, new FragmentRxBaseOp(), new FragmentRxMap());
//        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxSchedu());
//        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxFlatMap());
//        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxmerge());
//        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxBinding());
//        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxFilter());
//        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxTakeDoNextOn());
//        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxInterval());
//        loadRootFragment(R.id.fl_rxjava_right,new FragmentRxcToSocredList());
    }

    @Override
    public String setToolBarTitle() {
        return "RxJavaDemo";
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
