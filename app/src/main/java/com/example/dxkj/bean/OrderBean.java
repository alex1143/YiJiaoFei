package com.example.dxkj.bean;

/**
 * Created by sh on 2015/9/11.
 */
public class OrderBean {
    /**
     * 订单号
     */
    private String orderid;

    /**
     * 充值号码
     */
    private String phoneNum ;
    /**
     * 充值金额
     */
    private Double money;
    /**
     *订单状态
     */
    private Integer state;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderType() {
        return orderType;
    }



    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 订单类型 1话费 2 腾旭 3 流量 4 卡密 5 礼品卡

     */
    private Integer orderType;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "phoneNum='" + phoneNum + '\'' +
                ", money=" + money +
                ", state=" + state +
                ", productType=" + productType +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public OrderBean(String phoneNum,int state, Double money) {
        this.phoneNum = phoneNum;
        this.money = money;
        this.state = state;
    }

    public OrderBean(String orderid, String phoneNum, Double money, Integer state, String productType, String beginTime, String endTime, Integer orderType) {
        this.orderid = orderid;
        this.phoneNum = phoneNum;
        this.money = money;
        this.state = state;
        this.productType = productType;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orderType = orderType;
    }
}

