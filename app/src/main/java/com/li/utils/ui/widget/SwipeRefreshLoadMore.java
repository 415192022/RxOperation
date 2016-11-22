package com.li.utils.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;


/**
 * 自定义View继承SwipeRefreshLayout，添加上拉加载更多的布局属性
 * Created by Mingwei Li on 2016/11/18.
 */

public class SwipeRefreshLoadMore extends SwipeRefreshLayout {

    private final int mScaledTouchSlop;
    //    private final View mFooterView;
    private ListView mListView;
    private XRecyclerView xRecyclerView;
    private OnLoadListener mOnLoadListener;

    /**
     * 正在加载状态
     */
    private boolean isLoading;

    public SwipeRefreshLoadMore(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 填充底部加载布局
//        mFooterView = View.inflate(context, R.layout.view_footer, null);

        // 表示控件移动的最小距离，手移动的距离大于这个距离才能拖动控件
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        System.out.println("====" + mScaledTouchSlop);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mListView == null) {
            // 判断容器有多少个孩子
            if (getChildCount() > 0) {
                // 判断第一个孩子是不是ListView
                if (getChildAt(0) instanceof ListView) {
                    // 创建ListView对象
                    mListView = (ListView) getChildAt(0);

                    // 设置ListView的滑动监听
                    setListViewOnScroll();
                }
            }
        }

        if (xRecyclerView == null) {
            // 判断容器有多少个孩子
            if (getChildCount() > 0) {
                // 判断第一个孩子是不是ListView
                if (getChildAt(0) instanceof XRecyclerView) {
                    // 创建ListView对象
                    xRecyclerView = (XRecyclerView) getChildAt(0);

                    // 设置ListView的滑动监听
                    setRecyclerViewOnScroll();
                }
            }
        }

    }

    /**
     * 在分发事件的时候处理子控件的触摸事件
     *
     * @param ev
     * @return
     */
    private float mDownY, mUpY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 移动的起点
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动过程中判断时候能下拉加载更多
                if (canLoadMore()) {
                    // 加载数据
                    loadData();
                }

                break;
            case MotionEvent.ACTION_UP:
                // 移动的终点
                mUpY = getY();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断是否满足加载更多条件
     *
     * @return
     */
    private boolean canLoadMore() {
        // 1. 是上拉状态
        boolean condition1 = (mDownY - mUpY) >= mScaledTouchSlop;
        if (condition1) {
            System.out.println("是上拉状态");
            Log.d("canLoadMore", "是上拉状态");
        }

        // 2. 当前页面可见的item是最后一个条目
        boolean condition2 = false;
        if (mListView != null && mListView.getAdapter() != null) {
            condition2 = mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        } else if (null != xRecyclerView && null != xRecyclerView.getAdapter()) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) xRecyclerView.getLayoutManager();
            condition2 = linearLayoutManager.findLastVisibleItemPosition() == (xRecyclerView.getAdapter().getItemCount() - 1);
            Log.d("canLoadMore", linearLayoutManager.findLastVisibleItemPosition() + "========>" + xRecyclerView.getAdapter().getItemCount());
        }
        if (condition2) {
            System.out.println("是最后一个条目");
            Log.d("canLoadMore", "是最后一个条目");
        }
        // 3. 正在加载状态
        boolean condition3 = !isLoading;
        if (condition3) {
            System.out.println("不是正在加载状态");
            Log.d("canLoadMore", "不是正在加载状态");
        }
        return condition1 && condition2 && condition3;
    }

    /**
     * 处理加载数据的逻辑
     */
    private void loadData() {
        System.out.println("加载数据...");
        if (mOnLoadListener != null) {
            // 设置加载状态，让布局显示出来
            setLoading(true);
            mOnLoadListener.onLoad();
        }

    }

    /**
     * 设置加载状态，是否加载传入boolean值进行判断
     *
     * @param loading
     */
    public void setLoading(boolean loading) {
        // 修改当前的状态
        isLoading = loading;
        if (null != mListView) {
            if (isLoading) {
                // 显示布局
//                mListView.addFooterView(mFooterView);
//                Toast.makeText(getContext(), "加载中....", Toast.LENGTH_SHORT).show();
            } else {
                // 隐藏布局
//                mListView.removeFooterView(mFooterView);
//                Toast.makeText(getContext(), "加载完毕!", Toast.LENGTH_SHORT).show();
                // 重置滑动的坐标
                mDownY = 0;
                mUpY = 0;
            }
        } else if (null != xRecyclerView) {
            if (isLoading) {
                // 显示布局
//                xRecyclerView.addView(mFooterView);
//                Toast.makeText(getContext(), "加载中....", Toast.LENGTH_SHORT).show();
            } else {
                // 隐藏布局
//                xRecyclerView.removeView(mFooterView);
//                Toast.makeText(getContext(), "加载完毕!", Toast.LENGTH_SHORT).show();
                // 重置滑动的坐标
                mDownY = 0;
                mUpY = 0;
            }

        }

    }


    /**
     * 设置ListView的滑动监听
     */
    private void setListViewOnScroll() {

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 移动过程中判断时候能下拉加载更多
                if (canLoadMore()) {
                    // 加载数据
                    loadData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 移动过程中判断时候能下拉加载更多
                if (canLoadMore()) {
                    // 加载数据
                    loadData();
                }
            }
        });
    }

    private void setRecyclerViewOnScroll() {
        xRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 移动过程中判断时候能下拉加载更多
                if (canLoadMore()) {
                    // 加载数据
                    loadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 移动过程中判断时候能下拉加载更多
                if (canLoadMore()) {
                    // 加载数据
                    loadData();
                }
            }
        });
    }

    /**
     * 上拉加载的接口回调
     */

    public interface OnLoadListener {
        void onLoad();
    }

    public void setOnLoadListener(OnLoadListener listener) {
        this.mOnLoadListener = listener;
    }
}
