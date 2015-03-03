/**
 * 
 */
package com.gpcsoft.epp.eval.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.epp.project.domain.MessageCode;

/**
 * @author Administrator
 *
 */
public class CongruousFactorEnum {
	public static final String  FIRSTCHECK = "firstCheck";
	
	public static final String  RECHECK = "reCheck";
	
	public static final String  PRICECHECK = "priceCheck";
	
	public final static String AUDIT_METHOD_TYPE = "auditModelMethod";
	
	/**
	 * 资格后审
	 */						   
	public final static String POSTQUALIFICATION = "00";
	public final static MessageCode MC_POSTQUALIFICATION = new MessageCode(CongruousFactorEnum.POSTQUALIFICATION, getModelTypeCN(CongruousFactorEnum.POSTQUALIFICATION));
	
	/**
	 * 技术打分
	 */
	public final static String TECH_GRADE ="01";
	public final static MessageCode MC_TECH_GRADE = new MessageCode(CongruousFactorEnum.TECH_GRADE, getModelTypeCN(CongruousFactorEnum.TECH_GRADE));
	
	/**
	 * 商务打分
	 */
	public final static String BUSI_GRADE = "02";
	public final static MessageCode MC_BUSI_GRADE = new MessageCode(CongruousFactorEnum.BUSI_GRADE, getModelTypeCN(CongruousFactorEnum.BUSI_GRADE));
	
	/**
	 * 价格打分
	 */
	public final static String PRICE_GRADE = "03";
	public final static MessageCode MC_PRICE_GRADE = new MessageCode(CongruousFactorEnum.PRICE_GRADE, getModelTypeCN(CongruousFactorEnum.PRICE_GRADE));
	/**
	 * 综合打分
	 */
	public final static String SUMMATED_GRADE = "04";
	public final static MessageCode MC_SUMMATED_GRADE = new MessageCode(CongruousFactorEnum.SUMMATED_GRADE, getModelTypeCN(CongruousFactorEnum.SUMMATED_GRADE));
	
	/**
	 * 综合评价
	 */
	public final static String SUMMATED_EVALUATION = "05";
	public final static MessageCode MC_SUMMATED_EVALUATION = new MessageCode(CongruousFactorEnum.SUMMATED_EVALUATION, getModelTypeCN(CongruousFactorEnum.SUMMATED_EVALUATION));
	
	public static String getModelTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CongruousFactorEnum.AUDIT_METHOD_TYPE, str);
	} 
	
}
