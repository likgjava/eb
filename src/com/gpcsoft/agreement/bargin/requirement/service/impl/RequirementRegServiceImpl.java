package com.gpcsoft.agreement.bargin.requirement.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.bargin.requirement.dao.RequirementRegDao;
import com.gpcsoft.agreement.bargin.requirement.domain.RequirementReg;
import com.gpcsoft.agreement.bargin.requirement.manager.RequirementRegManager;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementRegService;
import com.gpcsoft.agreement.bargin.signup.dao.SignUprecordDaoXygh;
import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.dao.EmployeeDao;
import com.gpcsoft.srplatform.auth.domain.Employee;

@Service 
public class RequirementRegServiceImpl extends BaseGenericServiceImpl<RequirementReg> implements RequirementRegService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("requirementRegManagerImpl")
	private RequirementRegManager requirementRegManager;
	
	@Autowired(required=true) @Qualifier("requirementRegDaoHibernate")
	private RequirementRegDao requirementRegDao;
	
	@Autowired(required=true) @Qualifier("signUprecordDaoXyghHibernate")
	private SignUprecordDaoXygh signUprecordDaoXygh;
	
	@Autowired(required=true) @Qualifier("employeeDaoHibernate")
	private EmployeeDao employeeDao;
	
	/** 
	 * Description :  检查是否报名过
	 * Create Date: 2011-4-1下午03:47:00 by yucy  Modified Date: 2011-4-1下午03:47:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean checkRequirementReged(Map<String, Object> param)throws Exception {
		return requirementRegDao.checkRequirementReged(param);
	}

	/** 
	 * Description :  更新需求报名的状态
	 * Create Date: 2011-3-11下午05:05:28 by yucy  Modified Date: 2011-3-11下午05:05:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateStatus(String objIds, Map<String, Object> param ) throws Exception {
		return requirementRegDao.updateStatus(objIds, param );
	}

	/** 
	 * Description :  获得报名集合
	 * Create Date: 2011-4-7上午11:33:53 by yucy  Modified Date: 2011-4-7上午11:33:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<RequirementReg> getRequirementRegList(Map<String, Object> param)throws Exception {
		return requirementRegDao.getRequirementRegList(param);
	}

	/** 
	 * Description :  校验报名身份证号码的唯一性
	 * Create Date: 2010-11-8下午03:06:40 by yucy  Modified Date: 2010-11-8下午03:06:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean checkedIdCard(Map<String, Object> param) throws Exception {
		return requirementRegDao.checkedIdCard(param);
	}

	/** 
	 * Description :  保存需求报名信息
	 * Create Date: 2011-4-1下午01:52:33 by yucy  Modified Date: 2011-4-1下午01:52:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public RequirementReg saveRequirementReg(RequirementReg requirementReg,Map<String, Object> params) throws Exception {
		//查询是否存在此联系人
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("cardId", requirementReg.getIdCard());
		param.put("companyId", AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		
		if(StringUtils.hasLength((String)params.get("type")) && "create".equals((String)params.get("type"))) {
			String empId = signUprecordDaoXygh.getHasThisLinker(param);
			if(!StringUtils.hasLength(empId)) {
				Employee employee = new Employee();
				employee.setAddress(requirementReg.getAddress());
				employee.setCompany(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany());
				employee.setCreatedDate(new Date());
				employee.setCreateTime(new Date());
				employee.setDepartment(AuthenticationHelper.getCurrentUser(true).getEmp().getDepartment());
				employee.setIdCard(requirementReg.getIdCard());
				employee.setMobile(requirementReg.getLinkTel());
				employee.setName(requirementReg.getLinkMen());
				employee.setZipCode(requirementReg.getZipCode());
				employee.setShortSpellName(new WordToSpell().String2Alpha(requirementReg.getLinkMen()));
				employeeDao.save(employee);
			}else {
				Employee employee = employeeDao.get(empId);
				employee.setName(requirementReg.getLinkMen());
				employee.setMobile(requirementReg.getLinkTel());
				employee.setIdCard(requirementReg.getIdCard());
				employee.setAddress(requirementReg.getAddress());
				employee.setZipCode(requirementReg.getZipCode());
				employeeDao.save(employee);
			}
				
		}else {
			Employee employee = employeeDao.get((String)params.get("empId"));
			employee.setName(requirementReg.getLinkMen());
			employee.setMobile(requirementReg.getLinkTel());
			employee.setIdCard(requirementReg.getIdCard());
			employee.setAddress(requirementReg.getAddress());
			employee.setZipCode(requirementReg.getZipCode());
			employeeDao.save(employee);
		}
		return requirementRegDao.save(requirementReg);
	}

}
