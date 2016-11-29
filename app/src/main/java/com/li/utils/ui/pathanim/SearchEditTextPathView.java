package com.li.utils.ui.pathanim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Mingwei Li on 2016/11/27 0027.
 */

public class SearchEditTextPathView extends EditText {
    public SearchEditTextPathView(Context context) {
        super(context);
    }

    public SearchEditTextPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchEditTextPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SearchEditTextPathView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private final Paint mPaint = new Paint();
    private Path mPath;
    private PathMeasure pm;
    private Path editorPath;
    private float mPhase;
    private float mRadius = 30;;
    private float distance = 300;

    public void init(){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        createSearchPath(0);
        pm = new PathMeasure(mPath,false);
        editorPath = new Path();
    }

    @Override
    public void setAlpha(float alpha) {
        mPaint.setAlpha((int) alpha);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.translate(distance*mPhase, 0.0f);
        canvas.drawPath(mPath, mPaint);
        mPaint.setPathEffect(null);
        canvas.drawPath(editorPath, mPaint);
        canvas.restore();
    }
    private void createSearchPath(float phase){
        RectF oval = new RectF(0.0f, 0.0f, mRadius * 2.0f, mRadius * 2.0f);

        float cx = oval.centerX();
        float cy = oval.centerY();
        float rx = oval.width() / 2.0f;
        float ry = oval.height() / 2.0f;

        final float TAN_PI_OVER_8 = 0.414213562f;
        final float ROOT_2_OVER_2 = 0.707106781f;

        float sx = rx * TAN_PI_OVER_8;
        float sy = ry * TAN_PI_OVER_8;
        float mx = rx * ROOT_2_OVER_2;
        float my = ry * ROOT_2_OVER_2;

        float L = oval.left;
        float T = oval.top;
        float R = oval.right;
        float B = oval.bottom;

        mPath = new Path();
        mPath.moveTo(cx+mx,cy+my);
        mPath.quadTo(cx+sx,B,cx,B);
        mPath.quadTo(cx-sx,B,cx-mx,cy+my);
        mPath.quadTo(L,cy+sy,L,cy);
        mPath.quadTo(L,cy-sy,cx-mx,cy-my);
        mPath.quadTo(cx - sx,T,cx,T);
        mPath.quadTo(cx + sx,T,cx + mx, cy-my);
        mPath.quadTo(R, cy-sy,R, cy);
        mPath.quadTo(R, cy + sy,cx+mx,cy+my);
        mPath.lineTo(1.5f*R,1.5f*B);
    }
}
