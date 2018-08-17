package com.gdc.isfacademy.view.customs.customfonts;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;


@SuppressWarnings("ALL")
public class CustomTFSpan extends TypefaceSpan {

    private final Typeface typeface;

    public CustomTFSpan(Typeface typeface) {
        super("");
        this.typeface = typeface;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        applyTypeFace(ds, typeface);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyTypeFace(paint, typeface);
    }

    private static void applyTypeFace(Paint paint, Typeface tf) {
        paint.setTypeface(tf);
    }
}
