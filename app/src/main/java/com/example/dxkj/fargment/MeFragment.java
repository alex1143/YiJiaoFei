package com.example.dxkj.fargment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.dxkj.YiJiaoFei.FinanceActivity;
import com.example.dxkj.YiJiaoFei.LoginActivity;
import com.example.dxkj.YiJiaoFei.R;
import com.example.dxkj.bean.Users;
import com.example.dxkj.cache.MapCache;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 个人信息页面
 * Created by sh on 2015/9/16.
 */
public class MeFragment extends Fragment {

    /**
     * 卡密订单
     */
    @ViewInject(R.id.finance)
    private LinearLayout financeLayout;

    @ViewInject(R.id.gotolonginLayout)
    private LinearLayout gotolonginLayout;
    /**
     * 跳转登陆页面按钮
     */
    @ViewInject(R.id.gotoLongin)
    private Button gotoLongin;
    @ViewInject(R.id.ripple)
    private View view2;

    @ViewInject(R.id.userid)
    private TextView useridText;
    private Context context;

    private static MeFragment mMeFragment;

    SharedPreferences sharedPre;
    String userphone;
    String password;

    public static MeFragment newInstance() {
        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
        }
        return mMeFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.me_fragment, container, false);
        ViewUtils.inject(this, view);
        context = this.getActivity().getApplication();
        MaterialRippleLayout.on(view2)
                .rippleColor(Color.YELLOW)
                .rippleAlpha(0.9f)
                .rippleHover(true)
                .create();
        Users user = null;
        try {
         /* ACache cache= ACache.get(ContextUtil.getInstance());*/
       /*  user = (Users) cache.getAsObject("userObject");*/
            user = (Users) MapCache.cacheMap.get("userObject");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("错误", e.getMessage() + e.getLocalizedMessage());
        }
        if (user != null) {
            gotolonginLayout.setVisibility(View.INVISIBLE);//隐藏
            useridText.setText("您好：" + user.getUserid());
        }
        financeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FinanceActivity.class);
                startActivity(intent);

            }
        });

        gotoLongin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    /**
     * 读取SharedPreferences中的用户信息
     */
    public void readUserNews() {
        sharedPre = context.getSharedPreferences("loginNew", Context.MODE_PRIVATE);
        userphone = sharedPre.getString("userphone", "");
        password = sharedPre.getString("password", "");
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    public Boolean isLogin() {

        return true;
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
