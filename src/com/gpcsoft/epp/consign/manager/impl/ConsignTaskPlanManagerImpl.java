package com.gpcsoft.epp.consign.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.consign.dao.ConsignTaskPlanDao;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.domain.ConsignTaskPlan;
import com.gpcsoft.epp.consign.manager.ConsignTaskPlanManager;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;

@Repository
public class ConsignTaskPlanManagerImpl extends BaseManagerImpl<ConsignTaskPlan> implements ConsignTaskPlanManager {

	@Autowired(required=true)  @Qualifier("consignTaskPlanDaoHibernate")
	private ConsignTaskPlanDao consignTaskPlanDaoHibernate;

	/** 
	 * FuncName:getConsignByTaskPlan
	 * Description:根据采购计划获得委托协议
	 * @param   taskPlanIds:以逗号分隔的申报书主键
	 * @return  List<Consign>
	 * @author liangxj
	 * @Create Date: 2010-6-10下午02:00:34 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-10下午02:00:34 
	 */
	public List<Consign> getConsignByTaskPlan(String taskPlanIds){
	  	return consignTaskPlanDaoHibernate.getConsignByTaskPlan(taskPlanIds);
	}
	
	/** 
	 * FuncName:getTaskPlanByConsign
	 * Description :  根据委托协议获得采购计划
	 * @param  consignIds:以逗号分隔的委托协议主键
	 * @return  List<TaskPlan>
	 * @author liangxj
	 * @Create Date: 2010-6-10下午02:00:34 
	 * @Modifier  liangxj
	 * @Modified Date: 2010-6-10下午02:00:34 by 
	 */
	public List<TaskPlan> getTaskPlanByConsign(String consignIds){
		return consignTaskPlanDaoHibernate.getTaskPlanByConsign(consignIds);
	}
	
	/** 
	 * FuncName:removeByConsign
	 * Description :  根据委托协议删除中间表
	 * @param   consignIds:以逗号分隔的委托协议主键
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-10下午02:00:34 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-10下午02:00:34 
	 */
	public void removeByConsign(String consignIds){
		consignTaskPlanDaoHibernate.removeByConsign(consignIds);
	}

	/**
	 * FuncName:removeConsignTaskPlanBytaskPlanIdAndConsignId
	 * Description :根据项目主键和委托书主键删除中间表对象
	 * @param   taskPlanId:申报书主键,consignId:委托协议主键
	 * @return void
	 * @author liuke
	 * @Create Date: 2010-7-7下午01:26:14 
	 * @Modifier liuke
	 * @Modified Date: 2010-7-7下午01:26:14 
	 */
	public void removeConsignTaskPlanBytaskPlanIdAndConsignId(String taskPlanId, String consignId) {
		consignTaskPlanDaoHibernate.removeConsignTaskPlanBytaskPlanIdAndConsignId(taskPlanId, consignId);
	}

	/**
	 * FuncName:removeConsignTaskPlanByConsignId
	 * Description :根据委托书删除中间表对象  
	 * @param   consignId:委托协议主键
	 * @return  void
	 * @author lic
	 * @Create Date: 2010-7-14下午07:08:38 
	 * @Modifier   lic
	 * @Modified Date: 2010-7-14下午07:08:38 
	 */
	public void removeConsignTaskPlanByConsignId(String consignId) {
		consignTaskPlanDaoHibernate.removeConsignTaskPlanByConsignId(consignId);
	}

	/** 
	 * FuncName:searchByQueryObject
	 * Description :  根据查询对象获取对应的数据
	 * @param queryObject
	 * @return List<ConsignTaskPlan>
	 * @author liuy
	 * @Create Date: 2010-7-15下午06:50:42 
	 * @Modifier liuy
	 * @Modified Date: 2010-7-15下午06:50:42 
	 */
	public List<ConsignTaskPlan> searchByQueryObject(QueryObject<ConsignTaskPlan> queryObject) {
		return consignTaskPlanDaoHibernate.searchByQueryObject(queryObject);
	}

}
