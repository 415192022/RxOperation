package com.li.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by LMW on 2016/8/9.
 */
public class T {
    private Context context;
    private static T t;

    private T(Context context) {
        this.context = context;
    }

    public static T getInstance(Context context) {
        if (null == t) {
            synchronized (T.class) {
                if (null == t) {
                    t = new T(context);
                }
            }
        }
        return t;
    }

    private Toast toast;

    /**
     * 即时更新Toast
     *
     * @param str 需要显示的信息
     */
    public void showToast(String str) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, "" + str, Toast.LENGTH_SHORT);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
            temStr = str;
            toast.setText("" + temStr);
        }
        toast.show();
    }

    private String temStr;

    public void addText(String strings) {
        if (null != toast) {
            toast.setText(temStr += strings);
            toast.show();
        }
    }

}
