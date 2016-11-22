package com.li.pro.view.fragment.home;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.pro.adapter.home.FragmentCAllAdapter;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.pro.present.home.FragmentCExResourcePrecent;
import com.li.pro.view.ifragment.home.IFragmentCExResourceView;
import com.li.utils.ui.widget.SwipeRefreshLoadMore;
import com.li.utils.ui.widget.XRecyclerView;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/10 0010.
 */

public class FragmentCExResource extends BaseLazyFragment implements IFragmentCExResourceView, SwipeRefreshLoadMore.OnLoadListener, SwipeRefreshLoadMore.OnRefreshListener {
    private XRecyclerView xrv_fragment_c_exresource;
    private SwipeRefreshLoadMore srlm_fragment_c_exresource;
    private FragmentCAllAdapter fragmentCAllAdapter;

    private int page = 1;

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        fragmentCAllAdapter = FragmentCAllAdapter.newInstance().init(getActivity());
        xrv_fragment_c_exresource.setAdapter(fragmentCAllAdapter);
        FragmentCExResourcePrecent.getInstance().with(this).getFragmentCExResourceData(10, page);
    }

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_c_exresource;
    }

    @Override
    public void initView(View view) {
        srlm_fragment_c_exresource = (SwipeRefreshLoadMore) view.findViewById(R.id.srlm_fragment_c_exresource);
        srlm_fragment_c_exresource.setOnLoadListener(this);
        srlm_fragment_c_exresource.setOnRefreshListener(this);
        srlm_fragment_c_exresource.setRefreshing(true);
        srlm_fragment_c_exresource.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        xrv_fragment_c_exresource = (XRecyclerView) view.findViewById(R.id.xrv_fragment_c_exresource);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        xrv_fragment_c_exresource.setLayoutManager(linearLayoutManager);
        xrv_fragment_c_exresource.setHasFixedSize(true);
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
    public void getFragmentCExResourceStart() {
        srlm_fragment_c_exresource.setRefreshing(true);
        srlm_fragment_c_exresource.setLoading(true);
    }

    @Override
    public void getFragmentCExResource(BeanHomeResults beanHomeResults) {
        fragmentCAllAdapter.addData(beanHomeResults).refresh();
    }

    @Override
    public void getFragmentCExResourceComplete() {
        srlm_fragment_c_exresource.setRefreshing(false);
        srlm_fragment_c_exresource.setLoading(false);
    }

    @Override
    public void getFragmentCExResourceError() {
        srlm_fragment_c_exresource.setRefreshing(false);
        srlm_fragment_c_exresource.setLoading(false);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        fragmentCAllAdapter.clearAllData().refresh();
        page = 1;
        FragmentCExResourcePrecent.getInstance().getFragmentCExResourceData(10, page);
    }

    //加载更多
    @Override
    public void onLoad() {
        FragmentCExResourcePrecent.getInstance().getFragmentCExResourceData(10, page++);
    }
}
