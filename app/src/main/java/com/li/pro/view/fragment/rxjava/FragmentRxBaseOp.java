package com.li.pro.view.fragment.rxjava;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.li.fragmentutils.base.BaseFragment;
import com.li.fragmentutils.base.BaseSwipFragment;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/4 0004.
 */
public class FragmentRxBaseOp extends BaseFragment {
    TextView tv_rxbaseshow;
    Button btn_rxbasestar;

    //方式1
   public void start() {
        //建立观察者与被观察者的关系
        ob().subscribe(sb());
    }

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_rxbaseop;
    }

    @Override
    public void initView(View view) {
        tv_rxbaseshow = (TextView) view.findViewById(R.id.tv_rxbaseshow);
        btn_rxbasestar = (Button) view.findViewById(R.id.btn_rxbasestar);
        btn_rxbasestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //建立观察者与被观察者的关系
                ob().subscribe(sb());
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

    //方式1
    //创建被观察者
    public Observable ob() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                subscriber.onNext("1");
                subscriber.onNext("2");
                subscriber.onNext("3");
                subscriber.onNext("4");
                subscriber.onError(new Throwable("模拟错误"));
                subscriber.onCompleted();

            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }
    //方式1
    //创建观察者
    public Subscriber sb() {
        return new Subscriber() {
            @Override
            public void onStart() {
                super.onStart();
                tv_rxbaseshow.append("\nonStart");
            }

            @Override
            public void onCompleted() {
                tv_rxbaseshow.append("\nonCompleted");
            }

            @Override
            public void onError(Throwable e) {
                tv_rxbaseshow.append("\n" + e);
            }

            @Override
            public void onNext(Object o) {
                tv_rxbaseshow.append("\n" + o);
            }
        };
    }


}
