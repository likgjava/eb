package com.gpcsoft.smallscale.expert.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.smallscale.expert.dao.ExpertInfoDao;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.smallscale.expert.manager.ExpertInfoManager;
import com.gpcsoft.smallscale.expert.service.ExpertInfoService;
import com.gpcsoft.srplatform.auth.dao.EmployeeDao;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class ExpertInfoServiceImpl extends BaseGenericServiceImpl<ExpertInfo> implements ExpertInfoService {

	@Autowired(required=true) @Qualifier("expertInfoManagerImpl")
	private ExpertInfoManager expertInfoManager;
	
	@Autowired(required=true)  @Qualifier("expertInfoDaoHibernate")
	private ExpertInfoDao expertInfoDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("employeeDaoHibernate")
	private EmployeeDao employeeDaoHibernate;
	
	@Autowired(required=true) @Qualifier("userManagerImpl")
	private UserManager userManager;
	
	@Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDaoHibernate;
	
//	@Autowired(required=true)  @Qualifier("organizationDaoHibernate")
//	private OrganizationDao organizationDaoHibernate;

	/** 
	 * Description :  根据主键，获得专家的详细信息【基本信息，教育经历信息，培训经历信息，任职经历信息，职称管理信息】
	 * Create Date: 2010-11-26上午09:20:39 by likg  Modified Date: 2010-11-26上午09:20:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ExpertInfo getExpertAllInfo(String objId) throws Exception {
		return expertInfoDaoHibernate.getExpertAllInfo(objId);
	}
	
	/** 
	 * Description :  根据多个主键（已逗号分隔），获得专家的详细列表信息【基本信息，教育经历信息，培训经历信息，任职经历信息，职称管理信息】
	 * Create Date: 2010-11-26上午09:29:07 by likg  Modified Date: 2010-11-26上午09:29:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ExpertInfo> getExpertAllInfoList(String objIds) throws Exception {
		return expertInfoDaoHibernate.getExpertAllInfoList(objIds);
	}

	/** 
	 * Description :  开启或禁用专家
	 * Create Date: 2010-11-26上午11:05:14 by likg  Modified Date: 2010-11-26上午11:05:14 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateDisableOrEnable(String expertId, String isOff) throws Exception {
		//开启或禁用专家
		ExpertInfo expertInfo = expertInfoManager.get(expertId);
		expertInfo.setIsOff(isOff);
		
		//开启或禁用专家对应的账号
		User user = userManager.get(expertInfo.getUser().getObjId());
		if(ExpertEnum.ENABLE.equals(isOff)){ //启用
			user.setUsrIsLocked(false);
		}else if(ExpertEnum.DISABLE.equals(isOff)){ //禁用
			user.setUsrIsLocked(true);
		}
	}

	/** 
	 * Description :  保存专家信息【基本信息，用户信息，员工信息】
	 * Create Date: 2010-11-26下午01:25:30 by likg  Modified Date: 2010-11-26下午01:25:30 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveExpertInfo(ExpertInfo expertInfo, User user, Employee employee) throws Exception {
		//保存为正式
		if(expertInfo.getUseStatus()!=null && expertInfo.getUseStatus().equals(ExpertEnum.USE_VALID)){
			expertInfo.setAuditStatus(ExpertEnum.PASS_EXAM); //审核通过
		} else { //保存为临时
			expertInfo.setAuditStatus(ExpertEnum.DRAFT_EXAM); //草稿
		}
		expertInfo.setIsOff(ExpertEnum.ENABLE); //启用
		expertInfo.setCreateTime(new Date());
		expertInfo.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		
		expertInfoManager.save(expertInfo); //保存专家信息
		
		WordToSpell spell = new WordToSpell();
		employee.setShortSpellName(spell.String2Alpha(employee.getName())); //姓名简写
    	employeeDaoHibernate.save(employee); //保存个人信息
    	user.setEmp(employee);
    	
    	//分配角色
		Role role = roleDaoHibernate.getRoleByType(OrganizationEnum.ROLE_EXPERT);//获得咨询专家角色
    	Set<Role> roles = new HashSet<Role>();
    	roles.add(role);
    	user.setRoles(roles);
    	
    	//随机生成密码，并通过Email告知用户的密码
		String password = (new Random().nextInt(999999) + 123456) + "";
    	String encryptPassword = MathUtil.encryptPassWordSHA(password); //密码加密
    	user.setPassword(encryptPassword);
		this.sendEmail(user, password, CustomerMessage.EXPERT_REGISTER_COMPLETE,CustomerMessage.EXPERT_REGISTER_TEMPLATE);
		
		expertInfo.setUser(user);
	}

	/** 
	 * Description :  修改专家信息【基本信息，用户信息，员工信息】
	 * Create Date: 2010-11-29上午09:40:31 by likg  Modified Date: 2010-11-29上午09:40:31 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateExpertInfo(ExpertInfo expertInfo, Employee employee, String saveType) throws Exception {
		Employee oldEmployee = employeeDaoHibernate.get(employee.getObjId());
		oldEmployee.setName(employee.getName());
		oldEmployee.setSex(employee.getSex());
		oldEmployee.setMobile(employee.getMobile());
		oldEmployee.setEmail(employee.getEmail());
		oldEmployee.setMsn(employee.getMsn());
		oldEmployee.setQq(employee.getQq());
		oldEmployee.setTelOffice(employee.getTelOffice());
		oldEmployee.setFax(employee.getFax());
		oldEmployee.setAddress(employee.getAddress());
		
		//为employee设置一个默认的机构【特殊处理】
//		List<Organization> orgList = organizationDaoHibernate.getOrganizationBySpeciaFlag("MN");
//		if(orgList !=null  && orgList.size()>0){
//			Company company = (Company)orgList.get(0);			
//			oldEmployee.setCompany(company);
//		}
		
		//控制状态信息
		if("saveOfExpert".equals(saveType)){ //专家保存修改内容
			
		}else if("submitOfExpert".equals(saveType)){ //专家提交修改内容
			expertInfo.setAuditStatus(ExpertEnum.AWAIT_EXAM); //待审核
		}else if("submitOfManager".equals(saveType)){ //保存为正式
			expertInfo.setAuditStatus(ExpertEnum.PASS_EXAM); //审核通过
		}else if("saveOfManager".equals(saveType)){ //保存为临时
			expertInfo.setAuditStatus(ExpertEnum.DRAFT_EXAM); //草稿
		}
		
		User oldUser = userManager.get(expertInfo.getUser().getObjId());
		oldUser.setUsName(expertInfo.getUser().getUsName());
		oldUser.setEmp(oldEmployee);
		
		expertInfo.setUser(oldUser);
		
		expertInfoManager.save(expertInfo);
		employeeDaoHibernate.save(oldEmployee);
	}

	/** 
	 * Description :  审核专家信息
	 * Create Date: 2010-11-29下午02:05:48 by likg  Modified Date: 2010-11-29下午02:05:48 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditExpertInfo(ExpertInfo expertInfo) throws Exception {
		//得到旧的ExpertInfo信息
		ExpertInfo oldExpertInfo = expertInfoManager.get(expertInfo.getObjId());
		
		//审核通过
		if(expertInfo.getAuditStatus().equals(ExpertEnum.PASS_EXAM)){
			oldExpertInfo.setUseStatus(ExpertEnum.USE_VALID);
			oldExpertInfo.setValidTime(new Date());
			
			//分配角色
			Set<Role> roles = oldExpertInfo.getUser().getRoles();
			Role role = roleDaoHibernate.getRoleByType(OrganizationEnum.ROLE_EXPERT);//获得咨询专家角色
	    	roles.add(role);
	    	oldExpertInfo.getUser().setRoles(roles);
		}
		oldExpertInfo.setAuditStatus(expertInfo.getAuditStatus());
	}

	/** 
	 * Description :  保存专家注册信息【专家信息，用户信息，员工信息】
	 * Create Date: 2010-11-29下午05:10:00 by likg  Modified Date: 2010-11-29下午05:10:00 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveExpertInfoOfRegister(ExpertInfo expertInfo, User user, Employee employee) throws Exception {
		//加密之前的密码
		final String password = user.getPassword();		
		
		expertInfoManager.saveExpertInfoOfRegister(user, employee);
		
		expertInfo.setCreateUser(user);
		expertInfo.setUser(user);
		expertInfo.setIsOff(ExpertEnum.ENABLE); //启用
		expertInfo.setUseStatus(ExpertEnum.USE_TEMP); //临时
		expertInfo.setAuditStatus(ExpertEnum.AWAIT_EXAM); //待审核
		expertInfoManager.save(expertInfo);
		
		//通过Email告知用户的密码
		this.sendEmail(user, password, CustomerMessage.EXPERT_REGISTER_COMPLETE,CustomerMessage.EXPERT_REGISTER_TEMPLATE);
	}
	
	/** 
	 * Description :  发送通知邮件
	 * Create Date: 2010-11-30上午09:50:27 by likg  Modified Date: 2010-11-30上午09:50:27 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	private void sendEmail(User user, String password, String subjectMsg, String templateMsg) throws Exception {
        Map model = new HashMap();
        //加密之前的密码要在邮件里告知用户
        user.setUsrPasswordInit(password);//用户密码
        model.put("user", user);
        model.put("appName",SysInfo.getInstance().getProjectname());//系统名称
        model.put("appUrl", SysInfo.getInstance().getSitename());//首页
        //发送邮件
        MailUtil.sendEmailAlways(user.getEmail().split(","), messageSource.getMessage(subjectMsg),null, messageSource.getMessage(templateMsg), model);
	}
	
	/** 
	 * Description : 根据当前用户获得专家信息 
	 * Create Date: 2010-11-30下午02:26:41 by guoyr  Modified Date: 2010-11-30下午02:26:41 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ExpertInfo getUserExpertInfo(String userId)throws Exception{
		return expertInfoDaoHibernate.getUserExpertInfo(userId);
	}
	
	/** 
	 * Description :  根据参数获得专家的展示信息列表，及时加载User和employee信息
	 * Create Date: 2010-12-1下午12:09:08 by liangxj  Modified Date: 2010-12-1下午12:09:08 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含行业信息、区域信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<ExpertInfo> getExpertListForShow(Page<ExpertInfo> page,
			Map<String, Object> paramsMap) throws Exception {
		return expertInfoDaoHibernate.getExpertListForShow(page, paramsMap);
	}
	
	/** 
	 * Description :  根据品目获得下级品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getCategoryListShowByCategory(String categoryId,String categoryCode,Boolean isLeaf) throws Exception{
		return expertInfoDaoHibernate.getCategoryListShowByCategory(categoryId, categoryCode, isLeaf);
	}
	
	/** 
	 * Description :  根据品目获得包含专家数量的区域信息
	 * Create Date: 2010-12-1下午05:06:35 by liangxj  Modified Date: 2010-12-1下午05:06:35 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(String districtId,String categoryId,Short districtLevel) throws Exception {
		return expertInfoDaoHibernate.getDistrictListForShow(districtId, categoryId, districtLevel);
	}

	/** 
	 * Description :  保存专家申请信息
	 * Create Date: 2010-12-21下午05:06:50 by likg  Modified Date: 2010-12-21下午05:06:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveExpertInfoOfApply(ExpertInfo expertInfo, User user) throws Exception {
		expertInfo.setName(user.getEmp().getName());
		expertInfo.setUser(user);
		expertInfo.setIsOff(ExpertEnum.ENABLE);
		expertInfo.setCreateTime(new Date());
		expertInfo.setUseStatus(ExpertEnum.USE_TEMP);
		expertInfo.setAuditStatus(ExpertEnum.AWAIT_EXAM);
		
		expertInfoDaoHibernate.save(expertInfo);
	}
	
}
