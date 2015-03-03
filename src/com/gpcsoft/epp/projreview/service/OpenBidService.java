package com.gpcsoft.epp.projreview.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidItems;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.projreview.domain.OpenBid;

public interface OpenBidService extends BaseGenericService<OpenBid> {
	
	/**
	 * FuncName:getOpenBidByProjectId
	 * Description:通过项目主键得到开标对象 
	 * @param   projectId:项目主键
	 * @return  OpenBid
	 * @author liuke
	 * @Create Date: 2010-5-24下午01:04:00 
	 * @Modifier liuke
	 * @Modified Date: 2010-5-24下午01:04:00 
	 */
	public 	OpenBid getOpenBidByProjectId(String projectId);
	
	/** 
	 * FuncName:isJudgeSupplierNum
	 * Description :  判断投标供应商数量是否符合要求
	 * @param   projectId：项目Id;subProjectId：包组Id;
	 * @return  Boolean
	 * @author yangx
	 * @Create Date: 2010-11-4下午04:50:05 
	 * @Modifier  yangx
	 * @Modified Date: 2010-11-4下午04:50:05    
	 */
	public Boolean isJudgeSupplierNum(String projectId,String subProjectId) throws Exception;
	 
	/**
	  * FuncName:getOpenBidByProjectIdAndPackId
	  * Description :通过项目和包组得到开标记录  
	  * @param   projectId:项目主键s,subProjectId:包组主键 
	  * @return  List<OpenBid>
	  * @author liuke
	  * @Create Date: 2010-9-19下午01:49:44
	  * @Modifier   liuke
	  * @Modified Date: 2010-9-19下午01:49:44  
	  */
	 public List<OpenBid> getOpenBidByProjectIdAndPackId(String projectId,String subProjectId)throws Exception;
	 
	 /**
	  * FuncName:getOpenBidByProjectIdAndPackId
	  * Description :通过项目和包组得到对应的开标记录  
	  * @param   prjId:项目编号,packId:包组编号 
	  * @return  OpenBid
	  * @author liuy
	  * @Create Date: 2011-11-5下午16:49:44
	  * @Modifier   liuy
	  * @Modified Date: 2011-11-5下午16:49:44
	  */
	 public OpenBid getOpenBidByPrjIdAndPackId(String prjId,String packId)throws Exception;
	 
	 
	 /**
	   * FuncName:isSupplyerInBailrecord
	   * Description : 用以判断是否所有供应商都录入保证金缴纳情况
	   * @param projectId：项目Id,signUprecordId：报名Id
	   * @return  Boolean
	   * @author shenjianzhuang
	   * @Create Date: 2010-9-20上午10:19:27 
	   * @Modifier  shenjianzhuang
	   * @Modified Date: 2010-9-20上午10:19:27   
	   */		 	   	
	public Boolean isSupplyerInBailrecord(String projectId,String signUprecordId);
	
	/** 
	 * FuncName:isPacHaveWorkMemember
	 * Description:判断包组是否组建开标小组成员
	 * @param   projectId：项目主键,subProjectId:包组主键
	 * @return  Boolean
	 * @author yangx
	 * @Create Date: 2010-11-4下午05:02:27 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-4下午05:02:27    
	 */
	public Boolean isPacHaveWorkMemember(String projectId,String subProjectId);
	 
	 /**
	  * FuncName:isOpenBided
	  * Description :判断该包组是否已经开标  
	  * @param   String subProjectId 包组ID
	  * @return  true 表示开标  false 表示未开标
	  * @author liuke
	  * @Create Date: 2010-10-27上午10:59:26 
	  * @Modifier liuke
	  * @Modified Date: 2010-10-27上午10:59:26  
	  */
	 public Boolean isOpenBided(String subProjectId);
	 
	 
		/**
		 * 
		 * Description :线下新增投标记录  
		 * Create Date: 2010-11-18下午04:18:42 by liuke  Modified Date: 2010-11-18下午04:18:42 by liuke
		 * @param packIds       包件ID       
		 * @param Bid
		 * @param congFactorResponseList    指标响应List     
		 * @param bidItemsList 投标品目
		 * @return Bid
		 * @Exception
		 */
		public Bid saveUnderLineOpenBid(Bid bid,
				List<CongFactorResponse> congFactorResponseList, String[] packIds,
				List<BidItems> bidItemsList)throws Exception;
		
		
		
		
		/**
		 * Description : 线下修改投标对象 
		 * Create Date: 2010-11-18下午04:18:42 by liuke  Modified Date: 2010-11-18下午04:18:42 by liuke
		 * @param packIds  包件ID       
		 * @param Bid      
		 * @param congFactorResponseList   指标响应List
		 * @param bidItemsList 投标品目
		 * @return void
		 * @Exception
		 */
		public Bid updateUnderLineOpenBid(Bid bid,
				List<CongFactorResponse> congFactorResponseList, String[] packIds,
				List<BidItems> bidItemsList) throws Exception;
	 
	}
