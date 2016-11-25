package com.li.fragmentutils.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.li.fragmentutils.SupportActivity;
import com.li.utils.SystemBarHelper;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/10/28 0028.
 */

public abstract class BaseActivity extends SupportActivity {
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
        //执行业务逻辑
        doBusiness(savedInstanceState);


        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        if (toolbar != null) {
            toolbar.setTitle(setToolBarTitle());
        }
        //状态栏透明
        SystemBarHelper.immersiveStatusBar(this);
        //状态栏间隔高度
        SystemBarHelper.setHeightAndPadding(this, findViewById(R.id.app_bar));

        setSupportActionBar(toolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //是否显示返回箭头
            if (isShowBackArrow()) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            //隐藏ActionBar
            if (isHideActionBar()) {
                actionBar.hide();
            }
            //在使用v7包的时候显示icon和标题需指定一下属性。
            actionBar.setDisplayShowHomeEnabled(true);
            //设置ActionBar logo
            actionBar.setLogo(setLeftCornerLogo());
            //是否显示ActivonBar logo
            actionBar.setDisplayUseLogoEnabled(true);
            // 左侧图标点击事件使能
            actionBar.setHomeButtonEnabled(true);
            View customView = LayoutInflater.from(this).inflate(R.layout.layout_actionbar_tile, null);
            actionBar.setCustomView(customView);
            TextView actionBarTv = (TextView) customView.findViewById(R.id.tv_actionbar);
            actionBarTv.setText(setActionBarCenterTitle());
            actionBarTv.setTextColor(Color.WHITE);
        }
//        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

    }

    //绑定布局
    public abstract int bindLayout();

    //业务逻辑
    public abstract void doBusiness(Bundle savedInstanceState);

    //设置toolbar标题文字
    public abstract String setToolBarTitle();

    //是否设置透明状态栏
    public abstract boolean isSetTransparentBar();

    //是否显示左上角箭头
    public abstract boolean isShowBackArrow();

    //设置左上角logo
    public abstract int setLeftCornerLogo();

    //设置ActionBar中央标题名字
    public abstract String setActionBarCenterTitle();

    //设置是否隐藏标题栏
    public abstract boolean isHideActionBar();

//    public FragmentAnimator onCreateFragmentAnimator() {
//        return new DefaultHorizontalAnimator();
//    }
}
