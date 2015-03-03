package com.gpcsoft.bizplatform.agency.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.agency.dao.AgencyDao;
import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.agency.manager.AgencyManager;
import com.gpcsoft.bizplatform.agency.service.AgencyService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class AgencyServiceImpl extends BaseGenericServiceImpl<Agency> implements AgencyService {

	@Autowired(required=true) @Qualifier("agencyManagerImpl")
	private AgencyManager agencyManager;
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	@Autowired(required=true) @Qualifier("agencyDaoHibernate")
	private AgencyDao agencyDaoHibernate;
	
	/** 
	 * Description :  根据orgInfoId获取agent
	 * Create Date: 2011-10-24下午06:23:23 by sunl  Modified Date: 2011-10-24下午06:23:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency getAgentByOrgInfoId(String orgInfoId)throws Exception {
		return agencyDaoHibernate.getAgentByOrgInfoId(orgInfoId);
	}
	
	/** 
	 * Description :  保存代理机构角色申请
	 * 				  保存代理机构,机构里的agencyId更新为新保存的agencyId
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency saveApplyAgency(Agency agency,String orgInfoId) throws Exception {
		OrgInfo orgInfo = orgInfoManager.get(orgInfoId);
		agency.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		agency.setOrgInfo(orgInfo);
		agency = agencyManager.save(agency);
		
		orgInfo.setAuditType(OrganizationEnum.APPLY_AUDIT);//申请审核
		orgInfo.setAgencyId(agency.getObjId());//更新orgInfo里的agencyId
		return agency;
	}
	
	/** 
	 * Description :  取消机构角色申请
	 * 				  删除agency，并将orginfo的agency信息置空
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateCancelApply(String agencyId) throws Exception {
		 Agency agency = agencyManager.get(agencyId);
		 OrgInfo orgInfo = orgInfoManager.get(agency.getOrgInfo().getObjId());
		 orgInfo.setAgencyId(null);
		 agencyDaoHibernate.remove(agency);
	}

	/**
	 * Description : 代理机构注册
	 * 同步保存公司信息、机构信息、供应商信息、员工信息、账号信息以及用户的默认角色
     * Create Date: 2010-7-9下午13:33:22 by sunl  Modified Date: 2010-7-9下午13:33:23 by sunl
	 * @return
	 * @throws Exception
	 */
	public Agency saveAgencyOfRegister(Agency agency, Company company,Employee employee, OrgInfo orgInfo, User user) throws Exception {
    	
		//加密之前的密码要在邮件里告知用户
		final String password = user.getPassword();
		
		//设置主管单位
		if(agency.getUnitInCharge() != null) {
			OrgInfo parentOrgaInfo = orgInfoManager.get(agency.getUnitInCharge().getObjId());
			Company parent = new Company();
			parent.setObjId(parentOrgaInfo.getCompany().getObjId());
			company.setParent(parent);
		}
		
		//调用orgInfo的机构保存方法
		OrgInfo org = orgInfoManager.saveSimilar(company, employee, orgInfo, user);
		
		/** 以下是保存代理机构的详细信息*/
		if(!StringUtils.hasLength(agency.getAuditStatus())) {
			agency.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
		} else {
			agency.setAuditStatus(OrganizationEnum.AWAIT_EXAM);  //待审核
		}
		agency.setOrgInfo(org);
		agency.setCreateUser(user);
		agency.setCreateTime(new Date());
		agency = this.save(agency);
		
		org.setAgencyId(agency.getObjId());  //更新机构中的采购人id
		
		//发送通知邮件(注册完毕模板邮件)
		sendEmail(org,password,CustomerMessage.ORGINFO_REGISTER_COMPLETE,CustomerMessage.ORGINFO_REGISTER_TEMPLATE);
		
		return agency;
	}
	
	/** 
	 * Description :  根据主键，获得代理机构的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Agency> getAgencyAllInfoList(String objIds) throws Exception {
		return agencyDaoHibernate.getAgencyAllInfoList(objIds);
	}
	
	/** 
	 * Description :  根据主键，获得代理机构的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Agency getAgencyAllInfo(String objId) throws Exception {
		return agencyDaoHibernate.getAgencyAllInfo(objId);
	}
	
	/** 
	 * Description :  根据参数获得代理机构的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-8-9下午01:44:06 by liangxj  Modified Date: 2010-8-9下午01:44:06 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含行政级别、代理机构类型、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Agency> getAgencyListForShow(Page<Agency> page,Map<String, Object> paramsMap) throws Exception {
		return agencyDaoHibernate.getAgencyListForShow(page, paramsMap);
	}
	
	/** 
	 * Description :  获得代理机构类型的展示数据
	 * Create Date: 2010-8-12下午02:54:53 by liangxj  Modified Date: 2010-8-12下午02:54:53 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getAgentTypeListShow() throws Exception {
		return agencyDaoHibernate.getAgentTypeListShow();
	}
	
	/** 
	 * Description :  获得代理机构类型的展示数据
	 * Create Date: 2010-8-12下午02:54:53 by liangxj  Modified Date: 2010-8-12下午02:54:53 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getAdminGrdListShow(String agentType) throws Exception {
		return agencyDaoHibernate.getAdminGrdListShow(agentType);
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
        MailUtil.sendEmailAlways(
        		new String[]{user.getEmail()}, 
        		messageSource.getMessage(subjectMsg), 
        		null, 
        		messageSource.getMessage(templateMsg), 
        		model);
	}
}
