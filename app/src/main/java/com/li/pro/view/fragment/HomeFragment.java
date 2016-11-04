package com.li.pro.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.li.fragmentutils.SwipeBackFragment;
import com.li.fragmentutils.base.BaseFragment;
import com.li.fragmentutils.base.BaseSwipFragment;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/10/29 0029.
 */

public  class HomeFragment extends BaseFragment{
    private Button tv_test;
    int i;
    @Override
    public int ftagmentLayout() {
        return R.layout.layout_base_fragment;
    }
    @Override
    public void initView(View view) {
        tv_test= (Button) view.findViewById(R.id.tv_test);
        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                tv_test.setText((i+=1)+"");
            }
        });
    }

    @Override
    public String setToolBarTitle() {
        return null;
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }

    @Override
    public boolean isShowBackArrow() {
        return false;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }
}
