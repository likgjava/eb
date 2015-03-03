package com.gpcsoft.goods.goods.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsModifierDao;
import com.gpcsoft.goods.goods.domain.GoodsModifier;
import com.gpcsoft.goods.goods.manager.GoodsModifierManager;
import com.gpcsoft.goods.goods.service.GoodsModifierService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

@Service 
public class GoodsModifierServiceImpl extends BaseGenericServiceImpl<GoodsModifier> implements GoodsModifierService {

	@Autowired(required=true) @Qualifier("goodsModifierManagerImpl")
	private GoodsModifierManager goodsModifierManager;
	
	@Autowired(required=true)  @Qualifier("goodsModifierDaoHibernate")
	private GoodsModifierDao goodsModifierDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("roleManagerImpl")
	private RoleManager roleManager;
	/** 
	 * Description : 修改商品的维护商，如果该分类对应的商品的品牌有变更，则把变更以前的也更改  
	 * Create Date: 2010-8-6上午11:09:34 by guoyr  Modified Date: 2010-8-6上午11:09:34 by guoyr
	 * @param goodsClassId 商品的分类  
	 * @param goodsBrandId 商品的品牌
	 * @param supplierId 商品的维护的供应商
	 * @return  
	 * @Exception   
	 */
	public void updateGoodsModifier(String objId, String goodsClassId, String goodsBrandId, String supplierId){
		goodsModifierDaoHibernate.updateGoodsModifier(objId ,goodsClassId, goodsBrandId, supplierId);
	}
	/** 
	 * Description : 保存商品的维护商  
	 * Create Date: 2010-8-6上午11:09:34 by guoyr  Modified Date: 2010-8-6上午11:09:34 by guoyr
	 * @param goodsClassId 商品的分类  
	 * @param goodsBrandIds 商品的品牌
	 * @param supplierId 商品的维护的供应商
	 * @return  
	 * @Exception   
	 */
	public void saveGoodsModifier(String goodsClassIds, String goodsBrandId, String supplierId){
		
		// 新增
		for(String goodsClassId : goodsClassIds.split(",")) {
			if("".equals(goodsClassId))continue;
			goodsModifierManager.saveGoodsModifier(goodsClassId, goodsBrandId, supplierId);
		}
	}
	/**
     * Description : 查看该品牌下面的维护商列表的信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param   
     * @return  pageNumber, final int pageSize
     * @Exception
     */
    @SuppressWarnings("unchecked")
	public Page getGoodsModifiersListByBrandId(String brandId, Page pPage) throws Exception {
    	Page page = goodsModifierManager.getGoodsModifiersListByBrandId(brandId, pPage);
        
        return page;
    }

	/**
     * Description : 给指定的品牌的某个供应商指定其所维护的商品分类。
     * Create Date: Mar 31, 2010 15:31:32 PM by liujf  Modified Date: Mar 31, 2010 15:31:32 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    public void update4SpecifyGoodsClass(String goodsBrandId, String orgId, String [] goodsClassIds) {
    	goodsModifierManager.update4SpecifyGoodsClass(goodsBrandId, orgId, goodsClassIds);
    }
    
    /**
     * 删除该维护商在该品牌下面的所有信息(在给指定维护商选定商品分类的时候就要先做这个操作)。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param orgId
     * @param brandId
     */
    public void removeGoodsModifierBrandClass(String[] orgId, String brandId) {
    	Assert.notNull(brandId);
        Assert.notNull(orgId);
        
        goodsModifierManager.removeGoodsModifierBrandClass(orgId, brandId);
    }

	/**
     * Description : 查找该品牌下面的还没有被不是维护商的所有供应商信息。
     * Create Date: Mar 31, 2010 14:27:22 PM by liujf  Modified Date: Mar 31, 2010 14:27:22 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	public Page getNonGoodsModifierOrgInfo(final String goodsBrandId, int pageNumber, final int pageSize) {
    	Assert.notNull(goodsBrandId);
    	
    	Page page = goodsModifierManager.getNonGoodsModifierOrgInfo(goodsBrandId, pageNumber, pageSize);
    	
    	return page;
    }
    /**
     * Description : 由机构查找所以品牌ID。
     * @param OrgInfoObjId
     * @return
     * @throws Exception
     */
    public StringBuffer getBrandOfUser(User user) throws Exception{
//    	OrgInfo orgInfo = orgInfoManager.getOrgInfoByUser(user);
//    	return goodsModifierManager.getBrandObjIdByOrgInfo(orgInfo.getObjId());
    	return null;
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
	public Page getGoodsModifierBySupplierId(int pageNumber, final int pageSize) throws Exception {
//		Company company = AuthenticationHelper.getCurrentUser(true).getEmp().getCompany();
		
//		OrgInfo orgInfo = orgInfoManager.getOrgInfoByCompanyId(company.getObjId());
		
//		return goodsModifierManager.getGoodsModifier(orgInfo.getObjId(), pageNumber, pageSize);
		return null;
	}
	
	/** 
	 * Description :  指定维护商品品牌列表
	 * Create Date: 2010-8-2下午06:15:04 by sunl  Modified Date: 2010-8-2下午06:15:04 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsModifier> getGoodsBrandBySupplierId(Page<GoodsModifier> page,HttpServletRequest request) throws Exception {
		return goodsModifierDaoHibernate.getGoodsBrandBySupplierId(page,request);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goods.service.GoodsModifierService#getUnSpecifiedGoods(com.gpcsoft.core.utils.Page)
	 */
	public Page<GoodsModifier> getUnSpecifiedGoods(Page<GoodsModifier> page ,String goodsClassName, String goodsBrandName, String order, String order_flag)
			throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		String orgInfoId = "";
		// 如果当前用户是供应商
		if(null != user.getOrgInfo()) {
			// 如果当前用户是供应商且是管理员
			if(roleManager.isHasThisRole(user.getObjId(),OrganizationEnum.ROLE_MANAGER)){
				orgInfoId = "";
			}else{
				orgInfoId = user.getOrgInfo().getObjId();
			}
		}
		return goodsModifierDaoHibernate.getUnSpecifiedGoods(page , goodsClassName, goodsBrandName, order, order_flag, orgInfoId);
	}
	
}
