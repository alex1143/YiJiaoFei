package com.example.dxkj.YiJiaoFei;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dxkj.bean.Users;
import com.example.dxkj.cache.MapCache;
import com.example.dxkj.utils.Constant;
import com.example.dxkj.utils.HttpCallbackListener;
import com.example.dxkj.utils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * 话费充值页面
 * Created by dxkj on 2015/9/15.
 */
public class PhoneActivity extends AppCompatActivity {

    @ViewInject(R.id.edt_phone_number)
    private TextInputLayout put_edt_phoneNumber;  //手机号码
    @ViewInject(R.id.edt_other_money)
    private TextInputLayout put_edt_otherMoney;   //充值其他金额
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

    private EditText edt_phoneNumber;
    private EditText edt_otherMoney;
    public String money;        //充值金额
    public String phoneNumber;         //手机号码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        init();
        setVisbility();
        imageClick();
    }

    //初始化控件
    public void init() {
        ViewUtils.inject(this);
        edt_phoneNumber = put_edt_phoneNumber.getEditText();
        edt_otherMoney = put_edt_otherMoney.getEditText();
        put_edt_phoneNumber.setHint("请输入手机号码");
        put_edt_otherMoney.setHint("输入其他金额");
        if (edt_phoneNumber.getText().equals("")) {

        } else {
            //点击x清除信息
            tv_clear.setVisibility(View.VISIBLE);
            tv_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt_phoneNumber.setText("");
                    tv_clear.setVisibility(View.INVISIBLE);
                }
            });
        }
        //获取本机手机号码
        TelephonyManager phoneMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String phone = phoneMgr.getLine1Number();
        if (!TextUtils.isEmpty(phone)) {
            phoneNumber = phone.substring(3, 14);
        }

        tv_phoneNumber.setText(phone);
        Log.i("123", "phoneNumber" + phoneNumber);
        edt_phoneNumber.setText(phone);
        money = 10 + "";
        tv_phoneMoney.setText(money);
    }

    //设置订单信息及是否显示
    public void setVisbility() {

        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llayout.setVisibility(View.VISIBLE);
                Log.i("123", "edt_phoneNumber" + edt_phoneNumber.getText());
//                tv_phoneNumber.setText(edt_phoneNumber.getText());
                //获取输入金额
                money = edt_otherMoney.getText() + "";
//                tv_phoneMoney.setText(money);

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
                tv_phoneNumber.setText("+86 " + edt_phoneNumber.getText());
                phoneNumber = edt_phoneNumber.getText() + "";   //获取手机号码用于上传
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
                money = edt_otherMoney.getText() + "";
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
                commitNews();
                break;
            case R.id.btn_enterAgain:
                edt_phoneNumber.setText("");
                edt_otherMoney.setText("");
                edt_otherMoney.setText("");
                tv_phoneNumber.setText("");
                tv_phoneMoney.setText("");
                tv_clear.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void commitNews() {
        String upPhoneNumber = phoneNumber;
        String upMoney = money;
        Users user = (Users) MapCache.cacheMap.get("userObject");
        if (user == null) {
            startActivity(new Intent(PhoneActivity.this, LoginActivity.class));
            finish();
        } else {
            String token = user.getUserToken();
            String params = "phone=" + upPhoneNumber + "&&money=" + upMoney + "&&userToken=" + token;
            showProgressDialog();
            HttpUtils.sendHttpRequest(Constant.RECHARGE_URL, params, new HttpCallbackListener() {

                @Override
                public void onFinish(String response) {
                    runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          Log.i("123", "2");
                                          closeProgressDialog();
                                          Toast.makeText(getApplication(), "提交成功", Toast.LENGTH_SHORT).show();
                                      }
                                  }
                    );
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("123", "3");
                            closeProgressDialog();
                            Toast.makeText(PhoneActivity.this,
                                    "提交失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            });
        }
    }

    /**
     * 显示进度对话框
     */
    ProgressDialog progressDialog = null;

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在提交...");
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
