package com.li.pro.model.home;

import com.li.pro.bean.home.BeanHomeBase;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public interface IFragmentCApp {
    Observable<BeanHomeBase> getFragmentCApp(int count, int page);
}
