package com.gpcsoft.epp.taskplan.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.taskplan.dao.BlockTradeDao;
import com.gpcsoft.epp.taskplan.domain.BlockTrade;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;

@Repository
public class BlockTradeDaoHibernate extends BaseGenericDaoHibernate<BlockTrade> implements BlockTradeDao{

	/** 
	 * FuncName:getTaskPlanList
	 * Description:判断获胜的代理机构
	 * @param taskPlan:申报书对象
	 * @return List<TaskPlan>
	 * @author yangx
	 * @create Date:2010-6-9 
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlan> getTaskPlanList(TaskPlan taskPlan){
		StringBuffer sb = new StringBuffer("from TaskPlan where 1=1 ");
		if(taskPlan!=null){
			if(taskPlan.getObjId()!=null&&!"".equals(taskPlan.getObjId())){
				sb.append(" and objId = '"+taskPlan.getObjId()+"'");
			}
			if(taskPlan.getTaskType()!=null&&!"".equals(taskPlan.getTaskType())){
				sb.append(" and taskType = '"+taskPlan.getTaskType()+"'");
			}
			if(taskPlan.getUseStatus()!=null&&!"".equals(taskPlan.getUseStatus())){
				sb.append(" and useStatus='"+taskPlan.getUseStatus()+"'");
			}
			if(taskPlan.getConfirmStatus()!=null&&!"".equals(taskPlan.getConfirmStatus())){
				sb.append(" and confirmStatus='"+taskPlan.getConfirmStatus()+"'");
			}
			    sb.append(" and block_check_status is Null");
		}
		List<TaskPlan> list = this.getHibernateTemplate().find(sb.toString());
		return list;
	}
}
