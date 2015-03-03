/**
 * 
 */
package com.gpcsoft.pubservice.webService.webService.gxoa.ws.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.pubservice.webService.webService.gxoa.ws.dao.ProjectExtDao;

/**
 * @author Administrator
 *
 */
@Repository
public class ProjectExtDaoHibernate extends BaseGenericDaoHibernate<Project> implements ProjectExtDao {

	/** 
	 * Description :修改项目计划  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateProjectPlan(final String projectId,final String code, final String status) throws Exception{
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				Integer result = -1;
				sql.append("update ECP_PROJECT_PLAN t set t.PROCESS_STATUS = '").append(status).append("' where 1=1 ");
				sql.append("and t.PROJECT_ID = '").append(projectId).append("' and t.TASK_NO like '").append(code).append("%'");
				Query query = session.createSQLQuery(sql.toString());
				result = query.executeUpdate();
				return result;
			}
		});
	}
	
	/** 
	 * Description :清理立项数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void removeStepProject(final String projCode) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				//删除项目规则
				String hql1 = "DELETE FROM ECP_TEND_RULE T WHERE T.tender_id=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"')";
				//删除项目计划
				String hql2 = "DELETE FROM ECP_PROJECT_PLAN T WHERE T.PROJECT_ID=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"')";
				//删除项目
				String hql3 = "delete from ECP_TENDER_PROJECT where tenderno='"+projCode+"'";
				//删除项目任务书关联关系
				String hql4 = "DELETE FROM ECP_TEND_M_TASK T WHERE T.TENDERID=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"')";
				session.createSQLQuery(hql1).executeUpdate();
				session.createSQLQuery(hql2).executeUpdate();
				session.createSQLQuery(hql3).executeUpdate();
				session.createSQLQuery(hql4).executeUpdate();
				return null;
		}});
	}
	/** 
	 * Description :清理招标文件环节的数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void removeStepPurchaseFile(final String projCode) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				//删除招标文件下载记录（有外键引用）
				String hql1 = "DELETE from ECP_PROCFILE_ATT  T WHERE  T.FILEID = (SELECT T.FILEID FROM ECP_Tender_ProcFile T WHERE T.tenderid=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"'))";
				//删除招标文件
				String hql2 = "DELETE FROM ECP_Tender_ProcFile T WHERE T.tenderid=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"')";
				//删除包件
				String hql3 = "DELETE FROM ecp_tender_project t WHERE t.parent_id=(SELECT p.tenderid FROM ecp_tender_project p WHERE p.tenderno='"+projCode+"')";
				//删除标段关联申报书条目
				String hql4 = "DELETE FROM ECP_TEND_M_TASK_P T WHERE T.tenderid=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"')";
				//删除项目规则
				String hql5 = "DELETE FROM ECP_PROJ_PROCESSRULE T WHERE T.proj_id=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"')";
				session.createSQLQuery(hql1).executeUpdate();
				session.createSQLQuery(hql2).executeUpdate();
				session.createSQLQuery(hql3).executeUpdate();
				session.createSQLQuery(hql4).executeUpdate();
				session.createSQLQuery(hql5).executeUpdate();
				return null;
		}});
	}
	
	/** 
	 * Description :清理招标公告环节数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void removeStepBulletin(final String projCode) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				//删除预订开评标室数据
				String hql1 = "DELETE FROM ECP_BID_ROOM T WHERE T.proj_pack_id=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"')";
				//删除公告数据
				String hql2 = "DELETE FROM ECP_Base_Bulletin T WHERE T.tenderid=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"')";
				session.createSQLQuery(hql1).executeUpdate();
				session.createSQLQuery(hql2).executeUpdate();
				return null;
		}});
	}
	
	/** 
	 * Description :  根据项目编号清理澄清公告
	 * Create Date: 2011-10-24下午02:10:12 by sunl  Modified Date: 2011-10-24下午02:10:12 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void removeBulletinByProjCode(final String projCode,final String bulletinType) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = "DELETE FROM ECP_Base_Bulletin T WHERE T.tenderid=(SELECT TENDERID FROM ECP_tender_project p where p.tenderno='"+projCode+"') and t.bulletin_type='"+bulletinType+"'";
				session.createSQLQuery(hql).executeUpdate();
				return null;
		}});
	}
	
	/** 
	 * Description :  删除机构（置为无效状态）
	 * Create Date: 2011-10-25上午10:21:33 by sunl  Modified Date: 2011-10-25上午10:21:33 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer removeOrg(final String orgInfoId,final String status) throws Exception{
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				Integer result = -1;
				sql.append("update org_info t set t.use_status = '").append(status).append("' where t.org_info_id = '").append(orgInfoId).append("'");
				Query query = session.createSQLQuery(sql.toString());
				result = query.executeUpdate();
				return result;
			}
		});
	}
}
