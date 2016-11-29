package com.li.pro.view.fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.pro.adapter.FragmentHomeVPAdapter;
import com.li.utils.animathionutils.AnimationUtilsForRO;
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
                        view.findViewById(R.id.collapsing_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_deep));
                        AnimationUtilsForRO.getInstance().setImageWithFade((ImageView) view.findViewById(R.id.iv_main_header), R.drawable.sky);
                        setToolBarTitle("全部");
                        break;
                    case 1:
                        view.findViewById(R.id.collapsing_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red));
                        AnimationUtilsForRO.getInstance().setImageWithFade((ImageView) view.findViewById(R.id.iv_main_header), R.drawable.sun);
                        setToolBarTitle("App");
                        break;
                    case 2:
                        view.findViewById(R.id.collapsing_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_purple));
                        AnimationUtilsForRO.getInstance().setImageWithFade((ImageView) view.findViewById(R.id.iv_main_header), R.drawable.train);
                        setToolBarTitle("拓展资源");
                        break;
                    case 3:
                        view.findViewById(R.id.collapsing_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.dark_purple));
                        AnimationUtilsForRO.getInstance().setImageWithFade((ImageView) view.findViewById(R.id.iv_main_header), R.drawable.arsenal);
                        setToolBarTitle("前端");
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
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
        return "Home";
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
