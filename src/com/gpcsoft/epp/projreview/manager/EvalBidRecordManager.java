package com.gpcsoft.epp.projreview.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;

public interface EvalBidRecordManager extends BaseManager<EvalBidRecord> {

	/**
	 * FuncName:getEvalBidRecordByProjectId
	 * Description : 根据项目主键得到项目评标对象 
	 * @param   projectId:项目主键
	 * @return  EvalBidRecord
	 * @author liuke
	 * @Create Date: 2010-6-4下午01:38:54  
	 * @Modifier liuke
	 * @Modified Date: 2010-6-4下午01:38:54 
	 */
	public EvalBidRecord getEvalBidRecordByProjectId(String projectId);
	
	/**
	 * FuncName:getEvalBidRecordByProjectIdAndUserId
	 * Description :  通过项目主键和用户主键得到项目评标对象
	 * @param  projectId:项目主键,userId:用户主键
	 * @return  EvalBidRecord
	 * @author liuke
	 * @Create Date: 2010-6-22下午05:54:11 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-22下午05:54:11 
	 */
	public EvalBidRecord  getEvalBidRecordByProjectIdAndUserId(String projectId ,String userId);
	
	/**
	 * FuncName:getEvalBidRecordBySubProjectIdAndUserId
	 * Description :  通过包组主键和用户主键得到项目评标对象
	 * @param   subProjectId:包组主键,userId:用户主键
	 * @return  EvalBidRecord
	 * @author liuke
	 * @Create Date: 2010-6-22下午05:54:11 
	 * @Modifier liuke
	 * @Modified Date: 2010-6-22下午05:54:11  
	 */
	public EvalBidRecord  getEvalBidRecordBySubProjectIdAndUserId(String subProjectId ,String userId);
	
	/**
	 * FuncName:getEvalBidRecordBySubProjectIdAndExpertName
	 * Description :  根据包组主键和专家姓名得到项目评标对象
	 * @param   subProjectId:包组主键,expertName专家姓名
	 * @return  EvalBidRecord
	 * @author liuke
	 * @Create Date: 2010-8-12上午09:41:34
	 * @Modifier    liuke
	 * @Modified Date: 2010-8-12上午09:41:34
	 */
	public EvalBidRecord  getEvalBidRecordBySubProjectIdAndExpertName(String subProjectId ,String expertName);
	
}
