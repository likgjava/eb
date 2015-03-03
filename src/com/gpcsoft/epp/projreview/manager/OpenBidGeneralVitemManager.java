package com.gpcsoft.epp.projreview.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;

public interface OpenBidGeneralVitemManager extends BaseManager<OpenBidGeneralVitem> {
	
	/**
	 * FuncName:removeOpenBidGeneralVitemByProjectId
	 * Description :根据项目删除开标一览表明细  
	 * @param   projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-10-11下午01:49:36 
	 * @Modifier    liuke
	 * @Modified Date: 2010-10-11下午01:49:36
	 */
	public void removeOpenBidGeneralVitemByProjectId(String projectId);
	
	/**
	 * FuncName:getOpenBidGeneralVitemListByOpenbidGeneralview
	 * Description :根据开标一览表获得开标一览表明细
	 * @param openbidGeneralviewId
	 * @return  List<OpenBidGeneralVitem>
	 * @author liuke
	 * @Create Date: 2010-12-15上午11:47:01 
	 * @Modifier liuke
	 * @Modified Date: 2010-12-15上午11:47:01 by 
	 */
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemListByOpenbidGeneralview(String openbidGeneralviewId);
}
