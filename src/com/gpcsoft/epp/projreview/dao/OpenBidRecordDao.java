package com.gpcsoft.epp.projreview.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;

public interface OpenBidRecordDao extends BaseGenericDao<OpenBidRecord> {

	/**
	 * FuncName:getOpenBidRecordByPackId
	 * Description : 根据包件ID得到开标记录对象
	 * @param   packId:包组主键
	 * @return   List<OpenBidRecord>
	 * @author 		liuke
	 * @Create Date: 2010-5-26上午10:28:46 
	 * @Modifier    liuke
	 * @Modified Date: 2010-5-26上午10:28:46 
	 */
	public List<OpenBidRecord> getOpenBidRecordByPackId(String packId);
	
	/**
	 * FuncName:getOpenBidRecordByOPenBidId
	 * Description :  根据开标记录主表得到开标记录对象
	 * @param   openBidId:开标记录主键
	 * @return  List<OpenBidRecord>
	 * @author liuke
	 * @Create Date: 2010-5-26上午10:28:46 
	 * @Modifier  liuke
	 * @Modified Date: 2010-5-26上午10:28:46 
	 */
	public List<OpenBidRecord> getOpenBidRecordByOPenBidId(String openBidId);
	
	/**
	  * FuncName:getOpenBidRecordbyBidId
	  * Description:根据投标主键得到开标子表对象
	  * @param   bidId:投标主键
	  * @return  OpenBidRecord
	  * @author liuke
	  * @Create Date: 2010-5-26下午04:24:32 
	  * @Modifier   liuke
	  * @Modified Date: 2010-5-26下午04:24:32 
	  */
	public OpenBidRecord getOpenBidRecordbyBidId(String bidId);
	
	 /**
	  * FuncName:updateOpenBidRecord
	  * Description : 更新开标子表对象 
	  * @param   openBidRecord:开标子表
	  * @return  void
	  * @author liuke
	  * @Create Date: 2010-5-26下午04:36:22 
	  * @Modifier   liuke
	  * @Modified Date: 2010-5-26下午04:36:22 
	  */
	public void updateOpenBidRecord(OpenBidRecord openBidRecord);
	
	/**
	  * FuncName:getAllByProjectId
	  * Description: 根据项目ID获取所有开标记录
	  * @param projectId:项目主键
	  * @return List<OpenBidRecord>
	  * @author wanghz
	  * @Create Date 2010-8-1 下午02:21:21  
	  */
	 public List<OpenBidRecord> getAllByProjectId(String projectId)throws Exception;
	 
	 /**
	  * FuncName:getAllOpenBidRecordByProjectId
	  * Description :根据项目得到所有开标记录  
	  * @param   projectId:项目主键
	  * @return  List<OpenBidRecord>
	  * @author 	liuke
	  * @Create Date: 2010-10-11下午06:03:53
	  * @Modifier   liuke
	  * @Modified Date: 2010-10-11下午06:03:53  
	  */
	 public List<OpenBidRecord> getAllOpenBidRecordByProjectId(String projectId)throws Exception;
	 
	 /**
	  * FuncName:getOpenBidRecordBySupplierIdAndSubProjectId
	  * Description :根据包组和供应商得到开标记录对象 
	  * @param   projectId:项目主键,supplierId供应商主键
	  * @return  OpenBidRecord
	  * @author liuke
	  * @Create Date: 2010-8-7下午06:25:01 
	  * @Modifier  liuke
	  * @Modified Date: 2010-8-7下午06:25:01 
	  */	 
	public OpenBidRecord getOpenBidRecordBySupplierIdAndSubProjectId(String projectId,String supplierId);
	
	/**
	 * FuncName:getAllNoPackByProjectId
	 * Description : 得到所有未分包的项目的开标记录信息 
	 * @param   projectId:项目主键
	 * @return  List<OpenBidRecord>
	 * @author liuke
	 * @Create Date: 2010-9-2上午10:59:36 
	 * @Modifier    liuke
	 * @Modified Date: 2010-9-2上午10:59:36  
	 */
	 public List<OpenBidRecord> getAllNoPackByProjectId(String projectId)throws Exception;
	 
	 /**
	  * FuncName:removeAllOpenBidRecordByProject
	  * Description :根据项目删除开标记录
	  * @param projectId
	  * @return  void
	  * @author liuke
	  * @Create Date: 2010-12-15下午05:35:40
	  * @Modifier   liuke
	  * @Modified Date: 2010-12-15下午05:35:40  
	  */
	 public void removeAllOpenBidRecordByProject(String projectId);
	 
	 /**
	  * FuncName: removeAllOpenBidRecordBySubProjectIdAndBidId
	  * Description :  根据包组和投标ID删除开标记录表对象
	  * @param 
	  * @param subProjId
	  * @param bidId void
	  * @author: liuke
	  * @Create Date:2011-3-11  下午08:09:09
	  * @Modifier: liuke
	  * @Modified Date:2011-3-11  下午08:09:09
	  */
	 public void removeAllOpenBidRecordByBidId(String bidId);
	 
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
