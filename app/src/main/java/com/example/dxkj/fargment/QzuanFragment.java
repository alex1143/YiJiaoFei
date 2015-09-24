package com.example.dxkj.fargment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dxkj.YiJiaoFei.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Q钻充值
 * Created by dxkj on 2015/9/16.
 */
public class QzuanFragment extends Fragment {

    private View view;
    private Context context;
    private Spinner qz_type;                  //q钻类型
    private Spinner qz_number;                //q钻数量
    private TextView qz_price;                //q钻价格（activity传入）
    private TextView qz_other_number;         //q钻其他数量

    private OnItemClickedListener mylistener;  //第二步：定义属性
    private ArrayAdapter adapterType;
    private ArrayAdapter adapterNumber;
    //    private List<Map<String,String>> list;
    private Map<String, String> map;
    private static final String[] type = {"红钻", "黄钻", "绿钻", "蓝钻", "黑钻", "粉钻", "紫钻"};
    private static final String[] number = {"1", "2", "3", "4", "5", "6", "12"};

    public String type1;
    public String number1;
    public String price;

    private static QzuanFragment mqzuanFragment;

    public static QzuanFragment newInstance() {
        if (mqzuanFragment == null) {
            mqzuanFragment = new QzuanFragment();
        }
        return mqzuanFragment;
    }

    //第一步：自定义接口和接口中的抽象方法
    public interface OnItemClickedListener {
        void onClick(Map<String, String> map);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //第三步：让宿主一定要实现该Fragment的接口，否则就会抛出异常
        try {
            mylistener = (OnItemClickedListener) getActivity();

        } catch (ClassCastException e) {

            throw new ClassCastException(
                    activity.toString() + " must implement OnArticleSelectedListener"
            );
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity().getApplication();
        adapterType = new ArrayAdapter<>(context, R.layout.spinnerlist, type);
        adapterNumber = new ArrayAdapter<>(context, R.layout.spinnerlist, number);
        map = new HashMap<String, String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qz, container, false);
        init();
        setSpinner();
        setPrice();
        sendNews();
        return view;
    }

    public void init() {

        qz_type = (Spinner) view.findViewById(R.id.qz_type);
        qz_number = (Spinner) view.findViewById(R.id.qz_number);
        qz_price = (TextView) view.findViewById(R.id.qz_price);
        qz_other_number = (TextView) view.findViewById(R.id.qz_other_number);
        number1 = qz_other_number.getText() + "";
    }

    public void setSpinner() {
        qz_type.setAdapter(adapterType);
        qz_number.setAdapter(adapterNumber);
        qz_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type1 = type[position];
                sendNews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        qz_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                number1 = number[position];
                sendNews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void sendNews() {
        map.put("qzType", type1);
        map.put("qzNumber", number1);
        mylistener.onClick(map);
    }

    public void setPrice() {
        Bundle bundle = getArguments();
        price = bundle.getString("qzPrice");
        qz_price.setText(price);
    }
}
