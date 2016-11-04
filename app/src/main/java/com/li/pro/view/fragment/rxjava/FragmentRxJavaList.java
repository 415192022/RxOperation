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
                        ((FragmentRxJava) getParentFragment()).showHideFragment(findFragment(FragmentRxBaseOp.class), findFragment(FragmentRxMap.class));

                        break;
                    //Rx map操作符
                    case 1:
                        ((FragmentRxJava) getParentFragment()).showHideFragment(findFragment(FragmentRxMap.class), findFragment(FragmentRxBaseOp.class));
                        break;
                    //线程调度
                    case 2:

                        break;
                    //Rx flatMap操作符
                    case 3:

                        break;
                    //Rx merge操作符
                    case 4:

                        break;
                    //RxBinding
                    case 5:

                        break;
                    //Rx Filter操作符
                    case 6:

                        break;
                    //Rx tacke、DoOnNext操作符
                    case 7:

                        break;
                    //Rx interval操作符
                    case 8:

                        break;
                    //Rx toSortedList操作符
                    case 9:

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
