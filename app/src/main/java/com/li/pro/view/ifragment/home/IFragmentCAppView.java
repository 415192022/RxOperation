package com.li.pro.view.ifragment.home;

import com.li.pro.bean.home.BeanHomeResults;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public interface IFragmentCAppView {
    void getFragmentCAppStart();

    void getFragmentCApp(BeanHomeResults beanHomeResults);

    void getFragmentCAppComplete();

    void getFragmentCAppError();
}
