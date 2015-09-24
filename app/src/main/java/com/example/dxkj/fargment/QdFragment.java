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
 * q点充值
 * Created by dxkj on 2015/9/16.
 */
public class QdFragment extends Fragment {

    private View view;
    private Context context;
    private Spinner qd_number;                //q币数量
    private TextView qd_price;                //q币价格（activity传入）
    private TextView qd_other_number;         //q币其他数量

    private OnItemClickedListener mylistener;  //第二步：定义属性
    private ArrayAdapter adapterNumber;
    private Map<String, String> map;
    private static final String[] number = {"1", "2", "3", "5", "10", "20"};

    public String number1;
    public String price;

    private static QdFragment mqQdFragment;

    public static QdFragment newInstance() {
        if (mqQdFragment == null) {
            mqQdFragment = new QdFragment();
        }
        return mqQdFragment;
    }

    //第一步：自定义接口和接口中的抽象方法
    public interface OnItemClickedListener {
        void onClickQd(Map<String, String> map);
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
        adapterNumber = new ArrayAdapter<>(context, R.layout.spinnerlist, number);
        map = new HashMap<String, String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qd, container, false);
        init();
        setSpinner();
        setPrice();
        sendNews();
        return view;
    }

    public void init() {

        qd_number = (Spinner) view.findViewById(R.id.qd_number);
        qd_price = (TextView) view.findViewById(R.id.qd_price);
        qd_other_number = (TextView) view.findViewById(R.id.qd_other_number);
        number1 = qd_other_number.getText() + "";
    }

    public void setSpinner() {
        qd_number.setAdapter(adapterNumber);
        qd_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public void setPrice() {
        Bundle bundle = getArguments();
        price = bundle.getString("qdprice");
        qd_price.setText(price);
    }

    public void sendNews() {
        map.put("qdNumber", number1);
        mylistener.onClickQd(map);
    }
}
