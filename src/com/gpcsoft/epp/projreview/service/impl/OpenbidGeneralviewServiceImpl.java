package com.gpcsoft.epp.projreview.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.manager.BidManager;
import com.gpcsoft.epp.bid.manager.BidPackageManager;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;
import com.gpcsoft.epp.eval.manager.CongFactorResponseManager;
import com.gpcsoft.epp.eval.manager.PackCongFactorResponseManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projreview.dao.OpenBidRecordDao;
import com.gpcsoft.epp.projreview.dao.OpenbidGeneralviewDao;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.manager.OpenBidGeneralVitemManager;
import com.gpcsoft.epp.projreview.manager.OpenBidManager;
import com.gpcsoft.epp.projreview.manager.OpenBidRecordManager;
import com.gpcsoft.epp.projreview.manager.OpenbidGeneralviewManager;
import com.gpcsoft.epp.projreview.service.OpenbidGeneralviewService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
public class OpenbidGeneralviewServiceImpl extends BaseGenericServiceImpl<OpenbidGeneralview> implements OpenbidGeneralviewService {

	@Autowired(required=true) @Qualifier("openbidGeneralviewManagerImpl")
	private OpenbidGeneralviewManager openbidGeneralviewManager;

	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("openBidRecordManagerImpl")
	private OpenBidRecordManager openBidRecordManager;
	
	@Autowired(required=true) @Qualifier("openbidGeneralviewDaoHibernate")
	private OpenbidGeneralviewDao openbidGeneralviewDao;
	
	@Autowired(required=true)  @Qualifier("openbidGeneralviewDaoHibernate")
	private OpenbidGeneralviewDao openbidGeneralviewDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("openBidRecordDaoHibernate")
	private OpenBidRecordDao openBidRecordDaoHibernate;
	
	@Autowired(required=true) @Qualifier("bidManagerImpl")
	private BidManager bidManager;
	
	@Autowired(required=true) @Qualifier("bidPackageManagerImpl")
	private BidPackageManager bidPackageManager;
	
	@Autowired(required=true) @Qualifier("congFactorResponseManagerImpl")
	private CongFactorResponseManager congFactorResponseManager;
	
	@Autowired(required=true) @Qualifier("packCongFactorResponseManagerImpl")
	private PackCongFactorResponseManager packCongFactorResponseManager;
	
	@Autowired(required=true) @Qualifier("openBidManagerImpl")
	private OpenBidManager openBidManager;
	
	@Autowired(required=true) @Qualifier("openBidGeneralVitemManagerImpl")
	private OpenBidGeneralVitemManager openBidGeneralVitemManager;
	
	/**
	 * FuncName:getOpenbidGeneralviewList
	 * Description: 获取或生成开标一览表
	 * @param projectId:项目主键
	 * @return List<OpenbidGeneralview>
	 * @author wanghz
	 * @Create Date 2010-8-1 下午02:17:14 
	 */
	public List<OpenbidGeneralview> getOpenbidGeneralviewList(String projectId) throws Exception {
		logger.debug("\n OpenbidGeneralviewServiceImpl||getOpenbidGeneralviewList\n");
		// 1.获取开标一览表
		List<OpenbidGeneralview> openbidGeneralviewList = new ArrayList<OpenbidGeneralview>();
		// 2.获取开标记录，并且过滤开标一览表中已存在的记录
		List<OpenBidRecord> openBidRecordList = new ArrayList<OpenBidRecord>();
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(projectId);//项目规则
		if(projectRule.getIsDividePack()!= null && projectRule.getIsDividePack()){//如果项目已经分包
			 openBidRecordList = openBidRecordManager.getAllByProjectId(projectId);
		}else{ //如果没分包
			 openBidRecordList = openBidRecordDaoHibernate.getAllNoPackByProjectId(projectId);
		}
		if(projectRule.getIsDividePack()!= null && projectRule.getIsDividePack()){//如果项目已经分包
			 openbidGeneralviewList = openbidGeneralviewManager.getAllByProjectId(projectId);
		}else{ //如果没分包
			 openbidGeneralviewList = openbidGeneralviewDaoHibernate.getAllNoPackByProjectId(projectId);
		}
		for(OpenBidRecord openBidRecord:openBidRecordList){
			OpenbidGeneralview openbidGeneralview = new OpenbidGeneralview();
			Bid bid= new Bid();
			bid.setObjId(openBidRecord.getBidId());
			openbidGeneralview.setBid(bid);
			openbidGeneralview.setOpenBidRecord(openBidRecord);
			Project pack = projectManager.get(openBidRecord.getSubProjId());
			openbidGeneralview.setSubProj(pack);
			if(openBidRecord.getQuoteSum()!=null){
				openbidGeneralview.setBidQuotesum(openBidRecord.getQuoteSum().floatValue());
			}
			openbidGeneralview.setSupplierId(openBidRecord.getSupplier().getObjId());
			openbidGeneralview.setSupplierName(openBidRecord.getSupplier().getOrgName());
			openbidGeneralviewList.add(openbidGeneralview);
		}
		
		return openbidGeneralviewList;
	}
	
	/**
	 * FuncName:validateIsEndProjectPlan
	 * Description:验证项目计划是否完成
	 * @param packId:包件主键
	 * @return Boolean
	 * @author wanghz
	 * @Create Date 2010-8-1 下午11:10:46  
	 */
	public Boolean validateIsEndProjectPlan(String packId) throws Exception {
		logger.debug("\n OpenbidGeneralviewServiceImpl||validateIsEndProjectPlan\n");
		return openbidGeneralviewDao.validateIsEndProjectPlan(packId);
	}
	
	/**
	 * FuncName:saveOpenbidGeneralviewObject
	 * Description :保存开标一览表  
	 * @param   openbidGeneralview:开标一览表
	 * @return  OpenbidGeneralview
	 * @author 		liuke
	 * @Create Date: 2010-9-7上午11:28:58 
	 * @Modifier    liuke
	 * @Modified Date: 2010-9-7上午11:28:58  
	 */
	public OpenbidGeneralview saveOpenbidGeneralviewObject(OpenbidGeneralview openbidGeneralview) throws Exception {
		logger.debug("\n OpenbidGeneralviewServiceImpl||saveOpenbidGeneralviewObject\n");
		openbidGeneralviewManager.save(openbidGeneralview);
		// 获取项目ID
		Project pak = projectManager.get(openbidGeneralview.getSubProj().getObjId());
		String projectId = "";
		if (null == pak.getParentId()) {
			projectId = pak.getObjId();
		} else {
			projectId = pak.getParentId();
		}
		openbidGeneralview.setParentBizId(projectId);
		openbidGeneralview.setUser(AuthenticationHelper.getCurrentUser());
		return openbidGeneralview;
	}
	
	/**
	 * FuncName:updateOpenbidGeneralviewObject
	 * Description :修改开标一览表  
	 * @param   openbidGeneralview:开标一览表
	 * @return  OpenbidGeneralview
	 * @author 		liuke
	 * @Create Date: 2010-9-7上午11:28:58 
	 * @Modifier    liuke
	 * @Modified Date: 2010-9-7上午11:28:58 
	 */
	public OpenbidGeneralview updateOpenbidGeneralviewObject(OpenbidGeneralview openbidGeneralview) {
		logger.debug("\n OpenbidGeneralviewServiceImpl||updateOpenbidGeneralviewObject\n");
		openbidGeneralviewManager.save(openbidGeneralview);
		return openbidGeneralview;
	}
	
	/**
	 * FuncName:saveAllOpenbidGeneralviewByOpenBidRecord
	 * Description :根据开标记录子表保存开标一览表  
	 * @param  openBidRecord:开标列表,projectId:项目主键
	 * @return 		void
	 * @author 	   liuke 
	 * @Create Date: 2010-10-11下午05:09:07 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-11下午05:09:07 
	 */
	public void saveAllOpenbidGeneralviewByOpenBidRecord(List<OpenBidRecord> openBidRecord ,String projectId)throws Exception {
		logger.debug("\n OpenbidGeneralviewServiceImpl||saveAllOpenbidGeneralviewByOpenBidRecord\n");
		List<OpenbidGeneralview> openbidGeneralviewList = openbidGeneralviewManager.getAllByProjectId(projectId);
		if(null!=openBidRecord){
			if(openbidGeneralviewList.isEmpty()){
				for(OpenBidRecord bidRecord:openBidRecord){
					OpenbidGeneralview openbidGeneralview = new OpenbidGeneralview();
					openbidGeneralview.setOpenBidRecord(bidRecord);
					Bid bid = bidManager.get(bidRecord.getBidId());
					openbidGeneralview.setBid(bid);
					if(bidRecord.getQuoteSum()!=null){
						openbidGeneralview.setBidQuotesum(bidRecord.getQuoteSum().floatValue());
					}
					openbidGeneralview.setSupplierId(bidRecord.getSupplier().getObjId());
					openbidGeneralview.setSupplierName(bidRecord.getSellerName());
					Project subProject = projectManager.get(bidRecord.getSubProjId());
					openbidGeneralview.setSubProj(subProject);
					openbidGeneralviewManager.save(openbidGeneralview);
				}
			}
		}
	}
	
	/**
	 * FuncName:saveBidByOpenbidGeneralview
	 * Description:根据开标一览表对象保存投标信息
	 * @param openbidGeneralview
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-14下午03:55:32    
	 * @Modifier liuke
	 * @Modified Date: 2010-12-14下午03:55:32 
	 */
	private void saveBidByOpenbidGeneralview(OpenbidGeneralview openbidGeneralview){
		List<Bid> bidList = bidManager.getBidListByProjectIdAndUserId(openbidGeneralview.getProjectId(), openbidGeneralview.getSupplierId());
		Bid bidTemp = null;
		if(bidList.isEmpty()){  //供应商已经对该项目投标
			Bid bid = new Bid();
			OrgInfo supplier = (OrgInfo) openbidGeneralviewManager.get(openbidGeneralview.getSupplierId(), OrgInfo.class);
			Project project = (Project) projectManager.get(openbidGeneralview.getProjectId(), Project.class);
			bid.setSupplier(supplier);
			bid.setAttachRelaId(openbidGeneralview.getAttachment().getObjId());
			bid.setSupplierName(openbidGeneralview.getSupplierName());
			bid.setProject(project);
			bid.setBidQuoteSum(BigDecimal.valueOf(openbidGeneralview.getBidQuotesumTotal()));
			bid.setProjName(project.getProjName());
			bid.setProjCode(project.getProjCode());
			bidManager.save(bid);    //保存投标信息	
			bidTemp = bid;
		}else{
			bidTemp = bidList.get(0);
		}
		BidPackage bidPackage = new BidPackage(); 
		Project subProject = (Project) projectManager.get(openbidGeneralview.getSubProjectId(),Project.class);
		bidPackage.setPack(subProject);
		bidPackage.setBid(bidTemp);
		bidPackage.setQuotesum(openbidGeneralview.getBidQuotesum());
		bidPackageManager.save(bidPackage); //保存供应商报价包组信息
		List<OpenBidGeneralVitem> openBidGeneralVitemList = openbidGeneralview.getOpenBidGeneralVitemlist();
		   if(!openBidGeneralVitemList.isEmpty()){  //指标相应不为空
			   for(OpenBidGeneralVitem openBidGeneralVitem :openBidGeneralVitemList){
				   CongFactorResponse  congFactorResponse = new CongFactorResponse();
				   congFactorResponse.setRespValue(openBidGeneralVitem.getRespValue());
				   congFactorResponse.setFactorId(openBidGeneralVitem.getFactorId());
				   congFactorResponse.setFactorName(openBidGeneralVitem.getFactorName());
				   congFactorResponseManager.save(congFactorResponse); //保存指标响应信息
				   
				   PackCongFactorResponse packCongFactorResponse = new PackCongFactorResponse();    //保存指标响应中间表信息
				   packCongFactorResponse.setBidPackageId(bidPackage.getObjId());
				   packCongFactorResponse.setCongFactorResponse(congFactorResponse); 
				   packCongFactorResponseManager.save(packCongFactorResponse);//保存指标响应中间表信息
			   }
		   }	
	};
	
	/**
	 * FuncName:saveOpenBidByOpenbidGeneralview
	 * Description :根据开标一览表对象保存开标信息
	 * @param openbidGeneralview
	 * @return  void
	 * @author 	liuke
	 * @Create Date: 2010-12-14下午06:48:24 
	 * @Modifier   liuke
	 * @Modified Date: 2010-12-14下午06:48:24 
	 */
	private void saveOpenBidByOpenbidGeneralview(OpenbidGeneralview openbidGeneralview){
		OpenBid  oldOpenBid = openBidManager.getOpenBidBySubProjectId(openbidGeneralview.getSubProjectId());   
		List<Bid> bidList = bidManager.getBidListByProjectIdAndUserId(openbidGeneralview.getProjectId(), openbidGeneralview.getSupplierId());
		Bid bid = bidList.get(0);
		if(oldOpenBid==null){
			OpenBid openBid = new OpenBid();
			openBid.setProjId(openbidGeneralview.getProjectId());
			openBid.setSubProjId(openbidGeneralview.getSubProjectId());
			Project project = (Project) projectManager.get(openbidGeneralview.getProjectId(),Project.class);
			openBid.setProjName(project.getProjName());
			openBid.setProjCode(project.getProjCode());
			openBidManager.save(openBid);         //保存开标信息
			oldOpenBid = openBid;
		}
		OpenBidRecord openBidRecord = new OpenBidRecord();
		openBidRecord.setOpenBId(oldOpenBid.getObjId());
		openBidRecord.setSubProjId(openbidGeneralview.getSubProjectId());
		openBidRecord.setBidId(bid.getObjId());
		OrgInfo supplier = (OrgInfo) openbidGeneralviewManager.get(openbidGeneralview.getSupplierId(), OrgInfo.class);
		openBidRecord.setSupplier(supplier);
		openBidRecord.setQuoteSum(bid.getBidQuoteSum());
		openBidRecord.setSellerName(openbidGeneralview.getSupplierName());
		openBidRecordManager.save(openBidRecord);  //保存开标记录信息
		openbidGeneralview.setBid(bid);
		openbidGeneralview.setOpenBidRecord(openBidRecord);
		Project subProject = (Project) projectManager.get(openbidGeneralview.getSubProjectId(),Project.class);
		openbidGeneralview.setSubProj(subProject);
		openbidGeneralviewManager.save(openbidGeneralview); //保存开标一览表信息
		List<OpenBidGeneralVitem> openBidGeneralVitemList = openbidGeneralview.getOpenBidGeneralVitemlist();
		   if(!openBidGeneralVitemList.isEmpty()){  //指标相应不为空
			   for(OpenBidGeneralVitem openBidGeneralVitem :openBidGeneralVitemList){
				   openBidGeneralVitem.setOpenbidGeneralview(openbidGeneralview);
				   GenviewDefine genviewDefine = (GenviewDefine) openbidGeneralviewManager.get(openBidGeneralVitem.getGenviewDefineId(), GenviewDefine.class);
				   openBidGeneralVitem.setGenviewDefine(genviewDefine);
				   CongruousFactor congruousFactor = (CongruousFactor) openbidGeneralviewManager.get(openBidGeneralVitem.getFactorId(), CongruousFactor.class);
				   openBidGeneralVitem.setFactorId(congruousFactor.getObjId());
				   openBidGeneralVitemManager.save(openBidGeneralVitem);  //保存开标一览表指标
			   }
		   }	
	}

	/**
	 * FuncName:getOpenbidGeneralviewListByProject
	 * Description:获得开标一览表
	 * @param projectId:项目主键
	 * @return  List<OpenbidGeneralview>
	 * @author Administrator 
	 * @Create Date: 2010-12-14下午06:48:24 
	 */
	public List<OpenbidGeneralview> getOpenbidGeneralviewListByProject(String projectId) throws Exception {
		return openbidGeneralviewDaoHibernate.getAllByProjectId(projectId);
	}
	
	/**
	 * FuncName:updateOpenbidGeneralviewMessage
	 * Description:修改开标一览表信息
	 * @param openbidGeneralviewlist:开标一览表,projectId:项目主键
	 * @return  void
	 * @author 	 liuke
	 * @Create Date: 2010-12-15下午04:04:40
	 * @Modifier   liuke
	 * @Modified Date: 2010-12-15下午04:04:40 
	 */
	public void updateOpenbidGeneralviewMessage(List<OpenbidGeneralview> openbidGeneralviewlist,String projectId) {
		logger.debug("\n OpenbidGeneralviewServiceImpl||updateOpenbidGeneralviewMessage\n");
		this.removeAllOpenbidGeneralviewMessage(projectId);//根据开标一览表对象删除信息
    	for(OpenbidGeneralview openbidGeneralview:openbidGeneralviewlist){
    		this.saveBidByOpenbidGeneralview(openbidGeneralview);  //根据开标一览表对象保存投标信息
    		this.saveOpenBidByOpenbidGeneralview(openbidGeneralview);  //根据开标一览表对象保存开标信息
    	}  				    
	};
	
	/**
	 * FuncName:removeAllOpenbidGeneralviewMessage
	 * Description:根据开标一览表对象删除信息
	 * @param openbidGeneralview
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-14下午06:48:24 
	 * @Modifier  liuke
	 * @Modified Date: 2010-12-14下午06:48:24  
	 */
	private void removeAllOpenbidGeneralviewMessage(String projectId){
		openBidGeneralVitemManager.removeOpenBidGeneralVitemByProjectId(projectId);//删除开标一览表明细信息                     		  
		openbidGeneralviewManager.removeAllOpenbidGeneralviewByProjectId(projectId); //删除开标一览表信息  																		  
		openBidRecordManager.removeAllOpenBidRecordByProject(projectId);//删除开标记录信息
		openBidManager.removeAllOpenBidByProject(projectId);     //删除开标信息
		congFactorResponseManager.removeAllCongFactorResponseByProject(projectId);   //删除指标信息                                                      
		packCongFactorResponseManager.removeAllPackCongFactorResponseByProject(projectId); //删除指标包件中间表信息                                           
		bidPackageManager.removeAllBidPackageByProject(projectId);   //删除投标与包件中间表                                            
		bidManager.removeBidByProject(projectId);	//删除投标信息													
		
	}

	/**
	 * FuncName:saveOpenbidGeneralviewMessage
	 * Description :保存开标一览表及其他信息
	 * @param openbidGeneralviewlist:开标一览表,projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-14下午02:34:11 
	 * @Modifier  liuke
	 * @Modified Date: 2010-12-14下午02:34:11  
	 */
	public Project saveOpenbidGeneralviewMessage(List<OpenbidGeneralview> openbidGeneralviewlist, String projectId) {
		logger.debug("\n OpenbidGeneralviewServiceImpl||saveOpenbidGeneralviewMessage\n");
		Project project = (Project) projectManager.get(projectId,Project.class);
	    	for(OpenbidGeneralview openbidGeneralview:openbidGeneralviewlist){
	    		this.saveBidByOpenbidGeneralview(openbidGeneralview);  //根据开标一览表对象保存投标信息
	    		this.saveOpenBidByOpenbidGeneralview(openbidGeneralview);  //根据开标一览表对象保存开标信息
	    	}  
	    project.setParentBizId(projectId);
	    project.setUser(AuthenticationHelper.getCurrentUser());
		return project;
	};
}
