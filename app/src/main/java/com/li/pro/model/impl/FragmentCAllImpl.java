package com.li.pro.model.impl;

import com.li.pro.api.URLConst;
import com.li.pro.bean.home.BeanHomeBase;
import com.li.pro.model.IFragmentCAll;
import com.li.utils.RetrofitUtils;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class FragmentCAllImpl implements IFragmentCAll {
    @Override
    public Observable<BeanHomeBase> getFragmentCAll(@Path("number") int number, @Path("page") int page) {
        return RetrofitUtils.
                getInstance().
                getRetrofit(URLConst.URL_GANK_IO_BASE).
                create(IFragmentCAll.class).
                getFragmentCAll(number, page)
                ;
    }
}
