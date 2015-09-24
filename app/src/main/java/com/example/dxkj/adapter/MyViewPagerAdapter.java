package com.example.dxkj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.dxkj.utils.SlideShows;

import java.util.List;

/**
 * viewpager_fragment适配器
 * Created by dxkj on 2015/9/11.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    /**
     * 重写destroyItem方法  阻止fargment 销毁 提高 viewPager流畅度
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position != 1) {
            SlideShows.stopPlay();
        }
    }
}
