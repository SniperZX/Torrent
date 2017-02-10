package com.rock.software.torrent.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rock.software.torrent.Adapter.LiveDetailAdapter;
import com.rock.software.torrent.Bean.LiveBean;
import com.rock.software.torrent.R;

public class LiveDetailActivity extends AppCompatActivity {

    LiveDetailAdapter liveAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_detail);
        recyclerView = (RecyclerView) findViewById(R.id.detailList);
        int pos = getIntent().getIntExtra("pos",0);
        LiveBean liveBean = getIntent().getParcelableExtra("detail");
        liveAdapter = new LiveDetailAdapter(liveBean,LiveDetailActivity.this,pos);
        recyclerView.setAdapter(liveAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(LiveDetailActivity.this,3));

    }



}
