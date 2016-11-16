package com.li.pro.view.ifragment.home;

import com.li.pro.bean.home.BeanHomeResults;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public interface IFragmentCAllView {
    void getFragmentCAllStart();

    void getFragmentCAll(BeanHomeResults beanHomeResults);

    void getFragmentCAllComplete();

    void getFragmentCAllError();
}
