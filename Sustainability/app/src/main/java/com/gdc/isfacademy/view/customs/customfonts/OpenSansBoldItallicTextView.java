package com.gdc.isfacademy.view.customs.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by ashishthakur on 5/4/18.
 */

public class OpenSansBoldItallicTextView extends AppCompatTextView {
    public OpenSansBoldItallicTextView(Context context) {
        super(context);
        init(context);
    }

    public OpenSansBoldItallicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public OpenSansBoldItallicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        Typeface t = Typeface.createFromAsset(context.getAssets(),
                "fonts/OpenSans-BoldItalic_0.ttf");
        this.setTypeface(t);
    }
}
