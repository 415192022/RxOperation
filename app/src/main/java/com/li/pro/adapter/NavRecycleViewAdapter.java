package com.li.pro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/1 0001.
 */

public class NavRecycleViewAdapter extends RecyclerView.Adapter<NavRecycleViewAdapter.ViewHolder> implements View.OnClickListener {
    private List<String> list = null;
    private static NavRecycleViewAdapter navRecycleViewAdapter = null;
    private Context context;

    private NavRecycleViewAdapter() {
    }

    public static synchronized NavRecycleViewAdapter getInstance() {
        if (null == navRecycleViewAdapter) {
            navRecycleViewAdapter = new NavRecycleViewAdapter();
        }
        return navRecycleViewAdapter;
    }

    public NavRecycleViewAdapter init(Context context) {
        if (null == list) {
            list = new ArrayList<>();
        }
        this.context = context;
        return this;
    }

    public NavRecycleViewAdapter addItem(String... item) {
        for(String str:item){
            list.add(str);
        }
        return this;
    }
    public void clearAll(){
        list.clear();
    }
    public NavRecycleViewAdapter refresh() {
        notifyDataSetChanged();
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_nav, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_nav_item.setText(list.get(position) + "");
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nav_item;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_nav_item = (TextView) itemView.findViewById(R.id.tv_nav_item);
        }
    }

    @Override
    public void onClick(View v) {
        if(null != onNavRecycleViewItemClickListener){
            int pst=(int) v.getTag();
            onNavRecycleViewItemClickListener.onItemClick(v, pst,list.get(pst));
        }
    }
    private OnNavRecycleViewItemClickListener onNavRecycleViewItemClickListener;
    public void setOnNavRecycleViewItemClickListener(OnNavRecycleViewItemClickListener onNavRecycleViewItemClickListener){
        this.onNavRecycleViewItemClickListener=onNavRecycleViewItemClickListener;
    }
    public interface OnNavRecycleViewItemClickListener{
        void onItemClick(View view,int position,String str);
    }


}
