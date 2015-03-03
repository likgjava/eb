package com.gpcsoft.epp.taskplan.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.taskplan.dao.BlockTradeDao;
import com.gpcsoft.epp.taskplan.domain.BlockTrade;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.manager.BlockTradeManager;
@Repository
public class BlockTradeManagerImpl extends BaseManagerImpl<BlockTrade> implements BlockTradeManager{

	@Autowired(required=true)  @Qualifier("blockTradeDaoHibernate")
	private BlockTradeDao blockTradeDaoHibernate;
	
	/**
	 * FuncName: getAgentForRandom
	 * Description : 抽取代理机构
	 * @param List<OrgInfo> list:机构集合,num:抽取的个数
	 * @return List<OrgInfo>
	 * @author yangx
	 * @Create Date:   2010-6-9 by yangx
	 */
	public List<OrgInfo> getAgentForRandom(List<OrgInfo> list,int num) {
		if(list==null||list.size()<1||num<1)return null;//判断集合、抽取个数是否为空 
		Random rd = new Random();
		List<OrgInfo> listOrgInfo = new ArrayList<OrgInfo>();
		for(int i=0;i<num;i++){//循环抽取的个数 
			listOrgInfo.add(list.get(rd.nextInt(list.size())));//将抽取的代理机构存放到集合
		}
		return listOrgInfo;
	}
	
	/** 
	 * FuncName:getWinAgent
	 * Description:判断获胜的代理机构org_id
	 * @param org_ids:机构主键数组
	 * @return String
	 * @author yangx
	 * @Create Date:   2010-6-9  
	 */
	public String getWinAgent(String[] org_ids){
		if(org_ids==null||org_ids.length<1)return null;
		Map<Integer,String> compareMap = new HashMap<Integer,String>();
		/** 存放比较过的数据 */
		Map<String,Integer> judgeMap = new HashMap<String,Integer>();
		/** 要比较的数据 */
		int[] compareData = new int[org_ids.length];
		/** 循环所有的数据 */
		for(int i=0;i<org_ids.length;i++){
			/** 判断此数据是否已经比较过 */
			if(!judgeMap.containsKey(org_ids[i])){
			/** 相同的个数 */
			int count =0;
			/** 循环所有的数据 */
			for(int j=0;j<org_ids.length;j++){
				/** 判断是否为同一个值 */
				if(i!=j){
					/**  不为同一个值进行比较 */
					if(org_ids[i].equals(org_ids[j])){
						count++;
					}
				}
			}
			compareData[i]=count;
			judgeMap.put(org_ids[i],count);
			compareMap.put(count,org_ids[i]);
			}
		}
		/** 排序要比较的数据 */
		Arrays.sort(compareData);
		/** 判断是否为两个数据 */
		if(compareData.length>1){
		/** 后一个数据是否大于前一个 */
		return compareData[compareData.length-1]>compareData[compareData.length-2]? compareMap.get(compareData[compareData.length-1]):"false";
		}else{
			return compareMap.get(compareData[0]);
		}
	}
	
	/** 
	 * FuncName:getTaskPlanList
	 * Description:判断获胜的代理机构
	 * @param taskPlan:申报书对象
	 * @return List<TaskPlan>
	 * @author yangx
	 * @Create Date:   2010-6-9  
	 */
	public List<TaskPlan> getTaskPlanList(TaskPlan taskPlan){
		return blockTradeDaoHibernate.getTaskPlanList(taskPlan);
	}
}
