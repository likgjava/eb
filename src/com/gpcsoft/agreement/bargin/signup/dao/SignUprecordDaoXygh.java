package com.gpcsoft.agreement.bargin.signup.dao;

import java.util.Map;

import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;

/** 
  *  Comments: <strong>SignUprecordDaoXygh</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-11-8 下午03:09:53 by yucy    					                            
  *  <br/>Modified Date:  2010-11-8 下午03:09:53 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public interface SignUprecordDaoXygh extends SignUprecordDao{

	/** 
	 * Description :  确认报名
	 * Create Date: 2010-10-13下午10:21:05 by yucy  Modified Date: 2010-10-13下午10:21:05 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateSignUprecordAuditStatus(final Map<String, Object> param) ;

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
	 * Description :  取得评价对象
	 * Create Date: 2010-10-28下午04:49:35 by yucy  Modified Date: 2010-10-28下午04:49:35 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEvaluateObjectSupplier(String projectId) throws Exception ;
	
	/** 
	 * Description :  供应商用户取出被投诉举报报名机构(其他供应商和项目采购人)
	 * Create Date: 2010-10-29下午01:56:47 by dongcl  Modified Date: 2010-10-29下午01:56:47 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getComplainObjectSupplier(String projectId) throws Exception ;

	/** 
	 * Description :  校验身份证
	 * Create Date: 2010-11-8下午03:09:56 by yucy  Modified Date: 2010-11-8下午03:09:56 by yucy
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
	 * Description :  批量保存供应商的报名信息
	 * Create Date: 2011-8-3下午02:27:23 by likg  Modified Date: 2011-8-3下午02:27:23 by likg
	 * @param   projectId:项目Id  supplierIds:供应商Id，以逗号分割
	 * @return  
	 * @Exception   
	 */
	void saveSignUprecordBatch(String projectId, String supplierIds) throws Exception;
}
