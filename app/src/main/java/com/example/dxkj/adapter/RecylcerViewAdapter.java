package com.example.dxkj.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dxkj.YiJiaoFei.R;
import com.example.dxkj.YiJiaoFei.SubActivity;
import com.example.dxkj.utils.BitmapCompressTools;

import java.util.List;

/**
 * RecylcerView适配器
 * Created by dxkj on 2015/9/23.
 */
public class RecylcerViewAdapter extends RecyclerView.Adapter<RecylcerViewAdapter.ViewHolder> {

    private List<String> listData;
    private Context context;

    public RecylcerViewAdapter(List<String> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    //创建新View,被layoutmanager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitems, parent, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(listData.get(position));
        Bitmap bitmap = BitmapCompressTools.decodeSampledBitmapFromResource(context.getResources(), R.drawable.iv_items, 60, 60);
        holder.mImageView.setImageBitmap(bitmap);
        holder.mLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Toast.makeText(context, listData.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return listData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextView;
        public final ImageView mImageView;
        public LinearLayout mLlayout;
        public ViewHolder(View itemView) {
            super(itemView);
            mLlayout = (LinearLayout) itemView.findViewById(R.id.llayout);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_title);
        }
    }


    //添加条目
    public void addItem(String text, int position) {
        listData.add(position, text);
        notifyItemInserted(position); //Attention!
    }


    //删除条目
    public void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }
}
