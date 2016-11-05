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
        //加载一个不可替换的Fragment根
        loadRootFragment(R.id.fl_rxjava_left, new FragmentRxJavaList());
        //必须先预加载所有Fragment才能通过find找到Fragment对象
        loadMultipleRootFragment(R.id.fl_rxjava_right, 0,
                new FragmentRxBaseOp(), new FragmentRxMap(), new FragmentRxSchedu()
                , new FragmentRxFlatMap(), new FragmentRxmerge(), new FragmentRxBinding(), new FragmentRxFilter()
                , new FragmentRxTakeDoNextOn(), new FragmentRxInterval(), new FragmentRxcToSocredList()
        );
        //加载一个可替换的Fragment根并默认加载哪个Fragment
        replaceLoadRootFragment(R.id.fl_rxjava_right, new FragmentRxBaseOp(), true);
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
