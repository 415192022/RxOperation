package com.li.pro.view.fragment.home;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.li.fragmentutils.base.BaseFragment;
import com.li.pro.adapter.RxJavaListAdapter;
import com.li.utils.ui.widget.XRecyclerView;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/10/29 0029.
 */

public class HomeFragment2 extends BaseFragment {
    private XRecyclerView rv_test;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_home2;
    }

    @Override
    public void initView(View view) {
        rv_test = (XRecyclerView) view.findViewById(R.id.rv_test);
        rv_test.setHasFixedSize(true);
        rv_test.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_test.setAdapter(RxJavaListAdapter.getInstance().init(getActivity()).add("基本操作流程", "map", "线程调度", "flatMap", "merge", "RxBinding", "Filter", "Take、DoOnNext", "Interval、取消订阅", "toSortedList", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."));
    }

    @Override
    public String setToolBarTitle() {
        return null;
    }

    @Override
    public boolean isHideActionBar() {
        return true;
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
