package com.li.pro.view.fragment.filetransfer.filelist;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.li.fragmentutils.base.BaseLazySwipFragment;
import com.li.pro.adapter.filelist.AdapterPathList;
import com.li.pro.adapter.itemdecoration.DividerItemDecoration;
import com.li.pro.bean.filetransfer.FileInfo;
import com.li.pro.present.filelist.FragmentPathPresenter;
import com.li.pro.view.ifragment.filelist.IFragmentPathView;
import com.li.utils.ui.slideuplayout.SlideUp;
import com.li.utils.ui.widget.XSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2017/1/10 0010.
 */

public class FragmentFileList extends BaseLazySwipFragment implements IFragmentPathView, SwipeRefreshLayout.OnRefreshListener {
    //dir列表
    private RecyclerView rv_fragment_filest;
    private XSwipeRefreshLayout xsrl_file_path_list;
    //dir列表适配器
    private AdapterPathList adapterPathList = null;
    private FragmentPathPresenter fragmentPathPresenter = null;
    private SlideUp slideUp;
    private String rootPath = Environment.getExternalStorageDirectory() + "";
    private Bundle getBundle = null;


    public static FragmentFileList newInstance(String rootPath) {
        FragmentFileList fragmentFileList = new FragmentFileList();
        Bundle bundle = new Bundle();
        bundle.putString("ROOT_PATH", rootPath);
        fragmentFileList.setArguments(bundle);
        return fragmentFileList;
    }

    private void receiveBundle() {
        getBundle = getArguments();
        rootPath = getBundle.getString("ROOT_PATH");
        if (rootPath.equals(Environment.getExternalStorageDirectory() + "")) {
            setSwipeBackEnable(false);
        }
    }

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_filelist;
    }

    @Override
    public void initView(View view) {
        receiveBundle();
        List<Integer> list = new ArrayList<>();
        rv_fragment_filest = (RecyclerView) view.findViewById(R.id.rv_fragment_filelist);
        rv_fragment_filest.setHasFixedSize(true);

        xsrl_file_path_list = (XSwipeRefreshLayout) view.findViewById(R.id.xsrl_file_path_list);
        xsrl_file_path_list.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        xsrl_file_path_list.setOnRefreshListener(this);
        xsrl_file_path_list.setRefreshing(true);

        //线性列表
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
//        网格
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        rv_fragment_filest.setLayoutManager(mLinearLayoutManager);
        rv_fragment_filest.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, true);
        rv_fragment_filest.addItemDecoration(dividerItemDecoration);

        adapterPathList = AdapterPathList.getInstance(getActivity(), this);
        //初始化P
        fragmentPathPresenter = FragmentPathPresenter.getInstance(this);
        //加载指定路径下的目录
        fragmentPathPresenter.getDirsList(rootPath);
        rv_fragment_filest.setAdapter(adapterPathList);
    }

    @Override
    public String setToolBarTitle() {
        return "文件列表";
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
    public void getDirList(FileInfo fileModel) {
        adapterPathList.addDatas(fileModel);
    }

    @Override
    public void onStartLoadDirList() {
        xsrl_file_path_list.setRefreshing(true);
        if (adapterPathList != null && adapterPathList.getDatas().size() > 0) {
            adapterPathList.clearAllDatas();
        }
    }

    @Override
    public void onCompletLoadDirList() {
        xsrl_file_path_list.setRefreshing(false);
        adapterPathList.notifyDataSetChanged();
    }

    @Override
    public void onLoadDirError(String er) {
        Toast.makeText(getActivity(), er, Toast.LENGTH_LONG).show();
        xsrl_file_path_list.setRefreshing(false);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (!rootPath.equals(Environment.getExternalStorageDirectory() + "")) {
            pop();
            return !super.onBackPressedSupport();
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onRefresh() {
        fragmentPathPresenter.getDirsList(rootPath);
    }
}
