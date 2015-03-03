package com.gpcsoft.pubservice.utils;

import java.util.Date;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.srplatform.baseData.dao.SequenceNumberDao;
import com.gpcsoft.srplatform.baseData.dao.hibernate.SequenceNumberDaoHibernate;

public class SequenceNumberUtil {
	
	//序列号格式
	public static String SN_FORMAT = "pubservice.sequenceNumber.snFormat";
	
	//序列号时间格式
	public static String SN_TIME_FORMAT = "pubservice.sequenceNumber.timeFormat";

	//序列号前缀和序号长度([SN_PREFIX_PROJECT:P:3]=[键:序列号前缀:序号长度])
	//[SN_PREFIX_SHOPPING_CART:SC:3:购物车，SN_PREFIX_ORDER:O:4:订单，SN_PREFIX_CONTRACT:C:3:合同，SN_PREFIX_PROJECT:P:3:项目，
	//SN_PREFIX_AGREEMENT:A:3:协议，SN_PREFIX_PROCUREMENTTASK:PT:3:任务单，SN_PREFIX_GOODS_BRAND:B:6:商品品牌，SN_PREFIX_GIFT:GIFT:3:礼物]
	//SN_PREFIX_BULLETIN:BT:3:公告]
	public final static String SEQUENCE_NUMBER_PREFIX ="bizdata.sequenceNumberPrefix";
	public final static String SN_PREFIX_SHOPPING_CART = "SN_PREFIX_SHOPPING_CART";
	public final static String SN_PREFIX_ORDER = "SN_PREFIX_ORDER";
	public final static String SN_PREFIX_CONTRACT = "SN_PREFIX_CONTRACT";
	public final static String SN_PREFIX_PROJECT = "SN_PREFIX_PROJECT";
	public final static String SN_PREFIX_BULLETIN = "SN_PREFIX_BULLETIN";
	public final static String SN_PREFIX_AGREEMENT = "SN_PREFIX_AGREEMENT";
	public final static String SN_PREFIX_PROCUREMENTTASK = "SN_PREFIX_PROCUREMENTTASK";
	public final static String SN_PREFIX_GOODS_BRAND = "SN_PREFIX_GOODS_BRAND";
	public final static String SN_PREFIX_GIFT = "SN_PREFIX_GIFT";
	
	private static SequenceNumberDao sequenceNumberDaoHibernate;
	private static FrameMessageResource messageSource;
	
	static {
		sequenceNumberDaoHibernate =  (SequenceNumberDaoHibernate) FrameBeanFactory.getBean("sequenceNumberDaoHibernate");
		messageSource =  (FrameMessageResource) FrameBeanFactory.getBean("frameMessageResource");
	}
	
	/** 
	 * Description :  获取购物车的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getShoppingCartSN() throws Exception {
		return getPrefixTimeSN(getPrefix(SN_PREFIX_SHOPPING_CART), Integer.parseInt(getNolength(SN_PREFIX_SHOPPING_CART)));
	}
	
	/** 
	 * Description :  获取订单的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getOrderSN() throws Exception {
		return getPrefixTimeSN(getPrefix(SN_PREFIX_ORDER), Integer.parseInt(getNolength(SN_PREFIX_ORDER)));
	}
	
	/** 
	 * Description :  获取合同的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getContractSN() throws Exception {
		return getPrefixTimeSN(getPrefix(SN_PREFIX_CONTRACT), Integer.parseInt(getNolength(SN_PREFIX_CONTRACT)));
	}
	
	/** 
	 * Description :  获取项目的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getProjectSN() throws Exception {
		return getPrefixTimeSN(getPrefix(SN_PREFIX_PROJECT), Integer.parseInt(getNolength(SN_PREFIX_PROJECT)));
	}
	
	/** 
	 * Description :  获取公告的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getBulletinSN() throws Exception {
		return getPrefixTimeSN(getPrefix(SN_PREFIX_BULLETIN), Integer.parseInt(getNolength(SN_PREFIX_BULLETIN)));
	}
	
	/** 
	 * Description :  获取协议的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getAgreementSN() throws Exception {
		return getPrefixTimeSN(getPrefix(SN_PREFIX_AGREEMENT), Integer.parseInt(getNolength(SN_PREFIX_AGREEMENT)));
	}
	
	/** 
	 * Description :  获取任务单的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getProcurementtaskSN() throws Exception {
		return getPrefixTimeSN(getPrefix(SN_PREFIX_PROCUREMENTTASK), Integer.parseInt(getNolength(SN_PREFIX_PROCUREMENTTASK)));
	}
	
	/** 
	 * Description :  获取商品的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   goodsClassCode:商品分类编号
	 * @return  
	 * @Exception   
	 */
	public static String getGoodsSN(String goodsClassCode) throws Exception {
		return getPrefixSN(goodsClassCode + DateUtil.format(new Date(), "yyyy"), 6);
	}
	
	/** 
	 * Description :  获取商品品牌的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   goodsClassCode:商品分类编号
	 * @return  
	 * @Exception   
	 */
	public static String getGoodsBrandSN() throws Exception {
		return getPrefixSN(getPrefix(SN_PREFIX_GOODS_BRAND) + DateUtil.format(new Date(), "yyyy"), 6);
	}
	
	/** 
	 * Description :  获取礼物的序列号
	 * Create Date: 2011-5-6下午12:01:09 by likg  Modified Date: 2011-5-6下午12:01:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getGiftSN() throws Exception {
		return getPrefixTimeSN(getPrefix(SN_PREFIX_GIFT), Integer.parseInt(getNolength(SN_PREFIX_GIFT)));
	}
	
	/** 
	 * Description :  根据键值获取序列号的前缀
	 * Create Date: 2011-5-6下午12:47:12 by likg  Modified Date: 2011-5-6下午12:47:12 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public final static String getPrefix(String key){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getValueByConstant(SEQUENCE_NUMBER_PREFIX, key);
	}
	
	/** 
	 * Description :  根据键值获取序列号的序号长度
	 * Create Date: 2011-5-6下午12:50:04 by likg  Modified Date: 2011-5-6下午12:50:04 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public final static String getNolength(String key){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByConstant(SEQUENCE_NUMBER_PREFIX, key);
	}
	
	/** 
	 * Description :  获取“前缀序号”格式的序列号(eg:A01)
	 * Create Date: 2011-5-6上午09:40:35 by likg  Modified Date: 2011-5-6上午09:40:35 by likg
	 * @param   prefix:前缀
	 * @param   sLength:序号长度
	 * @return  
	 * @Exception   
	 */
	private static String getPrefixSN(String prefix, int sLength) throws Exception {
		return prefix + sequenceNumberDaoHibernate.updateAndGetCurSn(prefix, sLength);
	}

	/** 
	 * Description :  获取“前缀-时间-序号”格式的序列号(eg:SC-20110608-001)
	 * Create Date: 2011-5-6上午09:40:35 by likg  Modified Date: 2011-5-6上午09:40:35 by likg
	 * @param   prefix:前缀
	 * @param   sLength:序号长度
	 * @return  
	 * @Exception   
	 */
	private static String getPrefixTimeSN(String prefix, int sLength) throws Exception {
		String snFormat = messageSource.getMessage(SN_FORMAT); //序列号格式
		String separator1 = snFormat.substring(1, snFormat.indexOf("t")); //前缀与时间之间的分隔符
		String separator2 = snFormat.substring(snFormat.indexOf("t")+1, snFormat.length()-1); //时间与序号之间的分隔符
		
		prefix += separator1 + DateUtil.format(new Date(), messageSource.getMessage(SN_TIME_FORMAT));
		return prefix + separator2 + sequenceNumberDaoHibernate.updateAndGetCurSn(prefix, sLength);
	}

}