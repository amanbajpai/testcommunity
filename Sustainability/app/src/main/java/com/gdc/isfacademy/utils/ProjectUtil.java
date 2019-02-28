package com.gdc.isfacademy.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
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
import com.gdc.isfacademy.model.BadgeStudentResponse;
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
import java.util.ArrayList;
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






    public static void shareAppLinkAlertDialog(final Context mContext,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        Typeface externalFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Bold_0.ttf");
        CustomTFSpan tfSpan = new CustomTFSpan(externalFont);
        SpannableString spannableString = new SpannableString(mContext.getString(R.string.app_name));
        spannableString.setSpan(tfSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setTitle(spannableString)
                .setMessage(fromHtmlAlert(message))
                .setNegativeButton(R.string.txt_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                })
                .setPositiveButton(R.string.txt_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, mContext.getString(R.string.txt_hello) + " " +
                                MyPref.getInstance(mContext).readPrefs(AppConstants.STUDENT_NAME) + " " +
                                mContext.getString(R.string.txt_sending_app_link)  +mContext.getString(R.string.txt_next_line)+AppConstants.FOR_ANDROID+ AppConstants.ANDROID_APP_LINK
                        +mContext.getString(R.string.txt_next_line)+mContext.getString(R.string.txt_next_line)+AppConstants.FOR_IOS+AppConstants.IOS_APP_LINK);

                        sendIntent.setType("text/plain");
                        mContext.startActivity(Intent.createChooser(sendIntent, mContext.getResources().getText(R.string.txt_send_to)));
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
        alert.setCanceledOnTouchOutside(false);
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
            }
        });
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);

        pbutton.setEnabled(true);
        nbutton.setEnabled(true);
        pbutton.setTextColor(ContextCompat.getColor(mContext, R.color.color_text_and_spinner));
        nbutton.setTextColor(ContextCompat.getColor(mContext, R.color.color_text_and_spinner));

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
                    .setLargeIcon(BitmapFactory.decodeResource(ISFApp.getAppInstance().getApplicationContext().getResources(), R.drawable.app_icon_school))
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
        return useWhiteIcon ? R.drawable.app_icon_school : R.drawable.app_icon_school;
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

    public static String getTodayDate() {
        String date = "";
        try {
            Calendar calendar = Calendar.getInstance();
            date = calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return date;
    }

    public static ProgressDialog showDialog(Context context) {
        ProgressDialog progressDialog = null;
        try {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return progressDialog;
    }

    public static ProgressDialog dismissDialog(ProgressDialog progressDialog) {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return progressDialog;
    }

    public static int math(float f) {
        int c = (int) ((f) + 0.5f);
        float n = f + 0.5f;
        return (n - c) % 2 == 0 ? (int) f : c;
    }

    public static void logoutFromApp(Context context) {
        try {
            ISFApp.getAppInstance().getDaoSession().getQuestionDao().deleteAll();
            MyPref.getInstance(context).clearPrefs();
            MyPref.getInstance(context).writePrefs(AppConstants.STUDENT_KEY, "");

        }catch (Throwable e){
            e.printStackTrace();
        }
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();

    }

    @SuppressLint("SimpleDateFormat")
    public static String toDate(long timestamp) {
        Date date = new Date(timestamp);
        return new SimpleDateFormat("d/M").format(date);

    }


    /*
    *
    * Get date from timestamp
    *
    * */

    public static void hideKeyboard(Context context) {
        try {
            if (context == null) {
                context = ISFApp.getAppInstance().getApplicationContext();
            }
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0); // hide
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public static void hideKeyboardFrom(Context context, View view) {
        if (context == null) {
            context = ISFApp.getAppInstance().getApplicationContext();
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void logoutFromAppMenu(final Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AlertDialogTheme);
        builder.setTitle(R.string.app_name)
                .setMessage(fromHtmlAlert(mContext.getResources().getString(R.string.dialog_message_logout)))

                .setPositiveButton(R.string.dialog_btn_yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ISFApp.getAppInstance().getDaoSession().getQuestionDao().deleteAll();
                        MyPref.getInstance(mContext).clearPrefs();
                        Intent intent=new Intent(mContext,LoginActivity.class);
                        ((Activity) mContext).startActivity(intent, ActivityOptions.makeCustomAnimation(mContext,R.anim.slide_in,R.anim.slide_out).toBundle());
                        ((Activity) mContext).finishAfterTransition();

                    }

                })
                .setNegativeButton(R.string.dialog_btn_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(ContextCompat.getColor(mContext, R.color.color_text_and_spinner));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(ContextCompat.getColor(mContext, R.color.color_text_and_spinner));
    }



       /*
    *
    *
    * Alert dialog if user want to logout from app
    *
    * */

    public static String getValue(float value) {
        String formattedValue = Integer.toString((int) value);
        return formattedValue;
    }

    public void timeAgo(String date) {

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss 'GMT'Z yyyy");
            Date past = dateFormat.parse(date);
            Date now = new Date();
            long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
            long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
            long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());
            System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");

            if (seconds < 60) {
                System.out.println(seconds + " seconds ago");
            } else if (minutes < 60) {
                System.out.println(minutes + " minutes ago");
            } else if (hours < 24) {
                System.out.println(hours + " hours ago");
            } else {
                System.out.println(days + " days ago");
            }
        } catch (Exception j) {
            j.printStackTrace();
        }
    }


    public static ArrayList<BadgeStudentResponse>getBadgeList(ArrayList<BadgeStudentResponse>badgeStudentResponses){
        for (int position=0;position<badgeStudentResponses.size();position++){
            if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_ENERGY_SAVING)) {
                if (badgeStudentResponses.get(position).getValue() != null) {
                    float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                    if (value < AppConstants.VALUE_ENERGY_SAVING_BRNOZE) {
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.energy_saver_locked);
                    } else if (value >= AppConstants.VALUE_ENERGY_SAVING_BRNOZE && value < AppConstants.VALUE_ENERGY_SAVING_SILVER) {
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.energy_saving_bronze_badge);
                    } else if (value >= AppConstants.VALUE_ENERGY_SAVING_SILVER && value < AppConstants.VALUE_ENERGY_SAVING_GOLD) {
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.energy_saving_silver_badge);
                    } else if (value >= AppConstants.VALUE_ENERGY_SAVING_GOLD && value < AppConstants.VALUE_ENERGY_SAVING_HIDDEN) {
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.energy_saving_gold_badge);
                    } else if (value >= AppConstants.VALUE_ENERGY_SAVING_HIDDEN) {
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.energy_saving_hidden_badge);

                    }
                }
            }
            else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_QUIZHOLIC)) {
                if (badgeStudentResponses.get(position).getValue() != null) {
                    float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                    if (value < AppConstants.VALUE_QUIZHOLIC_BRONZE) {
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.quizholic_locked);
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                    } else if (value >= AppConstants.VALUE_QUIZHOLIC_BRONZE && value < AppConstants.VALUE_QUIZHOLIC_SILVER) {
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.quiz_holic_bronze_badge);
                    } else if (value >= AppConstants.VALUE_QUIZHOLIC_SILVER && value < AppConstants.VALUE_QUIZHOLIC_GOLDE) {
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.quiz_holic_silver_badge);
                    } else if (value >= AppConstants.VALUE_QUIZHOLIC_GOLDE && value < AppConstants.VALUE_QUIZHOLIC_HIDDEN) {
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.quiz_holic_gold_badge);
                    } else if (value >= AppConstants.VALUE_QUIZHOLIC_HIDDEN) {
                        badgeStudentResponses.get(position).setResourceIdImage(R.drawable.quiz_holic_hidden_badge);
                        badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                    }
                }
            } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_PERSISTANT)) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_PERSISTANT_BRONZE) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.persistent_locked);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                } else if (value >= AppConstants.VALUE_PERSISTANT_BRONZE && value < AppConstants.VALUE_PERSISTANT_SILVER) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.persistent_bronze_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                } else if (value >= AppConstants.VALUE_PERSISTANT_SILVER && value < AppConstants.VALUE_PERSISTANT_GOLD) {
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.persistent_silver_badge);
                } else if (value >= AppConstants.VALUE_PERSISTANT_GOLD && value < AppConstants.VALUE_PERSISTANT_HIDDEN) {
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.persitent_gold_badge);
                } else if (value >= AppConstants.VALUE_PERSISTANT_HIDDEN) {
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.persistent_hidden_badge);
                }

            } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_PERFECTION)) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_PERFECTION_BRONZE) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.perfection_locked);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                } else if (value >= AppConstants.VALUE_PERFECTION_BRONZE && value < AppConstants.VALUE_PERFECTION_SILVER) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.perfection_bronze_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                } else if (value >= AppConstants.VALUE_PERFECTION_SILVER && value < AppConstants.VALUE_PERFECTION_GOLD) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.perfection_silver_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                } else if (value >= AppConstants.VALUE_PERFECTION_GOLD && value < AppConstants.VALUE_PERFECTION_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.perfection_gold_badge);

                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                } else if (value >= AppConstants.VALUE_PERFECTION_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.perfection_hidden_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                }

            } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_BROADCASTER)) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_BROADCASTER_BRONZE) {
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.broadcaster_locked);
                } else if (value >= AppConstants.VALUE_BROADCASTER_BRONZE && value < AppConstants.VALUE_BROADCASTER_SILVER) {
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.broadcaster_bronze_badge);
                } else if (value >= AppConstants.VALUE_BROADCASTER_SILVER && value < AppConstants.VALUE_BROADCASTER_GOLD) {
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.broadcaster_silver_badge);
                } else if (value >= AppConstants.VALUE_BROADCASTER_GOLD && value < AppConstants.VALUE_BROADCASTER_HIDDEN) {
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.brodcaster_gold_badge);
                } else if (value >= AppConstants.VALUE_BROADCASTER_HIDDEN) {
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.broadcaster_hidden_badge);
                }
            } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_FRIENDLY)) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_FRIENDLY_BRONZE) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.friendly_locked);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                } else if (value >= AppConstants.VALUE_FRIENDLY_BRONZE && value < AppConstants.VALUE_FRIENDLY_SILVER) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.friendly_bronze_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                } else if (value >= AppConstants.VALUE_FRIENDLY_SILVER && value < AppConstants.VALUE_FRIENDLY_GOLD) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.friendly_silver_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                } else if (value >= AppConstants.VALUE_FRIENDLY_GOLD && value < AppConstants.VALUE_FRIENDLY_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.friendly_gold_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                } else if (value >= AppConstants.VALUE_FRIENDLY_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.friendly_hidden_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                }
            } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_LEGENDARY)) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_LEGENDARY_BRONZE) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.legendary_locked);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                } else if (value >= AppConstants.VALUE_LEGENDARY_BRONZE && value < AppConstants.VALUE_LEGENDARY_SILVER) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.legendary_bronze_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                } else if (value >= AppConstants.VALUE_LEGENDARY_SILVER && value < AppConstants.VALUE_LEGENDARY_GOLD) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.legendary_silver_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                } else if (value >= AppConstants.VALUE_LEGENDARY_GOLD && value < AppConstants.VALUE_LEGENDARY_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.legendary_gold_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                } else if (value >= AppConstants.VALUE_LEGENDARY_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.legendary_hidden_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                }
            } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_SUPERIOR_SPECLIST)) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());
                if (value < AppConstants.VALUE_SUPERIOR_SPECLIST_BRONZE) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.superior_locked);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_BRONZE && value < AppConstants.VALUE_SUPERIOR_SPECLIST_SILVER) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.superior_brnoze_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_SILVER && value < AppConstants.VALUE_SUPERIOR_SPECLIST_GOLD) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.superior_silver_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_GOLD && value < AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.superior_gold_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                } else if (value >= AppConstants.VALUE_SUPERIOR_SPECLIST_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.superior_hidden_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                }
            } else if (badgeStudentResponses.get(position).getBadgesType().equalsIgnoreCase(AppConstants.ID_CHAMPION)) {
                float value = Float.parseFloat(badgeStudentResponses.get(position).getValue());

                if (value < AppConstants.VALUE_CHAMPION_BRONZE) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.champion_locked);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.LOCKED);
                } else if (value >= AppConstants.VALUE_CHAMPION_BRONZE && value < AppConstants.VALUE_CHAMPION_SILVER) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.champion_bronze_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.BRONZE);
                } else if (value >= AppConstants.VALUE_CHAMPION_SILVER && value < AppConstants.VALUE_CHAMPION_GOLD) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.champion_silver_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.SILVER);
                } else if (value >= AppConstants.VALUE_CHAMPION_GOLD && value < AppConstants.VALUE_CHAMPION_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.champion_gold_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.GOLD);
                } else if (value >= AppConstants.VALUE_CHAMPION_HIDDEN) {
                    badgeStudentResponses.get(position).setResourceIdImage(R.drawable.champion_hidden_badge);
                    badgeStudentResponses.get(position).setBadgeAllotedFor(AppConstants.HIDDEN);
                }
            }

        }

        return badgeStudentResponses;


    }


}
