package com.li.pro.present.home;

import com.li.pro.bean.home.BeanHomeBase;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.pro.model.home.impl.FragmentCAppImpl;
import com.li.pro.view.ifragment.home.IFragmentCAppView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/15 0015.
 */

public class FragmentCAppPrecent {
    private static FragmentCAppPrecent fragmentCAppPrecent;
    private FragmentCAppImpl fragmentCAllimpl;
    private IFragmentCAppView fragmentCAppView;

    private FragmentCAppPrecent() {
    }

    public synchronized static FragmentCAppPrecent getInstance() {
        if (null == fragmentCAppPrecent) {
            fragmentCAppPrecent = new FragmentCAppPrecent();
        }
        return fragmentCAppPrecent;
    }

    public FragmentCAppPrecent with(IFragmentCAppView iFragmentCAppView) {
        fragmentCAllimpl = new FragmentCAppImpl();
        this.fragmentCAppView = iFragmentCAppView;
        return this;
    }

    /**
     * 获得All分类内容
     *
     * @param count
     * @param page
     */
    public void getFragmentCAppData(int count, int page) {
        fragmentCAllimpl.
                getFragmentCApp(count, page).
                unsubscribeOn(Schedulers.io()).
                flatMap(new Func1<BeanHomeBase, Observable<BeanHomeResults>>() {
                    @Override
                    public Observable<BeanHomeResults> call(BeanHomeBase beanHomeBase) {
                        return Observable.from(beanHomeBase.getResults());
                    }
                }).
                map(new Func1<BeanHomeResults, BeanHomeResults>() {
                    @Override
                    public BeanHomeResults call(BeanHomeResults beanHomeResults) {
                        return beanHomeResults;
                    }
                }).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<BeanHomeResults>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        fragmentCAppView.getFragmentCAppStart();
                    }

                    @Override
                    public void onCompleted() {
                        fragmentCAppView.getFragmentCAppComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentCAppView.getFragmentCAppError();
                    }

                    @Override
                    public void onNext(BeanHomeResults beanHomeResults) {
                        fragmentCAppView.getFragmentCApp(beanHomeResults);
                    }
                })
        ;
    }
}
