package com.example.dxkj.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dxkj.YiJiaoFei.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 首页广告轮播工具
 * Created by dxkj on 2015/9/24.
 */
public class SlideShows extends FrameLayout {

    //使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar
    private ImageLoader imageLoader = ImageLoader.getInstance();
    //轮播图图片数量
    private final static int IMAGE_COUNT = 3;
    //自动轮播的时间间隔
    private final static int TIME_INTERVAL = 4;
    //自动轮播启用开关
    private final static boolean isAutoPlay = true;

    //自定义轮播图的资源
    private String[] imageUrls;

    //放轮播图片的ImageView 的list
    private static List<ImageView> imageViewsList;

    //放圆点的View的list
    private List<View> dotViewsList;

    private ViewPager viewPager;
    //当前轮播页
    private int currentItem = 0;
    //定时任务
    private static ScheduledExecutorService scheduledExecutorService;

    private Context context;

    //Handler
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }

    };

    public SlideShows(Context context) {
        this(context, null);
    }

    public SlideShows(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideShows(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initImageLoader(context);
        initData();
        if (isAutoPlay) {
            startPlay();
        }
    }

    /**
     * 开始轮播图片
     */
    public void startPlay() {
        //定时周期
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        /**
         * 参数1：要执行的任务
         * 参数2：首次执行的延迟时间（4秒）
         * 参数3：一次执行终止和下一次执行开始之间的延迟（4秒）
         * 参数4：时间的单位
         */
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),3, TIME_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 停止轮播图片
     */
    public static void stopPlay() {
        scheduledExecutorService.shutdown();
    }

    /**
     * 初始化图片、圆点数据
     */
    public void initData() {
        imageViewsList = new ArrayList<>();
        dotViewsList = new ArrayList<>();
        new GetPictureTask().execute(Constant.PICTURE_URL1, Constant.PICTURE_URL2, Constant.PICTURE_URL3);
    }

    /**
     * 自动切换图片
     */
    private class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageViewsList.size();
            Message msg = handler.obtainMessage();
            msg.what = 1;
            handler.sendMessage(msg);
        }
    }

    /**
     * 初始化Views等UI
     */
    private void initView(Context context) {
        if (imageUrls == null || imageUrls.length == 0)
            return;
        LayoutInflater.from(context).inflate(R.layout.layout_show, this, true);
        LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();

        // 热点个数与图片相等
        for (int i = 0; i < imageUrls.length; i++) {
            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15,15);
            params.leftMargin = 4;
            params.rightMargin = 4;
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
            ImageView view = new ImageView(context);
            view.setTag(imageUrls[i]);
//            if (i == 0)
//                //给一个默认图
                view.setBackgroundResource(R.drawable.appmain_subject_1);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewsList.add(view);
        }

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setFocusable(true);

        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 填充ViewPager的页面适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager) container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ImageView imageView = imageViewsList.get(position);

            imageLoader.displayImage(imageView.getTag() + "", imageView);

            ((ViewPager) container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;
        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:                                    // 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case 2:                                    // 界面切换中
                    isAutoPlay = true;
                    break;
                case 0:                                    // 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int pos) {
            currentItem = pos;
            for (int i = 0; i < dotViewsList.size(); i++) {
                if (i == pos) {
                    (dotViewsList.get(pos)).setBackgroundResource(R.drawable.dot_enable);
                } else {
                    (dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_disable);
                }
            }
        }
    }

    /**
     * 销毁ImageView资源，回收内存
     */
    public static void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }

    /**
     * 异步获取网络图片
     */
    class GetPictureTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            imageUrls = new String[]{params[0], params[1], params[2]};
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                initView(context);
            }
        }
    }

    /**
     * ImageLoader 图片组件初始化
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");   //缓存目录
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).discCache(new UnlimitedDiskCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
                        // for
                        // release
                        // app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
