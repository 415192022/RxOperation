package com.li.utils.ui.preload;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by f.champigny on 30/08/16.
 */
public class TextViewState extends ViewState<TextView> {
    ColorStateList textColor;

    public TextViewState(TextView textView) {
        super(textView);
    }

    @Override
    protected void init() {
        super.init();
        this.textColor = view.getTextColors();
        this.darker = view.getTypeface() != null && view.getTypeface().isBold();
    }

    @Override
    protected void restore() {
        this.view.setTextColor(textColor);
    }

    @Override
    public void start(boolean fadein) {
        super.start(fadein);
        view.setTextColor(Color.TRANSPARENT);
    }
}
