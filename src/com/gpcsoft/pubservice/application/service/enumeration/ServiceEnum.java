package com.gpcsoft.pubservice.application.service.enumeration;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ServiceEnum {
	
    /**
	 * 服务金额单位【01:年， 02:月， 03:期】
	 */
	public final static String SERVICEUNIT = "pubservice.application.service.serviceUnit";
	public static final String YEAR  = "01";
	public static final String MONTH = "02";
	public static final String CYCLE = "03";
	public static final String getServiceUnitCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ServiceEnum.SERVICEUNIT, str);
	}
	
	/**
	 * 会员级别【0:普通会员  1:一级， 2:二级， 3:三级】
	 */
	public final static String MEMBERCLASSNUM = "pubservice.application.service.memberClassNum";
	public static final String DEFAULT  = "0";
	public static final String ONE  = "1";
	public static final String TWO = "2";
	public static final String THREE = "3";
	public static final String getMemberClassNumCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ServiceEnum.MEMBERCLASSNUM, str);
	}
	
	/**
     * 使用状态【临时:00,有效:01,无效:02】
     */
    public final static String USESTATUS = "pubservice.application.service.useStatus";
	public final static String USE_TEMP = "00";
	public final static String USE_VALID = "01";
	public final static String USE_INVALID = "02";
    public static final String getUseStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ServiceEnum.USESTATUS, str);
    }
	
    /**
	 * 审核状态[草稿:00,待审核:01,审核通过:02,审核不通过:03]
	 */
	public final static String AUDITSTATUS = "pubservice.application.service.auditStatus";
    public static final String DRAFT_EXAM  = "00";
    public static final String AWAIT_EXAM  = "01";
    public static final String PASS_EXAM  = "02";
    public static final String NO_PASS_EXAM  = "03";
    public static final String getAuditStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.AUDITSTATUS, str);
    }
    
    /**
	 * 服务类型常量[A:商圈会员服务，B:公告订阅服务]
	 */
	public static final String SERVICE_A  = "A";//商圈会员服务
	public static final String SERVICE_B  = "B";//公告订阅服务
	public static final String SERVICE_C  = "C";//机构域名服务
	public static final String SERVICE_D  = "D";//创建社区服务
	public static final String SERVICE_E  = "E";//加入社区服务

}
