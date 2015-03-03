package com.gpcsoft.epp.projreview.manager.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bid.dao.BidItemsDao;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidItems;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;

import com.gpcsoft.epp.negotationrecord.dao.NegotationRecordDao;
import com.gpcsoft.epp.negotationrecord.dao.NegotationRecordItemDao;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecord;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecordItem;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.dao.OpenBidDao;
import com.gpcsoft.epp.projreview.dao.OpenBidRecordDao;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.manager.OpenBidRecordManager;
import com.gpcsoft.epp.projrule.dao.ProjProcessRuleDao;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.workgroup.dao.WorkgMemberDao;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/** @Description: todo 
 * @version V1.0
 * @Create Date 2010-8-1 下午07:13:24 By wanghz   
 */
@Repository
public class OpenBidRecordManagerImpl extends BaseManagerImpl<OpenBidRecord> implements OpenBidRecordManager {

	@Autowired(required=true)  @Qualifier("openBidRecordDaoHibernate")
	private OpenBidRecordDao openBidRecordDaoHibernate;

	@Autowired(required=true)  @Qualifier("projProcessRuleDaoHibernate")
	private ProjProcessRuleDao projProcessRuleDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("workgMemberDaoHibernate")
	private WorkgMemberDao workgMemberDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("openBidDaoHibernate")
	private OpenBidDao openBidDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bidItemsDaoHibernate")
	private BidItemsDao bidItemsDao;
	
	@Autowired(required=true)  @Qualifier("negotationRecordDaoHibernate")
	private NegotationRecordDao negotationRecordDao;
	
	@Autowired(required=true)  @Qualifier("negotationRecordItemDaoHibernate")
	private NegotationRecordItemDao negotationRecordItemDao;
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
	public OpenBidRecord getOpenBidRecordByQueryObject(QueryObject queryObject) {
		List list = openBidRecordDaoHibernate.findByQuery(queryObject);
		if(list.size()>0){
			return (OpenBidRecord)list.get(0);		
		}else{
			return null;
		}	
	}
	
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
	public void saveOpenBidRecord(OpenBidRecord openBidRecord) {
		openBidRecordDaoHibernate.save(openBidRecord);
	}
	
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
	public List<OpenBidRecord> getOpenBidRecordByPackId(String packId) {
		List<OpenBidRecord> list = openBidRecordDaoHibernate.getOpenBidRecordByPackId(packId);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			OpenBidRecord openBidRecord = (OpenBidRecord) iterator.next();
			List<NegotationRecord> negotationRecordList = negotationRecordDao.getNegotationRecordList(packId, openBidRecord.getSupplier().getObjId());
			if(null!=negotationRecordList&&!negotationRecordList.isEmpty()){
				String negotationRecordId = negotationRecordList.get(0).getObjId();
				 List<NegotationRecordItem> negotationRecordItemlist = negotationRecordItemDao.getNegotationRecordItembyNegotationRecordId(negotationRecordId);
				 if(null!=negotationRecordItemlist&&!negotationRecordItemlist.isEmpty()){
					 openBidRecord.setQuoteSum(negotationRecordItemlist.get(0).getNegRecItemTotal());
				 }
			}
		}
		return openBidRecordDaoHibernate.getOpenBidRecordByPackId(packId);
	}
	
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
	public List<OpenBidRecord> getOpenBidRecordByOPenBidId(String openBidId) {
		return openBidRecordDaoHibernate.getOpenBidRecordByOPenBidId(openBidId);
	}
	
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
	public Page<OpenBidRecord> getOpenBidPageRecordByOPenBidId(String openBidId, int start,int pagesize) {
		Page page =	openBidRecordDaoHibernate.findbyHql("from OpenBidRecord openBidRecord where openBidRecord.openBId=?", start, pagesize, openBidId);
		return page;
	}
	
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
	public OpenBidRecord getOpenBidRecordbyBidId(String bidId) {
		return openBidRecordDaoHibernate.getOpenBidRecordbyBidId(bidId);
	}
	
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
	public void updateOpenBidRecord(OpenBidRecord openBidRecord) {
		openBidRecordDaoHibernate.updateOpenBidRecord(openBidRecord);	
	}
	
	 /**
	  * FuncName:getAllByProjectId
	  * Description: 根据项目ID获取所有开标记录
	  * @param projectId:项目主键
	  * @return List<OpenBidRecord>
	  * @author wanghz
	  * @Create Date 2010-8-1 下午02:21:21 
	  */
	public List<OpenBidRecord> getAllByProjectId(String projectId) throws Exception {
		return openBidRecordDaoHibernate.getAllByProjectId(projectId);
	}
	
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
	public Boolean isPacHaveWorkMemember(String projectId,String subProjectId){
		/**
		 * 1.拿到项目
		 * 2.判断是否分包开标
		 * 3.如果是判断包组对应的开标小组是否存在
		 * 4.如果不是判断项目对应的开标小组是否存在
		 */
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		ProjProcessRule projProcessRule = projProcessRuleDaoHibernate.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.SUBPROJECTOPENBID);
		if (projProcessRule!=null&&"true".equals(projProcessRule.getResValue())) {//按照包组开标
			List<WorkgMember> list = workgMemberDaoHibernate.getWorkgMemberByProjectId(subProjectId);
			if (list==null||list.size()<1) {
				exception.append(messageSource.getMessage(EsExceptionEnum.PACKAGE_NOTHAVE_OPNBIDMEMEBER));//包组未组建开标小组成员
			}
			
		}else{//按照项目开标
			List<WorkgMember> list = workgMemberDaoHibernate.getWorkgMemberByProjectId(projectId);
			if (list==null||list.size()<1) {
				exception.append(messageSource.getMessage(EsExceptionEnum.PACKAGE_NOTHAVE_OPNBIDMEMEBER));//包组未组建开标小组成员
			}
		}
		if(exception.length()>0){checkValue=false;}//若有异常信息，表示有异常，需要把判断置为false;}
		if (!checkValue) {
			throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}
	
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
	public Project saveOpenBidRecord(Bid bid,String[] packIds)throws Exception {
		Project project = projectDaoHibernate.get(bid.getProject().getObjId());//得到招标项目
	
		List<BidItems> biditemsList = bidItemsDao.getBidItemsByPackIds(packIds, bid.getObjId());
		for(String packId:packIds){
			Project pack = projectDaoHibernate.get(packId);   //得到包组信息
			List<OpenBid> list = openBidDaoHibernate.getOpenBidBySubProjectId(packId);
			// 保存开标
			OpenBid	openBid = new OpenBid();
			openBid.setProjId(project.getObjId());
			openBid.setSubProjId(pack.getObjId());
			openBid.setProjName(pack.getProjName());
			openBid.setProjCode(pack.getProjCode());
			String openBidId="";
			if(list.isEmpty()){
				openBidDaoHibernate.save(openBid);
				openBidId = openBid.getObjId();
			}else{
				openBidId = list.get(0).getObjId();
			}
			// 保存开标记录
			OpenBidRecord openBidRecord = new OpenBidRecord();
			openBidRecord.setOpenBId(openBidId);
			openBidRecord.setSubProjId(pack.getObjId());
			openBidRecord.setSellerName(bid.getSupplierName());
			openBidRecord.setOpenBRStatus("01");
			openBidRecord.setBidId(bid.getObjId());
			openBidRecord.setSupplier(bid.getSupplier());
			for(BidItems bidItem :biditemsList){     //保存开标资金明细
				if(bidItem!=null&&bidItem.getPackId().equals(packId)){
					openBidRecord.setQuoteSum(bidItem.getPrice());
				}
			}
			openBidRecord.setCreateTime(new Date());
			openBidRecord.setCreateUser(AuthenticationHelper.getCurrentUser());
			openBidRecordDaoHibernate.save(openBidRecord);
		}
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		project.setParentBizId(parentBizId);
		User user = AuthenticationHelper.getCurrentUser();
		project.setUser(user);
		return project;
	}
	
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
	public void removeAllOpenBidRecordByProject(String projectId) {
		openBidRecordDaoHibernate.removeAllOpenBidRecordByProject(projectId);
	}
}
