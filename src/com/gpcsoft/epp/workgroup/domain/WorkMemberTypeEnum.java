package com.gpcsoft.epp.workgroup.domain;

import java.util.ArrayList;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.epp.project.domain.MessageCode;

public class WorkMemberTypeEnum {
	
	public final static String WORKMEMBER_TYPE = "workMemberType";

	/**
	 * 采购人代表
	 */
	public final static String BUYER_REPRESENT = "00";
	public final static MessageCode MC_BUYER_REPRESENT = new MessageCode(WorkMemberTypeEnum.BUYER_REPRESENT, getCN(WorkMemberTypeEnum.BUYER_REPRESENT));
	
	/**
	 * 专家
	 */
	public final static String EXPERT_REPRESENT = "01";
	public final static MessageCode MC_EXPERT_REPRESENT = new MessageCode(WorkMemberTypeEnum.EXPERT_REPRESENT, getCN(WorkMemberTypeEnum.EXPERT_REPRESENT));
	
	/**
	 * 经办人代表
	 */
	public final static String AGENT_REPRESENT = "02";
	public final static MessageCode MC_AGENT_REPRESENT = new MessageCode(WorkMemberTypeEnum.AGENT_REPRESENT, getCN(WorkMemberTypeEnum.AGENT_REPRESENT));
	
	/**
	 * 监管人代表
	 */
	public final static String SUPERVISE_REPRESENT = "03";
	public final static MessageCode MC_SUPERVISE_REPRESENT = new MessageCode(WorkMemberTypeEnum.SUPERVISE_REPRESENT, getCN(WorkMemberTypeEnum.SUPERVISE_REPRESENT));
	
	/**
	 * 组长
	 */
	public final static String WORKMEMBER_TYPE_LEADER = "04";
	public final static MessageCode MC_WORKMEMBER_TYPE_LEADER = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_LEADER, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_LEADER));
	
	/**
	 * 副组长
	 */
	public final static String WORKMEMBER_TYPE_DEPUTY_LEADER = "05";
	public final static MessageCode MC_WORKMEMBER_TYPE_DEPUTY_LEADER = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_DEPUTY_LEADER, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_DEPUTY_LEADER));
	
	/**
	 * 成员
	 */
	public final static String WORKMEMBER_TYPE_WORKER = "06";
	public final static MessageCode MC_WORKMEMBER_TYPE_WORKER = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_WORKER, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_WORKER));
	/**
	 * 安全员
	 */
	public final static String WORKMEMBER_TYPE_SAFE = "07";
	public final static MessageCode MC_WORKMEMBER_TYPE_SAFE = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_SAFE, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_SAFE));
	
	/**
	 * 质量员
	 */
	public final static String WORKMEMBER_TYPE_QUALITY = "08";
	public final static MessageCode MC_WORKMEMBER_TYPE_QUALITY = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_QUALITY, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_QUALITY));
	
	/**
	 * 资料员
	 */
	public final static String WORKMEMBER_TYPE_DATA = "09";
	public final static MessageCode MC_WORKMEMBER_TYPE_DATA = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_DATA, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_DATA));
	
	/**
	 * 造价员
	 */
	public final static String WORKMEMBER_TYPE_BUILDING_COST = "10";
	public final static MessageCode MC_WORKMEMBER_TYPE_BUILDING_COST = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_BUILDING_COST, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_BUILDING_COST));
	
	/**
	 * 材料员
	 */
	public final static String WORKMEMBER_TYPE_MATERIAL = "11";
	public final static MessageCode MC_WORKMEMBER_TYPE_MATERIAL = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_MATERIAL, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_MATERIAL));
	
	/**
	 * 项目负责人
	 */
	public final static String WORKMEMBER_TYPE_PROJECT_PRINCIPAL = "12";
	public final static MessageCode MC_WORKMEMBER_TYPE_PROJECT_PRINCIPAL = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_PROJECT_PRINCIPAL, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_PROJECT_PRINCIPAL));
	
	/**
	 * 项目经理人
	 */
	public final static String WORKMEMBER_TYPE_PROJECT_MANAGER = "13";
	public final static MessageCode MC_WORKMEMBER_TYPE_PROJECT_MANAGER = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_PROJECT_MANAGER, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_PROJECT_MANAGER));
	
	/**
	 *  公证员
	 */
	public final static String WORKMEMBER_TYPE_NOTARIZATION = "14";
	public final static MessageCode MC_WORKMEMBER_TYPE_NOTARIZATION = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_NOTARIZATION, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_NOTARIZATION));
	
	/**
	 *  记录员
	 */
	public final static String WORKMEMBER_TYPE_RECORD = "15";
	public final static MessageCode MC_WORKMEMBER_TYPE_RECORD = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_RECORD, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_RECORD));
	
	/**
	 *  唱标员
	 */
	public final static String WORKMEMBER_TYPE_OPEN_BID = "16";
	public final static MessageCode MC_WORKMEMBER_TYPE_OPEN_BID = new MessageCode(WorkMemberTypeEnum.WORKMEMBER_TYPE_OPEN_BID, getCN(WorkMemberTypeEnum.WORKMEMBER_TYPE_OPEN_BID));
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(WorkMemberTypeEnum.WORKMEMBER_TYPE, str);
	}
	public final static String WORK_MEMBER_IS_LEADER = "epp_workgmIsLeaderCN";
	public static String workgmIsLeaderCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(WorkMemberTypeEnum.WORK_MEMBER_IS_LEADER, str);
	}
	/**
	 * 获取建筑工程成员类型
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-8-10 11:13
	 */
	public ArrayList getGCImplWorkMemeberType(){
		ArrayList<MessageCode> gcImplWorkMemberTypeList = new ArrayList<MessageCode>();
		gcImplWorkMemberTypeList.add(MC_WORKMEMBER_TYPE_SAFE);
		gcImplWorkMemberTypeList.add(MC_WORKMEMBER_TYPE_QUALITY);
		gcImplWorkMemberTypeList.add(MC_WORKMEMBER_TYPE_DATA);
		gcImplWorkMemberTypeList.add(MC_WORKMEMBER_TYPE_BUILDING_COST);
		gcImplWorkMemberTypeList.add(MC_WORKMEMBER_TYPE_MATERIAL);
		gcImplWorkMemberTypeList.add(MC_WORKMEMBER_TYPE_PROJECT_PRINCIPAL);
		gcImplWorkMemberTypeList.add(MC_WORKMEMBER_TYPE_PROJECT_MANAGER);
		return gcImplWorkMemberTypeList;
	}
	
	/**
	 * 获取开标小组成员类型
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-8-10 11:13
	 */
	public ArrayList getOpenBidWorkMemberType(){
		ArrayList<MessageCode> openBidWorkMemberType = new ArrayList<MessageCode>();
		openBidWorkMemberType.add(MC_BUYER_REPRESENT);
		openBidWorkMemberType.add(MC_SUPERVISE_REPRESENT);
		openBidWorkMemberType.add(MC_WORKMEMBER_TYPE_NOTARIZATION);
		openBidWorkMemberType.add(MC_WORKMEMBER_TYPE_RECORD);
		openBidWorkMemberType.add(MC_WORKMEMBER_TYPE_OPEN_BID);
		return openBidWorkMemberType;
	}
}
