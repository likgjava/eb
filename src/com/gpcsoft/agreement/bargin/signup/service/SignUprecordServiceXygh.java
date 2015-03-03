package com.gpcsoft.agreement.bargin.signup.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.requirement.domain.RequirementReg;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;

public interface SignUprecordServiceXygh extends SignUprecordService {
	/** 
	 * Description :  确认报名
	 * Create Date: 2010-10-13下午10:18:56 by yucy  Modified Date: 2010-10-13下午10:18:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateSignUprecordAuditStatus(Map<String, Object> param) throws Exception ;

	/** 
	 * Description :  判断该项目是否已经报名
	 * Create Date: 2010-10-13下午10:18:54 by yucy  Modified Date: 2010-10-13下午10:18:54 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean ifHasSignUp(Map<String, Object> param) throws Exception;

	/** 
	 * Description : 撤销报名 
	 * Create Date: 2010-10-14上午01:07:26 by yucy  Modified Date: 2010-10-14上午01:07:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateSignUprecordAppllyStatus(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得评价的对象
	 * Create Date: 2010-10-28下午04:45:55 by yucy  Modified Date: 2010-10-28下午04:45:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEvaluateObjectSupplier(String projectId) throws Exception;
	

	/** 
	 * Description :  取出被投诉举报报名机构
	 * Create Date: 2010-10-29下午01:55:32 by dongcl  Modified Date: 2010-10-29下午01:55:32 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getComplainObjectSupplier(String projectId) throws Exception;

	/** 
	 * Description :  校验身份证 唯一性
	 * Create Date: 2010-11-8下午03:08:25 by yucy  Modified Date: 2010-11-8下午03:08:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean checkedIdCard(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  判断是否有这个联系人
	 * Create Date: 2010-11-16上午11:49:58 by yucy  Modified Date: 2010-11-16上午11:49:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	String getHasThisLinker(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  保存供应商报名
	 * Create Date: 2010-11-16下午12:16:42 by yucy  Modified Date: 2010-11-16下午12:16:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public SignUprecord saveSignUprecordXygh(SignUprecord signUprecord,Map<String, Object> params) throws Exception;
	
	/** 
	 * Description :  保存报名(根据需求报名)
	 * Create Date: 2011-4-8上午09:50:40 by yucy  Modified Date: 2011-4-8上午09:50:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveSignUprecordByRequiremntReg(Project project,  List<RequirementReg> requirementRegList) throws Exception;
	
	/** 
	 * Description :  批量保存供应商的报名信息
	 * Create Date: 2011-8-3下午02:27:23 by likg  Modified Date: 2011-8-3下午02:27:23 by likg
	 * @param   projectId:项目Id  supplierIds:供应商Id，以逗号分割
	 * @return  
	 * @Exception   
	 */
	void saveSignUprecordBatch(String projectId, String supplierIds) throws Exception;
}
