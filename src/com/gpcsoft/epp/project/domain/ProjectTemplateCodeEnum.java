package com.gpcsoft.epp.project.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;

public class ProjectTemplateCodeEnum {
	public final static String PROJECTTEMPLATECODE = "projectTemplateCode";

	/**
	 * 政府采购——公开招标
	 */
	public final static String ZFCG_GKZB = "00";
	/**
	 * 政府采购——邀请招标
	 */
	public final static String ZFCG_YQZB = "01";
	/**
	 * 政府采购——竞争性谈判
	 */
	public final static String ZFCG_JZTP = "02";
	/**
	 * 政府采购——询价招标
	 */
	public final static String ZFCG_XJZB = "03";
	/**
	 * 政府采购——单一来源
	 */
	public final static String ZFCG_DYLY = "04";
	/**
	 * 建设工程——公开招标
	 */
	public final static String JSGC_GKZB = "05";
	/**
	 * 产权交易——竞价
	 */
	public final static String CCJY_JJ = "06";
	/**
	 * 土地交易——拍卖
	 */
	public final static String TDJY_PM = "07";
	/**
	 * 土地交易——竞标
	 */
	public final static String TDJY_JB = "08";
	/**
	 * 土地交易——挂牌
	 */
	public final static String TDJY_GP = "09";
	
	/**
	 * 建设工程——邀请招标
	 */
	public final static String JSGC_YQZB = "10";
	
	/**
	 * 其它--采购方式
	 */
	public final static String OTHER_BIDDING="30";
	
	/**
	 * 代理机构比选--随机选取
	 */
	public final static String DLBX_RANDOM="20";
	
	/**
	 * 代理机构比选-综合评价
	 */
	public final static String DLBX_COMBID="21";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjectTemplateCodeEnum.PROJECTTEMPLATECODE, str);
	} 
	public static String getCode(String tenderType, String ebuyMethod){
		String code = "";
		if(ProjectEnum.ZFCG.equals(tenderType)&& EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.ZFCG_GKZB;   
		}
		if(ProjectEnum.ZFCG.equals(tenderType)&& EbuyMethodEnum.INVITE_BIDDING.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.ZFCG_YQZB;   
		}
		
		if(ProjectEnum.ZFCG.equals(tenderType)&& EbuyMethodEnum.NEGOTIATE.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.ZFCG_JZTP;   
		}
		if(ProjectEnum.ZFCG.equals(tenderType)&& EbuyMethodEnum.INQUIRY.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.ZFCG_XJZB;   
		}
		if(ProjectEnum.ZFCG.equals(tenderType)&& EbuyMethodEnum.SINGLE_SOURCE.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.ZFCG_DYLY;   
		}
		if(ProjectEnum.JZGC.equals(tenderType)&& EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.JSGC_GKZB;   
		}
		if(ProjectEnum.CQJY.equals(tenderType)&& EbuyMethodEnum.COMPETITION.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.CCJY_JJ;   
		}
		if(ProjectEnum.TDJY.equals(tenderType)&& EbuyMethodEnum.AUCTION.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.TDJY_PM;   
		}
		if(ProjectEnum.TDJY.equals(tenderType)&& EbuyMethodEnum.COMPETITIVE_BIDDING.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.TDJY_JB;   
		}
		if(ProjectEnum.TDJY.equals(tenderType)&& EbuyMethodEnum.LIST.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.TDJY_GP;   
		}
		if(ProjectEnum.JZGC.equals(tenderType)&& EbuyMethodEnum.INVITE_BIDDING.equals(ebuyMethod)){
			code = ProjectTemplateCodeEnum.JSGC_YQZB;   
		}
		if(ProjectEnum.ZFCG.equals(tenderType)&&EbuyMethodEnum.OTHER_BIDDING.equals(ebuyMethod)){
			code=ProjectTemplateCodeEnum.OTHER_BIDDING;
		}
		if(ProjectEnum.DLBX.equals(tenderType)&&EbuyMethodEnum.RANDOM.equals(ebuyMethod)){
			code=ProjectTemplateCodeEnum.DLBX_RANDOM;
		}
		if(ProjectEnum.DLBX.equals(tenderType)&&EbuyMethodEnum.BIDCOM.equals(ebuyMethod)){
			code=ProjectTemplateCodeEnum.DLBX_COMBID;
		}
		return code;
	} 
	

}
