package com.li.pro.bean.rxjava;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class BeanFlatMapClass {
    private String className;
    private int studentCount;
    private List<BeanFlatMapStudent> beanFlatMapStudentList;

    public void setBeanFlatMapStudentList(List<BeanFlatMapStudent> beanFlatMapStudentList) {
        this.beanFlatMapStudentList = beanFlatMapStudentList;
    }

    public List<BeanFlatMapStudent> getBeanFlatMapStudentList() {

        return beanFlatMapStudentList;
    }

    @Override
    public String toString() {
        return "BeanFlatMapClass{" +
                "className='" + className + '\'' +
                ", studentCount=" + studentCount +
                '}';
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getClassName() {

        return className;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
