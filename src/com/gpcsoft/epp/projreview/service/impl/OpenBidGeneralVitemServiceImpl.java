package com.gpcsoft.epp.projreview.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.CollectionUtils;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.bid.dao.BidDao;
import com.gpcsoft.epp.bid.dao.BidPackageDao;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.manager.BidManager;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.manager.CongruousFactorManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projreview.dao.GenviewDefineDao;
import com.gpcsoft.epp.projreview.dao.OpenBidDao;
import com.gpcsoft.epp.projreview.dao.OpenBidGeneralVitemDao;
import com.gpcsoft.epp.projreview.dao.OpenBidRecordDao;
import com.gpcsoft.epp.projreview.dao.OpenbidGeneralviewDao;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.manager.GenviewDefineManager;
import com.gpcsoft.epp.projreview.manager.OpenBidGeneralVitemManager;
import com.gpcsoft.epp.projreview.manager.OpenBidManager;
import com.gpcsoft.epp.projreview.manager.OpenBidRecordManager;
import com.gpcsoft.epp.projreview.manager.OpenbidGeneralviewManager;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;
import com.gpcsoft.epp.projreview.service.OpenBidGeneralVitemService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class OpenBidGeneralVitemServiceImpl extends BaseGenericServiceImpl<OpenBidGeneralVitem> implements OpenBidGeneralVitemService {

	@Autowired(required=true) @Qualifier("openBidGeneralVitemManagerImpl")
	private OpenBidGeneralVitemManager openBidGeneralVitemManager;

	@Autowired(required=true) @Qualifier("genviewDefineManagerImpl")
	private GenviewDefineManager genviewDefineManager;
	
	@Autowired(required=true) @Qualifier("congruousFactorManagerImpl")
	private CongruousFactorManager congruousFactorManager;

	@Autowired(required=true) @Qualifier("openbidGeneralviewManagerImpl")
	private OpenbidGeneralviewManager openbidGeneralviewManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true)  @Qualifier("openBidGeneralVitemDaoHibernate")
	private OpenBidGeneralVitemDao openBidGeneralVitemDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bidPackageDaoHibernate")
	private BidPackageDao bidPackageDaoHibernate;

	@Autowired(required=true) @Qualifier("openBidManagerImpl")
	private OpenBidManager openBidManager;
	
	@Autowired(required=true) @Qualifier("openBidRecordManagerImpl")
	private OpenBidRecordManager openBidRecordManager;
	
	@Autowired(required=true) @Qualifier("bidManagerImpl")
	private BidManager bidManager;
	
	@Autowired(required=true)  @Qualifier("openbidGeneralviewDaoHibernate")
	private OpenbidGeneralviewDao openbidGeneralviewDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("openBidRecordDaoHibernate")
	private OpenBidRecordDao openBidRecordDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("openBidDaoHibernate")
	private OpenBidDao openBidDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("genviewDefineDaoHibernate")
	private GenviewDefineDao genviewDefineDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bidDaoHibernate")
	private BidDao bidDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("genviewDefineServiceImpl")
	private GenviewDefineService genviewDefineServiceImpl;
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
	public Project saveAllOpenBidGeneralVitem(String projectId, String allValues) {
		logger.debug("\nes OpenBidGeneralVitemServiceImpl||saveAllOpenBidGeneralVitem\n");
		openBidGeneralVitemManager.removeOpenBidGeneralVitemByProjectId(projectId);
		String[] allValueArrays = allValues.split(",");
		for(String value :allValueArrays){
			String newValue = value.replace("|", ",");
			String[] everyVal = newValue.split(",");
			OpenBidGeneralVitem vitem = new OpenBidGeneralVitem();
			GenviewDefine genviewDefine = genviewDefineManager.get(everyVal[0]) ;
			CongruousFactor congruousFactor = congruousFactorManager.get(everyVal[1]);
			String respValue = everyVal[2];
			OpenbidGeneralview  openbidGeneralview = openbidGeneralviewManager.getOpenbidGeneralviewByOpenBidRecordId(everyVal[3]);
			vitem.setGenviewDefine(genviewDefine);
			vitem.setFactorId(everyVal[1]);
			if(congruousFactor!=null){
				vitem.setFactorName(congruousFactor.getFactorName());
			}
			vitem.setRespValue(respValue);
			vitem.setOpenbidGeneralview(openbidGeneralview);
			openBidGeneralVitemManager.save(vitem);
		}
		Project project = projectManager.get(projectId);
		User user=AuthenticationHelper.getCurrentUser();
		project.setUser(user);
		project.setParentBizId(projectId);
		return project;
	}
	
	/**
	 * FuncName:getOpenBidGeneralVitemListByOpenbidGeneralview
	 * Description :根据开标一览表获得开标一览表明细
	 * @param openbidGeneralviewId:开标一览表主键
	 * @return  List<OpenBidGeneralVitem>
	 * @author liuke
	 * @Create Date: 2010-12-15上午11:29:22 
	 * @Modifier   liuke
	 * @Modified Date: 2010-12-15上午11:29:22 
	 */
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemListByOpenbidGeneralview(String openbidGeneralviewId) {
		logger.debug("\nes OpenBidGeneralVitemServiceImpl||getOpenBidGeneralVitemListByOpenbidGeneralview\n");
		return openBidGeneralVitemManager.getOpenBidGeneralVitemListByOpenbidGeneralview(openbidGeneralviewId);
	}

	
	/**
	 * FuncName:saveAllOpenBidGeneralVitemSec
	 * Description:保存修改开标一览表明细并保存开标一览表信息  
	 * @param   projectId:项目主键,allValues:??
	 * @return  Project
	 * @author lic
	 * @throws Exception 
	 * @Create Date: 2010-10-11下午01:13:49 
	 * @Modifier    lic
	 * @Modified Date: 2010-10-11下午01:13:49 
	 */
	public Project saveAllOpenBidGeneralVitemSec(String projectId,String allValues) throws Exception{
		logger.debug("\nes OpenBidGeneralVitemServiceImpl||saveAllOpenBidGeneralVitemSec\n");
		openBidGeneralVitemManager.removeOpenBidGeneralVitemByProjectId(projectId);   //删除所有开标一览表明细数据
		
		openbidGeneralviewDaoHibernate.removeAllOpenbidGeneralviewByProject(projectId);  //删除开标一览表数据
		
		openBidRecordDaoHibernate.removeAllOpenBidRecordByProject(projectId);   //删除开标明细数据
		
		openBidDaoHibernate.removeAllOpenBidByProject(projectId);
		
		List<BidPackage> bidPackageList = bidPackageDaoHibernate.getAllBidPackageByProjectId(projectId);
		
		Project project = projectManager.get(projectId);
		/*插入开标记录表对象*/
		List<OpenBid> openBidList = new ArrayList<OpenBid>();
		Set<Project> subProj = project.getSubProject();
		if(subProj.isEmpty()){
			OpenBid openBid = new OpenBid();
			openBid.setProjId(project.getObjId());
			openBid.setProjName(project.getProjName());
			openBid.setProjCode(project.getProjCode());
			openBid.setSubProjId(project.getObjId());
			openBidManager.save(openBid);
			openBidList.add(openBid);
		}else{
			for (Iterator iterator = subProj.iterator(); iterator.hasNext();) {
				Project sub = (Project) iterator.next();
				OpenBid openBid = new OpenBid();
				openBid.setProjId(project.getObjId());
				openBid.setProjName(project.getProjName());
				openBid.setProjCode(project.getProjCode());
				openBid.setSubProjId(sub.getObjId());
				openBidManager.save(openBid);
				openBidList.add(openBid);
				}
		}
		
		List<OpenBidRecord> openBidRecordList = new ArrayList<OpenBidRecord>();
		/*插入开标记录明细表对象*/
		for (Iterator iterator = bidPackageList.iterator(); iterator.hasNext();) {
			BidPackage bidPackage = (BidPackage) iterator.next();
			Bid bid = bidPackage.getBid();
			OpenBidRecord openbidRecord = new OpenBidRecord();
			openbidRecord.setSupplier(bid.getSupplier());
			openbidRecord.setSellerName(bid.getSupplierName());
			openbidRecord.setBidId(bid.getObjId());
			openbidRecord.setSubProjId(bidPackage.getPack().getObjId());
			openbidRecord.setQuoteSum(BigDecimal.valueOf(bidPackage.getQuotesum()));
			 for (Iterator iterator2 = openBidList.iterator(); iterator2.hasNext();) {
				 OpenBid openBid = (OpenBid) iterator2.next();
				 if(openbidRecord.getSubProjId().equals(openBid.getSubProjId())){
					 openbidRecord.setOpenBId(openBid.getObjId());
				 }
			}
			 openBidRecordManager.save(openbidRecord);
			 openBidRecordList.add(openbidRecord);
		}
		
		/*插入开标一览表数据*/
		List<OpenbidGeneralview> openbidGeneralviewList = openbidGeneralviewManager.getAllByProjectId(projectId);
		if(null!=openBidRecordList){
			if(openbidGeneralviewList.isEmpty()){
				for(OpenBidRecord bidRecord:openBidRecordList){
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
		
		
		/*插入开标一览表明细数据*/
		String[] allValueArrays = allValues.split(",");
		for(String value :allValueArrays){
			String newValue = value.replace("|", ",");
			String[] everyVal = newValue.split(",");
			OpenBidGeneralVitem vitem = new OpenBidGeneralVitem();
			GenviewDefine genviewDefine = genviewDefineManager.get(everyVal[0]) ;
			CongruousFactor congruousFactor = congruousFactorManager.get(everyVal[1]);
			String respValue ="";
			if(everyVal.length==5){
				respValue = everyVal[4];
			}
			vitem.setGenviewDefine(genviewDefine);
			vitem.setFactorId(everyVal[1]);
			if(congruousFactor!=null){
				vitem.setFactorName(congruousFactor.getFactorName());
			}
			vitem.setRespValue(respValue);
			String packId = everyVal[2];
			String suppilerId = everyVal[3];
			
			OpenbidGeneralview openbidGeneralview =	openbidGeneralviewDaoHibernate.getOpenbidGeneralviewBySupplierIdAndPackId(suppilerId, packId);
			vitem.setOpenbidGeneralview(openbidGeneralview);
			openBidGeneralVitemManager.save(vitem);
			
			if(!respValue.equals("")&&"总报价".equals(genviewDefine.getFactorName())){ //同步更新报价信息
				OpenBidRecord openbidRecord = openBidRecordDaoHibernate.getOpenBidRecordBySupplierIdAndSubProjectId(packId, suppilerId);
				try{
					new BigDecimal(respValue);
				}catch(Exception e){
					throw new EsException(messageSource.getMessage(EsExceptionEnum.ES_ISNOT_MONEY));
				}
				openbidRecord.setQuoteSum(new BigDecimal(respValue));
				openBidRecordDaoHibernate.save(openbidRecord);
			}
				
			
		}
		
		User user=AuthenticationHelper.getCurrentUser();
		project.setUser(user);
		project.setParentBizId(projectId);
		return project;
	}

	
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
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemListByProjectId(
			String projectId) {
		return openBidGeneralVitemDaoHibernate.getAllOpenBidGeneralVitem(projectId);
	}

	/** 
	 * FuncName : finishOpenBidGeneralVitem
	 * Description :  完成开标一览表
	 * Create Date: 2011-11-18下午04:24:58 by yangx  
	 * Modified Date: 2011-11-18下午04:24:58 by yangx
	 * @param   projectId：项目ID
	 * @return  OpenBidGeneralVitem
	 */
	public OpenBidGeneralVitem finishOpenBidGeneralVitem(String projectId) throws Exception{
		List<OpenBidGeneralVitem> listOpenBidGeneralVitem = openBidGeneralVitemDaoHibernate.getAllOpenBidGeneralVitem(projectId);
		if (listOpenBidGeneralVitem!=null&&listOpenBidGeneralVitem.size()>0) {
			OpenBidGeneralVitem openBidGeneralVitem = new OpenBidGeneralVitem();
			openBidGeneralVitem.setParentBizId(projectId);
			openBidGeneralVitem.setUser(AuthenticationHelper.getCurrentUser(true));
			return openBidGeneralVitem;
		}else{
			throw new EsException(messageSource.getMessage(EsExceptionEnum.NOT_SAVE_OPENBIDGENERALVITEN));
		}
	}

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
	public void saveOpenBidGeneralVitem(List<OpenBidGeneralVitem> openBidGeneralVitemList, String tenderProjectId, String supplierId, String bidId, String openBidRecordId) {
		/*数据较验*/
		if(openBidGeneralVitemList == null || openBidGeneralVitemList.size() == 0)
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLOBJECT);
		
		if(StringUtils.empty(tenderProjectId) || StringUtils.empty(supplierId) || StringUtils.empty(supplierId) || StringUtils.empty(bidId))
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLKEY);
		
		Project tenderProject = projectManager.get(tenderProjectId);
		OrgInfo supplier = orgInfoDaoHibernate.get(supplierId);
		Bid bid = bidDaoHibernate.get(bidId);
		OpenBidRecord openBidRecord = openBidRecordDaoHibernate.get(openBidRecordId);
		
		if(tenderProject == null || supplier == null || bid == null || openBidRecord == null)
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLOBJECT);
		
		
		/*保存GenviewDefine*/
		List<GenviewDefine> genviewDefineList = genviewDefineDaoHibernate.getGenviewDefineListByProjectId(tenderProjectId);//获得已有的表头
		/*如果没有,则创建保存表头的集合*/
		if(genviewDefineList == null)
			genviewDefineList = new ArrayList<GenviewDefine>();
		for(OpenBidGeneralVitem obgv: openBidGeneralVitemList){
			GenviewDefine genviewDefine = new GenviewDefine();
			
			String objId =UUID.randomUUID().toString();
			genviewDefine.setObjId(objId);
			genviewDefine.setFactorId(objId);
			genviewDefine.setProject(tenderProject);
			genviewDefine.setFactorName(obgv.getFactorName());
			if(genviewDefineServiceImpl.getGenviewDefine(genviewDefineList, genviewDefine.getFactorName()) == null){
				genviewDefineDaoHibernate.saveGenviewDefineUsingSQL(genviewDefine);//保存表头
				genviewDefineList.add(genviewDefine);
			}
		}
		
		
		/*保存OpenbidGeneralview*/
		OpenbidGeneralview openbidGeneralview = openbidGeneralviewDaoHibernate.getOpenbidGeneralviewBySupplierIdAndPackId(supplierId, tenderProjectId);
		if(openbidGeneralview == null){
			openbidGeneralview = new OpenbidGeneralview();
		}
		openbidGeneralview.setBid(bid);
		openbidGeneralview.setOpenBidRecord(openBidRecord);
		if(bid.getBidQuoteSum() != null)
			openbidGeneralview.setBidQuotesum(new Float(bid.getBidQuoteSum().toString()));
		openbidGeneralview.setSupplierId(supplier.getObjId());
		openbidGeneralview.setSupplierName(supplier.getOrgName());
		openbidGeneralview.setSubProj(tenderProject);
		openbidGeneralviewDaoHibernate.save(openbidGeneralview);
		
		
		/*保存开标一览表*/
		/*关联OpenBidGeneralVitem的对象*/
		for(OpenBidGeneralVitem openBidGeneralVitem: openBidGeneralVitemList){
			GenviewDefine genViewDefine = genviewDefineServiceImpl.getGenviewDefine(genviewDefineList, openBidGeneralVitem.getFactorName());
			if(genViewDefine == null)
				throw new EsException(EsExceptionEnum.ES_ERROR_NULLOBJECT);
			openBidGeneralVitem.setGenviewDefine(genViewDefine);
			openBidGeneralVitem.setOpenbidGeneralview(openbidGeneralview);
			openBidGeneralVitem.setFactorId(genViewDefine.getObjId());
		}
		if(openBidGeneralVitemList != null && openBidGeneralVitemList.size() > 0 && openBidGeneralVitemList.get(0) != null){
			/*删除已存在的开标一览表数据*/
			if(openBidGeneralVitemList.get(0) != null && openBidGeneralVitemList.get(0).getOpenbidGeneralview() != null && !StringUtils.empty(openBidGeneralVitemList.get(0).getOpenbidGeneralview().getObjId()))
				openBidGeneralVitemDaoHibernate.removeOpenBidGeneralVitemByOpenbidGeneralviewId(openBidGeneralVitemList.get(0).getOpenbidGeneralview().getObjId());
			openBidGeneralVitemDaoHibernate.save(openBidGeneralVitemList);
		}
	}
}
