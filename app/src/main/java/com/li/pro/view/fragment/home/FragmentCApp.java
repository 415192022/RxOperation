package com.li.pro.view.fragment.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.pro.adapter.home.FragmentCAllAdapter;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.pro.present.home.FragmentCAppPrecent;
import com.li.pro.view.ifragment.home.IFragmentCAppView;
import com.li.utils.ui.preload.PreLoader;
import com.li.utils.ui.widget.SwipeRefreshLoadMore;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/10 0010.
 */

public class FragmentCApp extends BaseLazyFragment implements IFragmentCAppView, SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLoadMore.OnLoadListener {
    private RecyclerView rv_home_app;
    private SwipeRefreshLoadMore swipeRefreshLoadMore;
    FragmentCAllAdapter fragmentCAllAdapter;

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

        rv_home_app = (RecyclerView) view.findViewById(R.id.rv_home_app);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_home_app.setLayoutManager(linearLayoutManager);
        rv_home_app.setHasFixedSize(true);


    }

    @Override
    protected void lazyFetchData() {
        fragmentCAllAdapter = FragmentCAllAdapter.newInstance().init(getActivity());
        rv_home_app.setAdapter(fragmentCAllAdapter);
        FragmentCAppPrecent.getInstance().with(this).getFragmentCAppData(10, 1);
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
        fragmentCAllAdapter.clearAllData().refresh();
        PreLoader.getInstance(getActivity()).start();
    }


    @Override
    public void getFragmentCApp(BeanHomeResults beanHomeResults) {
        fragmentCAllAdapter.addData(beanHomeResults).refresh();
    }

    @Override
    public void getFragmentCAppComplete() {
        PreLoader.getInstance(getActivity()).stop();
        swipeRefreshLoadMore.setRefreshing(false);
    }

    @Override
    public void getFragmentCAppError() {

    }

    @Override
    public void onRefresh() {
        fragmentCAllAdapter.clearAllData().refresh();
        FragmentCAppPrecent.getInstance().with(this).getFragmentCAppData(10, 1);
    }


    @Override
    public void onLoad() {

    }
}
