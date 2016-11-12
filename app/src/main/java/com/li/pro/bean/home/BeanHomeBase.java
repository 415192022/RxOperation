package com.li.pro.bean.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class BeanHomeBase implements Serializable{
    private boolean error;
    private List<BeanHomeResults> results;

    @Override
    public String toString() {
        return "BeanHomeBase{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    public void setResults(List<BeanHomeResults> results) {
        this.results = results;
    }

    public void setError(boolean error) {

        this.error = error;
    }

    public boolean isError() {

        return error;
    }

    public List<BeanHomeResults> getResults() {
        return results;
    }
}
