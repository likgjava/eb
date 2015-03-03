package com.gpcsoft.smallscale.vote.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class VoteEnum {
	/**
	 * 是否单一投票   0：指标评选   1：单一投票
	 */
	public static final String ISSINGLEVOTE_STATUS = "com.gpcsoft.smallscale.vote.domain.voteTemplate.isSingleVoteStatus";
	public static final String ISSINGLEVOTE_STATUS_YES = "1";
	public static final String ISSINGLEVOTE_STATUS_NO = "0";
	public static final String getIsSingleVoteCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(VoteEnum.ISSINGLEVOTE_STATUS, str);
	}
	
	/**
	 * 评选组类型(00：企业；01：品牌；02：服务；03：商品)
	 */
	public static final String GROUP_TYPE = "com.gpcsoft.smallscale.vote.domain.voteUnitGroup.groupType";
	public static final String GROUP_TYPE_ORG = "00";
	public static final String GROUP_TYPE_BRAND = "01";
	public static final String GROUP_TYPE_SERVICE = "02";
	public static final String GROUP_TYPE_GOODS = "03";
	public static final String getGroupTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(VoteEnum.GROUP_TYPE, str);
	}
	

}
