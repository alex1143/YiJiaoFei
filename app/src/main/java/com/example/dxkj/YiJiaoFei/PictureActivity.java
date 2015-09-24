package com.example.dxkj.YiJiaoFei;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.LinkedList;

public class PictureActivity extends AppCompatActivity {

    private LinkedList<String> mListItems;
    /**
     * 上拉刷新的控件
     */
    @ViewInject(R.id.pull_refresh_list)
    private PullToRefreshListView mPullRefreshListView;
    private ArrayAdapter<String> mAdapter;
    private int mItemCount = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        initView();
        // 初始化数据
        initDatas();
        setAdapters();
        initIndicator();
        setClick();

    }

    private void initView(){
        ViewUtils.inject(this);
    }

    /**
     * 设置适配器
     */
    private void setAdapters(){
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
        mPullRefreshListView.setAdapter(mAdapter);
    }

    /**
     * 设置刷新时的监听事件
     */
    private void setClick(){
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // 刷新时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 设置刷新时候的字体
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new GetDataTask().execute();
            }
        });
    }

    private void initDatas() {
        // 初始化数据和数据源
        mListItems = new LinkedList<String>();
        for (int i = 0; i < mItemCount; i++) {
            mListItems.add("" + i);
        }
    }

    /**
     * 加载刷新任务（从网络获取开启异步）
     */
    private class GetDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "" + (mItemCount++);
        }

        @Override
        protected void onPostExecute(String result) {
            mListItems.add(result);
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }

    /**
     * 设置下拉刷新时显示的文字
     */
    private void initIndicator(){
       // PullRefreshListView.getLoadingLayoutProxy(true, false);接收两个参数，为true,false返回设置下拉的ILoadingLayout；为false,true返回设置上拉的。
        ILoadingLayout iLoadingLayout = mPullRefreshListView.getLoadingLayoutProxy(true,false);
        iLoadingLayout.setPullLabel("你可劲拉，拉...");// 刚下拉时，显示的提示
        iLoadingLayout.setRefreshingLabel("好嘞，正在刷新...");// 刷新时
        iLoadingLayout.setReleaseLabel("你敢放，我就敢刷新...");// 下来达到一定距离时，显示的提示
    }
}
