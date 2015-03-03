package com.gpcsoft.epp.project.dao.hibernate;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.dao.ProjectMTaskPlanDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class ProjectMTaskPlanDaoHibernate extends BaseGenericDaoHibernate<ProjectMTaskPlan> implements ProjectMTaskPlanDao {
    
	/**
	 * FuncName:removeProjectMTaskPlanByProjectId
	 * Description :根据项目主键删除中间表数据  
s	 * @param   projectId:项目主键
	 * @return void
	 * @author liuke  
	 * @Create Date: 2010-7-7下午01:08:31 
	 * @Modifier  liuke
	 * @Modified Date: 2010-7-7下午01:08:31 
	 */
	@SuppressWarnings("unchecked")
	public void removeProjectMTaskPlanByProjectId(final String projectId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="delete from ProjectMTaskPlan pmtp where pmtp.tproject.objId = '"+projectId+"'";
				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
	}
    
	/**
	 * FuncName:reductionTaskPlanSubByProjectId
	 * Description :根据项目主键设置申报书大宗状态为null  
	 * @param   projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-7-7下午01:26:14 
	 * @Modifier    liuke
	 * @Modified Date: 2010-7-7下午01:26:14 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	@SuppressWarnings("unchecked")
	public void reductionTaskPlanSubByProjectId(final String projectId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
			    StringBuffer hql = new StringBuffer();
				hql.append("update TaskPlan tp set tp.block_check_status = null where tp.objId in");
				hql.append("(select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tpms.taskPlan.objId")+" from TaskPlanMSub tpms where tpms.taskPlanSub.objId in");
				hql.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("pmtp.taskPlanSub")+" from ProjectMTaskPlan pmtp where pmtp.tproject.objId = '"+projectId+"'))");//pmtp.taskPlanSub.objId改为pmtp.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
				Query query = session.createQuery(hql.toString());
				return query.executeUpdate();
			}
		});
	}

	/**
	 * FuncName:removeProjectMTaskPlanByBytaskPlanIdAndProjectId
	 * Description :根据项目主键和申报书主键删除中间表对象
	 * @param   taskPlanId:申报书主键,projectId:项目主键
	 * @return  void
	 * @author 		liuke
	 * @Create Date: 2010-7-7下午01:26:14 
	 * @Modifier    liuke
	 * @Modified Date: 2010-7-7下午01:26:14
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	@SuppressWarnings("unchecked")
	public void removeProjectMTaskPlanByBytaskPlanIdAndProjectId(final	String taskPlanId, final String projectId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
			    hql.append("delete from ProjectMTaskPlan pmtp where pmtp.tproject.objId ='"+projectId+"' and pmtp.taskPlanSub in");//pmtp.taskPlanSub.objId改为pmtp.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
				hql.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tpms.taskPlanSub.objId")+" from TaskPlanMSub tpms where tpms.taskPlan.objId ='"+taskPlanId+"')");
				Query query = session.createQuery(hql.toString());
				return query.executeUpdate();
			}
		});
	}

	/**
	 * FuncName:getProjectMTaskPlanByQueryObject
	 * Description :通过QueryObject对象得到中间表对象
	 * @param   queryObject:查询条件组装的对象
	 * @return  List<ProjectMTaskPlan>
	 * @author 	liuke
	 * @Create Date: 2010-7-28上午10:04:38 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-28上午10:04:38 by 
	 */
	@SuppressWarnings("unchecked")
	public List<ProjectMTaskPlan> getProjectMTaskPlanByQueryObject(QueryObject<ProjectMTaskPlan> queryObject) {
		List<ProjectMTaskPlan> list = this.findByQuery(queryObject);
		return list;
	}
    
	/**
	 * FuncName: getProjectListByTaskPlan
	 * Description :  创建或修改对象
	 * @param page:分页对象,auditStatus:审核状态,useStatus:临时状态
	 * @return Page
	 * @author: shenjz
	 * @Create Date:2011-1-5  下午03:45:17
	 * @Modifier: shenjz
	 * @Modified Date:2011-1-5  下午03:45:17
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public Page getProjectListByTaskPlan(Page page,String auditStatus,String useStatus){
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct pmtp.tproject from ProjectMTaskPlan pmtp where pmtp.tproject.auditStatus = ? and pmtp.tproject.useStatus = ? and  pmtp.taskPlanSub in");//pmtp.taskPlanSub.objId改为pmtp.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		hql.append("(select  tpms.taskPlanSub.objId  from TaskPlanMSub tpms where tpms.taskPlan.objId in");
		hql.append("(select  tp.objId  from TaskPlan tp  where tp.block_check_status = '00' ))");
		Page pages = this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(),auditStatus,useStatus);
		return pages;
	}
	/**
	 * FuncName: getProjectMTaskPlan
	 * Description :  根据项目逐渐获得招标项目关联申报书条目表
	 * @param 
	 * @param projectId
	 * @return ProjectMTaskPlan
	 * @author: shenjz
	 * @Create Date:2011-3-29  下午05:03:17
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-29  下午05:03:17
	 */
	public ProjectMTaskPlan getProjectMTaskPlan(String projectId){
		StringBuffer sb = new StringBuffer();
		sb.append("from ProjectMTaskPlan t where t.tproject.objId = '"+projectId+"' ");
		ProjectMTaskPlan p  =   this.findbyHql(sb.toString()).get(0);
		return p;
	}
	
	/** 
	 * FuncName:searchProjectList
	 * Description:所有项目的总金额
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
	public Double searchProjectMoney(QueryObject<Project> queryObject){
		StringBuffer preHql = new StringBuffer("from ProjectMTaskPlan t1 where t1.tproject.objId in (select t.objId from Project t where 1=1 and  t.parentId is null");
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
							whereHql.append(") or t.objId in (select distinct t3.tproject.objId from ProjectMTaskPlan t3 where t3.taskPlanSub.objId in ");
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
		whereHql.append(")");
		List<ProjectMTaskPlan> list = this.findbyHql(preHql.toString() + whereHql.toString());
		Double total = 0d;
		for (Iterator<ProjectMTaskPlan> iterator = list.iterator(); iterator.hasNext();) {
			ProjectMTaskPlan projectMTaskPlan = (ProjectMTaskPlan) iterator.next();
			/*
			 * Modified at 2011-6-23 by zhouzhanghe
			 * old:total+= Double.parseDouble(projectMTaskPlan.getTaskPlanSub().getTotalPrice().toString());
			 */
			total+= Double.parseDouble((projectMTaskPlan.getBudgetMoney() == null ? "0" : projectMTaskPlan.getBudgetMoney()).toString());
		}
		return total;
	}
	/**
	 * FuncName: searchProjectMoney2
	 * Description : 根据项目的创建日期获取某月的项目资金总金额
	 * @param 
	 * @param date1
	 * @param date2
	 * @return Double
	 * @author: shenjz
	 * @Create Date:2011-6-28  上午11:33:33
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28  上午11:33:33
	 */
	public List<ProjectMTaskPlan> searchProjectMoney2(String date1,String date2){
		StringBuffer preHql = new StringBuffer("from ProjectMTaskPlan t1  where 1=1 and  t1.tproject.parentId is null and  t1.tproject.createTime>=to_date('"+date1+"','yyyy-mm-dd') and t1.tproject.createTime<=to_date('"+date2+"','yyyy-mm-dd') ");
		List<ProjectMTaskPlan> list = this.findbyHql(preHql.toString());
//		Double total = 0d;
//		for (Iterator<ProjectMTaskPlan> iterator = list.iterator(); iterator.hasNext();) {
//			ProjectMTaskPlan projectMTaskPlan = (ProjectMTaskPlan) iterator.next();
//			total+= Double.parseDouble((projectMTaskPlan.getBudgetMoney() == null ? "0" : projectMTaskPlan.getBudgetMoney()).toString());
//		}
		return list;
	}
}
