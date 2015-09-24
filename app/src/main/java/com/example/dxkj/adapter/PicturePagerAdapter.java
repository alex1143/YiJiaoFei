package com.example.dxkj.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * 图片viewpager适配器
 * Created by dxkj on 2015/9/11.
 */
public class PicturePagerAdapter extends PagerAdapter {

    private ArrayList<ImageView> images;
    private Context context;

    public PicturePagerAdapter(ArrayList<ImageView> images, Context context) {
        this.context = context;
        this.images = images;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return images.size();
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(images.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup parent = (ViewGroup) images.get(position).getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        container.addView(images.get(position));
        return images.get(position);
    }
}
