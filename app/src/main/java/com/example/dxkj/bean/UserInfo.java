package com.example.dxkj.bean;

import java.io.Serializable;


/**
 * 会员联系信息表
 * 
 * @author 健旭
 * 
 */
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer userId;// userId;
	private String userName;// userinfoRealname;
	private String userinfoAddress;
	private String userinfoPhone;
	private String userinfoQq;
	private String userinfoWw;
	private String userinfoEmail;//USERINFO_EMAIL
	private String userinfoCardid;
	private String userinfoCardtype;
	private String userinfoCommnet;
	private String compayName;// userinfoCompayName;
	private String bizTel;// userinfoBizTel;
	private String productTel;// userinfoProductTel;
	private String financeTel;// userinfoFinanceTel;
	private String orderTel;// userinfoOrderTel;
	private String bizQQ;// userinfoBizQq;
	private String productQQ;// userinfoProductQq;
	private String financeQQ;// userinfoFinanceQq;
	private String orderQQ;// userinfoOrderQq;
	private String productNotify;// userinfoProductNotify;
	private String balanceNotify;// userinfoBalanceNotify;
	private String bizNotify;// userinfoBizNotify;
	private Integer usingIp;// userinfoUsingip;
	private Integer usingmail;// userinfoUsingmail;
	private Integer usingmac;// userinfoUsingmac;
	private Integer usingrandompwd;// userinfoUsingrandompwd;
	private Integer usingpaypwd;// userinfoUsingpaypwd;
	private Integer bjemail;// userinfoBjemail;
	private Integer bjqq;// userinfoBjqq;
	private Integer bjwindow;// userinfoBjwindow;
	private Integer emailstate;// userinfoEmailstate;
	private Integer loginTryCount;// userinfoLogintrycount;
	private String contacts;// userinfoContacts;
	private String productContacts;// userinfoProductcontacts;
	private String bizContacts;// userinfoBizcontacts;
	private String orderContacts;// userinfoOrdercontacts;
	private String financeContacts;// userinfoFinancecontacts;
	private String ordereMail;// userinfoOrderemail;
	private String balanceQQ;// userinfoBalanceqq;
	private String balanceMail;// userinfoBalancemail;
	private Double bjmoney;// userinfoBjmoney;
	private Double usermoney;// userinfoUsermoney;
	private String city;//USERINFO_CITY
	private String diqu;//USERINFO_DIQU
	private String Credit;//USERINFO_Credit
	private String weixin;//userinfo_weixin
	private String oftenLogin;//USERINFO_OFTENLOGIN
	private String noticephone;//USERINFO_NOTICEPHONE
	private String phonecheck;//USERINFO_PHONECHECK

	public String getPhonecheck() {
		return phonecheck;
	}

	public void setPhonecheck(String phonecheck) {
		this.phonecheck = phonecheck;
	}

	public String getNoticephone() {
		return noticephone;
	}

	public void setNoticephone(String noticephone) {
		this.noticephone = noticephone;
	}

	public String getWeixin() {
		return weixin;
	}

	public String getOftenLogin() {
		return oftenLogin;
	}

	public void setOftenLogin(String oftenLogin) {
		this.oftenLogin = oftenLogin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getCredit() {
		return Credit;
	}

	public void setCredit(String credit) {
		Credit = credit;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserinfoAddress() {
		return userinfoAddress;
	}

	public void setUserinfoAddress(String userinfoAddress) {
		this.userinfoAddress = userinfoAddress;
	}

	public String getUserinfoPhone() {
		return userinfoPhone;
	}

	public void setUserinfoPhone(String userinfoPhone) {
		this.userinfoPhone = userinfoPhone;
	}

	public String getUserinfoQq() {
		return userinfoQq;
	}

	public void setUserinfoQq(String userinfoQq) {
		this.userinfoQq = userinfoQq;
	}

	public String getUserinfoWw() {
		return userinfoWw;
	}

	public void setUserinfoWw(String userinfoWw) {
		this.userinfoWw = userinfoWw;
	}

	public String getUserinfoEmail() {
		return userinfoEmail;
	}

	public void setUserinfoEmail(String userinfoEmail) {
		this.userinfoEmail = userinfoEmail;
	}

	public String getUserinfoCardid() {
		return userinfoCardid;
	}

	public void setUserinfoCardid(String userinfoCardid) {
		this.userinfoCardid = userinfoCardid;
	}

	public String getUserinfoCardtype() {
		return userinfoCardtype;
	}

	public void setUserinfoCardtype(String userinfoCardtype) {
		this.userinfoCardtype = userinfoCardtype;
	}

	public String getUserinfoCommnet() {
		return userinfoCommnet;
	}

	public void setUserinfoCommnet(String userinfoCommnet) {
		this.userinfoCommnet = userinfoCommnet;
	}

	public String getCompayName() {
		return compayName;
	}

	public void setCompayName(String compayName) {
		this.compayName = compayName;
	}

	public String getBizTel() {
		return bizTel;
	}

	public void setBizTel(String bizTel) {
		this.bizTel = bizTel;
	}

	public String getProductTel() {
		return productTel;
	}

	public void setProductTel(String productTel) {
		this.productTel = productTel;
	}

	public String getFinanceTel() {
		return financeTel;
	}

	public void setFinanceTel(String financeTel) {
		this.financeTel = financeTel;
	}

	public String getOrderTel() {
		return orderTel;
	}

	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}

	public String getBizQQ() {
		return bizQQ;
	}

	public void setBizQQ(String bizQQ) {
		this.bizQQ = bizQQ;
	}

	public String getProductQQ() {
		return productQQ;
	}

	public void setProductQQ(String productQQ) {
		this.productQQ = productQQ;
	}

	public String getFinanceQQ() {
		return financeQQ;
	}

	public void setFinanceQQ(String financeQQ) {
		this.financeQQ = financeQQ;
	}

	public String getOrderQQ() {
		return orderQQ;
	}

	public void setOrderQQ(String orderQQ) {
		this.orderQQ = orderQQ;
	}

	public String getProductNotify() {
		return productNotify;
	}

	public void setProductNotify(String productNotify) {
		this.productNotify = productNotify;
	}

	public String getBalanceNotify() {
		return balanceNotify;
	}

	public void setBalanceNotify(String balanceNotify) {
		this.balanceNotify = balanceNotify;
	}

	public String getBizNotify() {
		return bizNotify;
	}

	public void setBizNotify(String bizNotify) {
		this.bizNotify = bizNotify;
	}

	public Integer getUsingIp() {
		return usingIp;
	}

	public void setUsingIp(Integer usingIp) {
		this.usingIp = usingIp;
	}

	public Integer getUsingmail() {
		return usingmail;
	}

	public void setUsingmail(Integer usingmail) {
		this.usingmail = usingmail;
	}

	public Integer getUsingmac() {
		return usingmac;
	}

	public void setUsingmac(Integer usingmac) {
		this.usingmac = usingmac;
	}

	public Integer getUsingrandompwd() {
		return usingrandompwd;
	}

	public void setUsingrandompwd(Integer usingrandompwd) {
		this.usingrandompwd = usingrandompwd;
	}

	public Integer getUsingpaypwd() {
		return usingpaypwd;
	}

	public void setUsingpaypwd(Integer usingpaypwd) {
		this.usingpaypwd = usingpaypwd;
	}

	public Integer getBjemail() {
		return bjemail;
	}

	public void setBjemail(Integer bjemail) {
		this.bjemail = bjemail;
	}

	public Integer getBjqq() {
		return bjqq;
	}

	public void setBjqq(Integer bjqq) {
		this.bjqq = bjqq;
	}

	public Integer getBjwindow() {
		return bjwindow;
	}

	public void setBjwindow(Integer bjwindow) {
		this.bjwindow = bjwindow;
	}

	public Integer getEmailstate() {
		return emailstate;
	}

	public void setEmailstate(Integer emailstate) {
		this.emailstate = emailstate;
	}

	public Integer getLoginTryCount() {
		return loginTryCount;
	}

	public void setLoginTryCount(Integer loginTryCount) {
		this.loginTryCount = loginTryCount;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getProductContacts() {
		return productContacts;
	}

	public void setProductContacts(String productContacts) {
		this.productContacts = productContacts;
	}

	public String getBizContacts() {
		return bizContacts;
	}

	public void setBizContacts(String bizContacts) {
		this.bizContacts = bizContacts;
	}

	public String getOrderContacts() {
		return orderContacts;
	}

	public void setOrderContacts(String orderContacts) {
		this.orderContacts = orderContacts;
	}

	public String getFinanceContacts() {
		return financeContacts;
	}

	public void setFinanceContacts(String financeContacts) {
		this.financeContacts = financeContacts;
	}

	public String getOrdereMail() {
		return ordereMail;
	}

	public void setOrdereMail(String ordereMail) {
		this.ordereMail = ordereMail;
	}

	public String getBalanceQQ() {
		return balanceQQ;
	}

	public void setBalanceQQ(String balanceQQ) {
		this.balanceQQ = balanceQQ;
	}

	public String getBalanceMail() {
		return balanceMail;
	}

	public void setBalanceMail(String balanceMail) {
		this.balanceMail = balanceMail;
	}

	public Double getBjmoney() {
		return bjmoney;
	}

	public void setBjmoney(Double bjmoney) {
		this.bjmoney = bjmoney;
	}

	public Double getUsermoney() {
		return usermoney;
	}

	public void setUsermoney(Double usermoney) {
		this.usermoney = usermoney;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDiqu() {
		return diqu;
	}

	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}

}
