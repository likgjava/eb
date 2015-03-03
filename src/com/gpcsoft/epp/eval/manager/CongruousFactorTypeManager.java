package com.gpcsoft.epp.eval.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;

public interface CongruousFactorTypeManager extends BaseManager<CongruousFactorType> {

	
	/**
	 * @Description: 获取指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:04:01 By wanghz
	 */
	public List<CongruousFactorType> getCongruousFactorTypeByProjectId(String projectId);
	
	/**
	 * @Description: 获取评分指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:04:01 By wanghz
	 */
	public List<CongruousFactorType> getGradeCongruousFactorTypeByProjectId(String projectId);
	
	/**
	 * @Description: 删除指标分类
	 * @param congruousFactorTypeList 不需要删除的指标分类
	 * @param projectId 项目ID
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-19 上午12:50:33 By wanghz
	 */
	public void removeCongruousFactorType(List<CongruousFactorType> congruousFactorTypeList, String projectId)throws EsException;
	
	/** 
	 * Description :  查询该项目下的指标分类权重值总和
	 * Create Date: 2010-9-27下午01:40:29 by yangx  Modified Date: 2010-9-27下午01:40:29 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getWeightCoefficientSumByProjectId(String projectId);
}
