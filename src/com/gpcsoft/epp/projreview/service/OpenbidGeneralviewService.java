package com.gpcsoft.epp.projreview.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;

public interface OpenbidGeneralviewService extends BaseGenericService<OpenbidGeneralview> {
	
	/**
	 * FuncName:getOpenbidGeneralviewList
	 * Description: 获取或生成开标一览表
	 * @param projectId:项目主键
	 * @return List<OpenbidGeneralview>
	 * @author wanghz
	 * @Create Date 2010-8-1 下午02:17:14 
	 */
	public List<OpenbidGeneralview> getOpenbidGeneralviewList(String projectId)throws Exception;
	
	/**
	 * FuncName:getOpenbidGeneralviewListByProject
	 * Description:获得开标一览表
	 * @param projectId:项目主键
	 * @return  List<OpenbidGeneralview>
	 * @author Administrator 
	 */
	public List<OpenbidGeneralview> getOpenbidGeneralviewListByProject(String projectId)throws Exception ;
	
	/**
	 * FuncName:validateIsEndProjectPlan
	 * Description:验证项目计划是否完成
	 * @param packId:包件主键
	 * @return Boolean
	 * @author wanghz
	 * @Create Date 2010-8-1 下午11:10:46  
	 */
	public Boolean validateIsEndProjectPlan(String packId)throws Exception;
	
	/**
	 * FuncName:saveOpenbidGeneralviewObject
	 * Description :保存开标一览表  
	 * @param   openbidGeneralview:开标一览表
	 * @return  OpenbidGeneralview
	 * @author 		liuke
	 * @Create Date: 2010-9-7上午11:28:58 
	 * @Modifier    liuke
	 * @Modified Date: 2010-9-7上午11:28:58  
	 */
	public OpenbidGeneralview saveOpenbidGeneralviewObject(OpenbidGeneralview openbidGeneralview) throws Exception;
	
	/**
	 * FuncName:updateOpenbidGeneralviewObject
	 * Description :修改开标一览表  
	 * @param   openbidGeneralview:开标一览表
	 * @return  OpenbidGeneralview
	 * @author 		liuke
	 * @Create Date: 2010-9-7上午11:28:58 
	 * @Modifier    liuke
	 * @Modified Date: 2010-9-7上午11:28:58 
	 */
	public OpenbidGeneralview updateOpenbidGeneralviewObject(OpenbidGeneralview openbidGeneralview);
	
	/**
	 * FuncName:saveAllOpenbidGeneralviewByOpenBidRecord
	 * Description :根据开标记录子表保存开标一览表  
	 * @param  openBidRecord:开标列表,projectId:项目主键
	 * @return 		void
	 * @author 	   liuke 
	 * @Create Date: 2010-10-11下午05:09:07 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-11下午05:09:07 
	 */
	public void saveAllOpenbidGeneralviewByOpenBidRecord(List<OpenBidRecord> openBidRecord,String projectId)throws Exception;
	
	/**
	 * FuncName:saveOpenbidGeneralviewMessage
	 * Description :保存开标一览表及其他信息
	 * @param openbidGeneralviewlist:开标一览表,projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-14下午02:34:11 
	 * @Modifier  liuke
	 * @Modified Date: 2010-12-14下午02:34:11  
	 */
	public Project saveOpenbidGeneralviewMessage(List<OpenbidGeneralview> openbidGeneralviewlist,String projectId);
	
	/**
	 * FuncName:updateOpenbidGeneralviewMessage
	 * Description:修改开标一览表信息
	 * @param openbidGeneralviewlist:开标一览表,projectId:项目主键
	 * @return  void
	 * @author 	 liuke
	 * @Create Date: 2010-12-15下午04:04:40
	 * @Modifier   liuke
	 * @Modified Date: 2010-12-15下午04:04:40 
	 */
	public void updateOpenbidGeneralviewMessage(List<OpenbidGeneralview> openbidGeneralviewlist,String projectId);
	
}
