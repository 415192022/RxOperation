package com.li.pro;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.li.fragmentutils.Fragmentation;
import com.li.fragmentutils.SupportActivity;
import com.li.fragmentutils.base.BaseActivity;
import com.li.fragmentutils.base.BaseSwipActivity;
import com.li.utils.ui.bottombar.BottomBar;
import com.li.utils.ui.bottombar.BottomBarTab;

import rxop.li.com.rxoperation.R;

public class ItemListActivity extends BaseActivity {

    private boolean mTwoPane;
    private CoordinatorLayout cdl_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_item_list;
    }

    private BottomBar bb_root;

    @Override
    public void doBusiness(Bundle savedInstanceState) {
        bb_root = (BottomBar) findViewById(R.id.bb_root);
        bb_root.addItem(new BottomBarTab(this, R.drawable.ic_home_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_discover_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_message_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_account_circle_white_24dp));
        bb_root.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onTabSelected(int position, int prePosition) {
                bb_root.setCurrentItem(position);
                HomeFragment homeFragment = Fragmentation.getInstance(ItemListActivity.this).findStackFragment(HomeFragment.class, getSupportFragmentManager(), true);
                Fragmentation.getInstance(ItemListActivity.this)
                        .showHideFragment(getSupportFragmentManager(), homeFragment, homeFragment);
                ((Button) homeFragment.getView().findViewById(R.id.tv_test)).setText("position：" + position + "\n prePosition" + prePosition);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
            }
        });


        //加载默认Fregment
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_home_root, new HomeFragment());
        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到可滑动关闭的Activity
                Intent intent = new Intent(ItemListActivity.this, ItemDetailActivity.class);
                startActivity(intent);
                //跳转到主页Fragment
                HomeFragment homeFragment = Fragmentation.getInstance(ItemListActivity.this).findStackFragment(HomeFragment.class, getSupportFragmentManager(), true);
                Fragmentation.getInstance(ItemListActivity.this)
                        .showHideFragment(getSupportFragmentManager(), homeFragment, homeFragment);
            }
        });

    }

    @Override
    public String setToolBarTitle() {
        return "asdsafsf";
    }

    @Override
    public boolean isSetTransparentBar() {
        return true;
    }


}
