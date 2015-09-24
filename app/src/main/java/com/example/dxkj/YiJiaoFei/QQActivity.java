package com.example.dxkj.YiJiaoFei;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dxkj.adapter.QQViewPagerAdapter;
import com.example.dxkj.fargment.QbFragment;
import com.example.dxkj.fargment.QdFragment;
import com.example.dxkj.fargment.QhuiYuanFragment;
import com.example.dxkj.fargment.QzuanFragment;
import com.example.dxkj.utils.ShowPopWindows;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q币充值页面
 * Created by dxkj on 2015/9/15.
 */
public class QQActivity extends FragmentActivity implements QzuanFragment.OnItemClickedListener,
        QbFragment.OnItemClickedListener, QhuiYuanFragment.OnItemClickedListener, QdFragment.OnItemClickedListener {

    @ViewInject(R.id.edt_qq)
    private EditText edt_qq;                          //qq号码
    @ViewInject(R.id.edt_qq_sure)
    private EditText edt_qq_sure;                     //确认号码
    @ViewInject(R.id.tv_qqZuan)
    private TextView tv_qqZ;                          //q钻
    @ViewInject(R.id.tv_qqHui)
    private TextView tv_qqHui;                        //qq会员
    @ViewInject(R.id.tv_qqB)
    private TextView tv_qqB;                          //q币
    @ViewInject(R.id.tv_qqD)
    private TextView tv_qqD;                          //q点
    @ViewInject(R.id.qq_pager)
    private ViewPager qq_pager;
    @ViewInject(R.id.title_name)
    private TextView tv_title_name;                   //充值标题
    @ViewInject(R.id.dot_0)
    private View point_0;
    @ViewInject(R.id.dot_1)
    private View point_1;
    @ViewInject(R.id.dot_2)
    private View point_2;
    @ViewInject(R.id.dot_3)
    private View point_3;
    @ViewInject(R.id.order_news)
    private TextView tv_news;                          //订单信息
    @ViewInject(R.id.top_head2)
    private ImageView imageView;

    private List<Fragment> listFragment;
    private QQViewPagerAdapter viewPagerAdapter;
    private ArrayList<View> dot;                     //小圆点集合
    private int oldPosition = 0;   //记录上一次点的位置
    private int currentItem;  //当前页面
    private Map<String, String> map;

    public String qzType;
    public String qzNumber;
    public String qzPrice = null;
    public String qhType;
    public String qhNumber;
    public String qhPrice = null;
    public String qbNumber;
    public String qbPrice = null;
    public String qdNumber;
    public String qdPrice = null;

    public static final String qz_price = "10";                  //q钻价格
    public static final String qh_price = "10";                  //qq会员价格
    public static final String qb_price = "1";                  //q币价格
    public static final String qd_price = "1";                  //q点价格
    public static final String[] title = {"Q钻充值", "QQ会员", "Q币充值", "Q点充值"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq);
        init();
        setTabClick();
        setImage();
        setViewPager();
        getNews();
        imageClick();
    }

    private void init() {
        ViewUtils.inject(this);
        map = new HashMap<String, String>();
        listFragment = new ArrayList<Fragment>();

        //q钻fragment
        QzuanFragment ft1 = QzuanFragment.newInstance();
        Bundle b1 = new Bundle();
        b1.putString("qzPrice", qz_price);
        ft1.setArguments(b1);

        //qq会员fragment
        QhuiYuanFragment ft2 = QhuiYuanFragment.newInstance();
        Bundle b2 = new Bundle();
        b2.putString("qhPrice", qh_price);
        ft2.setArguments(b2);

        //q币fragment
        QbFragment ft3 = QbFragment.newInstance();
        Bundle b3 = new Bundle();
        b3.putString("qbprice", qb_price);
        ft3.setArguments(b3);

        //q点fragment
        QdFragment ft4 = QdFragment.newInstance();
        Bundle b4 = new Bundle();
        b4.putString("qdprice", qd_price);
        ft4.setArguments(b4);
        listFragment.add(ft1);
        listFragment.add(ft2);
        listFragment.add(ft3);
        listFragment.add(ft4);
        viewPagerAdapter = new QQViewPagerAdapter(getSupportFragmentManager(), listFragment);
        qq_pager.setAdapter(viewPagerAdapter);
        dot = new ArrayList<View>();
    }

    //点击tab切换不同充值页面
    private void setTabClick() {
        tv_qqZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_title_name.setText(tv_qqZ.getText());
                qq_pager.setCurrentItem(0);
            }
        });
        tv_qqHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_title_name.setText(tv_qqHui.getText());
                qq_pager.setCurrentItem(1);
            }
        });
        tv_qqB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_title_name.setText(tv_qqB.getText());
                qq_pager.setCurrentItem(2);
            }
        });
        tv_qqD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_title_name.setText(tv_qqD.getText());
                qq_pager.setCurrentItem(3);
            }
        });
    }

    /**
     * 设置图片及小圆点数据
     */
    private void setImage() {
        //设置小圆点
        dot.add(point_0);
        dot.add(point_1);
        dot.add(point_2);
        dot.add(point_3);
    }

    private void setViewPager() {
        qq_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_title_name.setText(title[position]);
                dot.get(oldPosition).setBackgroundResource(R.drawable.dot_disable);
                dot.get(position).setBackgroundResource(R.drawable.dot_enable);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getNews() {

        tv_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopWindows sw = new ShowPopWindows(QQActivity.this);
                sw.showPopupWindow(v);
            }
        });

    }

    //接收qzfragment传回的数据
    @Override
    public void onClick(Map<String, String> map) {
        qzType = map.get("qzType");
        qzNumber = map.get("qzNumber");
        qzPrice = (Integer.parseInt(qzNumber) * Integer.parseInt(qz_price)) + "";
        Log.i("123", "qzType=" + qzType + "qzNumber=" + qzNumber + "qzprice=" + qzPrice);
//
    }

    @Override
    public void onClickQd(Map<String, String> map) {
        qdNumber = map.get("qdNumber");
        qdPrice = (Integer.parseInt(qd_price) * Integer.parseInt(qdNumber)) + "";
        Log.i("123", "qdNumber=" + qdNumber + "qdPrice=" + qdPrice);
    }

    @Override
    public void onClickQhui(Map<String, String> map) {
        qhType = map.get("qhType");
        qhNumber = map.get("qhNumber");
        qhPrice = (Integer.parseInt(qhNumber) * Integer.parseInt(qh_price)) + "";
        Log.i("123", "qhType=" + qhType + "qhNumber=" + qhNumber + "qhprice=" + qhPrice);
    }

    @Override
    public void onClickQb(Map<String, String> map) {
        qbNumber = map.get("qbNumber");
        qbPrice = (Integer.parseInt(qb_price) * Integer.parseInt(qbNumber)) + "";
        Log.i("123", "qbNumber=" + qbNumber + "qbPrice=" + qbPrice);
    }

    //标签栏按钮设置
    private void imageClick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
