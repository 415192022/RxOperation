package com.li.utils.ui.recyclerviewlayoutmanager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mingwei Li on 2017/1/18 0018.
 */

public class FullLinearLayout extends LinearLayoutManager {
    private int[] mMeasuredDimension = new int[2];
    int dividerHeight = 1;//分割线高度，默认为1,这里的分割线高度和fullLinearlayoutmanager的分割线高度必须一致！

    @SuppressWarnings("UnusedDeclaration")
    public FullLinearLayout(Context context, int dividerHeight) {
        super(context);
        this.dividerHeight = dividerHeight;
    }

    @SuppressWarnings("UnusedDeclaration")
    public FullLinearLayout(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);
        int width = 0;
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            try {
                measureScrapChild(recycler, i, widthSpec, View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), mMeasuredDimension);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            if (getOrientation() == HORIZONTAL) {
                width = width + mMeasuredDimension[0];
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                //【每个item的高度都要加上分割线高度】
                height = height + mMeasuredDimension[1];
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }
        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }
        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }
        setMeasuredDimension(widthSpec, height);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec, int[] measuredDimension) {
        View view = recycler.getViewForPosition(position);
        if (view != null) {
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom(), p.height);
            view.measure(widthSpec, childHeightSpec);
            measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
            measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
            recycler.recycleView(view);
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }
}
