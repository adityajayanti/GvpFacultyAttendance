package com.example.aditya.gvpattendance;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aditya on 22-02-2018.
 */

public class SharedPrefManager {
    private static com.example.aditya.gvpattendance.SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARERD_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ID = "ID";


    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized com.example.aditya.gvpattendance.SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new com.example.aditya.gvpattendance.SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String username , String fid){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARERD_PREF_NAME , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_ID,fid);
        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARERD_PREF_NAME ,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME,null) !=null){
            return true;
        }
        return  false;
    }

    public boolean islogout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARERD_PREF_NAME ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        return true;
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARERD_PREF_NAME , Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null);
    }

    public String getFid(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARERD_PREF_NAME , Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID,null);

    }
}

