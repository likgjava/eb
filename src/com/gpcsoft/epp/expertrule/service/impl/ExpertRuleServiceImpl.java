package com.gpcsoft.epp.expertrule.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.expertrule.dao.ExpertRuleDao;
import com.gpcsoft.epp.expertrule.domain.ExpertRuleTypeEnum;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRuleItem;
import com.gpcsoft.epp.expertrule.manager.ExpertRuleManager;
import com.gpcsoft.epp.expertrule.manager.ExpertRuleSendManager;
import com.gpcsoft.epp.expertrule.manager.TakeManager;
import com.gpcsoft.epp.expertrule.service.ExpertRuleService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projrule.domain.MeetRoom;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.eprocurement.taker.provider.CodePO;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class ExpertRuleServiceImpl extends BaseGenericServiceImpl<TakeExpertRule> implements ExpertRuleService{

	@Autowired(required=true)  @Qualifier("expertRuleManagerImpl")
	private ExpertRuleManager expertRuleManager;
	
	@Autowired(required=true)  @Qualifier("expertRuleDaoHibernate")
	private ExpertRuleDao expertRuleDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("takeManagerImpl")
	private TakeManager takeManager;
	
	@Autowired(required=true)  @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	private ExpertRuleSendManager expertRuleSendManager;
	private ExpertRuleSendManager getExpertRuleSendManager() {
		if (null==this.expertRuleSendManager) {
			this.expertRuleSendManager = (ExpertRuleSendManager)FrameBeanFactory.getBean("expertRuleSendManagerImpl");
		}
		return expertRuleSendManager;
	}
	
	/** 
	 * Description : 保存专家规则 
	 * Create Date: 2010-8-2下午03:16:36 by yangx  Modified Date: 2010-8-2下午03:16:36 by yangx
	 * Modified Date: 2010-11-1下午16:44:36 by zhouzhanghe
	 * @param   expertRule:专家规则
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule saveExpertRule(TakeExpertRule expertRule){
		logger.debug("\nExpertRuleServiceImpl||saveExpertRule\n");
		if(expertRule.getBuyerNameIds()!=null){//采购单位Id
			String buyerNameIds = "";
			for (String buyerIds:expertRule.getBuyerNameIds().split(SeparationEnum.COMMA)) {
				if (buyerIds!=null&&!"".equals(buyerIds.trim())) {
					buyerNameIds +=buyerIds+SeparationEnum.AT;
				}
			}
			expertRule.setBuyerNameIds(buyerNameIds.substring(0,buyerNameIds.lastIndexOf(SeparationEnum.AT)));
		}
		if(expertRule.getOutBuyerIds()!=null){//回避单位Id
			String outBuyerIds = "";
			for (String outBuyerId:expertRule.getOutBuyerIds().split(SeparationEnum.COMMA)) {
				if (outBuyerId!=null&&!"".equals(outBuyerId.trim())) {
					outBuyerIds +=outBuyerId+SeparationEnum.AT;
				}
			}
			expertRule.setOutBuyerIds(outBuyerIds.substring(0,outBuyerIds.lastIndexOf(SeparationEnum.AT)));
		}
		expertRuleManager.save(expertRule);
		
		//根据申请抽取专家设置的时间，更改项目的评审开始和评审结束时间  modified by zhouzhanghe at 2010.11.1 16:44
		if(expertRule == null || expertRule.getSubProjectId() == null || expertRule.getSubProjectId().getObjId() == null)
			throw new EsException(messageSource.getMessage(EsExceptionEnum.EXPERTRULE_SAVERULEFAILURE_BECAUSEDBYPROJECTIDISNULL));
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(expertRule.getSubProjectId().getObjId());
		projectRule.setEvalSTime(expertRule.getBidStarttime());
		projectRule.setEvalETime(expertRule.getBidEndtime());
		projectManager.save(projectRule,ProjectRule.class);
		//工作计划需要----------start
		User user = AuthenticationHelper.getCurrentUser();
		expertRule.setUser(user);
		Project project = projectManager.get(expertRule.getSubProjectId().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		expertRule.setParentBizId(parentBizId);
		//工作计划需要----------end
		return expertRule;
	}
	/** 
	 * Description : 提交专家规则 
	 * Create Date: 2010-8-2下午03:16:36 by yangx  Modified Date: 2010-8-2下午03:16:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule saveSubmitExpertRule(TakeExpertRule expertRule,String takeExpertRuleId){
		logger.debug("\nExpertRuleServiceImpl||saveSubmitExpertRule\n");
		if(expertRule.getBuyerNameIds()!=null){//采购单位Id
			String buyerNameIds = "";
			for (String buyerIds:expertRule.getBuyerNameIds().split(SeparationEnum.COMMA)) {
				if (buyerIds!=null&&!"".equals(buyerIds.trim())) {
					buyerNameIds +=buyerIds+SeparationEnum.AT;
				}
			}
			expertRule.setBuyerNameIds(buyerNameIds.substring(0,buyerNameIds.lastIndexOf(SeparationEnum.AT)));
		}
		if(expertRule.getOutBuyerIds()!=null){//回避单位Id
			String outBuyerIds = "";
			for (String outBuyerId:expertRule.getOutBuyerIds().split(SeparationEnum.COMMA)) {
				if (outBuyerId!=null&&!"".equals(outBuyerId.trim())) {
					outBuyerIds +=outBuyerId+SeparationEnum.AT;
				}
			}
			expertRule.setOutBuyerIds(outBuyerIds.substring(0,outBuyerIds.lastIndexOf(SeparationEnum.AT)));
		}
		expertRuleManager.save(expertRule);
		if (takeExpertRuleId!=null&&expertRule.getUseStatus()!=null&&CommonEnum.USER_STATUS_FORMAL.equals(expertRule.getUseStatus())) {
			this.getExpertRuleSendManager().sendExpertRule(takeExpertRuleId);
		}
		
		//工作计划需要----------start
		User user = AuthenticationHelper.getCurrentUser();
		expertRule.setUser(user);
		Project project = projectManager.get(expertRule.getSubProjectId().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		expertRule.setParentBizId(parentBizId);
		//工作计划需要----------end
		return expertRule;
	}
	
	/** 
	 * Description :  根据包组Id查询专家抽取规则
	 * Create Date: 2010-8-17下午06:49:55 by yangx  Modified Date: 2010-8-17下午06:49:55 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */	
	public TakeExpertRule getExpertRuleBySubProjectId(String subProjectId){
		logger.debug("\nExpertRuleServiceImpl||getExpertRuleBySubProjectId\n");
		QueryObject<TakeExpertRule> queryObject = new QueryObjectBase<TakeExpertRule>();
		queryObject.setEntityClass(TakeExpertRule.class);
		queryObject.getQueryParam().and(new QueryParam("subProjectId.objId",QueryParam.OPERATOR_EQ,subProjectId));
		List<TakeExpertRule> list = expertRuleManager.findByQuery(queryObject);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	/** 
	 * Description :  根据包组Id查询专家抽取规则
	 * Create Date: 2010-8-17下午06:49:55 by yangx  Modified Date: 2010-8-17下午06:49:55 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public TakeExpertRule toCreateOrUpdateTakeExpertRule(String projectId) {
		logger.debug("\n ExpertRuleServiceImpl||toCreateOrUpdateTakeExpertRule\n");
		QueryObject<TakeExpertRule> queryObject = new QueryObjectBase<TakeExpertRule>();
		queryObject.setEntityClass(TakeExpertRule.class);
		queryObject.getQueryParam().and(new QueryParam("subProjectId.objId",QueryParam.OPERATOR_EQ,projectId));
		List<TakeExpertRule> list = expertRuleManager.findByQuery(queryObject);
		Project project = (Project)this.get(projectId,Project.class);
		ProjectRule projectRule = (ProjectRule)projectManager.getProjectRuleByProjectId(projectId);
		TakeExpertRule takeExpertRule = new TakeExpertRule();
		takeExpertRule.setSubProjectId(project);
		try {
			if (list!=null&&list.size()>0) {//判断是否存在（修改）
				takeExpertRule = list.get(0);
				List<CodePO> room = takeManager.getInfoForFile(SeparationEnum.ROOM);//得到评审室
				List<CodePO> edu = takeManager.getInfoForFile(SeparationEnum.EDU);//专家学历
				List<CodePO> code = takeManager.getInfoForFile(SeparationEnum.CITYCODE);// 专家地域
				List<CodePO> group = takeManager.getInfoForFile(SeparationEnum.EXPERTGROUP);//专家类型 
				takeExpertRule.setBidRooms(room);
				takeExpertRule.setTopEducName(edu);
				takeExpertRule.setCityCodeName(code);
				takeExpertRule.setExpertGroupName(group);
				if(takeExpertRule.getOutBuyerIds()!=null){//回避单位Id
					takeExpertRule.setOutBuyerIds((takeExpertRule.getOutBuyerIds()).replaceAll(SeparationEnum.AT,SeparationEnum.COMMA));
				}
				
				QueryObject queryObject_ = new QueryObjectBase();
				queryObject_.setEntityClass(TakeExpertRuleItem.class);
				queryObject_.getQueryParam().and(new QueryParam("takeExpertRule.objId",QueryParam.OPERATOR_EQ,takeExpertRule.getObjId()));
				List list_ = expertRuleManager.findByQuery(queryObject_);
				if (list_!=null&&list_.size()>0) {
					takeExpertRule.setIsSubRule("yes");
				}else {
					takeExpertRule.setIsSubRule("no");
				}
			}else {
				List<CodePO> room = takeManager.getInfoForFile(SeparationEnum.ROOM);//得到评审室
				takeExpertRule.setBidRooms(room);
				takeExpertRule.setBidEndtime(projectRule.getEvalETime());
				takeExpertRule.setBidStarttime(projectRule.getEvalSTime());
				String buyerName = project.getBuyersName();//采购单位名称
				String buyerN ="";
				if (buyerName!=null&&!"".equals(buyerName)) {
					String[] buyerNames = buyerName.split(SeparationEnum.COMMA);
					Set<String> set=new HashSet<String>();
					for (int i=0;i<buyerNames.length;i++) {
						set.add(buyerNames[i]); 
					}
					Iterator<String> it= set.iterator();
					while(it.hasNext()){
						buyerN +=it.next()+SeparationEnum.COMMA;
					}
					takeExpertRule.setBuyerNames(buyerN.substring(0,buyerN.length()-1));
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
				throw new EsException(messageSource.getMessage(EsExceptionEnum.TRANS_EXPERTRULE_FAIL));
			}	
			return takeExpertRule;
	}
	

	/** 
	 * Description :  获取信息[来源：本地]
	 * Create Date: 2010-8-30下午04:31:01 by yangx  Modified Date: 2010-8-30下午04:31:01 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getInfoForFile(String infoType){
		logger.debug("\n ExpertRuleServiceImpl||getInfoForFile\n");
		return takeManager.getInfoForFile(infoType);
	}
	
	/** 
	 * Description :  通过WebService读取信息写到指定位置
	 * Create Date: 2010-8-30下午03:45:16 by yangx  Modified Date: 2010-8-30下午03:45:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveInfoForWebService(String infoType){
		logger.debug("\n ExpertRuleServiceImpl||saveInfoForWebService\n");
		if (infoType!=null&&SeparationEnum.ALL.equals(infoType)) {//判断是否是获取所有的数据
			expertRuleManager.saveInfoForWebService(SeparationEnum.CATEGORY);
			expertRuleManager.saveInfoForWebService(SeparationEnum.UNIT);
			expertRuleManager.saveInfoForWebService(SeparationEnum.CITYCODE);
			expertRuleManager.saveInfoForWebService(SeparationEnum.EXPERTGROUP);
			expertRuleManager.saveInfoForWebService(SeparationEnum.EDU);
			expertRuleManager.saveInfoForWebService(SeparationEnum.ROOM);
		}else{
			expertRuleManager.saveInfoForWebService(infoType);
		}
	}	
	
	/** 
	 * Description :  获取品目信息[来源：本地]
	 * Create Date: 2010-8-30下午04:43:33 by yangx  Modified Date: 2010-8-30下午04:43:33 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getPurCategory(){
		logger.debug("\n ExpertRuleServiceImpl||getPurCategory\n");
		return takeManager.getPurCategory();
	}
	
	/** 
	 * Description :  根据单位名称进行模糊查询单位信息
	 * Create Date: 2010-8-23下午03:38:47 by yangx  Modified Date: 2010-8-23下午03:38:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getUnitByName(String unitName){
		return takeManager.getUnitByName(unitName);
	}
	
	/** 
	 * Description :  保存专家规则条目
	 * Create Date: 2010-8-30下午08:31:57 by yangx  Modified Date: 2010-8-30下午08:31:57 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRuleItem saveExpertRuleCondition(TakeExpertRuleItem takeExpertRuleItem) {
		logger.debug("\n ExpertRuleServiceImpl||saveExpertRuleCondition\n");
		if(takeExpertRuleItem.getCityCode()!=null){//区域
			takeExpertRuleItem.setCityCode((takeExpertRuleItem.getCityCode()).replaceAll(SeparationEnum.COMMA,SeparationEnum.AT));
		}
		if(takeExpertRuleItem.getExpertGroup()!=null){//专家类型
			takeExpertRuleItem.setExpertGroup((takeExpertRuleItem.getExpertGroup()).replaceAll(SeparationEnum.COMMA,SeparationEnum.AT));
		}
		if(takeExpertRuleItem.getTopEduc()!=null){//学历
			takeExpertRuleItem.setTopEduc((takeExpertRuleItem.getTopEduc()).replace(SeparationEnum.COMMA,SeparationEnum.AT));
		}
		if (takeExpertRuleItem.getPurcategory()!=null) {//品目
			takeExpertRuleItem.setPurCategoryId(takeExpertRuleItem.getPurcategory().getObjId());
		}
		TakeExpertRuleItem t = (TakeExpertRuleItem)expertRuleManager.save(takeExpertRuleItem,TakeExpertRuleItem.class);
		return t;
	}
	
	/** 
	 * Description :  根据项目规则Id查询规则条目
	 * Create Date: 2010-8-31下午02:22:46 by yangx  Modified Date: 2010-8-31下午02:22:46 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<TakeExpertRuleItem> getTakeExpertRuleItemBytakeExpertRuleId(String takeExpertRuleId){
		logger.debug("\n ExpertRuleServiceImpl||getTakeExpertRuleItemBytakeExpertRuleId\n");
		List<TakeExpertRuleItem> list =  expertRuleDaoHibernate.getTakeExpertRuleItemBytakeExpertRuleId(takeExpertRuleId);//规则明细
		if (list!=null&&list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				TakeExpertRuleItem takeExpertRuleItem = list.get(i);
				takeExpertRuleItem = getTakeExpertRuleItemContent(takeExpertRuleItem);//填写内容
			}
		}
		return list;
	}
	/** 
	 * Description :  根据项目规则明细Id获取规则明细
	 * Create Date: 2010-8-31下午04:34:31 by yangx  Modified Date: 2010-8-31下午04:34:31 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRuleItem getTakeExpertRuleItemByObjId(String takeExpertRuleItemId){
		logger.debug("\n ExpertRuleServiceImpl||getTakeExpertRuleItemByObjId\n");
		TakeExpertRuleItem takeExpertRuleItem = (TakeExpertRuleItem)this.get(takeExpertRuleItemId,TakeExpertRuleItem.class);
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getPurCategoryId()!=null) {//判断是否有品目
			List<PurCategory> listpurCategory = takeManager.getPurCategory();
			for (int j=0;j<listpurCategory.size();j++){
				PurCategory purCategory = listpurCategory.get(j);
				if (takeExpertRuleItem.getPurCategoryId().equals(purCategory.getCategoryCode())) {//判断是否相同品目
					takeExpertRuleItem.setPurcategory(purCategory);
					break;
				}
			}
		}
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getAge()!=null) {//判断是否有年龄
			if (takeExpertRuleItem.getAge().endsWith("-")) {//判断是否为起始年龄
				String[] ages = takeExpertRuleItem.getAge().split("-");
				takeExpertRuleItem.setAgeStart(ages[0]);
			}else if (takeExpertRuleItem.getAge().startsWith("-")) {//判断是否为起始年龄
				String[] ages = takeExpertRuleItem.getAge().split("-");
				takeExpertRuleItem.setAgeEnd(ages[0]);
			}else {
				String[] ages = takeExpertRuleItem.getAge().split("-");
				takeExpertRuleItem.setAgeStart(ages[0]);
				takeExpertRuleItem.setAgeEnd(ages[1]);
			}
		}
		return takeExpertRuleItem;
	}
	
	/** 
	 * Description :  根据规则明细填写明细显示内容
	 * Create Date: 2010-8-31下午05:14:12 by yangx  Modified Date: 2010-8-31下午05:14:12 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRuleItem getTakeExpertRuleItemContent(TakeExpertRuleItem takeExpertRuleItem){
		logger.debug("\n ExpertRuleServiceImpl||getTakeExpertRuleItemContent\n");
		String extCondition = "";
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getPurCategoryId()!=null) {//判断是否有品目
			List<PurCategory> listpurCategory = takeManager.getPurCategory();
			for (int j=0;j<listpurCategory.size();j++){
				PurCategory purCategory = listpurCategory.get(j);
				if (takeExpertRuleItem.getPurCategoryId().equals(purCategory.getCategoryCode())) {//判断是否相同品目
					extCondition +="申请抽取评审品目为【"+purCategory.getCategoryName()+"】；";
					break;
				}
			}
		}
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getCityCode()!=null) {//判断是否有评审地域
			List<CodePO> listCityCode = takeManager.getInfoForFile(SeparationEnum.CITYCODE);
			String[] cityCodes = takeExpertRuleItem.getCityCode().split(SeparationEnum.AT);
			for (int c=0;c<cityCodes.length;c++) {
				String cityCodeId = cityCodes[c];
				for (int k=0;k<listCityCode.size();k++) {
					CodePO codePO = listCityCode.get(k);
					if (cityCodeId!=null&&cityCodeId.equals(codePO.getColumn_value())) {//判断是否相同评审地域
						extCondition +=codePO.getColumn_name()+"，";
						break;
					}
				}
			}
		}
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getSpecialtyYear()!=null) {//判断是否有专业工龄
			extCondition += "工作满"+takeExpertRuleItem.getSpecialtyYear()+"年以上，";
		}
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getExpertGroup()!=null) {//判断是否有专家类型
			List<CodePO> listExpertGroup = takeManager.getInfoForFile(SeparationEnum.EXPERTGROUP);
			String[] expertGroup = takeExpertRuleItem.getExpertGroup().split(SeparationEnum.AT);
			for (int g=0;g<expertGroup.length;g++) {
				String expertGroupId = expertGroup[g];
				for (int e=0;e<listExpertGroup.size();e++) {
					CodePO codePO = listExpertGroup.get(e);
					if (expertGroupId!=null&&expertGroupId.equals(codePO.getColumn_value())) {//判断是否相同专家类型
						if (g==0) {
							extCondition +="专家类型为 "+codePO.getColumn_name()+"，";
						}else{
							extCondition +=codePO.getColumn_name()+"，";
						}
						break;
					}
				}
			}
		}
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getAge()!=null) {//判断是否有年龄
			if (takeExpertRuleItem.getAge().endsWith("-")) {//判断是否为起始年龄
				String[] ages = takeExpertRuleItem.getAge().split("-");
				extCondition += ages[0]+"岁以上的，";
			}else if (takeExpertRuleItem.getAge().startsWith("-")) {//判断是否为起始年龄
				String[] ages = takeExpertRuleItem.getAge().split("-");
				extCondition += ages[0]+"岁以下的，";
			}else {
				String[] ages = takeExpertRuleItem.getAge().split("-");
				extCondition += ages[0]+"至"+ages[1]+"岁，";
			}
		}
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getTopEduc()!=null) {//判断是否有学历
			List<CodePO> listEdu = takeManager.getInfoForFile(SeparationEnum.EDU);
			String[] edu = takeExpertRuleItem.getTopEduc().split(SeparationEnum.AT);
			for (int g=0;g<edu.length;g++) {
				String eduId = edu[g];
				for (int e=0;e<listEdu.size();e++) {
					CodePO codePO = listEdu.get(e);
					if (eduId!=null&&eduId.equals(codePO.getColumn_value())) {//判断是否相同学历
						if (g==0) {
							extCondition +="学历为  "+codePO.getColumn_name()+"，";
						}else {
							extCondition +=codePO.getColumn_name()+"，";
						}
						break;
					}
				}
			}
		} 
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getExpertLevel()!=null) {//判断是否有抽取类型
			if (ExpertRuleTypeEnum.EXPERTLEVEL_ONLY.equals(takeExpertRuleItem.getExpertLevel())) {
				extCondition += "抽取类型为【只抽主评】，";
			}else {
				extCondition += "抽取类型为【正常抽取】，";
			}
		}
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getAmount()!=null) {//判断是否有正选人数
			extCondition += "正选"+takeExpertRuleItem.getAmount()+"人，";
		}
		if (takeExpertRuleItem!=null&&takeExpertRuleItem.getSubAmount()!=null) {//判断是否有备选人数
			extCondition += "备选"+takeExpertRuleItem.getSubAmount()+"人，";
		}
		takeExpertRuleItem.setContents(extCondition);
		return takeExpertRuleItem;
	}
	
	
	/** 
	 * Description : 根据项目Id获取评标室 
	 * Create Date: 2010-9-20下午02:08:03 by yangx  Modified Date: 2010-9-20下午02:08:03 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public String getMeetRoomByProjectId(String projectId){
		logger.debug("\n ExpertRuleServiceImpl||getMeetRoomByProjectId\n");
		List list = expertRuleDaoHibernate.getMeetRoomByProjectId(projectId);
		String room ="";
		if (list!=null&&list.size()>0) {
			MeetRoom meetRoom = (MeetRoom)list.get(0);
			room = meetRoom.getMeetRoomName();
		}
		return room;
	}


	public TakeExpertRule getByProjectId(String projectId) {
		logger.debug("\n ExpertRuleServiceImpl||getByProjectId\n");
		TakeExpertRule rule = expertRuleManager.getByProjectId(projectId);
		setCityNames(rule);
		return rule;
	}
	
	/** 
	 * Description :  获取所有条目的评审区域合集(去除重复) 打印预览用
	 * Create Date: 2010-10-15下午04:03:43 by wangcl  Modified Date: 2010-10-15下午04:03:43 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private void setCityNames(TakeExpertRule rule){
		logger.debug("\n ExpertRuleServiceImpl||setCityNames\n");
		List<CodePO> listCityCode = takeManager.getInfoForFile(SeparationEnum.CITYCODE);
		StringBuilder sb = new StringBuilder();
		Set<String> total = new HashSet<String>();
		for (Iterator<TakeExpertRuleItem> iterator = rule.getItems().iterator(); iterator.hasNext();) {
			TakeExpertRuleItem item = iterator.next();
			if(item.getCityCode()!=null){
				String[] cityCodes =item.getCityCode().split(SeparationEnum.AT);
				for (int i = 0; i < cityCodes.length; i++) {
					total.add(cityCodes[i]);
				}
			}
		}
		for (Iterator<String> iterator = total.iterator(); iterator.hasNext();) {
			String cityCodeId =  iterator.next();
			for (int k=0;k<listCityCode.size();k++) {
				CodePO codePO = listCityCode.get(k);
				if (cityCodeId!=null&&cityCodeId.equals(codePO.getColumn_value())) {//判断是否相同评审地域
					sb.append(codePO.getColumn_name()+"，");
					break;
				}
			}
		}
		String cityNames = sb.toString();
		if(cityNames.length()>0){
			rule.setCityNames(cityNames.substring(0, cityNames.length()-1));
		}
	}

	/** 
	 * Description :  按指定组别获取项目下所有标段的某组成员
	 * Create Date: 2010-8-5上午10:44:36 by yangx  Modified Date: 2010-8-5上午10:44:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project saveOrgetAllGroupMenber(String projectId, String groupType) {
		Project project = (Project)this.get(projectId, Project.class);
		WorkGroup workGroup = expertRuleManager.getWorkGroupByProjectIdAndType(projectId,groupType);
		return project;
	}

	/** 
	 * Description :  根据当前用户和包组获得成员信息  
	 * Create Date: 2010-8-5上午11:35:42 by yangx  Modified Date: 2010-8-5上午11:35:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<WorkgMember> getWorkMemberListByUserIdAndProjectId(
			String workgType, String projectId) {
		return expertRuleManager.getWorkMemberListByUserIdAndProjectId(workgType, projectId);
	}
	
}
