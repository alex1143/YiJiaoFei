package com.example.dxkj.fargment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * qq会员充值
 * Created by dxkj on 2015/9/16.
 */
public class QhuiYuanFragment extends Fragment {

    private View view;
    private Context context;
    private Spinner qh_type;                  //会员类型
    private Spinner qh_number;                //会员数量
    private TextView qh_price;                //会员价格（activity传入）
    private TextView qh_other_number;         //会员其他数量

    private OnItemClickedListener mylistener;  //第二步：定义属性
    private ArrayAdapter adapterType;
    private ArrayAdapter adapterNumber;
    private Map<String, String> map;
    private static final String[] type = {"红钻", "黄钻", "绿钻", "蓝钻", "黑钻", "粉钻", "紫钻"};
    private static final String[] number = {"1", "2", "3", "4", "5", "6", "12"};

    public String type1;
    public String number1;
    public String price;

    private static QhuiYuanFragment mqhuiYuanFragment;

    public static QhuiYuanFragment newInstance() {
        if (mqhuiYuanFragment == null) {
            mqhuiYuanFragment = new QhuiYuanFragment();
        }
        return mqhuiYuanFragment;
    }

    //第一步：自定义接口和接口中的抽象方法
    public interface OnItemClickedListener {
        void onClickQhui(Map<String, String> map);
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
        view = inflater.inflate(R.layout.fragment_qh, container, false);
        init();
        setSpinner();
        setPrice();
        sendNews();
        return view;
    }

    public void init() {

        qh_type = (Spinner) view.findViewById(R.id.qh_type);
        qh_number = (Spinner) view.findViewById(R.id.qh_number);
        qh_price = (TextView) view.findViewById(R.id.qh_price);
        qh_other_number = (TextView) view.findViewById(R.id.qh_other_number);
        number1 = qh_other_number.getText() + "";
    }

    public void setSpinner() {
        qh_type.setAdapter(adapterType);
        qh_number.setAdapter(adapterNumber);
        qh_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type1 = type[position];
                sendNews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        qh_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        map.put("qhType", type1);
        map.put("qhNumber", number1);
        Log.i("123", "map====" + map);
        mylistener.onClickQhui(map);
    }

    public void setPrice() {
        Bundle bundle = getArguments();
        price = bundle.getString("qhPrice");
        qh_price.setText(price);
    }
}
