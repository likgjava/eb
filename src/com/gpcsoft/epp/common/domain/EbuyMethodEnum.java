package com.gpcsoft.epp.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.epp.project.domain.MessageCode;
import com.gpcsoft.epp.project.domain.ProjectEnum;

/**
 * 
  *  Comments: <strong>EbuyMethodEnum</strong>采购方式           		
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
public class EbuyMethodEnum {
	public final static String EBUY_METHOD = "ebuyMethod";

	/**
	 * 公开招标
	 */						   
	public final static String OPEN_BIDDING = "00";
	public final static MessageCode MC_OPEN_BIDDING = new MessageCode(EbuyMethodEnum.OPEN_BIDDING, getEBuyMethodCN(EbuyMethodEnum.OPEN_BIDDING));
	
	/**
	 * 邀请招标
	 */
	public final static String INVITE_BIDDING = "01";
	public final static MessageCode MC_INVITE_BIDDING = new MessageCode(EbuyMethodEnum.INVITE_BIDDING, getEBuyMethodCN(EbuyMethodEnum.INVITE_BIDDING));
	
	/**
	 * 竞争性谈判
	 */
	public final static String NEGOTIATE = "02";
	public final static MessageCode MC_NEGOTIATE = new MessageCode(EbuyMethodEnum.NEGOTIATE, getEBuyMethodCN(EbuyMethodEnum.NEGOTIATE));
	
	/**
	 * 询价
	 */
	public final static String INQUIRY = "03";
	public final static MessageCode MC_INQUIRY = new MessageCode(EbuyMethodEnum.INQUIRY, getEBuyMethodCN(EbuyMethodEnum.INQUIRY));
	
	/**
	 * 单一来源
	 */
	public final static String SINGLE_SOURCE = "04";
	public final static MessageCode MC_SINGLE_SOURCE = new MessageCode(EbuyMethodEnum.SINGLE_SOURCE, getEBuyMethodCN(EbuyMethodEnum.SINGLE_SOURCE));
	
	/**
	 * 议价
	 */
	public final static String TALK = "05";
	public final static MessageCode MC_TALK = new MessageCode(EbuyMethodEnum.TALK, getEBuyMethodCN(EbuyMethodEnum.TALK));	
	
	/**
	 * 竞价
	 */
	public final static String COMPETITION = "06";
	public final static MessageCode MC_COMPETITION = new MessageCode(EbuyMethodEnum.COMPETITION, getEBuyMethodCN(EbuyMethodEnum.COMPETITION));	
	
	/**
	 * 反拍
	 */
	public final static String REVERSE = "07";
	public final static MessageCode MC_REVERSE = new MessageCode(EbuyMethodEnum.REVERSE, getEBuyMethodCN(EbuyMethodEnum.REVERSE));	
	
	/**
	 * 挂牌
	 */
	public final static String LIST = "08";
	public final static MessageCode MC_LIST = new MessageCode(EbuyMethodEnum.LIST, getEBuyMethodCN(EbuyMethodEnum.LIST));	
	
	/**
	 * 竞标
	 */
	public final static String COMPETITIVE_BIDDING = "09";
	public final static MessageCode MC_COMPETITIVE_BIDDING = new MessageCode(EbuyMethodEnum.COMPETITIVE_BIDDING, getEBuyMethodCN(EbuyMethodEnum.COMPETITIVE_BIDDING));	
	
	/**
	 * 拍卖
	 */
	public final static String AUCTION = "10";
	public final static MessageCode MC_AUCTION = new MessageCode(EbuyMethodEnum.AUCTION, getEBuyMethodCN(EbuyMethodEnum.AUCTION));		
	public final static String AGREEMENT_PURCHASE = "20";
	public final static MessageCode MC_AGREEMENT_PURCHASE = new MessageCode(EbuyMethodEnum.AGREEMENT_PURCHASE, getEBuyMethodCN(EbuyMethodEnum.AGREEMENT_PURCHASE));			
	
	
     
	/**
	 * 其它招标方式
	 */
	public final static String OTHER_BIDDING="30";
	public final static MessageCode MC_OTHER_BIDDING = new MessageCode(EbuyMethodEnum.OTHER_BIDDING, getEBuyMethodCN(EbuyMethodEnum.OTHER_BIDDING));
	
	/**
	 * 随机抽取
	 */
	public final static String RANDOM="20";
	public final static MessageCode MC_RANDOM = new MessageCode(EbuyMethodEnum.RANDOM, getEBuyMethodCN(EbuyMethodEnum.RANDOM));
	/**
	 * 综合评估
	 */
	public final static String BIDCOM="21";
	public final static MessageCode MC_BIDCOM = new MessageCode(EbuyMethodEnum.BIDCOM, getEBuyMethodCN(EbuyMethodEnum.BIDCOM));

	
	public static String getEBuyMethodCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EbuyMethodEnum.EBUY_METHOD, str);
	} 
	
	 /**
	 * 资金来源[01:自筹资金,02:上级拨款,03:其他]
	 */
    public final static String MONEY_RESOURCE = "moneyResource";

	public final static String SELF = "01";
	public final static String APPROPRIATION = "02";
	public final static String OTHERS = "03";
	public static final String getMoneyResourceCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EbuyMethodEnum.MONEY_RESOURCE, str);
    }
	
	/**
	 * 服务费支付方式
	 */
	public final static String SERVICE_PAY_TYPE = "servicePayType";//服务费收取方式
	public final static String SUPPLIER_PAY = "01";//中标单位支付
	public final static String BUYER_PAY = "02";//业主单位支付
	
	public static String getServiceFeePayTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EbuyMethodEnum.SERVICE_PAY_TYPE, str);
	} 
	
	/**
	 * 服务费计算方式
	 */
	public final static String SERVICE_CALCULATE_TYPE = "serviceCalculateType";//服务费收取方式
	public final static String FIX_MONEY = "01";//固定金额
	public final static String DIFFIENCE = "02";//差额累计
	public final static String PER_CENT = "03";//中标金额百分比
	public static String getServiceFeeCalculateTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EbuyMethodEnum.SERVICE_CALCULATE_TYPE, str);
	} 
	
	/**
	 * 评标方式
	 */
	public final static String EVAL_METHOD = "evalMethod";//评分方式
	public final static String SCORE = "01";//综合评分法
	public final static String EVALUATION = "02";//综合评价法
	
	public static String getEvalMethodCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EbuyMethodEnum.EVAL_METHOD, str);
	} 
	
	public static String getEBuyMethodEL(String str){
		if (str!=null&&EbuyMethodEnum.OPEN_BIDDING.equals(str)) {
			return "OPEN_BIDDING";
		}else if (str!=null&&EbuyMethodEnum.INVITE_BIDDING.equals(str)) {
			return "INVITE_BIDDING";
		}else if (str!=null&&EbuyMethodEnum.NEGOTIATE.equals(str)) {
			return "NEGOTIATE";
		}else if (str!=null&&EbuyMethodEnum.INQUIRY.equals(str)) {
			return "INQUIRY";
		}else if (str!=null&&EbuyMethodEnum.SINGLE_SOURCE.equals(str)) {
			return "SINGLE_SOURCE";
		}else if (str!=null&&EbuyMethodEnum.AGREEMENT_PURCHASE.equals(str)) {
			return "AGREEMENT_PURCHASE";
		}else if(str!=null&EbuyMethodEnum.OTHER_BIDDING.equals(str)){
			return "OTHER_BIDDING";
		}else{
			return null;
		}
	} 
	public static String getEBuyMethodValue(String str){
		String strValue = "00";
		if("00".equals(str)){
			strValue = EbuyMethodEnum.OPEN_BIDDING;
		}else if("01".equals(str)){
			strValue = EbuyMethodEnum.INVITE_BIDDING;
		}else if("02".equals(str)){
			strValue = EbuyMethodEnum.NEGOTIATE;
		}else if("03".equals(str)){
			strValue = EbuyMethodEnum.INQUIRY;
		}else if("04".equals(str)){
			strValue = EbuyMethodEnum.SINGLE_SOURCE;
		}else if("30".equals(str)){
			strValue = EbuyMethodEnum.OTHER_BIDDING;
		}
		return strValue;
	} 
	
	/**
	 * 根据任务书类型获取对应采购方式
	 * @param taskType 任务书类型，其定义在ProjectEnum类中
	 * @return
	 * Created at 2011-5-30 10:58 by zhouzhanghe
	 */
	public static ArrayList<MessageCode> getEbuyMethodByTaskType(String taskType){
		if(taskType == null)
			return null;
		else if(taskType.equals(ProjectEnum.ZFCG))
			return getZFCGEbuyMethod();
		else if(taskType.equals(ProjectEnum.TDJY))
			return getTDJYEbuyMethod();
		else if(taskType.equals(ProjectEnum.CQJY))
			return getCQJYEbuyMethod();
		else if(taskType.equals(ProjectEnum.JZGC))
			return getJZGCEbuyMethod();
		else if(taskType.equals(ProjectEnum.DLBX))
			return getDLBXEbuyMethod();
		else
			return null;
	}
	
	
	/**
	 * 获取所有的采购方式
	 * @return
	 * Created at 2011-5-30 10:58 by zhouzhanghe
	 */
	public static List getAllEbuyMethod(){
		ArrayList<MessageCode> messageCodeList = new ArrayList<MessageCode>();
		messageCodeList.add(MC_OPEN_BIDDING);
		messageCodeList.add(MC_INVITE_BIDDING);
		messageCodeList.add(MC_NEGOTIATE);
		messageCodeList.add(MC_INQUIRY);
		messageCodeList.add(MC_SINGLE_SOURCE);
		messageCodeList.add(MC_OTHER_BIDDING);
		
		messageCodeList.add(MC_LIST);
		messageCodeList.add(MC_COMPETITIVE_BIDDING);
		messageCodeList.add(MC_AUCTION);
		
		messageCodeList.add(MC_COMPETITION);
		return messageCodeList;
	}
	
	/**
	 * 获取政府采购的采购方式
	 * @return
	 * Created at 2011-5-30 10:58 by zhouzhanghe
	 */
	public static ArrayList<MessageCode> getZFCGEbuyMethod(){
		ArrayList<MessageCode> messageCodeList = new ArrayList<MessageCode>();
		messageCodeList.add(MC_OPEN_BIDDING);
		messageCodeList.add(MC_INVITE_BIDDING);
		messageCodeList.add(MC_NEGOTIATE);
		messageCodeList.add(MC_INQUIRY);
		messageCodeList.add(MC_SINGLE_SOURCE);
		messageCodeList.add(MC_OTHER_BIDDING);
		return messageCodeList;
	}
	
	/**
	 * 获取土地交易的采购方式
	 * @return
	 * Created at 2011-5-30 10:58 by zhouzhanghe
	 */
	public static ArrayList<MessageCode> getTDJYEbuyMethod(){
		ArrayList<MessageCode> messageCodeList = new ArrayList<MessageCode>();
		messageCodeList.add(MC_LIST);
		messageCodeList.add(MC_COMPETITIVE_BIDDING);
		messageCodeList.add(MC_AUCTION);
		return messageCodeList;
	}
	
	/**
	 * 获取产权交易的采购方式
	 * @return
	 * Created at 2011-5-30 10:58 by zhouzhanghe
	 */
	public static ArrayList<MessageCode> getCQJYEbuyMethod(){
		ArrayList<MessageCode> messageCodeList = new ArrayList<MessageCode>();
		messageCodeList.add(MC_COMPETITION);
		return messageCodeList;
	}
	
	/**
	 * 
	 * Description : 代理机构比选采购方式
	 * Create Date: 2011-12-16上午10:44:25 by chenhongjun  
	 * Modified Date: 2011-12-16上午10:44:25 by chenhongjun
	 *@return
	 *上午10:44:25 
	 *ArrayList<MessageCode>
	 */
	public static ArrayList<MessageCode> getDLBXEbuyMethod(){
		ArrayList<MessageCode> messageCodeList = new ArrayList<MessageCode>();
		messageCodeList.add(MC_RANDOM);
		messageCodeList.add(MC_BIDCOM);
		return messageCodeList;
	}
	
	/**
	 * 获取建筑工程的采购方式
	 * @return
	 * Created at 2011-5-30 10:58 by zhouzhanghe
	 */
	public static ArrayList<MessageCode> getJZGCEbuyMethod(){
		ArrayList<MessageCode> messageCodeList = new ArrayList<MessageCode>();
		messageCodeList.add(MC_OPEN_BIDDING);
		messageCodeList.add(MC_INVITE_BIDDING);
		return messageCodeList;
	}
}
