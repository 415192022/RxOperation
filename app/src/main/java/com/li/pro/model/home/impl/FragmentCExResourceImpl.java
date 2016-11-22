package com.li.pro.model.home.impl;

import com.li.pro.api.URLConst;
import com.li.pro.apiservice.home.IFragmentCExResourceApiService;
import com.li.pro.bean.home.BeanHomeBase;
import com.li.pro.model.home.IFragmentCApp;
import com.li.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class FragmentCExResourceImpl implements IFragmentCApp {
    @Override
    public Observable<BeanHomeBase> getFragmentCApp(int number, int page) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_GANK_IO_BASE, IFragmentCExResourceApiService.class).
                getFragmentCExResource(number, page)
                ;
    }
}
