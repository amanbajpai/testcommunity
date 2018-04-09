package com.gdc.isfacademy.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansSemiBoldTextView;

import java.lang.reflect.Field;

/**
 * Created by ashishthakur on 29/3/18.
 */

public class ProjectUtil {


    /*
    *
    *
    * Alert Dailoge if user want to exit from app
    *
    * */
    public static void logoutAlert(final Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AlertDialogTheme);
        builder.setTitle(R.string.app_name)
                .setMessage(fromHtmlAlert(mContext.getResources().getString(R.string.dialog_message_exit)))
                .setPositiveButton(R.string.dialog_btn_yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity) mContext).finish();
                    }

                })
                .setNegativeButton(R.string.dialog_btn_no, null);

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
    }


    @SuppressWarnings("deprecation")
    public static Spanned fromHtmlAlert(String text) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml("<font color='#000000'>" + text + "</font>", Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml("<font color='#000000'>" + text + "</font>");
        }
        return result;
    }

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {

        }
    }

    public static void showShareDialog(final Context context, View.OnClickListener listener) {

        final Dialog dialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.share_dialog, null);
        OpenSansSemiBoldTextView cancel_tv = (OpenSansSemiBoldTextView) view.findViewById(R.id.cancel_tv);
        OpenSansSemiBoldTextView profile_share_tv = (OpenSansSemiBoldTextView) view.findViewById(R.id.profile_share_tv);
        ImageView chat_image = (ImageView) view.findViewById(R.id.chat_image);
        ImageView google_image = (ImageView) view.findViewById(R.id.google_image);
        ImageView whats_app_image = (ImageView) view.findViewById(R.id.whats_app_image);
        ImageView insta_image = (ImageView) view.findViewById(R.id.insta_image);
        chat_image.setOnClickListener(listener);
        google_image.setOnClickListener(listener);
        whats_app_image.setOnClickListener(listener);
        insta_image.setOnClickListener(listener);
        profile_share_tv.setOnClickListener(listener);
        profile_share_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                ((HomeActivity)context).bottomNavigationView.setSelectedItemId(R.id.navigation_profile);


            }
        });
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.show();
    }

    public static void showToast(final Context context,String message){

        Toast.makeText(context,""+message,Toast.LENGTH_SHORT).show();
    }


}
