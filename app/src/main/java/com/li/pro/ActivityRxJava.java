package com.li.pro;

import android.os.Bundle;

import com.li.fragmentutils.base.BaseSwipActivity;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class ActivityRxJava extends BaseSwipActivity {
    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void doBusiness(Bundle savedInstanceState) {

    }

    @Override
    public String setToolBarTitle() {
        return null;
    }

    @Override
    public boolean isSetTransparentBar() {
        return true;
    }

    @Override
    public boolean isSetSwipBack() {
        return true;
    }
}
