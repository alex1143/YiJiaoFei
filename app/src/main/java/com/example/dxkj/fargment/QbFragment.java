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
 * q币充值
 * Created by dxkj on 2015/9/16.
 */
public class QbFragment extends Fragment {

    private View view;
    private Context context;
    private Spinner qb_number;                //q币数量
    private TextView qb_price;                //q币价格（activity传入）
    private TextView qb_other_number;         //q币其他数量

    private OnItemClickedListener mylistener;  //第二步：定义属性
    private ArrayAdapter adapterNumber;
    private Map<String, String> map;
    private static final String[] number = {"10", "20", "30", "50", "100", "200"};     //充值数量

    public String number1;
    public String price;

    private static QbFragment mqbQbFragment;

    public static QbFragment newInstance() {
        if (mqbQbFragment == null) {
            mqbQbFragment = new QbFragment();
        }
        return mqbQbFragment;
    }

    //第一步：自定义接口和接口中的抽象方法
    public interface OnItemClickedListener {
        void onClickQb(Map<String, String> map);
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
        view = inflater.inflate(R.layout.fragment_qb, container, false);
        init();
        setSpinner();
        setPrice();
        sendNews();
        return view;
    }

    public void init() {

        qb_number = (Spinner) view.findViewById(R.id.qb_number);
        qb_price = (TextView) view.findViewById(R.id.qb_price);
        qb_other_number = (TextView) view.findViewById(R.id.qb_other_number);
        number1 = qb_other_number.getText() + "";
    }

    public void setSpinner() {
        qb_number.setAdapter(adapterNumber);
        qb_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        price = bundle.getString("qbprice");
        qb_price.setText(price);
    }

    private void sendNews() {
        map.put("qbNumber", number1);
        mylistener.onClickQb(map);
    }
}
