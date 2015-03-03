package com.gpcsoft.epp.eval.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.eval.domain.CongruousFactor;

public interface CongruousFactorDao extends BaseGenericDao<CongruousFactor> {

	/**
	 * @Description: 获取指标分值总和
	 * @param factorTypeId 分类ID
	 * @return Double
	 * @throws Exception
	 * @Create Date 2010-9-19 下午02:31:55 By wanghz
	 */
	public Double getScoreSum(String factorTypeId);
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取指标集合(电子评标系统接口)
	 * @param 
	 * @return List<CongruousFactor>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午01:35:07
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午01:35:07
	 */
	public List<CongruousFactor> getCongruousFactorList(String projectCode,String packCode);
	
	/**
	 * FuncName: saveCongruousFactor
	 * Description :  保存指标数据
	 * @param 
	 * @return List<CongruousFactor>
	 * @author: zhouzhanghe
	 * @Create Date:2011-7-6 17:27
	 */
	public void saveCongruousFactor(List<Map> congruousFactorList);
	
	
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取所有指标
	 * @param 
	 * @return List<CongruousFactor>
	 * @author: yangyc
	 * @Create Date:2011-11-29  下午05:35:07
	 * @Modifier: yangyc
	 * @Modified Date:2011-11-29  下午05:35:07
	 */
	public List<CongruousFactor> getAllFactorByProjectId(String projectId);
}
