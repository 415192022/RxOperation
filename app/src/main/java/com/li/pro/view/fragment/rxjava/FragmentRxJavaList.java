package com.li.pro.view.fragment.rxjava;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.li.fragmentutils.Fragmentation;
import com.li.fragmentutils.SupportActivity;
import com.li.fragmentutils.base.BaseFragment;
import com.li.pro.adapter.NavRecycleViewAdapter;
import com.li.pro.adapter.RxJavaListAdapter;

import java.util.ArrayList;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class FragmentRxJavaList extends BaseFragment {
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
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    //Rx基础操作
                    case 0:
                        //用当前活动状态的Fragment去替换另一个Fragment
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxBaseOp.class), false);
                        break;
                    //Rx map操作符
                    case 1:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxMap.class), false);
                        break;
                    //线程调度
                    case 2:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxSchedu.class), false);

                        break;
                    //Rx flatMap操作符
                    case 3:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxFlatMap.class), false);
                        break;
                    //Rx merge操作符
                    case 4:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxmerge.class), false);
                        break;
                    //RxBinding
                    case 5:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxBinding.class), false);
                        break;
                    //Rx Filter操作符
                    case 6:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxFilter.class), false);
                        break;
                    //Rx tacke、DoOnNext操作符
                    case 7:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxTakeDoNextOn.class), false);
                        break;
                    //Rx interval操作符
                    case 8:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxInterval.class), false);
                        break;
                    //Rx toSortedList操作符
                    case 9:
                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxcToSocredList.class), false);
                        break;
                }
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
