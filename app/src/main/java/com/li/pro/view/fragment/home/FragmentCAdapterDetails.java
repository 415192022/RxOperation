package com.li.pro.view.fragment.home;

import android.view.View;
import android.widget.ProgressBar;

import com.li.fragmentutils.anim.FragmentAnimator;
import com.li.fragmentutils.base.BaseLazySwipFragment;
import com.li.utils.ui.widget.XProgressWebView;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/23 0023.
 */

public class FragmentCAdapterDetails extends BaseLazySwipFragment {

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
//        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnicmator();
        // 设置自定义动画
        return new FragmentAnimator(R.anim.h_fragment_enter, R.anim.h_fragment_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_pop_exit);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();

    }

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_details;
    }

    @Override
    public void initView(View view) {
        ((XProgressWebView) view.findViewById(R.id.wv_details)).init().withProgress((ProgressBar) view.findViewById(R.id.pb_detail)).loadUrl(getArguments().getString("URL"));
    }

    @Override
    public String setToolBarTitle() {
        return "详情";
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
