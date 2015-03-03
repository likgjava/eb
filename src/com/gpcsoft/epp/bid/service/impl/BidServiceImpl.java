package com.gpcsoft.epp.bid.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.epp.bid.dao.BidDao;
import com.gpcsoft.epp.bid.dao.BidItemsDao;
import com.gpcsoft.epp.bid.dao.BidPackageDao;
import com.gpcsoft.epp.bid.dao.BidReceiptDao;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidItems;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.domain.BidReceipt;
import com.gpcsoft.epp.bid.domain.BidReceiptEnum;
import com.gpcsoft.epp.bid.domain.BidSubmitTypeEnum;
import com.gpcsoft.epp.bid.manager.BidManager;
import com.gpcsoft.epp.bid.manager.BidPackageManager;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.Html2PDFConverter;
import com.gpcsoft.epp.common.utils.MD5Util;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;
import com.gpcsoft.epp.eval.manager.CongFactorResponseManager;
import com.gpcsoft.epp.eval.manager.PackCongFactorResponseManager;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMSubManager;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.SubFileDTO;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

import freemarker.template.TemplateException;

@Service 
public class BidServiceImpl extends BaseGenericServiceImpl<Bid> implements BidService {

	@Autowired(required=true) @Qualifier("bidManagerImpl")
	private BidManager bidManager;

	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("bidPackageManagerImpl")
	private BidPackageManager bidPackageManager;
	
	@Autowired(required=true) @Qualifier("congFactorResponseManagerImpl")
	private CongFactorResponseManager congFactorResponseManager;
	
	@Autowired(required=true) @Qualifier("packCongFactorResponseManagerImpl")
	private PackCongFactorResponseManager packCongFactorResponseManager;
	
	@Autowired(required=true)  @Qualifier("bidDaoHibernate")
	private BidDao bidDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bidPackageDaoHibernate")
	private BidPackageDao bidPackageDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bidItemsDaoHibernate")
	private BidItemsDao bidItemsDao;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubManagerImpl")
	private TaskPlanMSubManager taskPlanMSubManager;
	
	@Autowired(required=false)@Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManagerImpl;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true)  @Qualifier("bidReceiptDaoHibernate")
	private BidReceiptDao bidReceiptDaoHibernate;
	
	/**
	 * Description : 新增投标对象 Create Date: 2010-5-17下午02:34:27 by liuke
	 * Modified Date: 2010-5-17下午02:34:27 by liuke
	 * @param packIds       包件ID       
	 * @param Bid
	 * @param congFactorResponseList    指标响应List     
	 * @param bidItemsList 投标品目
	 * @return void
	 * @Exception
	 */
	public Bid saveBid(Bid bid,List<CongFactorResponse> congFactorResponseList, String[] packIds,List<BidItems> bidItemsList) {
		logger.debug("\nes BidServiceImpl||saveBid\n");
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
		return bid;
	}
	
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
	public Bid updateBid(Bid bid,List<CongFactorResponse> congFactorResponseList, String[] packIds,List<BidItems> bidItemsList) {
		logger.debug("\nes ProjectController||toCreateProjectByConsignId\n");
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
							if (null == bidItems.getObjId() || "".equals(bidItems.getObjId())) {
								bidItems.setObjId(null);
							}
							bidItems.setBidPackId(bidPackage.getObjId());
							bidItemsDao.save(bidItems);
						}
					}
				}
			}
		}else{
			for(String packId:packIds){
				// 3.保存投标与包件中间表
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
				// 4保存投标品目
				if(null != bidItemsList && bidItemsList.size()>0){
					for (BidItems bidItems:bidItemsList) {
						if(packId.equals(bidItems.getPackId())){
							if (null == bidItems.getObjId() || "".equals(bidItems.getObjId())) {
								bidItems.setObjId(null);
							}
							bidItems.setBidPackId(bidPackage.getObjId());
							bidItemsDao.save(bidItems);
						}
					}
				}
			}
		}
		projectManager.saveProjProcessStatus(bid.getProject().getObjId(),ProjProcessStatusEnum.SUPPLIERS_BID);/** 保存项目实施状态 */
		return bid;
	}
	
	/**
	 * Description :根据项目主键和供应商得到所有投标对象列表  
	 * Create Date: 2010-6-24上午11:06:12 by liuke Modified Date: 2010-6-24上午11:06:12 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Bid> getBidListByProjectIdAndUserId(String projectId, User user) {
		logger.debug("\nes BidServiceImpl||getBidListByProjectIdAndUserId\n");
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		String supplierId = orgInfo.getObjId();
		List<Bid> list = bidManager.getBidListByProjectIdAndUserId(projectId, supplierId);
		return list;
	}
	
	/**
	 * Description :根据项目主键得到投标对象列表  
	 * Create Date: 2010-6-28下午05:26:03 by liuke  Modified Date: 2010-6-28下午05:26:03 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Bid> getBidListByProjectId(String subProjectId) {
	   logger.debug("\nes BidServiceImpl||getBidListByProjectId\n");
	   return bidDaoHibernate.getAllBidByProjectId(subProjectId);
	}
	
	
	public List<Project> getBidPackByBidId(String bidId) {
		logger.debug("\nes BidServiceImpl||getBidPackByBidId\n");
		return bidManager.getBidPackByBidId(bidId);
	}
	
	/**
	 * Description :  采购方式为竞争性谈判时，录入谈判结果
	 * Create Date: 2010-8-17下午04:21:32 by shenjianzhuang  Modified Date: 2010-8-17下午04:21:32 by shenjianzhuang
	 * @param bid
	 * @param congFactorResponseList
	 * @param packIds
	 * @return  void
	 * @Exception 
	 */  
	public void saveOrUpdateResult(Bid bid,List<CongFactorResponse> congFactorResponseList, String[] packIds) {
		logger.debug("\nes BidServiceImpl||saveOrUpdateResult\n");
		User user = AuthenticationHelper.getCurrentUser();
		if(null == bid.getObjId() || "".equals(bid.getObjId())){
			bid.setCreateTime(new Date());
			bid.setCreateUser(user);
		}else{
			bid.setModifyTime(new Date());
			bid.setUpdateTime(new Date());
			bid.setUpdateUser(user);
			bidPackageManager.removeBidPackageAndFactorResponseAndResponse(bid.getObjId(),packIds,congFactorResponseList);// 1.删除投标与包件中间表
		}
		bid.setBidSubmitType(BidSubmitTypeEnum.NOT_ONLINE_SUBMITTYPE);//设置其投标方式为网下投标
		bidManager.save(bid);
		if(congFactorResponseList.size()>0){
			for(String packId:packIds){
				BidPackage bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId,bid.getObjId());// 2.保存投标与包件中间表
				if(null == bidPackage.getObjId() || "".equals(bidPackage.getObjId())){
					bidPackage.setCreateTime(new Date());
				}
				Project pack = new Project();
				pack.setObjId(packId);
				bidPackage.setPack(pack);
				bidPackage.setBid(bid);
				bidPackage.setQuotesum(Float.parseFloat(bid.getBidQuoteSum()+""));
				bidPackageManager.save(bidPackage);
				
				
				for(CongFactorResponse congFactorResponse:congFactorResponseList){// 2.2更新指标响应
					if("".equals(congFactorResponse.getObjId())){
						congFactorResponse.setObjId(null);
					}
					congFactorResponseManager.save(congFactorResponse);
					for(String tempPackId:congFactorResponse.getPackIds().split(",")){// 取到适应当前的指标响应
						if(tempPackId.equals(packId)){
							PackCongFactorResponse packCongFactorResponse = new PackCongFactorResponse();	// 2.3保存指标响应与 投标包件中间表
							packCongFactorResponse.setCongFactorResponse(congFactorResponse);
							packCongFactorResponse.setBidPackageId(bidPackage.getObjId());
							packCongFactorResponseManager.save(packCongFactorResponse);
						}
					}
				}
			}
		}else{
			for(String packId:packIds){
				BidPackage bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId,bid.getObjId());// 3.保存投标与包件中间表
				if(null == bidPackage.getObjId() || "".equals(bidPackage.getObjId())){
					bidPackage.setCreateTime(new Date());
				}
				Project pack = new Project();
				pack.setObjId(packId);
				bidPackage.setPack(pack);
				bidPackage.setBid(bid);
				bidPackage.setQuotesum(Float.parseFloat(bid.getBidQuoteSum()+""));
				bidPackageManager.save(bidPackage);
			}
		}
		projectManager.saveProjProcessStatus(bid.getProject().getObjId(),ProjProcessStatusEnum.SUPPLIERS_BID);//保存项目实施状态
	}


	/**
	 * 
	 * Description :是否可以投标  
	 * Create Date: 2010-8-30上午10:04:27 by liuke  Modified Date: 2010-8-30上午10:04:27 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isCanBid(String projectId, String supplierId,BailRecord bailRecord) throws EsException{
		logger.debug("\nes BidServiceImpl||isCanBid\n");
		return bidManager.isCanBid(projectId, supplierId,bailRecord);
	}

	
	/** 
	 * Description :  根据招标项目ID/包组ID查询所有相关的投标情况
	 * Create Date: 2010-8-30下午08:29:26 by liuy  Modified Date: 2010-8-30下午08:29:26 by liuy
	 * @param objId
	 * @return
	 */
	public List<BidPackage> getBidPackageListByProjectId(String projectId) throws Exception{
		logger.debug("\nes BidServiceImpl||getBidPackageListByProjectId\n");
		List<BidPackage> list = bidPackageDaoHibernate.getAllByPackId(projectId);
		return list;
	}
	
	/**
	 * @Description: 根据包件ID获取投标条目
	 * @param packIds 包件ID
	 * @param bidId 投标ID
	 * @return List<BidItems>
	 * @throws Exception
	 * @Create Date 2010-10-8 上午10:15:18 By wanghz
	 */
	public List<BidItems> getBidItemsByPackIds(String[] packIds, String bidId) throws EsException{
		logger.debug("\n=BidServiceImpl||getBidItemsByPackIds\n");
		List<BidItems> bidItems = bidItemsDao.getBidItemsByPackIds(packIds, bidId);
		if (null == bidItems || bidItems.isEmpty()) {// 获取申报书明细，组装投标条目
			bidItems = new ArrayList<BidItems>();
			for(String packId:packIds){
				List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubManager.getTaskPlanMSubsByPackId(packId);
				if (null != taskPlanMSubList && !taskPlanMSubList.isEmpty()) {
					for (TaskPlanMSub taskPlanMSub:taskPlanMSubList) {
						BidItems bidItem = new BidItems();
						bidItem.setPackId(packId);
						bidItem.setBudgetId(taskPlanMSub.getTaskPlan().getBudget().getObjId());
						bidItem.setBudgetName(taskPlanMSub.getTaskPlanSub().getBudgetName());
						bidItem.setMeasureUnit(taskPlanMSub.getTaskPlanSub().getUnit());
						bidItem.setNumber(taskPlanMSub.getTaskPlanSub().getQuantity());
//						bidItem.setPurchaseId(taskPlanMSub.getTaskPlanSub().getPurchase().getObjId());
						bidItem.setPurchaseName(taskPlanMSub.getTaskPlanSub().getPurchaseName());
						bidItem.setTaskPlansubId(taskPlanMSub.getTaskPlanSub().getObjId());
						bidItem.setPackName(projectManager.getProjectByTotal(packId).getProjName());
						bidItems.add(bidItem);
					}
				}
			}
		}else{// 获取申报书明细  过滤掉已经获取的投标条目
			if (null != packIds && packIds.length>0) {
				Set<String> tempPackIds = new HashSet<String>();
				for (BidItems bidItem:bidItems) {
					tempPackIds.add(bidItem.getPackId());
					bidItem.setPackName(projectManager.getProjectByTotal(bidItem.getPackId()).getProjName());
				}
				for (String packId:packIds) {
					if (!tempPackIds.contains(packId)) {
						List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubManager.getTaskPlanMSubsByPackId(packId);
						if (null != taskPlanMSubList && !taskPlanMSubList.isEmpty()) {
							for (TaskPlanMSub taskPlanMSub:taskPlanMSubList) {
								BidItems bidItem = new BidItems();
								bidItem.setPackId(packId);
								bidItem.setBudgetId(taskPlanMSub.getTaskPlan().getBudget().getObjId());
								bidItem.setBudgetName(taskPlanMSub.getTaskPlanSub().getBudgetName());
								bidItem.setMeasureUnit(taskPlanMSub.getTaskPlanSub().getUnit());
								bidItem.setNumber(taskPlanMSub.getTaskPlanSub().getQuantity());
//								bidItem.setPurchaseId(taskPlanMSub.getTaskPlanSub().getPurchase().getObjId());
								bidItem.setPurchaseName(taskPlanMSub.getTaskPlanSub().getPurchaseName());
								bidItem.setTaskPlansubId(taskPlanMSub.getTaskPlanSub().getObjId());
								String str = projectManager.getProjectByTotal(packId).getProjName();
								bidItem.setPackName(projectManager.getProjectByTotal(packId).getProjName());
								bidItems.add(bidItem);
							}
						}
					}
				}
			}
		}
		return bidItems;
	}


	public List<Bid> getAllBidByProjectId(String projectId) {
		logger.debug("\nes BidServiceImpl||getAllBidByProjectId\n");
		return bidManager.getAllBidByProjectId(projectId);
	}
	/**
	 * Description :  根据供应商ID和项目ID获取供应商投标对象
	 * Create Date: 2010-10-27上午11:42:10 by shenjianzhuang  Modified Date: 2010-10-27上午11:42:10 by shenjianzhuang
	 * @param Supplierid
	 * @param projectId
	 * @return  Bid
	 * @Exception	
	 */	
	public Bid getBidBySupplierid(String supplierId,String projectId){
		logger.debug("\n BidServiceImpl||getBidBySupplierid\n");
		return bidDaoHibernate.getBidBySupplierid(supplierId, projectId);
	}

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
	public List<Bid> getBidListByProject(String projectId) {
		logger.debug("\n BidServiceImpl||getBidListByProject\n");
		return  bidDaoHibernate.getBidListByProject(projectId);
	}
	
	/**
	 * FuncName: checkBidFileComplete
	 * Description :  检测投标文件是否上传完整
	 * @param 
	 * @return String
	 * @author: liuke
	 * @throws Exception 
	 * @Create Date:2011-5-11  上午11:32:48
	 * @Modifier: liuke
	 * @Modified Date:2011-5-11  上午11:32:48
	 * @Modifier: zhouzhanghe
	 * @Modified Date:2011-12-07 10:19
	 */
	public  boolean checkBidFileComplete(String path) throws Exception {
		logger.debug("\n BidServiceImpl||checkBidFileComplete\n");
		boolean success = true;
		boolean isExist = true;
		String[] fileList = FileUtil.listFiles(path);
		if(fileList!=null){  //如果文件目录存在
			String [] checkFile = {"EvalFactors.xml","EvalPoint.xml","Price.xml.enc","TenderDocument.xml"};
			isExist=this.selectFileName(fileList, checkFile);                      //判断招标文件文件结构是否完整
			if(isExist){         //判断招标文件分册类型是否完整
				String xmlPath = path+"TenderDocument.xml";
				try {
			    	org.w3c.dom.Document documentW3c = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlPath);
					org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
					org.dom4j.Document document = xmlReader.read(documentW3c);
						Element element =  document.getRootElement();
						List<Element> tenderDocumentElements = element.elements("section");
						for(Element tenderDocumentElement:tenderDocumentElements){
						  String name =	tenderDocumentElement.attributeValue("docId");
						  String fileName = tenderDocumentElement.attributeValue("fileName");
						  String endFlag = fileName.substring(fileName.indexOf("."));
						  String[] checkXmlFileArr = {name+endFlag};
						  isExist = this.selectFileName(fileList, checkXmlFileArr);	
						  if(!isExist){//判断分册文件再服务器上是否存在，如果不存在则要修改分册文档biddingStatus属性为false
							  this.updateTenderDocumentBidType(xmlPath,name);  //修改分册文档的投标状态
							  success = false;
						  }
						}
					} catch (Exception e) {
						e.printStackTrace();
						success = false;
					}
			}
		}else{  //如果文件路径不存在
			success = false;
		}
		return success;
	}
	  
	  /**
	   * FuncName: selectFileName
	   * Description :  判断旧数组中数据是否包含新数组数据
	   * @param 
	   * @param strOld
	   * @param strNew
	   * @return boolean
	   * @author: liuke
	   * @Create Date:2011-5-12  下午06:04:00
	   * @Modifier: liuke
	   * @Modified Date:2011-5-12  下午06:04:00
	   */
	 private boolean selectFileName(String[] strOld,String[]strNew){
		 logger.debug("\n BidServiceImpl||selectFileName\n"); 
		 if(strOld.length==0){
			 return false;
		 }else{
			 for(int i=0;i<strNew.length;i++){
				 String newFileName =  strNew[i];
				 boolean flag = false;
				   for(int j=0;j<strOld.length;j++){
					    String oldFileName = strOld[j];
					    if(newFileName.equals(oldFileName)){
					    	flag = true;
					    }
				   }
				 if(!flag){
					 return false;
				 }  
			 }
			 return true; 
		 }
	
	 } 
	 
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
	 public boolean checkBidFileMd5Complete(List<SubFileDTO> list,String path)throws Exception{
		logger.debug("\n BidServiceImpl||checkBidFileMd5Complete\n"); 
		boolean md5flag = true;
		String docId="";
		for(SubFileDTO subfile:list){  //获取分册文件的docId
			  String name = subfile.getDocId();
			  if(name.indexOf(".enc")!=-1&&!"Price.xml.enc".equals(name)){//判断是否加密压缩文件
				  docId = name.substring(0, name.indexOf(".")); 
			  }
			}
		for(SubFileDTO subfile:list){
		  String md5 ="";	
		  File file = new File(path+subfile.getDocId());
		  if(file.exists()){// 如果文件存在，则比较md5值
			  md5 = MD5Util.getFileMD5String(file);//读取整个文件MD5值 	
			  if(!subfile.getHashCode().equals(md5.toUpperCase())){
				  this.updateTenderDocumentBidType(path+"TenderDocument.xml",docId); //修改TenderDocument.xml中分册文件投标状态
				  md5flag = false;
			  }else{
				  this.updateTenderDocumentBidTypeForSuccess(path+"TenderDocument.xml",docId); //修改TenderDocument.xml中分册文件投标状态
			  }
		  }else{//文件不存在
			  this.updateTenderDocumentBidType(path+"TenderDocument.xml",docId); //修改TenderDocument.xml中分册文件投标状态
			  md5flag = false;
		  }
		}
		return md5flag; 
	 }

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
		public Bid getBidByProjCode(String projectCode,String supplierId){
			return bidDaoHibernate.getBidByProjCode(projectCode, supplierId);
		}
		
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
	public Bid saveBidForClient(String OrgCode,String projCode,String packCode)throws Exception {
		logger.debug("\n BidServiceImpl||saveBidForUE\n");
		String projectId = "";
		String supplierId = "";
		Bid bid = new Bid();
		String packId = "";
		BidPackage bidPackage = new BidPackage();
		Project project = projectManager.findProjectByProjCode(projCode);
		if(project!=null){
			OrgInfo supplier = userApiService.getOrgInfoByOrgCode(OrgCode);
			if(supplier!=null&&supplier.getObjId()!=null&&!"".equals(supplier.getObjId())){
				supplierId = supplier.getObjId();
			}
			Project subProject = projectManager.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){
				packId = project.getObjId();
			}else{
				packId = subProject.getObjId();
			}
			if(project!=null&&project.getObjId()!=null&&!"".equals(project.getObjId())){
				projectId = project.getObjId();
			}
			List<Bid> list = bidManager.getBidListByProjectIdAndUserId(projectId, supplierId);
			if(list!=null&&!list.isEmpty()){
				bid = list.get(0);
			}
			if(supplier!=null){
				bid.setSupplier(supplier);
				bid.setSupplierName(supplier.getOrgName());
			}
			if(project!=null){
				bid.setProject(project);
				bid.setProjName(project.getProjName());
				bid.setProjCode(project.getProjCode());
			}
			bid.setUseStatus(CommonEnum.USER_STATUS_TEMP);   //设置状态为临时
			bidManager.save(bid);     //保存投标对象
			 
			BidPackage oldBidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId()); 
			if(oldBidPackage!=null){
				bidPackage = oldBidPackage;
			}
			bidPackage.setBid(bid);
			if(subProject==null){
				bidPackage.setPack(project);
			}else{
				bidPackage.setPack(subProject);
			}
			bidPackage.setUseStatus(CommonEnum.USER_STATUS_TEMP);  //投标状态为临时
			bidPackage.setProcessStatus(BidSubmitTypeEnum.BID_NORMAL); //投标进度为正常
			bidPackageManager.save(bidPackage);
		}
		return bid;
	}
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
	public Bid saveBidAnonymousForClient(String bidNo,String projCode,String packCode)throws Exception {
		String projectId = "";
		Bid bid = new Bid();
		String packId = "";
		BidPackage bidPackage = new BidPackage();
		Project project = projectManager.findProjectByProjCode(projCode);
		if(project!=null){
			Project subProject = projectManager.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){
				packId = project.getObjId();
			}else{
				packId = subProject.getObjId();
			}
			if(project!=null&&project.getObjId()!=null&&!"".equals(project.getObjId())){
				projectId = project.getObjId();
			}
			List<Bid> list = bidManager.getBidAnonymousListByProjectIdAndBidNo(projectId, bidNo);
			if(list!=null&&!list.isEmpty()){
				bid = list.get(0);
			}
			if(project!=null){
				bid.setBidNo(bidNo);
				bid.setProject(project);
				bid.setProjName(project.getProjName());
				bid.setProjCode(project.getProjCode());
			}
			bid.setUseStatus(CommonEnum.USER_STATUS_TEMP);   //设置状态为临时
			bidManager.save(bid);     //保存投标对象
			BidPackage oldBidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId()); 
			if(oldBidPackage!=null){
				bidPackage = oldBidPackage;
			}
			bidPackage.setBid(bid);
			if(subProject==null){
				bidPackage.setPack(project);
			}else{
				bidPackage.setPack(subProject);
			}
			bidPackage.setUseStatus(CommonEnum.USER_STATUS_TEMP);  //投标状态为临时
			bidPackage.setProcessStatus(BidSubmitTypeEnum.BID_NORMAL); //投标进度为正常
			bidPackageManager.save(bidPackage);
		}
		return bid;
	}
	
	/**
	 * 
	 * Description :根据项目主键和供应商得到所有投标对象列表  
	 * Create Date: 2010-6-24上午11:06:12 by liuke Modified Date: 2010-6-24上午11:06:12 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Bid> getBidListByProjectIdAndUserId(String projectId,
			String supplierId) {
		return bidManager.getBidListByProjectIdAndUserId(projectId, supplierId);
	}
	
	/**
	 * FuncName: withdrawTender
	 * Description :撤标  
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-6-28  上午09:40:35
	 * @Modifier: liuke
	 * @Modified Date:2011-6-28  上午09:40:35
	 */
	public BidPackage saveWithdrawTender(String projCode,String packCode,String username,String password)throws Exception {
		String supplierId = "";
		String projectId = "";
		String packId = "";
		Project project = projectManager.findProjectByProjCode(projCode);
		BidPackage bidPackage = null;
		Project tempProject = null;
		if(project!=null){
			projectId = project.getObjId();
			Project subProject = projectManager.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){
				packId = project.getObjId();
				tempProject = project;
			}else{
				packId = subProject.getObjId();
				tempProject = subProject;
			}
				String decodeStringPassword = StringUtil.decodeString(password); //用Base64算法对字符串进行解码.
				String md5PassWord =  MathUtil.encryptPassWordSHA(decodeStringPassword);//密码转换成加密字符串
				List<User> userList = userApiService.getUserByUserName(username, md5PassWord);
				if(userList!=null&&!userList.isEmpty()){
					User user = userList.get(0);
					OrgInfo supplier = userApiService.getOrgInfoByUser(user);
					if(supplier!=null){
						supplierId = supplier.getObjId();
					}
				}
				List<Bid> bidList = bidManager.getBidListByProjectIdAndUserId(projectId, supplierId);
				if(bidList!=null&&!bidList.isEmpty()){
					Bid bid = bidList.get(0);
					bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId()); //获得投标包组信息
					//保存投标文件备份
					this.saveRemoveBidFile(bidPackage.getBidFile());
					//bidPackage.setBidFile(null);  //清空投标文件Id
					bidPackage.setUseStatus(CommonEnum.USER_STATUS_TEMP); //将投标状态改为临时
					bidPackage.setProcessStatus(BidSubmitTypeEnum.BID_REMOVE); //将状态修改为作废 
					bidPackageManager.save(bidPackage);
					//撤标回执相对路径
					String receiptFtlPath = project.getObjId()+File.separator+packId+File.separator+supplierId+File.separator+CommonEnum.BID_FILE_DIR;
					//生成撤标回执
					this.gengnerRatePdfByReceiptFtl(tempProject,bid,BidReceiptEnum.CB_Receipt,receiptFtlPath);
				}
		
		}
		return bidPackage;
	}
	
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
	public BidPackage saveWithdrawAnonymousTender(String projCode,String packCode,String bidNo)throws Exception {
		String projectId = "";
		String packId = "";
		Project project = projectManager.findProjectByProjCode(projCode);
		BidPackage bidPackage = null;
		Project tempProject = null;
		if(project!=null){
			projectId = project.getObjId();
			Project subProject = projectManager.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){
				packId = project.getObjId();
				tempProject = project;
			}else{
				packId = subProject.getObjId();
				tempProject = subProject;
			}
			List<Bid> bidList = bidManager.getBidAnonymousListByProjectIdAndBidNo(projectId, bidNo);
			if(bidList!=null&&!bidList.isEmpty()){
				Bid bid = bidList.get(0);
				bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId()); //获得投标包组信息
				//保存投标文件备份
				this.saveRemoveBidFile(bidPackage.getBidFile());
				//bidPackage.setBidFile(null);  //清空投标文件Id
				bidPackage.setUseStatus(CommonEnum.USER_STATUS_TEMP); //将投标状态改为临时
				bidPackage.setProcessStatus(BidSubmitTypeEnum.BID_REMOVE); //将状态修改为作废 
				bidPackageManager.save(bidPackage);
				//撤标回执相对路径
				String receiptFtlPath = project.getObjId()+File.separator+packId+File.separator+bidNo+File.separator+CommonEnum.BID_FILE_DIR;
				//生成撤标回执
				this.gengnerAnonymousRatePdfByReceiptFtl(tempProject,bid,BidReceiptEnum.CB_Receipt,receiptFtlPath);
			}
		
		}
		return bidPackage;
	}
	//删除投标文件
	private void saveRemoveBidFile(String attachRelaId)throws Exception{
		Attachment attachment = attachmentManagerImpl.getAttachmentsByRealID(attachRelaId).get(0);
		String bidFilePath =  messageSource.getMessage("epp.projectAttaPath");
		if(attachment!=null){
			String filePath = bidFilePath+attachment.getPath();   //招标文件上传路径
			ArrayList<String> fileArrayList = new ArrayList<String>();
			String[] fileList = FileUtil.listFiles(filePath);
			if(fileList != null && fileList.length >0){
		     for(int i=0;i<fileList.length;i++){
		    	String fileName = fileList[i];
		    	int n = fileName.indexOf("backupFile-");
		    	if(n!=0){  //不是备份文件
			    	fileArrayList.add(fileName);
		    	}
		     }
		    String[] tempFileList = (String[]) fileArrayList.toArray(new String[]{});  
		    String temps = ""; 
		    for(int j=0;j<tempFileList.length;j++){
		    	temps+=tempFileList[j]+",";
		     }
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String time = sdf.format(new Date());
			ZipUtils.zipFiles(new File(filePath+"backupFile-"+time+".zip"),filePath,tempFileList);
		    
			ZipUtils.deleteByFileNames(filePath,tempFileList);
		}
		}
	}
		
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
	public String getTenderInfoXml(String tempPath, String path) throws Exception{
		logger.debug("\n BidServiceImpl||getTenderInfoXml\n");
		StringBuffer xmlStrbuf = new StringBuffer();
		String xmlPath = path.replace("/", File.separator)+tempPath+File.separator+"TenderDocument.xml";
		    File tempFile = new File(xmlPath);
		    if(tempFile.exists()){
		    	try {
			    	org.w3c.dom.Document documentW3c = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlPath);
					org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
					org.dom4j.Document document = xmlReader.read(documentW3c);
						Element element =  document.getRootElement();
						List<Element> tenderDocumentElements = element.elements("section");
						for(Element tenderDocumentElement:tenderDocumentElements){
						  String biddingStatus = tenderDocumentElement.attributeValue("biddingStatus");
						  if("false".equals(biddingStatus)){
							  continue;
						  }else{
							  String name =	 tenderDocumentElement.attributeValue("name");
							  String docId = tenderDocumentElement.attributeValue("docId");
							  String fileName = tenderDocumentElement.attributeValue("fileName");
							  String type = tenderDocumentElement.attributeValue("type");
							  String endFlag = fileName.substring(fileName.lastIndexOf("."));
							  String lastTenderTime = tenderDocumentElement.attributeValue("lastTenderTime");
							  File file = new File(path+tempPath+File.separator+docId+endFlag+".enc");
							  String length = String.valueOf(file.length());
							  xmlStrbuf.append("<section>");
							  xmlStrbuf.append("<name>"+name+"</name>");
							  xmlStrbuf.append("<docId>"+docId+"</docId>");
							  xmlStrbuf.append("<type>"+type+"</type>");
							  xmlStrbuf.append("<fileSize>"+length+"</fileSize>");
							  xmlStrbuf.append("<tenderTime>"+lastTenderTime+"</tenderTime>");
							  xmlStrbuf.append("<downUrl></downUrl>");
							  xmlStrbuf.append("</section>");
						  }
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
		    }
		return xmlStrbuf.toString();
	}

	/** 
	 * Description :  获取供应商投标详情
	 * Create Date: 2011-7-8上午10:40:31 by sunl  Modified Date: 2011-7-8上午10:40:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBidInfoList(String projectId,String packId,String supplierId) throws Exception{
		logger.debug("\n BidServiceImpl||getBidInfoList\n");
		
		String tempPath = projectId+File.separator+packId+File.separator+supplierId+File.separator+CommonEnum.BID_FILE_DIR;
		String path = messageSource.getMessage("epp.projectAttaPath");
		String xmlPath = path+tempPath+File.separator+"TenderDocument.xml";
		
		List<String[]> resultList = new ArrayList<String[]>();
		    File tempFile = new File(xmlPath);
		    if(tempFile.exists()){
		    	try {
			    	org.w3c.dom.Document documentW3c = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlPath);
					org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
					org.dom4j.Document document = xmlReader.read(documentW3c);
						Element element =  document.getRootElement();
						List<Element> tenderDocumentElements = element.elements("section");
						for(Element tenderDocumentElement:tenderDocumentElements){
						  String test[] = new String[4];
						  String biddingStatus = tenderDocumentElement.attributeValue("biddingStatus");
						  if("false".equals(biddingStatus)){
							  break;
						  }else{
							  String fileName = tenderDocumentElement.attributeValue("fileName");//文件名称
							  
							  String docId = tenderDocumentElement.attributeValue("docId");//文档ID
							  String endFlag = fileName.substring(fileName.lastIndexOf("."));//后缀名
							  File file = new File(path+tempPath+File.separator+docId+endFlag+".enc");
							  
							  String name =	 tenderDocumentElement.attributeValue("name");//投标分册
							  String length = String.valueOf(file.length());//大小
							  
							  test[0] = name;
							  test[1] = fileName;
							  test[2] = length;
							  test[3] = biddingStatus;
							  resultList.add(test);
						  }
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
		    }
		return resultList;
	}
	
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
	public BidPackage saveConfirmTender(String projCode, String packCode,String username, String password) throws Exception {
		String supplierId = "";
		String projectId = "";
		String packId = "";
		BidPackage bidPackage = null;
		Project project = projectManager.findProjectByProjCode(projCode);
		Project tempProject = null;
		if(project!=null){
			projectId = project.getObjId();
			Project subProject = projectManager.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){
				packId = project.getObjId();
				tempProject = project;
			}else{
				packId = subProject.getObjId();
				tempProject = subProject;
			}
			String decodeStringPassword = StringUtil.decodeString(password); //用Base64算法对字符串进行解码.
			String md5PassWord =  MathUtil.encryptPassWordSHA(decodeStringPassword);//密码转换成加密字符串
			List<User> userList = userApiService.getUserByUserName(username, md5PassWord);
			if(userList!=null&&!userList.isEmpty()){
				User user = userList.get(0);
				OrgInfo supplier = userApiService.getOrgInfoByUser(user);
				if(supplier!=null){
					supplierId = supplier.getObjId();
				}
			}
			List<Bid> bidList = bidManager.getBidListByProjectIdAndUserId(projectId, supplierId);
			if(bidList!=null&&!bidList.isEmpty()){
				Bid bid = bidList.get(0);
				bid.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
				bidManager.save(bid);
				bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId()); 
				//创建压缩投标文件
				Attachment attachment = new Attachment();
				//将附件传入是为了设置文件大小
				String tempPath = createZipBidFile(packId,supplierId,attachment);
				//保存投标文件附件
				attachment.setRelationId(UUID.randomUUID().toString());
				attachment.setPath(tempPath);
				attachment.setViewName("bidFile.zip");
				attachment.setFileName("bidFile.zip");
				attachment.setCreateTime(new Date());
				attachmentManagerImpl.save(attachment);
				if(bidPackage!=null){
					bidPackage.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
					bidPackage.setBidFile(attachment.getRelationId());
					bidPackageManager.save(bidPackage);
				}
				//投标回执文件相对路径
				String receiptFtlPath = project.getObjId()+File.separator+packId+File.separator+supplierId+File.separator+CommonEnum.BID_FILE_DIR;
				//创建投标回执文件
				this.gengnerRatePdfByReceiptFtl(tempProject,bid,BidReceiptEnum.TB_Receipt,receiptFtlPath);
			}
		}
		return bidPackage;
	}
	
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
	public BidPackage saveConfirmAnonymousTender(String projCode, String packCode,String bidNo) throws Exception {
		String projectId = "";
		String packId = "";
		BidPackage bidPackage = null;
		Project project = projectManager.findProjectByProjCode(projCode);
		Project tempProject = null;
		if(project!=null){
			projectId = project.getObjId();
			Project subProject = projectManager.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){
				packId = project.getObjId();
				tempProject = project;
			}else{
				packId = subProject.getObjId();
				tempProject = subProject;
			}
			List<Bid> bidList = bidManager.getBidAnonymousListByProjectIdAndBidNo(projectId, bidNo);
			if(bidList!=null&&!bidList.isEmpty()){
				Bid bid = bidList.get(0);
				bid.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
				bidManager.save(bid);
				bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId()); 
				//创建压缩投标文件
				Attachment attachment = new Attachment();
				//将附件传入是为了设置文件大小
				String tempPath = createZipBidFile(packId,bidNo,attachment);
				//保存投标文件附件
				attachment.setRelationId(UUID.randomUUID().toString());
				attachment.setPath(tempPath);
				attachment.setViewName("bidFile.zip");
				attachment.setFileName("bidFile.zip");
				attachment.setCreateTime(new Date());
				attachmentManagerImpl.save(attachment);
				if(bidPackage!=null){
					bidPackage.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
					bidPackage.setBidFile(attachment.getRelationId());
					bidPackageManager.save(bidPackage);
				}
				//投标回执文件相对路径
				String receiptFtlPath = project.getObjId()+File.separator+packId+File.separator+bidNo+File.separator+CommonEnum.BID_FILE_DIR;
				//创建投标回执文件
				this.gengnerAnonymousRatePdfByReceiptFtl(tempProject,bid,BidReceiptEnum.TB_Receipt,receiptFtlPath);
			}
		}
		return bidPackage;
	}
	
	/**
	 * FuncName: gengnerRatePdfByReceiptFtl
	 * Description :  生成Pdf回执
	 * @param 
	 * @return Attachment
	 * @author: shenjz
	 * @throws Exception 
	 * @throws TemplateException 
	 * @throws IOException 
	 * @Create Date:2011-6-23  上午09:55:21
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-23  上午09:55:21
	 */
	@SuppressWarnings("static-access")
	private Attachment gengnerRatePdfByReceiptFtl(Project project,Bid bid,String bidReType,String receiptFtlPath) throws IOException, TemplateException, Exception{
		Attachment attachment = null;
		Map<String,Object> templateMap = new HashMap<String,Object>();
		templateMap.put("project", project);
		templateMap.put("packCode", "");
		String templateName = "";
		if(project.getParentId()!=null){
			templateMap.put("packCode", project.getProjCode());
			templateMap.put("project", projectManager.get(project.getParentId()));
		}
		templateMap.put("bid", bid);
		templateMap.put("today",DateUtil.formatDateTime(new Date()));
		Html2PDFConverter htPdf = new Html2PDFConverter();
		BidPackage bidPackage = bidPackageManager.getBidPackageByPackCodeAndBidId(project.getProjCode(), bid.getObjId());
		    attachment = new Attachment();
			attachment.setPath(receiptFtlPath);//这里存放相对路径
			String uuName = UUID.randomUUID().toString();
			attachment.setViewName(uuName+".pdf");
			attachment.setFileName(uuName+".pdf");
			attachment.setCreateTime(new Date());
			attachment.setRelationId(uuName);
			attachmentManagerImpl.save(attachment);
			BidReceipt oldReceipt = bidReceiptDaoHibernate.getBidReceiptByBidPackageId(bidPackage.getObjId(), bidReType);
			BidReceipt bidReceipt = new BidReceipt();
			if(oldReceipt!=null){
				bidReceipt = oldReceipt;
			}
			bidReceipt.setBid(bid);
			bidReceipt.setBidPackage(bidPackage);
			bidReceipt.setBidReProcessStatus(BidReceiptEnum.PROCESS_STATUS_CREATE);
			bidReceipt.setBidReType(bidReType);
			bidReceipt.setPack(project);
			bidReceipt.setBidReFileId(attachment.getRelationId());	
			bidReceiptDaoHibernate.save(bidReceipt);
			String filePath = messageSource.getMessage("epp.projectAttaPath").replace("/", File.separator)+receiptFtlPath+File.separator;
			
			if(BidReceiptEnum.TB_Receipt.equals(bidReType)){
				templateName = "receipt.ftl";
			}else if(BidReceiptEnum.CB_Receipt.equals(bidReType)){
				templateName = "removeReceipt.ftl";
			}
			htPdf.Html2PDF(freeMarkerService.getFreeMarkerContent(templateName, templateMap),filePath,attachment.getFileName());//生成pdf文件
			return attachment;
	}
	
	/**
	 * FuncName: gengnerAnonymousRatePdfByReceiptFtl
	 * Description : 生成匿名投标回执
	 * @param 
	 * @param project
	 * @param bid
	 * @param bidReType
	 * @param receiptFtlPath
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 * @throws Exception Attachment
	 * @author: shenjz
	 * @Create Date:2011-12-13  下午01:57:38
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-13  下午01:57:38
	 */
	@SuppressWarnings("static-access")
	private Attachment gengnerAnonymousRatePdfByReceiptFtl(Project project,Bid bid,String bidReType,String receiptFtlPath) throws IOException, TemplateException, Exception{
		Attachment attachment = null;
		Map<String,Object> templateMap = new HashMap<String,Object>();
		templateMap.put("project", project);
		templateMap.put("packCode", "");
		String templateName = "";
		if(project.getParentId()!=null){
			templateMap.put("packCode", project.getProjCode());
			templateMap.put("project", projectManager.get(project.getParentId()));
		}
		bid.setSupplierName("");
		templateMap.put("bid", bid);
		templateMap.put("today",DateUtil.formatDateTime(new Date()));
		Html2PDFConverter htPdf = new Html2PDFConverter();
		BidPackage bidPackage = bidPackageManager.getBidPackageByPackCodeAndBidId(project.getProjCode(), bid.getObjId());
		    attachment = new Attachment();
			attachment.setPath(receiptFtlPath);//这里存放相对路径
			String uuName = UUID.randomUUID().toString();
			attachment.setViewName(uuName+".pdf");
			attachment.setFileName(uuName+".pdf");
			attachment.setCreateTime(new Date());
			attachment.setRelationId(uuName);
			attachmentManagerImpl.save(attachment);
			BidReceipt oldReceipt = bidReceiptDaoHibernate.getBidReceiptByBidPackageId(bidPackage.getObjId(), bidReType);
			BidReceipt bidReceipt = new BidReceipt();
			if(oldReceipt!=null){
				bidReceipt = oldReceipt;
			}
			bidReceipt.setBid(bid);
			bidReceipt.setBidPackage(bidPackage);
			bidReceipt.setBidReProcessStatus(BidReceiptEnum.PROCESS_STATUS_CREATE);
			bidReceipt.setBidReType(bidReType);
			bidReceipt.setPack(project);
			bidReceipt.setBidReFileId(attachment.getRelationId());	
			bidReceiptDaoHibernate.save(bidReceipt);
			String filePath = messageSource.getMessage("epp.projectAttaPath").replace("/", File.separator)+receiptFtlPath+File.separator;
			
			if(BidReceiptEnum.TB_Receipt.equals(bidReType)){
				templateName = "receipt.ftl";
			}else if(BidReceiptEnum.CB_Receipt.equals(bidReType)){
				templateName = "removeReceipt.ftl";
			}
			htPdf.Html2PDF(freeMarkerService.getFreeMarkerContent(templateName, templateMap),filePath,attachment.getFileName(),bid.getBidNo());//生成pdf文件
			return attachment;
	}
	/**
	 * FuncName: createbidFile
	 * Description :  创建投标文件压缩包
	 * @param projectId   项目或包组ID
	 * @param orgInfoId   供应商ID
	 * @return String    存放文件的相对路径
	 * @author: liuke
	 * @Create Date:2011-6-23  下午06:47:26
	 * @Modifier: liuke
	 * @Modified Date:2011-6-23  下午06:47:26
	 */
	 private String createZipBidFile(String projectId,String orgInfoId,Attachment attachment){
		String newZipPath = messageSource.getMessage("epp.projectAttaPath");
		Project project = projectManager.get(projectId);
		String returnPath = "";
		if(project.getParentId() != null && !"".equals(project.getParentId())){//若为包组，则需要将项目ID取出，创建项目文件夹
			newZipPath += File.separator + project.getParentId();
			returnPath = File.separator + project.getParentId();
		}else{//若为项目，则需要将项目ID作为包组ID，创建包组文件夹
			newZipPath += File.separator + projectId;
			returnPath = File.separator + projectId;
		}
		String tempPath =  File.separator +projectId + File.separator +orgInfoId+ File.separator+CommonEnum.BID_FILE_DIR+ File.separator;
		returnPath += File.separator +projectId +File.separator +orgInfoId+ File.separator+CommonEnum.BID_FILE_DIR+ File.separator;
		String bidFilePath = newZipPath+tempPath;
		ArrayList<String> fileStrList = new ArrayList<String>(); //文件清单
		fileStrList.add("EvalFactors.xml");
		fileStrList.add("EvalPoint.xml");
		fileStrList.add("Price.xml.enc");
		fileStrList.add("TenderDocument.xml");
		String xmlPath = bidFilePath+"TenderDocument.xml";
		try {
	    	org.w3c.dom.Document documentW3c = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlPath);
			org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
			org.dom4j.Document document = xmlReader.read(documentW3c);
			Element element =  document.getRootElement();
			List<Element> tenderDocumentElements = element.elements("section");
			for(Element tenderDocumentElement:tenderDocumentElements){
			  String name =	tenderDocumentElement.attributeValue("docId");
			  String fileName = tenderDocumentElement.attributeValue("fileName");
			  String endFlag = fileName.substring(fileName.indexOf("."));
			  // add by yangx 获取目录下所有文件 判断是否存在该文件名
			  String[] fileList = FileUtil.listFiles(bidFilePath);
			  String fileNameFlagEnc = name+endFlag+".enc";//以.enc为后缀的投标文件
			  String fileNameFlag = name+endFlag;//不以.enc为后缀的投标文件
			  Boolean flagEnc = false;
			  if (fileList!=null&&fileList.length>0) {//存在文件
				for (String fileNameTemp:fileList) {//循环文件名称
					if (fileNameFlagEnc.equals(fileNameTemp)) {//是否以.enc为后缀
						flagEnc = true;
						break;
					}
				}  
			  }
			  if (flagEnc) {
				  fileStrList.add(fileNameFlagEnc);
			  }else{
				  fileStrList.add(fileNameFlag);
			  }
			}
			String[] files = (String[])fileStrList.toArray(new String[]{});
			File file = new File(bidFilePath+File.separator+"bidFile.zip");
			ZipUtils.zipFiles(file,bidFilePath,files);
			attachment.setFileSize(file.length());
		} catch (Exception e) {
				e.printStackTrace();
			}
		return returnPath;
	}	
	 
	/** 
	 * FuncName: updateTenderDocumentBidType
	 * Description :  如果MD5验证失败则修改投标分册状态为false
	 * @param  void
	 * @author: liuke
	 * @throws IOException 
	 * @Create Date:2011-7-18  下午05:15:53
	 * @Modifier: liuke
	 * @Modified Date:2011-7-18  下午05:15:53
	 */
	private void updateTenderDocumentBidType(String filePath,String docId) throws IOException{
		org.w3c.dom.Document documentW3c;
		try {
			File file = new File(filePath);
			if(file.exists()){ //判断分册文档是否存在
			documentW3c = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filePath);
			org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
			org.dom4j.Document document = xmlReader.read(documentW3c);
			Element element =  document.getRootElement();
			List<Element> tenderDocumentElements = element.elements("section");
			for(Element tenderDocumentElement:tenderDocumentElements){
				  String docIds = tenderDocumentElement.attributeValue("docId");
				  if(docId.equals(docIds)){
					  tenderDocumentElement.attribute("biddingStatus").setValue("false");
				  }
				}
			 XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(filePath));  
			   		xmlWriter.write(document);  
			   		xmlWriter.close(); 
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * FuncName: updateTenderDocumentBidTypeForSuccess
	 * Description :  如果MD5验证成功则修改投标分册状态为true
	 * @param 
	 * @param filePath
	 * @param docId
	 * @throws IOException void
	 * @author: shenjz
	 * @Create Date:2011-7-21  下午01:56:26
	 * @Modifier: shenjz
	 * @Modified Date:2011-7-21  下午01:56:26
	 */
	private void updateTenderDocumentBidTypeForSuccess(String filePath,String docId) throws IOException{
		org.w3c.dom.Document documentW3c;
		try {
			File file = new File(filePath);
			if(file.exists()){ //判断分册文档是否存在
			documentW3c = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filePath);
			org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
			org.dom4j.Document document = xmlReader.read(documentW3c);
			Element element =  document.getRootElement();
			List<Element> tenderDocumentElements = element.elements("section");
			for(Element tenderDocumentElement:tenderDocumentElements){
				  String docIds = tenderDocumentElement.attributeValue("docId");
				  if(docId.equals(docIds)){
					  tenderDocumentElement.attribute("biddingStatus").setValue("true");
				  }
				}
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(filePath));  
			   		  xmlWriter.write(document);  
			   		  xmlWriter.close(); 
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * FuncName: saveBidAndBidPackage
	 * Description :  保存投标与投标包件记录
	 * @param supplierId  供应商ID
	 * @param projId   项目ID
	 * @param packId   包件ID或项目ID
	 * @param bidQuoteSum    总报价
	 * @param bidLinker  联系人
	 * @author: liuke
	 * @Create Date:2011-9-29  上午10:57:12
	 * @Modifier: liuke
	 * @Modified Date:2011-9-29  上午10:57:12
	 */
	public Project saveBidAndBidPackage(String bailRecordValue) {
		/**
		 * 1.根据供应商ID获取供应商信息，根据项目ID,包组ID获取项目和包组信息
		 * 2.查询是否已经存在投标记录，存在则修改以前的投标记录，否则新增一条投标记录
		 * 3.查询是否已经存在投标包件记录，存在则修改以前的投标包件记录，否则新增一条投标包件记录
		 */
		/*插入开标一览表明细数据*/
		String[] allbailRecordValueArrays = bailRecordValue.split(",");
		Project  returnProject = null;
		for(String value :allbailRecordValueArrays){
			String newValue = value.replace("|", ",");
			String[] everyVal = newValue.split(",");
			
			String supplierId = everyVal[0];
			String projId = everyVal[1];
			String packId = everyVal[2];
			String bidLinker = everyVal[3];
			String bidQuoteSum = everyVal[4];
			
			List<Bid> oldBidList = bidManager.getBidListByProjectIdAndUserId(projId, supplierId);
			OrgInfo supplier = (OrgInfo)bidManager.get(supplierId, OrgInfo.class); //获取供应商信息
			Project project = projectManager.getProjectByTotal(projId);   //获取项目信息
			Project subProj = projectManager.getProjectByTotal(packId);   //获取包件信息
			Bid bid = null;
			if(!oldBidList.isEmpty()){  //查询是否已经存在投标记录,否则new一个新的
				bid = oldBidList.get(0);
			  }else{
				bid = new Bid(); 
			  }
			bid.setBidLinker(bidLinker);
			if(supplier!=null){    //如果供应商信息不为空
				bid.setSupplier(supplier);
				bid.setSupplierName(supplier.getOrgName());
			}
			if(project!=null){     //如果项目信息不为空
				bid.setProject(project);
				bid.setProjCode(project.getProjCode());
				bid.setProjName(project.getProjName());
			}
			bid.setBidQuoteSum(new BigDecimal(bidQuoteSum));  //添加总报价
			bidManager.save(bid);   //保存投标记录
			
			BidPackage bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId());
			if(bidPackage==null){  //判断是否存在投标包件记录，不存在则new一个新的
				bidPackage = new BidPackage();
			}
			bidPackage.setBid(bid);
			bidPackage.setPack(subProj);
			bidPackage.setQuotesum(Float.valueOf(bidQuoteSum));  //添加总报价
			//add by chenhj 20111025 确认为正在常投标
			bidPackage.setProcessStatus("00");
			bidPackageManager.save(bidPackage);  //保存投标包件记录
			returnProject = project;
			
		}	
		returnProject.setUser(AuthenticationHelper.getCurrentUser());
		returnProject.setParentBizId(returnProject.getObjId());
		return returnProject;
	}
	
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
	public List<Bid> getBidAnonymousListByProjectIdAndBidNO(String projectId,String bidNo){
		return bidManager.getBidAnonymousListByProjectIdAndBidNo(projectId, bidNo);
	}
	 
}
