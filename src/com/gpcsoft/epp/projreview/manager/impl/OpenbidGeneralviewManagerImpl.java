package com.gpcsoft.epp.projreview.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.projreview.dao.OpenbidGeneralviewDao;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.manager.OpenbidGeneralviewManager;

@Repository
public class OpenbidGeneralviewManagerImpl extends BaseManagerImpl<OpenbidGeneralview> implements OpenbidGeneralviewManager {

	@Autowired(required=true)  @Qualifier("openbidGeneralviewDaoHibernate")
	private OpenbidGeneralviewDao openbidGeneralviewDaoHibernate;
	
	/**
	 * FuncName:getAllByProjectId
	 * Description: 根据项目主键获取所有开标一览表
	 * @param projectId:项目主键
	 * @return List<OpenbidGeneralview>
	 * @author wanghz
	 * @Create Date 2010-8-1 下午02:30:11 
	 */
	public List<OpenbidGeneralview> getAllByProjectId(String projectId)throws Exception {
		return openbidGeneralviewDaoHibernate.getAllByProjectId(projectId);
	}

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
	public OpenbidGeneralview getOpenbidGeneralviewByOpenBidRecordId(String bidRecordId) {
		return openbidGeneralviewDaoHibernate.getOpenbidGeneralviewByOpenBidRecordId(bidRecordId);
	}

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
	public void removeAllOpenbidGeneralviewByProjectId(String projectId) {
		openbidGeneralviewDaoHibernate.removeAllOpenbidGeneralviewByProject(projectId);
	}
}
