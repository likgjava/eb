package com.gpcsoft.epp.projreview.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.projreview.dao.OpenBidGeneralVitemDao;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;
import com.gpcsoft.epp.projreview.manager.OpenBidGeneralVitemManager;

@Repository
public class OpenBidGeneralVitemManagerImpl extends BaseManagerImpl<OpenBidGeneralVitem> implements OpenBidGeneralVitemManager {

	@Autowired(required=true)  @Qualifier("openBidGeneralVitemDaoHibernate")
	private OpenBidGeneralVitemDao openBidGeneralVitemDaoHibernate;

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
	public void removeOpenBidGeneralVitemByProjectId(String projectId) {
		openBidGeneralVitemDaoHibernate.removeAllOpenBidGeneralVitemByProject(projectId);
	}

	/**
	 * FuncName:getOpenBidGeneralVitemListByOpenbidGeneralview
	 * Description :根据开标一览表获得开标一览表明细
	 * @param openbidGeneralviewId:开标一览表主键
	 * @return  List<OpenBidGeneralVitem>
	 * @author liuke
	 * @Create Date: 2010-12-15上午11:47:01 
	 * @Modifier liuke
	 * @Modified Date: 2010-12-15上午11:47:01 by 
	 */
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemListByOpenbidGeneralview(String openbidGeneralviewId) {
		return openBidGeneralVitemDaoHibernate.getOpenBidGeneralVitemListByOpenbidGeneralview(openbidGeneralviewId);
	}

}
