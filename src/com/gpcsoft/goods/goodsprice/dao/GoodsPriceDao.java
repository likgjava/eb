package com.gpcsoft.goods.goodsprice.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;

public interface GoodsPriceDao extends BaseGenericDao<GoodsPrice> {

	/** 
	 * Description :  禁用行情
	 * Create Date: 2010-4-16下午05:43:12 by yucy  Modified Date: 2010-4-16下午05:43:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateStopStatus(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  删除行情
	 * Create Date: 2010-4-17上午09:55:50 by yucy  Modified Date: 2010-4-17上午09:55:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean deletePrice(Map<String, Object> param)throws Exception;
	/** 
	 * Description : 获得商品的行情 
	 * Create Date: 2010-9-16下午04:17:23 by guoyr  Modified Date: 2010-9-16下午04:17:23 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsPrice> getGoodsPriceList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得行情的热门区域和相应的值
	 * Create Date: 2010-10-8下午02:58:53 by yucy  Modified Date: 2010-10-8下午02:58:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getHotTagAndVelue(Map<String, Object> params) throws Exception;

	/** 
	 * Description :  取得销售额和销售量的图形
	 * Create Date: 2010-10-9下午05:01:34 by yucy  Modified Date: 2010-10-9下午05:01:34 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<ListOrderedMap> getGoodsSalesChartData(Map<String, Object> param) throws Exception;


    public abstract List<OrgInfo> getProvideSupplierByGoods(String s)  throws Exception;

	/** 
	 * Description :  取的相应区域和相应的值
	 * Create Date: 2010-11-3下午02:24:40 by yucy  Modified Date: 2010-11-3下午02:24:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getHotTagAndVelueByDistrict(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得所有行情的区域和值 
	 * Create Date: 2010-11-4上午10:18:11 by yucy  Modified Date: 2010-11-4上午10:18:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getAllPriceAndValue(Map<String, Object> param) throws Exception;

}
