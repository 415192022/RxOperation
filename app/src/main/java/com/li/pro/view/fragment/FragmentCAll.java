package com.li.pro.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.pro.adapter.home.FragmentCAllAdapter;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.pro.present.FragmentCAllPrecent;
import com.li.pro.view.ifragment.IFragmentCAllView;
import com.li.utils.ui.widget.XSwipeRefreshLayout;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class FragmentCAll extends BaseLazyFragment implements IFragmentCAllView {
    private RecyclerView rv_home_all;
    private XSwipeRefreshLayout xsrl_home_all;
    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_c_all;
    }

    @Override
    public void initView(View view) {

        xsrl_home_all= (XSwipeRefreshLayout) view.findViewById(R.id.xsrl_home_all);
        xsrl_home_all.refreshDrawableState();
        rv_home_all= (RecyclerView) view.findViewById(R.id.rv_home_all);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rv_home_all.setLayoutManager(linearLayoutManager);
        rv_home_all.setHasFixedSize(true);
        rv_home_all.setAdapter(FragmentCAllAdapter.newInstance(getActivity()));
        FragmentCAllPrecent.getInstance().with(this).getFragmentCAllData(10,1);
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

    @Override
    public void getFragmentCAllStart() {

    }

    @Override
    public void getFragmentCAll(BeanHomeResults beanHomeResults) {
        System.out.print("======"+beanHomeResults);
        FragmentCAllAdapter.newInstance(getActivity()).addData(beanHomeResults);
    }

    @Override
    public void getFragmentCAllComplete() {

    }

    @Override
    public void getFragmentCAllError() {

    }
}
