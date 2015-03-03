package com.gpcsoft.epp.project.manager;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ProjectManager extends BaseManager<Project> {

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
	public Page<Project> searchProject(QueryObject queryObject, Page<Project> page);
	
	/**
	 * FuncName:getProjectByTotal
	 * Description:根据项目ID获取项目信息，并将项目的统计信息封装
	 * @param  Project
	 * @author: shenjz
	 * @Create Date:2011-1-5  上午09:15:35
	 * @Modifier: shenjz
	 * @Modified Date:2011-1-5  上午09:15:35
	 */
	public Project getProjectByTotal(String objId);

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
	public List<Project> getSubProjectByQueryObject(String projectId);
	
	/**
	 * FuncName:statisticsEbuyMethod
	 * Description :取得采购方式的统计数据（5种采购方式的项目数）
	 * @param  queryObject:查询的分页对象
	 * @return  List
	 * @author guom
	 * @Create Date: 2010-6-7下午4:23:57 
	 * @Modifier   guom
	 * @Modified Date: 2010-6-7下午4:23:57
	 */
	public List statisticsEbuyMethod(QueryObject queryObject);
	
	/** 
	 * FuncName:removeProjectMTaskPlan
	 * Description :  删除项目包组以及中间表
	 * @param   projectId:项目主键
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-6-25上午10:25:43 
	 * @Modifier    yangx
	 * @Modified Date: 2010-6-25上午10:25:43    
	 */
	public void removeProjectMTaskPlan(String projectId);
	
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
	public List getProjectMPreqByProjectId(QueryObject queryObject);
	
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
	public String getSumTaskPlanDetailProjectId(String projectId);
	
	/** 
	 * FuncName:saveProjProcessStatus
	 * Description :  根据项目Id保存项目实施状态
	 * @param   projectId:项目主键,projProcessStatus:项目进度
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-7-15上午11:15:27 
	 * @Modifier  yangx
	 * @Modified Date: 2010-7-15上午11:15:27     
	 */
	public void saveProjProcessStatus(String projectId,String projProcessStatus);
	
	/**
	 * FuncName:generatorProjectCodeByQO
	 * Description: 默认生成项目编号,Service层必须开启DB事务
	 * @param queryObject:为扩展参数,暂时不用,projBuyMethod:项目采购方式
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44  
	 */
	public String generatorProjectCodeByQO(QueryObject queryObject,String projBuyMethod)throws EsException;
	
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
	public Boolean checkEvalBid(String projectId, String subprojectId ,User user)throws Exception;
	
	/**
	 * FuncName:checkProjectTime
	 * Description:根据招标项目主键,对项目时间进行判断是否符合项目需求
	 * @param projectId
	 * @return Boolean
	 * @return  Boolean
	 * @author shenjianzhuang
	 * @Create Date:2010-8-9上午11:28:53 
	 * @Modifier  shenjianzhuang 
	 * @Modified Date: 2010-8-9下午13:25:53   
	 */	
	public Boolean checkProjectTime(String projectId)throws EsException; 
	
	/**
	 * FuncName:checkProjectImplstatus
	 * Description:根据招标项目Id，对项目状态进行判断是否符合项目需求
	 * @param projectId
	 * @return Boolean
	 * @return  Boolean
	 * @author shenjianzhuang
	 * @Create Date: 2010-8-10上午10:05:54 
	 * @Modifier    shenjianzhuang
	 * @Modified Date: 2010-8-10上午10:05:54  
	 */
	public Boolean checkProjectImplstatus(String projectId)throws EsException; 
	
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
	@SuppressWarnings("unchecked")
	public Page<Project> searchProjectByCurUser(QueryObject queryObject,Page<Project> page, User user);
	
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
	public ProjectRule getProjectRuleByProjectId(String projectId);
	
	/** 
	 * Description :  根据项目编号查询项目
	 * Create Date: 2011-3-16下午13:35:51 by zhouzhanghe
	 * @param  projCode 项目编号
	 * @return  项目对象
	 * @Exception   
	 */
	public Project findProjectByProjCode(String projCode);
	
	
	
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
	public Project findProjectByProjCodeAndParent(String projCode,String parentId);
	
}
