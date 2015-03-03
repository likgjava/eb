package com.gpcsoft.epp.eval.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;

public interface CongruousFactorTypeDao extends BaseGenericDao<CongruousFactorType> {

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
	
	/**
	 * FuncName: getCongruousFactorTypeList
	 * Description : 获取到指标分类集合
	 * @param 
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-5-9  上午11:13:20
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-9  上午11:13:20
	 */
	public List<CongruousFactorType> getCongruousFactorTypeList(String parentObjId,String projectId);
	
	/**
	 * FuncName: removeCongruousFactorTypeCascade
	 * Description :  删除指标类别以及其下的条目及其中间表
	 * @param 
	 * @param objId
	 * @throws EsException void
	 * @author: shenjz
	 * @Create Date:2011-5-10  下午02:52:12
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-10  下午02:52:12
	 */
	@SuppressWarnings("unchecked")
	public void removeCongruousFactorTypeCascade(final String objId)throws EsException;
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取指标分类集合(电子评标系统接口)
	 * @param projectCode:项目编号,packCode:包组编号
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午01:35:07
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午01:35:07
	 */
	public List<CongruousFactorType> getCongruousFactorTypList(String projectCode,String packCode);
	/**
	 * FuncName: getListForCheck
	 * Description :  根据编号和项目主键校验编号是否唯一
	 * @param 
	 * @param typeCode
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-6-2  下午04:09:13
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-2  下午04:09:13
	 */
	public List<CongruousFactorType> getListForCheck(String typeCode,String projectId);
	
	/**
	 * FuncName: getListForCheck
	 * Description :  根据项目Id取得所有指标分类
	 * @param 
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @author: yangyc
	 * @Create Date:2011-11-29  16:50:13
	 * @Modifier: yangyc
	 * @Modified Date:2011-11-29  16:50:13
	 */
	public List<CongruousFactorType> getAllFactorTypeByProjectId(String projectId);
}
