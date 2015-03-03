package com.gpcsoft.agreement.management.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface AgreementGoodsclassDao extends BaseGenericDao<AgreementGoodsclass> {

	
	/** 
	 * Description :  删除一级协议分类品牌(安全删除)
	 * Create Date: 2010-5-5下午03:10:28 by yucy  Modified Date: 2010-5-5下午03:10:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	int removeAgreementGoodsclassAndGoods(Map<String , Object> params);
	

	/** 
	 * Description :  取得授权分类的信息
	 * Create Date: 2010-4-26上午11:18:32 by yucy  Modified Date: 2010-4-26上午11:18:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Object getAuthorizedClass(Map<String, Object> params);

	/** 
	 * Description :  获得协议分类（带有无新商品属性）
	 * Create Date: 2010-4-27上午11:23:33 by yucy  Modified Date: 2010-4-27上午11:23:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<AgreementGoodsclass> agreementGoodsclassDaoHibernate(String agreementId);

	/** 
	 * Description :  先删掉协议分类下的商品然后根据新的参数添加
	 * Create Date: 2010-4-28下午10:49:11 by yucy  Modified Date: 2010-4-28下午10:49:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeGoodsByClassReAddGoods(AgreementGoodsclass objectService);

	/** 
	 * Description :  修改分类和商品的DiscountRatio
	 * Create Date: 2010-4-29上午09:45:51 by yucy  Modified Date: 2010-4-29上午09:45:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateClassAndGoodsDiscountRatio(AgreementGoodsclass objectService);


	/** 
	 * Description :  解除分类授权
	 * Create Date: 2010-5-6下午03:42:10 by yucy  Modified Date: 2010-5-6下午03:42:10 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeAuthorizedClass(Map<String, Object> params);


	/** 
	 * Description :  保存或新增每行数据
	 * Create Date: 2010-5-12下午04:45:08 by yucy  Modified Date: 2010-5-12下午04:45:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveOrUpDateRow(Map<String, Object> params);




}
