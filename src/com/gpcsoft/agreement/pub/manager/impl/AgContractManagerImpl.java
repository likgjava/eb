package com.gpcsoft.agreement.pub.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderDao;
import com.gpcsoft.agreement.order.dao.OrderProtaskDao;
import com.gpcsoft.agreement.pub.dao.AgContractDao;
import com.gpcsoft.agreement.pub.domain.AgContract;
import com.gpcsoft.agreement.pub.manager.AgContractManager;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.pubservice.utils.MobileMessageUtil;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Repository
public class AgContractManagerImpl extends BaseManagerImpl<AgContract> implements AgContractManager {

	@Autowired(required=true)  @Qualifier("agContractDaoHibernate")
	private AgContractDao agContractDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("orderProtaskDaoHibernate")
	private OrderProtaskDao orderProtaskDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("orderDaoHibernate")
	private OrderDao orderDaoHibernate;
	
	@Autowired(required=false) @Qualifier("userManagerImpl")
	private UserManager userManagerImpl;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true)  @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	
	//保存合同
	public AgContract saveContract(AgContract baseobject)throws Exception {
		String sure = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.pub.domain.Contract.confirmStatus", "SURE");
		String type = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.pub.domain.Contract.type", "AGREEMENT");
		
		//供应商机构管理员
		OrgInfo supplierOrgInfo = orgInfoDaoHibernate.get(baseobject.getSupplier().getObjId());
		User supplierUser = userManagerImpl.getManagerUser(supplierOrgInfo.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		//采购人机构管理员
		OrgInfo buyerOrgInfo = orgInfoDaoHibernate.get(baseobject.getBuyer().getObjId());
		User buyerUser = userManagerImpl.getManagerUser(buyerOrgInfo.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		
		//采购人确认
		if(sure.equals(baseobject.getBuyerConfirmStatus())){
			baseobject.setBuyerConfirmDate(new Date());
			baseobject.setBuyerConfirmUser(AuthenticationHelper.getCurrentUser(true));
			
			//获取采购人提交合同邮件、站内信提醒的标题
			String title = messageSource.getMessage(CustomerMessage.BUYER_SUBMINT_AGCONTRACT_REMIND_TITLE);
			
			//获取采购人提交合同邮件、站内信、短信提醒的内容
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("buyerOrgName", buyerOrgInfo.getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			String content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BUYER_SUBMINT_AGCONTRACT_REMIND_CONTENT), templateMap);
			
			//发送邮件，站内信，短信
			sendMessage(buyerUser, supplierUser, title, content);
		}
		//供应商确认
		if(sure.equals(baseobject.getSupplierConfirmStatus())){
			baseobject.setSupplierConfirmDate(new Date());
			baseobject.setSupplierConfirmUser(AuthenticationHelper.getCurrentUser(true));
			
			//获取供应商提交合同时邮件、站内信提醒的标题
			String title = messageSource.getMessage(CustomerMessage.SUPPLIER_SUBMINT_AGCONTRACT_REMIND_TITLE);
			
			//获取供应商提交合同时邮件、站内信、短信提醒的内容
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("supplierOrgName", supplierOrgInfo.getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			String content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.SUPPLIER_SUBMINT_AGCONTRACT_REMIND_CONTENT), templateMap);
			
			//发送邮件，站内信，短信
			sendMessage(supplierUser, buyerUser , title, content);
		}

		//如果有Id，则将数据库中的对象取出来
		if(baseobject.getObjId() != null && !baseobject.getObjId().equals("")){
			AgContract contract = (AgContract)this.get(baseobject.getObjId(),AgContract.class);
			try{
				BeanUtils.copyPropertiesFilterEmptyNew(contract,baseobject);
				return contract;
			}catch(Exception ce){
				ce.printStackTrace();return null;
			}
		}else{
			baseobject.setContractNo(SequenceNumberUtil.getContractSN());
			baseobject.setContractType(type);  //协议供货合同
			baseobject.setCreateTime(new Date());
			baseobject.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			agContractDaoHibernate.save(baseobject);
			return baseobject;
		}
	}

	//确认合同
	public AgContract auditContract(AgContract baseobject, String sureRole) throws Exception {
		String sure = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.pub.domain.Contract.confirmStatus", "SURE");
		
		AgContract contract = (AgContract)this.get(baseobject.getObjId(),AgContract.class);
		
		//供应商机构管理员
		User supplierUser = userManagerImpl.getManagerUser(contract.getSupplier().getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		//采购人机构管理员
		User buyerUser = userManagerImpl.getManagerUser(contract.getBuyer().getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		
		BeanUtils.copyPropertiesFilterEmptyNew(contract,baseobject);
		
		//采购人操作
		if("buyer".equals(sureRole)){
			contract.setBuyerConfirmDate(new Date());
			contract.setBuyerConfirmUser(AuthenticationHelper.getCurrentUser(true));
			//如果是签订，则同步任务书条目的剩余数量和金额
			if(sure.equals(baseobject.getBuyerConfirmStatus())){
				orderProtaskDaoHibernate.updateProtaskByContractForSign(contract.getObjId(),"+");
			}	
			
			//获取采购人确认合同时邮件、站内信、短信提醒的标题和内容
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("buyerConfirmStatusCN", contract.getBuyerConfirmStatusCN());
			templateMap.put("buyerOrgName", contract.getBuyer().getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			String title = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BUYER_CONFIRM_AGCONTRACT_REMIND_TITLE), templateMap);
			String content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BUYER_CONFIRM_AGCONTRACT_REMIND_CONTENT), templateMap);
			
			//发送邮件，站内信，短信
			sendMessage(buyerUser, supplierUser , title, content);
		}
		//供应商操作
		else if("supplier".equals(sureRole)){
			contract.setSupplierConfirmDate(new Date());
			contract.setSupplierConfirmUser(AuthenticationHelper.getCurrentUser(true));
			//如果是签订，则同步任务书条目的剩余数量和金额
			if(sure.equals(baseobject.getSupplierConfirmStatus())){
				orderProtaskDaoHibernate.updateProtaskByContractForSign(contract.getObjId(),"+");
			}
			
			//获取供应商确认合同时邮件、站内信、短信提醒的标题和内容
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("supplierConfirmStatusCN", contract.getSupplierConfirmStatusCN());
			templateMap.put("supplierOrgName", contract.getSupplier().getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			String title = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.SUPPLIER_CONFIRM_AGCONTRACT_REMIND_TITLE), templateMap);
			String content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.SUPPLIER_CONFIRM_AGCONTRACT_REMIND_CONTENT), templateMap);
			
			//发送邮件，站内信，短信
			sendMessage(supplierUser,buyerUser  , title, content);
		}
		
		return contract;
	}
	
	//作废合同
	public AgContract releaseContract(AgContract baseobject, String sureRole)
			throws Exception {
		String wait = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.pub.domain.Contract.confirmStatus", "WAIT");
		String invalid = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.pub.domain.Contract.useStatus", "INVALID");
		
		AgContract contract = (AgContract)this.get(baseobject.getObjId(),AgContract.class);
		
		//供应商机构管理员
		User supplierUser = userManagerImpl.getManagerUser(contract.getSupplier().getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		//采购人机构管理员
		User buyerUser = userManagerImpl.getManagerUser(contract.getBuyer().getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		
		BeanUtils.copyPropertiesFilterEmptyNew(contract,baseobject);
		
		//采购人操作
		if("buyer".equals(sureRole)){
			//申请作废或确认作废
			if(wait.equals(baseobject.getSupplierConfirmStatus()) || invalid.equals(baseobject.getUseStatus())){
				contract.setBuyerConfirmDate(new Date());
				contract.setBuyerConfirmUser(AuthenticationHelper.getCurrentUser());
				
				String title = "";
				String content = "";
				
				//如果是确认作废，则回退任务书条目的剩余数量和金额，同步作废订单
				if(invalid.equals(baseobject.getUseStatus())){
					orderProtaskDaoHibernate.updateProtaskByContractForSign(contract.getObjId(),"-");

					//作废订单
					orderDaoHibernate.invalidOrder(baseobject.getObjId());
					//删除订单与任务书的关系
					orderProtaskDaoHibernate.deleteProtaskByContractForInvalid(baseobject.getObjId());
					
					//获取采购人确认作废合同邮件、站内信提醒的标题
					title = messageSource.getMessage(CustomerMessage.BUYER_CONFIRM_INVALID_AGCONTRACT_REMIND_TITLE);
					
					//获取采购人确认作废合同邮件、站内信、短信提醒的内容
					Map<String, Object> templateMap = new HashMap<String, Object>();
					templateMap.put("buyerOrgName", contract.getBuyer().getOrgName());
					templateMap.put("siteName", SysInfo.getInstance().getSitename());
					content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BUYER_CONFIRM_INVALID_AGCONTRACT_REMIND_CONTENT), templateMap);
				}else{
					//获取采购人申请作废合同邮件、站内信提醒的标题
					title = messageSource.getMessage(CustomerMessage.BUYER_APPLY_INVALID_AGCONTRACT_REMIND_TITLE);
					
					//获取采购人申请作废合同邮件、站内信、短信提醒的内容
					Map<String, Object> templateMap = new HashMap<String, Object>();
					templateMap.put("buyerOrgName", contract.getBuyer().getOrgName());
					templateMap.put("siteName", SysInfo.getInstance().getSitename());
					content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BUYER_APPLY_INVALID_AGCONTRACT_REMIND_CONTENT), templateMap);
				}
				
				sendMessage(buyerUser, supplierUser , title, content);
			}	
		}
		//供应商操作
		else if("supplier".equals(sureRole)){
			//申请作废或确认作废
			if(wait.equals(baseobject.getBuyerConfirmStatus()) || invalid.equals(baseobject.getUseStatus())){
				contract.setSupplierConfirmDate(new Date());
				contract.setSupplierConfirmUser(AuthenticationHelper.getCurrentUser(true));
				
				String title = "";
				String content = "";
				
				//如果是确认作废，则回退任务书条目的剩余数量和金额，同步作废订单
				if(invalid.equals(baseobject.getUseStatus())){
					orderProtaskDaoHibernate.updateProtaskByContractForSign(contract.getObjId(),"-");
					//作废订单
					orderDaoHibernate.invalidOrder(baseobject.getObjId());
					//删除订单与任务书的关系
					orderProtaskDaoHibernate.deleteProtaskByContractForInvalid(baseobject.getObjId());
					
					//获取供应商确认作废合同邮件、站内信提醒的标题
					title = messageSource.getMessage(CustomerMessage.SUPPLIER_CONFIRM_INVALID_AGCONTRACT_REMIND_TITLE);
					
					//获取供应商确认作废合同邮件、站内信、短信提醒的内容
					Map<String, Object> templateMap = new HashMap<String, Object>();
					templateMap.put("supplierOrgName", contract.getSupplier().getOrgName());
					templateMap.put("siteName", SysInfo.getInstance().getSitename());
					content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.SUPPLIER_CONFIRM_INVALID_AGCONTRACT_REMIND_CONTENT), templateMap);
				}else{
					//获取供应商申请作废合同邮件、站内信提醒的标题
					title = messageSource.getMessage(CustomerMessage.SUPPLIER_APPLY_INVALID_AGCONTRACT_REMIND_TITLE);
					
					//获取供应商申请作废合同邮件、站内信、短信提醒的内容
					Map<String, Object> templateMap = new HashMap<String, Object>();
					templateMap.put("supplierOrgName", contract.getSupplier().getOrgName());
					templateMap.put("siteName", SysInfo.getInstance().getSitename());
					content = this.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.SUPPLIER_APPLY_INVALID_AGCONTRACT_REMIND_CONTENT), templateMap);
				}
				sendMessage(supplierUser,buyerUser  , title, content);
			}
		}
		return contract;
	}
	
	/** 
	 * Description :  获取模板内容
	 * Create Date: 2011-7-27下午03:32:31 by likg  Modified Date: 2011-7-27下午03:32:31 by likg
	 * @param   ftlFileName:模板名称	templateMap:模板所需数据
	 * @return  
	 * @Exception   
	 */
	private String getFreeMarkerContent(String ftlFileName, Map<String, Object> templateMap) throws Exception {
		String content = "";
		try {
			content = freeMarkerService.getFreeMarkerContent(ftlFileName, templateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/** 
	 * Description :  发送信息
	 * Create Date: 2011-7-22下午04:08:42 by yucy  Modified Date: 2011-7-22下午04:08:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private void sendMessage(User sendUser ,User receiveUser , String title , String content ) throws Exception {
		//发送邮件
		MailUtil.sendEmailNotAlways(new String[]{receiveUser.getEmp().getEmail()}, title , content ,null, null);
		//发站内信
		MessageUtil.sendMessagePrivete(new String[]{receiveUser.getObjId()},title, content,null, null, true);
		//发短信
		MobileMessageUtil.sendMobileMessage(receiveUser.getEmp().getMobile(),sendUser.getObjId(), sendUser.getUsername(), receiveUser.getObjId(), receiveUser.getUsername(), content, null, null);
	}

}
