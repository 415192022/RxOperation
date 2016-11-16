package com.li.pro.model.home.impl;

import com.li.pro.api.URLConst;
import com.li.pro.apiservice.home.IFragmentCAllApiService;
import com.li.pro.bean.home.BeanHomeBase;
import com.li.pro.model.home.IFragmentCAll;
import com.li.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class FragmentCAllImpl implements IFragmentCAll {
    @Override
    public Observable<BeanHomeBase> getFragmentCAll(int number, int page) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_GANK_IO_BASE, IFragmentCAllApiService.class).
                getFragmentCAll(number, page)
                ;
    }
}
