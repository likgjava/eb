package com.gpcsoft.epp.bid.service;

import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidItems;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.SubFileDTO;
import com.gpcsoft.srplatform.auth.domain.User;

public interface BidService extends BaseGenericService<Bid> {
	/**
	 * Description : 新增投标对象 Create Date: 2010-5-17下午02:34:27 by liuke
	 * Modified Date: 2010-5-17下午02:34:27 by liuke
	 * @param packIds       包件ID       
	 * @param Bid
	 * @param congFactorResponseList    指标响应List     
	 * @param bidItemsList 投标品目
	 * @return Bid
	 * @Exception
	 */
	public Bid saveBid(Bid bid,
			List<CongFactorResponse> congFactorResponseList, String[] packIds,
			List<BidItems> bidItemsList);
	
	/**
	 * Description : 修改投标对象 Create Date: 2010-5-17下午02:34:27 by liuke
	 * Modified Date: 2010-5-17下午02:34:27 by liuke
	 * @param packIds  包件ID       
	 * @param Bid      
	 * @param congFactorResponseList   指标响应List
	 * @param bidItemsList 投标品目
	 * @return void
	 * @Exception
	 */
	public Bid updateBid(Bid bid,
			List<CongFactorResponse> congFactorResponseList, String[] packIds,
			List<BidItems> bidItemsList);
		
	
	/** 
	 * Description :  获取供应商投标详情
	 * Create Date: 2011-7-8上午10:40:31 by sunl  Modified Date: 2011-7-8上午10:40:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBidInfoList(String projectId,String projId,String supplierId) throws Exception;
		
	/**
	 * 
	 * Description :根据项目主键和供应商得到所有投标对象列表 Create Date: 2010-6-24上午11:06:12 by
	 * liuke Modified Date: 2010-6-24上午11:06:12 by liuke
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	public List<Bid> getBidListByProjectIdAndUserId(String projectId, User user);

	/**
	 * 
	 * Description :  通过项目获得投标信息列表
	 * Create Date: 2010-12-15上午10:55:45 by liuke  Modified Date: 2010-12-15上午10:55:45 by l
	 * @param projectId
	 * @param user
	 * @return
	 * @return  List<Bid>
	 * @Exception
	 */
	public List<Bid> getBidListByProject(String projectId);
	
	
	/**
	 * Description :根据项目主键得到投标对象列表 Create Date: 2010-6-28下午05:26:03 by liuke
	 * Modified Date: 2010-6-28下午05:26:03 by liuke
	 * @param
	 * @return
	 * @Exception
	 */
	public List<Bid> getBidListByProjectId(String subProjectId);

	/**
	 * @Description: 根据投标ID获取投标投标包件,只返回包件ID
	 * @param bidId
	 * @return List<Project>
	 * @throws Exception
	 * @Create Date 2010-8-5 上午10:42:22 By wanghz
	 */
	public List<Project> getBidPackByBidId(String bidId);


	/**
	 * 
	 * Description : 采购方式为竞争性谈判时，录入谈判结果 Create Date: 2010-8-17下午04:21:32 by
	 * shenjianzhuang Modified Date: 2010-8-17下午04:21:32 by shenjianzhuang
	 * @param bid
	 * @param congFactorResponseList
	 * @param packIds
	 * @return void
	 * @Exception
	 */
	public void saveOrUpdateResult(Bid bid,
			List<CongFactorResponse> congFactorResponseList, String[] packIds);
	
	
	
	
	/**
	 * 
	 * Description :是否可以投标  
	 * Create Date: 2010-8-30上午10:04:27 by liuke  Modified Date: 2010-8-30上午10:04:27 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isCanBid(String projectId, String supplierId,BailRecord bailRecord)throws EsException;

	/** 
	 * Description :  根据招标项目ID/包组ID查询所有相关的投标情况
	 * Create Date: 2010-8-30下午08:29:26 by liuy  Modified Date: 2010-8-30下午08:29:26 by liuy
	 * @param objId
	 * @return
	 */
	public List<BidPackage> getBidPackageListByProjectId(String objId) throws Exception;
	
	/**
	 * @Description: 根据包件ID获取投标条目
	 * @param packIds 包件ID
	 * @param bidId 投标ID
	 * @return List<BidItems>
	 * @throws Exception
	 * @Create Date 2010-10-8 上午10:15:18 By wanghz
	 */
	public List<BidItems> getBidItemsByPackIds(String[] packIds, String bidId) throws EsException;
	
	/**
	 * 
	 * Description :通过项目主键得到投标列表  
	 * Create Date: 2010-5-26上午09:58:30 by liuke  Modified Date: 2010-5-26上午09:58:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Bid> getAllBidByProjectId(String projectId);
	/**
	 * Description :  根据供应商ID和项目ID获取供应商投标对象
	 * Create Date: 2010-10-27上午11:42:10 by shenjianzhuang  Modified Date: 2010-10-27上午11:42:10 by shenjianzhuang
	 * @param supplierid
	 * @param projectId
	 * @return  Bid
	 * @Exception	
	 */	
	public Bid getBidBySupplierid(String supplierid,String projectId);
	
	
	/**
	 * FuncName: checkBidFileComplete
	 * Description :  检测投标文件是否上传完整
	 * @param 
	 * @return boolean
	 * @author: liuke
	 * @Create Date:2011-5-11  上午11:32:48
	 * @Modifier: liuke
	 * @Modified Date:2011-5-11  上午11:32:48
	 */
	public boolean checkBidFileComplete(String path)throws Exception;
	
	
	
	 /**
	  * FuncName: checkBidFileMd5Complete
	  * Description :  检测文件MD5值是否一致
	 * @param xml
	  * @param 
	  * @return String
	  * @author: liuke
	  * @Create Date:2011-5-20  下午03:53:24
	  * @Modifier: liuke
	  * @Modified Date:2011-5-20  下午03:53:24
	  */
	 public boolean checkBidFileMd5Complete(List<SubFileDTO> list,String path)throws Exception;

	 
	 /**
	  * FuncName: saveBidForUE
	  * Description :  创建或修改对象
	  * @param 
	  * @return Bid
	  * @author: liuke
	  * @Create Date:2011-6-22  下午03:18:47
	  * @Modifier: liuke
	  * @Modified Date:2011-6-22  下午03:18:47
	  */
	 public Bid saveBidForClient(String OrgCode,String projCode,String packCode)throws Exception;
	 
	 	/**
		 * FuncName: saveBidAnonymousForClient
		 * Description :  保存匿名投标信息
		 * @param 
		 * @param bidNo
		 * @param projCode
		 * @param packCode
		 * @return
		 * @throws Exception Bid
		 * @author: shenjz
		 * @Create Date:2011-12-13  上午11:39:16
		 * @Modifier: shenjz
		 * @Modified Date:2011-12-13  上午11:39:16
		 */
	public Bid saveBidAnonymousForClient(String bidNo,String projCode,String packCode)throws Exception;

		/**
		 * FuncName: getBidByProjCode
		 * Description :  根据项目编号和供应商Id获取到供应商投标对象
		 * @param 
		 * @param supplierId
		 * @param projectId
		 * @return Bid
		 * @author: shenjz
		 * @Create Date:2011-6-22  下午04:24:31
		 * @Modifier: shenjz
		 * @Modified Date:2011-6-22  下午04:24:31
		 */
		public Bid getBidByProjCode(String projectCode,String supplierId);
		
		/**
		 * 
		 * Description :根据项目主键和供应商得到所有投标对象列表  
		 * Create Date: 2010-6-24上午11:06:12 by liuke Modified Date: 2010-6-24上午11:06:12 by liuke
		 * @param   
		 * @return  
		 * @Exception
		 */
		public List<Bid> getBidListByProjectIdAndUserId(String projectId ,String supplierId);
		
		
		/**
		 * FuncName: withdrawTender
		 * Description :撤标  
		 * @param  String projCode //项目编号
		 * @param  String packCode //包组编号
		 * @param  String username //用户名
		 * @param  String password //密码
		 * @author: liuke
		 * @Create Date:2011-6-28  上午09:40:35
		 * @Modifier: liuke
		 * @Modified Date:2011-6-28  上午09:40:35
		 */
		public BidPackage saveWithdrawTender(String projCode,String packCode,String username,String password)throws Exception;
		
		/**
		 * FuncName: saveWithdrawAnonymousTender
		 * Description :  匿名撤标
		 * @param projCode
		 * @param packCode
		 * @param bidNo
		 * @throws Exception BidPackage
		 * @author: shenjz
		 * @Create Date:2011-12-13  下午02:57:36
		 * @Modifier: shenjz
		 * @Modified Date:2011-12-13  下午02:57:36
		 */
		public BidPackage saveWithdrawAnonymousTender(String projCode,String packCode,String bidNo)throws Exception;
		
		/**
		 * FuncName: getTenderInfoXml
		 * Description : 获得投标文件信息xml
		 * @param 
		 * @return String
		 * @author: liuke
		 * @Create Date:2011-6-28  下午01:30:32
		 * @Modifier: liuke
		 * @Modified Date:2011-6-28  下午01:30:32
		 */
		public String getTenderInfoXml(String tempPath, String path)throws Exception;
		
		
		/**
		 * FuncName: saveConfirmTender
		 * Description :  保存确认投标
		 * @param  projCode
		 * @param  packCode
		 * @param  username
		 * @param  password
		 * @author: liuke
		 * @Create Date:2011-6-29  上午10:53:13
		 * @Modifier: liuke
		 * @Modified Date:2011-6-29  上午10:53:13
		 */
		public BidPackage saveConfirmTender(String projCode,String packCode,String username,String password)throws Exception;
		
		/**
		 * FuncName: saveConfirmAnonymousTender
		 * Description :  保存匿名投标
		 * @param bidNo:匿名投标编号
		 * @param projCode
		 * @param packCode
		 * @return
		 * @throws Exception BidPackage
		 * @author: shenjz
		 * @Create Date:2011-12-13  下午01:49:17
		 * @Modifier: shenjz
		 * @Modified Date:2011-12-13  下午01:49:17
		 */
		public BidPackage saveConfirmAnonymousTender(String projCode, String packCode,String bidNo) throws Exception;
		
		/**
		 * FuncName: saveBidAndBidPackage
		 * Description :  保存投标与投标包件记录
		 * @param 
		 * @param supplierId
		 * @param projId
		 * @param packId
		 * @param bidQuoteSum
		 * @param bidLinker void
		 * @author: liuke
		 * @Create Date:2011-9-29  上午10:57:12
		 * @Modifier: liuke
		 * @Modified Date:2011-9-29  上午10:57:12
		 */
		public Project saveBidAndBidPackage(String bailRecordValue);
		
		/**
		 * FuncName: getBidAnonymousListByProjectIdAndBidNO
		 * Description :  根据项目主键和匿名投标号得到所有投标对象列表  
		 * @param 
		 * @param projectId
		 * @param bidNo
		 * @return List<Bid>
		 * @author: shenjz
		 * @Create Date:2011-12-19  上午11:23:03
		 * @Modifier: shenjz
		 * @Modified Date:2011-12-19  上午11:23:03
		 */
		public List<Bid> getBidAnonymousListByProjectIdAndBidNO(String projectId,String bidNo);
}
