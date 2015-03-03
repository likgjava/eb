package com.gpcsoft.epp.taskplan.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.dao.TaskPlanDetailDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class TaskPlanDetailDaoHibernate extends BaseGenericDaoHibernate<TaskPlanDetail> implements TaskPlanDetailDao {

	/**
	 * FuncName:removeTaskPlanDetail
	 * Description: 删除申报书采购资金,同时删除关联申报书中间表
	 * @param objId 采购资金明细ID
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-7-26 下午06:12:23 
	 */
	public void removeTaskPlanDetail(String objId) throws Exception {
		this.remove(objId,TaskPlanDetail.class);
		String hql = "delete TaskPlanMDetail where taskPlanDetail.objId = ? ";
		this.getSession().createQuery(hql).setString(0, objId).executeUpdate();
	}

	/**
	 * FuncName: saveTaskPlanDetailBySQL
	 * Description :  保存申报书资金明细对象
	 * @param 
	 * @param taskPlanDetail
	 * @throws EsException void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午09:55:50
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午09:55:50
	 */
	@SuppressWarnings("unchecked")
	public void saveTaskPlanDetailBySQL(final TaskPlanDetail taskPlanDetail)
			throws EsException {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into ECP_TASK_PLAN_DETAIL (TASK_PLAN_DETAIL_ID,QUANTITY,APPROVAL_NUMBER,SUPERIOR_APPROPRIATION,LOCAL_APPROPRIATION,OWNER_APPROPRIATION,OTHER_APPROPRIATION,CREATE_USER) ");	
			sql.append(" values('"+taskPlanDetail.getObjId()+"','"+taskPlanDetail.getQuantity()+"','"+taskPlanDetail.getApprovalNumber()+"','"+taskPlanDetail.getSuperiorApp()+"','"+taskPlanDetail.getLocalApp()+"','"+taskPlanDetail.getOwnerApp()+"','"+taskPlanDetail.getOtherApp()+"','"+taskPlanDetail.getCreateUser().getObjId()+"')");
			Query query = session.createSQLQuery(sql.toString());	
			return query.executeUpdate();
			}
		});
	}
	
	/**
	 * FuncName: getTaskPlanDetailList
	 * Description :  查询采购资金明细批准文号
	 * @param 
	 * @return List<TaskPlanDetail>
	 * @author: shenjz
	 * @Create Date:2011-10-8  下午04:47:49
	 * @Modifier: shenjz
	 * @Modified Date:2011-10-8  下午04:47:49
	 */
	public List getTaskPlanDetailList(String orgId,String approvalNumber){
		return this.findbyHql("select t1.taskPlanDetail.approvalNumber from TaskPlanMDetail t1 where t1.taskPlan.budget.objId = '"+orgId+"' and t1.taskPlanDetail.approvalNumber like '%"+approvalNumber+"%'" );
	}
}
