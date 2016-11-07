package com.li.pro.view.fragment.rxjava;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.li.fragmentutils.Fragmentation;
import com.li.fragmentutils.SupportActivity;
import com.li.fragmentutils.base.BaseFragment;
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
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxBaseOp.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxBaseOp.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //Rx map操作符
                    case 1:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxMap.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxMap.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //线程调度
                    case 2:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxSchedu.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxSchedu.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //Rx flatMap操作符
                    case 3:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxFlatMap.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxFlatMap.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //Rx merge操作符
                    case 4:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxmerge.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxmerge.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //RxBinding
                    case 5:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxBinding.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxBinding.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //Rx Filter操作符
                    case 6:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxFilter.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxFilter.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //Rx tacke、DoOnNext操作符
                    case 7:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxTakeDoNextOn.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxTakeDoNextOn.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //Rx interval操作符
                    case 8:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxInterval.class), false);
                        //隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxInterval.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
                        break;
                    //Rx toSortedList操作符
                    case 9:
                        //用当前活动状态的Fragment去替换另一个Fragment
//                        Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()).replaceFragment(findFragment(FragmentRxToSocredList.class), false);
//                        隐藏当前活动的Fragment 显示需要显示的Fragment
                        showHideFragment(findFragment(FragmentRxToSocredList.class), Fragmentation.getInstance((SupportActivity) getActivity()).getActiveFragment(FragmentRxJavaList.this, getFragmentManager()));
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
