package com.example.dxkj.YiJiaoFei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 固话充值页面
 * Created by dxkj on 2015/9/15.
 */
public class GuHuaActivity extends AppCompatActivity {

    @ViewInject(R.id.edt_phone_number)
    private EditText edt_phoneNumber;  //座机区号
    @ViewInject(R.id.edt_phone_number2)
    private EditText edt_phoneNumber2;  //固定号码
    @ViewInject(R.id.edt_other_money)
    private EditText edt_otherMoney;   //充值其他金额
    @ViewInject(R.id.tv_phoneNumber)
    private TextView tv_phoneNumber;   //订单信息手机号码
    @ViewInject(R.id.tv_phoneMoney)
    private TextView tv_phoneMoney;    //订单信息充值金额
    @ViewInject(R.id.top_head2)
    ImageView imageView;               //标签栏箭头
    @ViewInject(R.id.tv_clear)
    TextView tv_clear;                 //手机号码清除
    @ViewInject(R.id.order_news)
    LinearLayout llayout;              //显示订单信息
    @ViewInject(R.id.tv_show)
    private TextView tvShow;           //是否显示订单信息
    @ViewInject(R.id.tv_hidden)
    private TextView tvHidden;         //隐藏信息

    public String money = null;        //充值金额
    public String phoneNumber;         //手机号码
    String str  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gu_hua);
        init();
        setVisbility();
        imageClick();
    }

    //初始化控件
    public void init() {
        ViewUtils.inject(this);
        if (edt_phoneNumber2.getText().equals("")&&edt_phoneNumber.getText().equals("")){
            tv_clear.setVisibility(View.INVISIBLE);
        }else {
            //点击x清除信息
            tv_clear.setVisibility(View.VISIBLE);
            tv_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt_phoneNumber.setText("");
                    edt_phoneNumber2.setText("");
                    tv_clear.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    //设置订单信息及是否显示
    public void setVisbility() {

        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llayout.setVisibility(View.VISIBLE);
            }
        });

        //隐藏订单信息
        tvHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llayout.setVisibility(View.INVISIBLE);
            }
        });

        //输入手机号码监听改变订单信息
        edt_phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("123","edt_phoneNumber2.getText()"+edt_phoneNumber2.getText());
                str = edt_phoneNumber.getText()+"";
                tv_clear.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_phoneNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tv_phoneNumber.setText(str+"+"+ edt_phoneNumber2.getText());
                tv_clear.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //输入金额监听改变订单信息
        edt_otherMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_phoneMoney.setText(edt_otherMoney.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //设置充值金额按钮
    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_10:
                money = "10";
                tv_phoneMoney.setText(money);
                Toast.makeText(getApplicationContext(), "10", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_20:
                money = "20";
                tv_phoneMoney.setText(money);
                break;
            case R.id.btn_30:
                money = "30";
                tv_phoneMoney.setText(money);
                break;
            case R.id.btn_50:
                money = "50";
                tv_phoneMoney.setText(money);
                break;
            case R.id.btn_100:
                money = "100";
                tv_phoneMoney.setText(money);
                break;
            case R.id.btn_200:
                money = "200";
                tv_phoneMoney.setText(money);
                break;
            case R.id.btn_commit:          //提交订单处理


                Toast.makeText(getApplicationContext(), "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_enterAgain:
                edt_phoneNumber.setText("");
                edt_phoneNumber2.setText("");
                edt_otherMoney.setText("");
                edt_otherMoney.setText("");
                tv_phoneNumber.setText("");
                tv_phoneMoney.setText("");
                tv_clear.setVisibility(View.INVISIBLE);
                break;
        }
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
