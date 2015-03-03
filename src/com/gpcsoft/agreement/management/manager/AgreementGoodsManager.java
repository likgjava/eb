package com.gpcsoft.agreement.management.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;

public interface AgreementGoodsManager extends BaseManager<AgreementGoods> {

	/** 
	 * Description :  
	 * Create Date: 2010-5-7上午10:44:20 by yucy  Modified Date: 2010-5-7上午10:44:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	List getAuthorizedGoods(Map<String, Object> params);

	/** 
	 * Description :  
	 * Create Date: 2010-5-7上午10:44:23 by yucy  Modified Date: 2010-5-7上午10:44:23 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer authorizGoods(Map<String, Object> params);

	/** 
	 * Description :  
	 * Create Date: 2010-5-7上午10:44:26 by yucy  Modified Date: 2010-5-7上午10:44:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeAuthorizedGoods(Map<String, Object> params);

	/** 
	 * Description :  删除商品(安全)
	 * Create Date: 2010-5-13下午02:36:57 by yucy  Modified Date: 2010-5-13下午02:36:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeGoods(Map<String, Object> params);

	/** 
	 * Description :  
	 * Create Date: 2010-5-19下午01:29:23 by yucy  Modified Date: 2010-5-19下午01:29:23 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	List getBrandSelectParam(Map<String, Object> params);

	/** 
	 * Description :   获取行情商品展示的列表数据
	 * Create Date: 2009-4-13上午11:04:42 by yucy  Modified Date: 2009-4-13上午11:04:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Goods> getPriceGoodsListByAgree(Page<Goods> page,Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取行情商品协议数据(折扣率，单价，协议价等等)
	 * Create Date: 2011-12-6下午02:45:02 by yucy  Modified Date: 2011-12-6下午02:45:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public AgreementGoods getAgreementGoodsByParam(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取行情商品列表)
	 * Create Date: 2011-12-6下午02:45:02 by yucy  Modified Date: 2011-12-6下午02:45:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<AgreementGoods> getAgreementGoodsListByParam(Map<String, Object> param) throws Exception;
}
