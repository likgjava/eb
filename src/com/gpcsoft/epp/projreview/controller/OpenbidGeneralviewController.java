package com.gpcsoft.epp.projreview.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.service.BidPackageService;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;
import com.gpcsoft.epp.eval.service.PackCongFactorResponseService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;
import com.gpcsoft.epp.projreview.service.OpenBidGeneralVitemService;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.projreview.service.OpenbidGeneralviewService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;


/**
 *  @gpcsoft.view value="openbidGeneralviewList"
 *  url="view/es/planform/projreview/OpenbidGeneralviewList.jsp"
 *  @gpcsoft.view value="openbidGeneralListview"
 *  url="view/es/planform/projreview/openbidGeneralListview.jsp"
 *  @gpcsoft.view value="newOpenbidGeneralviewList"
 *  url="view/es/planform/projreview/newOpenbidGeneralviewList.jsp" 
 *  @gpcsoft.view value="openbidGeneralview"
 *  url="view/es/planform/projreview/openbidGeneralview.jsp" 
 *  @gpcsoft.view value="updateOpenbidGeneralview"
 *  url="view/es/planform/projreview/updateOpenbidGeneralview.jsp" 
 *  @gpcsoft.view value="blankView"
 *  url="view/es/common/blank.jsp"
 *  @gpcsoft.view value="putInOpenbidGeneralviewList"
 *  url="view/es/planform/projreview/putInOpenbidGeneralviewList.jsp"
 *  @gpcsoft.view value="toViewOpenbidGeneral"
 *  url="view/es/planform/projreview/putInOpenbidGeneralview.jsp"
 *  
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OpenbidGeneralview.class})
@RequestMapping("/OpenbidGeneralviewController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class OpenbidGeneralviewController extends AnnotationMultiController<OpenBidRecord> {

	@Autowired(required=true) @Qualifier("openbidGeneralviewServiceImpl")
	private OpenbidGeneralviewService openbidGeneralviewService;
	
	@Autowired(required=true) @Qualifier("genviewDefineServiceImpl")
	private GenviewDefineService genviewDefineService;
	
	@Autowired(required=true) @Qualifier("openBidRecordServiceImpl")
	private OpenBidRecordService openBidRecordService;
	
	@Autowired(required=true) @Qualifier("bidPackageServiceImpl")
	private BidPackageService bidPackageService;
	
	@Autowired(required=true) @Qualifier("packCongFactorResponseServiceImpl")
	private PackCongFactorResponseService packCongFactorResponseService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	

	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("openBidGeneralVitemServiceImpl")
	private OpenBidGeneralVitemService openBidGeneralVitemService;
	
	/**
	 * FuncName:toOpenbidGeneralListview
	 * Description:跳转到开标一览表List
	 * @param projectId:项目主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-8-1 下午01:42:30 
	 */
	@RequestMapping(params = "method=toOpenbidGeneralListview")
	public ModelAndView toOpenbidGeneralListview(String projectId)throws Exception {
		logger.debug("\nes OpenbidGeneralviewController||toOpenbidGeneralListview\n");
		String returnUrl = "openbidGeneralviewList";
		Map params = new HashMap();
		if(null == projectId || "".equals(projectId) || "NULL".equals(projectId.toUpperCase())){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_OPENBID_LIST_IS_ERROR));
		}
		List<OpenbidGeneralview> openbidGeneralviewList = openbidGeneralviewService.getOpenbidGeneralviewList(projectId);
		Set<Project> packSet = new HashSet<Project>();
		for(OpenbidGeneralview openbidGeneralview:openbidGeneralviewList){
			packSet.add(openbidGeneralview.getSubProj());
		}
		if(openbidGeneralviewList==null||openbidGeneralviewList.isEmpty()){
			returnUrl ="blankView";
		}else{
			params.put("openbidGeneralviewList", openbidGeneralviewList);
			params.put("packList", packSet.toArray());
		}
		return new ModelAndView(returnUrl,params);  
	}
	
	/**
	 * FuncName:toNewOpenbidGeneralListview
	 * Description: 跳转到新开标一览表List
	 * @param projectId:项目主键
	 * @return ModelAndView
	 * @author liuke
	 * @Create Date 2010-8-1 下午01:42:30 
	 */
	@RequestMapping(params = "method=toNewOpenbidGeneralListview")
	public ModelAndView toNewOpenbidGeneralListview(String projectId)throws Exception {
		logger.debug("\nes OpenbidGeneralviewController||toNewOpenbidGeneralListview\n");
		if(null == projectId || "".equals(projectId) || "NULL".equals(projectId.toUpperCase())){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_OPENBID_LIST_IS_ERROR));
		}
	    List<OpenbidGeneralview> openbidGeneralviewList = openbidGeneralviewService.getOpenbidGeneralviewList(projectId);
		List<GenviewDefine> genviewDefineList =  genviewDefineService.getGenviewDefineListByProjectId(projectId);  
		List<OpenBidRecord> openBidRecordList =  openBidRecordService.getAllOpenBidRecordListByProjectId(projectId);
		List<BidPackage> bidPackageList = bidPackageService.getAllBidPackageListByProjectId(projectId);
		List<PackCongFactorResponse> packCongFactorResponseList = packCongFactorResponseService.getAllPackCongFactorResponseListByProjectId(projectId);
		openbidGeneralviewService.saveAllOpenbidGeneralviewByOpenBidRecord(openBidRecordList,projectId);//录入开标一览表数据
		Set<Project> packSet = new HashSet<Project>();
		for(OpenbidGeneralview openbidGeneralview:openbidGeneralviewList){
			packSet.add(openbidGeneralview.getSubProj());
		}
		Map params = new HashMap();
		params.put("projectId", projectId);
		params.put("genviewDefineList", genviewDefineList);
		params.put("packCongFactorResponseList", packCongFactorResponseList);
		params.put("bidPackageList", bidPackageList);
		params.put("openBidRecordList", openBidRecordList);
		params.put("packList", packSet.toArray());
		return new ModelAndView("newOpenbidGeneralviewList",params);  
	}
	
	/**
	 * FuncName:saveOpenbidGeneralview
	 * Description:新增开标一览表对象  
	 * @param   openbidGeneralview:开标一览表,status
	 * @return  ModelAndView
	 * @author lic
	 * @Create Date: 2010-9-27下午04:17:56 
	 * @Modifier    lic
	 * @Modified Date: 2010-9-27下午04:17:56 
	 */
	@RequestMapping(params = "method=saveOpenbidGeneralview")
	public ModelAndView saveOpenbidGeneralview(OpenbidGeneralview openbidGeneralview,SessionStatus status)throws Exception {
		logger.debug("\nes OpenbidGeneralviewController||saveOpenbidGeneralview\n");
		openbidGeneralviewService.saveOpenbidGeneralviewObject(openbidGeneralview);// 1.保存开标一览表
		status.setComplete();
		Map params = new HashMap();
		params.put("openbidGeneralview", openbidGeneralview);
		return new ModelAndView(Constants.JSON_VIEW,params);
	}
	
	/**
	 * FuncName:updateOpenbidGeneralview
	 * Description :修改开标一览表对象  
	 * @param   openbidGeneralview:开标一览表,status
	 * @return  ModelAndView
	 * @author lic
	 * @Create Date: 2010-9-27下午04:17:56 
	 * @Modifier lic
	 * @Modified Date: 2010-9-27下午04:17:56 
	 */
	@RequestMapping(params = "method=updateOpenbidGeneralview")
	public ModelAndView updateOpenbidGeneralview(OpenbidGeneralview openbidGeneralview,SessionStatus status)throws Exception {
		logger.debug("\nes OpenbidGeneralviewController||updateOpenbidGeneralview\n");
		openbidGeneralviewService.updateOpenbidGeneralviewObject(openbidGeneralview);// 1.修改开标一览表
		Boolean isEndProjectPlan = openbidGeneralviewService.validateIsEndProjectPlan(openbidGeneralview.getSubProj().getObjId());	// 2.验证是否完成项目计划
		status.setComplete();
		Map params = new HashMap();
		params.put("openbidGeneralview", openbidGeneralview);
		params.put("isEndProjectPlan", isEndProjectPlan);
		return new ModelAndView(Constants.JSON_VIEW,params);
	}
		
	/**
	 * FuncName:toOpenbidGeneralview
	 * Description :跳转到开标一览表页面
	 * @param projectId
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-12-13下午01:52:22 
	 * @Modifier liuke
	 * @Modified Date: 2010-12-13下午01:52:22		 			 		 
	 */
	@RequestMapping(params = "method=toOpenbidGeneralview")
	public ModelAndView toOpenbidGeneralview(String projectId)throws Exception {
		logger.debug("\nes OpenbidGeneralviewController||toOpenbidGeneralview\n");
		if(null == projectId || "".equals(projectId) || "NULL".equals(projectId.toUpperCase())){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_OPENBID_LIST_IS_ERROR));
		}
		String returnUrl ="openbidGeneralview";
		List<GenviewDefine> genviewDefineList =  genviewDefineService.getGenviewDefineListByProjectId(projectId);  
		List<Project> packList = projectService.getSubProjectByProjectId(projectId);   //获得该项目所有包组
		List<SignUprecord>  signList = signUprecordService.getSignUprecordByprojectId(projectId);
		Project project = projectService.getProjectByObjId(projectId);                 //根据项目主键获得项目
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);  //根据项目主键获得项目规则对象
		if(packList.isEmpty()){  //如果项目没分包
			packList.add(project);     
		}
		Map params = new HashMap();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
		params.put("thisTime", dateFormat.format(new Date()));
		params.put("projectId", projectId);
		params.put("genviewDefineList", genviewDefineList);
		params.put("packList", packList);
		params.put("signList", signList);
		params.put("rule", projectRule);
		List<Bid> bidList = bidService.getBidListByProject(projectId);
		if(!bidList.isEmpty()){
			returnUrl = "updateOpenbidGeneralview";
			List<OpenbidGeneralview> openbidGeneralviewList = openbidGeneralviewService.getOpenbidGeneralviewListByProject(projectId);//获得该项目的开标一览表列表
			params.put("openbidGeneralviewList", openbidGeneralviewList);
		}
		return new ModelAndView(returnUrl,params);  
	}
	
	/**
	 * FuncName:saveOpenbidGeneralviewMessage
	 * Description :保存开标一览表对象
	 * @param 	request
	 * @return  ModelAndView
	 * @author    liuke
	 * @Create Date: 2010-12-14上午11:22:55 
	 * @Modifier  liuke
	 * @Modified Date: 2010-12-14上午11:22:55  
	 */
	@RequestMapping(params = "method=saveOpenbidGeneralviewMessage")
	public ModelAndView saveOpenbidGeneralviewMessage(HttpServletRequest request)throws Exception {
		logger.debug("\nes OpenbidGeneralviewController||saveOpenbidGeneralviewMessage\n");
        String projectId = request.getParameter("projectId");
		String num = request.getParameter("num");
        String itemNum = request.getParameter("n");
        Integer numInt = 0;
        Integer itemNumInt = 0;
        if(num!=null&&!"".equals(num)){
        	numInt = Integer.valueOf(num);
        }
        if(itemNum!=null&&!"".equals(itemNum)){
        	itemNumInt = Integer.valueOf(itemNum);
        }
        List <OpenbidGeneralview> openbidGeneralviewlist = new ArrayList<OpenbidGeneralview>();     //组装开标一览表对象列表   
        for(int i=0;i<numInt;i++){
        	String supplierId = request.getParameter(i+"_supplierId");
        	if(supplierId!=null&&!"".equals(supplierId)){
	        	OpenbidGeneralview generalview = new OpenbidGeneralview();
	        	generalview.setProjectId(projectId);
	        	generalview.setSubProjectId(request.getParameter(i+"_subProjectId"));
	        	generalview.setSupplierName(request.getParameter(i+"_supplierName"));
	        	generalview.setSupplierId(request.getParameter(i+"_supplierId"));
	        	generalview.setBidQuotesum(Float.valueOf(request.getParameter(i+"_quotesum")));
	        	Boolean isUploadFile = new Boolean(request.getParameter(i+"_isUploadFile"));// 附件上传控制
	        	if(isUploadFile){
					Object o=AttachmentUtil.uploadFile(request,(i+"_attrFile"));
					if(o instanceof GpcBaseObject){
						Attachment attachment = (Attachment)o;
						attachmentService.saveAttachment((Attachment)o);
						generalview.setAttachment(attachment);
					}
				}else{
					String attId = request.getParameter(i+"_attrId");
					if(null != attId && !"".equals(attId)){
						Attachment attachment = new Attachment();
						attachment.setObjId(attId);
						generalview.setAttachment(attachment);
					}else{
						generalview.setAttachment(null);
					}
				}
        	List<OpenBidGeneralVitem> openBidGeneralVitemList = new ArrayList<OpenBidGeneralVitem>();   //组装开标一览表明细对象列表
        	for(int j=0;j<itemNumInt;j++){
        		OpenBidGeneralVitem openBidGeneralVitem = new OpenBidGeneralVitem();
        		openBidGeneralVitem.setRespValue(request.getParameter(i+"_openBidGeneralVitem_"+j));
        		String openBidGeneralVitemValue = request.getParameter(i+"_openBidGeneralVitemValue_"+j);
        		if(openBidGeneralVitemValue!=null){
        			String[] v = openBidGeneralVitemValue.replace("|", ",").split(",");
            		openBidGeneralVitem.setGenviewDefineId(v[0]);
            		openBidGeneralVitem.setFactorId(v[1]);
            		openBidGeneralVitem.setFactorName(v[2]);
        		}
        		openBidGeneralVitemList.add(openBidGeneralVitem);
        	}
        	generalview.setOpenBidGeneralVitemlist(openBidGeneralVitemList);
        	openbidGeneralviewlist.add(generalview);
        	}
        }
        Map<String, Float> temp = new HashMap<String, Float>();
        for(OpenbidGeneralview openbidGeneralview :openbidGeneralviewlist){//汇总报价总金额
        	 if(temp.get(openbidGeneralview.getSupplierId())==null){ //如果是第一次
        		 temp.put(openbidGeneralview.getSupplierId(), openbidGeneralview.getBidQuotesum());
        	 }else{
        		 temp.put(openbidGeneralview.getSupplierId(), temp.get(openbidGeneralview.getSupplierId())+openbidGeneralview.getBidQuotesum());
        	 }
        }    
        for(OpenbidGeneralview openbidGeneralview :openbidGeneralviewlist){//汇总报价总金额
        	openbidGeneralview.setBidQuotesumTotal(temp.get(openbidGeneralview.getSupplierId()));
        }    
        openbidGeneralviewService.saveOpenbidGeneralviewMessage(openbidGeneralviewlist,projectId);
		return new ModelAndView(Constants.JSON_VIEW);  
	}
	
	/**
	 * FuncName:updateOpenbidGeneralviewMessage
	 * Description :修改开标一览表对象
	 * @param request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-12-14上午11:22:55 
	 * @Modifier  liuke
	 * @Modified Date: 2010-12-14上午11:22:55  
	 */
	@RequestMapping(params = "method=updateOpenbidGeneralviewMessage")
	public ModelAndView updateOpenbidGeneralviewMessage(HttpServletRequest request)throws Exception {
		logger.debug("\nes OpenbidGeneralviewController||updateOpenbidGeneralviewMessage\n");
        String projectId = request.getParameter("projectId");
		String num = request.getParameter("num");
        String itemNum = request.getParameter("n");
        Integer numInt = 0;
        Integer itemNumInt = 0;
        if(num!=null&&!"".equals(num)){
        	numInt = Integer.valueOf(num);
        }
        if(itemNum!=null&&!"".equals(itemNum)){
        	itemNumInt = Integer.valueOf(itemNum);
        }
        List <OpenbidGeneralview> openbidGeneralviewlist = new ArrayList<OpenbidGeneralview>();   //组装开标一览表对象列表
        for(int i=0;i<numInt;i++){
        	String supplierId = request.getParameter(i+"_supplierId");
        	if(supplierId!=null&&!"".equals(supplierId)){
	        	OpenbidGeneralview generalview = new OpenbidGeneralview();
	        	generalview.setProjectId(projectId);
	        	generalview.setSubProjectId(request.getParameter(i+"_subProjectId"));
	        	generalview.setSupplierName(request.getParameter(i+"_supplierName"));
	        	generalview.setSupplierId(request.getParameter(i+"_supplierId"));
	        	generalview.setBidQuotesum(Float.valueOf(request.getParameter(i+"_quotesum")));
	        	Boolean isUploadFile = new Boolean(request.getParameter(i+"_isUploadFile"));// 附件上传控制
	        	if(isUploadFile){
					Object o=AttachmentUtil.uploadFile(request,(i+"_attrFile"));
					if(o instanceof GpcBaseObject){
						Attachment attachment = (Attachment)o;
						attachmentService.saveAttachment((Attachment)o);
						generalview.setAttachment(attachment);
					}
				}else{
					String attId = request.getParameter(i+"_attrId");
					if(null != attId && !"".equals(attId)){
						Attachment attachment = new Attachment();
						attachment.setObjId(attId);
						generalview.setAttachment(attachment);
					}else{
						generalview.setAttachment(null);
					}
				}
	        	List<OpenBidGeneralVitem> openBidGeneralVitemList = new ArrayList<OpenBidGeneralVitem>(); //组装开标一览表明细对象列表
	        	for(int j=0;j<itemNumInt;j++){
	        		OpenBidGeneralVitem openBidGeneralVitem = new OpenBidGeneralVitem();
	        		openBidGeneralVitem.setRespValue(request.getParameter(i+"_openBidGeneralVitem_"+j));
	        		String openBidGeneralVitemValue = request.getParameter(i+"_openBidGeneralVitemValue_"+j);
	        		if(openBidGeneralVitemValue!=null){
	        			String[] v = openBidGeneralVitemValue.replace("|", ",").split(",");
	            		openBidGeneralVitem.setGenviewDefineId(v[0]);
	            		openBidGeneralVitem.setFactorId(v[1]);
	            		openBidGeneralVitem.setFactorName(v[2]);
	        		}
	        		openBidGeneralVitemList.add(openBidGeneralVitem);
	        	}
	        	generalview.setOpenBidGeneralVitemlist(openBidGeneralVitemList);
	        	openbidGeneralviewlist.add(generalview);
	        	}
        }
        Map<String, Float> temp = new HashMap<String, Float>();
        for(OpenbidGeneralview openbidGeneralview :openbidGeneralviewlist){//汇总报价总金额
        	 if(temp.get(openbidGeneralview.getSupplierId())==null){ //如果是第一次
        		 temp.put(openbidGeneralview.getSupplierId(), openbidGeneralview.getBidQuotesum());
        	 }else{
        		 temp.put(openbidGeneralview.getSupplierId(), temp.get(openbidGeneralview.getSupplierId())+openbidGeneralview.getBidQuotesum());
        	 }
        }    
        
        for(OpenbidGeneralview openbidGeneralview :openbidGeneralviewlist){//汇总报价总金额
        	openbidGeneralview.setBidQuotesumTotal(temp.get(openbidGeneralview.getSupplierId()));
        }    
        openbidGeneralviewService.updateOpenbidGeneralviewMessage(openbidGeneralviewlist,projectId);
		return new ModelAndView(Constants.JSON_VIEW);  
	}
	
	/** 
	 * FuncName : toViewOpenbidGeneral
	 * Description :  跳转到查看开标一览表页面
	 * Create Date: 2011-11-15下午03:00:34 by yangx  
	 * Modified Date: 2011-11-15下午03:00:34 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toViewOpenbidGeneral")
	public ModelAndView toViewOpenbidGeneral(HttpServletRequest request)throws Exception{
		logger.debug("\nes OpenbidGeneralviewController||toNewOpenbidGeneralListview\n");
		String projectId = request.getParameter("projectId");
		if(null == projectId || "".equals(projectId) || "NULL".equals(projectId.toUpperCase())){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_OPENBID_LIST_IS_ERROR));
		}
	    List<OpenbidGeneralview> openbidGeneralviewList = openbidGeneralviewService.getOpenbidGeneralviewList(projectId);
		List<GenviewDefine> genviewDefineList =  genviewDefineService.getGenviewDefineListByProjectId(projectId);  
		List<BidPackage> bidPackageList = bidPackageService.getAllBidPackageListByProjectId(projectId);
		List<OpenBidRecord> openBidRecordList = new ArrayList<OpenBidRecord>();
			for (Iterator iterator = bidPackageList.iterator(); iterator.hasNext();) {
				BidPackage bidPackage = (BidPackage) iterator.next();
				Bid bid = bidPackage.getBid();
				OpenBidRecord openBidRecord = new OpenBidRecord();  
				openBidRecord.setSellerName(bid.getSupplierName());
				openBidRecord.setSubProjId(bidPackage.getPack().getObjId());
				openBidRecord.setSupplier(bid.getSupplier());
				openBidRecordList.add(openBidRecord);
			}
	    List<OpenBidGeneralVitem> openBidGeneralVitemList = openBidGeneralVitemService.getOpenBidGeneralVitemListByProjectId(projectId);
		Project project = projectService.getProjectByObjId(projectId);
	    List<Project> packList = new ArrayList<Project>();
		Set<Project> packSet = project.getSubProject();
	    if(packSet.isEmpty()){ //如果项目没分包，则把项目放入packList列表中
	    	packList.add(project);
	    }else{
	    	for (Iterator iterator = packSet.iterator(); iterator.hasNext();) { //如果项目分包，把包件放入packList列表中
				Project subProj = (Project) iterator.next();
				packList.add(subProj);
			}
	    }
		for(OpenbidGeneralview openbidGeneralview:openbidGeneralviewList){
			for(OpenBidGeneralVitem openBidGeneralVitem:openBidGeneralVitemList){
				if(openbidGeneralview.getObjId()!=null&&openbidGeneralview.getObjId().equals(openBidGeneralVitem.getOpenbidGeneralview().getObjId())){
					openBidGeneralVitem.setSupplierId(openbidGeneralview.getSupplierId());
				}
			}
	    }
		Map params = new HashMap();
		if(openBidGeneralVitemList.size()>0){
			params.put("vitemSize", "isOK");
		}else{
			params.put("vitemSize", "isNO");
		}
		ProjectRule rule = projectService.getProjectRuleByProjectId(projectId);
	    Date date = new Date();
	    if(date.after(rule.getOpenBidSDate())){
	    	params.put("isOpenBid", "YES");
	    }
		params.put("projectId", projectId);
		params.put("genviewDefineList", genviewDefineList);
		params.put("openBidGeneralVitemList", openBidGeneralVitemList);
		params.put("bidPackageList", bidPackageList);
		params.put("openBidRecordList", openBidRecordList);
		params.put("packList", packList);
		return new ModelAndView("toViewOpenbidGeneral",params);
	}
	
	/**
	 * FuncName: toPutInOpenbidGeneralview
	 * Description : 跳转到录入开标一览表页面
	 * @param 
	 * @param request
	 * @return ModelAndView
	 * @author: liuke
	 * @Create Date:2011-9-26  上午10:32:03
	 * @Modifier: liuke
	 * @Modified Date:2011-9-26  上午10:32:03
	 */
	@RequestMapping(params = "method=toPutInOpenbidGeneralview")
	public ModelAndView toPutInOpenbidGeneralview(HttpServletRequest request)throws Exception{
		logger.debug("\nes OpenbidGeneralviewController||toNewOpenbidGeneralListview\n");
		String projectId = request.getParameter("projectId");
		if(null == projectId || "".equals(projectId) || "NULL".equals(projectId.toUpperCase())){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_OPENBID_LIST_IS_ERROR));
		}
	    List<OpenbidGeneralview> openbidGeneralviewList = openbidGeneralviewService.getOpenbidGeneralviewList(projectId); //查询开标一览表数据
		List<GenviewDefine> genviewDefineList =  genviewDefineService.getGenviewDefineListByProjectId(projectId);  //查询开标一览表头数据
		List<BidPackage> bidPackageList = bidPackageService.getAllBidPackageListByProjectId(projectId);     //查询开标包组中间表数据
		List<OpenBidRecord> openBidRecordList = new ArrayList<OpenBidRecord>();
			for (Iterator iterator = bidPackageList.iterator(); iterator.hasNext();) {
				BidPackage bidPackage = (BidPackage) iterator.next();
				Bid bid = bidPackage.getBid();
				OpenBidRecord openBidRecord = new OpenBidRecord();  
				openBidRecord.setSellerName(bid.getSupplierName());
				openBidRecord.setSubProjId(bidPackage.getPack().getObjId());
				openBidRecord.setSupplier(bid.getSupplier());
				openBidRecordList.add(openBidRecord);
			}
	    List<OpenBidGeneralVitem> openBidGeneralVitemList = openBidGeneralVitemService.getOpenBidGeneralVitemListByProjectId(projectId); //查询开标一览表明细数据
	    
		Project project = projectService.getProjectByObjId(projectId);
	    List<Project> packList = new ArrayList<Project>();
		Set<Project> packSet = project.getSubProject();
	    if(packSet.isEmpty()){ //如果项目没分包，则把项目放入packList列表中
	    	packList.add(project);
	    }else{
	    	for (Iterator iterator = packSet.iterator(); iterator.hasNext();) { //如果项目分包，把包件放入packList列表中
				Project subProj = (Project) iterator.next();
				packList.add(subProj);
			}
	    }
	    
		for(OpenbidGeneralview openbidGeneralview:openbidGeneralviewList){
			for(OpenBidGeneralVitem openBidGeneralVitem:openBidGeneralVitemList){
				if(openbidGeneralview.getObjId()!=null&&openbidGeneralview.getObjId().equals(openBidGeneralVitem.getOpenbidGeneralview().getObjId())){
					openBidGeneralVitem.setSupplierId(openbidGeneralview.getSupplierId());
				}
			}
	    }
		Map params = new HashMap();
		if(openBidGeneralVitemList.size()>0){
			params.put("vitemSize", "isOK");
		}else{
			params.put("vitemSize", "isNO");
		}
		
		ProjectRule rule = projectService.getProjectRuleByProjectId(projectId);
	    Date date = new Date();
	    if(date.after(rule.getOpenBidSDate())){
	    	params.put("isOpenBid", "YES");
	    }
		params.put("projectId", projectId);
		params.put("genviewDefineList", genviewDefineList);
		params.put("openBidGeneralVitemList", openBidGeneralVitemList);
		params.put("bidPackageList", bidPackageList);
		params.put("openBidRecordList", openBidRecordList);
		params.put("packList", packList);
		return new ModelAndView("putInOpenbidGeneralviewList",params);  
	}
	
	
	
	
	
}
