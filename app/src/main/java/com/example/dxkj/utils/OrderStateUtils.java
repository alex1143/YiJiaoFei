package com.example.dxkj.utils;

/**
 * Created by sh on 2015/9/16.
 */
public class OrderStateUtils {
    /**
     * 获取订单状态
     * @param orderType
     * @param state
     * @return
     */
    public static String getOrderState(Integer orderType,Integer state){
        if(orderType==4||orderType==5){
            switch (state){
                case 1:return "提取成功";
                case 2:return "提取失败";
            }
        }
        else {
            switch (state){
                case 1:return "充值成功";
                case 2:return "充值失败";
                case 3:return "充值中";
            }
        }
        return "未知状态";
    }
}
;