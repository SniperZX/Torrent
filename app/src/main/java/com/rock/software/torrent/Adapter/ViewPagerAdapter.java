package com.rock.software.torrent.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rock.software.torrent.fragment.ResultFragment;

import java.util.List;

/**
 * Created by xybcoder on 16/3/1.
 * viewpager 适配器
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    String[] title ;
    List<ResultFragment> list;
    public ViewPagerAdapter(FragmentManager fm, List<ResultFragment> list, String[] title) {
        super(fm);
        this.title = title;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}
