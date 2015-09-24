package com.example.dxkj.fargment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dxkj.YiJiaoFei.R;
import com.example.dxkj.adapter.RecylcerViewAdapter;
import com.example.dxkj.bean.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 首页公告2
 * Created by dxkj on 2015/9/23.
 */
public class HomeFragment2 extends Fragment {

    private static HomeFragment2 mHomeFragment2;
    private Context context;

    private View mView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecylcerViewAdapter mAdapter;

    private List<String> list;

    private Users user;
    private List<Map<String, Object>> listdata;

    public static HomeFragment2 newInstance() {
        if (mHomeFragment2 == null) {
            mHomeFragment2 = new HomeFragment2();
        }
        return mHomeFragment2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_chat2, container, false);
        initData();
        initView();
        return mView;
    }

    public void initData() {
        list = new ArrayList<>();
        context = this.getActivity().getApplication();
        for (int i = 0; i < 20; i++) {
            list.add("公告=====》" + i);
        }
    }

    public void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.my_recycler_view);
        //创建默认的LinearLayoutManager
        mLinearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecylcerViewAdapter(list, context);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
