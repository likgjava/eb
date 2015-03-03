/**
 * 
 */
package com.gpcsoft.epp.webService.ueSystem.orgInfo.basic.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * @author liuke
 *
 */
@NodeMapping(tag="body")
public class BasicDTO {
	private String orgName;//机构名称
	private String shortName;//机构简称
	private String province;//行政区域（省）
	private String city;//行政区域（市）
	private String county;//行政区域（区县）
	private String address;//详细地址
	private String postcode;//邮编
	private String olinker;//联系人
	private String olPhone;//联系电话
	private String property;//企业类型/性质
	private String business;//所属行业
	private String operation;//经营方式
	private String cooperate;//是否有合作伙伴
	private String scale;//企业规模（人数）
	private String delegate;//法人代表（姓名）
	private String dphone;//法人代表（联系电话）

	
	@NodeMapping(tag="orgName")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@NodeMapping(tag="shortName")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	@NodeMapping(tag="province")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@NodeMapping(tag="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@NodeMapping(tag="county")
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	@NodeMapping(tag="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@NodeMapping(tag="postcode")
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@NodeMapping(tag="olinker")
	public String getOlinker() {
		return olinker;
	}
	public void setOlinker(String olinker) {
		this.olinker = olinker;
	}
	@NodeMapping(tag="olPhone")
	public String getOlPhone() {
		return olPhone;
	}
	public void setOlPhone(String olPhone) {
		this.olPhone = olPhone;
	}
	@NodeMapping(tag="property")
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	@NodeMapping(tag="business")
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	@NodeMapping(tag="operation")
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@NodeMapping(tag="cooperate")
	public String getCooperate() {
		return cooperate;
	}
	public void setCooperate(String cooperate) {
		this.cooperate = cooperate;
	}
	@NodeMapping(tag="scale")
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	@NodeMapping(tag="delegate")
	public String getDelegate() {
		return delegate;
	}
	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}
	@NodeMapping(tag="dphone")
	public String getDphone() {
		return dphone;
	}
	public void setDphone(String dphone) {
		this.dphone = dphone;
	}
	
	
}
