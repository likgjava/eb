package com.gpcsoft.agreement.management.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;

public interface AgreementGoodsDao extends BaseGenericDao<AgreementGoods> {

	/** 
	 * Description :   取得授权未授权的商品
	 * Create Date: 2010-4-26下午08:14:45 by yucy  Modified Date: 2010-4-26下午08:14:45 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getAuthorizedGoods(Map<String, Object> params);

	/** 
	 * Description :  解除授权
	 * Create Date: 2010-5-6下午05:43:50 by yucy  Modified Date: 2010-5-6下午05:43:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeAuthorizedGoods(Map<String, Object> params);

	/** 
	 * Description :  删除商品（安全）
	 * Create Date: 2010-5-13下午02:38:24 by yucy  Modified Date: 2010-5-13下午02:38:24 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeGoods(Map<String, Object> params);

	/** 
	 * Description :  
	 * Create Date: 2010-5-19下午01:30:31 by yucy  Modified Date: 2010-5-19下午01:30:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getBrandSelectParam(Map<String, Object> params);

	/** 
	 * Description :   获取行情商品展示的列表数据
	 * Create Date: 2009-4-13上午11:04:42 by yucy  Modified Date: 2009-4-13上午11:04:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> getPriceGoodsListByAgree(Page<Goods> page,Map<String, Object> param) throws Exception;
	
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
	public List<AgreementGoods> getAgreementGoodsListByParam(Map<String, Object> param) throws Exception;
}
