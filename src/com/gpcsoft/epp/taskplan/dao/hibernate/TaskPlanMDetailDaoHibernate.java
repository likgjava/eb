package com.gpcsoft.epp.taskplan.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.dao.TaskPlanMDetailDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class TaskPlanMDetailDaoHibernate extends BaseGenericDaoHibernate<TaskPlanMDetail> implements TaskPlanMDetailDao {
	
	/** 
	 * FuncName:getSubNotSumDetailsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书条目
	 * @param objId:部门ID,status:为汇总状态,taskType:申报书类型
	 * @return List<TaskPlanMDetail>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj  
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanMDetail> getSubNotSumDetailsByOrg(String objId,String status, String taskType,String ebuyMethod){
		StringBuffer hql = new StringBuffer();
		hql.append("from TaskPlanMDetail t ");
		hql.append("left join fetch t.taskPlan as p left join fetch t.taskPlanDetail ");
		hql.append("where t.taskPlan.department.objId =? and t.taskPlan.budget.objId !=? ");
		hql.append("and p.useStatus =? and p.auditDetail=? and p.confirmStatus=?");
		hql.append("and t.taskPlanDetail.objId not in ");
		hql.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("s.taskPlanDetail.objId")+" from TaskPlanMDetail s where t.taskPlan.department.objId =? and s.status =?) ");
		hql.append("and t.taskPlan.taskType =? and t.taskPlan.ebuyMethod=? ");
		hql.append("order by p.createTime,p.objId");
		List<TaskPlanMDetail> list = this.getHibernateTemplate().find(hql.toString(), new Object[]{objId,objId,CommonEnum.USER_STATUS_FORMAL,CommonEnum.CONFIRM_STATUS_WAIT,CommonEnum.CONFIRM_STATUS_WAIT,objId,status,taskType,ebuyMethod});
		return list;
	}
	
	/** 
	 * FuncName:removeSumMDetails
	 * Description : 根据taskPlan id ，删除所属的申报书所有汇总的资金明细，不级联删除taskPlanDetail
	 * @param taskPlanIds:申报书主键,status为汇总状态  
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj    
	 * @Modified Date:  2010-6-3 下午04:12:39 
	 */
	@SuppressWarnings("unchecked")
	public void removeSumMDetails(final String taskPlanIds, final String status) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				hql.append("delete from TaskPlanMDetail t where taskPlanDetail.objId in ");
				hql.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("p.taskPlanDetail.objId")+" from TaskPlanMDetail p where p.taskPlan.objId in (:planIds)) ");
				hql.append("and t.status =:status");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("planIds", taskPlanIds.split(","));
				query.setString("status", status);
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * FucnName:getTaskPlanMDetailByStatus
	 * Description : 获取本级的资金明细
	 * @param taskPlanId:申报书ID
	 * @return  List<TaskPlanDetail>
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:25:15 
	 * @Modifier  shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:25:15 
	 */	
	public List<TaskPlanMDetail> getTaskPlanMDetailByStatus(String taskPlanId ) {
		String hql = "from TaskPlanMDetail t where t.taskPlan.objId = ? and t.status = ?";
		return this.findbyHql(hql,taskPlanId,TaskPlanSumEnum.NORMAL);
	}

	
	/**
	 * FuncName: saveTaskPlanDetailBySQL
	 * Description :  保存申报书资金明细中间表对象
	 * @param 
	 * @param taskPlanDetail
	 * @throws EsException void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午09:55:50
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午09:55:50
	 */
	@SuppressWarnings("unchecked")
	public void saveTaskPlanMDetailBySQL(final TaskPlanMDetail taskPlanMDetail)
			throws EsException {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into ECP_TASK_DETAIL (TASK_M_DETAIL_ID,TASK_PLAN_ID,TASK_PLAN_DETAIL_ID,STATUS) ");	
			sql.append(" values('"+taskPlanMDetail.getObjId()+"','"+taskPlanMDetail.getTaskPlan().getObjId()+"','"+taskPlanMDetail.getTaskPlanDetail().getObjId()+"','"+taskPlanMDetail.getStatus()+"')");
			Query query = session.createSQLQuery(sql.toString());	
			return query.executeUpdate();
			}
		});
	}
}
