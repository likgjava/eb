package com.gpcsoft.epp.eval.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.dao.CongruousFactorTypeDao;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.manager.CongruousFactorTypeManager;

@Repository
public class CongruousFactorTypeManagerImpl extends BaseManagerImpl<CongruousFactorType> implements CongruousFactorTypeManager {

	@Autowired(required=true)  @Qualifier("congruousFactorTypeDaoHibernate")
	private CongruousFactorTypeDao congruousFactorTypeDaoHibernate;

	
	/**
	 * @Description: 获取指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:04:01 By wanghz
	 */
	public List<CongruousFactorType> getCongruousFactorTypeByProjectId(String projectId){
		return congruousFactorTypeDaoHibernate.getCongruousFactorTypeByProjectId(projectId);
	}
	
	/**
	 * @Description: 获取平分指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:04:01 By wanghz
	 */
	public List<CongruousFactorType> getGradeCongruousFactorTypeByProjectId(String projectId){
		return congruousFactorTypeDaoHibernate.getGradeCongruousFactorTypeByProjectId(projectId);
	}
	
	/**
	 * @Description: 删除指标分类
	 * @param congruousFactorTypeList 不需要删除的指标分类
	 * @param projectId 项目ID
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-19 上午12:50:33 By wanghz
	 */
	public void removeCongruousFactorType(List<CongruousFactorType> congruousFactorTypeList, String projectId)throws EsException{
		congruousFactorTypeDaoHibernate.removeCongruousFactorType(congruousFactorTypeList, projectId);
	}
	
	/** 
	 * Description :  查询该项目下的指标分类权重值总和
	 * Create Date: 2010-9-27下午01:40:29 by yangx  Modified Date: 2010-9-27下午01:40:29 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getWeightCoefficientSumByProjectId(String projectId){
		return congruousFactorTypeDaoHibernate.getWeightCoefficientSumByProjectId(projectId);
	}
}
