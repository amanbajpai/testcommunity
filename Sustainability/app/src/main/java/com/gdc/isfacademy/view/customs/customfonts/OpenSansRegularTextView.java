package com.gdc.isfacademy.view.customs.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class OpenSansRegularTextView extends AppCompatTextView {


    public OpenSansRegularTextView(Context context) {
        super(context);
        init(context);
    }

    public OpenSansRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OpenSansRegularTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface t = Typeface.createFromAsset(context.getAssets(),
                "fonts/OpenSans-Regular_0.ttf");
        this.setTypeface(t);
    }
}
