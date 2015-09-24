package com.example.dxkj.bean;

/**
 * Created by sh on 2015/9/17.
 */
public class UserLonginResponse {
    /**
     * 会员状态
     */
   public String status;
    /**
     * 登录尸体类
     */
    public Users entity;
    /**
     * 登录消息
     */
    public String message;
    /**
     * userToken
     */
    public String userToken;
    /**
     *
     */
    public UserInfo userInfo;
    /**
     *
     */
    public String success;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users getEntity() {
        return entity;
    }

    public void setEntity(Users entity) {
        this.entity = entity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "UserLonginResponse{" +
                "status='" + status + '\'' +
                ", entity=" + entity +
                ", message='" + message + '\'' +
                ", userToken='" + userToken + '\'' +
                ", userInfo=" + userInfo +
                ", success='" + success + '\'' +
                '}';
    }
}
