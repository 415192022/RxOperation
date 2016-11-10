package com.li.utils.ui.preload;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class PreLoader {
    private final Context context;

    private HashMap<View, ViewState> viewsState;

    boolean fadein = true;

    public PreLoader(Context context) {
        this.context = context;
        this.viewsState = new HashMap<>();
    }

    public static PreLoader with(Context context) {
        return new PreLoader(context);
    }

    public PreLoader on(int... viewsId) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            for (int view : viewsId) {
                add(activity.findViewById(view));
            }
        }
        return this;
    }

    public PreLoader fadein(boolean fadein) {
        this.fadein = fadein;
        return this;
    }

    private void add(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            viewsState.put(view, new TextViewState(textView));
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            viewsState.put(view, new ImageViewState(imageView));
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; ++i) {
                View child = viewGroup.getChildAt(i);
                add(child);
            }
        }
    }

    public PreLoader on(View... views) {
        for (View view : views)
            add(view);
        return this;
    }

    public PreLoader except(View... views) {
        for (View view : views) {
            this.viewsState.remove(view);
        }
        return this;
    }

    public PreLoader start() {
        for (ViewState viewState : viewsState.values()) {
            viewState.start(fadein);
        }
        return this;
    }

    public PreLoader stop() {
        for (ViewState viewState : viewsState.values()) {
            viewState.stop();
        }
        return this;
    }
}
