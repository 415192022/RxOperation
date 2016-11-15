package com.li.pro.view.ifragment;

import com.li.pro.bean.home.BeanHomeBase;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public interface IFragmentCAllView {
    void getFragmentCAllStart();
    void getFragmentCAll(BeanHomeBase beanHomeBase);
    void getFragmentCAllComplete();
    void getFragmentCAllError();
}
