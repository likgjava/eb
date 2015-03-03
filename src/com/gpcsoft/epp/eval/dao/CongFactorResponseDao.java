package com.gpcsoft.epp.eval.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;

public interface CongFactorResponseDao extends BaseGenericDao<CongFactorResponse> {

	/**
	 * @Description: 根据指标响应获取可用删除的指标响应
	 * @param congFactorResponseList
	 * @return List<CongFactorResponse>
	 * @throws Exception
	 * @Create Date 2010-8-6 下午02:07:41 By wanghz
	 */
	public List<CongFactorResponse> getCongFactorResponseIsMayRemove(List<CongFactorResponse> congFactorResponseList,String bidId);
	
	
	
	
	/**
	 * 
	 * Description :根据项目主键获得所有指标响应对象  
	 * Create Date: 2010-10-9下午06:35:36 by liuke  Modified Date: 2010-10-9下午06:35:36 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<CongFactorResponse> getAllCongFactorResponseByProjectId(String projectId);
	
	
	
	/**
	 * Description :  修改或更新对象
	 * Create Date: 2010-12-15下午06:48:35 by liuke  Modified Date: 2010-12-15下午06:48:35 by liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllCongFactorResponseByProject(String projectId);
	
}
