package com.li.pro.view.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.li.fragmentutils.base.BaseSwipActivity;
import com.li.utils.ui.pathanim.PathAnimationDemo;

import rxop.li.com.rxoperation.R;

public class ItemDetailActivity extends BaseSwipActivity {
    @Override
    public int bindLayout() {
        return R.layout.activity_item_detail;
    }


    @Override
    public void doBusiness(Bundle savedInstanceState) {
        final PathAnimationDemo view = (PathAnimationDemo) findViewById(R.id.animated_path);
//        ViewTreeObserver observer = view.getViewTreeObserver();
//        if (observer != null) {
//            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//
//                    float[][] points = new float[][]{
//                            {0, 0},
//                            {view.getWidth(), 0},
//                            {view.getWidth(), view.getHeight()},
//                            {0, view.getHeight()},
//                            {0, 0},
//                            {view.getWidth(), view.getHeight()},
//                            {view.getWidth(), 0},
//                            {0, view.getHeight()}
//                    };
//                    view.setPath(points);
//                }
//            });
//        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(view, "percentage", 0.0f, 1.0f);
                anim.setDuration(5000);
                anim.setInterpolator(new LinearInterpolator());
                anim.start();
            }
        });

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
        return null;
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }

    @Override
    public boolean isSetSwipBack() {
        return false;
    }

}
