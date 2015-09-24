package com.example.dxkj.bean;

/**
 * Created by sh on 2015/9/11.
 */
public class OrderTypeListBean {
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 图标id
     */
    private Integer imageId;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public OrderTypeListBean(String orderType, Integer imageId) {
        this.orderType = orderType;
        this.imageId = imageId;
    }
}
