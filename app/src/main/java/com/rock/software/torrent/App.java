package com.rock.software.torrent;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 76411 on 2016-09-30.
 */

public class App extends Application {
    private static App app;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized App getInstance() {
        return app;
    }

}
