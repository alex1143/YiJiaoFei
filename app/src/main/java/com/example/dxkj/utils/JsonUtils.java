package com.example.dxkj.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json解析工具
 * Created by dxkj on 2015/9/11.
 */
public class JsonUtils {


    public static List<Map<String, Object>> getJson(String url) {

        List<Map<String, Object>> list;
        Map<String, Object> map;
        Log.i("123", "1");
        try {
            JSONObject obj1 = new JSONObject(url);
            Log.i("123", "2");
            list = new ArrayList<Map<String, Object>>();
            map = new HashMap<String, Object>();
            Log.i("123", "3");
            String message = (String) obj1.get("message");
            map.put("message", message);
            Log.i("123", "4");
//            JSONArray arr1 = obj1.getJSONArray("announcementAllList");
//            Log.i("123","5");
//            for (int i = 0; i < arr1.length(); i++) {
//                Log.i("123","6");
//                JSONObject obj2 = arr1.getJSONObject(i);
//                Log.i("123","7");
//                String str = (String) obj2.get("NOTICEDATE");
//                Log.i("123","8");
//                map.put("str", str);
//                Log.i("123", "9");
//            }
            Log.i("123", "10");
            list.add(map);
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
