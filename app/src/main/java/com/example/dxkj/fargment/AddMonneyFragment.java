package com.example.dxkj.fargment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dxkj.YiJiaoFei.R;
import com.example.dxkj.utils.BitmapCompressTools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by sh on 2015/9/16.
 */
public class AddMonneyFragment extends Fragment {
    @ViewInject(R.id.taobao)
    ImageView taobao;
    @ViewInject(R.id.paipai)
    ImageView paipai;
    @ViewInject(R.id.tlzf)
    ImageView tlzf;
    @ViewInject(R.id.yhzz)
    ImageView yhzz;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.addmoney, container, false);//关联布局文件
        ViewUtils.inject(this, rootView);
        taobao.setImageBitmap(BitmapCompressTools.decodeSampledBitmapFromResource(getResources(), R.drawable.taobao, 300, 150));
        paipai.setImageBitmap(BitmapCompressTools.decodeSampledBitmapFromResource(getResources(), R.drawable.paipai, 300, 150));
        tlzf.setImageBitmap(BitmapCompressTools.decodeSampledBitmapFromResource(getResources(), R.drawable.tlzf, 300, 150));
        yhzz.setImageBitmap(BitmapCompressTools.decodeSampledBitmapFromResource(getResources(), R.drawable.yhzz, 300, 150));
        return rootView;
    }

}
