package com.example.dxkj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * qqviewpager适配器
 * Created by dxkj on 2015/9/16.
 */
public class QQViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public QQViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list = list;
    }


    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
}
