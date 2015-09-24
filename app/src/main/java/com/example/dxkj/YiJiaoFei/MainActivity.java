package com.example.dxkj.YiJiaoFei;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dxkj.adapter.MyViewPagerAdapter;
import com.example.dxkj.fargment.GoodsFragment;
import com.example.dxkj.fargment.HomeFragment;
import com.example.dxkj.fargment.MeFragment;
import com.example.dxkj.fargment.OrderTypeFragment;
import com.example.dxkj.utils.SlideShows;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity {

    @ViewInject(R.id.viewpager_fragment)
    private ViewPager viewPager;
    @ViewInject(R.id.top_head2)
    ImageView iv_head;
    @ViewInject(R.id.tab_menu)
    RadioGroup radioGroup;

    private Fragment homeFragment;
    private Fragment orderFragment;
    private Fragment goodsFragment;
    private Fragment meFragment;
    private List<Fragment> listFragment;
    private MyViewPagerAdapter viewPagerAdapter;

    private int currIndex;//当前页卡编号
    private int bmpW;//横线图片宽度
    private int offset;//图片移动的偏移量
    private ImageView image;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        InitImage();
        setFragment();
        setViewpagerOnClick();
        setTabClick();
    }

    /**
     * 初始化标签地下的
     */
    public void InitImage() {
        image = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.finance).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW / 4 - bmpW) / 2;
        //imgageview设置平移，使下划线平移到初始位置（平移一个offset）
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        image.setImageMatrix(matrix);
    }

    /**
     * 初始化fragment
     */
    private void setFragment() {
        listFragment = new ArrayList<>();
        homeFragment = HomeFragment.newInstance();
        goodsFragment = GoodsFragment.newInstance();
        orderFragment = OrderTypeFragment.newInstance();
        meFragment = MeFragment.newInstance();
        listFragment.add(homeFragment);
        listFragment.add(goodsFragment);
        listFragment.add(orderFragment);
        listFragment.add(meFragment);
    }

    /**
     * 点击标签栏的响应事件
     */
    private void setTabClick() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Chat:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.Address:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.Find:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.Me:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //fragmentViewpager响应事件
    public void setViewpagerOnClick() {
        viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), listFragment);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int one = offset * 2 + bmpW;//两个相邻页面的偏移量

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Animation animation = new TranslateAnimation(currIndex * one, position * one, 0, 0);//平移动画
                currIndex = position;
                animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
                animation.setDuration(200);//动画持续时间0.2秒
                image.startAnimation(animation);//是用ImageView来显示动画的
                int i = currIndex + 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 读取SharedPreferences中的用户信息
     */
    public void readUserNews() {
        SharedPreferences sharedPre = getSharedPreferences("loginNew", MODE_PRIVATE);
        String userphone = sharedPre.getString("userphone", "");
        String password = sharedPre.getString("password", "");
    }


    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦除第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {//退出程序
            SlideShows.destoryBitmaps();
            this.finish();
            System.exit(0);
        }
    }
}
