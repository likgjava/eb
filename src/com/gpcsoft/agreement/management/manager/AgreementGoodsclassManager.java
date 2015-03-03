package com.gpcsoft.agreement.management.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.core.manager.BaseManager;

public interface AgreementGoodsclassManager extends BaseManager<AgreementGoodsclass> {

	/** 
	 * Description :  删除协议分类及其下的的协议商品(安全)
	 * Create Date: 2010-5-7上午11:04:56 by yucy  Modified Date: 2010-5-7上午11:04:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	int removeAgreementGoodsclassAndGoods(Map<String, Object> params);

	/** 
	 * Description : 取得授权分类的信息 
	 * Create Date: 2010-5-7上午11:05:12 by yucy  Modified Date: 2010-5-7上午11:05:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Object getAuthorizedClass(Map<String, Object> params);

	/** 
	 * Description :  获得协议分类（带有无新商品属性）
	 * Create Date: 2010-5-7上午11:05:15 by yucy  Modified Date: 2010-5-7上午11:05:15 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<AgreementGoodsclass> getGoodsClassWithTips(String agreementId);

	/** 
	 * Description :  先删掉分类下的商品再根据新的分类添加
	 * Create Date: 2010-5-7上午11:05:18 by yucy  Modified Date: 2010-5-7上午11:05:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeGoodsByClassReAddGoods(AgreementGoodsclass objectService);

	/** 
	 * Description :   修改分类和商品的DiscountRatio
	 * Create Date: 2010-5-7上午11:05:22 by yucy  Modified Date: 2010-5-7上午11:05:22 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateClassAndGoodsDiscountRatio(AgreementGoodsclass objectService);

	/** 
	 * Description :  添加授权分类
	 * Create Date: 2010-5-7上午11:05:25 by yucy  Modified Date: 2010-5-7上午11:05:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer authorizGoodsClass(Map<String, Object> params);

	/** 
	 * Description :  解除分类授权
	 * Create Date: 2010-5-7上午11:05:28 by yucy  Modified Date: 2010-5-7上午11:05:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeAuthorizedClass(Map<String, Object> params);

	/** 
	 * Description : 保存或新增每行数据() 
	 * Create Date: 2010-5-12下午04:43:12 by yucy  Modified Date: 2010-5-12下午04:43:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveOrUpDateRow(Map<String, Object> params);

}
