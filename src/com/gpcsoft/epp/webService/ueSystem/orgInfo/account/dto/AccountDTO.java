package com.gpcsoft.epp.webService.ueSystem.orgInfo.account.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

@NodeMapping(tag="body")
public class AccountDTO {
	
	private String openBank;  //开户银行
	private String openName;	//开户名
	private String openAccount;   //开户账号
	public String getOpenBank() {
		return openBank;
	}
	
	@NodeMapping(tag="openBank")
	public String getOpenName() {
		return openName;
	}
	
	@NodeMapping(tag="openName")
	public String getOpenAccount() {
		return openAccount;
	}
	
	@NodeMapping(tag="openAccount")
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	public void setOpenName(String openName) {
		this.openName = openName;
	}
	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}     
	


}
