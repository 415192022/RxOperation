package com.li.pro.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.li.fragmentutils.SupportFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class FragmentHomeVPAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private List<String> titles;
    private List<SupportFragment> supportFragments;
    private static FragmentHomeVPAdapter fragmentHomeVPAdapter;
    public static synchronized FragmentHomeVPAdapter getInstance(Context context,FragmentManager fm){
        if(null == fragmentHomeVPAdapter){
            fragmentHomeVPAdapter=new FragmentHomeVPAdapter(context,fm);
        }
        return fragmentHomeVPAdapter;
    }
    private FragmentHomeVPAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    public FragmentHomeVPAdapter init(){
        if(null == titles){
            titles=new ArrayList<>();
        }
        if(null == supportFragments){
            supportFragments=new ArrayList<>();
        }
        return this;
    }

    public FragmentHomeVPAdapter addTitle(String... strings){
        for (String s: strings){
            titles.add(s);
        }
        return this;
    }

    public FragmentHomeVPAdapter addFragments(SupportFragment... supportFragment){
        for (SupportFragment fragment:supportFragment){
            supportFragments.add(fragment);
        }
        return this;
    }


    @Override
    public Fragment getItem(int position) {
        return supportFragments.get(position);
    }

    @Override
    public int getCount() {
        return supportFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
