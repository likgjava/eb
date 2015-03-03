package com.gpcsoft.bizplatform.base.qualitymanagement.service;
import java.util.List;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.core.service.BaseGenericService;

public interface QualificationService extends BaseGenericService<Qualification> {

	/** 
	 * Description : 保存资质 
	 * Create Date: 2010-7-29下午02:42:02 by guoyr  Modified Date: 2010-7-29下午02:42:02 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Qualification saveQualification(Qualification qualification) ;
	
	/** 
	 * Description : 上下移动时修改原行和目标行的排序 
	 * Create Date: 2010-8-2下午06:01:48 by guoyr  Modified Date: 2010-8-2下午06:01:48 by guoyr
	 * @param  sourceObjId 原行的排序
	 * @param  targetObjId 目标行的排序
	 * @return  
	 * @Exception   
	 */
	public void  updateSort (String sourceObjId, String targetObjId);
	
	/** 
	 * Description : 修改资质的名称 
	 * Create Date: 2010-11-16上午11:10:25 by guoyr  Modified Date: 2010-11-16上午11:10:25 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateQualificationName(String objId, String qualityName);
	
	/** 
	 * Description :  获取下级资质的列表
	 * Create Date: 2010-11-18上午10:37:51 by guoyr  Modified Date: 2010-11-18上午10:37:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Qualification> getSubQualificationList(String parentId);
	
	/** 
	 * Description : 根据资质的编号获得该资质
	 * Create Date: 2010-11-18上午10:28:16 by guoyr  Modified Date: 2010-11-18上午10:28:16 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Qualification getQualificationByCode(String qualityCode);
	
	/** 
	 * Description : 判断资质名称在父节点下是不是唯一 
	 * Create Date: 2010-11-25上午10:23:13 by guoyr  Modified Date: 2010-11-25上午10:23:13 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String qualityName, String objId, String parentId );
}
