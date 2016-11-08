package com.li.pro.view.fragment.rxjava;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.li.fragmentutils.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public class FragmentRxBinding extends BaseFragment {
    private Button mrb_rx_binding_click;
    private TextView tv_rx_binding_show;
    @Override
    public int ftagmentLayout() {
        return R.layout.layout_rx_binding;
    }

    @Override
    public void initView(View view) {
        mrb_rx_binding_click= (Button) view.findViewById(R.id.mrb_rx_binding_click);
        tv_rx_binding_show= (TextView) view.findViewById(R.id.tv_rx_binding_show);
        RxView.clicks(mrb_rx_binding_click).throttleFirst(5000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        tv_rx_binding_show.append("收到点击事件。\n");
                        Toast.makeText(getActivity(),""+aVoid,Toast.LENGTH_SHORT).show();
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
