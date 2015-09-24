package com.example.dxkj.utils;

/**
 * url
 * Created by sh on 2015/9/17.
 */
public class Constant {
    /**
     * 服务器IP地址
     */
    private static String IP = "http://172.16.1.175:8888";

    /**
     * 登陆地址
     */
    public static String LONGIN_URL = IP + "/huiyuanhoutai_V3.0/user/login.do";

    /**
     * 公告地址
     */
    public static String PUBLIC_URL = IP + "/huiyuanhoutai_V3.0/announcement/all.do";

    /**
     * 首页轮播图片地址
     */
    public static String PICTURE_URL1 = "http://image.zcool.com.cn/56/35/1303967876491.jpg";
    public static String PICTURE_URL2 = "http://image.zcool.com.cn/59/54/m_1303967870670.jpg";
    public static String PICTURE_URL3 = "http://image.zcool.com.cn/47/19/1280115949992.jpg";

    /**
     * 话费充值
     */
    public static String RECHARGE_URL = IP + "/huiyuanhoutai_V3.0/recharge/phone.do";
    /**
     * Q币充值接
     */
    public static String ORDER_URL = IP + "/huiyuanhoutai_V3.0/order/myHistoryOrders.do";
}
