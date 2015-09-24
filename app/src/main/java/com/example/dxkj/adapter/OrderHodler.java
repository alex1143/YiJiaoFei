package com.example.dxkj.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dxkj.YiJiaoFei.R;

/**
 * Created by sh on 2015/9/18.
 */
public class OrderHodler {
    TextView phoneNum = null;
    TextView money = null;
    TextView state =null;

    View view333;
    public OrderHodler(View view2){
        this.view333=view2;
         phoneNum = (TextView) view333.findViewById(R.id.phoneNum);
         money = (TextView) view333.findViewById(R.id.money);
         state = (TextView) view333.findViewById(R.id.orderstate);
        if(view333==null){
            Log.e("234", "view为空");
        }
        Log.e("234", "优质");
    }


}
