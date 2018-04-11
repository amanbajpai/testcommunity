package com.gdc.isfacademy.utils;

import android.content.Context;
import android.content.SharedPreferences;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/*Class id used to store the app data in share perfrence*/
public class MyPref {

    private static final String PERFERENCE_NAME = "ISF";

    private static MyPref instance;
    public static final String IS_VALID_TIME="isValidTime";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MyPref getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new MyPref();
            sharedPreferences = context.getSharedPreferences(PERFERENCE_NAME,
                    Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }

        return instance;
    }

    public void setAlarmTime(String alarmTime)
    {
        editor.putString("alarmTime", alarmTime);
        editor.commit();
    }

    public String getAlarmTime()
    {
        return sharedPreferences.getString("alarmTime", "0");
    }

    public void setHasAlarmSet(Boolean hasAlarmSet)
    {
        editor.putBoolean("hasAlarmSet", hasAlarmSet);
        editor.commit();
    }

    public boolean hasAlarmSet()
    {
        return sharedPreferences.getBoolean("hasAlarmSet", false);
    }




    public String readPrefs(String pref_name) {
        return sharedPreferences.getString(pref_name, "");

    }



    public void writePrefs(String pref_name, String pref_val) {
        editor.putString(pref_name, pref_val);
        editor.commit();
    }

    public void clearPrefs() {
        editor.clear();
        editor.commit();
    }

    public boolean readBooleanPrefs(String pref_name) {
        return sharedPreferences.getBoolean(pref_name, false);
    }
    public void writeBooleanPrefs(String pref_name, boolean pref_val) {
        editor.putBoolean(pref_name, pref_val);
        editor.commit();
    }


}
