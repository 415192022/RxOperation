package com.li.pro.view.fragment.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.pro.adapter.home.FragmentCAllAdapter;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.pro.present.home.FragmentCAllPrecent;
import com.li.pro.view.ifragment.home.IFragmentCAllView;
import com.li.utils.T;
import com.li.utils.ui.preload.PreLoader;
import com.li.utils.ui.widget.SwipeRefreshLoadMore;
import com.li.utils.ui.widget.XRecyclerView;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/10 0010.
 */

public class FragmentCAll extends BaseLazyFragment implements IFragmentCAllView, SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLoadMore.OnLoadListener {
    private XRecyclerView rv_home_all;
    private SwipeRefreshLoadMore xsrl_home_all;
    private FrameLayout fl_fragmentcall_nodata_error;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_c_all;
    }

    @Override
    public void initView(View view) {
        xsrl_home_all = (SwipeRefreshLoadMore) view.findViewById(R.id.xsrl_home_all);
        rv_home_all = (XRecyclerView) view.findViewById(R.id.rv_home_all);
        fl_fragmentcall_nodata_error = (FrameLayout) view.findViewById(R.id.fl_fragmentcall_nodata_error);
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

    @Override
    public void getFragmentCAllStart() {
        PreLoader.getInstance(getActivity()).start();
        xsrl_home_all.setRefreshing(true);
        xsrl_home_all.setLoading(true);
    }

    @Override
    public void getFragmentCAll(BeanHomeResults beanHomeResults) {
        FragmentCAllAdapter.getInstance().addData(beanHomeResults).refresh();
    }

    @Override
    public void getFragmentCAllComplete() {
        PreLoader.getInstance(getActivity()).stop();
        xsrl_home_all.setRefreshing(false);
        xsrl_home_all.setLoading(false);
        FragmentCAllAdapter.getInstance().refresh();
        fl_fragmentcall_nodata_error.setVisibility(View.GONE);
    }

    @Override
    public void getFragmentCAllError() {
        //预加载效果停止
        PreLoader.getInstance(getActivity()).stop();
        fl_fragmentcall_nodata_error.setBackgroundResource(R.drawable.error_view);
        fl_fragmentcall_nodata_error.setVisibility(View.VISIBLE);
    }

    int page = 1;

    @Override
    public void onRefresh() {
        FragmentCAllAdapter.getInstance().clearAllData().refresh();
        page = 1;
        FragmentCAllPrecent.getInstance().with(this).getFragmentCAllData(10, page);
    }

    @Override
    public void onLoad() {
        T.getInstance(getActivity()).showToast("加载更多");
        FragmentCAllPrecent.getInstance().with(this).getFragmentCAllData(10, page++);
    }

    @Override
    protected void lazyFetchData() {
        Toast.makeText(getActivity(), "initLazyView", Toast.LENGTH_SHORT);
        xsrl_home_all.setOnRefreshListener(this);
        xsrl_home_all.setOnLoadListener(this);
        xsrl_home_all.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        xsrl_home_all.setRefreshing(true);
        xsrl_home_all.setLoading(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_home_all.setLayoutManager(linearLayoutManager);
        rv_home_all.setHasFixedSize(true);

        FragmentCAllAdapter fragmentCAllAdapter = FragmentCAllAdapter.getInstance().init(getActivity());
        rv_home_all.setAdapter(fragmentCAllAdapter);
        FragmentCAllPrecent.getInstance().with(this).getFragmentCAllData(10, 1);
    }


}
