package com.gdc.isfacademy.view.customs.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by akshaydashore on 3/4/18
 */
public class OpenSansLightEditText extends AppCompatEditText {

    public OpenSansLightEditText(Context context) {
        super(context);
        init(context);
    }

    public OpenSansLightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OpenSansLightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface t = Typeface.createFromAsset(context.getAssets(),
                "fonts/OpenSans-Light_0.ttf");
        this.setTypeface(t);
    }
}