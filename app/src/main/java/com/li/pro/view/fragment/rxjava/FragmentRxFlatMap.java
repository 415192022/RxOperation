package com.li.pro.view.fragment.rxjava;

import android.view.View;
import android.widget.TextView;

import com.li.fragmentutils.base.BaseFragment;
import com.li.fragmentutils.base.BaseSwipFragment;
import com.li.pro.bean.rxjava.BeanFlatMapClass;
import com.li.pro.bean.rxjava.BeanFlatMapSchool;
import com.li.pro.bean.rxjava.BeanFlatMapStudent;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/4 0004.
 * Rx FlatMap 操作符的用法
 */

public class FragmentRxFlatMap extends BaseFragment {
    private TextView tv_rx_flatmap_show;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_rx_flatmap;
    }

    @Override
    public void initView(View view) {
        tv_rx_flatmap_show = (TextView) view.findViewById(R.id.tv_rx_flatmap_show);
        initData();
        rxFlatMapDemo();
    }

    //学生集合
    List<BeanFlatMapStudent> beanFlatMapStudentList;
    //班级集合
    List<BeanFlatMapClass> beanFlatMapClassList;
    //学校
    BeanFlatMapSchool beanFlatMapSchool;

    //初始化模拟数据
    public void initData() {
        beanFlatMapStudentList = new ArrayList<>();
        //创建学生集合
        for (int j = 0; j < 20; j++) {
            BeanFlatMapStudent beanFlatMapStudent = new BeanFlatMapStudent();
            beanFlatMapStudent.setName("学生" + j);
            beanFlatMapStudent.setAge((new Random().nextInt(20) < 18) ? 18 : 21);
            beanFlatMapStudentList.add(beanFlatMapStudent);
        }

        beanFlatMapClassList = new ArrayList<>();
        //创建班级集合
        for (int i = 0; i < 20; i++) {
            BeanFlatMapClass beanFlatMapClass = new BeanFlatMapClass();
            beanFlatMapClass.setClassName(i + "班");
            beanFlatMapClass.setStudentCount(i + new Random().nextInt(40));
            beanFlatMapClassList.add(beanFlatMapClass);
            beanFlatMapClass.setBeanFlatMapStudentList(beanFlatMapStudentList);
        }
        beanFlatMapSchool = new BeanFlatMapSchool();
        beanFlatMapSchool.setSchoolName("河南科技大学");
        beanFlatMapSchool.setBeanFlatMapClassList(beanFlatMapClassList);
    }

    /**
     * flatmap 操作符
     */
    public void rxFlatMapDemo() {
        tv_rx_flatmap_show.append(beanFlatMapSchool.getSchoolName() + "\n");
        Observable.from(beanFlatMapSchool.getBeanFlatMapClassList())
                .flatMap(new Func1<BeanFlatMapClass, Observable<BeanFlatMapStudent>>() {
                    @Override
                    public Observable<BeanFlatMapStudent> call(BeanFlatMapClass beanFlatMapClass) {
                        tv_rx_flatmap_show.append(beanFlatMapClass.getClassName() + ":\n");
                        return Observable.from(beanFlatMapClass.getBeanFlatMapStudentList());
                    }
                })
                .map(new Func1<BeanFlatMapStudent, BeanFlatMapStudent>() {
                    @Override
                    public BeanFlatMapStudent call(BeanFlatMapStudent beanFlatMapStudent) {
                        return beanFlatMapStudent;
                    }
                })
                .subscribe(new Action1<BeanFlatMapStudent>() {
                    @Override
                    public void call(BeanFlatMapStudent beanFlatMapStudent) {
                        tv_rx_flatmap_show.append(" 姓名:" + beanFlatMapStudent.getName() + " 年龄:" + beanFlatMapStudent.getAge() + "\n");
                    }
                })
        ;
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
