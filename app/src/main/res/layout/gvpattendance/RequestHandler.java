package com.example.aditya.gvpattendance;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Aditya on 22-02-2018.
 */

public class RequestHandler {
    private static com.example.aditya.gvpattendance.RequestHandler mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private RequestHandler(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized com.example.aditya.gvpattendance.RequestHandler getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new com.example.aditya.gvpattendance.RequestHandler(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
