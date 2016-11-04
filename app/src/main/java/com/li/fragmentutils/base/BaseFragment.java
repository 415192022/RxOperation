package com.li.fragmentutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.fragmentutils.SupportFragment;
import com.li.fragmentutils.SwipeBackFragment;

/**
 * Created by Mingwei Li on 2016/10/29 0029.
 */

public abstract class BaseFragment extends SupportFragment {
    private OnLockDrawLayoutListener mListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(ftagmentLayout(),container,false);
        initView(view);
        return view;
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

    //初始化View
    public abstract void initView(View view);
}
