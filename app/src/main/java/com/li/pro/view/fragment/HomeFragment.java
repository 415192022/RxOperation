package com.li.pro.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.pro.adapter.FragmentHomeVPAdapter;
import com.li.utils.ui.widget.XViewPager;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/10/29 0029.
 */

public class HomeFragment extends BaseLazyFragment {
    private XViewPager xvp_fragment_home;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_home;
    }

    @Override
    public void initView(View view) {
        xvp_fragment_home = (XViewPager) view.findViewById(R.id.xvp_fragment_home);
        xvp_fragment_home.
                setAdapter(FragmentHomeVPAdapter.getInstance(getActivity(), getFragmentManager()).
                                init().
                                addFragments(
                                        new FragmentCAll(),
                                        new FragmentCApp(),
                                        new FragmentCExResource(),
                                        new FragmentCFront(),
                                        new FragmentCIos(),
                                        new FragmentCRecommand(),
                                        new FragmentCRelaxVideo(),
                                        new FragmentCWelfare(),
                                        new FragmentCAndroid()).
                                addTitle("全部", "App", "拓展资源", "前端", "IOS", "推荐", "休息视频", "福利", "Android")
                );
        ((TabLayout) view.findViewById(R.id.tl_main)).setupWithViewPager(xvp_fragment_home);
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

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {

    }
}
