package com.gpcsoft.epp.bid.service.impl;

import java.math.BigDecimal;
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
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.bid.dao.BailRecordDao;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.manager.SignUprecordManager;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.enumeration.EnumMessage;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.auth.manager.EmployeeManager;

@Service 
public class BailRecordServiceImpl extends BaseGenericServiceImpl<BailRecord> implements BailRecordService {
	@Autowired(required=true) @Qualifier("bailRecordDaoHibernate")
	private BailRecordDao bailRecordDao;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=false)@Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
		
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	@Autowired(required=true) @Qualifier("employeeManagerImpl")
	private EmployeeManager employeeManager;
	
	@Autowired(required=true) @Qualifier("signUprecordManagerImpl")
	private SignUprecordManager signUprecordManager;
	
	/** 
	 * Description :  支付完保证金后，保存一条供应商保证金支付记录
	 * 				  在阳光易购支付页面支付完成后调用此业务方法
	 * Create Date: 2011-7-21下午02:38:25 by sunl  Modified Date: 2011-7-21下午02:38:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveBailRecordAfterPay(String projId,String empId,String payStatus) throws Exception {
		logger.debug("\nes BailRecordServiceImpl||saveBailRecordAfterPay\n");
		
		//根据项目和供应商获取报名记录
		QueryObject<SignUprecord> queryObject = new QueryObjectBase<SignUprecord>();
		queryObject.setEntityClass(SignUprecord.class);
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projId));
		
		//获取供应商ID
		OrgInfo supplier = orgInfoManager.getOrgInfoByCompany(employeeManager.get(empId).getCompany().getObjId());
		String supplierId = supplier == null ? "" : supplier.getObjId();
		
		queryObject.getQueryParam().and(new QueryParam("supplier.objId",QueryParam.OPERATOR_EQ,supplierId));
		SignUprecord signUprecord = signUprecordManager.getSignUprecord(queryObject);
		
		//获取项目（如果项目分包，则为包组信息）
		Project project = projectManager.get(projId);
		
		//判断是否已经存在保证金缴纳记录
		BailRecord bailRecord = new BailRecord();
		List<BailRecord> bailRecordList = bailRecordDao.getBailRecordByProjectAndSupplierId(projId, supplierId);
		//已经存在保证金缴纳记录
		if(bailRecordList!=null && !bailRecordList.isEmpty()) {
			bailRecord = bailRecordList.get(0);
		}
		//不存在保证金缴纳记录
		else {
			bailRecord.setProjId(projId);
			bailRecord.setSupplyerId(supplierId);
			bailRecord.setSupplyerName(supplier.getOrgName());
		}
		
		bailRecord.setSignUprecord(signUprecord);//保证金关联的报名记录
		bailRecord.setBailStatus(payStatus);//已缴纳状态
		bailRecord.setRenderTime(new Date());//缴纳时间
		bailRecord.setBallMoney(project.getBail());//保证金金额
		
		//保存保证金记录
		bailRecordDao.save(bailRecord);
		
		//获取采购项目
		if(StringUtils.hasLength(project.getParentId())) {
			project = projectManager.get(project.getParentId());
		}

		//获取供应商名称
		String supplierName = ((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getOrgName();
		
		//发站内信
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("projName", project.getProjName());
		templateMap.put("supplierName", supplierName);
		
		//获取经办人发送站内信BAIL_PAY
		Set<User> jbusers = project.getManager().getUsers();
		String userids[]= new String[jbusers.size()];
		int i=0;
		for (User user : jbusers) {
			userids[i] = user.getObjId();
			i++;
		}
		MessageUtil.sendMessageSystem(userids,"保证金费用已支付","",messageSource.getMessage(EnumMessage.BAIL_PAY),templateMap,false);
	}
	 /**
	 * 
	 * Description : 根据时间范围查询相应的保证金记录表
	 * Create Date: 2010-8-5下午02:58:38 by shenjianzhuang  Modified Date: 2010-8-5下午02:58:38 by shenjianzhuang
	 * @param page
	 * @param renderTime
	 * @param renderTime2
	 * @param returnedTime
	 * @param returnedTime2
	 * @return
	 * @return  Page<BailRecord>
	 * @Exception 
	 */
	public Page<BailRecord> getSelectedBailRecord(Page page, String renderTime,String renderTime2, String returnedTime,String returnedTime2) {
		  logger.debug("\nes BailRecordServiceImpl||getSelectedBailRecord\n");
		return bailRecordDao.getSelectedBailRecord(page,renderTime, renderTime2, returnedTime, returnedTime2);
	}
	
	/**
	 * 
	 * Description :  根据供应商报名记录查询相应的保证金记录
	 * Create Date: 2010-9-1下午03:18:46 by shenjianzhuang  Modified Date: 2010-9-1下午03:18:46 by shenjianzhuang
	 * @param signUprecordId
	 * @return
	 * @return  BailRecord
	 * @Exception 
	 */
	public List<BailRecord> getBailRecordByProjectId(String projectId) {
		logger.debug("\n BailRecordServiceImpl||getBailRecordBySignUprecord\n");
		return bailRecordDao.getBailRecordByProjectId(projectId);
	}


	/**
	 * 
	 * Description :  保存保证金对象
	 * Create Date: 2010-9-7上午09:57:16 by shenjianzhuang  Modified Date: 2010-9-7上午09:57:16 by shenjianzhuang
	 * @param bailRecord
	 * @return
	 * @return  BailRecord
	 * @Exception 
	 */	 
	public BailRecord saveBailRecord(BailRecord bailRecord) {
		logger.debug("\nes BailRecordServiceImpl||saveBailRecord\n");
		bailRecordDao.save(bailRecord);
		Project project = projectManager.get(bailRecord.getProjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bailRecord.setParentBizId(parentBizId);
		User user = AuthenticationHelper.getCurrentUser();
		bailRecord.setUser(user);
		return bailRecord;
	}

	/** 
	 * FuncName : getBailRecordByProjectAndSupplierId
	 * Description :  根据项目Id和供应商Id获取保证金记录
	 * Create Date: 2011-9-27下午06:18:42 by yangx  
	 * Modified Date: 2011-9-27下午06:18:42 by yangx
	 * @param   projectId：项目Id,supplierId：项目Id
	 * @return  List<BailRecord>
	 * @Exception   
	 */
	public List<BailRecord> getBailRecordByProjectAndSupplierId(String projectId,String supplierId){
		return bailRecordDao.getBailRecordByProjectAndSupplierId(projectId,supplierId);
	}

	/**
  	 * Description :  根据主键获取保证金对象
	 * Create Date: 2010-9-7下午05:11:55 by shenjianzhuang  Modified Date: 2010-9-7下午05:11:55 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  BailRecord
	 * @Exception 
	 */			
	public BailRecord getBailRecord(String objId) {
		logger.debug("\nes BailRecordServiceImpl||getBailRecord\n");
		return bailRecordDao.get(objId);
	}
	 /**
	 * FuncName: getBailRecordBySignUprecord
	 * Description :  创建或修改对象
	 * @param 
	 * @param signUprecordId
	 * @return BailRecord
	 * @author: shenjz
	 * @Create Date:2011-4-7  下午05:40:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-4-7  下午05:40:47
	*/		 
	public BailRecord getBailRecordBySignUprecord(String signUprecordId){
		return bailRecordDao.getBailRecordBySignUprecord(signUprecordId);
	}
	/**
	 * FuncName: idCanEntryBailRecord
	 * Description :  是否可以录入保证金
	 * @param 
	 * @param obj
	 * @return boolean
	 * @author: liuke
	 * @Create Date:2011-8-10  上午09:55:18
	 * @Modifier: liuke
	 * @Modified Date:2011-8-10  上午09:55:18
	 */
	public boolean isCanEntryBailRecord(Object[] obj) {
		Date SignUpETime  = (Date) obj[3];   //报名结束时间
		Date BailEndDate = (Date) obj[4];
		int signupCount =   ((BigDecimal)obj[0]).intValue();
		boolean signUpETimePasted = true;//是否已过报名结束时间
		if( SignUpETime == null || new Date().before(SignUpETime)){
			signUpETimePasted = false;
		}
		boolean bailEndDatePasted = true;//是否已过缴纳保证金截止时间
		if( BailEndDate == null || new Date().before(BailEndDate)){
			bailEndDatePasted  = false;
		}
		boolean isAllowPrintBidSupplier = false;//是否允许打印投标供应商清单
		String isAllowPrintBidSupplierHintMsg = "";//对是否允许打印投标供应商清单的提示信息
		if(!isAllowPrintBidSupplier){//如果目前不允许打印投标供应商
			isAllowPrintBidSupplierHintMsg = "";
			if(signUpETimePasted){//在过报名结束时间
				if(signupCount > 0 && signupCount < 3)//已有小于3家供应商报名
					isAllowPrintBidSupplier = true;
				else
					isAllowPrintBidSupplierHintMsg = "已过报名结束时间，但参与报名供应商不在3家以内，因此不能录入保证金。";
			}else
				isAllowPrintBidSupplierHintMsg = "还未到报名结束时间，因此不能录入保证金。";
		}
		if(!isAllowPrintBidSupplier){//如果目前不允许打印投标供应商
			isAllowPrintBidSupplierHintMsg = "";
			if(bailEndDatePasted){//已过交纳保证金截止时间
				if(signupCount >= 3)//有大于或等于3家供应商报名
					isAllowPrintBidSupplier = true;
				else
					isAllowPrintBidSupplierHintMsg = "已过交纳保证金截止时间，但参与报名供应商小于3家，因此不能录入保证金。";
			}else
				isAllowPrintBidSupplierHintMsg = "还未到交纳保证金截止时间，因此不能录入保证金。";
		}
		if(signupCount == 0){
			isAllowPrintBidSupplier = false;
			isAllowPrintBidSupplierHintMsg =  "截止目前，没有供应商参与报名。";
		}
		return isAllowPrintBidSupplier;
	}
	
	/** 
	 * FuncName : removeBailRecordByIds
	 * Description :  根据保证金Ids删除保证金记录
	 * Create Date: 2011-9-27下午04:36:18 by yangx  
	 * Modified Date: 2011-9-27下午04:36:18 by yangx
	 * @param   bailRecordIds：保证金Ids
	 * @return  
	 * @Exception   
	 */
	public void removeBailRecordByIds(String bailRecordIds){
		String[] bailRecordIds_ = bailRecordIds.split(SeparationEnum.COMMA);
		if (bailRecordIds_!=null&&bailRecordIds_.length>0) {
			for (String bailRecordId:bailRecordIds_) {
				BailRecord bailRecord = bailRecordDao.get(bailRecordId);
				if (bailRecord.getRenderAtt()!=null&&!"".equals(bailRecord.getRenderAtt())) {
					List<Attachment> attachRelaIdList = attachmentManager.getAttachmentsByRealID(bailRecord.getRenderAtt());//缴纳证明
					attachmentManager.removeAttachmentAndFile(attachRelaIdList);
				}
			}
			bailRecordDao.remove(bailRecordIds_,BailRecord.class);
		}
	}

	/** 
	 * FuncName : saveConfirmBailRecord
	 * Description :  保存确认保证金记录
	 * Create Date: 2011-9-27下午05:09:51 by yangx  
	 * Modified Date: 2011-9-27下午05:09:51 by yangx
	 * @param   bailRecord：保证金记录
	 * @return  BailRecord
	 * @Exception   
	 */
	public BailRecord saveConfirmBailRecord(BailRecord bailRecord){
		bailRecordDao.save(bailRecord);
		if (bailRecord!=null&&AuditStatusEnum.AUDIT_PASS.equals(bailRecord.getAuditStatus())) {
			completedWorkTaskManager.createCompTaskByPassivity("确认录入保证金","",bailRecord.getOpinion(),bailRecord.getObjId(),bailRecord.getProjId(),"",CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT);
		}else{
			completedWorkTaskManager.createCompTaskByPassivity("确认录入保证金","",bailRecord.getOpinion(),bailRecord.getObjId(),bailRecord.getProjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		}
		Project project = projectManager.get(bailRecord.getProjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bailRecord.setParentBizId(parentBizId);
		bailRecord.setUser(AuthenticationHelper.getCurrentUser(true));
		return bailRecord;
	}
	
	/** 
	 * FuncName : finshConfirmBailRecord
	 * Description :  完成补录保证金功能
	 * Create Date: 2011-11-18上午11:58:37 by yangx  
	 * Modified Date: 2011-11-18上午11:58:37 by yangx
	 * @param   projectId：项目ID
	 * @return  BailRecord
	 * @Exception   
	 */
	public BailRecord finishConfirmBailRecord(String projectId){
		BailRecord bailRecord = new BailRecord();
		bailRecord.setParentBizId(projectId);
		bailRecord.setUser(AuthenticationHelper.getCurrentUser(true));
		return bailRecord;
	}
}
