package com.li.fragmentutils.base;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.fragmentutils.SupportActivity;
import com.li.fragmentutils.SwipeBackFragment;
import com.li.utils.SystemBarHelper;
import com.li.utils.animathionutils.AnimationUtilsForRO;
import com.li.utils.ui.widget.XFrameLayout;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/10/29 0029.
 */

public abstract class BaseSwipFragment extends SwipeBackFragment {
    private OnLockDrawLayoutListener mListener;
    private Toolbar toolBar = null;

    public void startInitAnimation(SupportActivity supportActivity, View view, int xFrameLayout) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        final int cy = rect.centerY();
        final int cx = rect.centerX();
        XFrameLayout xf = (XFrameLayout) supportActivity.findViewById(xFrameLayout);
        xf.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                // get the hypothenuse so the mRadius is from one corner to the other
                float radius = (float) Math.hypot(right, bottom);
                AnimationUtilsForRO.getInstance().createCheckoutRevealAnimator(xf, cx, cy, 28f, radius).start();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(ftagmentLayout(), container, false);
        initView(view);
        toolBar = (Toolbar) view.findViewById(R.id.detail_toolbar);
        if (null != toolBar) {
            toolBar.setTitle(setToolBarTitle());
        }
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolBar);
        // Show the Up button in the action bar.

        //状态栏透明
        SystemBarHelper.immersiveStatusBar(getActivity());
        //状态栏间隔高度
        SystemBarHelper.setHeightAndPadding(getActivity(), view.findViewById(R.id.app_bar));
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
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
        }
        return attachToSwipeBack(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLockDrawLayoutListener) {
            mListener = (OnLockDrawLayoutListener) context;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mListener != null) {
            mListener.onLockDrawLayout(false);
        }
    }

    public void onMDetach() {
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        onMDetach();
    }

    public interface OnLockDrawLayoutListener {
        void onLockDrawLayout(boolean lock);
    }

    //设置布局
    public abstract int ftagmentLayout();

    //初始化View、处理业务逻辑
    public abstract void initView(View view);

    //设置ToolBar标题文字
    public abstract String setToolBarTitle();

    //设置是否隐藏标题栏
    public abstract boolean isHideActionBar();

    //是否显示左上角箭头
    public abstract boolean isShowBackArrow();

    //设置左上角logo
    public abstract int setLeftCornerLogo();

    //设置ToolBar标题文字
    protected void setToolBarTitle(String str) {
        if (null != toolBar) {
            toolBar.setTitle(str);
        }
    }
}
