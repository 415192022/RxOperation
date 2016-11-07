package com.li.pro.bean.rxjava;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class BeanFlatMapSchool {
    private String schoolName;
    private List<BeanFlatMapClass> beanFlatMapClassList;

    public List<BeanFlatMapClass> getBeanFlatMapClassList() {
        return beanFlatMapClassList;
    }

    public void setBeanFlatMapClassList(List<BeanFlatMapClass> beanFlatMapClassList) {
        this.beanFlatMapClassList = beanFlatMapClassList;
    }

    @Override
    public String toString() {
        return "BeanFlatMapSchool{" +
                "schoolName='" + schoolName + '\'' +
                '}';
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {

        return schoolName;
    }
}
