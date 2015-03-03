package com.gpcsoft.agreement.bargin.signup.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.bargin.requirement.domain.RequirementReg;
import com.gpcsoft.agreement.bargin.signup.dao.SignUprecordDaoXygh;
import com.gpcsoft.agreement.bargin.signup.service.SignUprecordServiceXygh;
import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.impl.SignUprecordServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.dao.EmployeeDao;
import com.gpcsoft.srplatform.auth.domain.Employee;

@Service 
public class SignUprecordServiceXyghImpl extends SignUprecordServiceImpl implements SignUprecordServiceXygh{

	@Autowired(required=true) @Qualifier("signUprecordDaoXyghHibernate")
	private SignUprecordDaoXygh signUprecordDaoXyghHibernate;

	@Autowired(required=true) @Qualifier("employeeDaoHibernate")
	private EmployeeDao employeeDao;
	
	/** 
	 * Description :  确认报名
	 * Create Date: 2010-10-13下午04:26:28 by yucy  Modified Date: 2010-10-13下午04:26:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateSignUprecordAuditStatus(Map<String, Object> param) throws Exception {
		return signUprecordDaoXyghHibernate.updateSignUprecordAuditStatus(param);
	}

	/** 
	 * Description :  判断该项目是否已经报名
	 * Create Date: 2010-10-13下午10:18:54 by yucy  Modified Date: 2010-10-13下午10:18:54 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean ifHasSignUp(Map<String, Object> param) throws Exception {
		return signUprecordDaoXyghHibernate.ifHasSignUp(param);
	}

	/** 
	 * Description : 撤销报名 
	 * Create Date: 2010-10-14上午01:07:26 by yucy  Modified Date: 2010-10-14上午01:07:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateSignUprecordAppllyStatus(Map<String, Object> param) throws Exception {
		return signUprecordDaoXyghHibernate.updateSignUprecordAppllyStatus(param);
	}

	/** 
	 * Description :  取得评价的对象
	 * Create Date: 2010-10-28下午04:45:55 by yucy  Modified Date: 2010-10-28下午04:45:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEvaluateObjectSupplier(String projectId) throws Exception {
		
		return signUprecordDaoXyghHibernate.getEvaluateObjectSupplier(projectId);
	}
	
	
	public Map<String, Object> getComplainObjectSupplier(String projectId) throws Exception {
		
		return signUprecordDaoXyghHibernate.getComplainObjectSupplier(projectId);
	}

	/** 
	 * Description :  校验身份证 唯一性
	 * Create Date: 2010-11-8下午03:08:25 by yucy  Modified Date: 2010-11-8下午03:08:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean checkedIdCard(Map<String, Object> param) throws Exception {
		return signUprecordDaoXyghHibernate.checkedIdCard(param);
	}

	/** 
	 * Description :  判断是否有这个联系人
	 * Create Date: 2010-11-16上午11:49:58 by yucy  Modified Date: 2010-11-16上午11:49:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getHasThisLinker(Map<String, Object> param) throws Exception {
		return signUprecordDaoXyghHibernate.getHasThisLinker(param);
	}

	/** 
	 * Description :  保存报名
	 * Create Date: 2010-11-16上午11:49:58 by yucy  Modified Date: 2010-11-16上午11:49:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public SignUprecord saveSignUprecordXygh(SignUprecord signUprecord,Map<String, Object> params) throws Exception{
		//查询是否存在此联系人
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("cardId", signUprecord.getIdCard());
		param.put("companyId", AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		
		if(StringUtils.hasLength((String)params.get("type")) && "create".equals((String)params.get("type"))) {
			String empId = signUprecordDaoXyghHibernate.getHasThisLinker(param);
			if(!StringUtils.hasLength(empId)) {
				Employee employee = new Employee();
				employee.setAddress(signUprecord.getAddress());
				employee.setCompany(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany());
				employee.setCreatedDate(new Date());
				employee.setCreateTime(new Date());
				employee.setDepartment(AuthenticationHelper.getCurrentUser(true).getEmp().getDepartment());
				employee.setIdCard(signUprecord.getIdCard());
				employee.setMobile(signUprecord.getLinkerTel());
				employee.setName(signUprecord.getLinker());
				employee.setZipCode(signUprecord.getZipCode());
				employee.setShortSpellName(new WordToSpell().String2Alpha(signUprecord.getLinker()));
				employeeDao.save(employee);
			}else {
				Employee employee = employeeDao.get(empId);
				employee.setName(signUprecord.getLinker());
				employee.setMobile(signUprecord.getLinkerTel());
				employee.setIdCard(signUprecord.getIdCard());
				employee.setAddress(signUprecord.getAddress());
				employee.setZipCode(signUprecord.getZipCode());
				employeeDao.save(employee);
			}
				
		}else {
			Employee employee = employeeDao.get((String)params.get("empId"));
			employee.setName(signUprecord.getLinker());
			employee.setMobile(signUprecord.getLinkerTel());
			employee.setIdCard(signUprecord.getIdCard());
			employee.setAddress(signUprecord.getAddress());
			employee.setZipCode(signUprecord.getZipCode());
			employeeDao.save(employee);
		}
		return signUprecordDaoXyghHibernate.save(signUprecord);
	}

	/** 
	 * Description :  保存报名(根据需求报名)
	 * Create Date: 2011-4-8上午09:50:40 by yucy  Modified Date: 2011-4-8上午09:50:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveSignUprecordByRequiremntReg(Project project,List<RequirementReg> requirementRegList) throws Exception {
		
		String regOrgId = "";
		for(RequirementReg requirementReg :requirementRegList){
			if(regOrgId.indexOf(requirementReg.getRegOrg().getObjId())<0){
				SignUprecord signUprecord = new SignUprecord();
				signUprecord.setProject(project);
				signUprecord.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
				signUprecord.setApplyDate(new Date());
				signUprecord.setCreateTime(new Date());
				signUprecord.setLinker(requirementReg.getLinkMen());
				signUprecord.setLinkerTel(requirementReg.getLinkTel());
				signUprecord.setIdCard(requirementReg.getIdCard());
				signUprecord.setAddress(requirementReg.getAddress());
				signUprecord.setSupplier(requirementReg.getRegOrg());
				signUprecord.setSupplierName(requirementReg.getRegOrg().getOrgName());
				
				signUprecordDaoXyghHibernate.save(signUprecord);
	
				regOrgId += requirementReg.getRegOrg().getObjId();//避免重复报名
			}
		}
		
		return true;
	}

	/** 
	 * Description :  批量保存供应商的报名信息
	 * Create Date: 2011-8-3下午02:27:23 by likg  Modified Date: 2011-8-3下午02:27:23 by likg
	 * @param   projectId:项目Id  supplierIds:供应商Id，以逗号分割
	 * @return  
	 * @Exception   
	 */
	public void saveSignUprecordBatch(String projectId, String supplierIds) throws Exception {
		signUprecordDaoXyghHibernate.saveSignUprecordBatch(projectId, supplierIds);
	}
}
