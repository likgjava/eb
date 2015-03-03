package com.gpcsoft.pubservice.mydesktop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.buyer.dao.BuyerDao;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.manager.BuyerManager;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.dao.SupplierDao;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.mydesktop.dao.ModelIndexDao;
import com.gpcsoft.pubservice.mydesktop.service.ModelIndexService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.UserManager;

@Service 
public class ModelIndexServiceImpl extends BaseGenericServiceImpl<GpcBaseObject> implements ModelIndexService {

	@Autowired(required=true)  @Qualifier("userManagerImpl")
	private UserManager userManager;
	
	@Autowired(required=true)  @Qualifier("modelIndexDaoJdbc")
	private ModelIndexDao modelIndexDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("supplierDaoHibernate")
	private SupplierDao supplierDaoHibernate;
	
	@Autowired(required=true) @Qualifier("buyerManagerImpl")
	private BuyerManager buyerManager;
	
	@Autowired(required=true)  @Qualifier("buyerDaoHibernate")
	private BuyerDao buyerDaoHibernate;
	
	/** 
	 * Description :  获得采购人的首页内容
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @return  包含用户信息、业务角色信息、任务信息
	 * @Exception   
	 */
	public Map<String, Object> getBuyerIndex() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		
		//存放用户信息
		User user = AuthenticationHelper.getCurrentUser(true);
		String lastLoginTime = userManager.getLatestLoginTime(user.getObjId());
		model.put("userId", user.getObjId());
		model.put("userName", user.getEmp().getName());
		model.put("lastLoginTime", lastLoginTime.indexOf(":")>=0?lastLoginTime.substring(0, 19):lastLoginTime);
		
		//获取所在部门名称
		if(user.getEmp().getDepartment() != null) {
			model.put("departmentName", user.getEmp().getDepartment().getName());
		}
		
		//存放业务角色信息和任务信息
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		if(orgInfo.getBuyerId() != null) {//采购人
			//获取采购人信息
			model.put("buyer", buyerManager.get(orgInfo.getBuyerId()));
			//获取待办任务
			model.put("task", modelIndexDaoHibernate.getBuyerTask(user.getObjId(), orgInfo.getObjId()));
			//获取项目列表
			model.put("bidList", modelIndexDaoHibernate.getBuyerBids(orgInfo.getObjId(), user.getObjId()));
		}
		model.put("orgInfo", orgInfo);
		
		//最新加盟供应商
		Page<Supplier> pageSupplierNew = new Page<Supplier>(1, 3, 3, new ArrayList<Supplier>());
		param.clear();
    	model.put("newSupplierList", supplierDaoHibernate.getSupplierListForShow(pageSupplierNew, param).getData());
		
		//获取子公司的采购信息
		model.put("subCompnyBuyList", modelIndexDaoHibernate.getSubCompanyBuyInfo(orgInfo.getObjId()));
		
		//获取统计信息(违规记录数，被关注记录数，未读站内信数，发布项目数，发布采购需求数)
		model.put("statisticsInfo", modelIndexDaoHibernate.getStatisticsInfo(orgInfo.getObjId(), user.getObjId()));
		
		model.put("nowDateStr", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.0"));
		
		return model;
	}
	
	/** 
	 * Description :  获得专家的首页内容
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @return  包含用户信息、业务角色信息、任务信息
	 * @Exception   
	 */
	public Map<String, Object> getExpertIndex() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//存放用户信息
		User user = AuthenticationHelper.getCurrentUser(true);
		
		model.put("userId", user.getObjId());
		model.put("userName", user.getEmp().getName());
		model.put("userAvatar", user.getAvatar());
		
		String lastLoginTime = userManager.getLatestLoginTime(user.getObjId());
		model.put("lastLoginTime", lastLoginTime.indexOf(":")>=0?lastLoginTime.substring(0, 19):lastLoginTime);
		
		//存放业务角色信息和任务信息
		model.put("role", "专家");
		
		return model;
	}
	
	/** 
	 * Description :  获得供应商的首页内容
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @return  包含用户信息、业务角色信息、任务信息
	 * @Exception   
	 */
	public Map<String, Object> getSupplierIndex() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		
		//存入机构状态，设置经营范围跳转判断
		model.put("currentOrgStatus", ((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getUseStatus());
		
		//存放用户信息
		User user = AuthenticationHelper.getCurrentUser(true);
		String lastLoginTime = userManager.getLatestLoginTime(user.getObjId());
		model.put("userId", user.getObjId());
		model.put("userName", user.getEmp().getName());
		model.put("lastLoginTime", lastLoginTime.indexOf(":")>=0?lastLoginTime.substring(0, 19):lastLoginTime);
		
		//获取所在部门名称
		if(user.getEmp().getDepartment() != null) {
			model.put("departmentName", user.getEmp().getDepartment().getName());
		}
		
		//存放业务角色信息和任务信息
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		if(orgInfo != null && orgInfo.getSupplierId() != null) {//供应商
			//获取供应商信息
			model.put("supplier", supplierDaoHibernate.get(orgInfo.getSupplierId()));
			//获取待办任务
			model.put("task", modelIndexDaoHibernate.getSupplierTask(user.getObjId(), orgInfo.getObjId()));
			//获取我的参与的项目
			model.put("bidList", modelIndexDaoHibernate.getSupplierBids(orgInfo.getObjId(), user.getObjId()));
		}
		model.put("orgInfo", orgInfo);
		
		//最新加盟采购人
		Page<Buyer> pageBuyerNew = new Page<Buyer>(1, 3, 3, new ArrayList<Buyer>());
		param.clear();
		param.put("validSort", "desc");
    	model.put("newBuyerList", buyerDaoHibernate.getBuyerListForShow(pageBuyerNew, param).getData());
    	
		//获取子公司的采购信息
		model.put("subCompnySaleInfoList", modelIndexDaoHibernate.getSubCompanySaleInfo(orgInfo.getObjId()));
		
		//获取统计信息(违规记录数，被关注记录数，未读站内信数，发布项目数，发布采购需求数)
		model.put("statisticsInfo", modelIndexDaoHibernate.getStatisticsInfo(orgInfo.getObjId(), user.getObjId()));
		
		model.put("nowDateStr", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.0"));
		
		return model;
	}
	
	/** 
	 * Description :  获得代理机构的首页内容
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @return  包含用户信息、业务角色信息、任务信息
	 * @Exception   
	 */
	public Map<String, Object> getAgencyIndex() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//存放用户信息
		User user = AuthenticationHelper.getCurrentUser(true);
		model.put("userId", user.getObjId());
		model.put("userName", user.getEmp().getName());
		model.put("userAvatar", user.getAvatar());
		String lastLoginTime = userManager.getLatestLoginTime(user.getObjId());
		model.put("lastLoginTime", lastLoginTime.indexOf(":")>=0?lastLoginTime.substring(0, 19):lastLoginTime);
		
		//存放业务角色信息和任务信息
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		if(orgInfo.getAgencyId() != null) {//代理机构
			model.put("role", "代理机构");
			model.put("task", modelIndexDaoHibernate.getAgencyTask(user.getObjId(), orgInfo.getObjId()));
			//获取项目列表
			model.put("projectList", modelIndexDaoHibernate.getAgencyProjects(orgInfo.getObjId()));
		}
		
		return model;
	}
	
	/** 
	 * Description :  获得商品库管理的首页
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * return 	待审核品牌数 待审核商品数
	 * @Exception   
	 */
	public Map<String, Object> getGoodsIndex() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//存放用户信息
		User user = AuthenticationHelper.getCurrentUser(true);
		model.put("userId", user.getObjId());
		model.put("userName", user.getEmp().getName());
		model.put("userAvatar", user.getAvatar());
		String lastLoginTime = userManager.getLatestLoginTime(user.getObjId());
		model.put("lastLoginTime", lastLoginTime.indexOf(":")>=0?lastLoginTime.substring(0, 19):lastLoginTime);
		
		//存放业务信息
		model.put("task", modelIndexDaoHibernate.getGoodsTask());
		
		return model;
	}
	
	/** 
	 * Description :  获得机构管理的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getOrgInfoIndex(String orgInfoId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//存放用户信息
		User user = AuthenticationHelper.getCurrentUser(true);
		String lastLoginTime = userManager.getLatestLoginTime(user.getObjId());
		model.put("userId", user.getObjId());
		model.put("userName", user.getEmp().getName());
		model.put("lastLoginTime", lastLoginTime.indexOf(":")>=0?lastLoginTime.substring(0, 19):lastLoginTime);
		
		//获取所在部门名称
		if(user.getEmp().getDepartment() != null) {
			model.put("departmentName", user.getEmp().getDepartment().getName());
		}
		
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		model.put("auditStatus", ((OrgInfo)user.getOrgInfo()).getAuditStatus());
		//存放角色信息
		if(StringUtils.hasLength(orgInfoId)){
			//获取机构信息
			model.put("orgInfo", orgInfo);
			
			//获取机构统计信息(认证资质数，成功案例数，已有联系人数，客户评价次数，客户评价总分，好评次数，被投诉次数，被举报次数，违规记录数)
			model.put("orgStatisticsInfo", modelIndexDaoHibernate.getOrgStatisticsInfo(orgInfo.getObjId()));
			
			//获取指定时间内的机构统计信息(被收藏次数，关注数量，销售金额，采购金额)
			model.put("orgStatisticsInfoByTime", modelIndexDaoHibernate.getOrgStatisticsInfoByTime(orgInfo.getObjId(), 36500));
			
			//获取子公司的机构信息
			model.put("subCompnyStatInfoList", modelIndexDaoHibernate.getSubCompanyStatisticsInfo(orgInfo.getObjId()));
			
		}else{
			model.put("role", "机构管理员");
		}
		
		//存放业务信息
		model.put("task", modelIndexDaoHibernate.getOrgInfoIndex(orgInfoId));
		
		return model;
	}
	
	/** 
	 * Description :  获得基础数据的首页
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * return 	待审核集中采购目录数目
	 * @Exception   
	 */
	public Map<String, Object> getBaseDataIndex() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//存放用户信息
		User user = AuthenticationHelper.getCurrentUser(true);
		model.put("userId", user.getObjId());
		model.put("userName", user.getEmp().getName());
		model.put("userAvatar", user.getAvatar());
		String lastLoginTime = userManager.getLatestLoginTime(user.getObjId());
		model.put("lastLoginTime", lastLoginTime.indexOf(":")>=0?lastLoginTime.substring(0, 19):lastLoginTime);
		
		//存放业务信息
		model.put("task", modelIndexDaoHibernate.getBaseDataIndex());
		
		return model;
	}

	/** 
	 * Description :  获取指定时间段的机构统计信息(被收藏次数，关注数量，销售金额，采购金额)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getOrgStatisticsInfoByTime(String orgInfoId, int days) throws Exception {
		return modelIndexDaoHibernate.getOrgStatisticsInfoByTime(orgInfoId, days);
	}
}
