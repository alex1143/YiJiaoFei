package com.example.dxkj.YiJiaoFei;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 流量充值页面
 * Created by dxkj on 2015/9/15.
 */
public class LiuLiangActivity extends AppCompatActivity {

    @ViewInject(R.id.edt_phone)
    private EditText edtPhone;                       //需要充值的号码
    @ViewInject(R.id.liuliang)
    private Spinner spinner_Liu;                      //流量套餐
    @ViewInject(R.id.liuliangPhone)
    TextView liuliangPhone;                           //订单中电话号码
    @ViewInject(R.id.liuliangType)
    private TextView liuliangType;                   //订单信息中流量类型
    @ViewInject(R.id.liuliangDate)
    private TextView liuliangDate;                   //订单信息中生效日期
    @ViewInject(R.id.liuliang2)
    private TextView tv_liu;                         //订单信息中流量数值
    @ViewInject(R.id.rad_type)
    private RadioGroup radType;
    @ViewInject(R.id.rad_date)
    private RadioGroup radDate;
    @ViewInject(R.id.liuliangPhone)
    private TextView tv_phone;
    @ViewInject(R.id.tv_liuliang_clear)
    private TextView tv_clear;
    @ViewInject(R.id.top_head2)
    ImageView imageView;

    private ArrayAdapter adapter;
    private static final String[] data = {"请选择","20M", "50M", "100M", "200M", "500M","1024M"};
    public String phoneNumber;         //手机号码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liu_liang);
        init();
        setRadioGroup();
        setSpinner();
        imageClick();
    }

    public void init() {
        ViewUtils.inject(this);
        if (edtPhone.getText().equals("")){

        }else {
            //点击x清除信息
            tv_clear.setVisibility(View.VISIBLE);
            tv_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edtPhone.setText("");
                    tv_clear.setVisibility(View.INVISIBLE);
                }
            });
        }
        //获取本机手机号码
        TelephonyManager phoneMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        phoneNumber = phoneMgr.getLine1Number();
        edtPhone.setText(phoneNumber);
        tv_phone.setText(phoneNumber);

        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                liuliangPhone.setText(edtPhone.getText());
                tv_clear.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setRadioGroup() {
        radType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) LiuLiangActivity.this.findViewById(id);
                liuliangType.setText(rb.getText());
            }
        });
        radDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) LiuLiangActivity.this.findViewById(id);
                liuliangDate.setText(rb.getText());
            }
        });
    }

    public void setSpinner() {
        adapter = new ArrayAdapter(this,R.layout.spinnerlist, data);
        spinner_Liu.setAdapter(adapter);
        spinner_Liu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_liu.setText(data[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_liuliang_commit:
                Toast.makeText(getApplicationContext(),"提交",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_liuliang_resert:
                edtPhone.setText("");
                tv_liu.setText("");
                liuliangDate.setText("");
                liuliangType.setText("");
                spinner_Liu.setSelection(0);
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
