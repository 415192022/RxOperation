package com.li.pro.present;

import com.li.pro.bean.home.BeanHomeBase;
import com.li.pro.model.impl.FragmentCAllImpl;
import com.li.pro.view.ifragment.IFragmentCAllView;

import rx.functions.Action1;

/**
 * Created by Mingwei Li on 2016/11/11 0011.
 */

public class FragmentCAllPrecent {
    private FragmentCAllImpl fragmentCAllimpl;
    private IFragmentCAllView iFragmentCAllView;
    private static FragmentCAllPrecent fragmentCAllPrecent;

    private FragmentCAllPrecent() {
    }

    public synchronized static FragmentCAllPrecent getInstance() {
        if (null == fragmentCAllPrecent) {
            fragmentCAllPrecent = new FragmentCAllPrecent();
        }
        return fragmentCAllPrecent;
    }

    public FragmentCAllPrecent with(IFragmentCAllView iFragmentCAllView) {
        fragmentCAllimpl = new FragmentCAllImpl();
        this.iFragmentCAllView = iFragmentCAllView;
        return this;
    }

    /**
     * 获得All分类内容
     *
     * @param count
     * @param page
     */
    public void getFragmentCAllData(int count, int page) {
        fragmentCAllimpl.
                getFragmentCAll(count, page).
                subscribe(new Action1<BeanHomeBase>() {
                    @Override
                    public void call(BeanHomeBase beanHomeBase) {
                        iFragmentCAllView.getFragmentCAll(beanHomeBase);
                    }
                })
        ;
    }

}
