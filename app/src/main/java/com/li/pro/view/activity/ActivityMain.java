package com.li.pro.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.li.fragmentutils.Fragmentation;
import com.li.fragmentutils.base.BaseActivity;
import com.li.pro.view.fragment.rxjava.FragmentRxJava;
import com.li.pro.view.fragment.HomeFragment;
import com.li.pro.adapter.NavRecycleViewAdapter;
import com.li.skipAnimation.main.TransitionsHeleper;
import com.li.utils.ui.bottombar.BottomBar;
import com.li.utils.ui.bottombar.BottomBarTab;

import rxop.li.com.rxoperation.R;

public class ActivityMain extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CoordinatorLayout cdl_root;
    private RecyclerView rv_nav;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private BottomBar bb_root;
    private DrawerLayout drawer;

    @Override
    public int bindLayout() {
        return R.layout.activity_item_list;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void doBusiness(Bundle savedInstanceState) {
        drawer = (DrawerLayout) findViewById(R.id.dl_nva);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        rv_nav = (RecyclerView) navigationView.
//                getHeaderView(0).
        findViewById(R.id.rv_nav);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_nav.setLayoutManager(mLayoutManager);
        NavRecycleViewAdapter.getInstance().init(this).clearAll();
        rv_nav.setAdapter(NavRecycleViewAdapter.getInstance()
                .init(this).addItem("RXJava", "开启wifi调试(需要Root)"));
        NavRecycleViewAdapter.getInstance().setOnNavRecycleViewItemClickListener(new NavRecycleViewAdapter.OnNavRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String str) {
                switch (position) {
                    case 0:
                        //RxJava
                        drawer.closeDrawers();
                        Fragmentation.getInstance(ActivityMain.this).loadRootTransaction(getSupportFragmentManager(), R.id.fl_mainroot, new FragmentRxJava());
                        break;
                }
            }
        });
        NavRecycleViewAdapter.getInstance().refresh();

        navigationView.setNavigationItemSelectedListener(this);
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
                HomeFragment homeFragment = Fragmentation.getInstance(ActivityMain.this).findStackFragment(HomeFragment.class, getSupportFragmentManager(), true);
                Fragmentation.getInstance(ActivityMain.this)
                        .showHideFragment(getSupportFragmentManager(), homeFragment, homeFragment);
                ((Button) homeFragment.getView().findViewById(R.id.tv_test)).setText("position：" + position + "\n prePosition" + prePosition);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dl_nva);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
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

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到可滑动关闭的Activity
                Intent intent = new Intent(ActivityMain.this, ItemDetailActivity.class);
                TransitionsHeleper.startActivity(ActivityMain.this, intent, fab);
                //跳转到主页Fragment
                HomeFragment homeFragment = Fragmentation.getInstance(ActivityMain.this).findStackFragment(HomeFragment.class, getSupportFragmentManager(), true);
                Fragmentation.getInstance(ActivityMain.this)
                        .showHideFragment(getSupportFragmentManager(), homeFragment, homeFragment);
            }
        });

    }

    @Override
    public String setToolBarTitle() {
        return "BaseProject";
    }

    @Override
    public boolean isSetTransparentBar() {
        return true;
    }

    @Override
    public boolean isShowBackArrow() {
        return false;
    }

    @Override
    public int setLeftCornerLogo() {
        return R.mipmap.ic_launcher;
    }

    @Override
    public String setActionBarCenterTitle() {
        return "RxJava2";
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
