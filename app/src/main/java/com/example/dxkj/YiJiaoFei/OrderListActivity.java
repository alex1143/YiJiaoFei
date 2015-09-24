package com.example.dxkj.YiJiaoFei;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dxkj.adapter.OrderListAdapter;
import com.example.dxkj.bean.OrderBean;
import com.example.dxkj.bean.ReturnOrder;
import com.example.dxkj.bean.Users;
import com.example.dxkj.cache.MapCache;
import com.example.dxkj.utils.BitmapCompressTools;
import com.example.dxkj.utils.Constant;
import com.example.dxkj.utils.HttpCallbackListener;
import com.example.dxkj.utils.HttpUtils;
import com.example.dxkj.utils.OrderStateUtils;
import com.example.dxkj.utils.ProductUtils;
import com.example.dxkj.utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单列表
 * Created by sh on 2015/9/15.
 */
public class OrderListActivity extends Activity {
    /**
     * Viewpager里所有view的集合
     */
    ArrayList<View> viewContainter = new ArrayList<View>();
    /**
     * 话费订单list
     */
    List<OrderBean> list = new ArrayList<OrderBean>();
    /**
     * 腾旭订单list
     */
    List<OrderBean> tencetList = new ArrayList<OrderBean>();
    /**
     * 流量订单list
     */
    List<OrderBean> gprsList = new ArrayList<OrderBean>();
    /**
     * 卡密订单list
     */
    List<OrderBean> cardList = new ArrayList<OrderBean>();
    /**
     * 礼品卡订单list
     */
    List<OrderBean> giftCardList = new ArrayList<OrderBean>();

    private ViewPager mViewPager;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_pager);
        mViewPager = (ViewPager) findViewById(R.id.vp_list);
        imageView = (ImageView) findViewById(R.id.top_head2);
        //初始化下拉刷新
        initPull();
        imageClick();
        mViewPager.setAdapter(new PagerAdapter() {

            //viewpager中的组件数量
            @Override
            public int getCount() {
                return viewContainter.size();
            }

            //滑动切换的时候销毁当前的组件
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }

            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return null;
            }

        });
        //获取 进入列表的订单类型
        Integer type = getIntent().getExtras().getInt("type");
        //设置第一次显示的订单页面
        mViewPager.setCurrentItem(type - 1);
        Bitmap bitmap = BitmapCompressTools.decodeSampledBitmapFromResource(getResources(), R.drawable.orderback, 300, 400);
        Drawable drawable = new BitmapDrawable(bitmap);
        mViewPager.setBackground(drawable);

    }

    /**
     * 初始化
     */
    public void initPull() {
        initPullPhone();
        initPullTemcent();
        initPullGprs();
        initPullCard();
        initPullGifCard();
    }

    /**
     * 手机下拉框初始化
     */
    public void initPullPhone() {

        final View plv1 = (View) LayoutInflater.from(this).inflate(
                R.layout.layout_listview_in_viewpager, null);
        //设置头部
        TextView ordertitle = (TextView) plv1.findViewById(R.id.ordertitle);
        ordertitle.setText("手机订单列表");
        /**
         * 初始化下拉框
         */
        initSpinner(1, plv1);
        //订单详细视图
        final RelativeLayout orderinfo = (RelativeLayout) plv1.findViewById(R.id.orderinfo);
        //设置订单详细视图属性
        setRelativeState(orderinfo);

        /**
         * 下拉刷新
         */
        final PullToRefreshListView pull = (PullToRefreshListView) plv1.findViewById(R.id.pulltorefreshList);
        pull.setMode(PullToRefreshBase.Mode.BOTH);//支持上拉也支持下拉
        pull.setScrollingWhileRefreshingEnabled(true);//滚动的时候不加载数据
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //刷新
                loadDatas(pull.getScrollY() < 0, 1, pull, plv1);
            }
        });
        /**
         * ListView点击事件
         */
        pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    TextView orderid = (TextView) plv1.findViewById(R.id.orderid);
                    TextView account = (TextView) plv1.findViewById(R.id.account);
                    TextView money = (TextView) plv1.findViewById(R.id.ordermoney);
                    TextView ordertype = (TextView) plv1.findViewById(R.id.ordertype);
                    TextView orderidstate = (TextView) plv1.findViewById(R.id.orderidstate);
                    TextView beginTime = (TextView) plv1.findViewById(R.id.beginTime);
                    TextView endTime = (TextView) plv1.findViewById(R.id.endTime);
                    //获取 list里的order
                    OrderBean orderBean = list.get(position - 1);
                    orderid.setText("订单号：" + orderBean.getOrderid());
                    account.setText("充值账号：" + orderBean.getPhoneNum());
                    money.setText("充值金额：￥" + orderBean.getMoney());
                    orderidstate.setText("充值状态：" + OrderStateUtils.getOrderState(orderBean.getOrderType(), orderBean.getState()));
                    ordertype.setText("充值类型：" + ProductUtils.getProductType(orderBean.getOrderType(), orderBean.getProductType()));
                    beginTime.setText("开始时间：" + TimeUtils.getStrTime(orderBean.getBeginTime()));
                    endTime.setText("处理时间：" + TimeUtils.getStrTime(orderBean.getEndTime()));
                    orderinfo.setVisibility(View.VISIBLE);//显示图片
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        viewContainter.add(plv1);
        loadDatas(true, 1, pull, plv1);
    }

    public void initPullGprs() {

        final View plv = (View) LayoutInflater.from(this).inflate(
                R.layout.layout_listview_in_viewpager, null);
        TextView ordertitle = (TextView) plv.findViewById(R.id.ordertitle);
        ordertitle.setText("流量订单列表");

        /**
         * 初始化下拉框
         */
        initSpinner(3, plv);

        //订单详细视图
        final RelativeLayout orderinfo = (RelativeLayout) plv.findViewById(R.id.orderinfo);
        //设置订单详细视图属性
        setRelativeState(orderinfo);
        /**
         * 下拉刷新
         */
        final PullToRefreshListView pull = (PullToRefreshListView) plv.findViewById(R.id.pulltorefreshList);
        pull.setMode(PullToRefreshBase.Mode.BOTH);//支持上拉也支持下拉
        pull.setScrollingWhileRefreshingEnabled(true);//滚动的时候不加载数据
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadDatas(pull.getScrollY() < 0, 3, pull, plv);
            }
        });
        /**
         * ListView点击事件
         */
        pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    TextView orderid = (TextView) plv.findViewById(R.id.orderid);
                    TextView account = (TextView) plv.findViewById(R.id.account);
                    TextView money = (TextView) plv.findViewById(R.id.ordermoney);
                    TextView ordertype = (TextView) plv.findViewById(R.id.ordertype);
                    TextView orderidstate = (TextView) plv.findViewById(R.id.orderidstate);
                    TextView beginTime = (TextView) plv.findViewById(R.id.beginTime);
                    TextView endTime = (TextView) plv.findViewById(R.id.endTime);
                    //获取 list里的order
                    OrderBean orderBean = list.get(position - 1);
                    orderid.setText("订单号：" + orderBean.getOrderid());
                    account.setText("充值账号：" + orderBean.getPhoneNum());
                    money.setText("充值金额：￥" + orderBean.getMoney());
                    orderidstate.setText("充值状态：" + OrderStateUtils.getOrderState(orderBean.getOrderType(), orderBean.getState()));
                    ordertype.setText("充值类型：" + ProductUtils.getProductType(orderBean.getOrderType(), orderBean.getProductType()));
                    beginTime.setText("开始时间：" + TimeUtils.getStrTime(orderBean.getBeginTime()));
                    endTime.setText("处理时间：" + TimeUtils.getStrTime(orderBean.getEndTime()));
                    orderinfo.setVisibility(View.VISIBLE);//显示图片
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        viewContainter.add(plv);
    }

    public void initPullTemcent() {

        final View plv2 = (View) LayoutInflater.from(this).inflate(
                R.layout.layout_listview_in_viewpager, null);
        TextView ordertitle2 = (TextView) plv2.findViewById(R.id.ordertitle);
        ordertitle2.setText("腾旭订单列表");
        /**
         * 初始化下拉框
         */
        initSpinner(2, plv2);


        //订单详细视图
        final RelativeLayout orderinfo = (RelativeLayout) plv2.findViewById(R.id.orderinfo);
        //设置订单详细视图属性
        setRelativeState(orderinfo);
        /**
         * 下拉刷新
         */

        final PullToRefreshListView pull2 = (PullToRefreshListView) plv2.findViewById(R.id.pulltorefreshList);
        pull2.setMode(PullToRefreshBase.Mode.BOTH);//支持上拉也支持下拉
        pull2.setScrollingWhileRefreshingEnabled(true);//滚动的时候不加载数据
        pull2.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadDatas(pull2.getScrollY() < 0, 2, pull2, plv2);
            }
        });

        /**
         * ListView点击事件
         */
        pull2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    TextView orderid = (TextView) plv2.findViewById(R.id.orderid);
                    TextView account = (TextView) plv2.findViewById(R.id.account);
                    TextView money = (TextView) plv2.findViewById(R.id.ordermoney);
                    TextView ordertype = (TextView) plv2.findViewById(R.id.ordertype);
                    TextView orderidstate = (TextView) plv2.findViewById(R.id.orderidstate);
                    TextView beginTime = (TextView) plv2.findViewById(R.id.beginTime);
                    TextView endTime = (TextView) plv2.findViewById(R.id.endTime);
                    //获取 list里的order
                    OrderBean orderBean = list.get(position - 1);
                    orderid.setText("订单号：" + orderBean.getOrderid());
                    account.setText("充值账号：" + orderBean.getPhoneNum());
                    money.setText("充值金额：￥" + orderBean.getMoney());
                    orderidstate.setText("充值状态：" + OrderStateUtils.getOrderState(orderBean.getOrderType(), orderBean.getState()));
                    ordertype.setText("充值类型：" + ProductUtils.getProductType(orderBean.getOrderType(), orderBean.getProductType()));
                    beginTime.setText("开始时间：" + TimeUtils.getStrTime(orderBean.getBeginTime()));
                    endTime.setText("处理时间：" + TimeUtils.getStrTime(orderBean.getEndTime()));
                    orderinfo.setVisibility(View.VISIBLE);//显示图片
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        viewContainter.add(plv2);

    }

    public void initPullCard() {

        final View plv = (View) LayoutInflater.from(this).inflate(
                R.layout.layout_listview_in_viewpager, null);
        TextView ordertitle = (TextView) plv.findViewById(R.id.ordertitle);
        ordertitle.setText("卡密订单列表");

        /**
         * 初始化下拉框
         */
        initSpinner(4, plv);

        //订单详细视图
        final RelativeLayout orderinfo = (RelativeLayout) plv.findViewById(R.id.orderinfo);
        //设置订单详细视图属性
        setRelativeState(orderinfo);
        /**
         * 下拉刷新
         */

        final PullToRefreshListView pull = (PullToRefreshListView) plv.findViewById(R.id.pulltorefreshList);
        pull.setMode(PullToRefreshBase.Mode.BOTH);//支持上拉也支持下拉
        pull.setScrollingWhileRefreshingEnabled(true);//滚动的时候不加载数据
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadDatas(pull.getScrollY() < 0, 4, pull, plv);
            }
        });
        viewContainter.add(plv);
    }

    public void initPullGifCard() {

        final View plv = (View) LayoutInflater.from(this).inflate(
                R.layout.layout_listview_in_viewpager, null);
        TextView ordertitle = (TextView) plv.findViewById(R.id.ordertitle);
        ordertitle.setText("礼品卡订单列表");

        /**
         * 初始化下拉框
         */
        initSpinner(5, plv);

        //订单详细视图
        final RelativeLayout orderinfo = (RelativeLayout) plv.findViewById(R.id.orderinfo);
        //设置订单详细视图属性
        setRelativeState(orderinfo);
        /**
         * 下拉刷新
         */

        final PullToRefreshListView pull = (PullToRefreshListView) plv.findViewById(R.id.pulltorefreshList);
        pull.setMode(PullToRefreshBase.Mode.BOTH);//支持上拉也支持下拉
        pull.setScrollingWhileRefreshingEnabled(true);//滚动的时候不加载数据
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadDatas(pull.getScrollY() < 0, 5, pull, plv);
            }
        });
        viewContainter.add(plv);
    }


    private int page, size = 30, count;// 初始化数据

    public void loadDatas(final boolean reflush, Integer type, final PullToRefreshListView listGoods, final View views) {
        if (reflush) {
            page = 1;
        } else {
            page++;
        }

        Users user = (Users) MapCache.cacheMap.get("userObject");
        if (user == null) {
            startActivity(new Intent(OrderListActivity.this, LoginActivity.class));
            finish();
        } else {
            String params = "userToken=" + user.getUserToken();
            HttpUtils.sendHttpRequest(Constant.ORDER_URL, params, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {

                    // 获取传递的对象中封装的内容
                    final String response2 = response;
                    Log.e("123", response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            ReturnOrder returnOrder = gson.fromJson(
                                    response2,
                                    new TypeToken<ReturnOrder>() {
                                    }.getType());
                            list = returnOrder.getList();
                            if (returnOrder.getList().size() == 0) {
                                Toast.makeText(OrderListActivity.this, "没有订单", Toast.LENGTH_SHORT)
                                        .show();
                                return;
                            }
                            OrderListAdapter adapter = new OrderListAdapter(OrderListActivity.this, R.layout.order_list,
                                    returnOrder.getList());
                            if (reflush) {//下拉刷新
                                listGoods.setAdapter(adapter);
                                listGoods.onRefreshComplete();//停止刷新
                            } else {//加载更多

                                List<OrderBean> list2 = new ArrayList<OrderBean>();
                                for (int i = 0; i < 10; i++) {
                                    list.add(new OrderBean("13361321206", 2, 6.0));
                                }
                                list.addAll(list2);
                                adapter.notifyDataSetChanged();
                                listGoods.onRefreshComplete();//停止刷新
                            }
                            if (count == page) {//没有更多的数据显示
                                listGoods.setMode(PullToRefreshBase.Mode.PULL_FROM_START);//只能刷新
                            }
                        }
                    });
                }

                @Override
                public void onError(Exception e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listGoods.onRefreshComplete();//停止刷新
                            Toast.makeText(OrderListActivity.this, "查询失败", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            });
        }
    }

    /**
     * 初始化下拉框
     *
     * @param type
     * @param v
     */
    public void initSpinner(int type, View v) {

        Spinner spinner = (Spinner) v.findViewById(R.id.Spinner01);
        Spinner spinner2 = (Spinner) v.findViewById(R.id.Spinner02);
        String[] m = null;
        String[] m2 = null;
        switch (type) {

            case 1:
                m = new String[]{"全部", "充值成功", "充值失败", "充值中"};
                m2 = new String[]{"全部", "移动", "联通", "电信", "固话", "虚拟运营商"};
                break;
            case 2:
                m = new String[]{"全部", "充值成功", "充值失败", "充值中"};
                m2 = new String[]{"全部", "Q币", "Q点", "会员", "砖"};
                break;
            case 3:
                m = new String[]{"全部", "充值成功", "充值失败", "充值中"};
                m2 = new String[]{"全部", "移动", "联通", "电信"};
                break;
            case 4:
                m = new String[]{"全部", "提取成功", "提取失败"};
                m2 = new String[]{"全部", "移动", "联通", "电信"};
                break;
            case 5:
                m = new String[]{"全部", "提取成功", "提取失败"};
                m2 = new String[]{"全部", "京东", "苏宁", "中石化"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m2);
        //设置下拉列表的风格
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner2.setAdapter(adapter2);
    }

    /**
     * 设置订单详细布局属性
     *
     * @param orderinfo
     */
    public void setRelativeState(final RelativeLayout orderinfo) {
        orderinfo.setVisibility(View.INVISIBLE);//隐藏图片
        orderinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderinfo.setVisibility(View.INVISIBLE);//隐藏图片
            }
        });
    }

    //标签栏按钮设置
    public void imageClick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //回退键设置
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}