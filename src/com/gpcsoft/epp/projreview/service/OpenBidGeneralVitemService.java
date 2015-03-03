package com.gpcsoft.epp.projreview.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;

public interface OpenBidGeneralVitemService extends BaseGenericService<OpenBidGeneralVitem> {

	/**
	 * FuncName:saveAllOpenBidGeneralVitem
	 * Description:保存开标一览表明细并保存开标一览表信息  
	 * @param   projectId:项目主键,allValues:??
	 * @return  Project
	 * @author lic
	 * @Create Date: 2010-10-11下午01:13:49 
	 * @Modifier    lic
	 * @Modified Date: 2010-10-11下午01:13:49 
	 */
	public Project saveAllOpenBidGeneralVitem(String projectId,String allValues);
	
	/**
	 * FuncName:getOpenBidGeneralVitemListByOpenbidGeneralview
	 * Description :根据开标一览表获得开标一览表明细
	 * @param openbidGeneralviewId:开标一览表主键
	 * @return  List<OpenBidGeneralVitem>
	 * @author 	liuke
	 * @Create Date: 2010-12-15上午11:29:22 
	 * @Modifier   liuke
	 * @Modified Date: 2010-12-15上午11:29:22 
	 */
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemListByOpenbidGeneralview(String openbidGeneralviewId);
	
	
	/**
	 * FuncName:saveAllOpenBidGeneralVitemSec
	 * Description:保存修改开标一览表明细并保存开标一览表信息  
	 * @param   projectId:项目主键,allValues:??
	 * @return  Project
	 * @author lic
	 * @Create Date: 2010-10-11下午01:13:49 
	 * @Modifier    lic
	 * @Modified Date: 2010-10-11下午01:13:49 
	 */
	public Project saveAllOpenBidGeneralVitemSec(String projectId,String allValues)throws Exception;
	
	
	/**
	 * FuncName: getOpenBidGeneralVitemListByProjectId
	 * Description :  根据项目主键获得开标一览表明细对象
	 * @param 
	 * @param projectId
	 * @return List<OpenBidGeneralVitem>
	 * @author: liuke
	 * @Create Date:2011-9-26  下午01:53:40
	 * @Modifier: liuke
	 * @Modified Date:2011-9-26  下午01:53:40
	 */
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemListByProjectId(String projectId);
	
	
	/** 
	 * FuncName : finishOpenBidGeneralVitem
	 * Description :  完成开标一览表
	 * Create Date: 2011-11-18下午04:24:58 by yangx  
	 * Modified Date: 2011-11-18下午04:24:58 by yangx
	 * @param   projectId：项目ID
	 * @return  OpenBidGeneralVitem
	 */
	public OpenBidGeneralVitem finishOpenBidGeneralVitem(String projectId) throws Exception;
	
	/**
	 * 保存开标一览表,该业务方法除保存List<OpenBidGeneralVitem>外，还级联保存了GenviewDefine、OpenbidGeneralview
	 * @param openBidGeneralVitemList
	 * @param tenderProjectId
	 * @param supplierId
	 * @param bidId
	 * @param openBidRecordId
	 * @author zhouzhanghe
	 * @created date 2011-11-19 11:30
	 */
	public void saveOpenBidGeneralVitem(List<OpenBidGeneralVitem> openBidGeneralVitemList, String tenderProjectId, String supplierId, String bidId, String openBidRecordId);
}
