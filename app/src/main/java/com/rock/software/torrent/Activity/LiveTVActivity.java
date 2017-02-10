package com.rock.software.torrent.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.huanrong.velley.GsonRequest;
import com.rock.software.torrent.Adapter.LiveAdapter;
import com.rock.software.torrent.App;
import com.rock.software.torrent.Bean.LiveBean;
import com.rock.software.torrent.R;

import java.util.HashMap;
import java.util.Map;

public class LiveTVActivity extends AppCompatActivity {

    LiveBean liveBean;
    LiveAdapter liveAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        getStation();
    }

    public void getStation(){

        GsonRequest<LiveBean> baseRequest = new GsonRequest<LiveBean>(Request.Method.GET, "http://tv2.toppn.com/fyapi.ashx?f=GetGroupStations", LiveBean.class, new Response.Listener<LiveBean>() {
            @Override
            public void onResponse(LiveBean liveBean1) {
                liveBean = liveBean1;
                liveAdapter = new LiveAdapter(liveBean,LiveTVActivity.this);
                recyclerView.setAdapter(liveAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(LiveTVActivity.this,3));
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> map = new HashMap<>();
                map.put("User-Agent", " Dalvik/1.6.0 (Linux; U; Android 4.4.2; E5823 Build/KOT49H)");
                map.put("feiyutv","1.1.11");
                return map;
            }

        };
        baseRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 3, 1));
        App.getInstance().getRequestQueue().add(baseRequest);


    }





}
