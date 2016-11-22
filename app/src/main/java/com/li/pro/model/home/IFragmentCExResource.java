package com.li.pro.model.home;

import com.li.pro.bean.home.BeanHomeBase;

import rx.Observable;

/**
 * Created by Mingwei Li on 2016/11/11 0011.
 */

public interface IFragmentCExResource {
    Observable<BeanHomeBase> getFragmentCExResource(int count, int page);
}
