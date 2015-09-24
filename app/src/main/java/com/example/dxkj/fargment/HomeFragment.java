package com.example.dxkj.fargment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.dxkj.YiJiaoFei.LoginActivity;
import com.example.dxkj.YiJiaoFei.R;
import com.example.dxkj.adapter.HomeListViewAdapter;
import com.example.dxkj.bean.Users;
import com.example.dxkj.cache.MapCache;
import com.example.dxkj.utils.Constant;
import com.example.dxkj.utils.HttpUtils;
import com.example.dxkj.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 首页窗体
 * Created by dxkj on 2015/9/11.
 */
@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {

    private View view;
    private ListView listView;
    private static HomeFragment mHomeFragment;
    private MaterialRefreshLayout materialRefreshLayout;
//    private CoordinatorLayout coordinatorLayout;                //支持Snackbar滑动取消

    private Context context;
    private HomeListViewAdapter listViewAdapter;

    private List<Map<String, Object>> list;
    private Users user;

    //分页处理（刷新）
    private String page = "1";
    private static final String size = "30";

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    break;
                case 2:
                    Snackbar snackbar = Snackbar.make(listView, "网络错误", Snackbar.LENGTH_SHORT);
                    View view = snackbar.getView();
                    ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(0xffffffff);  //设置Snackbar消息字体颜色
                    snackbar.setAction("检查网络", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                            startActivity(intent);
                        }
                    });
                    snackbar.show();
                    break;
                case 3:
                    listViewAdapter = new HomeListViewAdapter(context, list);
                    listView.setAdapter(listViewAdapter);
                    listViewAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static HomeFragment newInstance() {
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        return mHomeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        initView();
        return view;
    }


    //初始化控件并设置适配器
    public void initView() {
        context = this.getActivity().getApplication();
        listView = (ListView) view.findViewById(R.id.listview_chat);
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
//        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.container);

        materialRefreshLayout.setWaveColor(0xf90fffff);
        materialRefreshLayout.setIsOverLay(true);
        materialRefreshLayout.setWaveShow(false);
        setRefresh();
        getData();
    }

    public void setRefresh() {
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            //下拉刷新
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(context, "finish", Toast.LENGTH_SHORT).show();
                materialRefreshLayout.finishRefresh();
            }

            //上拉加载更多
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

            }
        });


    }

    //网络获取数据
    public void getData() {
        user = (Users) MapCache.cacheMap.get("userObject");
        if (user == null) {
            Snackbar snackbar = Snackbar.make(listView, "未登录....", Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(0xffffffff);  //设置Snackbar消息字体颜色
            snackbar.setAction("登录", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
            });
            snackbar.show();
        } else {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    list = new ArrayList<Map<String, Object>>();
                    String token = user.getUserToken();
                    String params = "userToken=" + token + "&&apge=" + page + "&&size=" + size;
                    byte[] data = HttpUtils.getUtils(Constant.PUBLIC_URL + "?" + params);
                    if (data != null) {
                        String news = new String(data);
                        list = JsonUtils.getJson(news);
                        Message msg = handler.obtainMessage();
                        msg.what = 3;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = handler.obtainMessage();
                        msg.what = 2;
                        handler.sendMessage(msg);
                    }
                }
            }).start();
        }
    }

    /**
     * 显示进度对话框
     */
    ProgressDialog progressDialog = null;

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
