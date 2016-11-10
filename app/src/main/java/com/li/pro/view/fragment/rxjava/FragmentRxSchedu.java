package com.li.pro.view.fragment.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.li.fragmentutils.base.BaseFragment;
import com.li.fragmentutils.base.BaseLazyFragment;
import com.li.pro.adapter.RxScheduAdpter;
import com.li.pro.api.URLConst;
import com.li.pro.bean.rxjava.BeanRxSchedu;
import com.li.pro.bean.rxjava.BeanRxScheduBase;
import com.li.utils.ui.preload.PreLoader;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/4 0004.
 * okhttp + RxJava
 */

public class FragmentRxSchedu extends BaseLazyFragment {
    private RecyclerView rv_rxschedushow;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_rx_schedu;
    }

    @Override
    public void initView(View view) {
        rv_rxschedushow = (RecyclerView) view.findViewById(R.id.rv_rxschedushow);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        rv_rxschedushow.setLayoutManager(glm);
        rv_rxschedushow.hasFixedSize();
        rv_rxschedushow.setAdapter(RxScheduAdpter.getInstance().init(getActivity()));
        RxScheduDemo();
    }

    /**
     * 线程调度Demo
     */
    public void RxScheduDemo() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(URLConst.URL_GANK_IO_BASE + "福利/20/1").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        subscriber.onNext(response.body().string());
                    }
                });
            }
        })
                .flatMap(new Func1<String, Observable<BeanRxSchedu>>() {
                    @Override
                    public Observable<BeanRxSchedu> call(String s) {
                        return Observable.from(new Gson().fromJson(s, BeanRxScheduBase.class).getResults());
                    }
                })
                .map(new Func1<BeanRxSchedu, BeanRxSchedu>() {
                    @Override
                    public BeanRxSchedu call(BeanRxSchedu beanRxSchedu) {
                        return beanRxSchedu;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BeanRxSchedu>() {
                    @Override
                    public void call(BeanRxSchedu beanRxSchedu) {
                        RxScheduAdpter.getInstance().addData(beanRxSchedu).refresh();
                    }
                })
        ;
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
    protected void initLazyView(@Nullable Bundle savedInstanceState) {

    }
}
