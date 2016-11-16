package com.li.pro.view.fragment.rxjava;

import android.view.View;

import com.li.fragmentutils.base.BaseLazySwipFragment;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class FragmentRxJava extends BaseLazySwipFragment {
    @Override
    public int ftagmentLayout() {
        return R.layout.layout_activit_rxjava;
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public String setToolBarTitle() {
        return "Rx基础操作";
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

    @Override
    protected void lazyFetchData() {
        //加载一个不可替换的Fragment根
        loadRootFragment(R.id.fl_rxjava_left, new FragmentRxJavaList());
        //必须先预加载所有Fragment才能通过find找到Fragment对象
        loadMultipleRootFragment(R.id.fl_rxjava_right, 0,
                new FragmentRxBaseOp(),
                new FragmentRxMap(),
                new FragmentRxSchedu(),
                new FragmentRxFlatMap(),
                new FragmentRxmerge(),
                new FragmentRxBinding(),
                new FragmentRxFilter(),
                new FragmentRxTakeDoNextOn(),
                new FragmentRxInterval(),
                new FragmentRxToSocredList()
        );


//        //加载一个可替换的Fragment根并默认加载哪个Fragment(调用Replace时必须加载,show/hide不用)
//        replaceLoadRootFragment(R.id.fl_rxjava_right, new FragmentRxBaseOp(), true);
    }

}
