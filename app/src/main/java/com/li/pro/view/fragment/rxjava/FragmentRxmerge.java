package com.li.pro.view.fragment.rxjava;

import android.view.View;
import android.widget.TextView;

import com.li.fragmentutils.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/4 0004.
 * Rx merge操作符  用于数据合并
 */

public class FragmentRxmerge extends BaseFragment {
    private TextView tv_rx_merge_show;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_rx_merge;
    }

    @Override
    public void initView(View view) {
        tv_rx_merge_show = (TextView) view.findViewById(R.id.tv_rx_merge_show);
        initData();
        mergeDemo();
    }

    List<String> list1;
    List<String> list2;

    //初始化数据
    public void initData() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i + " List1");
        }
        for (int i = 0; i < 5; i++) {
            list2.add(i + "List2");
        }
    }
    //创建Observable对象
    public Observable o1() {
        return Observable.from(list1).subscribeOn(Schedulers.io());
    }
    //创建Observable对象
    public Observable o2() {
        return Observable.from(list2).subscribeOn(Schedulers.io());
    }

    /**
     * Rx merge操作符用法
     */
    public void mergeDemo() {
        Observable.merge(o1(), o2())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        tv_rx_merge_show.append(o + "\n");
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
