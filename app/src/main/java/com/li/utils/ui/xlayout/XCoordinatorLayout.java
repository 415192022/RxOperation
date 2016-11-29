package com.li.utils.ui.xlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Mingwei Li on 2016/11/28 0028.
 */

public class XCoordinatorLayout extends FrameLayout implements IXLayout {
    private Path mRevealPath;
    boolean mClipOutlines;
    float mCenterX;
    float mCenterY;
    float mRadius;

    public XCoordinatorLayout(Context context) {
        super(context);
        init();
    }

    public XCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRevealPath = new Path();
        mClipOutlines = false;
        setWillNotDraw(false);
    }


    @Override
    public void draw(Canvas canvas) {
        if (!mClipOutlines) {
            super.draw(canvas);
            return;
        }
        final int state = canvas.save();
        mRevealPath.reset();
        mRevealPath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW);
        canvas.clipPath(mRevealPath);
        super.draw(canvas);
        canvas.restoreToCount(state);
    }

    @Override
    public void setClipOutLines(boolean shouldClip) {
        mClipOutlines = shouldClip;
    }

    @Override
    public void setClipCenter(int x, int y) {
        mCenterX = x;
        mCenterY = y;
    }

    @Override
    public void setClipRadius(float radius) {
        mRadius = radius;
        invalidate();
    }
}
