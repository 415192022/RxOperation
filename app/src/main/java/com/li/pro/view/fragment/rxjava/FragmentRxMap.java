package com.li.pro.view.fragment.rxjava;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.li.fragmentutils.base.BaseFragment;
import com.li.fragmentutils.base.BaseSwipFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public class FragmentRxMap extends BaseFragment {
    private TextView tv_rxmapshow;
    private Button btn_rxmapclick;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_rx_map;
    }

    @Override
    public void initView(View view) {
        tv_rxmapshow = (TextView) view.findViewById(R.id.tv_rxmapshow);
        view.findViewById(R.id.btn_rxmapclick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testMap();
            }
        });

        testMap();
    }

    void testMap() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Observable.from(list).map(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                tv_rxmapshow.append("字符："+s+"  ");
                return s.equals("1");
            }
        })
        .subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                tv_rxmapshow.append("状态："+aBoolean+"\n");
            }
        });
    }

    @Override
    public String setToolBarTitle() {
        return null;
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }

    @Override
    public boolean isShowBackArrow() {
        return false;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }
}
