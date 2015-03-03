package com.gpcsoft.bizplatform.organization.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: 信息枚举
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-22 下午02:26:52 by sunl    					                            
  *  <br/>Modified Date:  2010-7-22 下午02:26:52 by sunl                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public class OrganizationEnum {
	//推广备注信息
	public final static String PROMOTER_DEMO = "promoter.demo";
	//推广邮件标题枚举key值
	public final static String PROMOTER_TITLE = "smallscale.promoter.title";
	//推广邮件内容
	public final static String PROMOTER_CONTENT = "smallscale.promoter.invitation.content";
	
	public final static String MANAGER_NAME = "manager";//系统运营管理员名称
	
	/**
	 * 几个机构角色常量
	 */
	public final static String ROLE_SUPPLIER = "s";//供应商
	public final static String ROLE_MANUFACTURER = "m";//厂商
	public final static String ROLE_SERVER = "r";//服务商
	public final static String ROLE_BUYER = "b";//采购人
	public final static String ROLE_AGENCY = "a";//代理机构
	public final static String ROLE_MANAGER = "3";//业务管理员
	public final static String ROLE_ORG = "4";//机构管理员
	public final static String DEFALT_BUYER = "5";//默认采购人
	public final static String DEFALT_SUPPLIER = "6";//默认供应商
	public final static String ROLE_PROMOTER = "p";//推广人
	public final static String ROLE_EXPERT = "e";//咨询专家
	public final static String ROLE_GIFT_SUPPLIER = "g";//礼品供货商
	public final static String ROLE_BUSINESS_MEMBER = "v";//商圈会员 
	public final static String ROLE_MEDIUM = "t";//媒体 
	public final static String ROLE_GOODS_MANAGER = "7";//商品库管理员
	
	/**
	 * 服务角色
	 */
	public final static String ROLE_CREATE_COMMUNITY = "D";//创建社区角色
	public final static String ROLE_JOIN_COMMUNITY = "E";//加入社区角色

	/**
	 * 政治面貌【01:党员， 02:团员， 03:群众】
	 */
	public final static String POLITICALLANDSCAPE = "smallscale.expert.politicalLandscape";
	public static final String PARTY  = "01";
	public static final String LEAGUE = "02";
	public static final String MASSES = "03";
	public static final String getPoliticalLandscapeCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.POLITICALLANDSCAPE, str);
	}
	
	/**
	 * 机构审核状态[草稿:00,待审核:01,审核通过:02,审核不通过:03]
	 */
	public final static String AUDITSTATUS = "biz.organization.status";
    public static final String DRAFT_EXAM  = "00";
    public static final String AWAIT_EXAM  = "01";
    public static final String PASS_EXAM  = "02";
    public static final String NO_PASS_EXAM  = "03";
    public static final String getAuditStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.AUDITSTATUS, str);
    }
    
    /**
     * 审核类型[注册审核:00,变更审核:01,申请审核:02,案例审核:03]
     */
    public final static String ORGANIZATION_AUDIT_TYPE = "biz.organization.auditType";
    public static final String REG_AUDIT = "00";
    public static final String MODIFY_AUDIT = "01";
    public static final String APPLY_AUDIT = "02";
    public static final String CASE_AUDIT = "03";
    public static final String getAuditTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.ORGANIZATION_AUDIT_TYPE, str);
    }
    
    /**
     * 使用状态[临时:00,有效:01,无效:02]
     */
    public final static String USESTATUS = "biz.organization.useStatus";
	public final static String USE_TEMP = "00";
	public final static String USE_VALID = "01";
	public final static String USE_INVALID = "02";
    public static final String getUseStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.USESTATUS, str);
    }
    
    /**
     * 发布状态[未发布:00,已发布:01]
     */
    public final static String PUBLISHEDSTATUS = "biz.organization.publishStatus";

	public final static String UNPUBLISHED = "00";
	public final static String PUBLISHED = "01";
	public static final String getPubStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.PUBLISHEDSTATUS, str);
    }
	
	/**
     * 货币类型[元:01,美元:02,欧元:03,日元:04,港币:05,台币:06]
     */
    public final static String SUPPLIER_REG_MONEY_TYPE = "biz.supplier.regMoneyType";
	public final static String RMB = "01";
	public final static String DOLLOR = "02";
	public final static String EURO = "03";
	public final static String YEN = "04";
	public final static String HK = "05";
	public final static String NT = "06";
    public static final String getRegMoneyTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.SUPPLIER_REG_MONEY_TYPE, str);
    }
    
    /**
     * 单位性质 [ENT_GYQY:01:国有企业,ENT_JTQY:02:集体企业,ENT_SYQY:03:私营企业,ENT_LYQY:04:联营企业,ENT_YXZRGS:05:有限责任公司,
     * ENT_GFYXGS:06:股份有限公司,ENT_GFHZQY:07:股份合作企业,ENT_HZJYQYGA:08:合资经营企业（港澳台资）,ENT_HZJYQYGAT:09:合作经营企业（港澳台资）,
     * ENT_GATDZQY:10:港澳台独资经营企业,ENT_GATTZGS:11:港澳台商投资股份有限公司,ENT_ZWHZQY:12:中外合资经营企业,ENT_ZWHZJYQY:13:中外合作经营企业,
     * ENT_WZQY:14:外资企业,ENT_WSTZGS:15:外商投资股份有限公司,ENT_QT:16:其他]
     */
    public final static String ORGINFO_ENTPRPT = "biz.orgInfo.entprpt";
    public final static String ENT_GYQY = "01";
    public final static String ENT_JTQY = "02";
    public final static String ENT_SYQY = "03";
    public final static String ENT_LYQY = "04";
    public final static String ENT_YXZRGS = "05";
    public final static String ENT_GFYXGS = "06";
    public final static String ENT_GFHZQY = "07";
    public final static String ENT_HZJYQYGA = "08";
    public final static String ENT_HZJYQYGAT = "09";
    public final static String ENT_GATDZQY = "10";
    public final static String ENT_GATTZGS = "11";
    public final static String ENT_ZWHZQY = "12";
    public final static String ENT_ZWHZJYQY = "13";
    public final static String ENT_WZQY = "14";
    public final static String ENT_WSTZGS = "15";
    public final static String ENT_QT = "16";
    public static final String getEntPrptCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.ORGINFO_ENTPRPT, str);
    }
    
    /**
     * 企业类型
     */
    public static final String getEXEntPrptCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.ORGINFO_ENTPRPT, str);
    }
    
    /**
     * 企业规模[10人以内,11-20人,21-50人,51-100人,101-200人,201人-300人,301-500人,501-800人,801-1000人,1001-2000人,2001-3000人,3000人以上]
     */
    public final static String EX_UNITSCAPE = "biz.orgInfo.unitScape";
    public final static String EX_10 = "01";
    public final static String EX_20 = "02";
    public final static String EX_50 = "03";
    public final static String EX_100 = "04";
    public final static String EX_200 = "05";
    public final static String EX_300 = "06";
    public final static String EX_500 = "07";
    public final static String EX_800 = "08";
    public final static String EX_1000 = "09";
    public final static String EX_2000 = "10";
    public final static String EX_3000 = "11";
    public final static String EX_up = "12";
    public static final String getEXUnitScapeCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.EX_UNITSCAPE, str);
    }
    
    /**
     * 采购主体 [01.国家机关 02.事业单位 03.团体组织 04.企业单位]
     */
    public final static String PURCHASE_SUBJECT = "biz.buyer.purSbjct";
    public final static String PUR_GJJG = "01";
    public final static String PUR_SYDW = "02";
    public final static String PUR_TTZZ = "03";
    public final static String PUR_QYDW = "04";
    public static final String getPurSbjctCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.PURCHASE_SUBJECT, str);
    }
    
    /**
     * 单位性质 [:01:国有企业,:02:合作企业,:03:合资企业,BUY_DZQY:04:独资企业,BUY_JTYE:05:集体企业,BUY_SYQY:06:私营企业,BUY_GTJSH:07:个体工商户,BUY_QT:08:其他]
     */
    public final static String UNITTYPE = "biz.buyer.unitType";
    public final static String BUY_GYQY = "01";
    public final static String BUY_HEZUOQY = "02";
    public final static String BUY_HEZIQY = "03";
    public final static String BUY_DZQY = "04";
    public final static String BUY_JTYE = "05";
    public final static String BUY_SYQY = "06";
    public final static String BUY_GTJSH = "07";
    public final static String BUY_QT = "08";
    public static final String getBuyerUnitTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.UNITTYPE, str);
    }
    
    /**
     * 所属行业 [01：工业品 02：消费品 03：原材料 04：商业服务]
     */
    public final static String BELONGINDUSTRY = "biz.buyer.belongIndustry";
    public final static String INDU_GYP = "01";
    public final static String INDU_XFP = "02";
    public final static String INDU_YCL = "03";
    public final static String INDU_SYFW = "04";
    public final static String OTHER = "05";
    public static final String getBelongIndustryCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.BELONGINDUSTRY, str);
    }
    
    /**
     * 所属行业 [01：工业品 02：消费品 03：原材料 04：商业服务]
     */
    public final static String EX_BELONGINDUSTRY = "biz.orgInfo.belongIndustry";
    public final static String EX_INDU_GYP = "01";
    public final static String EX_INDU_XFP = "02";
    public final static String EX_INDU_YCL = "03";
    public final static String EX_INDU_SYFW = "04";
    public final static String EX_OTHER = "05";
    public static final String getEXBelongIndustryCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.EX_BELONGINDUSTRY, str);
    }
    
    /**
     * 主管部门 [01:财政 02:建设 03:发改委 04:经贸委 05:监察 06:统计]
     */
    public final static String BUYER_CMPTDEPTYPE = "biz.buyer.cmptDepType";
    public final static String BUYER_CZ = "01";
    public final static String BUYER_JS = "02";
    public final static String BUYER_FGW = "03";
    public final static String BUYER_JMW = "04";
    public final static String BUYER_JC = "05";
    public final static String BUYER_TJ = "06";
    public static final String getCmptDepTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.BUYER_CMPTDEPTYPE, str);
    }
    
    /**
     *  资金归口处室，01：行政政法 02：教科文 03：经济建设 04：农业 05：社会保障 06：企业 07：金融 08：其他
     */
    public final static String FUND_DEPT = "biz.buyer.fundDept";
    public final static String FUND_XZZF = "01";
    public final static String FUND_JKW = "02";
    public final static String FUND_JJJS = "03";
    public final static String FUND_NY = "04";
    public final static String FUND_SHBZ = "05";
    public final static String FUND_QY = "06";
    public final static String FUND_JR = "07";
    public final static String FUND_QT = "08";
    public static final String getFundDeptCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.FUND_DEPT, str);
    }
    
    /**
     * 行政部门[行政管理部门:01,公检法司部门:02,农林水气象部门:03,工业交通等部门:04,文体广播部门:05,教育部门:06,卫生部门:07,科学部门:08,抚恤社保部门:09,其它:10]
     */
    public final static String EXEC_DEPT = "biz.buyer.execDept";
    public final static String XZGLBM = "01";
    public final static String GJFSBM = "02";
    public final static String NLSQXBM = "03";
    public final static String GYJTBM = "04";
    public final static String WTGBBM = "05";
    public final static String JYBM = "06";
    public final static String WSBM = "07";
    public final static String KXBM = "08";
    public final static String FXSBBM = "09";
    public final static String QT = "10";
    public static final String getExecDeptCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.EXEC_DEPT, str);
    }
    
    /**
     * 代理机构类型[01:政府集中采购中心 02:政府部门采购中心 03:社会中介（招标公司） 04:企业专职采购部门]
     */
    public final static String AGENT_AGENTTYPE = "biz.agent.agentType";
    public final static String ZFJZCGZX = "01";
    public final static String ZFBMCGZX = "02";
    public final static String SHZJ = "03";
    public final static String QYZZCGBM = "04";
    public static final String getAgentTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.AGENT_AGENTTYPE, str);
    }
    
    /**
     * 企业类型[01：国家机关 02：事业单位 03：团体组织]
     */
    public final static String AGENT_UNITTYPE = "biz.agent.unitType";
    public final static String AGENT_GJJJ = "01";
    public final static String AGENT_SYDW = "02";
    public final static String AGENT_TTZZ = "03";
    public static final String getUnitTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.AGENT_UNITTYPE, str);
    }
    
    /**
     * 行政级别[1.省、2.市、3.县、4.区]
     */
    public final static String AGENT_ADMINGRD = "biz.agent.adminGrd";
    public final static String AGENT_PROVINCE = "01";
    public final static String AGENT_CITY = "02";
    public final static String AGENT_COUNTY = "03";
    public final static String AGENT_AREA = "04";
    public static final String getAdminGrdCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.AGENT_ADMINGRD, str);
    }
    
    public final static String ORG_INFO_ISOFF = "isOff";
    //禁用
    public final static String DISABLE = "2";
    //启用
    public final static String ENABLE = "1";
    /**
     * 禁用启用中文
     * @param   
     * @return  
     */
    public static String getIsOffCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.ORG_INFO_ISOFF, str);
	}
    
    /**
     * 机构角色类型
     */
    public final static String ORG_INFO_TYPE= "orginfotype";
	//供应商
    public static final String SUPPLIER  = "01";
    //采购人
    public static final String BUYER  = "02";
    //代理机构
    public static final String AGENT = "03";
    //监管机构
    public static final String SUPERVISE = "04";
    //监督机构
    public static final String INSPECT = "05";
    /**
     * 显示中文类型。
     * @param status
     * @return
     */
    public static String getOrgTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.ORG_INFO_TYPE, str);
	}
    
    /**采购方式*/
    public static final String EBUY_METHOD = "buyMethod";
    public static final String OPEN_BIDDING = "00";
    public static final String INVITE_BIDDING = "01";
    public static final String NEGOTIATE = "02";
    public static final String INQUIRY = "03";
    public static final String SINGLE_SOURCE = "04";
    
    public static String getBuyMethodCN(String str){
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.EBUY_METHOD, str);
    }
    
	/**
	 * 热门标签中的标签类型
	 */
	public final static String TAGSTYPE ="bizdata.tagsType";
	public final static String GOODS_TAGS ="01";
	public final static String SUPPLIER_TAGS = "02";
	public final static String AGENT_TAGS = "03";
	public final static String PRICE_TAGS = "04";
	public final static String GOODS_CLASS_TAGS = "05";
	public final static String PUR_CATEGORY_TAGS = "06";
	
	public final static String getTagsTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.TAGSTYPE, str);
	}
    
}
