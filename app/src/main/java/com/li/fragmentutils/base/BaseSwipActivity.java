package com.li.fragmentutils.base;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.li.fragmentutils.SwipeBackActivity;
import com.li.fragmentutils.SwipeBackLayout;
import com.li.pro.FirstSwipeBackFragment;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/10/28 0028.
 */

public abstract class BaseSwipActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        if (isSetTransparentBar()) {
            if (Build.VERSION.SDK_INT >= 19) {
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }

        //=========================手势返回=========================================================
        if (isSetSwipBack()) {
            if (savedInstanceState == null) {
                loadRootFragment(R.id.fl_container, FirstSwipeBackFragment.newInstance());
            }
            getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_ALL);
        }
        //==================================================================================



        //执行业务逻辑
        doBusiness(savedInstanceState);


        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(setToolBarTitle());
        }
    }

    //绑定布局
    public abstract int bindLayout();

    //业务逻辑
    public abstract void doBusiness(Bundle savedInstanceState);

    //设置toolbar标题文字
    public abstract String setToolBarTitle();

    //是否设置透明状态栏
    public abstract boolean isSetTransparentBar();

    //是否支持手势返回
    public abstract boolean isSetSwipBack();

//    public FragmentAnimator onCreateFragmentAnimator() {
//        return new DefaultHorizontalAnimator();
//    }
}
