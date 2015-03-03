package com.gpcsoft.epp.signuprecord.service;
import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.srplatform.auth.domain.User;

public interface SignUprecordService extends BaseGenericService<SignUprecord> {
	/** 
	 * Description :  更改报名状态为支付状态
	 * Create Date: 2011-7-21下午01:43:12 by sunl  Modified Date: 2011-7-21下午01:43:12 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePayStatus(String docId,String empId, String status) throws Exception;
		
	/**
	 * Description :  根据项目ID和供应商ID查询供应商的报名信息
	 * Create Date: 2010-5-20上午02:15:17 by yemx  Modified Date: 2010-5-20上午02:15:17 by yemx
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord getSignUprecordBySupplierId(String projectId,User supplierUser);

	/** 
	 * Description :  代理机构:根据项目ID查询供应商的报名信息
	 * Create Date: 2010-6-29下午04:15:56 by yangx  Modified Date: 2010-6-29下午04:15:56 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<SignUprecord> getSignupRecordList(String projectId);
	

	
	/** 
	 * Description :  获取供应商报名信息列表 [根据状态]
	 * Create Date: 2010-6-23下午03:27:02 by yangx  Modified Date: 2010-6-23下午03:27:02 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<SignUprecord> searchSignUprecordByQueryObject(Page<SignUprecord> page,QueryObject queryObject);
	/** 
	 * Description :  根据查询条件统计对应的报名信息数据
	 * Create Date: 2010-7-10上午10:57:34 by yangx  Modified Date: 2010-7-10上午10:57:34 by yangx
	 * @param   queryObject[auditStatus:审核状态;managerID:项目负责人]
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getSignUprecordTotalByQueryObject(QueryObject queryObject);
	
	/**
	 * 
	 * Description :保存供应商报名和报名响应 
	 * Create Date: 2010-8-13上午11:52:12 by liuke  Modified Date: 2010-8-13上午11:52:12 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord saveSignUprecordAndSignUpRespone(SignUprecord signUprecord,List<SignUpRespone> signUpResponeList,String subPrjIds)throws Exception;
	
	/**
	 * 
	 * Description : 修改供应商报名和报名响应  
	 * Create Date: 2010-9-6下午02:44:42 by liuke  Modified Date: 2010-9-6下午02:44:42 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord updateSignUprecordAndSignUpRespone(SignUprecord signUprecord,List<SignUpRespone> signUpResponeList);
	
	/**
	 * 
	 * Description : 抽取一定数量的供应商 
	 * Create Date: 2010-8-17上午11:11:32 by liuke  Modified Date: 2010-8-17上午11:11:32 by liuke
	 * @param   String num 抽取供应商个数  List<OrgInfo> supplierList 所有供应商列表
	 * @return  
	 * @Exception
	 */
	public List<OrgInfo> getSupplierList(List<OrgInfo> supplierList, String num);
   /**
	 * 
	 * Description :根据项目得到供应商报名信息  
	 * Create Date: 2010-9-1上午11:22:49 by shenjianzhuang  Modified Date: 2010-9-1上午11:22:49 by shenjianzhuang
	 * @param projectId
	 * @return
	 * @return  List<SignUprecord> 
	 * @Exception 
	 */
	public List<SignUprecord> getSignUprecordByprojectId(String projectId);
	
	/**
	 * 
	 * Description :根据主键获得报名信息对象 
	 * Create Date: 2010-9-7上午09:34:28 by liuke  Modified Date: 2010-9-7上午09:34:28 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord getSignUprecordByobjId(String objId);
	
	
	/**
	 * 
	 * Description : 审核供应商报名
	 * Create Date: 2010-9-7上午09:40:47 by liuke  Modified Date: 2010-9-7上午09:40:47 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord auditSignUprecord(String objIds,String opinion,String auditStatus);

	/**
	 * Description :  得到审核通过的供应商报名信息列表
	 * Create Date: 2010-10-20上午10:12:55 by shenjianzhuang  Modified Date: 2010-10-20上午10:12:55 by shenjianzhuang
	 * @param projectId
	 * @return
	 * @return  List<SignUprecord>
	 * @Exception	
	 */		
	public List<SignUprecord> findSignupRecordList(String projectId);


	/**
	 * Description :  删除供应商报名信息
	 * Create Date: 2010-10-20下午03:29:16 by shenjianzhuang  Modified Date: 2010-10-20下午03:29:16 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  SignUprecord
	 * @Exception
	 */		
	public SignUprecord deleteSignUprecord(String  objId);
	/**
	 * Description :  用于判断代理机构是否重复录入供应商
	 * Create Date: 2010-10-21上午09:44:37 by shenjianzhuang  Modified Date: 2010-10-21上午09:44:37 by shenjianzhuang
	 * @param objId
	 * @param objId2
	 * @return  Boolean
	 */	
	public Boolean checkSupllierInSignupRecord(String objId,String objId2);
	
	/**
	 * 
	 * Description :保存供应商报名和报名响应 
	 * Create Date: 2010-8-13上午11:52:12 by shenjz  Modified Date: 2010-8-13上午11:52:12 by shenjz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord saveSignUprecordAndSignUpResponeForAgency(SignUprecord signUprecord,List<SignUpRespone> signUpResponeList);
	
	/**
	 * FuncName: saveSignUprecordAndSignUpRespone1
	 * Description :  保存供应商报名和报名响应 (分包组的)
	 * @param 
	 * @param signUprecord
	 * @param signUpResponeList
	 * @return SignUprecord
	 * @author: shenjz
	 * @Create Date:2011-5-30  上午09:15:10
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-30  上午09:15:10
	 */
	public SignUprecord saveSignUprecordForSubPrj(SignUprecord signUprecord,List<SignUpRespone> signUpResponeList,String subPrjIds); 
	/** 
	 * Description :  根据查询条件统计对应的报名信息数据
	 * Create Date: 2010-7-10上午10:57:34 by yangx  Modified Date: 2010-7-10上午10:57:34 by yangx
	 * @param   queryObject[auditStatus:审核状态;managerID:项目负责人]
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<SignUprecord> getSignUprecordListByQueryObject(QueryObject queryObject);
	
	/**
	 * FuncName: getSignUprecordByprojectIdAndSupplierId
	 * Description :  根据项目和供应商得到报名信息 
	 * @param projectId
	 * @param supplierId
	 * @return SignUprecord
	 * @author: shenjz
	 * @Create Date:2011-6-2  上午10:17:29
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-2  上午10:17:29
	 */
	public SignUprecord getSignUprecordByprojectIdAndSupplierId(String projectId, String supplierId);
	
		
	/**
	 * 
	 * FuncName: getSignUprecordByprojectAndSupplierId
	 * Description :  根据项目与供应商获取报名信息
	 * @param 
	 * @param projectId
	 * @param supplierId
	 * @return SignUprecord
	 * @author: liuke
	 * @Create Date:2011-8-2  下午05:02:40
	 * @Modifier: liuke
	 * @Modified Date:2011-8-2  下午05:02:40
	 */
	public SignUprecord getSignUprecordByprojectAndSupplierId(String projectId, String supplierId);
	
	/** 
	 * FuncName : checkSignupRecord
	 * Description :  完成供应商报名
	 * Create Date: 2011-8-8下午01:52:38 by yangx  Modified Date: 2011-8-8下午01:52:38 by yangx
	 * @param   projectId
	 * @return  SignUprecord
	 * @Exception   
	 */
	public SignUprecord checkSignupRecord(String projectId);
	/**
	 * FuncName: getSignUprecordNotEntryBailRecord
	 * Description :  得到未录入保证金的项目记录
	 * @param 
	 * @return List<SignUprecord>
	 * @author: liuke
	 * @Create Date:2011-8-10  上午10:29:46
	 * @Modifier: liuke
	 * @Modified Date:2011-8-10  上午10:29:46
	 */
	public List<Object> getSignUprecordNotEntryBailRecord();
	
	
	/** 
	 * FuncName : getSignUprecordBySupplierId
	 * Description :  根据供应商Id获取报名记录
	 * Create Date: 2011-9-27下午03:15:34 by yangx  
	 * Modified Date: 2011-9-27下午03:15:34 by yangx
	 * @param   supplierId：供应商Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getSignUprecordBySupplierId(String supplierId);
	
	/**
	 * 
	 * Description :根据项目和审核状态获得报名信息  
	 * Create Date: 2010-9-10下午01:43:22 by liuke  Modified Date: 2010-9-10下午01:43:22 by liuke
	 * @param   projectId：项目Id,auditStatus：审核状态
	 * @return  List<SignUprecord>
	 * @Exception
	 */
	public List<SignUprecord> getSignUprecordByprojectIdAndAuditStatus(String projectId);
	
	/**
	 * FuncName: getCountSignUpSupplierByProjectId
	 * Description :  统计报名供应商家数
	 * @param projectId
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-4 11:47
	 */
	public int getCountSignUpSupplierByProjectId(String projectId) ;
}
