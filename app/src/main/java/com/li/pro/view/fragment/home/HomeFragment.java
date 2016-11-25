package com.li.pro.view.fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
        xvp_fragment_home.setOffscreenPageLimit(9);
        xvp_fragment_home.setAdapter(
                FragmentHomeVPAdapter.
                        getInstance(getActivity(), getFragmentManager()).init().
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
        xvp_fragment_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_deep));
                        break;
                    case 1:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
                        break;
                    case 2:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
                        break;
                    case 3:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.cyan));
                        break;
                    case 4:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
                        break;
                    case 5:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_green));
                        break;
                    case 6:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.yellow));
                        break;
                    case 7:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.orange));
                        break;
                    case 8:
                        view.findViewById(R.id.detail_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.pink_pressed));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
