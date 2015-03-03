package com.gpcsoft.epp.taskplan.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ProjMemberEnum {

	public final static String PROJ_MEMBER_TYPE = "epp.ProjMember.projMemeberType";

	public static String getProjMemeberTypeCn(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjMemberEnum.PROJ_MEMBER_TYPE, str);
	} 
}
