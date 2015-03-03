package com.gpcsoft.epp.eval.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;

public interface PackCongFactorResponseDao extends BaseGenericDao<PackCongFactorResponse> {

	/**
	 * @Description: 获取所有指标响应
	 * @param bidId投标ID
	 * @param packIds 包件ID
	 * @return List<Map<String,String>>
	 * @throws Exception
	 * @Create Date 2010-8-4 下午05:13:45 By wanghz
	 */
	public List<Map<String, String>> getFactorResponseByBidIdAndPackIds(String bidId,String[] packIds)throws Exception;
	
	/**
	 * @Description: 根据投标Id获取指标响应
	 * @param bidId
	 * @return List<PackCongFactorResponse>
	 * @throws Exception
	 * @Create Date 2010-8-5 下午01:18:03 By wanghz
	 */
	public List<PackCongFactorResponse> getPackCongFactorResponseIsMayRemove(String bidId);
	
	/**
	 * @Description: 获取 供应商投标的指标分类(一级分类)
	 * @param supplierId 供应商ID
	 * @param packId 包件ID/项目Id
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-20 下午10:42:03 By wanghz
	 */
	public List<CongruousFactorType> getBidCongruousFactorType(String supplierId,String packId);
	
	/**
	 * @Description: 获取 供应商投标的指标分类(子分类)
	 * @param supplierId 供应商ID
	 * @param packId 包件ID/项目Id
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-21 下午02:39:02 By wanghz
	 */
	public List<CongruousFactorType> getgetBidCongruousFactorTypeChild(String supplierId,String packId);
	
	
	
	
	/**
	 * 
	 * Description :根据项目获得所有指标响应与包件中间表 
	 * Create Date: 2010-10-11上午09:27:01 by liuke  Modified Date: 2010-10-11上午09:27:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<PackCongFactorResponse> getAllPackCongFactorResponseListByProjectId(String projectId);
	
	
	/** 
	 * Description :删除包件指标中间表信息
	 * Create Date: 2010-12-15下午07:08:05 by liuke  Modified Date: 2010-12-15下午07:08:05 by liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllPackCongFactorResponseByProject(String projectId);
	
}
