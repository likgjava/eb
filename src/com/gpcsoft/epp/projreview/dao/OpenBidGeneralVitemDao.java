package com.gpcsoft.epp.projreview.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;

public interface OpenBidGeneralVitemDao extends BaseGenericDao<OpenBidGeneralVitem> {

	/**
	 * FuncName:getAllOpenBidGeneralVitem
	 * Description :根据项目获得所有开标一览表明细  
	 * @param   projectId:项目主键
	 * @return  List<OpenBidGeneralVitem>
	 * @author liuke
	 * @Create Date: 2010-10-11下午01:39:25 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-11下午01:39:25 s 
	 */
	public List<OpenBidGeneralVitem> getAllOpenBidGeneralVitem(String projectId);
	
	/**
	 * FuncName:getOpenBidGeneralVitemListByOpenbidGeneralview
	 * Description :根据开标一览表获得开标一览表明细
	 * @param openbidGeneralviewId
	 * @return  List<OpenBidGeneralVitem>
	 * @author liuke
	 * @Create Date: 2010-12-15上午11:52:14 
	 * @Modifier  liuke
	 * @Modified Date: 2010-12-15上午11:52:14  
	 */
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemListByOpenbidGeneralview(String openbidGeneralviewId);
	
	/**
	 * FuncName:removeAllOpenBidGeneralVitemByProject
	 * Description:根据项目删除开标一览表明细对象
	 * @param projectId
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-15下午04:28:08 
	 * @Modifier    liuke
	 * @Modified Date: 2010-12-15下午04:28:08
	 */
	public void removeAllOpenBidGeneralVitemByProject(String projectId);
	
	
	
	/**
	 * FuncName : removeOpenBidGeneralVitemByOpenBidGeneralId
	 * Description :  根据开标一览表表头删除开标一览表信息
	 * Create Date: 2011-11-1上午10:37:16 by liuke
	 * Modified Date: 2011-11-1上午10:37:16 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeOpenBidGeneralVitemByOpenBidGeneralId(String openBidGeneralId);
	
	/**
	 * FuncName : getOpenBidGeneralVitemByGenViewIdAndFactorId
	 * Description :  
	 * @param GenViewId
	 * @param factorId
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-15 13:31
	 */
	public OpenBidGeneralVitem getOpenBidGeneralVitemByGenViewIdAndFactorId(String GenViewId, String factorId);
	
	
	/**
	 * FuncName : removeOpenBidGeneralVitemByOpenbidGeneralviewId
	 * Description :  根据开标一览删除开标一览表信息
	 * @param   
	 * @return  
	 * @Exception
	 * @author zhouzhanghe
	 * @Created date 2011-11-16 10:25
	 */
	public void removeOpenBidGeneralVitemByOpenbidGeneralviewId(String openbidGeneralviewId);
}
