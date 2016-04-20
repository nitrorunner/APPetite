package com.example.ryanj11_tech.appetite;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ryanj11_tech on 4/20/2016.
 */
public class modPrefs {
    SharedPreferences sharedPreferences;
    String errorMsg="Not Found";

    public modPrefs(Context context, String reqPref){
        sharedPreferences=context.getSharedPreferences(reqPref, Context.MODE_PRIVATE);
    }
    public String getNameFromPref(String reqKey){
        String loggedUserName = sharedPreferences.getString(reqKey, errorMsg);
        return loggedUserName;
    }
    public void putData(String key,String data){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.commit();
    }
    public void delData(String key,String data){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
//    public static void savePref(Context context, String key, int value) {
//        SharedPreferences sharedPref = context.getSharedPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(key, value);
//        editor.commit();
//    }
}
