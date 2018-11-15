package com.gdc.isfacademy.view.customs.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by ashishthakur on 13/11/18.
 */

public class OpenSansItallicEditText extends AppCompatEditText {

    public OpenSansItallicEditText(Context context) {
        super(context);
        init(context);
    }

    public OpenSansItallicEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OpenSansItallicEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface t = Typeface.createFromAsset(context.getAssets(),
                "fonts/OpenSans-Italic_0.ttf");
        this.setTypeface(t);
    }
}
