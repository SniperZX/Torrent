package com.rock.software.torrent.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rock.software.torrent.Adapter.ViewPagerAdapter;
import com.rock.software.torrent.MyView.TabIndicatorView;
import com.rock.software.torrent.R;
import com.rock.software.torrent.fragment.ResultFragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class SearchResultActivity extends AppCompatActivity {


    private static final String TAB_CHAT = "chat";
//    FragmentTabHost tabhost;
    private TabIndicatorView andques;
    private String key;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        key = getIntent().getStringExtra("key");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 1. 初始化TabHost
//        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        List<ResultFragment> resultFragments = new ArrayList<>();
        String[] websites = getResources().getStringArray(R.array.Websites);
        String[] titles =  getResources().getStringArray(R.array.title);
        try {
            key = URLEncoder.encode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < websites.length; i++) {
            ResultFragment resultFragment = ResultFragment.newInstance(i,key);
            resultFragments.add(resultFragment);
        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),resultFragments,titles);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        TabHost.TabSpec spec;
//        for (int i = 0; i < websites.length; i++) {
//            spec = tabhost.newTabSpec("" + i);
//            spec.setIndicator(getTabItemView(i));
//            // 3. 添加TabSpec
//            Bundle bundle = new Bundle();
//            bundle.putInt("index", i);
//            bundle.putString("key", key);
//            tabhost.addTab(spec, ResultFragment.class, bundle);
//        }
    }


    private View getTabItemView(int index) {
        View view = getLayoutInflater().inflate(R.layout.tab_item, null);
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText("搜索" + index);
        return view;
    }

}
