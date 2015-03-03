package com.gpcsoft.bizplatform.buyer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.buyer.dao.BuyerDao;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.manager.BuyerManager;
import com.gpcsoft.bizplatform.buyer.service.BuyerService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.srplatform.auth.dao.OrganizationDao;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class BuyerServiceImpl extends BaseGenericServiceImpl<Buyer> implements BuyerService {

	@Autowired(required=true) @Qualifier("buyerManagerImpl")
	private BuyerManager buyerManager;
	@Autowired(required=true) @Qualifier("buyerDaoHibernate")
	private BuyerDao buyerDaoHibernate;
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	@Autowired(required=true)  @Qualifier("organizationDaoHibernate")
    private OrganizationDao organizationDaoHibernate;
	@Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDaoHibernate;
    
	/** 
	 * Description :  根据orgInfoId获取Buyer
	 * Create Date: 2011-10-24下午06:23:23 by sunl  Modified Date: 2011-10-24下午06:23:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer getBuyerByOrgInfoId(String orgInfoId)throws Exception {
		return buyerDaoHibernate.getBuyerByOrgInfoId(orgInfoId);
	}
	
	/**
	 * Description : 采购人注册，同步保存公司信息、机构信息、供应商信息、员工信息、账号信息以及用户的默认角色
     * Create Date: 2010-7-9下午13:33:22 by sunl  Modified Date: 2010-7-9下午13:33:23 by sunl
	 * @return
	 * @throws Exception
	 */
	public Buyer saveBuyerOfRegister(Buyer buyer,Company company,Employee employee,OrgInfo orgInfo,User user,String promoterUID) throws Exception{
		
		//加密之前的密码要在邮件里告知用户
		final String password = user.getPassword();
		
		//设置上级机构
		if(buyer.getParentUnit() != null) {
			OrgInfo parentOrgaInfo = orgInfoManager.get(buyer.getParentUnit().getObjId());
			Company parent = new Company();
			parent.setObjId(parentOrgaInfo.getCompany().getObjId());
			company.setParent(parent);
		}
		
		//将默认采购人角色添加到角色里
		Set<Role> roles = new HashSet<Role>();
		Role defaultBuyer = roleDaoHibernate.getRoleByType(OrganizationEnum.DEFALT_BUYER);//获取默认采购人角色
		roles.add(defaultBuyer);
		user.setRoles(roles);
		
		//调用orgInfo的机构保存方法
		OrgInfo org = orgInfoManager.saveSimilar(company, employee, orgInfo, user);
		
		/** 以下是保存采购人详细信息*/
		if(!StringUtils.hasLength(orgInfo.getAuditStatus())) {
    		buyer.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
    	}else {
    		buyer.setAuditStatus(orgInfo.getAuditStatus());
    	}
		buyer.setOrgInfo(org);
		buyer.setCreateUser(user);
		buyer.setCreateTime(new Date());
		buyer = this.save(buyer);
		
		org.setBuyerId(buyer.getObjId());  //更新机构中的采购人id
		//发送通知邮件(注册完毕模板邮件)
		sendEmail(org,password,CustomerMessage.ORGINFO_REGISTER_COMPLETE,CustomerMessage.ORGINFO_REGISTER_TEMPLATE);
		
		return buyer;
	}
	
	/** 
	 * Description :  取消机构角色申请
	 * 				  删除buyer，并将orginfo的buyer信息置空
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateCancelApply(String buyerId) throws Exception {
		 Buyer buyer = buyerManager.get(buyerId);
		 OrgInfo orgInfo = orgInfoManager.get(buyer.getOrgInfo().getObjId());
		 orgInfo.setBuyerId(null);
		 buyerManager.remove(buyerId, Buyer.class);
	}
	
	/** 
	 * Description :  保存采购人角色申请
	 * 				  保存采购人,机构里的buyerId更新为新保存的buyerId
	 * Create Date: 2010-7-26下午07:34:02 by sunl  Modified Date: 2010-7-26下午07:34:02 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer saveApplyBuyer(Buyer buyer,String orgInfoId) throws Exception {
		OrgInfo orgInfo = orgInfoManager.get(orgInfoId);
		buyer.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		buyer.setOrgInfo(orgInfo);
		buyer = buyerManager.save(buyer);
		
		orgInfo.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		orgInfo.setAuditType(OrganizationEnum.APPLY_AUDIT);//申请审核
		orgInfo.setBuyerId(buyer.getObjId());//更新orgInfo里的buyerId
		return buyer;
	}

	/** 
	 * Description :  发送通知邮件
	 * Create Date: 2010-7-29下午02:52:21 by sunl  Modified Date: 2010-7-29下午02:52:21 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	private void sendEmail(OrgInfo orgInfo,String password, String subjectMsg, String templateMsg) throws Exception {
		//此处不能从内存中取得用户邮箱地址,因为可能用户已经修改了邮箱地址
		User user = (User)get(orgInfo.getUser().getObjId(), User.class);
		
        Map model = new HashMap();
        //加密之前的密码要在邮件里告知用户
        user.setUsrPasswordInit(password);//用户密码
        model.put("user", user);
        model.put("orgName", orgInfo.getOrgName());//机构名称
        model.put("appName",SysInfo.getInstance().getProjectname());//系统名称
        model.put("appUrl", SysInfo.getInstance().getSitename());//首页
        model.put("loginUrl", SysInfo.getInstance().getServername() + "/view/srplatform/login/login.jsp");//登录页面
        
        //发送邮件
        MailUtil.sendEmailNotAlways(new String[]{user.getEmail()}, messageSource.getMessage(subjectMsg),null, messageSource.getMessage(templateMsg), model);
	}
	
	/** 
	 * Description :   根据参数获得采购人机构的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-10-19上午11:46:16 by liangxj  Modified Date: 2010-10-19上午11:46:16 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含所属区域、企业类型、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Buyer> getBuyerListForShow(Page<Buyer> page,Map<String, Object> paramsMap) throws Exception {
		return buyerDaoHibernate.getBuyerListForShow(page, paramsMap);
	}
	
	/** 
	 * Description :  根据主键，获得采购人的详细信息，包括基本信息、扩展信息、评价等
	 * Create Date: 2010-10-19下午05:17:50 by liangxj  Modified Date: 2010-10-19下午05:17:50 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer getBuyerAllInfo(String objId) throws Exception {
		return buyerDaoHibernate.getBuyerAllInfo(objId);
	}
	
	/** 
	 * Description :  获得采购人企业类型的展示数据 
	 * Create Date: 2010-10-19下午08:17:07 by liangxj  Modified Date: 2010-10-19下午08:17:07 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getUnitTypeListShow(String keyWord) throws Exception {
		return buyerDaoHibernate.getUnitTypeListShow(keyWord);
	}
	
	/** 
	 * Description :  根据企业性质获得包含采购人数量的区域信息
	 * Create Date: 2010-10-19下午08:19:10 by liangxj  Modified Date: 2010-10-19下午08:19:10 by liangxj
	 * @param   districtId 区域id unitType 企业类型 districtLevel 区域级别
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(String districtId,String unitType,Short districtLevel,String keyWord) throws Exception {
		return buyerDaoHibernate.getDistrictListForShow(districtId, unitType, districtLevel,keyWord);
	}

	/** 
	 * Description :  保存采购人对象（只包含机构信息）
	 * Create Date: 2010-11-12下午06:14:42 by likg  Modified Date: 2010-11-12下午06:14:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveSimpleBuyer(Buyer buyer, OrgInfo orgInfo) throws Exception 
	{
		//设置公司信息
		Company company = orgInfo.getCompany();
		company.setName(orgInfo.getOrgName());
		company.setCode(orgInfo.getOrgCode());
    	company.setIsLeaf(true);
    	company.setLevel(new Integer(Organization.ORG_TYPE_COM).shortValue());
    	WordToSpell spell = new WordToSpell();
    	company.setShortSpellName(spell.String2Alpha(orgInfo.getOrgName())); //拼音简写
    	company.setCreateTime(new Date());
    	
		//保存机构信息，同步级联保存公司信息 
    	orgInfo.setIsOff(OrganizationEnum.ENABLE);//启用禁用(默认启用1)
    	orgInfo.setCreateUser(AuthenticationHelper.getCurrentUser(true));
    	orgInfo.setCreateTime(new Date());
    	if(orgInfo.getUseStatus()!=null && orgInfo.getUseStatus().equals(OrganizationEnum.USE_VALID))
    	{
    		orgInfo.setAuditStatus(OrganizationEnum.PASS_EXAM);//通过
    		buyer.setAuditStatus(OrganizationEnum.PASS_EXAM);//通过
    		company.setAuditStatus(Organization.ORG_AUDIT_STATUS_AGREE);  //审核状态为通过
    		
    	}
    	else
    	{
    		orgInfo.setUseStatus(OrganizationEnum.USE_TEMP);//临时状态
    		orgInfo.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
    		company.setAuditStatus(Organization.ORG_AUDIT_STATUS_DRAFT);  //审核状态为临时
    		buyer.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
    	}
    	
    	//设置上级机构
		if(buyer.getParentUnit() != null) {
			OrgInfo parentOrgaInfo = orgInfoManager.get(buyer.getParentUnit().getObjId());
			Company parent = new Company();
			parent.setObjId(parentOrgaInfo.getCompany().getObjId());
			company.setParent(parent);
		}
		
		OrgInfo org = orgInfoManager.save(orgInfo);
		
    	//设置上级单位
    	String parentId = "";
    	if(company.getParent() != null) {
    		parentId = company.getParent().getObjId();
    	}
    	if(StringUtils.hasLength(parentId)) {
	        Organization orgtion = (Organization)this.organizationDaoHibernate.get(parentId);
	        orgInfo.getCompany().setPath(orgtion.getPath() + "#" + orgInfo.getCompany().getObjId());
	        if (orgtion.getIsLeaf().booleanValue()) {
	            orgtion.setIsLeaf(Boolean.valueOf(false));
	        }
    	}
		
		buyer.setOrgInfo(org);
		buyer.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		buyer.setCreateTime(new Date());
		buyer = this.save(buyer);
		
		org.setBuyerId(buyer.getObjId());  //更新机构中的采购人id
	}
}
