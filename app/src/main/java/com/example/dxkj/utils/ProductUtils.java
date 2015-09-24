package com.example.dxkj.utils;

/**
 * Created by sh on 2015/9/16.
 */
public class ProductUtils {
    public static String getProductType(Integer orderType,String type){
        if(orderType!=2&&orderType!=5){
            switch (type){
                case "1":return "移动";
                case "2":return "联通";
                case "3":return "电信";
                case "4":return "固话";
                default:return "虚拟运营商";
            }
        }
        return type;
    }
}
