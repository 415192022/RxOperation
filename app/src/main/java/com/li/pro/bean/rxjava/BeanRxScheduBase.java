package com.li.pro.bean.rxjava;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class BeanRxScheduBase {
    private boolean error;
    private List<BeanRxSchedu> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<BeanRxSchedu> results) {
        this.results = results;
    }

    public boolean isError() {

        return error;
    }

    public List<BeanRxSchedu> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "BeanRxScheduBase{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
