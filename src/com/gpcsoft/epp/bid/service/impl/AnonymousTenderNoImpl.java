package com.gpcsoft.epp.bid.service.impl;

import java.util.Hashtable;

import com.gpcsoft.epp.bid.service.AnonymousTenderNo;
import com.gpcsoft.epp.common.utils.DateUtil;

public class AnonymousTenderNoImpl implements AnonymousTenderNo {

	/**
	 * 根据投标申请信息，来判断是否可以申请资格
	 * @param tenderApply  投标申请信息
	 * @return  核对结果
	 *          "true_****":表示可以申请投标；"false_****":表示不可能申请投标;其中"****"表示备注信息
	 *          如false_每家单位只允许当天投递三次标书，您今天已投递了三次
	 */
	private static int SIZE=100000;
	private static Hashtable<String, String> tenderMarkMap=new Hashtable<String, String>(SIZE);
	
	public String getCheckResult(String tenderApply) {
		String checkValue = "false";
		String checkMemo = "";
		String checkTenderApply = tenderApply+""+DateUtil.getCurDate().toString();
		int mapValue = (tenderMarkMap.get(checkTenderApply)==null?0:Integer.parseInt(tenderMarkMap.get(checkTenderApply)));
		if(mapValue < 300){//判断该单位当天投标过的次数，不超过限定的投标次数，则允许其继续做投标
			tenderMarkMap.put(checkTenderApply, ""+(mapValue));
			checkValue = "true";
			checkMemo = "每家单位只允许当天投递三次标书，您今天已投递了  "+mapValue+"  次";
		}else{
			checkValue = "false";
			checkMemo = "每家单位只允许当天投递三次标书，您今天已投递了三次";
		}
		return checkValue+"_"+checkMemo;
	}
	/**
	 * 确定投标有效后，将记录数追加
	 * @param tenderApply  投标申请信息
	 */
	public void checkResult(String tenderApply) {
		String checkTenderApply = tenderApply+""+DateUtil.getCurDate().toString();
		int mapValue = (tenderMarkMap.get(checkTenderApply)==null?0:Integer.parseInt(tenderMarkMap.get(checkTenderApply)));
		tenderMarkMap.put(checkTenderApply, ""+(mapValue+1));
	}
	
	
	
	public static void main(String[] args) {
		AnonymousTenderNoImpl anonymousTenderNo = new AnonymousTenderNoImpl();
		for(int i = 0 ; i < 6 ; i ++){
			System.out.println(anonymousTenderNo.getCheckResult("2323223233"));
		}
	}
	

}
