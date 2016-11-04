package com.li.pro.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.li.fragmentutils.base.BaseFragment;
import com.li.pro.adapter.NavRecycleViewAdapter;
import com.li.pro.adapter.RxJavaListAdapter;

import java.util.ArrayList;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class FragmentActivityRxJavaList extends BaseFragment {
    private ArrayList<String> listMenus;
    private RecyclerView recyclerView;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_rxjava_list;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_rxjava_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        RxJavaListAdapter.getInstance().cleanAll();
        recyclerView.setAdapter(RxJavaListAdapter.getInstance().init(getActivity()).add("基本操作流程", "map", "线程调度", "flatMap", "merge", "RxBinding", "Filter", "Take、DoOnNext", "Interval、取消订阅", "toSortedList", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."));
        RxJavaListAdapter.getInstance().setItemChecked(0);
        RxJavaListAdapter.getInstance().refresh();
        RxJavaListAdapter.getInstance().setOnItemClickListener(new RxJavaListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                RxJavaListAdapter.getInstance().setItemChecked(position);
            }
        });
    }
}
