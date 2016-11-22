package com.li.pro.present.home;

import com.li.pro.bean.home.BeanHomeBase;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.pro.model.home.impl.FragmentCExResourceImpl;
import com.li.pro.view.ifragment.home.IFragmentCExResourceView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Mingwei Li on 2016/11/15 0015.
 */

public class FragmentCExResourcePrecent {
    private static FragmentCExResourcePrecent fragmentCAppPrecent;
    private FragmentCExResourceImpl fragmentCExResource;
    private IFragmentCExResourceView fragmentCExResourceView;

    private FragmentCExResourcePrecent() {
    }

    public synchronized static FragmentCExResourcePrecent getInstance() {
        if (null == fragmentCAppPrecent) {
            fragmentCAppPrecent = new FragmentCExResourcePrecent();
        }
        return fragmentCAppPrecent;
    }

    public FragmentCExResourcePrecent with(IFragmentCExResourceView iFragmentCExResourceView) {
        fragmentCExResource = new FragmentCExResourceImpl();
        this.fragmentCExResourceView = iFragmentCExResourceView;
        return this;
    }

    /**
     * 获得All分类内容
     *
     * @param count
     * @param page
     */
    public void getFragmentCExResourceData(int count, int page) {
        fragmentCExResource.
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
                        fragmentCExResourceView.getFragmentCExResourceStart();
                    }

                    @Override
                    public void onCompleted() {
                        fragmentCExResourceView.getFragmentCExResourceComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentCExResourceView.getFragmentCExResourceError();
                    }

                    @Override
                    public void onNext(BeanHomeResults beanHomeResults) {
                        fragmentCExResourceView.getFragmentCExResource(beanHomeResults);
                    }
                })
        ;
    }
}
