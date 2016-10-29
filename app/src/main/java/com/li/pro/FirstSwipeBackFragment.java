package com.li.pro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.fragmentutils.SwipeBackFragment;

import rxop.li.com.rxoperation.R;


public class FirstSwipeBackFragment extends SwipeBackFragment {
    public static FirstSwipeBackFragment newInstance() {
        Bundle args = new Bundle();
        FirstSwipeBackFragment fragment = new FirstSwipeBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_back_first, container, false);
        return attachToSwipeBack(view);
    }
}
