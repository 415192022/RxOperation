package com.li.utils.ui.dispatchviewpager;

/**
 * Created by Mingwei Li on 2016/11/26 0026.
 */

public class RxBusPostman {
    public static void postQuickReturnEvent(boolean show) {
        RxBus.getDefault().post(new QuickReturnEvent(!show));
    }

    public static void postOnBackPressEvent(OnBackPressEvent event) {
        RxBus.getDefault().post(event);
    }

    public static void postOnClickScreenEvent(OnClickOutsideToHideEvent event) {
        RxBus.getDefault().post(event);
    }

}
