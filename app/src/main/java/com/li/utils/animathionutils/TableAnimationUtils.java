package com.li.utils.animathionutils;

import android.support.annotation.DrawableRes;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by Mingwei Li on 2016/11/26 0026.
 */

public class TableAnimationUtils {
    private static TableAnimationUtils tableAnimationUtils;

    private TableAnimationUtils() {
        if (null == fadeIn) {
            fadeIn = new AlphaAnimation(0, 1);
        }
        if (null == fadeOut) {
            fadeOut = new AlphaAnimation(1, 0);
        }
    }

    public static synchronized TableAnimationUtils getInstance() {
        if (null == tableAnimationUtils) {
            tableAnimationUtils = new TableAnimationUtils();
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
}
