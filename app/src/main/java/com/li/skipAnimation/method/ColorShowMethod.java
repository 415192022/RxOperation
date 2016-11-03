package com.li.skipAnimation.method;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.li.skipAnimation.bean.InfoBean;
import com.li.skipAnimation.view.RenderView;

import rxop.li.com.rxoperation.R;


/**
 * Created by Mr_immortalZ on 2016/10/24.
 * email : mr_immortalz@qq.com
 */

public abstract class ColorShowMethod extends ShowMethod {

    protected int startColor;
    public int endColor;

    public ColorShowMethod(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    @Override
    public void translate(InfoBean bean, RenderView parent, View child) {
        if (startColor != 0) {
            startColor = parent.getResources().getColor(startColor);
        } else {
            startColor = parent.getResources().getColor(R.color.showmethod_start_color);
        }

        if (endColor != 0) {
            endColor = parent.getResources().getColor(endColor);
        } else {
            endColor = parent.getResources().getColor(R.color.showmethod_end_color);
        }
        parent.setPaintColor(endColor);
        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(parent, "backgroundColor", startColor, endColor);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        set.playTogether(
                ObjectAnimator.ofFloat(child, "translationX", 0, -bean.translationX),
                ObjectAnimator.ofFloat(child, "translationY", 0, -bean.translationY),
                ObjectAnimator.ofFloat(child, "scaleX", 1),
                ObjectAnimator.ofFloat(child, "scaleY", 1),
                colorAnimator
        );
        set.setInterpolator(new AccelerateInterpolator());
        set.setDuration(duration).start();
    }


}
