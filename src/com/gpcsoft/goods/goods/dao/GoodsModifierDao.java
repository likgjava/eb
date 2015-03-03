package com.gpcsoft.goods.goods.dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.GoodsModifier;

public interface GoodsModifierDao extends BaseGenericDao<GoodsModifier> {
	/**
     * Description : 查看该品牌下面的维护商列表的信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
	@SuppressWarnings("unchecked")
	public Page getGoodsModifiersListByBrandId(final String brandId, int pageNumber, final int pageSize) throws DataAccessException ;

    /**
     * 删除该维护商在该品牌下面的所有信息(在给指定维护商选定商品分类的时候就要先做这个操作)。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param [] orgIds
     * @param brandId
     */
    public void removeGoodsModifierBrandClass(String[] orgIds, String brandId);

    /**
     * 清空该维护商在该品牌下面所维护的分类信息(在给指定维护商选定商品分类的时候就要先做这个操作)。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param [] orgIds
     * @param brandId
     */
    public void resetGoodsModifierBrandClass(String[] orgIds, String brandId);

	/**
     * Description : 查找该品牌下面的还没有被不是维护商的所有供应商信息。
     * Create Date: Mar 31, 2010 14:27:22 PM by liujf  Modified Date: Mar 31, 2010 14:27:22 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	public Page getNonGoodsModifierOrgInfo(final String brandId, int pageNumber, final int pageSize) ;
    
    /**
     * Description：查看品牌维护商的信息.
     * Created Date:Jun 17, 2010 10:45:53 AM by liujf Modified Date:Jun 17, 2010 10:45:53 AM by liujf
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public Page getGoodsModifier(String supplierId, int pageNumber, final int pageSize) throws Exception;
	
	/** 
	 * Description :  指定维护商品品牌列表
	 * Create Date: 2010-8-2下午06:15:04 by sunl  Modified Date: 2010-8-2下午06:15:04 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsModifier> getGoodsBrandBySupplierId(Page<GoodsModifier> page,HttpServletRequest request) throws Exception;
	
	/** 
	 * Description : 获得还没有指定维护供应商的商品
	 * Create Date: 2010-8-5下午03:35:31 by guoyr  Modified Date: 2010-8-5下午03:35:31 by guoyr
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsModifier> getUnSpecifiedGoods(Page<GoodsModifier> page ,String goodsClassName, String goodsBrandName, String order, String order_flag, String orgInfoId) throws Exception;
	
	/** 
	 * Description : 修改商品的维护商  
	 * Create Date: 2010-8-6上午11:09:34 by guoyr  Modified Date: 2010-8-6上午11:09:34 by guoyr
	 * @param goodsClassId 商品的分类  
	 * @param goodsBrandId 商品的品牌
	 * @param supplierId 商品的维护的供应商
	 * @return  
	 * @Exception   
	 */
	public void updateGoodsModifier(String objId,String goodsClassId, String goodsBrandId, String supplierId);


	/** 
	 * Description :  为新品牌更新维护商
	 * Create Date: 2010-9-2下午04:34:16 by yucy  Modified Date: 2010-9-2下午04:34:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateBrandForNewBrand(String oldBrandId, String objId) throws Exception;
	
}
