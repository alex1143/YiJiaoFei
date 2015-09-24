package com.example.dxkj.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dxkj.utils.HttpUtils;
import com.example.dxkj.utils.JsonUtils;

import java.util.List;
import java.util.Map;

/**
 * 异步下载任务
 * Created by dxkj on 2015/9/11.
 */
public class MyAsyncTask extends AsyncTask<String, Void, List<Map<String, Object>>> {

    List<Map<String, Object>> list;
    CallBack callback;

    public MyAsyncTask(CallBack callback) {
        // TODO Auto-generated constructor stub
        this.callback = callback;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected List<Map<String, Object>> doInBackground(String... params) {
        byte[] data = HttpUtils.getUtils(params[0]);
        Log.i("123", "data=" + data);
        String uri = new String(data);
        Log.i("123", "uri=" + uri);
        list = JsonUtils.getJson(uri);
        Log.i("123", "list1=" + list);
        return list;
    }

    @Override
    protected void onPostExecute(List<Map<String, Object>> maps) {

        super.onPostExecute(maps);
        Log.i("123", "maps=" + maps);
        callback.send(maps);
    }


    public interface CallBack {
        public void send(List<Map<String, Object>> result);
    }
}
