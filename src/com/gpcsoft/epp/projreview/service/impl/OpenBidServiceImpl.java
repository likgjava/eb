package com.gpcsoft.epp.projreview.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.bid.dao.BidItemsDao;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidItems;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.manager.BidManager;
import com.gpcsoft.epp.bid.manager.BidPackageManager;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;
import com.gpcsoft.epp.eval.manager.CongFactorResponseManager;
import com.gpcsoft.epp.eval.manager.PackCongFactorResponseManager;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projreview.dao.OpenBidDao;
import com.gpcsoft.epp.projreview.dao.OpenBidRecordDao;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.manager.OpenBidManager;
import com.gpcsoft.epp.projreview.manager.OpenBidRecordManager;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

@Service 
public class OpenBidServiceImpl extends BaseGenericServiceImpl<OpenBid> implements OpenBidService {

	@Autowired(required=true) @Qualifier("openBidManagerImpl")
	private OpenBidManager openBidManager;
	
	@Autowired(required=true) @Qualifier("openBidRecordManagerImpl")
	private OpenBidRecordManager openBidRecordManager;
	
	@Autowired(required=true)  @Qualifier("openBidDaoHibernate")
	private OpenBidDao openBidDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projProcessRuleManagerImpl")
	private ProjProcessRuleManager projProcessRuleManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true)  @Qualifier("bidItemsDaoHibernate")
	private BidItemsDao bidItemsDao;
	
	@Autowired(required=true) @Qualifier("bidPackageManagerImpl")
	private BidPackageManager bidPackageManager;
	
	@Autowired(required=true) @Qualifier("bidManagerImpl")
	private BidManager bidManager;
	
	@Autowired(required=true) @Qualifier("congFactorResponseManagerImpl")
	private CongFactorResponseManager congFactorResponseManager;
	
	@Autowired(required=true) @Qualifier("packCongFactorResponseManagerImpl")
	private PackCongFactorResponseManager packCongFactorResponseManager;
	
	@Autowired(required=true)  @Qualifier("openBidRecordDaoHibernate")
	private OpenBidRecordDao openBidRecordDaoHibernate;
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
	public OpenBid getOpenBidByProjectId(String projectId) {
		logger.debug("\nes OpenBidServiceImpl||getOpenBidByProjectId\n");
		QueryObject<OpenBid> queryObject = new QueryObjectBase<OpenBid>() ;
		queryObject.setEntityClass(OpenBid.class);
		queryObject.setQueryParam(new QueryParam("projId",QueryParam.OPERATOR_EQ,projectId));
		OpenBid openBid = openBidManager.getOpenBidByQueryObject(queryObject);
		return openBid;
	}

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
	public Boolean isJudgeSupplierNum(String projectId,String subProjectId) throws Exception {
		logger.debug("\nes=OpenBidServiceImpl||isJudgeSupplierNum\n");
		Boolean checkValue = true;
		ProjProcessRule projProcessRule = projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.SUBPROJECTOPENBID);//查询规则是否分包开标
		if (projProcessRule!=null&&"true".equals(projProcessRule.getResValue())) {//按照包组开标
			Project project = projectManager.get(subProjectId);
			checkValue = openBidManager.isJudgeSupplierNum(project.getEbuyMethod(),project.getObjId(),project.getProjName());
		}else{//按照项目开标
			Project project = projectManager.get(projectId);
			List<Project> subProjectList = projectManager.getSubProjectByQueryObject(projectId);//根据项目查询包组
			if (subProjectList!=null&&subProjectList.size()>0) {//判断是否存在包组
				for (Project subProject:subProjectList) {//遍历包组判断是否所有包组投标供应商数量都符合条件
					checkValue = openBidManager.isJudgeSupplierNum(project.getEbuyMethod(),subProject.getObjId(),subProject.getProjName());
				}
			}else{//不存在包组,检查项目的投标供应商数量是否符合条件
				checkValue = openBidManager.isJudgeSupplierNum(project.getEbuyMethod(),project.getObjId(),project.getProjName());
			}
		}
		return checkValue;
	}
	
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
	public List<OpenBid> getOpenBidByProjectIdAndPackId(String projectId,String subProjectId) throws Exception {
		logger.debug("\nes=OpenBidServiceImpl||getOpenBidByProjectIdAndPackId\n");
		return openBidDaoHibernate.getOpenBidByProjectIdAndPackId(projectId, subProjectId);
	}
	
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
	public Boolean isSupplyerInBailrecord(String projectId,String signUprecordId) {
		logger.debug("\nes=OpenBidServiceImpl||isSupplyerInBailrecord\n");
		return openBidManager.isSupplyerInBailrecord(projectId,signUprecordId);
	}
	
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
	 public Boolean	isPacHaveWorkMemember(String projectId,String subProjectId){
		 logger.debug("\nes=OpenBidServiceImpl||isPacHaveWorkMemember\n");
		 return openBidRecordManager.isPacHaveWorkMemember(projectId,subProjectId);
	 }
	 
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
	public Boolean isOpenBided(String subProjectId) {
		logger.debug("\nes=OpenBidServiceImpl||isOpenBided\n");
		List<OpenBid> openBidList =	openBidDaoHibernate.getOpenBidBySubProjectId(subProjectId);
		if(openBidList.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	
	
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
			List<BidItems> bidItemsList) throws Exception{
		logger.debug("\nes BidServiceImpl||updateUnderLineBid\n");
		User user = AuthenticationHelper.getCurrentUser();
		if(null == bid.getObjId() || "".equals(bid.getObjId())){
			bid.setCreateTime(new Date());
			bid.setCreateUser(user);
		}else{
			bid.setModifyTime(new Date());
			bid.setUpdateTime(new Date());
			bid.setUpdateUser(user);
			// 删除投标品目
			bidItemsDao.removeBidItemsByBidId(bid.getObjId(),bidItemsList);
			
			// 1.删除投标与包件中间表
			bidPackageManager.removeBidPackageAndFactorResponseAndResponse(bid.getObjId(),packIds,congFactorResponseList);
		}
		bidManager.save(bid);
		if(congFactorResponseList.size()>0){
			for(String packId:packIds){
				// 2.保存投标与包件中间表
				BidPackage bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId,bid.getObjId());
				if(null == bidPackage.getObjId() || "".equals(bidPackage.getObjId())){
					bidPackage.setCreateTime(new Date());
				}
				Project pack = new Project();
				pack.setObjId(packId);
				bidPackage.setPack(pack);
				bidPackage.setBid(bid);
				Float priceSum = 0.0F;
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if (packId.equals(bidItems.getPackId())) {
							priceSum += Float.parseFloat(bidItems.getPrice()+"");
						}
					}
				}
				bidPackage.setQuotesum(priceSum);
				bidPackageManager.save(bidPackage);
				
				// 2.2更新指标响应
				for(CongFactorResponse congFactorResponse:congFactorResponseList){
					if (packId.equals(congFactorResponse.getPackIds())) {
						if("".equals(congFactorResponse.getObjId())){
							congFactorResponse.setObjId(null);
						}
						congFactorResponseManager.save(congFactorResponse);
						// 2.3保存指标响应与 投标包件中间表
						PackCongFactorResponse packCongFactorResponse = new PackCongFactorResponse();
						packCongFactorResponse.setCongFactorResponse(congFactorResponse);
						packCongFactorResponse.setBidPackageId(bidPackage.getObjId());
						packCongFactorResponseManager.save(packCongFactorResponse);
					}
				}
				// 2.3保存投标品目
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if(packId.equals(bidItems.getPackId())){
								bidItems.setObjId(null);
							bidItems.setBidPackId(bidPackage.getObjId());
							bidItemsDao.save(bidItems);
						}
					}
				}
			}
		}else{
			for(String packId:packIds){
				// 3.保存投标与包件中间表
				BidPackage  bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId,bid.getObjId());
				if(null == bidPackage){
					bidPackage = new BidPackage();
					bidPackage.setCreateTime(new java.util.Date());
				}
				Project pack = new Project();
				pack.setObjId(packId);
				bidPackage.setPack(pack);
				bidPackage.setBid(bid);
				Float priceSum = 0.0F;
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if (packId.equals(bidItems.getPackId())) {
							priceSum += Float.parseFloat(bidItems.getPrice()+"");
						}
					}
				}
				bidPackage.setQuotesum(priceSum);
				bidPackageManager.save(bidPackage);
				// 4保存投标品目
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if(packId.equals(bidItems.getPackId())){
								bidItems.setObjId(null);
							bidItems.setBidPackId(bidPackage.getObjId());
							bidItemsDao.save(bidItems);
						}
					}
				}
			}
		}
		
		//删除开标记录
		openBidRecordDaoHibernate.removeAllOpenBidRecordByBidId(bid.getObjId());
			
		//保存开标记录
		openBidRecordManager.saveOpenBidRecord(bid,packIds);
		projectManager.saveProjProcessStatus(bid.getProject().getObjId(),ProjProcessStatusEnum.SUPPLIERS_BID);/** 保存项目实施状态 */
		return bid;
	}
	
	
	/**
	 * Description : 线下保存投标对象 Create Date: 2010-5-17下午02:34:27 by liuke
	 * Modified Date: 2010-5-17下午02:34:27 by liuke
	 * @param packIds  包件ID       
	 * @param Bid      
	 * @param congFactorResponseList   指标响应List
	 * @param bidItemsList 投标品目
	 * @return void
	 * @Exception
	 */
	public Bid saveUnderLineOpenBid(Bid bid,
			List<CongFactorResponse> congFactorResponseList, String[] packIds,
			List<BidItems> bidItemsList)throws Exception {
		logger.debug("\n OpenBidServiceImpl||saveUnderLineOpenBid\n");
		User user = AuthenticationHelper.getCurrentUser();
		if (null == bid.getObjId() || "".equals(bid.getObjId())) {
			bid.setCreateTime(new Date());
			bid.setCreateUser(user);
		} else {
			bid.setModifyTime(new Date());
			bid.setUpdateTime(new Date());
			bid.setUpdateUser(user);
			// 1.删除投标与包件中间表
			bidPackageManager.removeBidPackageAndFactorResponseAndResponse(bid.getObjId(),packIds,congFactorResponseList);
		}
		bidManager.save(bid);
		if (congFactorResponseList.size()>0) {
			for(String packId:packIds){
				// 2.保存投标与包件中间表
				BidPackage bidPackage = new BidPackage();
				Project pack = new Project();
				pack.setObjId(packId);
				bidPackage.setPack(pack);
				bidPackage.setBid(bid);
				Float priceSum = 0.0F;
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if (packId.equals(bidItems.getPackId())) {
							priceSum += Float.parseFloat(bidItems.getPrice()+"");
						}
					}
				}
				bidPackage.setQuotesum(priceSum);
				bidPackageManager.save(bidPackage);
				// 2.2更新指标响应
				for(CongFactorResponse congFactorResponse:congFactorResponseList){
					if (packId.equals(congFactorResponse.getPackIds())) {
						if("".equals(congFactorResponse.getObjId())){
							congFactorResponse.setObjId(null);
						}
						congFactorResponseManager.save(congFactorResponse);
						// 2.3保存指标响应与 投标包件中间表
						PackCongFactorResponse packCongFactorResponse = new PackCongFactorResponse();
						packCongFactorResponse.setCongFactorResponse(congFactorResponse);
						packCongFactorResponse.setBidPackageId(bidPackage.getObjId());
						packCongFactorResponseManager.save(packCongFactorResponse);
					}
				}
				// 2.3保存投标品目
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if(packId.equals(bidItems.getPackId())){
							bidItems.setObjId(null);
							bidItems.setBidPackId(bidPackage.getObjId());
							bidItemsDao.save(bidItems);
						}
					}
				}
			}
		}else{
			for(String packId:packIds){
				// 3.保存投标与包件中间表
				BidPackage bidPackage = new BidPackage();
				Project pack = new Project();
				pack.setObjId(packId);
				bidPackage.setPack(pack);
				bidPackage.setBid(bid);
				Float priceSum = 0.0F;
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if (packId.equals(bidItems.getPackId())) {
							priceSum += Float.parseFloat(bidItems.getPrice()+"");
						}
					}
				}
				bidPackage.setQuotesum(priceSum);
				bidPackageManager.save(bidPackage);
				// 4保存投标品目
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if(packId.equals(bidItems.getPackId())){
							bidItems.setObjId(null);
							bidItems.setBidPackId(bidPackage.getObjId());
							bidItemsDao.save(bidItems);
						}
					}
				}
			}
		}
		bid.setUser(user);
		bid.setParentBizId(bid.getProject().getObjId());
		projectManager.saveProjProcessStatus(bid.getProject().getObjId(),ProjProcessStatusEnum.SUPPLIERS_BID);/** 保存项目实施状态 */
		
		//保存开标记录
		openBidRecordManager.saveOpenBidRecord(bid,packIds);
		return bid;
	}
	
	/**
	  * FuncName:getOpenBidByProjectIdAndPackId
	  * Description :通过项目和包组得到对应的开标记录  
	  * @param   prjId:项目ID,packId:包组Id
	  * @return  OpenBid
	  * @author liuy
	  * @Create Date: 2011-11-5下午16:49:44
	  * @Modifier   liuy
	  * @Modified Date: 2011-11-5下午16:49:44
	  */
	public OpenBid getOpenBidByPrjIdAndPackId(String prjId,
			String packId) throws Exception {
		List<OpenBid> openBidList = openBidDaoHibernate.getOpenBidByProjectIdAndPackId(prjId, packId);
		OpenBid openBid = null;
		if(openBidList != null && !openBidList.isEmpty()){
			openBid = (OpenBid)openBidList.get(0);
		}
		return openBid;
	}

	
}
