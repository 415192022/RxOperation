package com.li.pro.model.impl;

import com.li.pro.api.URLConst;
import com.li.pro.apiservice.IFragmentCAllApiService;
import com.li.pro.bean.home.BeanHomeBase;
import com.li.pro.model.IFragmentCAll;
import com.li.utils.RetrofitUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class FragmentCAllImpl implements IFragmentCAll {
    @Override
    public Observable<BeanHomeBase> getFragmentCAll(int number, int page) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_GANK_IO_BASE, IFragmentCAllApiService.class).
                getFragmentCAll(number, page).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                ;
    }
}
