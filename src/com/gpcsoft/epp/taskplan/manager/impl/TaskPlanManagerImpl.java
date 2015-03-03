package com.gpcsoft.epp.taskplan.manager.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.dao.TaskPlanDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.manager.TaskPlanManager;
import com.gpcsoft.srplatform.baseData.dao.SequenceNumberDao;

@Repository
public class TaskPlanManagerImpl extends BaseManagerImpl<TaskPlan> implements TaskPlanManager {

	@Autowired(required=true)  @Qualifier("taskPlanDaoHibernate")
	private TaskPlanDao taskPlanDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("sequenceNumberDaoHibernate")
	private SequenceNumberDao sequenceNumberdao;
	
	/**
	 * FuncName:submitTaskPlan
	 * Description:提交采购计划
	 * @param   objIds:以逗号分割的采购计划主键,useStatus:使用状态,confirmStatus:确认状态,auditDetail:审核状态
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43  
	 */
	public void submitTaskPlan(String objIds,String useStatus,String confirmStatus,String auditDetail) {
		taskPlanDaoHibernate.submitTaskPlan(objIds,useStatus,confirmStatus,auditDetail);
	}

	/**
	 * FuncName:getTaskPlanBySubs
	 * Description:根据条目和状态获得申报书
	 * @param subIds:条目主键,status:
	 * @return   List<TaskPlan>
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43    
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43  
	 */
	public List<TaskPlan> getTaskPlanBySubs(String subIds,String status){
		subIds = subIds.replace(",","','");
		//查询taskPlan
		List<TaskPlan> taskPlanList = taskPlanDaoHibernate.getTaskPlanBySubsAndStatus(subIds, status);
		return taskPlanList;
	}
	
	/**
	 * FuncName:getSubNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书
	 * @param orgId:代理机构id,status:汇总状态,taskCode:申报书编号,applyDate:申请日期
	 * @return Page<TaskPlan>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj   
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public Page<TaskPlan> getSubNotSumSubsByOrg( Page<TaskPlan> page, String orgId, String status,String taskCode, String applyDate) throws Exception{
		return taskPlanDaoHibernate.getSubNotSumSubsByOrg(page, orgId, status,taskCode,applyDate);
	}

	/**
	 * FuncName:getTaskPlanListByObjId
	 * Description : 通过申报书主键得到申报书列表 
	 * @param   objId:申报书主键
	 * @return  List<TaskPlan>
	 * @author liuke
	 * @Create Date: 2010-7-5下午03:22:01    
	 * @Modifier liuke
	 * @Modified Date: 2010-7-5下午03:22:01  
	 */
	public List<TaskPlan> getTaskPlanListByObjId(String objId) {
		List<TaskPlan> list =	taskPlanDaoHibernate.getTaskPlanListByObjId(objId);
		return list;
	}

	/** 
	 * Funcname:getTaskPlanTotalByQueryObject
	 * Description :  根据查询条件统计所有的申报书数据
	 * @param queryObject[taskType:类型，useStatus:使用状态,createUser:采购人帐号ID,confirmStatus:确认状态,auditDetail:审核状态,governmentId:部门ID,leader:负责人ID]
	 * @return BigDecimal
	 * @author Administrator
	 * @Create Date: 2010-7-7下午04:02:54 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-7-7下午04:02:54  
	 */
	public BigDecimal getTaskPlanTotalByQueryObject(QueryObject queryObject) {
		return taskPlanDaoHibernate.getTaskPlanTotalByQueryObject(queryObject);
	}

	/** 
	 * FuncName:getTaskPlanPageByQueryObject
	 * Description:通过查询对象获得对应的申报书列表
	 * @param queryObject:拼装的对象,page:分页对象
	 * @return Page<TaskPlan>
	 * @author yangx
	 * @Create Date: 2010-7-13下午01:09:32 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-13下午01:09:32  
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getTaskPlanPageByQueryObject(QueryObject queryObject, Page<TaskPlan> page) {
		return taskPlanDaoHibernate.getTaskPlanPageByQueryObject(queryObject,page);
	}

	/**
	 * FuncName:getTaskPlanSubListByNotblockAndPass
	 * Description : 得到非大宗已审核通过待起草委托协议的申报书明细列表
	 * @param   page:分页对象,queryObject:拼装的对象
	 * @return  Page
	 * @author liuke
	 * @Create Date: 2010-7-8上午09:39:48 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-8上午09:39:48  
	 */
	public Page getTaskPlanSubListByNotblockAndPass(Page page,QueryObject<TaskPlanSub> queryObject) {
		return taskPlanDaoHibernate.getTaskPlanSubListByNotblockAndPass(page,queryObject);
	}

	/** 
	 * FuncName:getTaskPlanListByTaskPlanIds
	 * Description:通过申报书主键得到申报书列表
	 * @param TaskPlanIds:申报书主键
	 * @return List<TaskPlan> 
	 * @author liuke
	 * @Create Date: 2010-7-8上午09:39:48 
	 * @Modifier    liuke
	 * @Modified Date: 2010-7-8上午09:39:48  
	 */
	public List<TaskPlan> getTaskPlanListByTaskPlanIds(String TaskPlanIds) {
		return taskPlanDaoHibernate.getTaskPlanListByTaskPlanIds(TaskPlanIds);
	}
	
	/**
	 * FuncName:generatorTaskPlanCodeByQO
	 * Description: 仅为获得采购申报书编号+1在页面展示
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44  
	 */
	public String generatorTaskPlanCodeByQO(QueryObject queryObject)throws EsException {
		String prefixCode = "";// 1.获取采购申报书前缀编号
		try {
			prefixCode = messageSource.getMessage("taskPlanPrefixCode");
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_GET_PREFIX_CODE_ISNULL));
		}
		StringBuffer returnCode = new StringBuffer();// 2.按照规则生成采购申报书编号
		returnCode.append(prefixCode);
		if(null != prefixCode && !"".equals(prefixCode)){
			returnCode.append("-");
		}
		returnCode.append(DateUtil.format(new Date(), "yyyyMMdd"));
		try {
			returnCode.append("-"+(generateNumber(Integer.parseInt(sequenceNumberdao.getMaxSn(returnCode.toString(), 3))+1)));
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_IS_DB_READONLY));
		}
		return returnCode.toString();
	}
	
	/**
	 * FuncName: generatorUpdateTaskPlanCodeByQO
	 * Description:生成采购申报书编号,Service层必须开启DB事物
	 * @param 
	 * @param queryObject
	 * @return
	 * @throws EsException String
	 * @author: shenjz
	 * @Create Date:2011-11-9  上午10:00:25
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-9  上午10:00:25
	 */
	public String generatorUpdateTaskPlanCodeByQO(QueryObject queryObject)throws EsException {
		String prefixCode = "";// 1.获取采购申报书前缀编号
		try {
			prefixCode = messageSource.getMessage("taskPlanPrefixCode");
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_GET_PREFIX_CODE_ISNULL));
		}
		StringBuffer returnCode = new StringBuffer();// 2.按照规则生成采购申报书编号
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
	 * FuncName: getTaskPlanSubListByConfirmConsign
	 * Description :  得到所有已确认委托协议后的申报书明细列表
	 * @param page:分页对象,queryObject:组装的查询条件对象
	 * @return Page
	 * @author liuy
	 * @Create Date: 2010-7-15下午02:25:38 
	 * Modified Date: 2011-10-20上午11:49:28 by yangx
	 */
	public Page getTaskPlanSubListByConfirmConsign(Page page,String budgetName,String purchaseName,String ebuyMethod,String taskType,String taskPlanSubIds_not,String agentId){
		return taskPlanDaoHibernate.getTaskPlanSubListByConfirmConsign(page,budgetName,purchaseName,ebuyMethod,taskType,taskPlanSubIds_not,agentId);
	}
	
	/** 
	 * FuncName:getTaskPlanPageByQO
	 * Description :  获取待处理采购申报书
	 * @param   QueryObject[taskType:申报书类型,useStatus:使用状态,confirmStatus:确认状态,block_check_status:大宗审核状态,taskCode：申报书编号,applyDate:日期]
	 * @return  Page<TaskPlan>
	 * @author yangx
	 * @Create Date: 2010-7-15下午07:04:45 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-15下午07:04:45 
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getTaskPlanPageByQO(QueryObject queryObject,Page page){
		return taskPlanDaoHibernate.getTaskPlanPageByQO(queryObject,page);
	}

	/**
	 * FuncName:getTaskPlanSubInProject
	 * Description : 得到被列入项目中的申报书条目列表
	 * @param   
	 * @return  List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-16上午10:57:17 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-16上午10:57:17  
	 */
	public List<TaskPlanSub> getTaskPlanSubInProject() {
		return taskPlanDaoHibernate.getTaskPlanSubInProject();
	}
	
	/**
	 * FuncName:getNoSetupProjectLeader
	 * Description: 获取未指定项目经办人申报书
	 * @param type{count:记录条数,data:数据集合}
	 * @return List
	 * @author wanghz
	 * @Create Date 2010-9-2 下午01:30:38  
	 */
	public List getNoSetupProjectLeader(String type)throws EsException{
		return taskPlanDaoHibernate.getNoSetupProjectLeader(type);
	}
	
	/**
	 * FuncName:getMoneySubByTaskPlan
	 * Description:根据申报书获取到申报书所有条目"预算总金额"的和
	 * @param taskPlan:申报书对象
	 * @return  float
	 * @author shenjianzhuang
	 * @Create Date: 2010-12-13下午04:27:32 
	 * @Modifier   shenjianzhuang
	 * @Modified Date: 2010-12-13下午04:27:32  
	 */	
	public float getMoneySubByTaskPlan(TaskPlan taskPlan){
		float moneySub = 0;
		Set<TaskPlanMSub> taskPlanMSubs = taskPlan.getTaskPlanMSubs();
		if(taskPlanMSubs.size()!=0){
			for (Iterator iterator = taskPlanMSubs.iterator(); iterator
					.hasNext();) {
				TaskPlanMSub taskPlanMSub = (TaskPlanMSub) iterator.next();
				TaskPlanSub taskPlanSub = taskPlanMSub.getTaskPlanSub();
				moneySub+= taskPlanSub.getTotalPrice().floatValue();
			}
		}
		return moneySub;
	}
	
	/**
	 * FuncName:getMoneyDetailByTaskPlan
	 * Description :  根据申报书获取到申报书所有资金明细"总资金"的和
	 * @param taskPlan:申报书对象
	 * @return  float
	 * @author shenjianzhuang
	 * @Create Date: 2010-12-13下午04:27:43 
	 * @Modifier  shenjianzhuang  
	 * @Modified Date: 2010-12-13下午04:27:43  
	 */	
	public float getMoneyDetailByTaskPlan(TaskPlan taskPlan){
		float moneyDetail = 0;
		Set<TaskPlanMDetail> taskPlanMDetails = taskPlan.getTaskPlanMDetails();
		if(taskPlanMDetails.size()!=0){
			for (Iterator iterator = taskPlanMDetails.iterator(); iterator
					.hasNext();) {
				TaskPlanMDetail taskPlanMDetail = (TaskPlanMDetail) iterator
						.next();
				TaskPlanDetail taskPlanDetail = taskPlanMDetail.getTaskPlanDetail();
				moneyDetail+=Float.parseFloat(taskPlanDetail.getQuantity());
			}
		}
		return moneyDetail;
	}
	
	/**
	 * FuncName: generatNumber
	 * Description : 处理Integer类型字符串
	 * @param 
	 * @param a
	 * @return String
	 * @author: shenjz
	 * @Create Date:2011-11-9  上午09:47:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-9  上午09:47:47
	 */
	@SuppressWarnings("unused")
	private String generateNumber(Integer a){
		if(a.toString().length()==1){
			return "00"+a;
		}else if(a.toString().length()==2){
			return "0"+a;
		}else if(a.toString().length()==3){
			return ""+a;
		}
		return "";
	}
}
