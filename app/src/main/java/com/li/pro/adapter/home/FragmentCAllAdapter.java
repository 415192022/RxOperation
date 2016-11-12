package com.li.pro.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.li.pro.bean.home.BeanHomeResults;

import java.util.ArrayList;
import java.util.List;

import rxop.li.com.rxoperation.R;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class FragmentCAllAdapter extends RecyclerView.Adapter<FragmentCAllAdapter.ViewHolder> implements View.OnClickListener{
    private List<BeanHomeResults> beanHomeResultses = new ArrayList<>();
    private Context context;
    private static FragmentCAllAdapter fragmentCAllAdapter;

    private FragmentCAllAdapter(Context context) {
        this.context = context;
    }

    public static FragmentCAllAdapter newInstance(Context context) {
        return fragmentCAllAdapter = new FragmentCAllAdapter(context);
    }

    public FragmentCAllAdapter addData(BeanHomeResults beanHomeResults) {
        beanHomeResultses.add(beanHomeResults);
        notifyDataSetChanged();
        return fragmentCAllAdapter;
    }

    public FragmentCAllAdapter clearAll() {
        beanHomeResultses.clear();
        notifyDataSetChanged();
        return fragmentCAllAdapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fragment_c_all,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DraweeController draweeController= Fresco.newDraweeControllerBuilder()
                .setUri(beanHomeResultses.get(position).getImages().get(0))
                .setAutoPlayAnimations(true)
                .build();
        holder.sdv_fragment_c_all.setController(draweeController);
        holder.tv_fragment_c_all_title.setText(beanHomeResultses.get(position).getDesc());
        holder.tv_fragment_c_all_authro.setText(beanHomeResultses.get(position).getWho());
        holder.tv_fragment_c_all_date.setText(beanHomeResultses.get(position).getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return beanHomeResultses.size();
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView sdv_fragment_c_all;
        private TextView tv_fragment_c_all_title;
        private TextView tv_fragment_c_all_authro;
        private TextView tv_fragment_c_all_date;
        public ViewHolder(View itemView) {
            super(itemView);
            sdv_fragment_c_all = (SimpleDraweeView) itemView.findViewById(R.id.sdv_fragment_c_all);
            tv_fragment_c_all_title = (TextView) itemView.findViewById(R.id.tv_fragment_c_all_title);
            tv_fragment_c_all_authro = (TextView) itemView.findViewById(R.id.tv_fragment_c_all_authro);
            tv_fragment_c_all_date = (TextView) itemView.findViewById(R.id.tv_fragment_c_all_date);
        }
    }
}
