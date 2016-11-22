package com.li.pro.view.fragment.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.pro.adapter.home.FragmentCAllAdapter;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.pro.present.home.FragmentCAppPrecent;
import com.li.pro.view.ifragment.home.IFragmentCAppView;
import com.li.utils.ui.widget.SwipeRefreshLoadMore;
import com.li.utils.ui.widget.XRecyclerView;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/10 0010.
 */

public class FragmentCApp extends BaseLazyFragment implements IFragmentCAppView, SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLoadMore.OnLoadListener {
    private XRecyclerView xRecyclerView;
    private SwipeRefreshLoadMore swipeRefreshLoadMore;
    private FragmentCAllAdapter fragmentCAllAdapter;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_c_app;
    }

    @Override
    public void initView(View view) {
        swipeRefreshLoadMore = (SwipeRefreshLoadMore) view.findViewById(R.id.xsrl_home_app);
        swipeRefreshLoadMore.setOnRefreshListener(this);
        swipeRefreshLoadMore.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLoadMore.setRefreshing(true);
        swipeRefreshLoadMore.setOnLoadListener(this);
        swipeRefreshLoadMore.setLoading(false);

        xRecyclerView = (XRecyclerView) view.findViewById(R.id.rv_home_app);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setHasFixedSize(true);


    }

    private int page = 1;

    @Override
    protected void lazyFetchData() {
        fragmentCAllAdapter = FragmentCAllAdapter.newInstance().init(getActivity());
        xRecyclerView.setAdapter(fragmentCAllAdapter);
        FragmentCAppPrecent.getInstance().with(this).getFragmentCAppData(10, page);
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
    public void getFragmentCAppStart() {
        //预加载效果开始
//        PreLoader.getInstance(getActivity()).start();
        swipeRefreshLoadMore.setRefreshing(true);
        swipeRefreshLoadMore.setLoading(true);
    }


    @Override
    public void getFragmentCApp(BeanHomeResults beanHomeResults) {
        fragmentCAllAdapter.addData(beanHomeResults).refresh();
    }

    @Override
    public void getFragmentCAppComplete() {
        //预加载效果停止
//        PreLoader.getInstance(getActivity()).stop();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setLoading(false);
    }

    @Override
    public void getFragmentCAppError() {
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setLoading(false);
    }

    @Override
    public void onRefresh() {
        fragmentCAllAdapter.clearAllData().refresh();
        page = 1;
        FragmentCAppPrecent.getInstance().with(this).getFragmentCAppData(10, page);
    }


    @Override
    public void onLoad() {
        FragmentCAppPrecent.getInstance().with(this).getFragmentCAppData(10, page++);
    }
}
