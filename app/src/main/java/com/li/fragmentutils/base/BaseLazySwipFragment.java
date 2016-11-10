package com.li.fragmentutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public abstract class BaseLazySwipFragment extends BaseSwipFragment {
    private boolean mInited = false;
    protected BaseLazyFragment.OnBackToFirstListener _mBackToFirstListener;
    private Bundle mSavedInstanceState;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseLazyFragment.OnBackToFirstListener) {
            _mBackToFirstListener = (BaseLazyFragment.OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBackToFirstListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mBackToFirstListener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            if (!isHidden()) {
                mInited = true;
                initLazyView(null);
            }
        } else {
            // isSupportHidden()仅在saveIns tanceState!=null时有意义,是库帮助记录Fragment状态的方法
            if (!isSupportHidden()) {
                mInited = true;
                initLazyView(savedInstanceState);
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!mInited && !hidden) {
            mInited = true;
            initLazyView(mSavedInstanceState);
        }
    }

    /**
     * 懒加载
     */
    protected abstract void initLazyView(@Nullable Bundle savedInstanceState);

//    /**
//     * 处理回退事件
//     *
//     * @return
//     */
////    @Override
//    public boolean onBackPressedSupport() {
//        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
//            popChild();
//        } else {
//            if (this instanceof FragmentRxJava) {   // 如果是 第一个Fragment 则退出app
//                _mActivity.finish();
//            } else {                                    // 如果不是,则回到第一个Fragment
//                _mBackToFirstListener.onBackToFirstFragment();
//            }
//        }
//        return true;
//    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}
