package com.gpcsoft.epp.signuprecord.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;

public interface SignUprecordDao extends BaseGenericDao<SignUprecord> {

	/** 
	 * Description :  更改报名状态为支付状态
	 * Create Date: 2011-7-21下午01:43:12 by sunl  Modified Date: 2011-7-21下午01:43:12 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePayStatus(String projId, String supplierId, String status) throws Exception;
	
	/** 
	 * Description :  获取供应商报名信息列表 
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
	 * Description :根据项目和供应商得到报名信息  
	 * Create Date: 2010-8-30上午10:14:25 by liuke  Modified Date: 2010-8-30上午10:14:25 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord getSignUprecordByprojectIdAndSupplierId(String projectId,String supplierId);
	
	/**
	 * 
	 * Description :根据项目和供应商得到报名信息 Create Date: 2010-8-30上午10:14:25 by liuke
	 * Modified Date: 2010-8-30上午10:14:25 by shenjz
	 * @param
	 * @return
	 * @Exception
	 */
	public SignUprecord getSignUprecordByprojectAndSupplierId(String projectId,String supplierId);
	
	  /**
		 * 
		 * Description :根据项目得到报名信息  
		 * Create Date: 2010-9-1上午11:22:49 by shenjianzhuang  Modified Date: 2010-9-1上午11:22:49 by shenjianzhuang
		 * @param projectId
		 * @return
		 * @return  List<SignUprecord> 
		 * @Exception 
		 */
	public List<SignUprecord> getSignUprecordByprojectId(String projectId);
	
	
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
	 * Description :根据报名信息ID审核报名信息
	 * @param objIds
	 * @param auditStatus
	 */
	public void saveAuditSignUprecord(String objIds,String auditStatus);
	
	/**
	 * FuncName: getSignUprecordList
	 * Description :  获取报名xml（电子评审系统）
	 * @param 
	 * @return List<SignUprecord>
	 * @author: shenjz
	 * @Create Date:2011-3-24  上午11:25:19
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  上午11:25:19
	 */
	public List<SignUprecord> getSignUprecordList(String projectids);
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
	 * 
	 * FuncName: getSignUprecordListByprojectIdAndSupplierId
	 * Description :  创建或修改对象
	 * @param 
	 * @param projectId
	 * @param supplierId
	 * @return List<SignUprecord>
	 * @author: liuke
	 * @Create Date:2011-6-1  下午07:12:30
	 * @Modifier: liuke
	 * @Modified Date:2011-6-1  下午07:12:30
	 */
	public List<SignUprecord> getSignUprecordListByprojectIdAndSupplierId(
			String projectId, String supplierId);
	
	/**
	 * FuncName: getCountSignUpSupplierByProjectId
	 * Description :  统计报名供应商家数
	 * @param projectId
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-4 11:47
	 */
	public int getCountSignUpSupplierByProjectId(String projectId) ;	
	/** 
	 * FuncName : getWaitAuditSignUprecordByProjectid
	 * Description :  根据项目ID获取待审核的报名信息
	 * Create Date: 2011-8-8下午02:26:48 by yangx  Modified Date: 2011-8-8下午02:26:48 by yangx
	 * @param   projectId：项目Id 
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getWaitAuditSignUprecordByProjectid(String projectId);
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
	@SuppressWarnings("unchecked")
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
}
