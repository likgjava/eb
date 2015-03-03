package com.gpcsoft.epp.projreview.dao;



import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projreview.domain.OpenBid;

public interface OpenBidDao extends BaseGenericDao<OpenBid> {
	
	/**
	 * FuncName:saveOpenBid
	 * Description:保存开标主表对象  
	 * @param   openBid:开标主键
	 * @return  	void
	 * @author 	   liuke
	 * @Create Date: 2010-5-26下午05:17:56 
	 * @Modifier   liuke
	 * @Modified Date: 2010-5-26下午05:17:56 
	 */
	public void saveOpenBid(OpenBid openBid);
	
	 /**
	  * FuncName:getOpenBidByProjectIdAndPackId
	  * Description :通过项目和包组得到开标记录  
	  * @param   projectId:项目主键,subProjectId:包租主键
	  * @return  List<OpenBid> 
	  * @author liuke
	  * @Create Date: 2010-9-19下午01:49:44 
	  * @Modifier   liuke
	  * @Modified Date: 2010-9-19下午01:49:44 
	  */
	 public List<OpenBid> getOpenBidByProjectIdAndPackId(String projectId,String subProjectId)throws Exception;
	 
	 /**
	  * FuncName:getOpenBidBySubProjectId
	  * Description :根据包组ID得到开标记录列表  
	  * @param   String subProjId  包组ID
	  * @return  List<OpenBid> 开标对象列表
	  * @author liuke
	  * @Create Date: 2010-10-27上午11:24:18 
	  * @Modifier   liuke
	  * @Modified Date: 2010-10-27上午11:24:18  
	  */
	 public  List<OpenBid> getOpenBidBySubProjectId(String subProjId);
	 
	 /**
	  * FuncName:removeAllOpenBidByProject
	  * Description :根据项目删除开标信息
	  * @param projectId:项目主键
	  * @return  void
	  * @author liuke
	  * @Create Date: 2010-12-15下午05:48:38 
	  * @Modifier   liuke
	  * @Modified Date: 2010-12-15下午05:48:38 
	  */
	 public void removeAllOpenBidByProject(String projectId);
	 
	 
	 /**
	  * FuncName: removeAllOpenBidNotInBidPackage
	  * Description :  删除项目里不存在的开标记录
	  * @param 
	  * @param projectId void
	  * @author: liuke
	  * @Create Date:2011-3-12  上午11:36:22
	  * @Modifier: liuke
	  * @Modified Date:2011-3-12  上午11:36:22
	  */
	 public void removeAllOpenBidNotInBidPackage(String projectId);
	 
	 
	 /**
	  * FuncName: removeAllOpenBidByPack
	  * Description :  根据包组删除开标对象
	  * @param 
	  * @param packId void
	  * @author: liuke
	  * @Create Date:2011-5-28  下午12:57:23
	  * @Modifier: liuke
	  * @Modified Date:2011-5-28  下午12:57:23
	  */
	 public void removeAllOpenBidByPack(String packId);
	 
	 /**
	 * FuncName: getOpenBidByProjectId
	 * Description :根据项目ID得到开标记录列表  
	 * @param   String projectId  项目ID
	 * @return  List<OpenBid> 开标对象列表
	 * @author zhouzhanghe
	 * @Create Date: 2010-11-08 20:01
	 */
	public List<OpenBid> getOpenBidByProjectId(String projectId);
}