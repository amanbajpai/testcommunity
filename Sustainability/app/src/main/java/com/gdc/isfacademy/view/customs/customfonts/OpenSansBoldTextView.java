package com.gdc.isfacademy.view.customs.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by ashishthakur on 29/3/18.
 */

public class OpenSansBoldTextView extends AppCompatTextView {

    public OpenSansBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public OpenSansBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public OpenSansBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        Typeface t = Typeface.createFromAsset(context.getAssets(),
                "fonts/OpenSans-Bold_0.ttf");
        this.setTypeface(t);
    }
}