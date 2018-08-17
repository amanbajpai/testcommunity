package com.gdc.isfacademy.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.receivers.AlarmReceiver;
import com.gdc.isfacademy.receivers.QuizeReminderReciever;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.activity.LoginActivity;
import com.gdc.isfacademy.view.activity.SplashActivity;
import com.gdc.isfacademy.view.customs.customfonts.CustomTFSpan;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansSemiBoldTextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashishthakur on 29/3/18.
 */

@SuppressWarnings("ALL")
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
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /*
*
* show all apps log from here...
* */
    public static void showLog(String Tag, String value, int logPrintingSection) {
        try {
            if (logPrintingSection == AppConstants.ERROR_LOG) {
                Log.e(Tag, value);
            } else {
                Log.d(Tag, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void showShareDialog(final Context context, View.OnClickListener listener) {

        final Dialog dialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.share_dialog, null);
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
                ((HomeActivity) context).bottomNavigationView.setSelectedItemId(R.id.navigation_profile);


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

    public static void showToast(final Context context, String message) {

        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }


    public static void setAlarm(Context context) {
        try {
            AlarmManager alarmMgr;
            PendingIntent alarmIntent;
            if (!MyPref.getInstance(context).hasAlarmSet()) {
                Log.e("alaram set", "set");
                MyPref.getInstance(context).setHasAlarmSet(true);
                MyPref.getInstance(context).setAlarmTime("16");

                // Set the alarm to start at approximately 4:00 p.m.
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 16);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);


                alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(context, AlarmReceiver.class);
                alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, alarmIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void setAlarmReminder(Context context) {
        try {
            AlarmManager alarmMgr;
            PendingIntent alarmIntent;
            if (!MyPref.getInstance(context).hasAlarmSetReminder()) {
                Log.e("alaram set", "set");
                MyPref.getInstance(context).setHasAlarmSetReminder(true);
                MyPref.getInstance(context).setAlarmTimeReminder("17");

                // Set the alarm to start at approximately 5:00 p.m.
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 17);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);


                alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(context, QuizeReminderReciever.class);
                alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, alarmIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void checkTime(Context context) {
        try {
            Calendar calendar = Calendar.getInstance();

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            Log.e("hours", "" + hour);


            if (hour >= 8 && hour < 16) {
                Log.e("hoursInvalide", "" + hour);
               // MyPref.getInstance(context).writeBooleanPrefs(MyPref.IS_VALID_TIME, false);
              //  showInvalidTimeDialog(context);

            } else {

                Log.e("hoursValid", "" + hour);
               // MyPref.getInstance(context).writeBooleanPrefs(MyPref.IS_VALID_TIME, true);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showInvalidTimeDialog(final Context context) {
        try {
            appRestrictionAlertDialog(context);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void appRestrictionAlertDialog(final Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        Typeface externalFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Bold_0.ttf");
        CustomTFSpan tfSpan = new CustomTFSpan(externalFont);
        SpannableString spannableString = new SpannableString("ISF Community");
        spannableString.setSpan(tfSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setTitle(spannableString)
                .setMessage(fromHtmlAlert(mContext.getResources().getString(R.string.app_restriction_message)))
                .setPositiveButton(R.string.txt_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if (mContext instanceof LoginActivity) {
                            ((LoginActivity) mContext).finish();
                        } else if (mContext instanceof HomeActivity) {
                            ((HomeActivity) mContext).finish();
                        }
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
        alert.setCanceledOnTouchOutside(false);
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mContext instanceof LoginActivity) {
                    ((LoginActivity) mContext).finish();
                } else if (mContext instanceof HomeActivity) {
                    ((HomeActivity) mContext).finish();
                }
            }
        });
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setEnabled(true);
        pbutton.setTextColor(ContextCompat.getColor(mContext, R.color.color_text_and_spinner));
    }


    public static Dialog showCustomDialog(Context context, int resId) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resId);
        Window windo = dialog.getWindow();
        windo.setDimAmount(0.7f);
        WindowManager.LayoutParams wlp = windo.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        windo.setAttributes(wlp);
        return dialog;
    }


    public static void sendNotification(String message) {
        try {
            //MyPref.getInstance(KalamanseeApp.getAppInstance().getApplicationContext()).writeBooleanPrefs("test",true);

            long when = System.currentTimeMillis();
            NotificationManager mNotificationManager = (NotificationManager) ISFApp.getAppInstance().getApplicationContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent contentIntent = null;
            Intent notificationIntent = new Intent(ISFApp.getAppInstance().getApplicationContext(), SplashActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            contentIntent = PendingIntent.getActivity(ISFApp.getAppInstance().getApplicationContext(),
                    (int) when, notificationIntent, 0);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ISFApp.getAppInstance().getApplicationContext())
                    .setSmallIcon(getNotificationIconnew())
                    .setLargeIcon(BitmapFactory.decodeResource(ISFApp.getAppInstance().getApplicationContext().getResources(), R.drawable.app_icon))
                    .setContentTitle("Daily challenge!")
                    .setContentText(message)
                    .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setColor(R.color.white)
                    .setSound(defaultSoundUri)
                    .setContentIntent(contentIntent);
            Notification notification =
                    notificationBuilder
                            .setStyle(new NotificationCompat.BigTextStyle()).build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            notification.defaults = Notification.DEFAULT_ALL;
            Random random = new Random();
            int m = random.nextInt(9999 - 1000) + 1000;
            mNotificationManager.notify(m, notification);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getNotificationIconnew() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.app_icon : R.drawable.icon_push;
    }



    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String getTodayDate()
    {
        String date = "";
        try
        {
            Calendar calendar = Calendar.getInstance();

            date = calendar.get(Calendar.DAY_OF_MONTH)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return date;
    }

    public static ProgressDialog showDialog(Context context)
    {
        ProgressDialog progressDialog = null;
        try
        {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return progressDialog;
    }

    public static ProgressDialog dismissDialog(ProgressDialog progressDialog)
    {
        try
        {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return progressDialog;
    }

    public static int math(float f) {
        int c = (int) ((f) + 0.5f);
        float n = f + 0.5f;
        return (n - c) % 2 == 0 ? (int) f : c;
    }




    public void timeAgo(String date){

        try
        {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss 'GMT'Z yyyy");
            Date past = dateFormat.parse(date);
            Date now = new Date();
            long seconds= TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
            long minutes= TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
            long hours=TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
            long days=TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());
          System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");

            if(seconds<60)
            {
                System.out.println(seconds+" seconds ago");
            }
            else if(minutes<60)
            {
                System.out.println(minutes+" minutes ago");
            }
            else if(hours<24)
            {
                System.out.println(hours+" hours ago");
            }
            else
            {
                System.out.println(days+" days ago");
            }
        }
        catch (Exception j){
            j.printStackTrace();
        }
    }



    public static void logoutFromApp(Context context){
        MyPref.getInstance(context).writePrefs(AppConstants.STUDENT_KEY, "");
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }


    /*
    *
    * Get date from timestamp
    *
    * */

    @SuppressLint("SimpleDateFormat")
    public static String toDate(long timestamp) {
        Date date = new Date(timestamp);
        return new SimpleDateFormat("d/M").format(date);

         }

    public static void hideKeyboard(Context context){
        try {
            if(context==null){
                context=ISFApp.getAppInstance().getApplicationContext();
            }
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (imm.isActive()){
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0); // hide
            }
        }catch (Throwable e){
            e.printStackTrace();
        }

    }




}
