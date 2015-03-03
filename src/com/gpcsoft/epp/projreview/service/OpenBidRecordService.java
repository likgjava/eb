package com.gpcsoft.epp.projreview.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;

public interface OpenBidRecordService extends BaseGenericService<OpenBidRecord> {

	/**
	 * FuncName:getopenBidRecordListByPackId
	 * Description:根据包件ID得到开标记录列表
	 * @param   packId:包组主键
	 * @return  List
	 * @author liuke
	 * @Create Date: 2010-6-20下午04:09:37 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-20下午04:09:37 
	 */
	@SuppressWarnings("unchecked")
	public List getopenBidRecordListByPackId(String packId);
	 
	/**
	  * FuncName:saveOpenBidRecordBySubProjectId
	  * Description :根据包组进行开标  
	  * @param   projectId：项目Id,subProjectId：包组Id
	  * @return  Project
	  * @author 	 liuke
	  * @Create Date: 2010-9-17下午06:08:46 
	  * @Modifier   liuke
	  * @Modified Date: 2010-9-17下午06:08:46  
	  */
	 public Project saveOpenBidRecordBySubProjectId(String projectId,String subProjectId)throws Exception;
	 
	 /** 
	  * FuncName:getSubProjectByProjectId
	  * Description:根据项目主键获取对应的所有包组信息
	  * @param projectId:项目主键
	  * @return List<Project>
	  * @author Administrator
	  * @Create Date: 2010-5-21下午02:25:13   
	  * @Modifier Administrators
	  * @Modified Date: 2010-5-21下午02:25:13 
	  */
	public List<Project> getSubProjectByProjectId(String projectId)throws Exception;
	
	/**
	 * FuncName:getAllOpenBidRecordListByProjectId
	 * Description :根据项目Id获得所有开标记录对象   
	 * @param   projectId:项目主键
	 * @return  List<OpenBidRecord>
	 * @author liuke
	 * @Create Date: 2010-10-9下午05:46:34 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-9下午05:46:34 
	 */
	public List<OpenBidRecord> getAllOpenBidRecordListByProjectId(String projectId)throws Exception;
	
	/**
	  * FuncName:getOpenBidRecordListByOpenBidIdAndSupplierId
	  * Description :根据开标Id和供应商ID获得开标记录对象
	  * @param   openBidId:开标ID
	  * @param   supplierId:供应商ID
	  * @return  OpenBidRecord
	  * @author  shengn
	  * @Create  Date:2011-10-12 15:59  
	  */
	public OpenBidRecord getOpenBidRecordListByOpenBidIdAndSupplierId(String openBidId,String supplierId)throws Exception;
	
}
