package com.gpcsoft.epp.purchasedoc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.ProjPlanStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyTypeEnum;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.manager.DosBuyRecordManager;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocManager;
import com.gpcsoft.epp.purchasedoc.service.DosBuyRecordService;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.enumeration.EnumMessage;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.auth.manager.EmployeeManager;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

@Service 
public class DosBuyRecordServiceImpl extends BaseGenericServiceImpl<DosBuyRecord> implements DosBuyRecordService {

	@Autowired(required=true) @Qualifier("dosBuyRecordManagerImpl")
	private DosBuyRecordManager dosBuyRecordManager;
	
	@Autowired(required=true) @Qualifier("purchaseDocManagerImpl")
	private PurchaseDocManager purchaseDocManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
    
	@Autowired(required=false)@Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	
	@Autowired(required=true) @Qualifier("projProcessRuleManagerImpl")
	private ProjProcessRuleManager projProcessRuleManager;
	
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	@Autowired(required=true) @Qualifier("employeeManagerImpl")
	private EmployeeManager employeeManager;
	
	/**
	 * 
	 * Description :通过供应商主键判断是否购买采购文件  
	 * Create Date: 2010-5-27下午04:52:06 by liuke  Modified Date: 2010-5-27下午04:52:06 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean IsBuyPurchaseDoc(String projectId, String subprojectId ,User user) {
		logger.debug("\nDosBuyRecordServiceImpl||IsBuyPurchaseDoc\n");
		return dosBuyRecordManager.IsBuyPurchaseDoc(projectId, subprojectId, user);
	}
	
	/** 
	 * FuncName : saveDosBuyRecord
	 * Description :  保存标书费购买记录
	 * Create Date: 2011-9-22下午01:35:21 by yangx  
	 * Modified Date: 2011-9-22下午01:35:21 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public DosBuyRecord saveDosBuyRecord(DosBuyRecord dosBuyRecord,String projectIds)throws Exception {
		logger.debug("\nDosBuyRecordServiceImpl||saveDosBuyRecord\n");
		List<DosBuyRecord>  dosBuyRecordList = new ArrayList<DosBuyRecord>();
		String[] projectId = projectIds.split(SeparationEnum.COMMA);
		Project project = projectManager.get(projectId[0]);
		ProjProcessRule projProcessRule = null;
		String parentProjectId = "";
		if (project!=null&&!StringUtils.empty(project.getParentId())) {
			parentProjectId = project.getParentId();
		} else {
			parentProjectId = project.getObjId();
		}
		projProcessRule = projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(parentProjectId,SysConfigEnum.DOPURDOC);//是否按照标段制作标书
		for (int i=0;i<projectId.length;i++) {
			DosBuyRecord dosBuyRecordBaki = new DosBuyRecord();
			BeanUtils.copyPropertiesFilterEmpty(dosBuyRecordBaki, dosBuyRecord);
			dosBuyRecordBaki.setTenderId(projectId[i]);
			dosBuyRecordBaki.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			String tempProjectId = "";
			if (projProcessRule!=null&&ProjPlanStatusEnum.IN_TRUE.equals(projProcessRule.getResValue())) {//是按照标段制作标书
				tempProjectId = projectId[i];
			} else {
				//add by chenhj 20111021 修改没有分包,也没有按标段制作招标文件报错问题
				if(!StringUtils.empty(project.getParentId())){//如果分包,由project可能是包ID
					tempProjectId = project.getParentId();
				}else {
					tempProjectId=project.getObjId();
				}
			}
			PurchaseDoc purchaseDoc = purchaseDocManager.getPurchaseDocByProjectId(tempProjectId);
			dosBuyRecordBaki.setFileId(purchaseDoc.getObjId());
			dosBuyRecordList.add(dosBuyRecordBaki);
		}
		dosBuyRecordManager.save(dosBuyRecordList);
		dosBuyRecord.setUser(AuthenticationHelper.getCurrentUser());
		dosBuyRecord.setParentBizId(parentProjectId);
		return dosBuyRecord;
	}
	
	/** 
	 * Description :  根据供应商Id获取OrgInfo
	 * Create Date: 2010-9-7下午05:08:56 by yangx  Modified Date: 2010-9-7下午05:08:56 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfo(String supplierId){
		logger.debug("\nDosBuyRecordServiceImpl||getOrgInfo\n");
		return (OrgInfo)dosBuyRecordManager.get(supplierId,OrgInfo.class);
	}
	
	/** 
	 * FuncName :   删除标书费购买记录
	 * Description :  removeDosBuyRecordByIds
	 * Create Date: 2011-9-22下午04:23:24 by yangx  
	 * Modified Date: 2011-9-22下午04:23:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeDosBuyRecordByIds(String[] objIds)throws EsException{
		logger.debug("\nDosBuyRecordServiceImpl||getOrgInfo\n");
		if (objIds!=null&&objIds.length>0) {
			for (String dosBuyRecordId:objIds) {
				DosBuyRecord dosBuyRecord = dosBuyRecordManager.get(dosBuyRecordId);
				if (dosBuyRecord.getDocBuyFile()!=null&&!"".equals(dosBuyRecord.getDocBuyFile())) {
					List<Attachment> attachRelaIdList = attachmentManager.getAttachmentsByRealID(dosBuyRecord.getDocBuyFile());//缴纳证明
					attachmentManager.removeAttachmentAndFile(attachRelaIdList);
				}
				if (dosBuyRecord.getInvoiceFile()!=null&&!"".equals(dosBuyRecord.getInvoiceFile())) {
					List<Attachment> invoiceFileList = attachmentManager.getAttachmentsByRealID(dosBuyRecord.getInvoiceFile());//发票
					attachmentManager.removeAttachmentAndFile(invoiceFileList);
				}
			}
			dosBuyRecordManager.remove(objIds, DosBuyRecord.class);
		}
	}
	
	/**
	 * 
	 * Description :根据主键获得询价文件购买信息  
	 * Create Date: 2010-9-14下午05:36:55 by liuke  Modified Date: 2010-9-14下午05:36:55 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public DosBuyRecord getDosBuyRecordByObjId(String objId) {
		logger.debug("\nDosBuyRecordServiceImpl||getDosBuyRecordByObjId\n");
		return dosBuyRecordManager.get(objId);
	}
	

	/** 
	 * Description :  根据项目id获取招标文件购买记录
	 * Create Date: 2010-10-19下午05:00:25 by wangcl  Modified Date: 2010-10-19下午05:00:25 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<DosBuyRecord> getByProjectId(String projectId) {
		logger.debug("\nDosBuyRecordServiceImpl||getByProjectId\n");
		return dosBuyRecordManager.getByProjectId(projectId);
	}
	
	/** 
	 * FuncName : getDosBuyRecordBySupplierId
	 * Description :  根据供应商Id获取标书费购买记录
	 * Create Date: 2011-9-22下午11:38:18 by yangx  
	 * Modified Date: 2011-9-22下午11:38:18 by yangx
	 * @param   supplierId：供应商Id
	 * @return  DosBuyRecord
	 * @Exception   
	 */
	public List<DosBuyRecord> getDosBuyRecordBySupplierId(String supplierId,String projectId){
		return dosBuyRecordManager.getDosBuyRecordBySupplierId(supplierId,projectId);
	}
	
	/** 
	 * FuncName : saveConfirmDosBuyRecord
	 * Description :  审核标书费购买记录审核状态
	 * Create Date: 2011-9-26下午05:57:15 by yangx  
	 * Modified Date: 2011-9-26下午05:57:15 by yangx
	 * @return  DosBuyRecord
	 * @Exception   
	 */
	public DosBuyRecord saveConfirmDosBuyRecord(DosBuyRecord dosBuyRecord){
		dosBuyRecordManager.save(dosBuyRecord);
		if (dosBuyRecord!=null&&AuditStatusEnum.AUDIT_PASS.equals(dosBuyRecord.getAuditStatus())) {
			completedWorkTaskManager.createCompTaskByPassivity("审核标书费购买记录","",dosBuyRecord.getOpinion(),dosBuyRecord.getObjId(),dosBuyRecord.getTenderId(),"",CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT);
		}else{
			completedWorkTaskManager.createCompTaskByPassivity("审核标书费购买记录","",dosBuyRecord.getOpinion(),dosBuyRecord.getObjId(),dosBuyRecord.getTenderId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		}
		return dosBuyRecord;
	}
	
	/** 
	 * FuncName : finshConfirmDosBuyRecord
	 * Description :  完成标书确认
	 * Create Date: 2011-11-16下午01:38:07 by yangx  
	 * Modified Date: 2011-11-16下午01:38:07 by yangx
	 * @param   dosBuyRecordId：标书费对象Id
	 * @return  DosBuyRecord
	 */
	public DosBuyRecord finishConfirmDosBuyRecord(String projectId){
		DosBuyRecord dosBuyRecord = new DosBuyRecord();
		dosBuyRecord.setParentBizId(projectId);
		dosBuyRecord.setUser(AuthenticationHelper.getCurrentUser());
		return dosBuyRecord;
	}
	
	/** 
	 * Description :  网银支付成功之后，修改采购文件的支付状态
	 * Create Date: 2012-1-6下午03:03:16 by likg  Modified Date: 2012-1-6下午03:03:16 by likg
	 * @param   projId:项目id(如果项目分包，则为包件id)		empId:员工id
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePayStatus(String projId, String empId) throws Exception {
		//获取项目信息
		Project project = projectManager.get(projId);
		BigDecimal purDocPrice = project.getPurDocPrice(); //标书费金额
		if(StringUtils.hasLength(project.getParentId())) {
			project = projectManager.get(project.getParentId());
		}
		
		//获取供应商ID
		OrgInfo supplier = orgInfoManager.getOrgInfoByCompany(employeeManager.get(empId).getCompany().getObjId());
		String supplierId = (supplier==null ? "" : supplier.getObjId());
		String supplierName = (supplier==null ? "" : supplier.getOrgName());
		
		//判断是否已经存在采购文件购买记录
		DosBuyRecord dosBuyRecord = new DosBuyRecord();
		List<DosBuyRecord> dosBuyRecordList = getDosBuyRecordBySupplierId(supplierId, projId);
		//已经存在采购文件购买记录
		if(dosBuyRecordList!=null && !dosBuyRecordList.isEmpty()) {
			dosBuyRecord = dosBuyRecordList.get(0);
		}
		//不存在采购文件购买记录
		else {
			dosBuyRecord.setSupplierId(supplierId); //供应商ID
			dosBuyRecord.setSupplierName(supplier.getOrgName()); //供应商名称
			dosBuyRecord.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			dosBuyRecord.setUser(AuthenticationHelper.getCurrentUser());
			
			//是否按照标段制作标书
			ProjProcessRule projProcessRule = projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(project.getObjId(), SysConfigEnum.DOPURDOC);
			String tempProjectId = "";
			if (projProcessRule!=null && ProjPlanStatusEnum.IN_TRUE.equals(projProcessRule.getResValue())) {//是按照标段制作标书
				tempProjectId = projId;
			} else {
				tempProjectId = project.getObjId();
			}
			dosBuyRecord.setTenderId(tempProjectId);  //根据是否按照标段售卖标书来设定支付记录里面的项目id
			PurchaseDoc purchaseDoc = purchaseDocManager.getPurchaseDocByProjectId(tempProjectId);
			dosBuyRecord.setFileId(purchaseDoc.getObjId());
			dosBuyRecord.setParentBizId(project.getObjId());
		}
		
		//更新采购文件支付状态
		dosBuyRecord.setDocBuyStatus(DosBuyTypeEnum.ALREADY_PAY); //购买采购文件状态[01：已支付]
		dosBuyRecord.setUseStatus(CommonEnum.USER_STATUS_FORMAL); //使用状态
		dosBuyRecord.setAuditStatus(AuditStatusEnum.WAIT_AUDIT); //审核状态
		dosBuyRecord.setRenderTime(new Date());
		dosBuyRecord.setAmount(purDocPrice);
		dosBuyRecord.setPayType(DosBuyTypeEnum.Telegraphic_Transfer);
		dosBuyRecordManager.save(dosBuyRecord);
		
		//给经办人发送站内信
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("projName", project.getProjName());
		templateMap.put("supplierName", supplierName);
		Set<User> jbusers = project.getManager().getUsers();
		String userids[]= new String[jbusers.size()];
		int i=0;
		for(User user : jbusers) {
			userids[i] = user.getObjId();
			i++;
		}
		MessageUtil.sendMessageSystem(userids,"招标文件费用已支付","",messageSource.getMessage(EnumMessage.DOC_PAY),templateMap,false);
		
		return true;
	}
	
}
