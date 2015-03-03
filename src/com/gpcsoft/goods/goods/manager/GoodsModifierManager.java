package com.gpcsoft.goods.goods.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsModifier;

public interface GoodsModifierManager extends BaseManager<GoodsModifier> {
	
	/** 
	 * Description : 保存商品的维护商  
	 * Create Date: 2010-8-6上午11:09:34 by guoyr  Modified Date: 2010-8-6上午11:09:34 by guoyr
	 * @param goodsClassId 商品的分类  
	 * @param goodsBrandIds 商品的品牌
	 * @param supplierId 商品的维护的供应商
	 * @return  
	 * @Exception   
	 */
	public void saveGoodsModifier(String goodsClassId, String goodsBrandIds, String supplierId);
	/**
     * Description : 查看该品牌下面的维护商列表的信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	public Page getGoodsModifiersListByBrandId(String brandId, Page page) throws Exception ;

    /** 
     * Description :  指定维护商 
     * Create Date: 2010-11-9下午05:49:49 by yucy  Modified Date: 2010-11-9下午05:49:49 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    public void createGoodsModifier(GoodsBrand brand, String [] orgIDs) ;

	/**
     * Description : 给指定的品牌的某个供应商指定其所维护的商品分类。
     * Create Date: Mar 31, 2010 15:31:32 PM by liujf  Modified Date: Mar 31, 2010 15:31:32 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    public void update4SpecifyGoodsClass(String goodsBrandId, String orgId, String [] goodsClassIds);

    /**
     * 删除该维护商在该品牌下面的所有信息(在给指定维护商选定商品分类的时候就要先做这个操作)。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param orgId
     * @param brandId
     */
    public void removeGoodsModifierBrandClass(String[] orgId, String brandId);

	/**
     * Description : 查找该品牌下面的还没有被不是维护商的所有供应商信息。
     * Create Date: Mar 31, 2010 14:27:22 PM by liujf  Modified Date: Mar 31, 2010 14:27:22 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	public Page getNonGoodsModifierOrgInfo(final String brandId, int pageNumber, final int pageSize);
    /**
     * Description : 由机构查找所以品牌ID。
     * @param OrgInfoObjId
     * @return
     * @throws Exception
     */
    public StringBuffer getBrandObjIdByOrgInfo(String OrgInfoObjId) throws Exception;
    
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
	 * Description :  为新品牌更新维护商
	 * Create Date: 2010-9-2下午04:34:16 by yucy  Modified Date: 2010-9-2下午04:34:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateBrandForNewBrand(String oldBrandId, String objId) throws Exception;
}
