package com.example.dxkj.fargment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dxkj.YiJiaoFei.R;

import java.util.ArrayList;
import java.util.List;

/**
 * slidingmenu菜单页面
 * Created by dxkj on 2015/9/10.
 */
public class MenuFragment extends Fragment {

    private ListView lv_select;
    private TextView user_name;
    private ArrayAdapter<String> adapter;
    private List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_layout, container, false);
        lv_select = (ListView) view.findViewById(R.id.lv_select);
        user_name = (TextView) view.findViewById(R.id.user_name);
        initData();
        setSlidingMenu();
        return view;
    }

    public void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i <= 5; i++) {
            list.add(i + "beijing");
        }
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        lv_select.setAdapter(adapter);
    }

    public void setSlidingMenu() {
        user_name.setText("alex");
    }
}
