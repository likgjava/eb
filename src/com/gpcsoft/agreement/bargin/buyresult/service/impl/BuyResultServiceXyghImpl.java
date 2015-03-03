package com.gpcsoft.agreement.bargin.buyresult.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordDetailService;
import com.gpcsoft.agreement.bargin.bulletin.service.BulletinAgreementService;
import com.gpcsoft.agreement.bargin.buyresult.dao.BuyResultDaoXygh;
import com.gpcsoft.agreement.bargin.buyresult.dao.BuyWinnerDaoXygh;
import com.gpcsoft.agreement.bargin.buyresult.service.BuyResultServiceXygh;
import com.gpcsoft.agreement.bargin.signup.dao.SignUprecordDaoXygh;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.SuccessCaseManager;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.buyresult.domain.BuyResult;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.buyresult.service.impl.BuyResultServiceImpl;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.pubservice.utils.MobileMessageUtil;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

@Service 
public class BuyResultServiceXyghImpl extends BuyResultServiceImpl implements BuyResultServiceXygh{

	@Autowired(required=true) @Qualifier("buyResultDaoXyghHibernate")
	private BuyResultDaoXygh buyResultDaoXygh;
	
	@Autowired(required=true) @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;

	@Autowired(required=true) @Qualifier("buyWinnerDaoXyghHibernate")
	private BuyWinnerDaoXygh buyWinnerDaoXygh;
	
	@Autowired(required=true) @Qualifier("signUprecordDaoXyghHibernate")
	private SignUprecordDaoXygh signUprecordDaoXygh;
	
	@Autowired(required=true) @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDao;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("successCaseManagerImpl")
	private SuccessCaseManager successCaseManager;

	@Autowired(required=true) @Qualifier("userManagerImpl")
	private UserManager userManagerImpl;
	
	@Autowired(required=true) @Qualifier("bulletinAgreementServiceImpl")
	private BulletinAgreementService bulletinAgreementService;
	
	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;
	
	@Autowired(required=true) @Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	
	@Autowired(required=true) @Qualifier("biddingRecordDetailServiceImpl")
	private BiddingRecordDetailService biddingRecordDetailService;
	
	/** 
	 * Description :  供应商轮次报价数据
	 * Create Date: 2010-10-14下午03:23:27 by yucy  Modified Date: 2010-10-14下午03:23:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getSupplierBargainByTurn(Map<String, Object> param)  throws Exception{
		return buyResultDaoXygh.getSupplierBargainByTurn(param);
	}

	/** 
	 * Description :  保存成交结果
	 * Create Date: 2010-10-14下午05:23:32 by yucy  Modified Date: 2010-10-14下午05:23:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveBuyResult(Map<String, Object> param) throws Exception {
		
		Project project =  projectDaoHibernate.get((String)param.get("projectId"));
		
		BuyResult buyResult = new BuyResult();
		buyResult.setProject(project);
		buyResult.setProjCode(project.getProjCode());
		buyResult.setProjName(project.getProjName());
		
		//放弃
		if("giveup".equals((String)param.get("confirmType"))){
			buyResult.setBuyrResult(enumService.getValueByConstant("bizdata.confirmResult", "GIVEUP"));
			buyResult.setBuyrOpinion((String)param.get("opinion"));
		}else if("confirm".equals((String)param.get("confirmType"))){
			buyResult.setBuyrResult(enumService.getValueByConstant("bizdata.confirmResult", "CONFIRM"));
		}
		buyResult.setUseStatus('1');//正式
		
		//保存
		buyResultDaoXygh.save(buyResult);
		
		// 采购人
		OrgInfo buyer = orgInfoDao.get(project.getBuyersId());
		List<OrgInfo> supplierList = new ArrayList<OrgInfo>();
		
		// 成交的供应商
		List<OrgInfo> successSupplierList = new ArrayList<OrgInfo>();
		
		//成交
		if(!"giveup".equals((String)param.get("confirmType"))){
			
			List<BuyWinner> buyWinnerList = new ArrayList<BuyWinner>();
			
			List<BuyWinner> dealYesWinnerList = new ArrayList<BuyWinner>();

			//中标供应商
			String dealSupplier = (String)param.get("dealSupplier");
			if(StringUtils.hasLength(dealSupplier)){
				for(String dealSupplierId : dealSupplier.split(",")){
					BuyWinner buyWinner= new BuyWinner();
					OrgInfo supplier = orgInfoDao.get(dealSupplierId);
					supplierList.add(supplier);
					buyWinner.setSelllerId(supplier.getObjId());
					buyWinner.setSellerName(supplier.getOrgName());
					buyWinner.setResultType(ResultTypeEnum.DEAL_YES);
					sendMassageAndEmail(project,supplier,"NO_PARTICIPATOR");
					
					// 添加成交的供应商
					successSupplierList.add(supplier);
					
					// 为成交的供应商创建成功案例
					successCaseManager.createSupplierSuccessCase(project.getProjName(), 
																project.getCreateTime(), 
																project.getPurCategoryIds(), 
																project.getPurCategoryNames(), 
																project.getEbuyMethodCN(), 
																project.getBudgetTotalMoney(), 
																supplier, buyer);
					dealYesWinnerList.add(buyWinner);
					
					buyWinner.setBuyResult(buyResult);
					buyWinner.setUseStatus('1');//正式
					
					buyWinnerList.add(buyWinner);
					
					//获取供应商机构管理员
					User user = userManagerImpl.getManagerUser(supplier.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());

					//发送短信
					MobileMessageUtil.sendMobileMessage(
							user.getEmp().getMobile(), 
							AuthenticationHelper.getCurrentUser(true).getObjId(), 
							AuthenticationHelper.getCurrentUser(true).getUsername(),
							user.getObjId(), 
							user.getUsername(), 
							messageSource.getMessage(CustomerMessage.INVITATION_BUYRESULT_DEAL_MESSAGE).replace("#projectName", project.getProjName()), 
							null, 
							null);
					
				}
			}
			
			//未中标供应商
			String lostSupplier = (String)param.get("lostSupplier");
			if(StringUtils.hasLength(lostSupplier)){
				for(String lostSupplierId :lostSupplier.split(",")){
					BuyWinner buyWinner= new BuyWinner();
					OrgInfo supplier = orgInfoDao.get(lostSupplierId);
					supplierList.add(supplier);
					buyWinner.setSelllerId(supplier.getObjId());
					buyWinner.setSellerName(supplier.getOrgName());
					buyWinner.setResultType(ResultTypeEnum.DEAL_NO);
					//调接口 发站内信和邮件
					sendMassageAndEmail(project,supplier,"NO_ENOUGH_PARTICIPATOR");
					
					buyWinner.setBuyResult(buyResult);
					buyWinner.setUseStatus('1');//正式
					buyWinnerList.add(buyWinner);
				}
			}
			
			//保存中标条目
			String detailIds = (String)param.get("detailIds");
			if(StringUtils.hasLength(detailIds)){
				biddingRecordDetailService.updateRecordDetailForResult(detailIds);
			}
			
			//发布结果公告
			List<Bulletin> bulletinList = bulletinManager.findByHql("from Bulletin b where b.project.objId=?", new Object[]{project.getObjId()});
			if(bulletinList!=null && bulletinList.size()>0){
				Bulletin bulletin = bulletinList.get(0);
				
				
				Bulletin bulletinNew = new Bulletin();
				
				BeanUtils.copyPropertiesFilterEmptyNew(bulletinNew, bulletin);
				bulletinNew.setObjId(null);
				
				bulletinNew.setBullType(BulletinTypeEnum.BARGIN_RESULT_BULLETIN);//
				
				bulletinNew.setBullTitle(project.getProjName()+"结果公告");//结果公告

				String contentsString = "";
				String ftlName = "bulletin_result.ftl";
				Map<String, Object> templateMap = new HashMap<String, Object>();
				templateMap = bulletinAgreementService.getBulletinTemplateMap(project);
				templateMap.put("buyWinnerList", dealYesWinnerList);
				templateMap.put("buyResult", buyResult);
				contentsString = freeMarkerService.getFreeMarkerContent(ftlName, templateMap);
				String fileName = java.util.UUID.randomUUID().toString().replace('-', '_');
				Properties properties = new Properties();
				InputStream in = BuyResultServiceXyghImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
				properties.load(in);
				String path = properties.getProperty("bulletinUrl") ;
				FileUtil.writerFile(path+fileName,contentsString,"UTF-8");
				Attachment attachment = new Attachment();
				attachment.setFileName(fileName);
//				attachment.setPath(path);
				attachment.setViewName(bulletinNew.getBullTitle());
				attachment.setCreateTime(new Date());
				attachmentManager.save(attachment);
				bulletinNew.setContent(attachment);
				
				bulletinManager.save(bulletinNew);//将结果公告保存
			}
			
			// 为采购人创建成功案例
			successCaseManager.createBuyerSuccessCase(project.getProjName(), 
													project.getCreateTime(), 
													project.getPurCategoryIds(), 
													project.getPurCategoryNames(), 
													project.getEbuyMethodCN(), 
													project.getBudgetTotalMoney(), 
													buyer, successSupplierList);
			
			//保存
			buyWinnerDaoXygh.save(buyWinnerList);
		}
		//放弃
		else{
			List<SignUprecord> signUprecordList = signUprecordDaoXygh.getSignUprecordByprojectId(project.getObjId());
			for(SignUprecord signUprecord: signUprecordList){
				//调接口 发站内信和邮件
				sendMassageAndEmail(project,signUprecord.getSupplier(),"GIVEUP");
			}
		}
		
		//修改项目信息
		project.setProjProcessStatus(ProjProcessStatusEnum.CALIBRATION_BID); //修改项目过程状态
		project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.CALIBRATION_BID)); //修改项目过程中文状态
		project.setTenderRecord("01"); //修改项目的备案状态为：已备案
		projectDaoHibernate.save(project);
		
		return true;
	}
	
	/** 
	 * Description :  发送邮件和站内信
	 * Create Date: 2010-11-19上午10:10:33 by yucy  Modified Date: 2010-11-19上午10:10:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void sendMassageAndEmail(Project project ,OrgInfo supplier,String dealType)throws Exception {
		String email = null;
		
		User managerUser =  userManagerImpl.getManagerUser(supplier.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		
		if(managerUser!=null&&StringUtils.hasLength(managerUser.getEmail())){
			email = managerUser.getEmail();
		}else if(StringUtils.hasLength(supplier.getCompany().getEmail())){
			email = supplier.getCompany().getEmail();
		}
		Map<String, Object> templateMap = new HashMap<String, Object>();
		String dealString = enumService.getDescByConstant("bizdata.confirmResult.message", dealType);
		
		templateMap.put("project",project);
		templateMap.put("dealString",dealString);
		templateMap.put("confirmDate",DateUtil.format(new Date(), "yyyy-MM-dd"));
		
		String  messageContent = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.INVITATION_BUYRESULT_TEMPLATE), templateMap);
		
		//发邮件
        MailUtil.sendEmailNotAlways(
        		new String[]{email}, 
        		project.getProjName()+","+ dealString ,
        		messageContent, 
        		null, 
        		null );

        //发站内信
		MessageUtil.sendMessagePrivete(
				orgInfoDao.getAllManagersByOrgInfoIds(User.USER_TYPE_MANAGER.toString(), supplier.getObjId()),
				project.getProjName()+","+ dealString+"！",
				messageContent,
				null,
				null,
				true);
	}
	
	/** 
	 * Description :  取得成交结果数据
	 * Create Date: 2010-10-15下午02:15:04 by yucy  Modified Date: 2010-10-15下午02:15:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getBuyResultData(Map<String, Object> param)throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("buyResult", buyResultDaoXygh.getBuyResultByProject(param));
		//供应商查看自己的成交数据
		if("supplier".equals((String)param.get("userType"))&&StringUtils.hasLength((String)param.get("projectId"))){
			param.put("supplierId" ,AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
			resultMap.put("buyWinnerList", buyWinnerDaoXygh.getBuyWinnerList(param));
		}
		//采购人查看所有的成交数据
		else{
			resultMap.put("buyWinnerList", buyWinnerDaoXygh.getBuyWinnerByProjectId((String)param.get("projectId")));
		}
		return resultMap;
	}

	/** 
	 * Description :  获得供应商单轮报价的数据
	 * Create Date: 2010-10-18上午10:54:18 by yucy  Modified Date: 2010-10-18上午10:54:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getSingleTurnRecordDate(Map<String, Object> param) throws Exception {
		return buyResultDaoXygh.getSingleTurnRecordDate(param);
	}

	/** 
	 * Description :  取得报价记录明细by报价记录
	 * Create Date: 2010-10-18上午11:27:24 by yucy  Modified Date: 2010-10-18上午11:27:24 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecordItem> getRecordItemsByRecord(Map<String, Object> param) throws Exception {
		return buyResultDaoXygh.getRecordItemsByRecord(param);
	}
}
