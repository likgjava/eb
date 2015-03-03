package com.gpcsoft.smallscale.pointmall.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.pointmall.manager.GiftSupplierManager;
import com.gpcsoft.smallscale.pointmall.service.GiftSupplierService;
import com.gpcsoft.smallscale.pointmall.dao.GiftSupplierDao;
import com.gpcsoft.smallscale.pointmall.domain.GiftSupplier;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.UserManager;

@Service 
public class GiftSupplierServiceImpl extends BaseGenericServiceImpl<GiftSupplier> implements GiftSupplierService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("giftSupplierManagerImpl")
	private GiftSupplierManager giftSupplierManager;
	
	@Autowired(required=true) @Qualifier("giftSupplierDaoHibernate")
	private GiftSupplierDao giftSupplierDao;
	
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	@Autowired(required = true)	@Qualifier("userManagerImpl")
	private UserManager userManager;
	
    @Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDaoHibernate;

	/**
	 * Description :  保存礼品供货商
	 * Create Date: 2011-1-12上午11:41:42 by zhaojf  Modified Date: 2011-1-12上午11:41:42 by zhaojf
	 * @param   saveType=newAdd 新增, saveType=modify 修改
	 * @return  
	 * @Exception
	 */
	public void saveGiftSupplier(GiftSupplier giftSupplier,String saveType)
			throws Exception {		
		if(saveType.equals("modify")){//修改
			giftSupplierDao.modifyGiftSupplier(giftSupplier.getObjId(), giftSupplier.getStartTime(), giftSupplier.getEndTime());
		}else{//新增
			
			OrgInfo orgInfo = orgInfoManager.get(giftSupplier.getSupplierId());
			
			//对此供货商联系人赋予" 礼品供货商 "角色
			User userRole = userManager.get(orgInfo.getUser().getObjId());
			
			Role roleOrg = this.getRoleByType(OrganizationEnum.ROLE_GIFT_SUPPLIER);//获得礼品供货商角色
			Set<Role> roles = userRole.getRoles();
			
		    roles.add(roleOrg);
		    userRole.setRoles(roles);
		    
		    userManager.save(userRole);//保存角色
			
			giftSupplier = giftSupplierDao.save(giftSupplier);
		}
	}
	
	/**
	 * Description :  获得礼品供货商角色
	 * Create Date: 2011-1-13上午11:54:34 by zhaojf  Modified Date: 2011-1-13上午11:54:34 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Role getRoleByType(String roleType) {
		return roleDaoHibernate.getRoleByType(roleType);
	}

	/**
	 * Description :  判断礼品供货商是否唯一
	 * Create Date: 2011-1-12下午03:51:37 by zhaojf  Modified Date: 2011-1-12下午03:51:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String giftSupplierName) {
		return giftSupplierDao.isUnique(giftSupplierName);
	}

	/**
	 * Description :  删除
	 * Create Date: 2011-1-12下午04:11:10 by zhaojf  Modified Date: 2011-1-12下午04:11:10 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String removeGiftSupplier(String objId) throws Exception {
		String isGiftSupplierExits = "success";
		
		//判断此供货商是否还包含有礼品
		if(giftSupplierDao.isHasGiftByGiftSupplier(objId)){
			isGiftSupplierExits = "unsuccess";
		}else{
			giftSupplierDao.remove(objId, GiftSupplier.class);
		}
		return isGiftSupplierExits;		
	}

	/**
	 * Description :  禁用或启用
	 * Create Date: 2011-1-12下午05:22:37 by zhaojf  Modified Date: 2011-1-12下午05:22:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void modifyIsUsedStatus(String objId, boolean isUsed)
			throws Exception {
		giftSupplierDao.modifyIsUsedStatus(objId, !isUsed);
	}

	/**
	 * Description :  获取被禁用的礼品供货商Id(多个用逗号分开)
	 * Create Date: 2011-1-20下午04:42:43 by zhaojf  Modified Date: 2011-1-20下午04:42:43 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String getObjIdsOfNotUsed() throws Exception {
		String objIds = "";
		List<GiftSupplier> list = new ArrayList<GiftSupplier>();
		String hql = "from GiftSupplier s where s.isUsed = false";
		list = giftSupplierDao.findbyHql(hql);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				objIds = objIds + list.get(i).getSupplierId() + ",";
			}
			objIds = objIds.substring(0, objIds.length()-1);
		}
		return objIds;
	}

}
