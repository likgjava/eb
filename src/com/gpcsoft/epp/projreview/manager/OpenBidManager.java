package com.gpcsoft.epp.projreview.manager;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.projreview.domain.OpenBid;

public interface OpenBidManager extends BaseManager<OpenBid> {

	/**
	 * FuncName:getOpenBidByQueryObject
	 * Description :根据查询对象得到开标对象  
	 * @param   queryObject:查询对象
	 * @return OpenBid
	 * @author liuke  
	 * @Create Date: 2010-5-24下午01:13:46 
	 * @Modifier    liuke
	 * @Modified Date: 2010-5-24下午01:13:46 by 
	 */
	public OpenBid getOpenBidByQueryObject(QueryObject  queryObject);
	
	/**
	 * FuncName:saveOpenBid
	 * Description :保存开标对象  
	 * @param   openBid:开标对象
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-5-25下午05:23:23 
	 * @Modifier   liuke
	 * @Modified Date: 2010-5-25下午05:23:23  
	 */
	public void saveOpenBid(OpenBid openBid);
	
	/**
	 * FuncName:getOpenBidByprojectId
	 * Description :  根据项目主键得到开标主表对象
	 * @param   projectId:项目主键
	 * @return  OpenBid
	 * @author liuke
	 * @Create Date: 2010-6-4下午02:11:29  
	 * @Modifier liuke
	 * @Modified Date: 2010-6-4下午02:11:29  
	 */
	public OpenBid getOpenBidByprojectId(String projectId);
	
	/**
	 * FuncName:getOpenBidBySubProjectId
	 * Description :根据包组主键得到开标主表对象
	 * @param   subProjectId 包组主键
	 * @return  OpenBid
	 * @author liuke
	 * @Create Date: 2010-6-4下午02:11:29 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4下午02:11:29 
	 */
	public OpenBid getOpenBidBySubProjectId(String subProjectId);

	/** 
	 * FuncName:isJudgeSupplierNum
	 * Description :  判断投标供应商数量是否符合要求
	 * @param   ebuyMethod:采购方式,projectId:项目主键,projName:项目名称
	 * @return  Boolean
	 * @author yangx
	 * @Create Date: 2010-11-4下午04:50:05 
	 * @Modifier    yangx
	 * @Modified Date: 2010-11-4下午04:50:05 
	 */
	public Boolean isJudgeSupplierNum (String ebuyMethod,String projectId,String projName)throws Exception ;
	
	/**
	 * FuncName:isSupplyerInBailrecord
	 * Description : 用以判断是否所有供应商都录入保证金缴纳情况
	 * @param projectId：项目Id,signUprecordId：报名Id
	 * @return  Boolean
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-20上午10:19:27 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-9-20上午10:19:27  
	 */	
	public Boolean isSupplyerInBailrecord(String projectId,String signUprecordId);
	
	/**
	 * FuncName:removeAllOpenBidByProject
	 * Description :根据项目删除开标信息
	 * @param projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-15下午05:46:51 
	 * @Modifier    liuke
	 * @Modified Date: 2010-12-15下午05:46:51 by 
	 */
	public void removeAllOpenBidByProject(String projectId);
}
