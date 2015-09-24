package com.example.dxkj.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.PopupWindow;

import com.example.dxkj.YiJiaoFei.R;

/**
 * popwindows
 * Created by dxkj on 2015/9/16.
 */
public class ShowPopWindows {

    private Context context;
    public ShowPopWindows(Context context){
        this.context = context;
    }

    public void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.pop_windows, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.abc_ab_share_pack_mtrl_alpha));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }

}
