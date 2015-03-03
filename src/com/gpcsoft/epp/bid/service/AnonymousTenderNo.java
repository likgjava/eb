package com.gpcsoft.epp.bid.service;

/**
 * 匿名投标次数控制
 * @author liuy
 *
 */
public interface AnonymousTenderNo {
	/**
	 * 根据投标申请信息，来判断是否可以申请资格
	 * @param tenderApply  投标申请信息
	 * @return  核对结果
	 *          "true_****":表示可以申请投标；"false_****":表示不可能申请投标;其中"****"表示备注信息
	 *          如false_每家单位只允许当天投递三次标书，您今天已投递了三次
	 */
	public String getCheckResult(String tenderApply);
	
	
	/**
	 * 确定投标有效后，将记录数追加
	 * @param tenderApply  投标申请信息
	 */
	public void checkResult(String tenderApply);

}
