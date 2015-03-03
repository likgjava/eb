package com.gpcsoft.epp.projreview.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.common.domain.FileVirtualPathEnum;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projreview.dao.EvalFactorResultDao;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.domain.EvalFactorResult;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.manager.EvaSellerRecordManager;
import com.gpcsoft.epp.projreview.manager.EvalBidRecordManager;
import com.gpcsoft.epp.projreview.manager.OpenBidManager;
import com.gpcsoft.epp.projreview.manager.OpenBidRecordManager;
import com.gpcsoft.epp.projreview.manager.OpenbidGeneralviewManager;
import com.gpcsoft.epp.projreview.service.EvaSellerRecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class EvaSellerRecordServiceImpl extends BaseGenericServiceImpl<EvaSellerRecord> implements EvaSellerRecordService {

	@Autowired(required=true) @Qualifier("evaSellerRecordManagerImpl")
	private EvaSellerRecordManager evaSellerRecordManager;

	@Autowired(required=true) @Qualifier("evalBidRecordManagerImpl")
	private EvalBidRecordManager evalBidRecordManager;
    
	@Autowired(required=true) @Qualifier("openBidRecordManagerImpl")
	private OpenBidRecordManager openBidRecordManager;
	
	@Autowired(required=true) @Qualifier("openBidManagerImpl")
	private OpenBidManager openBidManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;
	
	@Autowired(required=true) @Qualifier("openbidGeneralviewManagerImpl")
	private OpenbidGeneralviewManager openbidGeneralviewManager;
	
	@Autowired(required=true)  @Qualifier("evalFactorResultDaoHibernate")
	private EvalFactorResultDao evalFactorResultDao;
	
	 /**
	  * FuncName:getEvaSellerRecordListByProjectId
	  * Description :根据项目主键得到当前人录入的评审记录  
	  * @param   subProjectId:包组主键
	  * @return  List<EvaSellerRecord>
	  * @author 	liuke
	  * @Create Date: 2010-6-4下午01:32:35 
	  * @Modifier    liuke
	  * @Modified Date: 2010-6-4下午01:32:35 
	  */
	public List<EvaSellerRecord> getEvaSellerRecordListByProjectId(String subProjectId) {
		logger.debug("\nes EvaSellerRecordServiceImpl||getEvaSellerRecordListByProjectId\n");
		List<EvaSellerRecord> evaSellerRecordList = new ArrayList<EvaSellerRecord>();
		List<EvaSellerRecord> evaSellerRecordTableList = evaSellerRecordManager.getEvaSellerRecordList(subProjectId);
		OpenBid openBid = openBidManager.getOpenBidBySubProjectId(subProjectId);
		String oPenBidId ="";
		if(null!=openBid) {
			oPenBidId = openBid.getObjId();
		}
		List<OpenBidRecord>  openBidRecordList = openBidRecordManager.getOpenBidRecordByOPenBidId(oPenBidId);
			for(int i=0;openBidRecordList != null && i<openBidRecordList.size();i++) {    //循环开标记录表中信息
				EvaSellerRecord evaSellerRecord = new EvaSellerRecord();
				OpenBidRecord openBidRecord = (OpenBidRecord) openBidRecordList.get(i);
				OpenbidGeneralview penbidGeneralview = openbidGeneralviewManager.getOpenbidGeneralviewByOpenBidRecordId(openBidRecord.getObjId());
				evaSellerRecord.setSupplier(openBidRecord.getSupplier());
				evaSellerRecord.setSupplierName(openBidRecord.getSellerName());
				BigDecimal bigDecimal = null;
				if(null!= penbidGeneralview){
					bigDecimal = new BigDecimal(penbidGeneralview.getBidQuotesum());
				}else{
					bigDecimal = openBidRecord.getQuoteSum();
				}
				evaSellerRecord.setQuoteSum(bigDecimal);
				evaSellerRecord.setSubProjId(subProjectId);
				 for(int j=0;j<evaSellerRecordTableList.size();j++) {   //循环评标表中信息
					 EvaSellerRecord evaSellerRecords = (EvaSellerRecord)evaSellerRecordTableList.get(j);
					 if(openBidRecord.getSupplier().getObjId().equals(evaSellerRecords.getSupplier().getObjId()))  {
						 evaSellerRecord = evaSellerRecords;
					 }
				 }
				 evaSellerRecordList.add(evaSellerRecord); 
			}
	    return evaSellerRecordList;
	}
	
	/**
	  * FuncName:saveAllEvaSellerRecord
	  * Description :   修改或更新评标供应商表 
	  * @param   evaSellerRecord:开标记录,projectId:项目主键
	  * @return  EvaSellerRecord
	  * @author 	liuke
	  * @Create Date: 2010-6-4下午05:15:46 
	  * @Modifier   liuke
	  * @Modified Date: 2010-6-4下午05:15:46 
	  */
	public EvaSellerRecord saveAllEvaSellerRecord(EvaSellerRecord evaSellerRecord,String projectId) {
		logger.debug("\nes EvaSellerRecordServiceImpl||saveAllEvaSellerRecord\n");
		//根据项目主键查询评标主表对象是否存在
		String subProjectId = evaSellerRecord.getSubProjId();
		Project project = projectManager.get(projectId);
		/** 代理机构录入评审信息 */
		EvalBidRecord  evalBidRecord = evalBidRecordManager.getEvalBidRecordBySubProjectIdAndExpertName(subProjectId, evaSellerRecord.getEvalLinkerName());
		if(null==evalBidRecord) {//如果评标主表对象不存在，则保存主表
			EvalBidRecord newEvalBidRecord = new EvalBidRecord();
			newEvalBidRecord.setEvalLinkerName(evaSellerRecord.getEvalLinkerName());
			newEvalBidRecord.setProjId(projectId);
			newEvalBidRecord.setProjCode(project.getProjCode());
			newEvalBidRecord.setProjName(project.getProjName());
			newEvalBidRecord.setSubProjId(subProjectId);
			evalBidRecordManager.save(newEvalBidRecord);
			String evalId = newEvalBidRecord.getObjId();
			evaSellerRecord.setEvalId(evalId);	
		} else {
			String evalId = evalBidRecord.getObjId();
			evaSellerRecord.setEvalId(evalId);
		} 
		evaSellerRecord.setSubProjName(project.getProjName());//保存评标子表对象
		evaSellerRecord.setSubProjCode(project.getProjCode());
		evaSellerRecordManager.save(evaSellerRecord);
		projectManager.saveProjProcessStatus(projectId, ProjProcessStatusEnum.EXPERT_ACCREDITATION);//保存项目实施状态
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		evaSellerRecord.setParentBizId(parentBizId);
		User user = AuthenticationHelper.getCurrentUser();
		evaSellerRecord.setUser(user);
		return evaSellerRecord;
	}
	
	/**
	  * FuncName:updateAllEvaSellerRecord
	  * Description :修改或更新评标供应商表 
	  * @param   evaSellerRecord:开标记录,projectId:项目主键
	  * @return EvaSellerRecord
	  * @author liuke  
	  * @Create Date: 2010-6-4下午05:15:46 
	  * @Modifier liuke 
	  * @Modified Date: 2010-6-4下午05:15:46
	  */
	public EvaSellerRecord updateAllEvaSellerRecord(EvaSellerRecord evaSellerRecord,String projectId) {
		logger.debug("\nes EvaSellerRecordServiceImpl||updateAllEvaSellerRecord\n");
		String subProjectId = evaSellerRecord.getSubProjId();//根据项目主键查询评标主表对象是否存在
		Project project = projectManager.get(projectId);
		EvalBidRecord  evalBidRecord = evalBidRecordManager.getEvalBidRecordBySubProjectIdAndExpertName(subProjectId, evaSellerRecord.getEvalLinkerName());//代理机构录入评审信息
		if(null==evalBidRecord) {//如果评标主表对象不存在，则保存主表
			EvalBidRecord newEvalBidRecord = new EvalBidRecord();
			newEvalBidRecord.setEvalLinkerName(evaSellerRecord.getEvalLinkerName());
			newEvalBidRecord.setProjId(projectId);
			newEvalBidRecord.setProjCode(project.getProjCode());
			newEvalBidRecord.setProjName(project.getProjName());
			newEvalBidRecord.setSubProjId(subProjectId);
			evalBidRecordManager.save(newEvalBidRecord);
			String evalId = newEvalBidRecord.getObjId();
			evaSellerRecord.setEvalId(evalId);
		} else {
			String evalId = evalBidRecord.getObjId();
			evaSellerRecord.setEvalId(evalId);
		} 
		evaSellerRecord.setSubProjName(project.getProjName());//保存评标子表对象
		evaSellerRecord.setSubProjCode(project.getProjCode());
		evaSellerRecordManager.save(evaSellerRecord);
		projectManager.saveProjProcessStatus(projectId, ProjProcessStatusEnum.EXPERT_ACCREDITATION);//保存项目实施状态
		return evaSellerRecord;
	}
	
	 /**
	  * FuncName:getOrgInfobyObjId
	  * Description :根据主键得到机构信息  
	  * @param   objId:机构主键
	  * @return  OrgInfo
	  * @author lic
	  * @Create Date: 2010-6-20下午02:20:41
	  * @Modifier  lic 
	  * @Modified Date: 2010-6-20下午02:20:41 
	  */
	public OrgInfo getOrgInfobyObjId(String objId) {
		logger.debug("\nes EvaSellerRecordServiceImpl||getOrgInfobyObjId\n");
		OrgInfo  supplier =(OrgInfo)this.get(objId,OrgInfo.class);
		return supplier;
	}

	/** 
	  * FuncName:getSubProjectByProjectId
	  * Description :根据项目主键获取对应的所有包组信息
	  * @param projectId:项目主键
	  * @return  List<Project>
	  * @author Administrator
	  * @Create Date: 2010-5-21下午02:25:13 
	  * @Modifier   Administrator
	  * @Modified Date: 2010-5-21下午02:25:13  
	  */
	public List<Project> getSubProjectByProjectId(String projectId)throws Exception {
		logger.debug("\n EvaSellerRecordServiceImpl||getSubProjectByProjectId\n");
		return projectManager.getSubProjectByQueryObject(projectId);
	}

	/**
	  * FuncName:getAllAuditMessage
	  * Description : 根据包组Id和供应商Id得到评标结果列表
	  * @param   supplierId:供应商主键,subProjId:包组主键
	  * @return  List<EvaSellerRecord>
	  * @author liuke
	  * @Create Date: 2010-6-23下午04:14:42 
	  * @Modifier   liuke
	  * @Modified Date: 2010-6-23下午04:14:42  
	  */
	@SuppressWarnings("unchecked")
	public List<EvaSellerRecord> getAllAuditMessage(String supplierId,String subProjId) throws Exception {
		logger.debug("\n EvaSellerRecordServiceImpl||getAllAuditMessage\n");
		QueryObject queryObject = new QueryObjectBase();
		queryObject.setEntityClass(EvaSellerRecord.class);
		queryObject.getQueryParam().and(new QueryParam("supplier.objId",QueryParam.OPERATOR_EQ,supplierId));
		queryObject.getQueryParam().and(new QueryParam("subProjId",QueryParam.OPERATOR_EQ,subProjId));
		List<EvaSellerRecord> list = evaSellerRecordManager.findByQuery(queryObject);
		return list;
	}

	/** 
	 * FuncName:saveBidEvaluationReport
	 * Description :  保存评审报告
	 * @param bulletin:评审报告
	 * @return Bulletin:评审报告
	 * @author Administrator
	 * @Create Date: 2010-6-5上午11:30:42 
	 * @Modifier Administrator
     * @Modified Date: 2010-6-5上午11:30:42
	 */
	public Bulletin saveBidEvaluationReport(Bulletin bulletin) {
		logger.debug("\n EvaSellerRecordServiceImpl||saveBidEvaluationReport\n");
		String filePath = messageSource.getMessage("uploadUrl");//创建附件对象
		String path=filePath+FileVirtualPathEnum.BUYBIDRESULT;
		FileUtil.mkdirs(path);
		String saveName=java.util.UUID.randomUUID().toString();
		bulletin.setContent(getAttachment(FileVirtualPathEnum.BUYBIDRESULT,bulletin,saveName));
		bulletinManager.save(bulletin);
		FileUtils.writerFile(path+saveName,bulletin.getBullContents());//把公告内容写到指定文件中
		Project subProject = projectManager.get(bulletin.getProject().getObjId());//保存项目实施状态
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());//项目规则
		String projectId = "";
		if(projectRule!=null&&projectRule.getIsDividePack()!=null&&!projectRule.getIsDividePack()){ //项目没分包
			projectId = subProject.getObjId();
		}else{//项目分包
			projectId = subProject.getParentId();
		}
		projectManager.saveProjProcessStatus(projectId, ProjProcessStatusEnum.GENERATE_EVALUATION_REPORT);
		Project project = projectManager.get(bulletin.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		User user = AuthenticationHelper.getCurrentUser();
		bulletin.setUser(user);
		bulletin.setParentBizId(parentBizId);
		return bulletin;
	}
    
	/**
	 * FuncName:getbidEvaluationReportByProjectId
	 * Description:根据项目主键得到评标报告信息
	 * @param   subProjId:包组主键
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-6-25下午02:58:31 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-25下午02:58:31 
	 */
	public Bulletin getbidEvaluationReportByProjectId(String subProjId) {
		logger.debug("\n EvaSellerRecordServiceImpl||getbidEvaluationReportByProjectId\n");
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.BID_EVALUATION_REPORT));//公告类型为：评标报告
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,subProjId));
		Bulletin bulletin = bulletinManager.getBulletinByQueryObject(queryObject);
		if(bulletin!=null&&bulletin.getContent()!=null){
			String filePath = messageSource.getMessage("uploadUrl");
			String attachPath = bulletin.getContent().getPath()==null?"":bulletin.getContent().getPath();
			String bullContent = FileUtils.readFileByLines(filePath+attachPath+bulletin.getContent().getFileName(), "UTF-8");//读取公告内容
			bulletin.setBullContents(bullContent);
		}
		return bulletin;
	}
	
	/**
	 * FuncName:getEvaSellerRecordListForEval
	 * Description :得到评审对象  
	 * @param   subProjId:包组主键,supplierId:供应商主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-8-30下午07:36:34
	 * @Modifier  liuke
	 * @Modified Date: 2010-8-30下午07:36:34  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordListForEval(String subProjId, String supplierId) {
		logger.debug("\n EvaSellerRecordServiceImpl||getEvaSellerRecordListForEval\n");
		List<EvaSellerRecord> evaSellerRecordTableList = evaSellerRecordManager.getEvaSellerRecordListBySubProjectAndSupplier(subProjId, supplierId); 
		for(int i=0;i<evaSellerRecordTableList.size();i++){
			EvaSellerRecord sellRecord = evaSellerRecordTableList.get(i);
			EvalBidRecord bidRecord = evalBidRecordManager.get(sellRecord.getEvalId());
			sellRecord.setEvalLinkerName(bidRecord.getEvalLinkerName());
		}
		return evaSellerRecordTableList;
	}

	/** 
	 * FuncName:saveSubmitBidEvaluationReport
	 * Description :提交评审报告
	 * @param bulletin
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-6-5上午11:30:42   
	 * @Modifier Administrator
	 * @Modified Date: 2010-6-5上午11:30:42 
	 */
	public Bulletin saveSubmitBidEvaluationReport(Bulletin bulletin) {
		logger.debug("\n EvaSellerRecordServiceImpl||saveSubmitBidEvaluationReport\n");
		String filePath = messageSource.getMessage("uploadUrl");//创建附件对象
		String path=filePath+FileVirtualPathEnum.BUYBIDRESULT;
		if (bulletin.getContent()==null||bulletin.getContent().getPath()==null) {
			FileUtil.mkdirs(path);
			String saveName=java.util.UUID.randomUUID().toString();
			bulletin.setContent(getAttachment(FileVirtualPathEnum.BUYBIDRESULT, bulletin, saveName));
		}
		bulletinManager.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		Project subProject = projectManager.get(bulletin.getProject().getObjId());//保存项目实施状态
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());//项目规则
		String projectId = "";
		if(projectRule!=null&&projectRule.getIsDividePack()!=null&&!projectRule.getIsDividePack()){ //项目没分包
			projectId = subProject.getObjId();
		}else{//项目分包
			projectId = subProject.getParentId();
		}
		projectManager.saveProjProcessStatus(projectId, ProjProcessStatusEnum.GENERATE_EVALUATION_REPORT);
		Project project = projectManager.get(bulletin.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		User user = AuthenticationHelper.getCurrentUser();
		bulletin.setUser(user);
		bulletin.setParentBizId(parentBizId);
		return bulletin;
	}

	/**
	 * FuncName:getEvaSellerRecordList
	 * Description : 根据项目主键评标结果
	 * @param subProjId:包组主键
	 * @return List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-26下午01:30:15 
	 * @Modifier liuke
	 * @Modified Date: 2010-6-26下午01:30:15  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordList(String subProjId) {
		logger.debug("\n EvaSellerRecordServiceImpl||getEvaSellerRecordList\n");
		return evaSellerRecordManager.getEvaSellerRecordList(subProjId);
	}
	
	/** 
	 * FuncName:getAttachment
	 * Description:组装公告附件对像
	 * @param   path:公告文件存储路径,bulletin:公告对象,fileName:文件名称
	 * @return  Attachment
	 * @author yangx
	 * @Create Date: 2010-9-21下午04:00:13 
	 * @Modifier  yangx  
	 * @Modified Date: 2010-9-21下午04:00:13     
	 */
	private Attachment getAttachment(String path,Bulletin bulletin,String fileName){
		logger.debug("\n EvaSellerRecordServiceImpl||getAttachment\n");
		Attachment attachment = new Attachment();//公告文件的存储路径和文件名
		attachment.setPath(path);
		attachment.setViewName(bulletin.getBullTitle());
		attachment.setFileName(fileName);
		attachment.setCreateTime(bulletin.getCreateTime());
		return attachment;
	}
	
	/**
	 * FuncName:saveCreateEvaSellerRecord
	 * Description: 创建评审记录与批量保存评审打分
	 * @param evaSellerRecord:评审记录,evalFactorResultList:评审打分,packId:包组主键
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午06:00:42 
	 */
	public EvaSellerRecord saveCreateEvaSellerRecord(EvaSellerRecord evaSellerRecord,List<EvalFactorResult> evalFactorResultList,String packId){
		logger.debug("\n EvaSellerRecordServiceImpl||saveCreateEvaSellerRecord\n");
		//EvalBidRecord evalBidRecord = evalBidRecordManager.getEvalBidRecordBySubProjectIdAndUserId(packId, user.getObjId());// 获取评审记录主表
		User user = AuthenticationHelper.getCurrentUser();
		if (null == evaSellerRecord.getEvalId() || "".equals(evaSellerRecord.getEvalId())) {// 没有评审记录,则创建评审记录
			EvalBidRecord evalBidRecord = new EvalBidRecord();
			Project pack = (Project)projectManager.get(packId,Project.class);
			Project project = pack;
			if (null != pack.getParentId() && !"".equals(pack.getParentId())) {
				project = (Project)projectManager.get(pack.getParentId(),Project.class);
			}
			evalBidRecord.setCreateTime(new java.util.Date());
			evalBidRecord.setCreateUser(user);
			evalBidRecord.setEvalLinker(user.getObjId());
			evalBidRecord.setEvalLinkerName(user.getEmp().getName());
			evalBidRecord.setProjName(project.getProjName());
			evalBidRecord.setProjId(project.getObjId());
			evalBidRecord.setProjCode(project.getProjCode());
			evalBidRecord.setSubProjId(pack.getObjId());
			evalBidRecordManager.save(evalBidRecord);
			evaSellerRecord.setEvalId(evalBidRecord.getObjId());
			evaSellerRecord.setObjId(null);
			evaSellerRecord.setParentBizId(project.getObjId());
			evaSellerRecord.setUser(user);
		}else{// 更新评审记录
			EvalBidRecord evalBidRecord = evalBidRecordManager.get(evaSellerRecord.getEvalId());
			evalBidRecord.setUpdateTime(new java.util.Date());
			evalBidRecord.setUpdateUser(user);
			evalBidRecordManager.save(evalBidRecord);
		}
		evaSellerRecordManager.save(evaSellerRecord);
		for (EvalFactorResult evalFactorResult:evalFactorResultList) {
			if ("".equals(evalFactorResult.getObjId())) {
				evalFactorResult.setObjId(null);
			}
			evalFactorResult.setSellerRecId(evaSellerRecord.getObjId());
			evalFactorResultDao.save(evalFactorResult);
		}
		return evaSellerRecord;
	}
	
	/**
	 * FuncName:updateEvaSellerRecord
	 * Description: 更新评审记录与批量保存评审打分
	 * @param evaSellerRecord 评审记录,evalFactorResultList:评审打分,packId:包件ID
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午06:00:42  
	 */
	public EvaSellerRecord updateEvaSellerRecord(EvaSellerRecord evaSellerRecord,List<EvalFactorResult> evalFactorResultList,String packId){
		logger.debug("\n EvaSellerRecordServiceImpl||updateEvaSellerRecord\n");
		//EvalBidRecord evalBidRecord = evalBidRecordManager.getEvalBidRecordBySubProjectIdAndUserId(packId, user.getObjId());// 获取评审记录主表
		User user = AuthenticationHelper.getCurrentUser();
		if (null == evaSellerRecord.getEvalId() || "".equals(evaSellerRecord.getEvalId())) {// 没有评审记录,则创建评审记录
			EvalBidRecord evalBidRecord = new EvalBidRecord();
			Project pack = (Project)projectManager.get(packId,Project.class);
			Project project = pack;
			if (null != pack.getParentId() && !"".equals(pack.getParentId())) {
				project = (Project)projectManager.get(pack.getParentId(),Project.class);
			}
			evalBidRecord.setCreateTime(new java.util.Date());
			evalBidRecord.setCreateUser(user);
			evalBidRecord.setEvalLinker(user.getObjId());
			evalBidRecord.setEvalLinkerName(user.getEmp().getName());
			evalBidRecord.setProjName(project.getProjName());
			evalBidRecord.setProjId(project.getObjId());
			evalBidRecord.setProjCode(project.getProjCode());
			evalBidRecord.setSubProjId(pack.getObjId());
			evalBidRecordManager.save(evalBidRecord);
			evaSellerRecord.setEvalId(evalBidRecord.getObjId());
			evaSellerRecord.setObjId(null);
		}else{// 更新评审记录
			EvalBidRecord evalBidRecord = evalBidRecordManager.get(evaSellerRecord.getEvalId());
			evalBidRecord.setUpdateTime(new java.util.Date());
			evalBidRecord.setUpdateUser(user);
			evalBidRecordManager.save(evalBidRecord);
		}
		evaSellerRecordManager.save(evaSellerRecord);
		for (EvalFactorResult evalFactorResult:evalFactorResultList) {
			if ("".equals(evalFactorResult.getObjId())) {
				evalFactorResult.setObjId(null);
			}
			evalFactorResult.setSellerRecId(evaSellerRecord.getObjId());
			evalFactorResultDao.save(evalFactorResult);
		}
		return evaSellerRecord;
	}
   
	/** 
	 * FuncName : finishEvaSellerRecord
	 * Description :  完成评审打分
	 * Create Date: 2011-12-20 上午10:55:33 by caojz  
	 * Modified Date: 2011-12-20 上午10:55:33 by caojz
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public EvaSellerRecord finishEvaSellerRecord(String projectId) {
		EvaSellerRecord evaSellerRecord  = new EvaSellerRecord();
		evaSellerRecord.setParentBizId(projectId);
		evaSellerRecord.setUser(AuthenticationHelper.getCurrentUser(true));
	    evaSellerRecord.setSubProjId(projectId);
		return evaSellerRecord;
	}
}
