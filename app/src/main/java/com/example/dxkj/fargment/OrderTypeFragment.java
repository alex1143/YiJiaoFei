package com.example.dxkj.fargment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dxkj.YiJiaoFei.LoginActivity;
import com.example.dxkj.YiJiaoFei.OrderListActivity;
import com.example.dxkj.YiJiaoFei.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by sh on 2015/9/14.
 */
public class OrderTypeFragment extends Fragment {
    /**
     * 手机订单
     */
    @ViewInject(R.id.phone)
    private LinearLayout phoneList;
    /**
     * 腾讯订单
     */
    @ViewInject(R.id.tencent)
    private LinearLayout tencentList;
    /**
     * 流量订单
     */
    @ViewInject(R.id.gprs)
    private LinearLayout gprsList;
    /**
     * 卡密订单
     */
    @ViewInject(R.id.card)
    private LinearLayout cardList;
    /**
     * 礼品卡订单
     */
    @ViewInject(R.id.giftcard)
    private LinearLayout giftcardList;

    private Context context;

    private static OrderTypeFragment mOrderTypeFragment;

    public static OrderTypeFragment newInstance() {
        if (mOrderTypeFragment == null) {
            mOrderTypeFragment = new OrderTypeFragment();
        }
        return mOrderTypeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_list_fragment, container, false);
        context = this.getActivity().getApplication();
        ViewUtils.inject(this, view);
        setOnclick();
        return view;
    }

    /**
     * 跳转触发监听
     */
    public void setOnclick() {
        phoneList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOrderList(1);
            }
        });
        tencentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOrderList(2);
            }
        });
        gprsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOrderList(3);
            }
        });
        cardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOrderList(4);
            }
        });
        giftcardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOrderList(5);
            }
        });
    }

    /**
     * 根据订单那类型跳转Activity
     *
     * @param type
     */
    public void gotoOrderList(Integer type) {
        //判断登录状态：true 已登录 false 未登录
        boolean isLogin = false;
        if (isLogin) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        } else {
            Intent intent = new Intent(getActivity(), OrderListActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
            getActivity().finish();
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
}
