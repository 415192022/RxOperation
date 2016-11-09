package com.li.utils.ui.mdbottom;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import rxop.li.com.rxoperation.R;


public class ShiftingLayout extends ViewGroup implements ItemsLayoutContainer {
    private static final String TAG = ShiftingLayout.class.getSimpleName();
    public static final double ROUND_DECIMALS = 10d;
    public static final float RATIO_MIN_INCREASE = 0.05f;
    private final int maxActiveItemWidth;
    private final int minActiveItemWidth;
    private final int maxInactiveItemWidth;
    private final int minInactiveItemWidth;
    private int totalChildrenSize;
    private int minSize, maxSize;
    private int selectedIndex;
    private boolean hasFrame;
    OnItemClickListener listener;
    private MenuParser.Menu menu;

    public ShiftingLayout(final Context context) {
        super(context);
        totalChildrenSize = 0;
        maxActiveItemWidth = getResources().getDimensionPixelSize(R.dimen.bbn_shifting_maxActiveItemWidth);
        minActiveItemWidth = getResources().getDimensionPixelSize(R.dimen.bbn_shifting_minActiveItemWidth);
        maxInactiveItemWidth = getResources().getDimensionPixelSize(R.dimen.bbn_shifting_maxInactiveItemWidth);
        minInactiveItemWidth = getResources().getDimensionPixelSize(R.dimen.bbn_shifting_minInactiveItemWidth);
    }

    @Override
    public void removeAll() {
        removeAllViews();
        totalChildrenSize = 0;
        selectedIndex = 0;
        menu = null;
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b) {
        if (!hasFrame || getChildCount() == 0) {
            return;
        }

        if (totalChildrenSize == 0) {
            totalChildrenSize = minSize * (getChildCount() - 1) + maxSize;
        }

        int width = (r - l);
        int left = (width - totalChildrenSize) / 2;

        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            final LayoutParams params = child.getLayoutParams();
            setChildFrame(child, left, 0, params.width, params.height);
            left += child.getWidth();
        }
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        MiscUtils.log(TAG, Log.INFO, "onSizeChanged(" + w + ", " + h + ")");
        super.onSizeChanged(w, h, oldw, oldh);
        hasFrame = true;

        if (null != menu) {
            populateInternal(menu);
            menu = null;
        }
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        // MiscUtils.log(TAG, Log.VERBOSE, "setChildFrame: " + left + ", " + top + ", " + width + ", " + height);
        child.layout(left, top, left + width, top + height);
    }

    public void setTotalSize(final int minSize, final int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public void setSelectedIndex(final int index, final boolean animate) {
        MiscUtils.log(TAG, Log.INFO, "setSelectedIndex: " + index);

        if (selectedIndex == index) {
            return;
        }

        int oldSelectedIndex = this.selectedIndex;
        this.selectedIndex = index;

        MiscUtils.log(TAG, Log.DEBUG, "change selection: %d --> %d", oldSelectedIndex, selectedIndex);

        if (!hasFrame || getChildCount() == 0) {
            return;
        }

        final BottomNavigationItemViewAbstract current = (BottomNavigationItemViewAbstract) getChildAt(oldSelectedIndex);
        final BottomNavigationItemViewAbstract child = (BottomNavigationItemViewAbstract) getChildAt(index);

        current.setExpanded(false, minSize, animate);
        child.setExpanded(true, maxSize, animate);
    }

    @Override
    @Keep
    @SuppressWarnings ("unused")
    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public void populate(@NonNull final MenuParser.Menu menu) {
        MiscUtils.log(TAG, Log.INFO, "populate: " + menu);

        if (hasFrame) {
            populateInternal(menu);
        } else {
            this.menu = menu;
        }
    }

    private void populateInternal(@NonNull final MenuParser.Menu menu) {
        MiscUtils.log(TAG, Log.DEBUG, "populateInternal");

        final BottomNavigation parent = (BottomNavigation) getParent();
        final float density = getResources().getDisplayMetrics().density;
        final int screenWidth = parent.getWidth();

        MiscUtils.log(TAG, Log.VERBOSE, "density: " + density);
        MiscUtils.log(TAG, Log.VERBOSE, "screenWidth(dp): " + (screenWidth / density));

        int itemWidthMin;
        int itemWidthMax;

        final int totalWidth = maxInactiveItemWidth * (menu.getItemsCount() - 1) + maxActiveItemWidth;
        MiscUtils.log(TAG, Log.VERBOSE, "totalWidth(dp): " + totalWidth / density);

        if (totalWidth > screenWidth) {
            float ratio = (float) screenWidth / totalWidth;
            ratio = (float) ((double) Math.round(ratio * ROUND_DECIMALS) / ROUND_DECIMALS) + RATIO_MIN_INCREASE;
            MiscUtils.log(TAG, Log.VERBOSE, "ratio: " + ratio);

            itemWidthMin = (int) Math.max(maxInactiveItemWidth * ratio, minInactiveItemWidth);
            itemWidthMax = (int) (maxActiveItemWidth * ratio);

            if (BottomNavigation.DEBUG) {
                MiscUtils.log(TAG, Log.DEBUG, "computing sizes...");
                MiscUtils.log(TAG, Log.VERBOSE, "itemWidthMin(dp): " + itemWidthMin / density);
                MiscUtils.log(TAG, Log.VERBOSE, "itemWidthMax(dp): " + itemWidthMax / density);
                MiscUtils.log(TAG, Log.VERBOSE, "total items size(dp): "
                    + (itemWidthMin * (menu.getItemsCount() - 1) + itemWidthMax) / density);
            }

            if (itemWidthMin * (menu.getItemsCount() - 1) + itemWidthMax > screenWidth) {
                itemWidthMax = screenWidth - (itemWidthMin * (menu.getItemsCount() - 1)); // minActiveItemWidth?
                if (itemWidthMax == itemWidthMin) {
                    itemWidthMin = minInactiveItemWidth;
                    itemWidthMax = screenWidth - (itemWidthMin * (menu.getItemsCount() - 1));
                }
            }
        } else {
            itemWidthMax = maxActiveItemWidth;
            itemWidthMin = maxInactiveItemWidth;
        }

        if (BottomNavigation.DEBUG) {
            MiscUtils.log(TAG, Log.VERBOSE, "active size (dp): "
                + maxActiveItemWidth / density + ", " + minActiveItemWidth / density);
            MiscUtils.log(TAG, Log.VERBOSE, "inactive size (dp): "
                + maxInactiveItemWidth / density + ", " + minInactiveItemWidth / density);
            MiscUtils.log(TAG, Log.VERBOSE, "itemWidth(dp): "
                + (itemWidthMin / density) + ", " + (itemWidthMax / density));
        }

        setTotalSize(itemWidthMin, itemWidthMax);

        for (int i = 0; i < menu.getItemsCount(); i++) {
            final BottomNavigationItem item = menu.getItemAt(i);
            MiscUtils.log(TAG, Log.DEBUG, "item: " + item);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidthMin, getHeight());

            if (i == selectedIndex) {
                params.width = itemWidthMax;
            }

            BottomNavigationItemViewAbstract view =
                new BottomNavigationShiftingItemView(parent, i == selectedIndex, menu);
            view.setItem(item);
            view.setLayoutParams(params);
            view.setClickable(true);
            view.setTypeface(parent.typeface);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (null != listener) {
                        listener.onItemClick(ShiftingLayout.this, v, finalI, true);
                    }
                }
            });
            view.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(final View v) {
                    Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            addView(view);
        }
    }
}
