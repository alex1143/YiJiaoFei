package com.example.dxkj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.dxkj.bean.OrderBean;

import java.util.List;

/**
 * Created by sh on 2015/9/14.
 */
public class OrderListAdapter extends ArrayAdapter<OrderBean>  {
    private int resourceId;

    public OrderListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public OrderListAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public OrderListAdapter(Context context, int resource, OrderBean[] objects) {
        super(context, resource, objects);
    }

    public OrderListAdapter(Context context, int resource, int textViewResourceId, OrderBean[] objects) {
        super(context, resource, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public OrderListAdapter(Context context, int resource, List<OrderBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    public OrderListAdapter(Context context, int resource, int textViewResourceId, List<OrderBean> objects) {
        super(context, resource, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    OrderBean orderBean = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        orderBean = (OrderBean) getItem(position);
/*        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);*/
/*        TextView phoneNum = (TextView) view.findViewById(R.id.phoneNum);
        TextView money = (TextView) view.findViewById(R.id.money);
        TextView state = (TextView) view.findViewById(R.id.orderstate);*/

        String state = null;
        OrderHodler holder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    resourceId, null);
            if(convertView==null){
                Log.e("234","convertView为空");
            }

            holder = new OrderHodler(convertView);
            convertView.setTag(holder);
        }else {
            holder = (OrderHodler) convertView.getTag();
        }
      switch (orderBean.getState()){
            case 1:state ="成功";
                break;
            case 2:state ="失败";
                holder.state.setTextColor(Color.RED);
                break;
            case 3:state ="充值中";
                break;
            default:state ="未知";
        }


        holder.state.setText(state);
        holder.phoneNum.setText(orderBean.getPhoneNum());
        holder.money.setText("￥ "+orderBean.getMoney() + "");
        return convertView;
    }



}
