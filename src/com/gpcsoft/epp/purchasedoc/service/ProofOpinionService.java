package com.gpcsoft.epp.purchasedoc.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.purchasedoc.domain.ProofOpinion;

public interface ProofOpinionService extends BaseGenericService<ProofOpinion> {
	/**
	 * 
	 * Description :根据项目得到招标文件论证对象列表 
	 * Create Date: 2010-8-6下午01:21:57 by liuke  Modified Date: 2010-8-6下午01:21:57 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ProofOpinion> getProofOpinionListbyProjectId(String projectId);
	
	/** 
	 * Description :  保存招标文件论证信息
	 * Create Date: 2010-9-6下午03:24:17 by yangx  Modified Date: 2010-9-6下午03:24:17 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProofOpinion saveProofOpinion(ProofOpinion proofOpinion);
	/** 
	 * Description :  修改招标文件论证信息
	 * Create Date: 2010-9-6下午03:24:17 by yangx  Modified Date: 2010-9-6下午03:24:17 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProofOpinion updateProofOpinion(ProofOpinion proofOpinion);
	
	/** 
	 * Description :  根据主键获取招标文件论证对象
	 * Create Date: 2010-9-7下午05:13:18 by yangx  Modified Date: 2010-9-7下午05:13:18 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProofOpinion getProofOpinionByObjId(String objId);
}
