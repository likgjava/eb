package com.gpcsoft.goods.goods.manager.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsModifierDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsModifier;
import com.gpcsoft.goods.goods.manager.GoodsModifierManager;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class GoodsModifierManagerImpl extends BaseManagerImpl<GoodsModifier> implements GoodsModifierManager {

	@Autowired(required=true)  @Qualifier("goodsModifierDaoHibernate")
	private GoodsModifierDao goodsModifierDaoHibernate;

	/** 
	 * Description : 保存商品的维护商  
	 * Create Date: 2010-8-6上午11:09:34 by guoyr  Modified Date: 2010-8-6上午11:09:34 by guoyr
	 * @param goodsClassId 商品的分类  
	 * @param goodsBrandIds 商品的品牌
	 * @param supplierId 商品的维护的供应商
	 * @return  
	 * @Exception   
	 */
	public void saveGoodsModifier(String goodsClassId, String goodsBrandId, String supplierId) {
		 GoodsModifier goodsModifier = new GoodsModifier();
		 goodsModifier.setObjId(null);
		 GoodsClass goodsClass = new GoodsClass();
		 goodsClass.setObjId(goodsClassId);
		 goodsModifier.setGoodsClass(goodsClass);
		 GoodsBrand goodsBrand = new GoodsBrand();
		 goodsBrand.setObjId(goodsBrandId);
		 goodsModifier.setGoodsBrand(goodsBrand);
		 OrgInfo supplier = new OrgInfo();
		 supplier.setObjId(supplierId);
		 goodsModifier.setSupplier(supplier);
		 goodsModifierDaoHibernate.save(goodsModifier);
	}
	/**
     * Description : 查看该品牌下面的维护商列表的信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	public Page getGoodsModifiersListByBrandId(String brandId, Page pPage) throws Exception {
/*    	Page page = goodsModifierDaoHibernate.getGoodsModifiersListByBrandId(brandId, new Long(pPage.getPageNum()).intValue(), pPage.getPageSize());
    	
    	List<GoodsModifier> goodsModifiersList = page.getData();
    	for (GoodsModifier goodsModifier : goodsModifiersList) {
    		if (goodsModifier.getSupplier() != null) {
    			OrgInfo org = goodsModifier.getSupplier();
        		String goodsModifierNames = getGoodsModifierNames(brandId, org.getObjId());
        		goodsModifier.setGoodsModifierNames(goodsModifierNames);
    		}
    	}
    	
    	return page;*/
    	return null;
    }
    
    /**
     * Description : 查看该品牌下面的某个维护商所维护的商品分类信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param brandId
     * @param supplierId
     * @return  
     * @Exception
     */
    public List<GoodsModifier> getGoodsModifiersList(String brandId, String orgId) {
    	List<GoodsModifier> goodsModifiersList = goodsModifierDaoHibernate.findbyHql("from GoodsModifier m where m.goodsBrand.objId=? and m.supplier.objId=?", brandId, orgId);
    	
    	return goodsModifiersList;
    }

    /**
     * Description : 查看该品牌下面的某个维护商所维护的商品分类信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param brandId
     * @param orgId
     * @return  
     * @Exception
     */
    @SuppressWarnings("unused")
	private String getGoodsModifierNames(String brandId, String orgId) {
    	List<GoodsModifier> goodsModifiersList = getGoodsModifiersList(brandId, orgId);
    	if (goodsModifiersList == null || goodsModifiersList.isEmpty()) {
        	return null;
    	}

    	StringBuffer names = new StringBuffer();
		for (GoodsModifier goodsModifier : goodsModifiersList) {
			if (goodsModifier.getGoodsClass() != null) {
				names.append(goodsModifier.getGoodsClass().getGoodsClassName() + ",");
			}
		}
		
		if (names.length() > 0) {
			return names.deleteCharAt(names.length() - 1).append(".").toString();
		} else {
			return null;
		}
    }

    /** 
     * Description :  指定维护商 
     * Create Date: 2010-11-9下午05:49:49 by yucy  Modified Date: 2010-11-9下午05:49:49 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    public void createGoodsModifier(GoodsBrand brand, String [] orgIDs) {
        Assert.notNull(brand.getObjId());
        Assert.notNull(orgIDs);
        
        Date currentDate = new Date();
        User currentUser = AuthenticationHelper.getCurrentUser(true);
        
        for (String orgID : orgIDs) {
        	for(GoodsClass goodsClass : brand.getGoodsClasses()){
        		GoodsModifier goodsModifier = new GoodsModifier();
        		goodsModifier.setGoodsBrand(brand);
        		goodsModifier.setGoodsClass(goodsClass);
        		OrgInfo supplier = new OrgInfo();
        		supplier.setObjId(orgID);
        		goodsModifier.setSupplier(supplier);
        		goodsModifier.setCreateTime(currentDate);
        		goodsModifier.setUpdateTime(currentDate);
        		goodsModifier.setCreateUser(currentUser);
        		goodsModifier.setUpdateUser(currentUser);
        		goodsModifierDaoHibernate.save(goodsModifier);
        	}
        }
    }

	/**
     * Description : 给指定的品牌的某个供应商指定其所维护的商品分类。
     * Create Date: Mar 31, 2010 15:31:32 PM by liujf  Modified Date: Mar 31, 2010 15:31:32 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    public void update4SpecifyGoodsClass(String goodsBrandId, String orgId, String [] goodsClassIds) {
    	Assert.notNull(goodsBrandId);
        Assert.notNull(orgId);
//        Assert.notNull(goodsClassIds);
    	
    	/*删除该维护商在该品牌下面的所有信息*/
        String[] orgIds = new String[1];
        orgIds[0] = orgId;
        if (goodsClassIds != null && goodsClassIds.length > 0) {
        	goodsModifierDaoHibernate.removeGoodsModifierBrandClass(orgIds, goodsBrandId);
        } else {
        	/*如果不选择任何商品分类，则需要清空该维护商在该品牌下面所维护的分类信息*/
        	goodsModifierDaoHibernate.resetGoodsModifierBrandClass(orgIds, goodsBrandId);
        }
        
        GoodsBrand goodsBrand = new GoodsBrand();
        goodsBrand.setObjId(goodsBrandId);
        Date currentDate = new Date();
        User currentUser = AuthenticationHelper.getCurrentUser(true);
        
        if (goodsClassIds != null && goodsClassIds.length > 0) {
        	for (String goodsClassId : goodsClassIds) {
            	GoodsModifier goodsModifier = new GoodsModifier();
            	goodsModifier.setGoodsBrand(goodsBrand);
            	OrgInfo supplier = new OrgInfo();
            	supplier.setObjId(orgId);
            	GoodsClass goodsClass = new GoodsClass();
            	goodsClass.setObjId(goodsClassId);
            	goodsModifier.setGoodsClass(goodsClass);
            	goodsModifier.setSupplier(supplier);
            	goodsModifier.setCreateTime(currentDate);
            	goodsModifier.setUpdateTime(currentDate);
            	goodsModifier.setCreateUser(currentUser);
            	goodsModifier.setUpdateUser(currentUser);
            	goodsModifierDaoHibernate.save(goodsModifier);
            }
        }
    }

    /**
     * 清空该维护商在该品牌下面所维护的分类信息(在给指定维护商选定商品分类的时候就要先做这个操作)。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param [] orgIds
     * @param brandId
     */
    public void resetGoodsModifierBrandClass(String[] orgIds, String brandId) {
    	Assert.notNull(brandId);
        Assert.notNull(orgIds);
        
    	goodsModifierDaoHibernate.resetGoodsModifierBrandClass(orgIds, brandId);
    }
    
    /**
     * 删除该维护商在该品牌下面的所有信息(在给指定维护商选定商品分类的时候就要先做这个操作)。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param orgId
     * @param brandId
     */
    public void removeGoodsModifierBrandClass(String[] orgIds, String brandId) {
    	Assert.notNull(brandId);
        Assert.notNull(orgIds);
        
    	goodsModifierDaoHibernate.removeGoodsModifierBrandClass(orgIds, brandId);
    }

	/**
     * Description : 查找该品牌下面的还没有被不是维护商的所有供应商信息。
     * Create Date: Mar 31, 2010 14:27:22 PM by liujf  Modified Date: Mar 31, 2010 14:27:22 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	public Page getNonGoodsModifierOrgInfo(final String brandId, int pageNumber, final int pageSize) {
    	Page page = goodsModifierDaoHibernate.getNonGoodsModifierOrgInfo(brandId, pageNumber, pageSize);
    	
    	return page;
    }
    /**
     * Description : 由机构查找所以品牌ID。
     * @param OrgInfoObjId
     * @return
     * @throws Exception
     */
    public StringBuffer getBrandObjIdByOrgInfo(String OrgInfoObjId) throws Exception{
    	StringBuffer brandIds = new StringBuffer();
    	List<GoodsModifier> modifierList = goodsModifierDaoHibernate.findbyHql("from GoodsModifier where supplier.objId = ?", OrgInfoObjId);
    	if(modifierList.size()>0){
    		Set<String> brandSet = new HashSet<String>();
	    	for(GoodsModifier modifier : modifierList){
	    		brandSet.add(modifier.getGoodsBrand().getObjId());
	    		//brandIds.append(modifier.getGoodsBrand().getObjId()+",");
	    	}
	    	Object[] brands = brandSet.toArray();
	    	for(int i=0;i<brands.length;i++){
	    		brandIds.append(brands[i]+",");
	    	}
	    	return brandIds;
    	}else{
    		return null;
    	}
    }
    
    /**
     * Description：查看品牌维护商的信息.
     * Created Date:Jun 17, 2010 10:45:53 AM by liujf Modified Date:Jun 17, 2010 10:45:53 AM by liujf
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public Page getGoodsModifier(String supplierId, int pageNumber, final int pageSize) throws Exception {
		return goodsModifierDaoHibernate.getGoodsModifier(supplierId, pageNumber, pageSize);
	}
	
	/** 
	 * Description :  为新品牌更新维护商
	 * Create Date: 2010-9-2下午04:34:16 by yucy  Modified Date: 2010-9-2下午04:34:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateBrandForNewBrand(String oldBrandId, String objId)throws Exception {
		goodsModifierDaoHibernate.updateBrandForNewBrand(oldBrandId, objId);
	}
}
