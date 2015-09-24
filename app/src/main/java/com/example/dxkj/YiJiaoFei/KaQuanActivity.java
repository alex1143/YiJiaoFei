package com.example.dxkj.YiJiaoFei;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * 卡券订单页面
 * Created by dxkj on 2015/9/15.
 */
public class KaQuanActivity extends AppCompatActivity {

    @ViewInject(R.id.top_head2)
    private ImageView imageView;               //标签栏箭头
    @ViewInject(R.id.read)
    private TextView tv_read;                  //使用阅读
    @ViewInject(R.id.spinner_type)
    private Spinner spinner_type;              //产品类型
    @ViewInject(R.id.spinner_product)
    private Spinner spinner_product;           //购买的产品
    @ViewInject(R.id.edt_kaquan_bumber)
    private TextInputLayout put_edt_number;                //购买数量
    @ViewInject(R.id.tv_show)
    private TextView tv_show;                   //是否显示信息
    @ViewInject(R.id.goods_type)
    private TextView tv_type;                   //订单信息中产品类型
    @ViewInject(R.id.goods)
    private TextView product;                     //订单信息中产品
    @ViewInject(R.id.goods_number)
    private TextView goods_number;              //订单信息中产品数量
    @ViewInject(R.id.tv_hide)
    private TextView tv_hide;                   //隐藏
    @ViewInject(R.id.tv_kaquan_clear)
    TextView tv_clear;                          //清除
    @ViewInject(R.id.order_news)
    LinearLayout llayout;

    private EditText edt_number;
    private ArrayAdapter adapterType;
    private ArrayAdapter adapterProduct;
    private static final String[] data = {"A型", "B型", "O型", "AB型", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ka_quan);
        init();
        work();
        imageClick();
    }

    public void init() {
        ViewUtils.inject(this);
        edt_number = put_edt_number.getEditText();
        put_edt_number.setHint("请输入数量");
        if (edt_number.getText().equals("")) {
            tv_clear.setVisibility(View.INVISIBLE);
        } else {
            //点击x清除信息
            tv_clear.setVisibility(View.VISIBLE);
            tv_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt_number.setText("");
                    tv_clear.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    public void work() {

        tv_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KaQuanActivity.this);
                builder.setIcon(R.drawable.warn);
                builder.setMessage("使用须知");
                builder.create().show();
            }
        });

        tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llayout.setVisibility(View.VISIBLE);
            }
        });

        tv_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llayout.setVisibility(View.INVISIBLE);
            }
        });

        edt_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //输入信息时监听
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                goods_number.setText(edt_number.getText());
                tv_clear.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        adapterType = new ArrayAdapter(this, R.layout.spinnerlist, data);
        adapterProduct = new ArrayAdapter(this, R.layout.spinnerlist, data);
        spinner_type.setAdapter(adapterType);
        spinner_type.setVisibility(View.VISIBLE);
        spinner_product.setAdapter(adapterProduct);
        spinner_product.setVisibility(View.VISIBLE);
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_type.setText(data[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product.setText(data[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_kaquan_commit:
                Toast.makeText(getApplicationContext(), "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_kaquan_resert:
                Toast.makeText(getApplicationContext(), "重置", Toast.LENGTH_SHORT).show();
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
