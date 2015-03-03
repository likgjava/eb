package com.gpcsoft.epp.project.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.ProjImplStatusEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectDepartment;
import com.gpcsoft.epp.project.domain.ProjectItemTend;
import com.gpcsoft.epp.project.domain.ProjectLinkGovMan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.SysInfo;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
@SuppressWarnings(value={"unchecked"})
public class ProjectDaoHibernate extends BaseGenericDaoHibernate<Project> implements ProjectDao {

	/**
	 * FuncName:saveProjectDepart
	 * Description:分配执行部门 
	 * @param projectDepartment:执行部门
	 * @return  ProjectDepartment
	 */
	public ProjectDepartment saveProjectDepart(ProjectDepartment projectDepartment) {
		return (ProjectDepartment)this.getHibernateTemplate().save(projectDepartment);
	}

	/**
	 * FuncName:saveProjectLinkMan
	 * Description: 指定经办人
	 * @param projectLinkGovMan:项目经办人
	 * @return ProjectLinkGovMan
	 */
	public ProjectLinkGovMan saveProjectLinkMan(ProjectLinkGovMan projectLinkGovMan) {
		return (ProjectLinkGovMan)this.getHibernateTemplate().save(projectLinkGovMan);
	}

	/**
	 * FuncName:queryProject
	 * Description:根据ID查询项目信息
	 * @param objId:项目主键,
	 * @return Page<Project>
	 */
	public Page<Project> queryProject(String objId,int start, int pageSize) {
		Page<Project> page=super.findbyHql("from Project where objId=?",start,pageSize,objId);
		return page;
	}

	/**
	 * FuncName:addConsign
	 * Description:提交委托
	 * @param  consign:委托协议
	 * @return Consign
	 */
	public Consign addConsign(Consign consign) {
		return (Consign)this.getHibernateTemplate().save(consign);
	}

	/** 
	 * FuncName:searchProject
	 * Description:根据查询条件获得相关的项目信息
	 * @param queryObject:组装的查询条件对象,page:分页对象
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:11:42   
	 * @Modifier Administrator
	 * @Modified Date: 2010-5-19上午10:11:42 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10
	 * @Modifier chenhj 增加查询按申报书查询招标项目列表,taskPlanId 
	 * @Modified Date: 2011-10-13 14:57 
	 */
	public Page<Project> searchProject(final QueryObject queryObject,final Page<Project> page) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//查询分页记录
				StringBuffer preHql = new StringBuffer("select t from Project t where 1=1 and  t.parentId is null ");
				preHql.append(" and t.sysFlag='"+CommonEnum._SYS_FLAG+"'");
				StringBuffer whereHql = new StringBuffer();
				List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
				if(queryList != null && !queryList.isEmpty()){
					for (int i = 0; i < queryList.size(); i++) {
						QueryParam queryParam = (QueryParam)queryList.get(i);
						if("ebuyMethod".equals(queryParam.getName())){
							whereHql.append(" and t.ebuyMethod = '"+(String)queryParam.getValue()+"' ");
						}
						if("projCode".equals(queryParam.getName())){
							whereHql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
						}
						if("projName".equals(queryParam.getName())){
							whereHql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
						}
						if("buyerId".equals(queryParam.getName())){//若有采购人ID，则需要把采购人ID过滤加入到里面
							
							//-----------------------------------------------begin----------------------------------------
							/*
							 * Description:由于把ProjectMTaskPlan.taskPlanSub属性改为存放TaskPlanSub、RecordForm的主键
							 * Modified at 2011-6-23 14:26 by zhouzhanghe
							   old:
									whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
									whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ");
									whereHql.append(") or t.objId in (select distinct t3.tproject.objId from ProjectMTaskPlan t3 where t3.taskPlanSub in ");
									whereHql.append("(select distinct t4.taskPlanSub.objId from TaskPlanMSub t4 where t4.taskPlan.objId in (select t5.objId from TaskPlan t5 where t5.budget.objId = '"+(String)queryParam.getValue()+"'))))");
							*/
							whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
							whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ))");
							//-----------------------------------------------end----------------------------------------
						}
						
						//add by chenhj 20111013 增加申报书ID查询招标项目连接
						if("taskPlanId".equals(queryParam.getName())){
							whereHql.append(" and t.objId in(select distinct spt.tproject.objId from SubProjectMTaskPlanSub spt where 1=1 ");
								whereHql.append(" and spt.taskPlan.objId='").append(String.valueOf(queryParam.getValue())).append("'")
							.append(" ) ");
						}
						
						if("agencies".equals(queryParam.getName())){//若有代理机构ID，则需要把代理机构ID过滤加入到里面
							whereHql.append(" and t.agencies.objId = '"+(String)queryParam.getValue()+"'  ");
						}
						if("manager".equals(queryParam.getName())){//若有项目经办人ID，则需要把项目经办人ID过滤加入到里面
							whereHql.append(" and t.manager.objId = '"+(String)queryParam.getValue()+"'  ");
						}
						if("superviseId".equals(queryParam.getName())){//若有监管机构ID，则需要把监管机构ID过滤加入到里面
							whereHql.append(" and t.monitor.objId = '"+(String)queryParam.getValue()+"'  ");
						}
					}
				}
				whereHql.append(" order by t.createTime desc, t.projCode desc ");
				Query query = session.createQuery(preHql.toString() + whereHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Project> projectList=query.list();
				page.setData(projectList);
				//查询总数
				StringBuffer countHql = new StringBuffer("select count(t.objId) from Project t where 1=1 and  t.parentId is null  ");
				countHql.append(" and t.sysFlag='"+CommonEnum._SYS_FLAG+"'");
				query = session.createQuery(countHql.toString() + whereHql.toString());
				page.setTotalRowNum((Long) query.list().get(0));
				return null;
		}});
		return page;
	}

	/**
	 * FuncName:searchAllProject
	 * Description:获取全部项目列表(不通过控件显示，所以不分页用LIST返回)
	 * @param  queryObject:组装的查询对象
	 * @return  List<Project> 
	 * @author guom
	 * @Create Date: 2010-6-7下午4:23:57 
	 * @Modifier guom
	 * @Modified Date: 2010-6-7下午4:23:57
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public List<Project> searchAllProject(QueryObject queryObject) {
		StringBuffer preHql = new StringBuffer("select t from Project t where 1=1 and  t.parentId is null ");
		preHql.append(" and t.sysFlag='"+CommonEnum._SYS_FLAG+"'");
		StringBuffer whereHql = new StringBuffer();
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if ("ebuyMethod".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.ebuyMethod = '"+(String)queryParam.getValue()+"' ");
				}
				if ("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if ("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if ("buyerId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有采购人ID，则需要把采购人ID过滤加入到里面
					//-----------------------------------------------begin----------------------------------------
					/*
					 * Description:由于把ProjectMTaskPlan.taskPlanSub属性改为存放TaskPlanSub、RecordForm的主键
					 * Modified at 2011-6-23 14:26 by zhouzhanghe
					   old:
							whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
							whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ");
							whereHql.append(") or t.objId in (select distinct t3.tproject.objId from ProjectMTaskPlan t3 where t3.taskPlanSub in ");
							whereHql.append("(select distinct t4.taskPlanSub.objId from TaskPlanMSub t4 where t4.taskPlan.objId in (select t5.objId from TaskPlan t5 where t5.budget.objId = '"+(String)queryParam.getValue()+"'))))");
					*/
					whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
					whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ))");
					//-----------------------------------------------end----------------------------------------
				}
				if("tenderType".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())){
					whereHql.append(" and t.tenderType = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("agencies".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有代理机构ID，则需要把代理机构ID过滤加入到里面
					whereHql.append(" and t.agencies.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("manager".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有项目负责人ID，则需要把项目负责人ID过滤加入到里面
					whereHql.append(" and t.manager.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("superviseId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有监管机构经办人ID，则需要把监管机构经办人ID过滤加入到里面
					whereHql.append(" and t.monitor.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("supplyId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有供应商ID，则需要把供应商ID过滤加入到里面
					whereHql.append(" and t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.project.objId")+" from SignUprecord ts where ts.supplier.objId='"+(String)queryParam.getValue()+"' )");
				}
				if ("governmentId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有业务处室ID，则需要把业务处室ID过滤加入到里面
					whereHql.append(" and t.objId in ");
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.tproject.objId")+" from ProjectMTaskPlan tm where tm.taskPlanSub in");//tm.taskPlanSub.objId改为tm.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ms.taskPlanSub.objId")+" from TaskPlanMSub ms where ms.taskPlan.objId in ");
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tp.objId")+" from TaskPlan tp where tp.government.objId = '"+(String)queryParam.getValue()+"'))) ");
				}
			}
		}
		whereHql.append(" order by t.createTime desc, t.projCode desc ");
		return this.findbyHql(preHql.toString() + whereHql.toString());
	}
	
	/** 
	 * FuncName:updateProjectAgency
	 * Description :更新项目的代理机构
	 * @param   projectId:项目主键,agencyId:代理机构主键
	 * @return  void
	 * @author    sunl
	 * @Create Date: 2010-6-18下午05:31:15 
	 * @Modifier    sunl
	 * @Modified Date: 2010-6-18下午05:31:15    
	 */
	public void updateProjectAgency(String projectId,String agencyId){
		StringBuffer hql = new StringBuffer();
		hql.append("update Project set agencies.objId = '" + agencyId + "' where objId = '" + projectId + "'");
		this.getSession().createQuery(hql.toString()).executeUpdate();
	}
	
	/** 
	 * FuncName:removeProjectMTaskPlan
	 * Description:删除包组条目中间表
	 * @param   projectId:项目主键
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-6-25上午10:25:43 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-25上午10:25:43    
	 */
	public void removeProjectMTaskPlan(final String projectId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = "delete SubProjectMTaskPlanSub where project.objId='"+projectId+"'";
				return session.createQuery(hql.toString()).executeUpdate();
			}});
	}
	
	/** 
	 * FuncName:getTotalProjectMTaskPlanByProjectId
	 * Description:根据项目ID获取统计分包数量
	 * @param   objId:项目主键
	 * @return  SubProjectMTaskPlanSub
	 * @author   yangx
	 * @Create Date: 2010-6-30下午03:48:07 
	 * @Modifier  yangx   
	 * @Modified Date: 2010-6-30下午03:48:07     
	 */
	public SubProjectMTaskPlanSub getTotalProjectMTaskPlanByProjectId(String objId){
		SubProjectMTaskPlanSub projectMTaskPlan = new SubProjectMTaskPlanSub();
		if(objId!=null&&!"".equals(objId)){
			/** 统计中间表中的总数量 */
			String hql = "select sum(t.quantity) as quantity from SubProjectMTaskPlanSub t where t.tproject.objId='"+objId+"'";
			List projectMTaskPlanlist= this.findbyHql(hql.toString());
			/** 判断是否有统计数量 */
			if(projectMTaskPlanlist!=null&&projectMTaskPlanlist.size()>0){
				projectMTaskPlan.setQuantity((BigDecimal)projectMTaskPlanlist.get(0));
			}
			return projectMTaskPlan;
		}
		return null;
	}
	
	/** 
	 * FuncName:getTotalTaskPlanSubByProjectId
	 * Description:根据项目ID获取统计条目数量
	 * @param   objId:项目主键
	 * @return  TaskPlanSub
	 * @author yangx
	 * @Create Date: 2010-6-30下午03:48:07 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-30下午03:48:07    
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public TaskPlanSub getTotalTaskPlanSubByProjectId(String objId){
		TaskPlanSub taskPlanSub = new TaskPlanSub();
		if(objId!=null&&!"".equals(objId)){
			/** 统计条目中的总数量 */
			
			//-----------------------------------------------begin----------------------------------------
			/*
			 * 从ProjectMTaskPlan.quantity中统计数量
			 * Modified at 2011-6-23 14:26 by zhouzhanghe
			StringBuffer sb = new StringBuffer();
			sb.append("select sum(t.quantity) from TaskPlanSub t where t.objId in");
			sb.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tt.taskPlanSub")+" from ProjectMTaskPlan tt where tt.tproject.objId='"+objId+"')");
			List taskPlanSublist= this.findbyHql(sb.toString());*/
			
			List taskPlanSublist= this.findbyHql("select sum(pmtp.quantity) from ProjectMTaskPlan pmtp where pmpt.tproject.objId = ?",new Object[]{objId});
			//-----------------------------------------------end----------------------------------------
			
			/** 判断是否有统计数量 */
			if(taskPlanSublist!=null&&taskPlanSublist.size()>0){
				taskPlanSub.setQuantity((BigDecimal)taskPlanSublist.get(0));
			}
			return taskPlanSub;
		}
		return null;
	}
	
	/** 
	 * FuncName:getSumTaskPlanDetailProjectId
	 * Description :根据项目主键采购预算总金额
	 * @param   projectId:项目主键
	 * @return  String
	 * @author yangx
	 * @Create Date: 2010-7-7下午04:14:47 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-7下午04:14:47  
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10    
	 */
	public String getSumTaskPlanDetailProjectId(String projectId){
		if(projectId==null||"".equals(projectId))return "0";
		/** 采购预算总金额 */

		
		//-----------------------------------------------begin----------------------------------------
		/*
		 * 从ProjectMTaskPlan.quantity中统计金额
		 * Modified at 2011-6-23 14:26 by zhouzhanghe
		StringBuffer hql = new StringBuffer();
		hql.append("select sum(t.totalPrice) from TaskPlanSub t");
		hql.append(" where t.objId in");
		hql.append(" (select tm.taskPlanSub from ProjectMTaskPlan tm where tm.tproject.objId='"+projectId+"')");
		*/
		List list= this.findbyHql("select sum(pmtp.budgetMoney) from ProjectMTaskPlan pmtp where pmtp.tproject.objId = ?",new Object[]{projectId});
		//-----------------------------------------------end----------------------------------------
		
		if(list!=null&&list.size()>0){
			return (list.get(0))!=null?(list.get(0)).toString():"0";
		}
		return "0";
	}
	
	/** 
	 * FuncName:getProjectBySubProjectId
	 * Description : 根据包组Id查询项目 
	 * @param   subProjectId:包组主键
	 * @return  Project
	 * @author yangx
	 * @Create Date: 2010-8-3下午01:54:00 
	 * @Modifier   yangx
	 * @Modified Date: 2010-8-3下午01:54:00  
	 */
	public Project getProjectBySubProjectId(String subProjectId){
		StringBuffer hql = new StringBuffer();
		hql.append("From Project t left join fetch t.agencies a where t.objId in");
		hql.append("(select p.parentId from Project p where p.objId='"+subProjectId+"')");
		List list = this.findbyHql(hql.toString());
		if(list!=null&&list.size()>0){
			return (Project)list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * FuncName:getTaskPlanSubBySubTenderId
	 * Description : 根据包件Id获得中间表数据
	 * @param   subTenderId:包件主键
	 * @return  SubProjectMTaskPlanSub
	 * @author caojz
	 * @Create Date: 2011-8-25上午09:34:57 
	 * @Modifier  caojz
	 * @Modified Date: 2011-8-25上午09:34:57 
	 */
	 public SubProjectMTaskPlanSub getTaskPlanSubBySubTenderId(String subTenderId) {
		List list = this.findbyHql(" from SubProjectMTaskPlanSub sub left join fetch sub.project p where p.objId=?", subTenderId);
		if(list!=null && list.size()>0){
			return (SubProjectMTaskPlanSub)list.get(0);
		}else{
			return null;
		}
	 }
	/**
	 * FuncName:getPackNamesByPackIds
	 * Description: 根据包件ID获取包件名称(多个名称逗号分隔)
	 * @param packIds:包件主键
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-8-11 上午11:23:11 
	 */
	public String getPackNamesByPackIds(final String[] packIds){
		return (String)getHibernateTemplate().execute(new HibernateCallback(){
			public String doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("select pack.projName,pack.projName from Project as pack where pack.objId in ( ");
				for(String s:packIds){
					hql.append("?,");
				}
				hql = new StringBuffer(hql.toString().substring(0,hql.toString().length()-1));
				hql.append(" ) ");
				Query query = session.createQuery(hql.toString());
				for(int i=0;i<packIds.length;i++){
					query.setString(i, packIds[i]);
				}
				StringBuffer packNames = new StringBuffer();
				List<Object[]> objects = query.list();
				if(null !=objects && objects.size()>0){
					for(Object[] object:objects){
						packNames.append(object[0]+",");
					}
					packNames = new StringBuffer(packNames.toString().substring(0,packNames.toString().length()-1));
				}
				return packNames.toString();
			}});
	}

	/**
	 * FuncName:getPackNumsByProjectId
	 * Description: 项目主键获得包件数量
	 * @param projectId:项目主键
	 * @return String
	 * @author liuke
	 * @Create Date 2010-8-11 上午11:23:11  
	 */
	public BigDecimal getPackNumsByProjectId(String projectId) {
		List list = this.findbyHql("select count(t) from Project t where t.parentId = ?", projectId);
		Object object = list.get(0);
		return new BigDecimal(object.toString());
	}
	
	/** 
	 * FuncName:removeProject
	 * Description:删除项目
	 * @param   projectId:项目主键
	 * @return void
	 * @author yangx 
	 * @Create Date: 2010-9-19下午02:03:04 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-19下午02:03:04    
	 */
	public void removeProject(final String projectId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String sql = "delete from ECP_TEND_M_TASK_P where TENDERID='"+projectId+"'";
				String hql4 = "delete from ProjMember pm  where pm.recordForm.objId =(select rf.objId from RecordForm rf where rf.project.objId='"+projectId+"')";
				String hql3 = "delete RecordForm where project.objId='"+projectId+"'";
				String hql = "delete ProjectMTaskPlan where tproject.objId='"+projectId+"'";
				String hql2 = "delete Project where objId='"+projectId+"'";
				session.createSQLQuery(sql).executeUpdate();
				session.createQuery(hql4).executeUpdate();
				session.createQuery(hql3).executeUpdate();
				session.createQuery(hql).executeUpdate();
				session.createQuery(hql2).executeUpdate();
				return null;
		}});
	}
	
	/** 
	 * FuncName:removeProjectRule
	 * Description:根据项目主键删除项目规则
	 * @param   projectId:项目主键
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-11-11上午11:32:23 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-11上午11:32:23   
	 */
	public void removeProjectRule(final String projectId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = "delete ProjectRule where project.objId='"+projectId+"'";
				session.createQuery(hql).executeUpdate();
				return null;
		}});
	}
	
	/** 
	 * FuncName:searchProjectByCurUser
	 * Description :  根据供应商当前用户和查询对象获得对应的参加过的招标项目
	 * @param queryObject:查询条件组装的对象,page:分页对象,user:当前登录用户
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-6-26上午11:41:11 
	 * @Modifier   Administrator
	 * @Modified Date: 2010-6-26上午11:41:11  
	 */
	public Page<Project> searchProjectByCurUser(QueryObject queryObject,Page<Project> page, User user) {
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Project t ");
		hql.append(" where (");
		hql.append(" t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.project.objId")+" from SignUprecord ts where ts.supplier.objId='"+orgInfo.getObjId()+"' )");
		hql.append(" or t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tp.objId")+" from Project tp where (tp.ebuyMethod='"+EbuyMethodEnum.INVITE_BIDDING+"' or tp.ebuyMethod='"+EbuyMethodEnum.SINGLE_SOURCE+"') and tp.parentId is null)");
		hql.append(" ) ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("ebuyMethod".equals(queryParam.getName())){
					hql.append(" and t.ebuyMethod = "+(String)queryParam.getValue()+" ");
				}
				if("projCode".equals(queryParam.getName())){
					hql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("projName".equals(queryParam.getName())){
					hql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
			}
		}
		hql.append(" order by t.createTime desc,t.projCode desc ");
		Page<Project> page1 = this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(), Project.class);
		return page1;
	}
	
	/** 
	 * FuncName:getPuaseProjectList
	 * Description :  获取暂停项目列表
	 * @param   page:分页对象,queryObject:查询条件组装的对象
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-11下午04:09:45 
	 * @Modifier   yangx 
	 * @Modified Date: 2010-10-11下午04:09:45    
	 */
	public Page<Project> getPuaseProjectList(Page page,QueryObject<Project> queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Project t where 1=1 ");
		hql.append(" and t.sysFlag='"+CommonEnum._SYS_FLAG+"'");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					hql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					hql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("agencies".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){//若有代理机构ID，则需要把代理机构ID过滤加入到里面
					hql.append(" and t.agencies.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if("manager".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){//若有项目经办人ID，则需要把项目经办人ID过滤加入到里面
					hql.append(" and t.manager.objId = '"+(String)queryParam.getValue()+"'  ");
				}
			}
		}
		hql.append(" and t.parentId is null and t.projImplStatus="+ProjImplStatusEnum.SUSPEND+"");
		hql.append(" and t.objId not in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("e.project.objId")+" from ProjectExceptionApply e where e.useStatus="+CommonEnum.USER_STATUS_FORMAL+")");
		hql.append(" order by t.createTime desc");
		return this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(), Project.class);
	}
	
	/** 
	 * FuncName:getPuaseProjectListForAudit
	 * Description :  获取待审核暂停项目列表
	 * @param   page:分页对象,queryObject:查询条件组装的对象
	 * @return  Page<Project>
	 * @author 	yangx
	 * @Create Date: 2010-10-11下午07:07:03 
	 * @Modifier   yangx
	 * @Modified Date: 2010-10-11下午07:07:03    
	 */
	public Page<Project> getPuaseProjectListForAudit(Page page,QueryObject<Project> queryObject){
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Project t where 1=1 ");
		hql.append(" and t.sysFlag='"+CommonEnum._SYS_FLAG+"'");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					hql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					hql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("superviseId".equals(queryParam.getName())){//若有监管机构ID，则需要把监管机构ID过滤加入到里面
					hql.append(" and t.monitor.objId = '"+(String)queryParam.getValue()+"'  ");
				}
			}
		}
		hql.append(" and t.objId in ");
		hql.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("e.project.objId")+" from ProjectExceptionApply e where e.useStatus="+CommonEnum.USER_STATUS_FORMAL+" and e.auditStatus="+AuditStatusEnum.WAIT_AUDIT+")");
		hql.append(" order by t.createTime desc");
		return this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(), Project.class);
	}
	
	/** 
	 * FuncName:searchProjectList
	 * Description:获取项目列表
	 * @param  queryObject[ebuyMethod：采购方式,projCode：项目编号,projName：项目名称,buyerId：采购人Id,agencies：代理机构Id,manager项目负责人Id,superviseId：监管机构经办人Id,supplyId：供应商Id,governmentId：业务处室Id]
	 * @param  page:分页对象
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-13下午04:54:23  
	 * @Modifier yangx
	 * @Modified Date: 2010-10-13下午04:54:23  
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10    
	 */
	public Page<Project> searchProjectList(QueryObject<Project> queryObject,Page page){
		StringBuffer preHql = new StringBuffer("select  t from Project t,ProjectRule pr where 1=1 and  t.parentId is null and t.objId=pr.project.objId ");
		preHql.append(" and t.sysFlag='"+CommonEnum._SYS_FLAG+"'");
		StringBuffer whereHql = new StringBuffer();
		StringBuffer supplyHql = new StringBuffer();
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		boolean anonymous = false;
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				
				//过滤备案状态
				if ("projImplStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projImplStatus = '"+(String)queryParam.getValue()+"' ");
				}
				//过滤备案状态
				if ("tenderRecord".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.tenderRecord = '"+(String)queryParam.getValue()+"' ");
				}

				//过滤创建人
				if ("createUser.objId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.createUser.objId = '"+(String)queryParam.getValue()+"' ");
				}
				
				if ("ebuyMethod".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.ebuyMethod = '"+(String)queryParam.getValue()+"' ");
				}
				//过滤状态
				if ("useStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.useStatus = '"+(String)queryParam.getValue()+"' ");
				}
				if ("projProcessStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					if("onging".equals((String)queryParam.getValue())){
						whereHql.append(" and (t.projProcessStatus not in ('"+ProjProcessStatusEnum.DRAFT_RESULT_NOTICES+"') or t.projProcessStatus is null)");
					}else 
						whereHql.append(" and t.projProcessStatus in ('"+ProjProcessStatusEnum.DRAFT_RESULT_NOTICES+"')");
				}
				if ("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if ("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if ("buyerId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有采购人ID，则需要把采购人ID过滤加入到里面
					
					//-----------------------------------------------begin----------------------------------------
					/*
					 * Description:由于把ProjectMTaskPlan.taskPlanSub属性改为存放TaskPlanSub、RecordForm的主键
					 * Modified at 2011-6-23 14:26 by zhouzhanghe
					   old:
							whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
							whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ");
							whereHql.append(") or t.objId in (select distinct t3.tproject.objId from ProjectMTaskPlan t3 where t3.taskPlanSub in ");
							whereHql.append("(select distinct t4.taskPlanSub.objId from TaskPlanMSub t4 where t4.taskPlan.objId in (select t5.objId from TaskPlan t5 where t5.budget.objId = '"+(String)queryParam.getValue()+"'))))");
					*/
					whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
					whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ))");
					//-----------------------------------------------end----------------------------------------
				}
				if("tenderType".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())){
					whereHql.append(" and t.tenderType = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("agencies".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有代理机构ID，则需要把代理机构ID过滤加入到里面
					whereHql.append(" and t.agencies.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("manager".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有项目负责人ID，则需要把项目负责人ID过滤加入到里面
					whereHql.append(" and t.manager.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				
				if ("superviseId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有监管机构经办人ID，则需要把监管机构经办人ID过滤加入到里面
					whereHql.append(" and t.monitor.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				
				
				if ("ruleAnonymous".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//非匿名投标项目，需要过滤报名信息
					if("1".equals((String)queryParam.getValue())) {
						anonymous = true;
						whereHql.append(" and pr.ruleAnonymous='"+(String)queryParam.getValue()+"' ");
					}
				}
				
				if (!anonymous && "supplyId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有供应商ID，则需要把供应商ID过滤加入到里面
					whereHql.append(" and(");
					whereHql.append(" t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.project.objId")+" from SignUprecord ts where ts.supplier.objId='"+(String)queryParam.getValue()+"') ");
					whereHql.append(" or t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts2.project.parentId")+" from SignUprecord ts2 where ts2.supplier.objId='"+(String)queryParam.getValue()+"') ");
					whereHql.append(" )");
					
				}
				
				
				//add by chenhj 20111013 增加申报书ID查询招标项目连接
				if("taskPlan.objId".equals(queryParam.getName())){
					whereHql.append(" and t.objId in(select distinct spt.tproject.objId from ProjectMTaskPlan spt ,TaskPlanMSub ts where 1=1 ");
						whereHql.append(" and  spt.taskPlanSub=ts.taskPlanSub.objId and  ts.taskPlan.objId='").append(String.valueOf(queryParam.getValue())).append("'")
					.append(" ) ");
				}
				
				if("resProjectId".equals(queryParam.getName())){
					whereHql.append(" and t.resProjectId='").append(queryParam.getValue()).append("'");
				}
				//ECP_TEND_M_TASK
				
				if ("governmentId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有业务处室ID，则需要把业务处室ID过滤加入到里面
					whereHql.append(" and t.objId in ");
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.tproject.objId")+" from ProjectMTaskPlan tm where tm.taskPlanSub in");//tm.taskPlanSub.objId改为tm.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ms.taskPlanSub.objId")+" from TaskPlanMSub ms where ms.taskPlan.objId in ");
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tp.objId")+" from TaskPlan tp where tp.government.objId = '"+(String)queryParam.getValue()+"'))) ");
				}
			}
		}
		whereHql.append(" order by t.createTime desc, t.projCode desc ");
		return this.findbyHql(preHql.toString() + whereHql.toString(),page.getStart(),page.getPageSize(),Project.class);
	}
	
	/** 
	 * FuncName:statisticsEbuyMethod
	 * Description :  取得采购方式的统计数据（5种采购方式的项目数）
	 * @param   queryObject:组装的查询条件对象
	 * @return List
	 * @author yangx  
	 * @Create Date: 2010-10-15下午04:32:56 
	 * @Modifier   yangx 
	 * @Modified Date: 2010-10-15下午04:32:56    
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10   
	 */
	public List statisticsEbuyMethod(final QueryObject queryObject){
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public List doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql=new StringBuffer();
				StringBuffer whereHql = new StringBuffer();
				hql.append("select count(*),t.ebuyMethod from Project t where t.parentId is null and t.ebuyMethod is not null ");
				hql.append(" and t.sysFlag='"+CommonEnum._SYS_FLAG+"'");
				List<QueryParam> queryList = null;
				if (queryObject!=null) {
					queryList = queryObject.getQueryParam().getAndParams();
				}
				if(queryList != null && !queryList.isEmpty()){
					for (int i = 0; i < queryList.size(); i++) {
						QueryParam queryParam = (QueryParam)queryList.get(i);
						if ("buyerId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有采购人ID，则需要把采购人ID过滤加入到里面
							
							//-----------------------------------------------begin----------------------------------------
							/*
							 * Description:由于把ProjectMTaskPlan.taskPlanSub属性改为存放TaskPlanSub、RecordForm的主键
							 * Modified at 2011-6-23 14:26 by zhouzhanghe
							   old:
									whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
									whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ");
									whereHql.append(") or t.objId in (select distinct t3.tproject.objId from ProjectMTaskPlan t3 where t3.taskPlanSub in ");
									whereHql.append("(select distinct t4.taskPlanSub.objId from TaskPlanMSub t4 where t4.taskPlan.objId in (select t5.objId from TaskPlan t5 where t5.budget.objId = '"+(String)queryParam.getValue()+"'))))");
							*/
							whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
							whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ))");
							//-----------------------------------------------end----------------------------------------
							
						}
						if ("agencies".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有代理机构ID，则需要把代理机构ID过滤加入到里面
							whereHql.append(" and t.agencies.objId = '"+(String)queryParam.getValue()+"'  ");
						}
						if ("manager".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有项目负责人ID，则需要把项目负责人ID过滤加入到里面
							whereHql.append(" and t.manager.objId = '"+(String)queryParam.getValue()+"'  ");
						}
						if ("superviseId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有监管机构经办人ID，则需要把监管机构经办人ID过滤加入到里面
							whereHql.append(" and t.monitor.objId = '"+(String)queryParam.getValue()+"'  ");
						}
						if ("supplyId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有供应商ID，则需要把供应商ID过滤加入到里面
							whereHql.append(" and t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.project.objId")+" from SignUprecord ts where ts.supplier.objId='"+(String)queryParam.getValue()+"' )");
						}
						if ("governmentId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有业务处室ID，则需要把业务处室ID过滤加入到里面
							whereHql.append(" and t.objId in ");
							whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.tproject.objId")+" from ProjectMTaskPlan tm where tm.taskPlanSub in");//tm.taskPlanSub.objId改为tm.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
							whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ms.taskPlanSub.objId")+" from TaskPlanMSub ms where ms.taskPlan.objId in ");
							whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tp.objId")+" from TaskPlan tp where tp.government.objId = '"+(String)queryParam.getValue()+"'))) ");
						}
					}
				}
				whereHql.append(" group by t.ebuyMethod");
				Query query = session.createQuery(hql.toString() + whereHql.toString());
				List list = query.list();
				return list;
			}});
	}
	
	/** 
	 * FuncName:getProjectRuleByProjectId
	 * Description :  根据项目Id获取项目规则
	 * @param   projectId：项目Id
	 * @return  ProjectRule
	 * @author yangx
	 * @Create Date: 2010-11-10下午05:04:41 
	 * @Modifier  yangx
	 * @Modified Date: 2010-11-10下午05:04:41     
	 */
	public ProjectRule getProjectRuleByProjectId(String projectId){
		StringBuffer hql = new StringBuffer();
		hql.append("from ProjectRule t where t.project.objId = '"+projectId+"'  )");
		List list = this.findbyHql(hql.toString());
		return (list!=null&&list.size()>0)?(ProjectRule)list.get(0):null;
	}
	
	/** 
	 * FuncName: getProjectByNameOrCode  
	 * Description : 根据包组名称或编号查询项目下的包组
	 * Create Date: 2011-1-19下午08:43:54 by yangx  Modified Date: 2011-1-19下午08:43:54 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Project> getProjectByNameOrCode(QueryObject<Project> queryObject){
		StringBuffer hql = new StringBuffer("From Project t where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if ("code".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//包组编号
					hql.append(" and t.projCode = '"+(String)queryParam.getValue()+"' ");
				}
				if ("name".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//包组名称
					hql.append(" and t.projName = '"+(String)queryParam.getValue()+"' ");
				}
				if ("projectId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//项目Id
					hql.append(" and t.parentId = '"+(String)queryParam.getValue()+"' ");
				}
				if ("subProjectId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//项目Id
					hql.append(" and t.objId != '"+(String)queryParam.getValue()+"' ");
				}
			}
		}
		return this.findbyHql(hql.toString());
	}
	
	
	/** 
	 * Description :  根据项目Id获取分包总金额
	 * Create Date: 2011-3-9下午03:17:58 by yangx  Modified Date: 2011-3-9下午03:17:58 by yangx
	 * @param   projectId：项目ID
	 * @return  String
	 * @Exception   
	 */
	public String getSumSProjectMTSByProjectId(String projectId){
		if(projectId==null||"".equals(projectId))return "0";
		/** 采购预算总金额 */
		StringBuffer hql = new StringBuffer();
		hql.append("select sum(tm.money) from SubProjectMTaskPlanSub tm where tm.tproject.objId='"+projectId+"'");
		List list = this.findbyHql(hql.toString());
		if(list!=null&&list.size()>0){
			return (list.get(0))!=null?(list.get(0)).toString():"0";
		}
		return "0";
	}
	
	
	/**
	 * 
	 * FuncName: getSubProjectMTaskPlanSubByPackId
	 * Description :  创建或修改对象
	 * @param 
	 * @param packId
	 * @return SubProjectMTaskPlanSub
	 * @author: liuke
	 * @Create Date:2011-2-23  下午05:26:27
	 * @Modifier: liuke
	 * @Modified Date:2011-2-23  下午05:26:27
	 */
	public SubProjectMTaskPlanSub getSubProjectMTaskPlanSubByPackId(
			String packId) {
		List list = this.findbyHql("from SubProjectMTaskPlanSub t where t.project.objId = ?", packId);
		return (list.size()>0)? (SubProjectMTaskPlanSub)list.get(0):null;
	}
	/** 
	 * FuncName:searchProjectList
	 * Description获取项目列表数目
	 * @param  queryObject[ebuyMethod：采购方式,projCode：项目编号,projName：项目名称,buyerId：采购人Id,agencies：代理机构Id,manager项目负责人Id,superviseId：监管机构经办人Id,supplyId：供应商Id,governmentId：业务处室Id]
	 * @param  page:分页对象
	 * @return  Page<Project>
	 * @author shenjz
	 * @Create Date: 2011-4-8上午9:54:23  
	 * @Modifier shenjz
	 * @Modified Date: 2011-4-8上午9:54:23  
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10    
	 */
	public Long searchProjectCount(QueryObject<Project> queryObject){
		StringBuffer preHql = new StringBuffer("select count(*) from Project t where 1=1 and  t.parentId is null");
		preHql.append(" and t.sysFlag='"+CommonEnum._SYS_FLAG+"'");
		StringBuffer whereHql = new StringBuffer();
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if ("projProcessStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projProcessStatus = '"+(String)queryParam.getValue()+"' ");
				}
				if ("ebuyMethod".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.ebuyMethod = '"+(String)queryParam.getValue()+"' ");
				}
				if ("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if ("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if ("buyerId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有采购人ID，则需要把采购人ID过滤加入到里面
					//-----------------------------------------------begin----------------------------------------
					/*
					 * Description:由于把ProjectMTaskPlan.taskPlanSub属性改为存放TaskPlanSub、RecordForm的主键
					 * Modified at 2011-6-23 14:26 by zhouzhanghe
					   old:
							whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
							whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ");
							whereHql.append(") or t.objId in (select distinct t3.tproject.objId from ProjectMTaskPlan t3 where t3.taskPlanSub in ");
							whereHql.append("(select distinct t4.taskPlanSub.objId from TaskPlanMSub t4 where t4.taskPlan.objId in (select t5.objId from TaskPlan t5 where t5.budget.objId = '"+(String)queryParam.getValue()+"'))))");
					*/
					whereHql.append(" and (t.objId in (select distinct t2.tproject.objId from ProjectMTaskPlan t2 where 1=1 ");
					whereHql.append(" and t2.buyMainBody.objId = '"+(String)queryParam.getValue()+"'  ))");
					//-----------------------------------------------end----------------------------------------
				}
				if("tenderType".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())){
					whereHql.append(" and t.tenderType = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("agencies".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有代理机构ID，则需要把代理机构ID过滤加入到里面
					whereHql.append(" and t.agencies.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("manager".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有项目负责人ID，则需要把项目负责人ID过滤加入到里面
					//edit by shenjz 2011-6-1
					whereHql.append(" and t.manager.objId = '"+(String)queryParam.getValue()+"'  ");
					whereHql.append(" or t.monitor.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("superviseId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有监管机构经办人ID，则需要把监管机构经办人ID过滤加入到里面
					whereHql.append(" and t.monitor.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if ("supplyId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有供应商ID，则需要把供应商ID过滤加入到里面
					//edit by shenjz 2011-6-1
					whereHql.append(" and t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.project.objId")+" from SignUprecord ts where ts.supplier.objId='"+(String)queryParam.getValue()+"' )");
					whereHql.append(" or t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.project.parentId")+" from SignUprecord ts where ts.supplier.objId='"+(String)queryParam.getValue()+"' )");
				}
				if ("governmentId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//若有业务处室ID，则需要把业务处室ID过滤加入到里面
					whereHql.append(" and t.objId in ");
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.tproject.objId")+" from ProjectMTaskPlan tm where tm.taskPlanSub in");//tm.taskPlanSub.objId改为tm.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ms.taskPlanSub.objId")+" from TaskPlanMSub ms where ms.taskPlan.objId in ");
					whereHql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tp.objId")+" from TaskPlan tp where tp.government.objId = '"+(String)queryParam.getValue()+"'))) ");
				}
			}
		}
		List list = this.findbyHql(preHql.toString() + whereHql.toString());
		return (list.isEmpty())? 0:(Long) list.get(0);
	}
	/**
	 * FuncName: findProjectByProjCodeBy
	 * Description :  通过项目编号获取项目
	 * @param 
	 * @param projCode
	 * @param packCode
	 * @return Project
	 * @author: shenjz
	 * @Create Date:2011-5-17  上午10:25:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-17  上午10:25:40
	 */
	public Project findProjectByProjCode(String projCode) {
		String hql = "from Project where projCode = '"+projCode+"'";
		List list = this.findbyHql(hql);
		return (list.isEmpty())?null:(Project)list.get(0);
	}
	/**
	 * FuncName: findProjectByProjCodeBy
	 * Description :  通过包组编号和项目编号获取到项目包组
	 * @param 
	 * @param projCode
	 * @param packCode
	 * @return Project
	 * @author: shenjz
	 * @Create Date:2011-5-17  上午10:25:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-17  上午10:25:40
	 */
	public Project findProjectByProjCodeBy(String projCode,String packCode){
		String hql = "from Project p1 where p1.projCode = '"+packCode+"' and p1.parentId =(select p2.objId from Project p2 where p2.projCode='"+projCode+"')";
		List list = this.findbyHql(hql);
		return (list.isEmpty())?null:(Project)list.get(0);
	}
	/**
	 * FuncName: getProject
	 * Description :  获取到一个项目下的包组集合包组
	 * @param 
	 * @param projCode
	 * @return List<Project>
	 * @author: shenjz
	 * @Create Date:2011-5-18  上午11:53:11
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-18  上午11:53:11
	 */
	public List<Project> getSubProjectList(String projCode){
		String hql = "from Project p1 where p1.parentId =(select p2.objId from Project p2 where p2.projCode='"+projCode+"')";
		return findbyHql(hql);
	}
	/**
	 * FuncName: getProjectNumber
	 * Description :  获取品目分类项目数目 
	 * @param 
	 * @param itemsId
	 * @return Long
	 * @author: shenjz
	 * @Create Date:2011-6-28  上午09:55:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28  上午09:55:31
	 */
	public Long getProjectNumber(String itemsId){
		User user = AuthenticationHelper.getCurrentUser();
		String hql = "select count(*) from Project p where p.purCategoryIds like '%"+itemsId+"%' and p.parentId is null"; 
		 hql += " and p.agencies.objId = '"+user.getOrgInfo().getObjId()+"' " ;
		List list = this.findbyHql(hql) ;
		return (list.isEmpty())? 0:(Long) list.get(0);
	}
	/**
	 * FuncName: getProjectNumber
	 * Description :  获取采购方式和项目类型项目数目 
	 * @param 
	 * @param itemsId
	 * @return Long
	 * @author: shenjz
	 * @Create Date:2011-6-28  上午09:55:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28  上午09:55:31
	 */
	public Long getProjectEbuyMethodNumber(String ebuyMethod,String tenderType,String year){
		User user = AuthenticationHelper.getCurrentUser();
		String hql = "select count(*) from Project p where p.ebuyMethod = '"+ebuyMethod+"' and p.tenderType='"+tenderType+"' and p.parentId is null "; 
		  	   hql += " and p.agencies.objId = '"+user.getOrgInfo().getObjId()+"' " ;
		if(year!=null){
			hql += " and p.createTime <=to_date('"+year+"-12-31','yyyy-mm-dd')";
		}
		List list = this.findbyHql(hql) ;
		return (list.isEmpty())? 0:(Long) list.get(0);
	}
	/**
	 * FuncName: getProjectNumber
	 * Description :  获取采购方式项目数目  
	 * @param 
	 * @param itemsId
	 * @return Long
	 * @author: shenjz
	 * @Create Date:2011-6-28  上午09:55:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28  上午09:55:31
	 */
	public Long getProjectEbuyMethodNumber2(String ebuyMethod,String year){
		User user = AuthenticationHelper.getCurrentUser();
		String hql = "select count(*) from Project p where p.ebuyMethod = '"+ebuyMethod+"' and p.parentId is null "; 
			   hql += " and p.agencies.objId = '"+user.getOrgInfo().getObjId()+"' " ;
		if(year!=null){
			hql += " and p.createTime <=to_date('"+year+"-12-31','yyyy-mm-dd')";
		}
		List list = this.findbyHql(hql) ;
		return (list.isEmpty())? 0:(Long) list.get(0);
	}
	
	/**
	 * FuncName: getProjectPurCategoryNumber
	 * Description :  获取到品目采购分类该类别下的数目
	 * @param year
	 * @return Long
	 * @author: shenjz
	 * @Create Date:2011-6-29  上午09:42:03
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-29  上午09:42:03
	 */
	public List getProjectPurCategoryNumber(String year){
		StringBuffer sb = new StringBuffer();
		sb.append("select t.purchaseName,sum(t.quantity) from TaskPlanSub t where t.objId in (select p.taskPlanSub from ProjectMTaskPlan p where 1=1 ");
		if(year!=null){
			sb.append(" and p.tproject.createTime <=to_date('"+year+"-12-31','yyyy-mm-dd')");
		}
		sb.append(" ) group by t.purchaseName order by sum(t.quantity) desc");
		List list = this.findbyHql(sb.toString()) ;
		return list;
	}
	/**
	 * FuncName: searchProjectListForEntryBailRecord
	 * Description :  查询待录入保证金的项目
	 * @param 
	 * @param queryObject
	 * @param page
	 * @return Page<Project>
	 * @author: liuke
	 * @Create Date:2011-8-10  下午01:51:21
	 * @Modifier: liuke
	 * @Modified Date:2011-8-10  下午01:51:21
	 */
	public Page<Project> searchProjectListForEntryBailRecord(
			QueryObject<Project> queryObject, Page<Project> page) {
		StringBuffer preHql = new StringBuffer(" from Project t where 1=1 and  t.parentId is null");
		StringBuffer whereHql = new StringBuffer();
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if ("projectIds".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.objId in ('"+(String)queryParam.getValue()+"') ");
				}
				if ("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if ("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					whereHql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
			}
		}
		return this.findbyHql(preHql.toString() + whereHql.toString(),page.getStart(),page.getPageSize(),Project.class);
	}
	
	/** 
	 * FuncName : getSubProjectByProjectId
	 * Description :  根据项目Id获取包组
	 * Create Date: 2011-10-11下午04:44:00 by yangx  
	 * Modified Date: 2011-10-11下午04:44:00 by yangx
	 * Modified Date: 2011-11-06下午04:44:00 by chenhj  修改增加按分包编号排序
	 * @param   projectId：项目Id
	 * @return  List<Project>
	 * @Exception   
	 */
	public List<Project> getSubProjectByProjectId(String projectId){
		return this.findbyHql("from Project t where t.parentId='"+projectId+"' order by t.projCode");
	}
	
	
	
	/** 
	 * FuncName : getProjectImplStatusByProjectId
	 * Description :  根据项目Id获取项目实施状态[00：正常;01:暂停;02:终止;]
	 * Create Date: 2011-11-09上午09:56:00 by yangyc  
	 * @param   projectId：项目Id
	 * @return  List<Project>
	 * @Exception   
	 */
	public String getProjectImplStatusByProjectId(String projectId) {
		Project project =  this.findbyHql("from Project t where t.objId='"+projectId+"'").get(0);
		return project.getProjImplStatus();
	}
	

	/**
	 * FuncName : getSubProjectCountByProjectId
	 * Description :  根据项目Id获取包组数量
	 * @param projectId
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-10 14:28
	 */
	public short getSubProjectCountByProjectId(String projectId) {
		String hql = "select count(*) from Project p where p.parentId = ?"; 
		List list = this.findbyHql(hql,projectId) ;
		return (list.isEmpty())? 0:(short) Short.parseShort(list.get(0).toString());
	}

	/** 
	 * FuncName : getProjectIdByPurchaseDocId
	 * Description :  根据招标文件Id获取项目
	 * Create Date: 2011-10-20  11:30  by caojz  
	 * Modified Date: 2011-10-20 11:30 by caojz
	 * @param   purchaseId
	 * @return  Project
	 * @Exception   
	 */
	public  Project getProjectIdByPurchaseDocId(String purchaseId){
		 List list =  this.findbyHql("select pd.project from PurchaseDoc pd where pd.objId='"+purchaseId+"'");
		 return (list.isEmpty())?null:(Project)list.get(0);
	}
}
