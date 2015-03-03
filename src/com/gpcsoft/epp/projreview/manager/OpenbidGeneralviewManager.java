package com.gpcsoft.epp.projreview.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;

public interface OpenbidGeneralviewManager extends BaseManager<OpenbidGeneralview> {
	
	/**
	 * FuncName:getAllByProjectId
	 * Description: 根据项目主键获取所有开标一览表
	 * @param projectId:项目主键
	 * @return List<OpenbidGeneralview>
	 * @author wanghz
	 * @Create Date 2010-8-1 下午02:30:11 
	 */
	public List<OpenbidGeneralview> getAllByProjectId(String projectId)throws Exception;
	
	/**
	 * FuncName:getOpenbidGeneralviewByOpenBidRecordId
	 * Description :根据评审结果主键获得开标一览表对象  
	 * @param   bidRecordId:评审结果主键
	 * @return  OpenbidGeneralview
	 * @author liuke
	 * @Create Date: 2010-9-16下午04:49:11 
	 * @Modifier  liuke
	 * @Modified Date: 2010-9-16下午04:49:11 
	 */
	public OpenbidGeneralview getOpenbidGeneralviewByOpenBidRecordId(String bidRecordId);
	
	/**
	 * FuncName:removeAllOpenbidGeneralviewByProjectId
	 * Description: 删除开标一览表对象
	 * @param projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-15下午04:48:52 
	 * @Modifier   liuke
	 * @Modified Date: 2010-12-15下午04:48:52 
	 */
	public void removeAllOpenbidGeneralviewByProjectId(String projectId);
}
