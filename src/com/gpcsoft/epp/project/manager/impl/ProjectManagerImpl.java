package com.gpcsoft.epp.project.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.dao.SequenceNumberDao;

@Repository
@SuppressWarnings("unchecked")
public class ProjectManagerImpl extends BaseManagerImpl<Project> implements ProjectManager {

	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("sequenceNumberDaoHibernate")
	private SequenceNumberDao sequenceNumberdao;
	
	/** 
	 * FuncName:searchProject
	 * Description:根据查询条件获得相关的项目信息
	 * @param queryObject:查询条件,page:分页对象
	 * @param page
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:11:42   
	 * @Modifier Administrator
	 * @Modified Date: 2010-5-19上午10:11:42 
	 */
	public Page<Project> searchProject(QueryObject queryObject,Page<Project> page) {
		return projectDaoHibernate.searchProject(queryObject, page);
	}

	/**
	 * FuncName:getProjectByTotal
	 * Description:根据项目ID获取项目信息，并将项目的统计信息封装
	 * @param  Project
	 * @author: shenjz
	 * @Create Date:2011-1-5  上午09:15:35
	 * @Modifier: shenjz
	 * @Modified Date:2011-1-5  上午09:15:35
	 */
	public Project getProjectByTotal(String objId) {
		Project project = projectDaoHibernate.get(objId);
		return project;
	}

	/** 
	 * FuncName:getSubProjectByQueryObject
	 * Description :根据项目Id获得项目对应的包组信息
	 * @param   projectId:项目主键
	 * @return List<Project>
	 * @author yangx  
	 * @Create Date: 2010-11-9下午05:13:01
	 * @Modifier   yangx 
	 * @Modified Date: 2010-11-9下午05:13:01    
	 */
	public List<Project> getSubProjectByQueryObject(String projectId) {
		logger.debug("\n ProjectServiceImpl||getSubProjectByProjectId\n");
		Assert.notNull(projectId, "必须要关联项目!");
		QueryObject queryObject = new QueryObjectBase();
		queryObject.setEntityClass(Project.class);
		queryObject.getQueryParam().and(new QueryParam("parentId",QueryParam.OPERATOR_EQ,projectId));
		queryObject.getQueryProjections().setOrderProperty("projCode");//根据包组编号排序
		queryObject.getQueryProjections().setOrderProperty("createTime");//根据创建时间排序(默认升序)
		queryObject.getQueryProjections().setDescFlag(false); 		 
		return projectDaoHibernate.findByQuery(queryObject);
	}

	/**
	 * FuncName:statisticsEbuyMethod
	 * Description :取得采购方式的统计数据（5种采购方式的项目数）
	 * @param  queryObject:查询的分页对象
	 * @return  List
	 * @author 		guom
	 * @Create Date: 2010-6-7下午4:23:57 
	 * @Modifier   guom
	 * @Modified Date: 2010-6-7下午4:23:57
	 */
	public List statisticsEbuyMethod(QueryObject queryObject) {
		List list = projectDaoHibernate.statisticsEbuyMethod(queryObject);
		return list;
	}

	/** 
	 * FuncName:removeProjectMTaskPlan
	 * Description:删除项目包组以及中间表
	 * @param   projectId:项目主键
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-6-25上午10:25:43 
	 * @Modifier    yangx
	 * @Modified Date: 2010-6-25上午10:25:43    
	 */
	public void removeProjectMTaskPlan(String projectId){
		projectDaoHibernate.removeProjectMTaskPlan(projectId);
	}
	
	/** 
	 * FuncName:getProjectMPreqByProjectId
	 * Description:根据包组ID获取中间表(包组与需求条目中间表)值
	 * @param   queryObject:查询条件的组装对象
	 * @return List
	 * @author yangx  
	 * @Create Date: 2010-6-25下午01:11:34 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-25下午01:11:34   
	 */
	public List getProjectMPreqByProjectId(QueryObject queryObject){
		return projectDaoHibernate.findByQuery(queryObject);
	}

	/** 
	 * FuncName:getSumTaskPlanDetailProjectId
	 * Description :  根据项目ID获取采购预算总金额
	 * @param   projectId:项目主键
	 * @return  String
	 * @author yangx
	 * @Create Date: 2010-7-7下午04:14:47 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-7下午04:14:47    
	 */
	public String getSumTaskPlanDetailProjectId(String projectId){
		return projectDaoHibernate.getSumTaskPlanDetailProjectId(projectId);
	}

	/** 
	 * FuncName:saveProjProcessStatus
	 * Description:根据项目Id保存项目实施状态
	 * @param   projectId:项目主键,projProcessStatus:项目进度
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-7-15上午11:15:27 
	 * @Modifier  yangx
	 * @Modified Date: 2010-7-15上午11:15:27     
	 */
	public void saveProjProcessStatus(String projectId, String projProcessStatus) {
		Project project = this.get(projectId);
		if(project==null){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
		}
		project.setProjProcessStatus(projProcessStatus);
		this.save(project);
	}
	
	/**
	 * FuncName:generatorProjectCodeByQO
	 * Description: 默认生成项目编号,Service层必须开启DB事务
	 * @param queryObject:为扩展参数,暂时不用,projBuyMethod:项目采购方式
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44  
	 */
	public String generatorProjectCodeByQO(QueryObject queryObject,String projBuyMethod)throws EsException {
		String prefixCode = "";// 1.根据项目采购方式获取前缀编号
		try {
			if (projBuyMethod.equals(EbuyMethodEnum.OPEN_BIDDING)) {				// 公开招标
				prefixCode = messageSource.getMessage("openProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.INVITE_BIDDING)) {		// 邀请招标
				prefixCode = messageSource.getMessage("inviteProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.NEGOTIATE)) {			// 竞争性谈判
				prefixCode = messageSource.getMessage("negotiateProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.INQUIRY)) {				// 询价
				prefixCode = messageSource.getMessage("inquiryProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.SINGLE_SOURCE)) {		// 单一来源
				prefixCode = messageSource.getMessage("singleSourceProjectPrefixCode");
			} else {
				prefixCode = "";
			}
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_GET_PREFIX_CODE_ISNULL));
		}
		// 2.按照规则生成项目编号
		StringBuffer returnCode = new StringBuffer();
		returnCode.append(prefixCode);
		if(null != prefixCode && !"".equals(prefixCode)){
			returnCode.append("-");
		}
		returnCode.append(DateUtil.format(new Date(), "yyyyMMdd"));
		try {
			returnCode.append("-"+sequenceNumberdao.updateAndGetCurSn(returnCode.toString(), 3));
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_IS_DB_READONLY));
		}
		return returnCode.toString();
	}

	/**
     * FuncName:checkEvalBid
     * Description:根据招标项目Id,b包组Id,和人员信息，判断是否可以评标
     * @param   projectId:项目主键,
     * @return  Boolean
     * @author liuke
     * @Create Date: 2010-8-4下午03:29:04   
     * @Modifier liuke
     * @Modified Date: 2010-8-4下午03:29:04 
     */
	public Boolean checkEvalBid(String projectId, String subProjectId, User user) throws Exception {
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		Date nowDate = new Date();
		if (null==projectId ||"".equals(projectId)) {//项目不存在
			exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
		} else {
			ProjectRule projectRule = projectDaoHibernate.getProjectRuleByProjectId(projectId);
			Date evalStartTime = projectRule.getEvalSTime();
			Date evalEndTime = projectRule.getEvalETime();
			if (null==evalStartTime){//评标开始时间未设置
				exception.append(messageSource.getMessage(EsExceptionEnum.EVAL_STIME_IS_NONE));
			}	
			if (null==evalEndTime) {//评标结束时间未设置
				exception.append(messageSource.getMessage(EsExceptionEnum.EVAL_ETIME_IS_NONE));
			}
			if (null!=evalStartTime&&nowDate.before(evalStartTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.BEFORE_EVAL_TIME));
			}
			if (null!=evalStartTime&&nowDate.after(evalEndTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.AFTER_EVAL_TIME));
			}
		}
		if(exception.length()>0)checkValue=false;//若有异常信息，表示有异常，需要把判断置为false;
		if (!checkValue) {
			throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}
	
	/**
	 * FuncName:checkProjectTime
	 * Description:根据招标项目主键,对项目时间进行判断是否符合项目需求
	 * @param projectId
	 * @return Boolean
	 * @author shenjianzhuang
	 * @Create Date:2010-8-9上午11:28:53 
	 * @Modifier  shenjianzhuang 
	 * @Modified Date: 2010-8-9下午13:25:53   
	 */	
	public Boolean checkProjectTime(String projectId) {
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		Date nowDate = new Date();
		if (null==projectId ||"".equals(projectId)) {
			exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));//项目不存在
		} else {
			ProjectRule projectRule = (ProjectRule)projectDaoHibernate.getProjectRuleByProjectId(projectId);
			Date signupStartTime=projectRule.getSignUpSTime();//报名开始时间
			Date signupEndTime=projectRule.getSignUpETime();//报名结束时间
			Date submitStartTime=projectRule.getSubmitStTime();//投标开始时间
			Date submitEndTime=projectRule.getSubmitETime();//投标结束时间
			Date evalStartTime = projectRule.getEvalSTime();//评标开始时间
			Date evalEndTime = projectRule.getEvalETime();//评标结束时间
			if(null==signupStartTime) {
				exception.append(messageSource.getMessage(EsExceptionEnum.SIGNUP_START_TIME_IS_NONE));//报名开始时间未设置
			}
			if(null==signupEndTime) {
				exception.append(messageSource.getMessage(EsExceptionEnum.SIGNUP__END_TIME_IS_NONE));//报名结束时间未设置
			}
			if(null==submitStartTime) {
				exception.append(messageSource.getMessage(EsExceptionEnum.SUBMIT__START_TIME_IS_NONE));//投标开始时间未设置
			}
			if(null==submitEndTime) {
				exception.append(messageSource.getMessage(EsExceptionEnum.SUBMIT__END_TIME_IS_NONE));//投标结束时间未设置
			}
			if (null==evalStartTime) { 
				exception.append(messageSource.getMessage(EsExceptionEnum.EVAL_STIME_IS_NONE));//评标开始时间未设置
			}	
			if (null==evalEndTime) {
				exception.append(messageSource.getMessage(EsExceptionEnum.EVAL_ETIME_IS_NONE));//评标结束时间未设置
			}
			if(null!=signupEndTime&&null!=signupStartTime&&signupEndTime.before(signupStartTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.SIGNUP_TIME));//报名结束时间应该大于报名开始时间
			}
			if(null!=signupEndTime&&null!=submitStartTime&&submitStartTime.before(signupEndTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.SIGNUP_SUBMIT_TIME));//投标开始时间应该大于报名结束时间
			}
			if(null!=submitEndTime&&null!=submitStartTime&&submitEndTime.before(submitStartTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.SUBMIT_TIME));//投标结束时间应该大于投标开始时间
			}
			if (null!=submitEndTime&&null!=evalStartTime&&evalStartTime.before(submitEndTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.SUBMIT_EVAL_TIME));//评标开始时间应该大于投标结束时间
			}
			if(null!=evalStartTime&&null!=evalEndTime&&evalEndTime.before(evalStartTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.EVAL_TIME));//评标结束时间应该大于评标开始时间
			}
			if(null!=signupStartTime&&nowDate.before(signupStartTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.BEFORE_SIGNUP_TIME));//未到报名时间
			}
			if(null!=signupEndTime&&nowDate.after(signupEndTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.AFTER_SIGNUP_TIME));//报名时间已过
			}
			if(null!=submitStartTime&&nowDate.before(submitStartTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.BEFORE_SUBMIT_TIME));//未到投标时间
			}
			if(null!=submitEndTime&&nowDate.after(submitEndTime)) {
				exception.append(messageSource.getMessage(EsExceptionEnum.AFTER_SUBMIT_TIME));//投标时间已过
			}
			if (null!=evalStartTime&&nowDate.before(evalStartTime))  {
				exception.append(messageSource.getMessage(EsExceptionEnum.BEFORE_EVAL_TIME));//未到评标时间
			}
			if (null!=evalStartTime&&nowDate.after(evalEndTime))  {
				exception.append(messageSource.getMessage(EsExceptionEnum.AFTER_EVAL_TIME));//评标时间已过
			}
		}
		if(exception.length()>0){checkValue=false;}//若有异常信息，表示有异常，需要把判断置为false;}
		if (!checkValue) {
			throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}
	
	/**
	 * FuncName:checkProjectImplstatus
	 * Description:根据招标项目主键,对项目状态进行判断是否符合项目需求
	 * @param projectId:项目主键
	 * @return Boolean
	 * @author     shenjianzhuang
	 * @Create Date: 2010-8-10上午10:05:54 
	 * @Modifier    shenjianzhuang
	 * @Modified Date: 2010-8-10上午10:05:54  
	 */
	public Boolean checkProjectImplstatus(String projectId)throws EsException {
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		if (null==projectId ||"".equals(projectId)) {
			exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));//项目不存在
		} else {
			Project project = projectDaoHibernate.get(projectId);
			String projImplStatus =project.getProjImplStatus();
			if(projImplStatus.equals("01")) {
				exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_IMPLSTATUS_SUSPENDEDED));//项目已处于暂停状态
			}
			if(projImplStatus.equals("02")) {
				exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_IMPLSTATUS_STOPPED));//项目已处于终止状态
			}
		}
		if(exception.length()>0){checkValue=false;}//若有异常信息，表示有异常，需要把判断置为false;}
		if (!checkValue) {
			throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}
	
	/** 
	 * FuncName:searchProjectByCurUser
	 * Description :根据供应商当前用户和查询对象获得对应的参加过的招标项目
	 * @param queryObject:查询对象的分页条件,page:分页对象,user:当前登录用户
	 * @return Page<Project> 
	 * @author Administrator
	 * @Create Date: 2010-6-26上午11:41:11 
	 * @Modifier   Administrator
	 * @Modified Date: 2010-6-26上午11:41:11
	 */
	public Page<Project> searchProjectByCurUser(QueryObject queryObject,Page<Project> page, User user) {
		return projectDaoHibernate.searchProjectByCurUser(queryObject, page, user);
	}
	
	/** 
	 * FuncName:getProjectRuleByProjectId
	 * Description :根据项目Id获取项目规则
	 * @param   projectId：项目Id
	 * @return  ProjectRule
	 * @author yangx
	 * @Create Date: 2010-11-10下午05:04:41
	 * @Modifier yangx
	 * @Modified Date: 2010-11-10下午05:04:41  
	 */
	public ProjectRule getProjectRuleByProjectId(String projectId){
		return projectDaoHibernate.getProjectRuleByProjectId(projectId);
	}

	public Project findProjectByProjCode(String projCode) {
		return projectDaoHibernate.findProjectByProjCode(projCode);
	}

	
	/**
	 * FuncName: findProjectByProjCodeAndParent
	 * Description :  根据项目编号与parent查询项目
	 * @param 
	 * @param projCode
	 * @param parentId
	 * @return Project
	 * @author: liuke
	 * @Create Date:2011-5-30  下午03:54:03
	 * @Modifier: liuke
	 * @Modified Date:2011-5-30  下午03:54:03
	 */
	public Project findProjectByProjCodeAndParent(String projCode,
			String parentId) {
		QueryObject queryObject = new QueryObjectBase();
		queryObject.setEntityClass(Project.class);
		queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		queryObject.getQueryParam().and(new QueryParam("parentId",QueryParam.OPERATOR_EQ,parentId));
		List list = projectDaoHibernate.findByQuery(queryObject);
		return (list.isEmpty())?null:(Project)list.get(0);
	}
}
