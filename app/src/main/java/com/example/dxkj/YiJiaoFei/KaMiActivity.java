package com.example.dxkj.YiJiaoFei;

import android.os.Bundle;
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
 * 卡密订单页面
 * Created by dxkj on 2015/9/15.
 */
public class KaMiActivity extends AppCompatActivity {

    @ViewInject(R.id.spinner_kami_type)
    private Spinner spinnerKaMiType;            //卡密类型
    @ViewInject(R.id.spinner_kami_money)
    private Spinner spinnerKaMiMoney;        //卡密金额
    @ViewInject(R.id.protuct_out)
    private TextView tv_out;                    //剩余数量
    @ViewInject(R.id.protuct_number)
    private EditText edt_number;                 //购买数量
    @ViewInject(R.id.tv_show)
    private TextView tv_show;
    @ViewInject(R.id.goods_type)
    private TextView tv_type;                   //订单信息中产品类型
    @ViewInject(R.id.goods_number)
    private TextView goods_number;              //产品数量
    @ViewInject(R.id.goods_money)
    private TextView goods_money;               //产品价格
    @ViewInject(R.id.llayout)
    private LinearLayout llayout;
    @ViewInject(R.id.tv_hide)
    private TextView tv_hide;
    @ViewInject(R.id.top_head2)
    private ImageView imageView;

    private ArrayAdapter adapterType;
    private ArrayAdapter adapterMoney;
    private static final String[] data = {"A型", "B型", "O型", "AB型", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ka_mi);
        init();
        work();
        imageClick();
    }

    public void init(){
        ViewUtils.inject(this);
    }

    public void work(){
        adapterType = new ArrayAdapter(this, R.layout.spinnerlist, data);
        adapterMoney = new ArrayAdapter(this, R.layout.spinnerlist, data);
        spinnerKaMiType.setAdapter(adapterType);   //商品类型
        spinnerKaMiMoney.setAdapter(adapterMoney);  //商品价格

        //下拉菜单监听
        spinnerKaMiType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_type.setText(data[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerKaMiMoney.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                goods_money.setText(data[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        goods_number.setText(edt_number.getText());
        //设置是否可见
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
        //edittext监听改变订单信息
        edt_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                goods_number.setText(edt_number.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_kami_commit:
                Toast.makeText(getApplicationContext(), "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_kami_resert:
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
