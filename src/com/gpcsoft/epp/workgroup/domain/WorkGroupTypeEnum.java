package com.gpcsoft.epp.workgroup.domain;

import java.util.ArrayList;
import java.util.List;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.epp.project.domain.MessageCode;

/**
 * 
  *  Comments: <strong>WorkGroupTypeEnum</strong>工作小组类型
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-5-13 上午05:43:28 by yemx    					                            
  *  <br/>Modified Date:  2010-5-13 上午05:43:28 by yemx                            
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class WorkGroupTypeEnum {
	
	public final static String WORKGROUP_TYPE = "workGroupType";

	/**
	 * 项目小组
	 */
	public final static String PROJECT = "00";
	
	/**
	 * 论证小组
	 */
	public final static String PROOF = "01";
	
	/**
	 * 评审小组
	 */
	public final static String APPRAISAL = "02";
	
	/**
	 * 开标小组
	 */
	public final static String OPEN_BID = "03";
	
	/**
	 *组员
	 */
	public final static String IS_NOLEADER = "00";
	/**
	 *组长
	 */
	public final static String IS_LEADER = "01";
	
	/**
	 * 建筑工程施工小组
	 */
	public final static String WORKGROUP_TYPE_GC_IMPLEMENT = "05";	
	
	/** 领导小组
	 */
	public final static String WORKGROUP_TYPE_LEADINGGROUP = "04";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(WorkGroupTypeEnum.WORKGROUP_TYPE, str);
	}
	
	/**
	 * 获得领导小组的成员类型
	 * @return
	 * @author: zhouzhanghe
	 * @Create Date:2011-9-27 10:27
	 */
	public List<MessageCode> getLeadingWorkGroupMemberType(){
		List<MessageCode> leadingWorkGroupTypeList = new ArrayList<MessageCode>();
		
		leadingWorkGroupTypeList.add(WorkMemberTypeEnum.MC_WORKMEMBER_TYPE_LEADER);
		leadingWorkGroupTypeList.add(WorkMemberTypeEnum.MC_WORKMEMBER_TYPE_DEPUTY_LEADER);
		leadingWorkGroupTypeList.add(WorkMemberTypeEnum.MC_WORKMEMBER_TYPE_WORKER);
		return leadingWorkGroupTypeList;
	}
}
