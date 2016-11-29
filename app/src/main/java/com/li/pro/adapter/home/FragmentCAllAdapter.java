package com.li.pro.adapter.home;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.li.fragmentutils.Fragmentation;
import com.li.fragmentutils.SupportActivity;
import com.li.pro.bean.home.BeanHomeResults;
import com.li.pro.view.fragment.home.FragmentCAdapterDetails;
import com.li.utils.ui.preload.PreLoader;

import java.util.ArrayList;
import java.util.List;

import rxop.li.com.rxoperation.R;


/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class FragmentCAllAdapter extends RecyclerView.Adapter<FragmentCAllAdapter.ViewHolder> {
    private List<BeanHomeResults> beanHomeResultses = new ArrayList<>();
    private Context context;
    private static FragmentCAllAdapter fragmentCAllAdapter;
    private ObjectAnimator objectAnimator;

    private FragmentCAllAdapter() {
    }

    public static synchronized FragmentCAllAdapter getInstance() {
        if (null == fragmentCAllAdapter) {
            fragmentCAllAdapter = new FragmentCAllAdapter();
        }
        return fragmentCAllAdapter;
    }

    public static FragmentCAllAdapter newInstance() {
        return new FragmentCAllAdapter();
    }

    public FragmentCAllAdapter init(Context context) {
        this.context = context;
        objectAnimator = new ObjectAnimator();
        objectAnimator.setDuration(200);
        objectAnimator.setPropertyName("alpha");
        return this;
    }

    public FragmentCAllAdapter addData(BeanHomeResults beanHomeResults) {
        beanHomeResultses.add(beanHomeResults);
        return this;
    }

    public FragmentCAllAdapter clearAllData() {
        beanHomeResultses.clear();
        return this;
    }

    public FragmentCAllAdapter refresh() {
        notifyDataSetChanged();
        return this;
    }

    @Override
    public FragmentCAllAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PreLoader.getInstance(context).on(parent);
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_fragment_c_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(FragmentCAllAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentCAdapterDetails fragmentCAdapterDetails = new FragmentCAdapterDetails();
                Bundle bundle = new Bundle();
                bundle.putString("URL", beanHomeResultses.get(position).getUrl());
                fragmentCAdapterDetails.setArguments(bundle);
                fragmentCAdapterDetails.startInitAnimation((SupportActivity) context, v, R.id.fl_mainroot);
                Fragmentation.getInstance((SupportActivity) context).loadRootTransaction(((SupportActivity) context).getSupportFragmentManager(), R.id.fl_mainroot, fragmentCAdapterDetails);
            }
        });
//        objectAnimator.setFloatValues(0f,1f);
//        objectAnimator.setTarget(holder.itemView);
//        objectAnimator.start();
        if (null != beanHomeResultses.get(position).getImages() && null != beanHomeResultses.get(position) && null != beanHomeResultses && beanHomeResultses.get(position).getImages().size() > 0) {
            DraweeController draweeController = Fresco.newDraweeControllerBuilder().
                    setAutoPlayAnimations(true).
                    setUri(beanHomeResultses.get(position).getImages().get(0)).
                    build();
            holder.sdv_fragment_c_all.setController(draweeController);
        }
        holder.tv_fragment_c_all_title.setText(beanHomeResultses.get(position).getDesc());
        holder.tv_fragment_c_all_authro.setText(beanHomeResultses.get(position).getWho());
        holder.tv_fragment_c_all_date.setText(beanHomeResultses.get(position).getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return beanHomeResultses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public SimpleDraweeView sdv_fragment_c_all;
        public TextView tv_fragment_c_all_title;
        public TextView tv_fragment_c_all_authro;
        public TextView tv_fragment_c_all_date;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            sdv_fragment_c_all = (SimpleDraweeView) itemView.findViewById(R.id.sdv_fragment_c_all);
            tv_fragment_c_all_title = (TextView) itemView.findViewById(R.id.tv_fragment_c_all_title);
            tv_fragment_c_all_authro = (TextView) itemView.findViewById(R.id.tv_fragment_c_all_authro);
            tv_fragment_c_all_date = (TextView) itemView.findViewById(R.id.tv_fragment_c_all_date);
        }
    }
}
