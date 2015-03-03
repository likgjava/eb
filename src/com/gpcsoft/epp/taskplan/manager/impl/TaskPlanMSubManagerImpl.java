package com.gpcsoft.epp.taskplan.manager.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.taskplan.dao.TaskPlanMSubDao;
import com.gpcsoft.epp.taskplan.dao.TaskPlanSubDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMSubManager;

@Repository
public class TaskPlanMSubManagerImpl extends BaseManagerImpl<TaskPlanMSub> implements TaskPlanMSubManager {

	@Autowired(required=true)  @Qualifier("taskPlanMSubDaoHibernate")
	private TaskPlanMSubDao taskPlanMSubDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("taskPlanSubDaoHibernate")
	private TaskPlanSubDao taskPlanSubDaoHibernate;

	/** 
	 * FuncName:getSubNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书条目
	 * @param objId:申报书主键,status:汇总状态,taskType:申报书类型
	 * @return List<TaskPlanMSub>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj 
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanMSub> getSubNotSumSubsByOrg(String objId,String status, String taskType,String ebuyMethod){
		return taskPlanMSubDaoHibernate.getSubNotSumSubsByOrg(objId,status, taskType,ebuyMethod);
	}
	
	/** 
	 * FuncName:saveMSubsByTaskPlan
	 * Description : 根据申报书的主键，将其下的条目和明细汇总
	 * @param taskPlanIds:申报书主键,sumTaskPlanId:汇总的申报书主键,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier   liangxj  
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void saveMSubsByTaskPlan(String taskPlanIds,String sumTaskPlanId,String status){
		List<TaskPlanSub> taskPlanList = this.getSubByTaskPlan(taskPlanIds);
		for (TaskPlanSub taskPlanSub : taskPlanList) {
			TaskPlanMSub taskPlanMSub = new TaskPlanMSub();
			TaskPlan taskPlan = new TaskPlan();
			taskPlanMSub.setStatus(TaskPlanSumEnum.SUMMARY);
			taskPlanMSub.setTaskPlanSub(taskPlanSub);
			taskPlan.setObjId(sumTaskPlanId);
			taskPlanMSub.setTaskPlan(taskPlan);
			taskPlanMSubDaoHibernate.save(taskPlanMSub);
		}
	}
	
	/** 
	 * FuncName:getSubByTaskPlan
	 * Description:根据申报书的主键,获得条目
	 * @param taskPlanIds:以逗号分割的申报书主键
	 * @return List<TaskPlanSub>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier    liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanSub> getSubByTaskPlan(String taskPlanIds){
		String[] ids = taskPlanIds.split(",");
		String tIds = "";
		for (String id : ids) {
			tIds += "'" + id + "'," ;
		}
		tIds = tIds.substring(0,tIds.length()-1);
		List<TaskPlanSub> taskPlanList = taskPlanSubDaoHibernate.findbyHql("select t.taskPlanSub from TaskPlanMSub t where t.taskPlan.objId in ( "+tIds+" )");
		return taskPlanList;
	}
	
	/** 
	 * FuncName:removeSumMSubs
	 * Description : 根据申报书条目主键,删除所属的申报书所有汇总的条目,不级联删除申报书条目
	 * @param taskPlanSubIds:申报书条目主键,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier   liangxj   
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void removeSumMSubs(String taskPlanSubIds, String status) {
		taskPlanMSubDaoHibernate.removeSumMSubs(taskPlanSubIds, status);
	}

	/** 
	 * FuncName:removeByTaskPlanSubId
	 * Description:根据申报书条目ID删除对应的中间表数据
	 * @param taskPlanSubId:申报书条目主键
	 * @return void
	 * @author Administrator
	 * @Create Date: 2010-7-10下午04:55:41
	 * @Modifier :Administrator
	 * @Modified Date: 2010-7-10下午04:55:41 
	 */
	public void removeByTaskPlanSubId(String taskPlanSubId) {
		taskPlanMSubDaoHibernate.removeByTaskPlanSubId(taskPlanSubId);
	}

	/** 
	 * FuncName:getTaskPlanMSubByProjectId
	 * Description :  根据项目主键获取申报书与申报书条目中间对象
	 * @param   projectId:项目主键
	 * @return  List
	 * @author yangx
	 * @Create Date: 2010-6-24下午01:10:46 
	 * @Modifier    yangx
	 * @Modified Date: 2010-6-24下午01:10:46  
	 */
	@SuppressWarnings("unchecked")
	public List getTaskPlanMSubByProjectId(String projectId){
		return taskPlanMSubDaoHibernate.getTaskPlanMSubByProjectId(projectId);
	}
	
	/** 
	 * FuncName:getProjectMPreqByTaskPlanMSubId
	 * Description:获取采购申报书明细与项目标段中间表
	 * @param   queryObject:组装的查询条件对象
	 * @return  List
	 * @author yangx
	 * @Create Date: 2010-6-30上午11:03:45 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-30上午11:03:45  
	 */
	@SuppressWarnings("unchecked")
	public List getProjectMPreqByTaskPlanMSubId(QueryObject queryObject){
		return taskPlanMSubDaoHibernate.findByQuery(queryObject);
	}
	
	/**
	 * FuncName:getTaskPlanSubByTaskPlan
	 * Description : 根据申报书获得申报书明细列表
 	 * @param   taskPlanId:申报书主键
	 * @return   List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-15上午09:34:57 
	 * @Modifier    liuke
	 * @Modified Date: 2010-7-15上午09:34:57  
	 */
	public List<TaskPlanSub> getTaskPlanSubByTaskPlan(String taskPlanId) {
		return taskPlanMSubDaoHibernate.getTaskPlanSubByTaskPlan(taskPlanId);
	}
	
	/** 
	 * FuncName:getTaskPlanSubByTaskPlanId
	 * Description :  根据申报书id获取采购申报书明细
	 * @param   projectId:项目主键
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-6-30上午10:32:43 
	 * @Modifier yangx
	 * @Modified Date: 2010-6-30上午10:32:43  
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanMSub> getTaskPlanSubByTaskPlanId(String projectId){
		List<TaskPlanMSub> taskPlanMSubList = this.getTaskPlanMSubByProjectId(projectId);//采购申报书条目中间表
		if(taskPlanMSubList!=null&&taskPlanMSubList.size()>0){
			for(int i=0;i<taskPlanMSubList.size();i++){
				BigDecimal money = new BigDecimal(0);
				BigDecimal num = new BigDecimal(0);
				TaskPlanMSub taskPlanMSub = taskPlanMSubList.get(i);//得到申报书条目对象
				QueryObject<SubProjectMTaskPlanSub> queryObjectM = new QueryObjectBase<SubProjectMTaskPlanSub>();
				queryObjectM.setEntityClass(SubProjectMTaskPlanSub.class);
				queryObjectM.getQueryParam().and(new QueryParam("taskPlanSub.objId",QueryParam.OPERATOR_EQ,taskPlanMSub.getTaskPlanSub().getObjId()));
				List<SubProjectMTaskPlanSub> projectMTaskPlanList = this.getProjectMPreqByTaskPlanMSubId(queryObjectM);//根据申报书条目id查询中间表
				if(projectMTaskPlanList!=null&&projectMTaskPlanList.size()>0){
					for(int j=0;j<projectMTaskPlanList.size();j++){//遍历条目中间表
						SubProjectMTaskPlanSub projectMTaskPlan = projectMTaskPlanList.get(j);
						if(taskPlanMSub.getTaskPlanSub()!=null&&projectMTaskPlan.getTaskPlanSub()!=null&&(taskPlanMSub.getTaskPlanSub().getObjId()).equals(projectMTaskPlan.getTaskPlanSub().getObjId())){//判断是否为同一申报书条目
							money = money.add(projectMTaskPlan.getMoney());//金额
							num = num.add(projectMTaskPlan.getQuantity());//中间表对象中数量相加
						}
					}
				}
				if(money.longValue()!=0){//金额
					if(taskPlanMSub.getTaskPlanSub()!=null){
						if (taskPlanMSub.getTaskPlanSub().getTotalPrice().subtract(money).compareTo(new BigDecimal("0"))!=1) {
							taskPlanMSub.getTaskPlanSub().setRealMoney(new BigDecimal(0));//设置实际金额
						}else{
							taskPlanMSub.getTaskPlanSub().setRealMoney(taskPlanMSub.getTaskPlanSub().getTotalPrice().subtract(money));//设置实际金额
						}
					}
				}else{
					taskPlanMSub.getTaskPlanSub().setRealMoney(taskPlanMSub.getTaskPlanSub().getTotalPrice());//设置实际金额
				}
				if(num.longValue()!=0){//数量
					if(taskPlanMSub.getTaskPlanSub()!=null){
						if (taskPlanMSub.getTaskPlanSub().getQuantity().subtract(num).compareTo(new BigDecimal("0"))!=1) {//大于0
							taskPlanMSub.getTaskPlanSub().setRealQuantity(new BigDecimal(0));
						}else{//小于等于0
							taskPlanMSub.getTaskPlanSub().setRealQuantity(taskPlanMSub.getTaskPlanSub().getQuantity().subtract(num));//设置实际金额
						}
					}
				}else{
					taskPlanMSub.getTaskPlanSub().setRealQuantity(taskPlanMSub.getTaskPlanSub().getQuantity());//设置实际金额
				}
			}
		}
		return taskPlanMSubList;
	}
	
	/**
	 * FuncName:getTaskPlanMSubsByPackId
	 * Description: 根据包件主键获取所有申报书明细中间表
	 * @param packId:包件主键
	 * @return List<TaskPlanSub>
	 * @author wanghz
	 * @Create Date 2010-10-8 上午10:51:10  
	 */
	public List<TaskPlanMSub> getTaskPlanMSubsByPackId(String packId){
		return taskPlanMSubDaoHibernate.getTaskPlanMSubsByPackId(packId);
	}
	
	/** 
	 * FuncName:getTaskPlanMSubByTaskPlanIdAndStatus
	 * Description :  根据申报书和汇总状态查询申报书、条目中间表
	 * @param   taskPlanId:申报书主键,status:汇总状态
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-11-18下午04:01:52 
	 * @Modifier    yangx
	 * @Modified Date: 2010-11-18下午04:01:52  
	 */
	public List<TaskPlanMSub>  getTaskPlanMSubByTaskPlanIdAndStatus(String taskPlanId,String status){
		return taskPlanMSubDaoHibernate.getTaskPlanMSubByTaskPlanIdAndStatus(taskPlanId, status);
	}
	
	/**
	 * FuncName:getTaskPlanMSubByStatus
	 * Description :  获取本级的申报书明细
	 * @param	taskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:26:21 
	 * @Modifier   shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:26:21 
	 */  
	public List<TaskPlanMSub> getTaskPlanMSubByStatus(String taskPlanId) {
		return taskPlanMSubDaoHibernate.getTaskPlanMSubByStatus(taskPlanId);
	}
	
}
