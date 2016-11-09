package com.li.utils.ui.mdbottom;

import android.support.annotation.IdRes;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by alessandro on 4/4/16 at 11:13 PM.
 * Project: Material-BottomNavigation
 */
@Keep
public interface ItemsLayoutContainer {
    void setSelectedIndex(int index, final boolean animate);

    int getSelectedIndex();

    void populate(@NonNull MenuParser.Menu menu);

    void setLayoutParams(ViewGroup.LayoutParams params);

    void setOnItemClickListener(OnItemClickListener listener);

    View findViewById(@IdRes final int id);

    void removeAll();
}
