package com.gpcsoft.agreement.bargin.project.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordDao;
import com.gpcsoft.agreement.bargin.bulletin.manager.BulletinAgreementManager;
import com.gpcsoft.agreement.bargin.project.dao.BargainProjectDao;
import com.gpcsoft.agreement.bargin.project.dao.BargainTurnDao;
import com.gpcsoft.agreement.bargin.project.dao.ProjectContactInfoDao;
import com.gpcsoft.agreement.bargin.project.dao.ProjectPayInfoDao;
import com.gpcsoft.agreement.bargin.project.dao.ProjectSignInfoDao;
import com.gpcsoft.agreement.bargin.project.dao.ProjectTaskItemDao;
import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.agreement.bargin.project.domain.ProjectContactInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectQueryDto;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectTaskItem;
import com.gpcsoft.agreement.bargin.project.domain.RequireTaskItem;
import com.gpcsoft.agreement.bargin.project.manager.RequireTaskItemManager;
import com.gpcsoft.agreement.bargin.project.service.BargainProjectService;
import com.gpcsoft.agreement.bargin.require.dao.RequireItemDao;
import com.gpcsoft.agreement.bargin.require.domain.RequireGoodsAccessories;
import com.gpcsoft.agreement.bargin.require.domain.RequireGoodsGift;
import com.gpcsoft.agreement.bargin.require.domain.RequireGoodsOpt;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.agreement.bargin.requirement.dao.RequirementDao;
import com.gpcsoft.agreement.bargin.requirement.dao.RequirementRegDao;
import com.gpcsoft.agreement.bargin.requirement.domain.RequirementReg;
import com.gpcsoft.agreement.bargin.signup.dao.SignUprecordDaoXygh;
import com.gpcsoft.agreement.bargin.signup.service.SignUprecordServiceXygh;
import com.gpcsoft.agreement.cart.dao.ShoppingCartDao;
import com.gpcsoft.agreement.cart.dao.ShoppingCartItemDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsAccessories;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsGift;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsOption;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.dao.ProjectExceptionApplyDao;
import com.gpcsoft.epp.project.domain.ProjImplStatusEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;
import com.gpcsoft.epp.project.service.impl.ProjectServiceImpl;
import com.gpcsoft.epp.projrule.dao.ProjProcessRuleDao;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.concern.service.ConcernService;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.manager.ServiceSubscribeManager;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.pubservice.utils.MobileMessageUtil;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;
import com.gpcsoft.srplatform.auth.dao.EmployeeDao;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class BargainProjectServiceImpl extends ProjectServiceImpl implements BargainProjectService {
	
	@Autowired(required=true) @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true) @Qualifier("bargainTurnDaoHibernate")
	private BargainTurnDao bargainTurnDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projectTaskItemDaoHibernate")
	private ProjectTaskItemDao projectTaskItemDaoHibernate;
	
	@Autowired(required=true) @Qualifier("requireItemDaoHibernate")
	private RequireItemDao requireItemDaoHibernate;
	
	@Autowired(required=true) @Qualifier("shoppingCartItemDaoHibernate")
	private ShoppingCartItemDao shoppingCartItemDaoHibernate;
	
	@Autowired(required=true) @Qualifier("shoppingCartDaoHibernate")
	private ShoppingCartDao shoppingCartDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projProcessRuleDaoHibernate")
	private ProjProcessRuleDao projProcessRuleDaoHibernate;
	
	@Autowired(required=true) @Qualifier("bargainProjectDaoHibernate")
	private BargainProjectDao bargainProjectDao;
	
	@Autowired(required=true) @Qualifier("biddingRecordDaoHibernate")
	private BiddingRecordDao biddingRecordDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("projectSignInfoDaoHibernate")
	private ProjectSignInfoDao projectSignInfoDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("projectPayInfoDaoHibernate")
	private ProjectPayInfoDao projectPayInfoDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("projectContactInfoDaoHibernate")
	private ProjectContactInfoDao projectContactInfoDaoHibernate;
	
	@Autowired(required=true) @Qualifier("requireTaskItemManagerImpl")
	private RequireTaskItemManager requireTaskItemManagerImpl;
	
	@Autowired(required=true) @Qualifier("requirementDaoHibernate")
	private RequirementDao requirementDao;
	
	@Autowired(required=true) @Qualifier("requirementRegDaoHibernate")
	private RequirementRegDao requirementRegDao;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceXyghImpl")
	private SignUprecordServiceXygh signUprecordServiceXygh;
	
	@Autowired(required=true) @Qualifier("bulletinAgreementManagerImpl")
	private BulletinAgreementManager bulletinAgreementManagerImpl;
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("employeeDaoHibernate")
	private EmployeeDao employeeDaoHibernate;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("concernServiceImpl")
	private ConcernService concernService;
	
	@Autowired(required=true) @Qualifier("serviceSubscribeManagerImpl")
	private ServiceSubscribeManager serviceSubscribeManagerImpl;
	
	@Autowired(required=true) @Qualifier("signUprecordDaoXyghHibernate")
	private SignUprecordDaoXygh signUprecordDaoXyghHibernate;
	
	@Autowired(required=true)  @Qualifier("projectExceptionApplyDaoHibernate")
	private ProjectExceptionApplyDao projectExceptionApplyDaoHibernate;
	
	/** 
	 * Description :  创建竞价项目，同步创项目与任务书关联表，需求条目表
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project createProjectNoGoods(Project project,Map<String, Object> paramsMap) throws Exception {
		String task = (String)paramsMap.get("task");
		String require = (String)paramsMap.get("require");
		User user = AuthenticationHelper.getCurrentUser(true);
		
		if(!StringUtils.hasLength(project.getObjId())) {
			project.setProjCode(SequenceNumberUtil.getProjectSN());//项目编号
			if(!StringUtils.hasLength(project.getProjProcessStatus())) {
				project.setProjProcessStatus(ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE);//设定项目规则
				project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE));
			}
			project.setProjImplStatus(ProjImplStatusEnum.NORMAL);//实施状态-正常
			if(!StringUtils.hasLength(project.getAuditStatus()))
			{
				project.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//审核状态-待审核
			}
			if(!StringUtils.hasLength(project.getUseStatus()))
			{
				project.setUseStatus(CommonEnum.USER_STATUS_TEMP);//使用状态-临时
			}
			project.setEbuyMethod((String)paramsMap.get("ebuyMethod"));//采购方式
			project.setManager(user.getEmp());//项目负责人
			project.setMonitor(user.getEmp());//项目监管人
			project.setBuyersId(user.getOrgInfo().getObjId());
			project.setBuyersName(((OrgInfo)user.getOrgInfo()).getOrgName());
		}
		
		project = projectDaoHibernate.save(project);
		
		List<ProjectTaskItem> taskList = new ArrayList<ProjectTaskItem>();
		if(StringUtils.hasLength(task)) {
			//项目任务书条目关联表
			JSONArray taskArray = JSONArray.fromObject(task);
			ProjectTaskItem proTaskItem = new ProjectTaskItem();
			for(Object obj : taskArray) {
				proTaskItem = (ProjectTaskItem)JsonUtils.json2Bean(((JSONObject)obj).toString(), ProjectTaskItem.class);
				proTaskItem.setProject(project);//项目
				proTaskItem.setBuyMainBody((OrgInfo)user.getOrgInfo());//采购主体
				taskList.add(proTaskItem);
			}
			if(taskList.size() > 0) {
				projectTaskItemDaoHibernate.save(taskList);
			}
		}
		
		//同步品目
		String categoryids = "";
		String categorynames = "";
		//需求条目
		if(StringUtils.hasLength(require)) {
			JSONArray requireArray = JSONArray.fromObject(require);
			Set<RequireItem> requireSet = new HashSet<RequireItem>();
			List<RequireTaskItem> requireTaskList = new ArrayList<RequireTaskItem>();
			RequireItem requireItem = new RequireItem();
			String tiid = "";
			for(Object obj : requireArray) {
				requireItem = (RequireItem)JsonUtils.json2Bean(((JSONObject)obj).toString(), RequireItem.class);
				if(StringUtils.hasLength(requireItem.getObjId()) && requireItem.getObjId().indexOf("_tiid")==-1){
					RequireItem oldRequireItem = requireItemDaoHibernate.get(requireItem.getObjId());
					
					//避免集合拷贝时将原来的集合拷贝为空
					requireItem.setRequireGoodsGifts(null);
					requireItem.setRequireGoodsAccess(null);
					requireItem.setRequireGoodsOpt(null);
					BeanUtils.copyPropertiesFilterEmpty(oldRequireItem, requireItem);
					requireItem = oldRequireItem;
				}
				
				//带有任务书条目需求条目创建时为了记录任务书条目ID故意在objId后加"_tiid"字符串,所以requireItem.setObjId(null);
				if(StringUtils.hasLength(requireItem.getObjId()) && requireItem.getObjId().indexOf("_tiid")!=-1) {
					tiid = requireItem.getObjId().replaceAll("_tiid", "");//任务书条目ID
					requireItem.setObjId(null);
					
					//需求条目和任务书关联表
					RequireTaskItem requireTask = new RequireTaskItem();
					requireTask.setRequireItem(requireItem);
					requireTask.setCreateUser(user);
					requireTask.setCreateTime(new Date());
					for (ProjectTaskItem projecttaskItem : taskList) {
						if(tiid.equals(projecttaskItem.getProtaskItem().getObjId())) {
							requireTask.setProtaskItem(projecttaskItem.getProtaskItem());
							requireTaskList.add(requireTask);
						}
					}
				}
				if(requireItem.getGoods()==null || !StringUtils.hasLength(requireItem.getGoods().getObjId())) {
					requireItem.setGoods(null);
				}
				requireItem.setProject(project);
				requireSet.add(requireItem);
			}
			requireItemDaoHibernate.save(requireSet);//保存需求条目
			
			requireTaskItemManagerImpl.save(requireTaskList);//保存需求和任务书关联表
			
			//同步品目
			for (RequireItem object : requireSet) {
				categoryids += object.getPurCategory().getObjId()+",";
				categorynames += object.getPurCategory().getCategoryName()+",";
			}
			if(StringUtils.hasLength(categoryids)) {
				categoryids = categoryids.substring(0,categoryids.length()-1);
				categorynames = categorynames.substring(0,categorynames.length()-1);
			}
			project.setPurCategoryIds(categoryids);
			project.setPurCategoryNames(categorynames);
		}
		
		//如果是根据采购需求创建的项目,则将项目id更新至采购需求中
		if(StringUtils.hasLength((String)paramsMap.get("requirementIds"))){
			Map<String, Object>  map = new HashMap<String , Object >();
			map.put("projectId", project.getObjId());
			requirementDao.updateStatus((String)paramsMap.get("requirementIds"), map );
			
			map.put("requirement.objId", (String)paramsMap.get("requirementIds"));
			List<RequirementReg> requirementRegList = requirementRegDao.getRequirementRegList(map);
			//将需求中已审核通过的报名供应商默认报名至该项目
			signUprecordServiceXygh.saveSignUprecordByRequiremntReg(project, requirementRegList);
		}
		return project;
	}
	
	/** 
	 * Description :  创建竞价项目（有商品），同步创项目与任务书关联表，需求条目表
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project createProjectGoods(Project project,Map<String,Object> paramMap) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		project.setProjCode(SequenceNumberUtil.getProjectSN()); //项目编号
		if(!StringUtils.hasLength(project.getProjProcessStatus())) {
			project.setProjProcessStatus(ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE);//设定项目规则
			project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE));
		}
		project.setProjImplStatus(ProjImplStatusEnum.NORMAL);//实施状态-正常
		project.setTenderRecord("00"); //设置备案状态为：未备案
		if(!StringUtils.hasLength(project.getAuditStatus())) {
			project.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//审核状态-待审核
		}
		if(!StringUtils.hasLength(project.getUseStatus())) {
			project.setUseStatus(CommonEnum.USER_STATUS_TEMP);//使用状态-临时
		}
		project.setEbuyMethod((String)paramMap.get("ebuyMethod"));//采购方式
		project.setManager(user.getEmp());//项目负责人
		project.setMonitor(user.getEmp());//项目监管人
		project.setBuyersId(user.getOrgInfo().getObjId());
		project.setBuyersName(((OrgInfo)user.getOrgInfo()).getOrgName());
		project = projectDaoHibernate.save(project);
		
		Map<String, Object>  map = new HashMap<String , Object >();
		if(StringUtils.hasLength((String)paramMap.get("requirementIds"))) {
			map.put("requirement.objId", (String)paramMap.get("requirementIds"));
			List<RequirementReg> requirementRegList = requirementRegDao.getRequirementRegList(map);
			//将需求中已审核通过的报名供应商默认报名至该项目
			signUprecordServiceXygh.saveSignUprecordByRequiremntReg(project, requirementRegList);
			//如果是根据采购需求创建的项目,则将项目id更新至采购需求中 by yucy
			map.put("projectId", project.getObjId());
			requirementDao.updateStatus((String)paramMap.get("requirementIds"), map );
		}

		//项目任务书条目关联表
		if(StringUtils.hasLength(paramMap.get("task").toString())) {
			JSONArray taskArray = JSONArray.fromObject(paramMap.get("task").toString());
			List<ProjectTaskItem> taskList = new ArrayList<ProjectTaskItem>();
			ProjectTaskItem proTaskItem = new ProjectTaskItem();
			for(Object obj : taskArray) {
				proTaskItem = (ProjectTaskItem)JsonUtils.json2Bean(((JSONObject)obj).toString(), ProjectTaskItem.class);
				proTaskItem.setProject(project);//项目
				proTaskItem.setBuyMainBody((OrgInfo)user.getOrgInfo());//采购主体
				taskList.add(proTaskItem);
			}
			projectTaskItemDaoHibernate.save(taskList);
		}
		
		List<RequireItem> requireList = new ArrayList<RequireItem>();
		
		//拷贝购物车条目到需求条目
		String cartId = "";
		String cartItemId = "";
		String cartItemIds = "";
		String [] cartItemArray = paramMap.get("cartItemIds").toString().split(",");
		ShoppingCartItem cartItem = new ShoppingCartItem();
		//同步商品分类和品目
		String categoryids = "";
		String categorynames = "";
		if(cartItemArray.length >= 1 && !"".equals(cartItemArray[0])) {
			for (int i=0; i<cartItemArray.length; i++) {
				RequireItem requireItem = new RequireItem();
				String temp = cartItemArray[i];
				//取购物车条目id by yucy
				cartItemId = temp.split("_")[0];
				cartItemIds += cartItemIds.equals("")?cartItemId:","+cartItemId;
				
				cartItem = shoppingCartItemDaoHibernate.get(cartItemId);
				if(i == 0){
					cartId = cartItem.getShoppingCart().getObjId();
				} 
				//i++;
				BeanUtils.copyPropertiesFilterEmptyNew(requireItem,cartItem);
				requireItem.setObjId(null);
				requireItem.setProject(project);
				
				// 显示的获取商品分类和品目
				GoodsClass goodsClass = new GoodsClass();
				goodsClass.setObjId(cartItem.getGoods().getGoodsClass().getObjId());
				goodsClass.setGoodsClassName(cartItem.getGoods().getGoodsClass().getGoodsClassName());
				PurCategory category = new PurCategory();
				category.setObjId(cartItem.getGoods().getPurCategory().getObjId());
				category.setCategoryName(cartItem.getGoods().getPurCategory().getCategoryName());
				//商品分类和品目
				requireItem.setGoodsClass(goodsClass);
				requireItem.setPurCategory(category);
				
				//重新设置页面提交值
				requireItem.setGoodsPrice(new BigDecimal(temp.split("_")[1]));
				requireItem.setGoodsQty(new BigDecimal(temp.split("_")[2]));
				Double total  = requireItem.getGoodsPrice().doubleValue() * requireItem.getGoodsQty().doubleValue();
				requireItem.setGoodsTotal(BigDecimal.valueOf(total));
				requireItem.setGoodsUnit(com.gpcsoft.bizplatform.base.common.util.StringUtils.ascii2Native(temp.split("_")[3]));
				requireItem.setRequireGoodsOpt(new HashSet<RequireGoodsOpt>());
				
				//处理商品选配
				for (ShoppingCartGoodsOption soptin : cartItem.getCartGoodsOptions()) {
					RequireGoodsOpt goodsOpt = new RequireGoodsOpt();
					BeanUtils.copyPropertiesFilterEmptyNew(goodsOpt,soptin);
					goodsOpt.setObjId(null);
					goodsOpt.setOptionalFitting(soptin.getOption());//代理对象无法拷贝此处显示设置一下
					goodsOpt.setRequireItem(requireItem);
					requireItem.getRequireGoodsOpt().add(goodsOpt);
				}
				//处理商品零配件
				for(ShoppingCartGoodsAccessories cartGoodsAccess : cartItem.getCartGoodsAccessories()){
					RequireGoodsAccessories requireGoodsAccess = new RequireGoodsAccessories();
					BeanUtils.copyPropertiesFilterEmptyNew(requireGoodsAccess, cartGoodsAccess);
					requireGoodsAccess.setObjId(null);
					requireGoodsAccess.setGoodsAccess(requireGoodsAccess.getGoodsAccess());//代理对象无法拷贝此处显示设置一下
					requireGoodsAccess.setRequireItem(requireItem);
					requireItem.getRequireGoodsAccess().add(requireGoodsAccess);
				}
				
				requireList.add(requireItem);
			}
		}
		
		//非购物车商品的需求条目
		JSONArray requireArray = JSONArray.fromObject(paramMap.get("require").toString());
		for(Object obj : requireArray) {
			RequireItem rItem = new RequireItem();
			rItem = (RequireItem)JsonUtils.json2Bean(((JSONObject)obj).toString(), RequireItem.class);
			if(rItem.getGoods() != null) {
				rItem.setProject(project);
				requireList.add(rItem);;
			}
		}
		requireItemDaoHibernate.save(requireList);
		
		//同步商品品目
		for (RequireItem object : requireList) {
			if(object.getPurCategory()!=null){
				categoryids += object.getPurCategory().getObjId()+",";
				categorynames += object.getPurCategory().getCategoryName()+",";
			}
		}
		if(StringUtils.hasLength(categoryids)) {
			categoryids = categoryids.substring(0,categoryids.length()-1);
			categorynames = categorynames.substring(0,categorynames.length()-1);
		}
		project.setPurCategoryIds(categoryids);
		project.setPurCategoryNames(categorynames);
		
		//删除购物车条目和相关商品接口
		if(cartItemArray.length >= 1 && !"".equals(cartItemArray[0])) {
			shoppingCartItemDaoHibernate.remove(cartItemIds.split(","), ShoppingCartItem.class);
			shoppingCartDaoHibernate.updateSortChatAndItemQytAndMoney(cartId);
		}
		return project;
	}
	
	/** 
	 * Description :  删除竞价或反拍项目（项目任务书关联表,删除需求条目,轮次,报价,报价条目,项目规则）
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeRBProject(String projId) throws Exception {
		//删除ProjectMTaskPlan，ProjectPlan，Project
		projectDaoHibernate.remove(projId, Project.class);
		//删除需求条目
		requireItemDaoHibernate.removeRequireItemByProjId(projId);
		//删除轮次
		bargainTurnDaoHibernate.removeBargainTurnByProjId(projId);
		//删除项目规则
		projProcessRuleDaoHibernate.removeProjProcessRuleByProjectId(projId);
		//删除报价
		biddingRecordDaoHibernate.removeBiddingRecordByProjId(projId);
		
		/***********删除扩展信息-报名信息-支付信息-联系信息*************/
		projectSignInfoDaoHibernate.removeSignInfoByProjId(projId);
		projectPayInfoDaoHibernate.removePayInfoByProjId(projId);
		projectContactInfoDaoHibernate.removeContactInfoByProjId(projId);
	}
	
	/** 
	 * Description :  修改议价项目
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project updateTalkProject(Project project, Map<String, Object> paramsMap) throws Exception {
		String task = (String) paramsMap.get("task"); //任务书条目
		String require = (String) paramsMap.get("require"); //需求条目
		
		//设置项目信息
		User user = AuthenticationHelper.getCurrentUser(true);
		if(project.getUseStatus().equals(CommonEnum.USER_STATUS_FORMAL)){
			project.setProjProcessStatus(ProjProcessStatusEnum.SUPPLIERS_BID); //供应商投标
			project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.SUPPLIERS_BID));
		}
		project = projectDaoHibernate.save(project);
		
		List<ProjectTaskItem> taskList = new ArrayList<ProjectTaskItem>();
		if(StringUtils.hasLength(task)) {
			//项目任务书条目关联表
			JSONArray taskArray = JSONArray.fromObject(task);
			ProjectTaskItem proTaskItem = new ProjectTaskItem();
			for(Object obj : taskArray) {
				proTaskItem = (ProjectTaskItem)JsonUtils.json2Bean(((JSONObject)obj).toString(), ProjectTaskItem.class);
				proTaskItem.setProject(project);//项目
				proTaskItem.setBuyMainBody((OrgInfo)user.getOrgInfo());//采购主体
				taskList.add(proTaskItem);
			}
			if(taskList.size() > 0) {
				projectTaskItemDaoHibernate.save(taskList);
			}
		}
		
		//同步商品分类和品目
		String classids = "";
		String classnames = "";
		String categoryids = "";
		String categorynames = "";
		//需求条目
		if(StringUtils.hasLength(require)) {
			JSONArray requireArray = JSONArray.fromObject(require);
			Set<RequireItem> requireSet = new HashSet<RequireItem>();
			List<RequireTaskItem> requireTaskList = new ArrayList<RequireTaskItem>();
			RequireItem requireItem = new RequireItem();
			String tiid = "";
			for(Object obj : requireArray) {
				requireItem = (RequireItem)JsonUtils.json2Bean(((JSONObject)obj).toString(), RequireItem.class);
				if(StringUtils.hasLength(requireItem.getObjId()) && requireItem.getObjId().indexOf("_tiid")==-1){
					RequireItem oldRequireItem = requireItemDaoHibernate.get(requireItem.getObjId());
					
					//避免集合拷贝时将原来的集合拷贝为空
					requireItem.setRequireGoodsGifts(null);
					requireItem.setRequireGoodsAccess(null);
					requireItem.setRequireGoodsOpt(null);
					BeanUtils.copyPropertiesFilterEmpty(oldRequireItem, requireItem);
					requireItem = oldRequireItem;
				}
				
				//带有任务书条目需求条目创建时为了记录任务书条目ID故意在objId后加"_tiid"字符串,所以requireItem.setObjId(null);
				if(StringUtils.hasLength(requireItem.getObjId()) && requireItem.getObjId().indexOf("_tiid")!=-1) {
					tiid = requireItem.getObjId().replaceAll("_tiid", "");//任务书条目ID
					requireItem.setObjId(null);
					
					//需求条目和任务书关联表
					RequireTaskItem requireTask = new RequireTaskItem();
					requireTask.setRequireItem(requireItem);
					requireTask.setCreateUser(user);
					requireTask.setCreateTime(new Date());
					for (ProjectTaskItem projecttaskItem : taskList) {
						if(tiid.equals(projecttaskItem.getProtaskItem().getObjId())) {
							requireTask.setProtaskItem(projecttaskItem.getProtaskItem());
							requireTaskList.add(requireTask);
						}
					}
				}
				if(requireItem.getGoods()==null || !StringUtils.hasLength(requireItem.getGoods().getObjId())) {
					requireItem.setGoods(null);
				}
				requireItem.setProject(project);
				requireSet.add(requireItem);
			}
			requireItemDaoHibernate.save(requireSet);//保存需求条目
			
			requireTaskItemManagerImpl.save(requireTaskList);//保存需求和任务书关联表
			
			//同步商品分类和品目
			for (RequireItem object : requireSet) {
				classids += object.getGoodsClass().getObjId()+",";
				classnames += object.getGoodsClass().getGoodsClassName()+",";
				categoryids += object.getPurCategory().getObjId()+",";
				categorynames += object.getPurCategory().getCategoryName()+",";
			}
			if(StringUtils.hasLength(classids)) {
				classids = classids.substring(0,classids.length()-1);
				classnames = classnames.substring(0,classnames.length()-1);
				categoryids = categoryids.substring(0,categoryids.length()-1);
				categorynames = categorynames.substring(0,categorynames.length()-1);
			}
			project.setGoodsClassIds(classids);
			project.setGoodsClassNames(classnames);
			project.setPurCategoryIds(categoryids);
			project.setPurCategoryNames(categorynames);
		}
		
		//如果是根据采购需求创建的项目,则将项目id更新至采购需求中
		if(StringUtils.hasLength((String)paramsMap.get("requirementIds"))){
			Map<String, Object>  map = new HashMap<String , Object >();
			map.put("projectId", project.getObjId());
			requirementDao.updateStatus((String)paramsMap.get("requirementIds"), map );
			
			map.put("requirement.objId", (String)paramsMap.get("requirementIds"));
			List<RequirementReg> requirementRegList = requirementRegDao.getRequirementRegList(map);
			//将需求中已审核通过的报名供应商默认报名至该项目
			signUprecordServiceXygh.saveSignUprecordByRequiremntReg(project, requirementRegList);
		}
		return project;
	}
	
	/** 
	 * Description :  保存议价项目
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project saveTalkProject(Project project, Map<String,Object> paramMap) throws Exception {
		String supplierIds = (String) paramMap.get("supplierIds"); //报价供应商id
		String requirementIds = (String) paramMap.get("requirementIds"); //采购需求Id by yucy
		
		//设置项目信息
		User user = AuthenticationHelper.getCurrentUser(true);
		project.setProjCode(SequenceNumberUtil.getProjectSN()); //设置项目编号
		project.setEbuyMethod(EbuyMethodEnum.TALK); //采购方式
		project.setProjImplStatus(ProjImplStatusEnum.NORMAL); //实施状态-正常
		project.setManager(user.getEmp()); //项目负责人
		project.setMonitor(user.getEmp()); //项目监管人
		project.setBuyersId(user.getOrgInfo().getObjId()); //项目采购人id
		project.setBuyersName(((OrgInfo)user.getOrgInfo()).getOrgName()); //采购人的机构名称
		if(project.getUseStatus().equals(CommonEnum.USER_STATUS_FORMAL)) {
			project.setProjProcessStatus(ProjProcessStatusEnum.SUPPLIERS_BID); //供应商投标
			project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.SUPPLIERS_BID));
		} else {
			project.setProjProcessStatus(ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE);//设定项目规则
			project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE));
		}
		project = projectDaoHibernate.save(project);
		
		//保存供应商报名信息
		if(StringUtils.hasLength(requirementIds)) {
			Map<String, Object>  map = new HashMap<String , Object >();
			map.put("requirement.objId", requirementIds);
			List<RequirementReg> requirementRegList = requirementRegDao.getRequirementRegList(map);
			//将需求中已审核通过的报名供应商默认报名至该项目
			signUprecordServiceXygh.saveSignUprecordByRequiremntReg(project, requirementRegList);
			
			//如果是根据采购需求创建的项目,则将项目id更新至采购需求中 by yucy
			map.put("projectId", project.getObjId());
			requirementDao.updateStatus(requirementIds, map);
		} else if(StringUtils.hasLength(supplierIds)) {
			signUprecordDaoXyghHibernate.saveSignUprecordBatch(project.getObjId(), supplierIds);
		}
		
		//项目任务书条目关联表
		if(StringUtils.hasLength(paramMap.get("task").toString())) {
			JSONArray taskArray = JSONArray.fromObject(paramMap.get("task").toString());
			List<ProjectTaskItem> taskList = new ArrayList<ProjectTaskItem>();
			ProjectTaskItem proTaskItem = new ProjectTaskItem();
			for(Object obj : taskArray) {
				proTaskItem = (ProjectTaskItem)JsonUtils.json2Bean(((JSONObject)obj).toString(), ProjectTaskItem.class);
				proTaskItem.setProject(project);//项目
				proTaskItem.setBuyMainBody((OrgInfo)user.getOrgInfo());//采购主体
				taskList.add(proTaskItem);
			}
			projectTaskItemDaoHibernate.save(taskList);
		}
		
		List<RequireItem> requireList = new ArrayList<RequireItem>();
		
		//拷贝购物车条目到需求条目
		String cartId = "";
		String cartItemId = "";
		String cartItemIds = "";
		String [] cartItemArray = paramMap.get("cartItemIds").toString().split(",");
		ShoppingCartItem cartItem = new ShoppingCartItem();
		//同步商品分类和品目
		String classids = "";
		String categoryids = "";
		String classnames = "";
		String categorynames = "";
		if(cartItemArray.length >= 1 && !"".equals(cartItemArray[0])) {
			for (int i=0; i<cartItemArray.length; i++) {
				RequireItem requireItem = new RequireItem();
				String temp = cartItemArray[i];
				//取购物车条目id by yucy
				cartItemId = temp.split("_")[0];
				cartItemIds += cartItemIds.equals("")?cartItemId:","+cartItemId;
				
				cartItem = shoppingCartItemDaoHibernate.get(cartItemId);
				if(i == 0){
					cartId = cartItem.getShoppingCart().getObjId();
				} 
				//i++;
				BeanUtils.copyPropertiesFilterEmptyNew(requireItem,cartItem);
				requireItem.setObjId(null);
				requireItem.setProject(project);
				
				// 显示的获取商品分类和品目
				GoodsClass goodsClass = new GoodsClass();
				goodsClass.setObjId(cartItem.getGoods().getGoodsClass().getObjId());
				goodsClass.setGoodsClassName(cartItem.getGoods().getGoodsClass().getGoodsClassName());
				PurCategory category = new PurCategory();
				category.setObjId(cartItem.getGoods().getPurCategory().getObjId());
				category.setCategoryName(cartItem.getGoods().getPurCategory().getCategoryName());
				//商品分类和品目
				requireItem.setGoodsClass(goodsClass);
				requireItem.setPurCategory(category);
				
				//重新设置页面提交值
				requireItem.setGoodsPrice(new BigDecimal(temp.split("_")[1]));
				requireItem.setGoodsQty(new BigDecimal(temp.split("_")[2]));
				Double total  = requireItem.getGoodsPrice().doubleValue() * requireItem.getGoodsQty().doubleValue();
				requireItem.setGoodsTotal(BigDecimal.valueOf(total));
				requireItem.setGoodsUnit(com.gpcsoft.bizplatform.base.common.util.StringUtils.ascii2Native(temp.split("_")[3]));
				requireItem.setRequireGoodsOpt(new HashSet<RequireGoodsOpt>());
				
				//处理商品选配
				for (ShoppingCartGoodsOption soptin : cartItem.getCartGoodsOptions()) {
					RequireGoodsOpt goodsOpt = new RequireGoodsOpt();
					BeanUtils.copyPropertiesFilterEmptyNew(goodsOpt,soptin);
					goodsOpt.setObjId(null);
					goodsOpt.setOptionalFitting(soptin.getOption());//代理对象无法拷贝此处显示设置一下
					goodsOpt.setRequireItem(requireItem);
					requireItem.getRequireGoodsOpt().add(goodsOpt);
				}
				//处理商品礼包
				for(ShoppingCartGoodsGift cartGoodsGift : cartItem.getCartGoodsGifts()){
					RequireGoodsGift requireGoodsGift = new RequireGoodsGift();
					BeanUtils.copyPropertiesFilterEmptyNew(requireGoodsGift, cartGoodsGift);
					requireGoodsGift.setObjId(null);
					requireGoodsGift.setGoodsGift(cartGoodsGift.getGoodsGift());//代理对象无法拷贝此处显示设置一下
					requireGoodsGift.setRequireItem(requireItem);
					requireItem.getRequireGoodsGifts().add(requireGoodsGift);
				}
				//处理商品零配件
				for(ShoppingCartGoodsAccessories cartGoodsAccess : cartItem.getCartGoodsAccessories()){
					RequireGoodsAccessories requireGoodsAccess = new RequireGoodsAccessories();
					BeanUtils.copyPropertiesFilterEmptyNew(requireGoodsAccess, cartGoodsAccess);
					requireGoodsAccess.setObjId(null);
					requireGoodsAccess.setGoodsAccess(requireGoodsAccess.getGoodsAccess());//代理对象无法拷贝此处显示设置一下
					requireGoodsAccess.setRequireItem(requireItem);
					requireItem.getRequireGoodsAccess().add(requireGoodsAccess);
				}
				
				requireList.add(requireItem);
			}
		}
		
		//非购物车商品的需求条目
		JSONArray requireArray = JSONArray.fromObject(paramMap.get("require").toString());
		for(Object obj : requireArray) {
			RequireItem rItem = new RequireItem();
			rItem = (RequireItem)JsonUtils.json2Bean(((JSONObject)obj).toString(), RequireItem.class);
			if(rItem.getGoods() != null) {
				rItem.setProject(project);
				requireList.add(rItem);;
			}
		}
		requireItemDaoHibernate.save(requireList);
		
		//同步商品分类和品目
		for (RequireItem object : requireList) {
			if(object.getGoodsClass()!=null){
				classids += object.getGoodsClass().getObjId()+",";
				classnames += object.getGoodsClass().getGoodsClassName()+",";
			}
			if(object.getPurCategory()!=null){
				categoryids += object.getPurCategory().getObjId()+",";
				categorynames += object.getPurCategory().getCategoryName()+",";
			}
		}
		if(StringUtils.hasLength(classids)) {
			classids = classids.substring(0,classids.length()-1);
			categoryids = categoryids.substring(0,categoryids.length()-1);
			classnames = classnames.substring(0,classnames.length()-1);
			categorynames = categorynames.substring(0,categorynames.length()-1);
		}
		project.setGoodsClassIds(classids);
		project.setPurCategoryIds(categoryids);
		project.setGoodsClassNames(classnames);
		project.setPurCategoryNames(categorynames);
		
		//删除购物车条目和相关商品接口
		if(cartItemArray.length >= 1 && !"".equals(cartItemArray[0])) {
			shoppingCartItemDaoHibernate.remove(cartItemIds.split(","), ShoppingCartItem.class);
			shoppingCartDaoHibernate.updateSortChatAndItemQytAndMoney(cartId);
		}
		return project;
	}
	
	/** 
	 * Description :  创建轮次和项目规则信息,同步项目状态
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void createTurnAndRule(Project project,Map<String,Object> paramMap) throws Exception {
		//创建轮次
		JSONArray turnArray = JSONArray.fromObject(paramMap.get("turn").toString());
		List<BargainTurn> turnList = new ArrayList<BargainTurn>();
		BargainTurn bargainTurn = new BargainTurn();
		for(Object obj : turnArray) {
			bargainTurn = (BargainTurn)JsonUtils.json2Bean(((JSONObject)obj).toString(), BargainTurn.class);
			if(!StringUtils.hasLength(bargainTurn.getObjId())) {
				bargainTurn.setObjId(null);
			}
			turnList.add(bargainTurn);
		}
		
		//保存轮次
		bargainTurnDaoHibernate.save(turnList);
		
		//创建项目规则
		JSONArray ruleArray = JSONArray.fromObject(paramMap.get("rule").toString());
		List<ProjProcessRule> ruleList = new ArrayList<ProjProcessRule>();
		ProjProcessRule projRule = new ProjProcessRule();
		for(Object obj : ruleArray) {
			projRule = (ProjProcessRule)JsonUtils.json2Bean(((JSONObject)obj).toString(), ProjProcessRule.class);
			if(projRule.getObjId() != null && "".equals(projRule.getObjId())) {
				projRule.setObjId(null);
			}
			ruleList.add(projRule);
		}
		projProcessRuleDaoHibernate.save(ruleList);
	}
	
	/** 
	 * Description :  创建报名信息、付款方式、联系方式等信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void createSignPayLinkerInfo(Map<String,Object> paramMap) throws Exception {
		//供应商资质信息
		ProjectSignInfo projectSignInfo = (ProjectSignInfo)paramMap.get("projectSignInfo");
		projectSignInfoDaoHibernate.save(projectSignInfo);

		//支付方式信息
		ProjectPayInfo projectPayInfo = (ProjectPayInfo)paramMap.get("projectPayInfo");
		projectPayInfoDaoHibernate.save(projectPayInfo);
		
		//联系人信息
		
		ProjectContactInfo projectContactInfo = (ProjectContactInfo)paramMap.get("projectContactInfo");
		projectContactInfoDaoHibernate.save(projectContactInfo);

			List<Employee> emp = employeeDaoHibernate.findbyHql("from Employee e where e.name=?", new Object[]{projectContactInfo.getLinker()});
			
			//保存
			if(!projectContactInfo.getLinker().equals(AuthenticationHelper.getCurrentUser(true).getEmp().getName())) {
				Employee employee = new Employee();
				
				if(emp != null && emp.size() > 0) {
					employee = emp.get(0);
				}
				employee.setAddress(projectContactInfo.getAddress());
				employee.setCompany(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany());
				employee.setCreatedDate(new Date());
				employee.setCreateTime(new Date());
				employee.setDepartment(AuthenticationHelper.getCurrentUser(true).getEmp().getDepartment());
				employee.setMobile(projectContactInfo.getMobilePhone());
				employee.setName(projectContactInfo.getLinker());
				employee.setZipCode(projectContactInfo.getPostCode());
				employee.setShortSpellName(new WordToSpell().String2Alpha(projectContactInfo.getLinker()));
				employeeDaoHibernate.save(employee);
			}
		//}
		
		//发布项目时创建公告
		if(StringUtils.hasLength((String)paramMap.get("status")) && CommonEnum.USER_STATUS_FORMAL.equals((String)paramMap.get("status"))) {
			Project project = projectDaoHibernate.get((String)paramMap.get("projId"));
			//更新项目状态
			project.setAuditStatus(CommonEnum.USER_STATUS_FORMAL);//审核状态
			project.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//提交状态
			project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.SUPPLIERS_BID)); //修改项目过程中文状态
			projectDaoHibernate.save(project);
			//提交后，发布项目公告
			bulletinAgreementManagerImpl.saveBulletinAgreementInterface(project);
			
			
			//
		}
	}
	
	/** 
	 * Description :  发布项目，发公告
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void releaseProject(String projId) throws Exception {
		//更新项目状态
		Project project = projectDaoHibernate.get(projId);
		project.setAuditStatus(CommonEnum.USER_STATUS_FORMAL);//审核状态
		project.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//提交状态
		projectDaoHibernate.save(project);
		
		//发布公告
		bulletinAgreementManagerImpl.saveBulletinAgreementInterface(project);
		
		//给我的客户发送信息
		concernService.fitConcernByOrgBidRange(project);
		
		//给订阅了公告的用户发送信息
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("serviceId", ServiceEnum.SERVICE_B);//公告订阅服务
		params.put("purCategoryIds", project.getPurCategoryIds());
		sendMessageAndMailAndMobile(serviceSubscribeManagerImpl.getAllManageUserHasService(params) ,project);
	}
	
	/** 
	 * Description :  发送短信，站内信，邮件（公告订阅）
	 * Create Date: 2011-7-25上午11:08:05 by yucy  Modified Date: 2011-7-25上午11:08:05 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private void sendMessageAndMailAndMobile(List<User> users,Project projet) throws Exception{
		//获取标题和内容（从模板）
		String subject = "";
		String content = "";
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("projectName", projet.getProjName());
		templateMap.put("siteName", SysInfo.getInstance().getSitename());
		try {
			subject = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.RELEASE_BULLETIN_REMIND_TITLE), templateMap);
			content = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.RELEASE_BULLETIN_REMIND_CONTENT), templateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(User user:users){
			//邮件
			MailUtil.sendEmailNotAlways(new String[]{user.getEmp().getEmail()}, subject, content, null, null);
			//站内信
			MessageUtil.sendMessageSystem(new String[]{user.getObjId()}, subject, content, null, null, false);
			//短信
			MobileMessageUtil.sendMobileMessage(
					user.getEmp().getMobile(), 
					AuthenticationHelper.getCurrentUser(true).getObjId(),
					AuthenticationHelper.getCurrentUser(true).getUsername(),
					user.getObjId(), 
					user.getUsername(),
					content, 
					null, 
					null);
		}
	}
	
	/** 
	 * Description :  项目延时，修改公告
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveDelayProject(Map<String,Object> paramMap) throws Exception {
		//轮次
		JSONArray turnArray = JSONArray.fromObject(paramMap.get("turn").toString());
		List<BargainTurn> turnList = new ArrayList<BargainTurn>();
		BargainTurn bargainTurn = new BargainTurn();
		for(Object obj : turnArray) {
			bargainTurn = (BargainTurn)JsonUtils.json2Bean(((JSONObject)obj).toString(), BargainTurn.class);
			if(!StringUtils.hasLength(bargainTurn.getObjId())) {
				bargainTurn.setObjId(null);
			}
			turnList.add(bargainTurn);
		}
		//保存轮次
		bargainTurnDaoHibernate.save(turnList);
		
		//获取项目
		Project project = (Project)paramMap.get("project");
		
		//修改公告内容（已发布的项目要修改公告内容）
		//根据项目获取公告
		Bulletin bulletin = bulletinService.getBulletinByProjectId(project.getObjId(), BulletinTypeEnum.BARGIN_BULLETIN);
		if(bulletin != null) {
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap = bulletinAgreementManagerImpl.getBulletinTemplateMap(project);
			String newContent = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BULLETIN_TEMPLATE), templateMap);
			bulletin.setBullContents(newContent);
			bulletinAgreementManagerImpl.saveBulletinAgreement(bulletin);
		}
	}

	/** 
	 * Description :  取出待评价对象
	 * Create Date: 2010-10-28下午03:45:12 by yucy  Modified Date: 2010-10-28下午03:45:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEvaluateObjectSupplier(String projectId) throws Exception {
		Project project = projectDaoHibernate.get(projectId);
		return bargainProjectDao.getEvaluateObjectSupplier(project.getBuyersId(),projectId);
	}

	/** 
	 * Description :  项目查询数据(采购人)
	 * Create Date: 2011-6-29下午02:14:33 by yucy  Modified Date: 2011-6-29下午02:14:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Object> getProjectList(Page<Object> page,Map<String, Object> param) throws Exception {
		return bargainProjectDao.getProjectList(page,param);
	}

	/** 
	 * Description :  项目查询数据(供应商)
	 * Create Date: 2011-6-29下午02:14:33 by yucy  Modified Date: 2011-6-29下午02:14:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Object> getMyProjectList(Page<Object> page, Map<String, Object> param) throws Exception {
		return bargainProjectDao.getMyProjectList(page,param);
	}

	/**
	 * 我与客户的交易项目记录
	 */
	public Page<ProjectQueryDto> getExchangeProjectRecord(Page<ProjectQueryDto> page, Map<String, Object> param) throws Exception {
		return bargainProjectDao.getExchangeProjectRecord(page, param);
	}

	/** 
	 * Description :  保存终止项目的信息
	 * Create Date: 2011-12-6下午06:11:43 by likg  Modified Date: 2011-12-6下午06:11:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveStopProjectInfo(ProjectExceptionApply projectExceptionApply, Map<String, Object> param) throws Exception {
		//是否需要审核
		String needAudit = (String) param.get("needAudit");
		
		//修改项目状态项目信息
		Project project = projectDaoHibernate.get(projectExceptionApply.getProject().getObjId());
		if("true".equalsIgnoreCase(needAudit)) {
			project.setProjImplStatus(ProjImplStatusEnum.SUSPEND);
		} else {
			project.setProjImplStatus(ProjImplStatusEnum.STOP);
			
			//删除项目与任务书中间表的数据
			projectTaskItemDaoHibernate.deleteByProjectId(project.getObjId());
		}
		projectDaoHibernate.save(project);
		
		//保存终止项目申请记录
		if("true".equalsIgnoreCase(needAudit)) {
			projectExceptionApply.setUseStatus(CommonEnum.USER_STATUS_TEMP);
			projectExceptionApply.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		} else {
			projectExceptionApply.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
			projectExceptionApply.setAuditStatus(AuditStatusEnum.AUDIT_PASS);
		}
		projectExceptionApplyDaoHibernate.save(projectExceptionApply);
	}

}
