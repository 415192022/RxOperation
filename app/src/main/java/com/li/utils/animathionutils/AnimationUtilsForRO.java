package com.li.utils.animathionutils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.li.utils.ui.widget.XFrameLayout;

/**
 * Created by Mingwei Li on 2016/11/26 0026.
 */

public class AnimationUtilsForRO {
    private static AnimationUtilsForRO tableAnimationUtils;

    private AnimationUtilsForRO() {
        if (null == fadeIn) {
            fadeIn = new AlphaAnimation(0, 1);
        }
        if (null == fadeOut) {
            fadeOut = new AlphaAnimation(1, 0);
        }
    }

    public static synchronized AnimationUtilsForRO getInstance() {
        if (null == tableAnimationUtils) {
            tableAnimationUtils = new AnimationUtilsForRO();
        }
        return tableAnimationUtils;
    }

    Animation fadeOut;
    Animation fadeIn;

    public void setImageWithFade(final ImageView img, @DrawableRes final int resId) {
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(500);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(500);
        fadeOut.setAnimationListener(new AnimationListenerAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                img.startAnimation(fadeIn);
            }
        });
        fadeIn.setAnimationListener(new AnimationListenerAdapter() {
            @Override
            public void onAnimationStart(Animation animation) {
                img.setImageResource(resId);
            }
        });
        img.startAnimation(fadeOut);
    }


    public Animator createCheckoutRevealAnimator(final XFrameLayout view, float x, float y, float startRadius, float endRadius) {
        Animator retAnimator;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            retAnimator = ViewAnimationUtils.createCircularReveal(view, (int) x, (int) y, startRadius, endRadius);
        } else {
            view.setClipOutLines(true);
            view.setClipCenter((int) x, (int) y);
            view.setClipRadius(startRadius);
            retAnimator = ObjectAnimator.ofFloat(view, "clipRadius", startRadius, endRadius);
        }
        retAnimator.setDuration(500);
        retAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setClipOutLines(false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        retAnimator.setInterpolator(new AccelerateInterpolator(2.0f));
        return retAnimator;
    }
}
