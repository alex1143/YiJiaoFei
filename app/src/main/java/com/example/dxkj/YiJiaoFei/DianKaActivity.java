package com.example.dxkj.YiJiaoFei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 点卡充值页面
 * Created by dxkj on 2015/9/11.
 */
public class DianKaActivity extends AppCompatActivity {

    @ViewInject(R.id.top_head2)
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_ka);
        init();
        imageClick();
    }

    public void init(){
        ViewUtils.inject(this);
    }
    //标签栏按钮设置
    public void imageClick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
