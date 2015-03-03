package com.gpcsoft.epp.projreview.manager;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;

public interface OpenBidRecordManager extends BaseManager<OpenBidRecord> {
	
	/**
	 * FuncName:getOpenBidRecordByQueryObject
	 * Description :根据开标主表对象得到开标子表对象  
	 * @param   queryObject
	 * @return  OpenBidRecord
	 * @author liuke
	 * @Create Date: 2010-5-25下午05:57:05 
	 * @Modifier  liuke
	 * @Modified Date: 2010-5-25下午05:57:05 
	 */
	@SuppressWarnings("unchecked")
	public OpenBidRecord getOpenBidRecordByQueryObject(QueryObject queryObject);
	
	/**
	 * FuncName: saveOpenBidRecord
	 * Description : 保存开标子表对象  
	 * @param   openBidRecord:开标记录
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-5-25下午06:06:24 
	 * @Modifier  liuke
	 * @Modified Date: 2010-5-25下午06:06:24  
	 */
	 public void saveOpenBidRecord(OpenBidRecord openBidRecord);
	 
	 /**
	  * FuncName:getOpenBidRecordByPackId
	  * Description :根据包件ID得到开标记录列表
	  * @param packId
	  * @return List<OpenBidRecord>
	  * @author liuke  
	  * @Create Date: 2010-5-26上午10:27:09 
	  * @Modifier  liuke
	  * @Modified Date: 2010-5-26上午10:27:09 
	  */
	 public List<OpenBidRecord> getOpenBidRecordByPackId(String packId);
     
	 /**
	  * FuncName:getOpenBidRecordByOPenBidId
	  * Description :  根据开标记录主表得到开标记录对象
	  * @param   openBidId
	  * @return  List<OpenBidRecord>
	  * @author liuke
	  * @Create Date: 2010-5-26上午10:27:09 
	  * @Modifier  liuke
	  * @Modified Date: 2010-5-26上午10:27:09  
	  */
	 public List<OpenBidRecord> getOpenBidRecordByOPenBidId(String openBidId);
     
	 /**
	  * FuncName:getOpenBidPageRecordByOPenBidId
	  * Description : 开标记录主表得到开标记录对象
	  * @param   openBidId:开标记录主键,start:起始页,pagesize:分页大小
	  * @return  Page<OpenBidRecord>
	  * @author liuke
	  * @Create Date: 2010-5-26下午03:15:33 
	  * @Modifier   liuke
	  * @Modified Date: 2010-5-26下午03:15:33  
	  */
	 public Page<OpenBidRecord> getOpenBidPageRecordByOPenBidId(String openBidId,int start,int pagesize);
	 
	 /**
	  * FuncName:getOpenBidRecordbyBidId
	  * Description :  根据投标主键得到开标子表对象
	  * @param   bidId:投标主键
	  * @return  OpenBidRecord
	  * @author liuke
	  * @Create Date: 2010-5-26下午04:24:32
	  * @Modifier  liuke
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
	  * @Modifier    liuke
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
	  * FuncName:isPacHaveWorkMemember
	  * Description :  判断包组是否组建开标小组成员
	  * @param   projectId：项目Id,subProjectId：包组Id
	  * @return  Boolean
	  * @author yangx
	  * @Create Date: 2010-11-4下午05:02:27 
	  * @Modifier    yangx
	  * @Modified Date: 2010-11-4下午05:02:27   
	  */	 	
	 public Boolean	isPacHaveWorkMemember(String projectId,String subProjectId);
	 
	 /**
	  * FuncName:saveOpenBidRecord
	  * Description :根据项目和包组信息保存开标记录  
	  * @param   bid:开标记录,packIds:包组主键
	  * @return  Project
	  * @author liuke
	  * @Create Date: 2010-11-18下午04:42:37   
	  * @author liuke
	  * @Modified Date: 2010-11-18下午04:42:37 
	  */
	 public Project saveOpenBidRecord(Bid bid,String[] packIds)throws Exception;
	 
	 /**
	  * FuncName:removeAllOpenBidRecordByProject
	  * Description :根据项目删除开标记录
	  * @param projectId
	  * @return  void
	  * @author liuke
	  * @Create Date: 2010-12-15下午05:27:01 
	  * @Modifier   liuke
	  * @Modified Date: 2010-12-15下午05:27:01 
	  */
	 public void removeAllOpenBidRecordByProject(String projectId);
	 
}
