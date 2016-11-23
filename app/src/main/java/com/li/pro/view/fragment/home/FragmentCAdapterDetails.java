package com.li.pro.view.fragment.home;

import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.li.fragmentutils.base.BaseLazySwipFragment;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.utils.ui.WebViewHelper;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/23 0023.
 */

public class FragmentCAdapterDetails extends BaseLazySwipFragment {
    private WebView wv_details;
    BeanHomeResults beanHomeResults;

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
        new WebViewHelper(
                (WebView) view.findViewById(R.id.wv_details), (ProgressBar) view.findViewById(R.id.pb_detail))
                .loadUrl(getArguments().getString("URL")
                );
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
