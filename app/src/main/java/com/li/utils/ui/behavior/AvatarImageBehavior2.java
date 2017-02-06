package com.li.utils.ui.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.li.utils.ui.progressbar.FreshDownloadView;

import rxop.li.com.rxoperation.R;


@SuppressWarnings("unused")
public class AvatarImageBehavior2 extends CoordinatorLayout.Behavior<FreshDownloadView> {

    private final static float MIN_AVATAR_PERCENTAGE_SIZE = 0.3f;
    private final static int EXTRA_FINAL_AVATAR_PADDING = 80;

    private final static String TAG = "behavior";
    private Context mContext;

    private float mCustomFinalYPosition;
    private float mCustomStartXPosition;
    private float mCustomStartToolbarPosition;
    private float mCustomStartHeight;
    private float mCustomFinalHeight;

    private float mAvatarMaxSize;
    private float mFinalLeftAvatarPadding;
    private float mStartPosition;
    private int mStartXPosition;
    private float mStartToolbarPosition;
    private int mStartYPosition;
    private int mFinalYPosition;
    private int mStartHeight;
    private int mFinalXPosition;
    private float mChangeBehaviorPoint;

    public AvatarImageBehavior2(Context context, AttributeSet attrs) {
        mContext = context;

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageBehavior);
            mCustomFinalYPosition = a.getDimension(R.styleable.AvatarImageBehavior_finalYPosition, 0);
            mCustomStartXPosition = a.getDimension(R.styleable.AvatarImageBehavior_startXPosition, 0);
            mCustomStartToolbarPosition = a.getDimension(R.styleable.AvatarImageBehavior_startToolbarPosition, 0);
            mCustomStartHeight = a.getDimension(R.styleable.AvatarImageBehavior_startHeight, 0);
            mCustomFinalHeight = a.getDimension(R.styleable.AvatarImageBehavior_finalHeight, 0);

            a.recycle();
        }

        init();

        mFinalLeftAvatarPadding = context.getResources().getDimension(
                R.dimen.spacing_normal);
    }

    private void init() {
        bindDimensions();
    }

    private void bindDimensions() {
        mAvatarMaxSize = mContext.getResources().getDimension(R.dimen.image_width);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FreshDownloadView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FreshDownloadView child, View dependency) {
        maybeInitProperties(child, dependency);

        final int maxScrollDistance = (int) (mStartToolbarPosition);
        float expandedPercentageFactor = dependency.getY() / maxScrollDistance;

        if (expandedPercentageFactor < mChangeBehaviorPoint) {
            float heightFactor = (mChangeBehaviorPoint - expandedPercentageFactor) / mChangeBehaviorPoint;

            float distanceXToSubtract = ((mStartXPosition - mFinalXPosition)
                    * heightFactor) + (child.getHeight() / 2);
            float distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + (child.getHeight() / 2);
            Log.e("mStartXPosition distanceXToSubtract ", (mStartXPosition - distanceXToSubtract) + "");
            if ((mStartXPosition - distanceXToSubtract) > -160) {
                child.setX(mStartXPosition - distanceXToSubtract);
            }
            if ((mStartYPosition - distanceYToSubtract) > -125) {
                child.setY(mStartYPosition - distanceYToSubtract);
            }
            if (((mStartXPosition - distanceXToSubtract) / 400) > 0.3) {
                child.setScaleX((mStartXPosition - distanceXToSubtract) / 400);
                child.setScaleY((mStartXPosition - distanceXToSubtract) / 400);
            }

            float heightToSubtract = ((mStartHeight - mCustomFinalHeight) * heightFactor);

//            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//            lp.width = (int) (mStartHeight - heightToSubtract);
//            lp.height = (int) (mStartHeight - heightToSubtract);
//            child.setLayoutParams(lp);
        } else {
            float distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + (mStartHeight / 2);

            child.setX(mStartXPosition - child.getWidth() / 2);
            child.setY(mStartYPosition - distanceYToSubtract);

//            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//            lp.width = (int) (mStartHeight);
//            lp.height = (int) (mStartHeight);
//            child.setLayoutParams(lp);
        }
        return true;
    }

    private void maybeInitProperties(FreshDownloadView child, View dependency) {
        if (mStartYPosition == 0)
            mStartYPosition = (int) (dependency.getY());

        if (mFinalYPosition == 0)
            mFinalYPosition = (dependency.getHeight() / 2);

        if (mStartHeight == 0)
            mStartHeight = child.getHeight();

        if (mStartXPosition == 0)
            mStartXPosition = (int) (child.getX() + (child.getWidth() / 2));

        if (mFinalXPosition == 0)
            mFinalXPosition = mContext.getResources().getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + ((int) mCustomFinalHeight / 2);

        if (mStartToolbarPosition == 0)
            mStartToolbarPosition = dependency.getY();

        if (mChangeBehaviorPoint == 0) {
            mChangeBehaviorPoint = (child.getHeight() - mCustomFinalHeight) / (2f * (mStartYPosition - mFinalYPosition));
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
