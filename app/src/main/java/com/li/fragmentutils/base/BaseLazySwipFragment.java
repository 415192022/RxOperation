package com.li.fragmentutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.li.fragmentutils.anim.FragmentAnimator;

/**
 * Created by Mingwei Li on 2016/11/9 0009.
 */

public abstract class BaseLazySwipFragment extends BaseSwipFragment {
    protected BaseLazyFragment.OnBackToFirstListener _mBackToFirstListener;
    private boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData; // 标识已经触发过懒加载数据

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        FragmentAnimator fragmentAnimator = _mActivity.getFragmentAnimator();
        fragmentAnimator.setEnter(0);
        fragmentAnimator.setExit(0);
        return fragmentAnimator;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasFetchData = false;
        isViewPrepared = false;
    }

    public void onMMAttach(Context context) {
    }

    @Override
    public void onMAttach(Context context) {
        if (context instanceof BaseLazyFragment.OnBackToFirstListener) {
            _mBackToFirstListener = (BaseLazyFragment.OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBackToFirstListener");
        }
        onMMAttach(context);
    }


    @Override
    public void onMDetach() {
        _mBackToFirstListener = null;
    }


    /**
     * 懒加载
     */
    protected void lazyFetchData() {
    }

    ;

    private void lazyFetchDataIfPrepared() {
// 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true; //已加载过数据
            lazyFetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {//当当前为显示页面时
            lazyFetchDataIfPrepared();
        }
    }

    /**
     * 处理回退事件
     *
     * @return
     */
//    @Override
//    public boolean onBackPressedSupport() {
//        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
//            popChild();
//        } else {
//            if (this instanceof HomeFragment) {   // 如果是 第一个Fragment 则退出app
//                _mActivity.finish();
//            } else {                                    // 如果不是,则回到第一个Fragment
//            _mBackToFirstListener.onBackToFirstFragment();
//            }
//        }
//        return true;
//    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }

    protected void onMAttach() {
    }

    ;
}
