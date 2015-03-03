package com.gpcsoft.epp.eval.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.eval.domain.CongruousFactor;

public interface CongruousFactorService extends BaseGenericService<CongruousFactor> {

	/**
	 * @Description: 新增指标
	 * @param projId
	 * @param congruousFactor
	 * @return CongruousFactor
	 * @throws Exception
	 * @Create Date 2010-8-3 上午11:06:45 By wanghz
	 */
	public CongruousFactor saveCreateCongruousFactor(CongruousFactor congruousFactor,String projId)throws Exception;
	
	/**
	 * @Description: 更新指标
	 * @param projId
	 * @param congruousFactor
	 * @return CongruousFactor
	 * @throws Exception
	 * @Create Date 2010-8-3 上午11:06:45 By wanghz
	 */
	public CongruousFactor updateCongruousFactor(CongruousFactor congruousFactor,String projId)throws Exception;
	
	/**
	 * @Description: 保存引入指标,批量创建
	 * @param packIds
	 * @param factorTypeId
	 * @return CongruousFactor
	 * @throws Exception
	 * @Create Date 2010-8-11 上午10:30:23 By wanghz
	 */
	public CongruousFactor saveCreateCongruousFactor(String[] packIds,String isNeed,List<CongruousFactor> congruousFactorList);
	
	/**
	 * @Description: 删除指标
	 * @param objId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-8 上午12:00:16 By wanghz
	 */
	public void removeCongruousFactor(String objId);
	
	/**
	 * @Description: 获取指标分值总和
	 * @param factorTypeId 分类ID
	 * @return Double
	 * @throws Exception
	 * @Create Date 2010-9-19 下午02:31:55 By wanghz
	 */
	public Double getScoreSum(String factorTypeId);
	
	/**
	* FunName: saveBatchUpdateCongruousFactor
	* Description : 批量更新指标数据
	* @param： congruousFactorList 需更新的集合
	* @return boolean 是否更新成功
	* @Author: zhouzhanghe
	* @Create Date: 2011-7-5 9:15
	*/
	public boolean saveBatchUpdateCongruousFactor(List<CongruousFactor> congruousFactorList);
	
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
