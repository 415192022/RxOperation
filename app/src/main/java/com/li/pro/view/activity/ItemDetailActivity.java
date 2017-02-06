package com.li.pro.view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.li.fragmentutils.base.BaseSwipActivity;
import com.li.utils.ui.fablayout.FABRevealLayout;
import com.li.utils.ui.fablayout.OnRevealChangeListener;

import rxop.li.com.rxoperation.R;

public class ItemDetailActivity extends BaseSwipActivity {
//    private FloatingActionButton fab_action;
//    FABRevealLayout fabRevealLayout;
//    private TextView tv_click;

    // 控制ToolBar的变量
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;

    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;


    @Override
    public int bindLayout() {
        return R.layout.layout_fragment_file_trancsfer2;
    }

    private void configureFABReveal(FABRevealLayout fabRevealLayout) {
        fabRevealLayout.setOnRevealChangeListener(new OnRevealChangeListener() {
            @Override
            public void onMainViewAppeared(FABRevealLayout fabRevealLayout, View mainView) {

                fabRevealLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fabRevealLayout.revealSecondaryView();
                    }
                });
            }

            @Override
            public void onSecondaryViewAppeared(final FABRevealLayout fabRevealLayout, View secondaryView) {
                fabRevealLayout.findViewById(R.id.fab_action).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        });
    }

    ImageView main_iv_placeholder; // 大图片

    LinearLayout main_ll_title_container; // Title的LinearLayout

    FrameLayout main_fl_title; // Title的FrameLayout

    AppBarLayout main_abl_app_bar; // 整个可以滑动的AppBar

    TextView main_tv_toolbar_title; // 标题栏Title

    Toolbar main_tb_toolbar; // 工具栏

    @Override
    public void doBusiness(Bundle savedInstanceState) {
//        fabRevealLayout = (FABRevealLayout) findViewById(R.id.fab_reveal_layout);
//        configureFABReveal(fabRevealLayout);

//        main_iv_placeholder = (ImageView) findViewById(R.id.main_iv_placeholder);
//        main_ll_title_container = (LinearLayout) findViewById(R.id.main_ll_title_container);
//        main_fl_title = (FrameLayout) findViewById(R.id.main_fl_title);
//        main_abl_app_bar = (AppBarLayout) findViewById(R.id.main_abl_app_bar);
//        main_tv_toolbar_title = (TextView) findViewById(R.id.main_tv_toolbar_title);
//        main_tb_toolbar = (Toolbar) findViewById(R.id.main_tb_toolbar);
//
//        main_tb_toolbar.setTitle("");
//
//        // AppBar的监听
//        main_abl_app_bar.addOnOffsetChangedListener(
//                new AppBarLayout.OnOffsetChangedListener() {
//                    @Override
//                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                        int maxScroll = appBarLayout.getTotalScrollRange();
//                        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
//                        handleAlphaOnTitle(percentage);
//                        handleToolbarTitleVisibility(percentage);
//                    }
//                });
//
//        initParallaxValues(); // 自动滑动效果
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String setToolBarTitle() {
        return "This a Activity";
    }

    @Override
    public boolean isSetTransparentBar() {
        return true;
    }

    @Override
    public boolean isShowBackArrow() {
        return true;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }

    @Override
    public String setActionBarCenterTitle() {
        return "sssss";
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }

    @Override
    public boolean isSetSwipBack() {
        return true;
    }


    // 设置自动滑动的动画效果
    private void initParallaxValues() {
        CollapsingToolbarLayout.LayoutParams petDetailsLp =
                (CollapsingToolbarLayout.LayoutParams) main_iv_placeholder.getLayoutParams();

        CollapsingToolbarLayout.LayoutParams petBackgroundLp =
                (CollapsingToolbarLayout.LayoutParams) main_fl_title.getLayoutParams();

        petDetailsLp.setParallaxMultiplier(0.9f);
        petBackgroundLp.setParallaxMultiplier(0.3f);

        main_iv_placeholder.setLayoutParams(petDetailsLp);
        main_fl_title.setLayoutParams(petBackgroundLp);
    }

    // 处理ToolBar的显示
    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(main_tv_toolbar_title, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(main_tv_toolbar_title, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    // 控制Title的显示
    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(main_ll_title_container, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(main_ll_title_container, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    // 设置渐变的动画
    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
