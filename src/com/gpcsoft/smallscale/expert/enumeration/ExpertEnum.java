package com.gpcsoft.smallscale.expert.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ExpertEnum {
	
	/**
	 * 专家申请的角色类型【consultant:咨询员， reviewers:评审员】
	 */
	public static final String CONSULTANT  = "consultant";
	public static final String REVIEWERS  = "reviewers";
	
    /**
	 * 政治面貌【01:党员， 02:团员， 03:群众】
	 */
	public final static String POLITICALLANDSCAPE = "smallscale.expert.politicalLandscape";
	public static final String PARTY  = "01";
	public static final String LEAGUE = "02";
	public static final String MASSES = "03";
	public static final String getPoliticalLandscapeCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ExpertEnum.POLITICALLANDSCAPE, str);
	}
	
	/**
	 * 国家职业资格等级【01:国家职业资格一级，02:国家职业资格二级，03:国家职业资格三级，04:国家职业资格四级，05:国家职业资格五级，06:无资格证书】
	 */
	public final static String PROFESSIONQUALIFICATIONLEVEL = "smallscale.expert.professionQualificationLevel";
	public static final String ONE_LEVEL  = "01";
	public static final String TWO_LEVEL = "02";
	public static final String THREE_LEVEL = "03";
	public static final String FOUR_LEVEL  = "04";
	public static final String FIVE_LEVEL = "05";
	public static final String NO_LEVEL = "06";
	public static final String getProfessionQualificationLevelCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ExpertEnum.PROFESSIONQUALIFICATIONLEVEL, str);
	}
	
	/**
	 * 专家审核状态【00:草稿， 01:待审核， 02:审核通过， 03:审核不通过】
	 */
	public final static String AUDITSTATUS = "smallscale.expert.auditStatus";
    public static final String DRAFT_EXAM  = "00";
    public static final String AWAIT_EXAM  = "01";
    public static final String PASS_EXAM  = "02";
    public static final String NO_PASS_EXAM  = "03";
    public static final String getAuditStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ExpertEnum.AUDITSTATUS, str);
    }
    
    /**
     * 专家使用状态【临时:00,有效:01,无效:02】
     */
    public final static String USESTATUS = "smallscale.expert.useStatus";
	public final static String USE_TEMP = "00";
	public final static String USE_VALID = "01";
	public final static String USE_INVALID = "02";
    public static final String getUseStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ExpertEnum.USESTATUS, str);
    }
    
    /**
     * 专家启用禁用状态【1：启用， 2:禁用】
     */
    public final static String ISOFF = "smallscale.expert.isOff";
    public final static String ENABLE = "1";
    public final static String DISABLE = "2";
    public static String getIsOffCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ExpertEnum.ISOFF, str);
	}
    
}
