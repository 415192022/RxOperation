package com.li.pro.view.ifragment.home;

import com.li.pro.bean.home.BeanHomeResults;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public interface IFragmentCExResourceView {
    void getFragmentCExResourceStart();

    void getFragmentCExResource(BeanHomeResults beanHomeResults);

    void getFragmentCExResourceComplete();

    void getFragmentCExResourceError();
}
