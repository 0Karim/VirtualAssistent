package com.example.manobahnasy.virtualassistent;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Karim on 14/03/2018.
 */

public class MySingletone {
    private static MySingletone mInstance;
    private RequestQueue requestQueue ;
    private static Context ctx;

    private MySingletone(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingletone getmInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingletone(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T>void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
