package com.li.fragmentutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.fragmentutils.SwipeBackFragment;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/10/29 0029.
 */

public abstract class BaseSwipFragment extends SwipeBackFragment {
    private OnLockDrawLayoutListener mListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(ftagmentLayout(),container,false);
        initView(view);
        Toolbar toolBar= (Toolbar) view.findViewById(R.id.detail_toolbar);
        if(null != toolBar){
            toolBar.setTitle(setToolBarTitle());
        }
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolBar);
        // Show the Up button in the action bar.
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
}
