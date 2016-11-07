package com.li.pro.bean.rxjava;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class BeanFlatMapStudent {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "BeanFlatMapStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public int getAge() {
        return age;
    }
}
