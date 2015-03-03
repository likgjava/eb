package com.gpcsoft.epp.webService.webService.ue.soap;


public class OrginfoSoapService {

	/**
	 * FuncName: maintainOrgInfo
	 * Description :维护机构信息接口
	 * @param userCode 用户名
	 * @param password 密码
	 * @param orgCode 组织机构代码
	 * @param infoType  信息类型
	 * @param content  信息内容
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-24  下午12:52:34
	 * @Modifier: liuke
	 * @Modified Date:2011-3-24  下午12:52:34
	 */
	public String maintainOrgInfo(String userCode, String password, String orgCode, String infoType, String content){
		return "SUCESS";
	}
	
	/**
	 * FuncName: maintainOrgInfo
	 * Description :下载机构信息接口
	 * @param userCode 用户名
	 * @param password 密码
	 * @param orgCode 组织机构代码
	 * @param infoType  信息类型
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-24  下午12:52:34
	 * @Modifier: liuke
	 * @Modified Date:2011-3-24  下午12:52:34
	 */
	public String downOrgInfo(String userCode, String password, String orgCode, String infoType) {
		return "SUCESS";
	}

}
