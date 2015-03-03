package com.gpcsoft.epp.purchasedoc.decision;

import java.io.File;
import java.util.List;
import java.util.UUID;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.manager.ProjectMTaskPlanManager;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocManager;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.ElectronicReviewService;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的起草招标文件操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class DraftPurDocDesion implements ProjectPlanDesion {

	private ProjProcessRuleManager projProcessRuleManager;
	private ProjProcessRuleManager getProjProcessRuleManager(){
		if(this.projProcessRuleManager == null){
				this.projProcessRuleManager = (ProjProcessRuleManager)FrameBeanFactory.getBean("projProcessRuleManagerImpl");
		}
		return this.projProcessRuleManager;
	}
	private AttachmentService attachmentService;
	private AttachmentService getAttachmentService(){
		if(attachmentService==null){
			this.attachmentService = (AttachmentService)FrameBeanFactory.getBean("attachmentServiceImpl");
		}
		return this.attachmentService;
	}
	private ProjectMTaskPlanManager projectMTaskPlanManager;
	private ProjectMTaskPlanManager getProjectMTaskPlanManager(){
		if(this.projectMTaskPlanManager == null){
				this.projectMTaskPlanManager = (ProjectMTaskPlanManager)FrameBeanFactory.getBean("projectMTaskPlanManagerImpl");
		}
		return this.projectMTaskPlanManager;
	}
	
	private FrameMessageResource messageSource;
	private FrameMessageResource getMessageSource(){
		if(this.messageSource == null){
			this.messageSource = (FrameMessageResource)FrameBeanFactory.getBean("frameMessageResource");
		}
		return this.messageSource;
	}
	
	private ElectronicReviewService electronicReviewService;
	private ElectronicReviewService getElectronicReviewService(){
		if(electronicReviewService==null){
			this.electronicReviewService = (ElectronicReviewService)FrameBeanFactory.getBean("electronicReviewServiceImpl");
		}
		return this.electronicReviewService;
	}
	
	private PurchaseDocManager purchaseDocManager;
	private PurchaseDocManager getPurchaseDocManager(){
		if(this.purchaseDocManager == null){
				this.purchaseDocManager = (PurchaseDocManager)FrameBeanFactory.getBean("purchaseDocManagerImpl");
		}
		return this.purchaseDocManager;
	}
	/** 
	 * Description :  判断决策是否可以结束提交招标文件业务
	 * Create Date: 2010-12-2上午10:49:34 by yangx  Modified Date: 2010-12-2上午10:49:34 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject		对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		PurchaseDoc purchaseDoc = (PurchaseDoc)bizObject;//业务对象
		purchaseDoc.setConfirmStatus(PurchaseDocEnum.FORAUDIT);
		purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		//获取业务规则
		ProjProcessRule projProcessRule= this.getProjProcessRuleManager().getProjProcessRuleByProjectIdAndCode(purchaseDoc.getProject().getObjId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		if (projProcessRule!=null&&"false".equals(projProcessRule.getResValue())) {//业务规则不需要采购人确认招标文件
			Double sumPrice = 0.0;//获取预算总金额
			List taskPalnSubList = this.getProjectMTaskPlanManager().getAllTaskPlanSubByProject(purchaseDoc.getProject().getObjId());
			for (int i=0;i<taskPalnSubList.size();i++) {
				TaskPlanSub taskPlanSub = (TaskPlanSub) taskPalnSubList.get(i);
				sumPrice += taskPlanSub.getTotalPrice().doubleValue();
			}
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);//采购办审核通过(因去掉了采购文件审核节点)
		}else{
			purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);//00待采购人确认   01 采购人确认通过  02  采购办审核通过 03 监察局审核通过
		}
		
		this.createPurchaseFile(purchaseDoc);
		this.getPurchaseDocManager().save(purchaseDoc,PurchaseDoc.class);
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}
	
	
	//创建待下载的招标文件方法
	private void createPurchaseFile(PurchaseDoc purchaseDoc)throws Exception{
		//添加压缩招标文件代码
		List<Attachment>attachmentList = this.getAttachmentService().getAttachmentsByRealID(purchaseDoc.getAttachRelaId());	
		String fileName = "";
		String viewName = ""; 
		if(!attachmentList.isEmpty()){
			Attachment attachment = attachmentList.get(0);
			fileName = attachment.getFileName();
			viewName = attachment.getViewName();
			if(viewName.indexOf("ebd")<=0 && fileName.indexOf("ebd")<=0){//表示该文件未压缩过(政采统一格式ebd)的招标文件，需要做压缩操作
				String attPath = attachment.getPath()==null? "" : attachment.getPath();
				String filePath =(attPath.replaceAll("\\$\\{rootPath}", Constants.ROOTPATH)+attachment.getFileName()).replace("/", File.separator);
				String str = getMessageSource().getMessage("epp.projectAttaPathTemp");
				String tempPath = str+File.separator+purchaseDoc.getProject().getObjId()+File.separator+fileName;
				String targetPath =	getPurchaseDocManager().createNewPurchaseFile(tempPath,filePath,fileName, viewName);  //创建临时文件夹，并拷贝word文件到临时文件夹
				this.createEvalFactorsXmlFile(targetPath, purchaseDoc.getProject().getObjId());
				this.createBiddingDocumentFile(targetPath,fileName);
				String purDocFilePath = File.separator+purchaseDoc.getProject().getObjId()+File.separator+CommonEnum.PUR_FILE_DIR;//采购文件ZIP路径
				getPurchaseDocManager().createZipFile(purDocFilePath,targetPath, fileName, viewName);
				Attachment zipattachment = new Attachment();
				zipattachment.setFileName(fileName);
				zipattachment.setPath(purDocFilePath);
				String viewNameTemp = viewName.substring(0, viewName.lastIndexOf("."));
				zipattachment.setViewName(viewNameTemp+".ebd");
				zipattachment.setRelationId(UUID.randomUUID().toString());
				zipattachment = (Attachment) this.getAttachmentService().save(zipattachment);
				purchaseDoc.setAttachRelaId(zipattachment.getRelationId());
				FileUtil.delFolder(tempPath);//清空临时文件夹
			}
		}
	}
	
	//创建EvalFactorsXml文件方法
	private String createEvalFactorsXmlFile(String targetPath,String projectId){
	    String targetFilePath ="";
		Project project = (Project) getPurchaseDocManager().get(projectId, Project.class);
	    String xmlContent = this.getElectronicReviewService().getCongruousFactor(project.getProjCode(), "");
	    targetFilePath = targetPath+File.separator+"EvalFactors.xml";
		FileUtil.writerFile(targetFilePath, xmlContent, "utf-8");
		return "";
	}
	
	//创建BiddingDocumentXml文件方法
	private String createBiddingDocumentFile(String targetPath,String docId)throws Exception{
		String targetFilePath="";
		//获得上传招标文件后缀名     add by liuke 2011.11.30
		String endFlag = "";
		String[] l = FileUtil.listFiles(targetPath);
		for (int i = 0; i < l.length; i++) {
			String name = l[i].substring(0,l[i].indexOf("."));
			if(docId.equals(name)){
				endFlag = l[i].substring(l[i].indexOf("."));
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<format name=\"招标书格式\" docId=\"对应的文档名称\" remark=\"备注\">");
		sb.append("<section name=\"招标文件\" docId=\""+docId+"\"  fileName=\""+docId+endFlag+"\"  type=\"Default\" />");
		sb.append("</format>");
		String content = sb.toString();
		targetFilePath = targetPath+File.separator+"BiddingDocument.xml";
		FileUtil.writerFile(targetFilePath, content, "utf-8");
		return "";
	}
}
