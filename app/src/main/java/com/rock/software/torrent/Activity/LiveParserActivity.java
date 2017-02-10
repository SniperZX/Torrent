package com.rock.software.torrent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.huanrong.velley.GsonRequest;
import com.rock.software.torrent.App;
import com.rock.software.torrent.Bean.LiveParser;
import com.rock.software.torrent.R;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LiveParserActivity extends AppCompatActivity {

    int id;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_parser);
        id = getIntent().getIntExtra("id",0);
        if(id==0){
            Toast.makeText(this, "id失效", Toast.LENGTH_SHORT).show();
            return;
        }else{
            getStationUrl(id);
        }
        
    }

    public void getStationUrl(int id){

        GsonRequest<LiveParser> baseRequest = new GsonRequest<LiveParser>(Request.Method.GET, "http://tv2.toppn.com/fyapi.ashx?f=GetStationUrls&a="+ URLEncoder.encode("{station:"+id+"}"), LiveParser.class, new Response.Listener<LiveParser>() {
            @Override
            public void onResponse(LiveParser liveParser) {
                String url = decypet(liveParser.getRetObject().get(0).getUrl());
                Intent intent = new Intent(LiveParserActivity.this, VideoViewPlayingActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("User-Agent", " Dalvik/1.6.0 (Linux; U; Android 4.4.2; E5823 Build/KOT49H)");
                map.put("feiyutv","1.1.11");
                return map;
            }

        };
        baseRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 3, 1));
        App.getInstance().getRequestQueue().add(baseRequest);


    }


    private String decypet(String arg4) {
        int v0 = arg4.charAt(0) - 98;
        StringBuilder v1 = new StringBuilder();
        ++v0;
        while(v0 < arg4.length() - 1) {
            v1.append(((char)(arg4.charAt(v0) - 1)));
            ++v0;
        }

        return v1.toString();
    }

}
