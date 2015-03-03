package com.gpcsoft.epp.projreview.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;

public interface OpenbidGeneralviewDao extends BaseGenericDao<OpenbidGeneralview> {

	/**
	 * FuncName:getAllByProjectId
	 * Description: 根据项目ID获取所有开标一览表
	 * @param projectId:项目主键
	 * @return List<OpenbidGeneralview>
	 * @author wanghz
	 * @Create Date 2010-8-1 下午02:30:11 
	 */
	public List<OpenbidGeneralview> getAllByProjectId(String projectId)throws Exception;
	
	/**
	 * FuncName:validateIsEndProjectPlan
	 * Description: 验证项目计划是否完成
	 * @param packId 包件ID
	 * @return Boolean
	 * @author wanghz
	 * @Create Date 2010-8-1 下午11:10:46  
	 */
	public Boolean validateIsEndProjectPlan(String packId)throws Exception;
	
	/**
	 * FuncName:getAllNoPackByProjectId
	 * Description :得到所有未分包的项目开标一览表 
	 * @param   projectId:项目主键
	 * @return  List<OpenbidGeneralview>
	 * @author liuke
	 * @Create Date: 2010-9-2上午10:46:43 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-2上午10:46:43  
	 */
	public List<OpenbidGeneralview> getAllNoPackByProjectId(String projectId)throws Exception;
	
	/**
	 * FuncName:getOpenbidGeneralviewByOpenBidRecordId
	 * Description :根据评审结果主键获得开标一览表对象  
	 * @param   
	 * @return  OpenbidGeneralview
	 * @author liuke
	 * @Create Date: 2010-9-16下午04:49:11 
	 * @Modifier  liuke
	 * @Modified Date: 2010-9-16下午04:49:11 
	 */
	public OpenbidGeneralview getOpenbidGeneralviewByOpenBidRecordId(String bidRecordId);
	
	/**
	 * FuncName:removeAllOpenbidGeneralviewByProject
	 * Description :根据项目ID删除所有开标一览表
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-15下午05:04:07 
	 * @Modifier   liuke
	 * @Modified Date: 2010-12-15下午05:04:07 
	 */
	public void removeAllOpenbidGeneralviewByProject(String projectId);
	
	
	/**
	 * FuncName: getOpenbidGeneralviewBySupplierIdAndPackId
	 * Description :  根据包件与供应商ID获得开标一览表数据
	 * @param 
	 * @param supplierId
	 * @param packId void
	 * @author: liuke
	 * @Create Date:2011-9-29  下午07:13:21
	 * @Modifier: liuke
	 * @Modified Date:2011-9-29  下午07:13:21
	 */
	public OpenbidGeneralview getOpenbidGeneralviewBySupplierIdAndPackId(String supplierId,String packId);
}
