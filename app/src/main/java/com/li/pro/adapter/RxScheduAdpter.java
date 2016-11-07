package com.li.pro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.li.pro.bean.rxjava.BeanRxSchedu;

import java.util.ArrayList;
import java.util.List;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/7 0007.
 */

public class RxScheduAdpter extends RecyclerView.Adapter<RxScheduAdpter.ViewHolder> {
    private List<BeanRxSchedu> beanRxScheduList;
    private static RxScheduAdpter rxScheduAdpter;
    private Context context;

    private RxScheduAdpter() {
    }

    public synchronized static RxScheduAdpter getInstance() {
        if (null == rxScheduAdpter) {
            rxScheduAdpter = new RxScheduAdpter();
        }
        return rxScheduAdpter;
    }

    public RxScheduAdpter init(Context context) {
        if (null == beanRxScheduList) {
            beanRxScheduList = new ArrayList<>();
        }
        this.context = context;
        return this;
    }
    public void refresh(){
        notifyDataSetChanged();
    }

    public RxScheduAdpter reLoadData(BeanRxSchedu t) {
        clearAllData();
        addData(t);
        return this;
    }

    public RxScheduAdpter addData(BeanRxSchedu t) {
        if (null != beanRxScheduList) {
            beanRxScheduList.add(t);
        }
        return this;
    }

    public void clearAllData() {
        if (null != beanRxScheduList) {
            beanRxScheduList.clear();
        }
    }

    @Override
    public RxScheduAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_rx_schedu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RxScheduAdpter.ViewHolder holder, int position) {
        holder.sdv_rx_schedu_item_show.setImageURI(beanRxScheduList.get(position).getUrl());
        holder.tv_rx_schedu_item_detail.setText(beanRxScheduList.get(position).getWho());
    }

    @Override
    public int getItemCount() {
        return beanRxScheduList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView sdv_rx_schedu_item_show;
        public TextView tv_rx_schedu_item_detail;

        public ViewHolder(View itemView) {
            super(itemView);
            sdv_rx_schedu_item_show = (SimpleDraweeView) itemView.findViewById(R.id.sdv_rx_schedu_item_show);
            tv_rx_schedu_item_detail = (TextView) itemView.findViewById(R.id.tv_rx_schedu_item_detail);
        }
    }
}
