package com.gpcsoft.epp.webService.ueSystem.orgInfo.user.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="body")
public class UserDTO {
	
	private String lName;  //联系人姓名
	private String lSex;	      //性别
	private String lId;      //身份证号码
	private String lEmail; //电子邮件
	private String lMPhone;  //移动电话
	private String lPhone;  //固定电话
	private String lFax;       //传真
	private String laddress;       //通信地址
	private String lPostcode;       //邮编
	
	@NodeMapping(tag="lName")
	public String getLName() {
		return lName;
	}
	
	@NodeMapping(tag="lSex")
	public String getLSex() {
		return lSex;
	}
	
	@NodeMapping(tag="lId")
	public String getLId() {
		return lId;
	}
	
	@NodeMapping(tag="lEmail")
	public String getLEmail() {
		return lEmail;
	}
	
	@NodeMapping(tag="lMPhone")
	public String getLMPhone() {
		return lMPhone;
	}
	
	@NodeMapping(tag="lPhone")
	public String getLPhone() {
		return lPhone;
	}

	@NodeMapping(tag="lFax")
	public String getLFax() {
		return lFax;
	}
	
	@NodeMapping(tag="laddress")
	public String getLaddress() {
		return laddress;
	}
	
	@NodeMapping(tag="lPostcode")
	public String getLPostcode() {
		return lPostcode;
	}
	public void setLName(String name) {
		lName = name;
	}
	public void setLSex(String sex) {
		lSex = sex;
	}
	public void setLId(String id) {
		lId = id;
	}
	public void setLEmail(String email) {
		lEmail = email;
	}
	public void setLMPhone(String phone) {
		lMPhone = phone;
	}
	public void setLPhone(String phone) {
		lPhone = phone;
	}
	public void setLFax(String fax) {
		lFax = fax;
	}
	public void setLaddress(String laddress) {
		this.laddress = laddress;
	}
	public void setLPostcode(String postcode) {
		lPostcode = postcode;
	}
	
	
	
}
