package com.gpcsoft.agreement.management.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.core.service.BaseGenericService;

public interface AgreementGoodsclassService extends BaseGenericService<AgreementGoodsclass> {

	/** 
	 * Description :  删除协议分类及其下的的协议商品(安全)
	 * Create Date: 2010-4-22下午12:33:49 by yucy  Modified Date: 2010-4-22下午12:33:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	int removeAgreementGoodsclassAndGoods(Map<String, Object> params);

	/** 
	 * Description :  取得授权分类的信息
	 * Create Date: 2010-4-26上午11:17:02 by yucy  Modified Date: 2010-4-26上午11:17:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Object getAuthorizedClass(Map<String, Object> params);

	/** 
	 * Description :  获得协议分类（带有无新商品属性）
	 * Create Date: 2010-4-27上午11:20:54 by yucy  Modified Date: 2010-4-27上午11:20:54 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<AgreementGoodsclass> getGoodsClassWithTips(String agreementId);

	/** 
	 * Description :  先删掉分类下的商品再根据新的分类添加
	 * Create Date: 2010-4-28下午10:47:29 by yucy  Modified Date: 2010-4-28下午10:47:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeGoodsByClassReAddGoods(AgreementGoodsclass objectService);

	/** 
	 * Description :  修改分类和商品的DiscountRatio
	 * Create Date: 2010-4-29上午09:43:19 by yucy  Modified Date: 2010-4-29上午09:43:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateClassAndGoodsDiscountRatio(AgreementGoodsclass objectService);

	/** 
	 * Description :  添加授权分类
	 * Create Date: 2010-5-6上午11:25:30 by yucy  Modified Date: 2010-5-6上午11:25:30 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer authorizGoodsClass(Map<String, Object> params);

	/** 
	 * Description :  解除分类授权
	 * Create Date: 2010-5-6下午03:40:20 by yucy  Modified Date: 2010-5-6下午03:40:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeAuthorizedClass(Map<String, Object> params);

	/** 
	 * Description :  保存或新增每行数据
	 * Create Date: 2010-5-12下午04:40:49 by yucy  Modified Date: 2010-5-12下午04:40:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveOrUpDateRow(Map<String, Object> params);


}
