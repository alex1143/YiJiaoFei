package com.example.dxkj.fargment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dxkj.YiJiaoFei.DianKaActivity;
import com.example.dxkj.YiJiaoFei.GamesActivity;
import com.example.dxkj.YiJiaoFei.GuHuaActivity;
import com.example.dxkj.YiJiaoFei.KaMiActivity;
import com.example.dxkj.YiJiaoFei.KaQuanActivity;
import com.example.dxkj.YiJiaoFei.LiuLiangActivity;
import com.example.dxkj.YiJiaoFei.PhoneActivity;
import com.example.dxkj.YiJiaoFei.QQActivity;
import com.example.dxkj.YiJiaoFei.R;
import com.example.dxkj.YiJiaoFei.ShuiDianMeiActivity;
import com.example.dxkj.adapter.GridViewAdapter;

import java.util.List;
import java.util.Map;

/**
 * 商品页面
 * Created by dxkj on 2015/9/11.
 */
public class GoodsFragment extends Fragment {

    private Context context;
    private GridView gridView;
    private View view;
    private GridViewAdapter gridViewAdapter;
    private List<Map<String, Integer>> list;
    private String[] str_name = {"手机充值", "卡券", "固话", "卡密", "q币充值", "流量充值",
            "水电煤", "游戏充值", "点卡充值"};
    private Integer[] imgs = {R.drawable.shouji, R.drawable.kaquan, R.drawable.guhua, R.drawable.kami,
            R.drawable.qq, R.drawable.liiulaing, R.drawable.shuidianmei, R.drawable.youxi, R.drawable.dianka};
    private Handler handler = new Handler();

    private static GoodsFragment mGoodsFragment;

    public static GoodsFragment newInstance() {
        if (mGoodsFragment == null) {
            mGoodsFragment = new GoodsFragment();
        }
        return mGoodsFragment;
    }

    /**
     * 延时加载
     */
    private Runnable LOAD_DATA = new Runnable() {
        @Override
        public void run() {
            setGridClickListener();
//            closeProgressDialog();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goods, container, false);
//        showProgressDialog();

        initView();
        handler.postDelayed(LOAD_DATA, 500);
        return view;
    }

    private void initView() {
        context = this.getActivity().getApplication();
        gridView = (GridView) view.findViewById(R.id.gr_goods);
        gridViewAdapter = new GridViewAdapter(context, str_name, imgs);
        gridView.setAdapter(gridViewAdapter);
    }

    public void setGridClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent();
                        intent.setClass(context, PhoneActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent();
                        intent1.setClass(context, KaQuanActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent();
                        intent2.setClass(context, GuHuaActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent();
                        intent3.setClass(context, KaMiActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent();
                        intent4.setClass(context, QQActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent();
                        intent5.setClass(context, LiuLiangActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent();
                        intent6.setClass(context, ShuiDianMeiActivity.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent();
                        intent7.setClass(context, GamesActivity.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent();
                        intent8.setClass(context, DianKaActivity.class);
                        startActivity(intent8);
                        break;
                }
            }
        });
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
