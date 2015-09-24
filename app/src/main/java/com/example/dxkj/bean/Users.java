package com.example.dxkj.bean;


import java.io.Serializable;

/**
 * 
 * @author 曲杰 2014-5-29 用户表
 * 
 */
/**
 * @author dxkj
 *
 */

public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// userId
	private String userid;// userAccount
	private String username;// userName
	private String userpwd;// userPwd
	private String userkey;// userKey
	private String url;// userReturnurl
	private String userloginpwd;// userLoginpwd
	private String tbplusmoney;// userTbid
	private String paipaiPlusMoney;// userPpid
	private String userpaypwd;// userPaypwd
	private Integer referTag;// userSdstatus
	private Integer priority;// userPriority
	private String userip;// userIp
	private Integer jktag;// userJkstatus
	private String productmanager;// userPm
	private Integer prepaid;// userPrePaid
	// ---------------------------------不往数据库存储的字段
	private String userToken;
	private String loginCity; 
	// -------------------------------------新增
	private Integer userStatus;
	private Integer focusId;
	private String userSalt;
	private Integer show170;
	private Integer operateId;
	private Integer userpricefocusId;
	private String loginArea;//本次登录地

	// --------------------------------------暂时没有用到
	// private Integer usertag;
	// private Double usermoney;
	// private String remark;
	// private String diqu;
	// private Double bjmoney;
	// private Integer orderstate;
	//
	// private Integer external;
	// private String useremail;
	// private String province;
	// private String city;
	// private String userToken;
	// private String loginCity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShow170() {
		return show170;
	}

	public void setShow170(Integer show170) {
		this.show170 = show170;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getTbplusmoney() {
		return tbplusmoney;
	}

	public void setTbplusmoney(String tbplusmoney) {
		this.tbplusmoney = tbplusmoney;
	}

	public String getUserloginpwd() {
		return userloginpwd;
	}
	
	public void setUserloginpwd(String userloginpwd) {
		this.userloginpwd = userloginpwd;
	}

	public String getUserpaypwd() {
		return userpaypwd;
	}

	public void setUserpaypwd(String userpaypwd) {
		this.userpaypwd = userpaypwd;
	}

	public Integer getReferTag() {
		return referTag;
	}

	public void setReferTag(Integer referTag) {
		this.referTag = referTag;
	}

	public String getPaipaiPlusMoney() {
		return paipaiPlusMoney;
	}

	public void setPaipaiPlusMoney(String paipaiPlusMoney) {
		this.paipaiPlusMoney = paipaiPlusMoney;
	}

	public Integer getJktag() {
		return jktag;
	}

	public void setJktag(Integer jktag) {
		this.jktag = jktag;
	}

	public String getProductmanager() {
		return productmanager;
	}

	public void setProductmanager(String productmanager) {
		this.productmanager = productmanager;
	}

	public Integer getPrepaid() {
		return prepaid;
	}

	public void setPrepaid(Integer prepaid) {
		this.prepaid = prepaid;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getFocusId() {
		return focusId;
	}

	public void setFocusId(Integer focusId) {
		this.focusId = focusId;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public Integer getUserpricefocusId() {
		return userpricefocusId;
	}

	public void setUserpricefocusId(Integer userpricefocusId) {
		this.userpricefocusId = userpricefocusId;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getLoginCity() {
		return loginCity;
	}

	public void setLoginCity(String loginCity) {
		this.loginCity = loginCity;
	}

	public String getLoginArea() {
		return loginArea;
	}

	public void setLoginArea(String loginArea) {
		this.loginArea = loginArea;
	}
	
}
