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

    public static final String TODAY_CURRENT_CONSUMPTION="currentConsumption";
    public static final String WEEK_CONSUMPTION="weekConsumption";
    public static final String TODAY_CURRENT_CONSUMPTION_UNIT="currentConsumptionUnit";

    public static final String TOTAL_CURRENT_CONSUMPTION_BUILDING="currentConsumptionBuilding";
    public static final String TOTAL_WEEK_CONSUMPTION_BUILDING="weekConsumptionBuilding";
    public static final String TOTAL_CURRENT_CONSUMPTION_BUILDING_UNIT="currentConsumptionBuildingUnit";
    public static final String QUIZ_COUNT="quiz_count";
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

    public void writeIntegerPrefs(String pref_name, int pref_val) {
        editor.putInt(pref_name, pref_val);
        editor.commit();
    }

    public void writeFloatPrefs(String pref_name, float pref_val) {
        editor.putFloat(pref_name, pref_val);
        editor.commit();
    }
    public Float readFloatPrefs(String pref_name) {
        return sharedPreferences.getFloat(pref_name, -1f);
    }

    public Integer readIntegerPrefs(String pref_name) {
        return sharedPreferences.getInt(pref_name, 0);
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


    public void setAnsweredDate(String answeredDate)
    {
        editor.putString("answeredDate", answeredDate);
        editor.commit();
    }

    public String getAnsweredDate()
    {
        return sharedPreferences.getString("answeredDate", "");
    }


    public void setAnsweredCount(int answeredCount)
    {
        editor.putInt("answeredCount", answeredCount);
        editor.commit();
    }

    public int getAnsweredCount()
    {
        return sharedPreferences.getInt("answeredCount", 0);
    }



}
