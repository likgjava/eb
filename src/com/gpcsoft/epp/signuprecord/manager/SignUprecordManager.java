package com.gpcsoft.epp.signuprecord.manager;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.srplatform.auth.domain.User;

public interface SignUprecordManager extends BaseManager<SignUprecord> {

	/**
	 * Description :  根据查询条件获取供应商报名列表
	 * Create Date: 2010-5-19上午08:06:34 by yemx  Modified Date: 2010-5-19上午08:06:34 by yemx
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page getSignUprecordByQueryObject(QueryObject queryObject,boolean cache,int start,int pageSize);
	
	/**
	 * Description :  保存供应商报名
	 * Create Date: 2010-5-19上午09:36:16 by yemx  Modified Date: 2010-5-19上午09:36:16 by yemx
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveSignUprecord(SignUprecord signUprecord);
	
	
	/**
	 * 
	 * FuncName: getSignUprecord
	 * Description :  根据QueryObject对象查询报名信息
	 * @param 
	 * @param queryObject
	 * @return SignUprecord
	 * @author: liuke
	 * @Create Date:2010-12-31  上午11:36:16
	 * @Modifier: liuke
	 * @Modified Date:2010-12-31  上午11:36:16
	 */
	@SuppressWarnings("unchecked")
	public SignUprecord getSignUprecord(QueryObject queryObject);
	

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
	 * Description :  用于判断代理机构是否重复录入供应商
	 * Create Date: 2010-10-21上午09:44:37 by shenjianzhuang  Modified Date: 2010-10-21上午09:44:37 by shenjianzhuang
	 * @param objId
	 * @param objId2
	 * @return  Boolean
	 */	
	public Boolean checkSupllierInSignupRecord(String objId,String objId2);
	
	
	/** 
	 * FuncName : getSignUprecordListByProjectId
	 * Description :  根据项目Id获取报名记录
	 * Create Date: 2011-8-8下午02:02:37 by yangx  Modified Date: 2011-8-8下午02:02:37 by yangx
	 * @param   projectId：项目Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getSignUprecordListByProjectId(String projectId);
}
