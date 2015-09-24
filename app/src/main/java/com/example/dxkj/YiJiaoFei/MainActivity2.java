package com.example.dxkj.YiJiaoFei;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dxkj.adapter.FragmentAdapter;
import com.example.dxkj.fargment.GoodsFragment;
import com.example.dxkj.fargment.HomeFragment2;
import com.example.dxkj.fargment.MeFragment;
import com.example.dxkj.fargment.OrderTypeFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * Created by dxkj on 2015/9/20.
 */
public class MainActivity2 extends AppCompatActivity {

    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;
    @ViewInject(R.id.tab_layout)
    private TabLayout tabLayout;
    @ViewInject(R.id.view_pager)
    private ViewPager viewPager;
    //DrawerLayout中的左侧菜单控件
    @ViewInject(R.id.navigation_view)
    private NavigationView mNavigationView;
    //DrawerLayout控件
    @ViewInject(R.id.drawer_layout)
    private DrawerLayout mDrawerLayout;
    public List<String> titles;

    public Fragment homeFragment;
    public Fragment orderFragment;
    public Fragment goodsFragment;
    public Fragment meFragment;
    public List<Fragment> listFragment;

    private static boolean mBackKeyPressed = false;//记录是否有首次按键


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewUtils.inject(this);
        init();
    }

    public void init() {
        //初始化toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //初始化标题栏
        titles = new ArrayList<>();
        titles.add("公告");
        titles.add("商品");
        titles.add("订单");
        titles.add("我");

        //对NavigationView添加item的监听事件
        mNavigationView.setNavigationItemSelectedListener(naviListener);
        //开启应用默认打开DrawerLayout
//        mDrawerLayout.openDrawer(mNavigationView);

        //初始化tablayout的title
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));

        //初始化fragment
        listFragment = new ArrayList<>();
        homeFragment = HomeFragment2.newInstance();
        goodsFragment = GoodsFragment.newInstance();
        orderFragment = OrderTypeFragment.newInstance();
        meFragment = MeFragment.newInstance();
        listFragment.add(homeFragment);
        listFragment.add(goodsFragment);
        listFragment.add(orderFragment);
        listFragment.add(meFragment);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), listFragment, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    private NavigationView.OnNavigationItemSelectedListener naviListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            //点击NavigationView中定义的menu item时触发反应
            switch (menuItem.getItemId()) {
                case R.id.menu_publicnews:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.menu_goods:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.menu_order:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.menu_me:
                    viewPager.setCurrentItem(3);
                    break;
            }
            //关闭DrawerLayout回到主界面选中的tab的fragment页
            mDrawerLayout.closeDrawer(mNavigationView);
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //右上角显示菜单按钮
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_publicnews:
                viewPager.setCurrentItem(0);
                break;
            case R.id.menu_goods:
                viewPager.setCurrentItem(1);
                break;
            case R.id.menu_order:
                viewPager.setCurrentItem(2);
                break;
            case R.id.menu_me:
                viewPager.setCurrentItem(3);
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(mNavigationView);
                break;
        }
        return super.onOptionsItemSelected(item);
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
            this.finish();
            System.exit(0);
        }
    }
}
