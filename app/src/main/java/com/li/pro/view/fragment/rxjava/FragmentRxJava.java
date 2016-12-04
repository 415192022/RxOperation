package com.li.pro.view.fragment.rxjava;

import android.view.View;

import com.li.fragmentutils.anim.FragmentAnimator;
import com.li.fragmentutils.base.BaseLazySwipFragment;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/1 0001.
 */

public class FragmentRxJava extends BaseLazySwipFragment {
    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
//        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnicmator();
        // 设置自定义动画
        return new FragmentAnimator(R.anim.h_fragment_enter, R.anim.h_fragment_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_pop_exit);
    }

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_rxjava;
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public String setToolBarTitle() {
        return "Rx基础操作";
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }

    @Override
    public boolean isShowBackArrow() {
        return true;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }

    @Override
    protected void lazyFetchData() {

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext(null);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        //加载一个不可替换的Fragment根
                        loadRootFragment(R.id.fl_rxjava_left, new FragmentRxJavaList());
        //必须先预加载所有Fragment才能通过find找到Fragment对象
                        loadMultipleRootFragment(R.id.fl_rxjava_right, 0,
                                new FragmentRxBaseOp(),
                                new FragmentRxMap(),
                                new FragmentRxSchedu(),
                                new FragmentRxFlatMap(),
                                new FragmentRxmerge(),
                                new FragmentRxBinding(),
                                new FragmentRxFilter(),
                                new FragmentRxTakeDoNextOn(),
                                new FragmentRxInterval(),
                                new FragmentRxToSocredList()
                        );
                    }
                });
//        //加载一个可替换的Fragment根并默认加载哪个Fragment(调用Replace时必须加载,show/hide不用)
//        replaceLoadRootFragment(R.id.fl_rxjava_right, new FragmentRxBaseOp(), true);
    }
}
