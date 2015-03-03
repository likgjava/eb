package com.gpcsoft.bizplatform.base.qualitymanagement.dao;

import java.util.List;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface QualificationDao extends BaseGenericDao<Qualification> {

	/** 
	 * Description : 修改资质是否为叶子结点 
	 * Create Date: 2010-7-30上午10:44:19 by guoyr  Modified Date: 2010-7-30上午10:44:19 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateQualificationIsLeaf(String objId,boolean isleaf);
	
	/** 
	 * Description : 查询当前资质的父结点的子结点的个数 
	 * Create Date: 2010-8-3下午06:16:29 by guoyr  Modified Date: 2010-8-3下午06:16:29 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getSubQualificationCount(String objId);
	
	/** 
	 * Description :删除资质
	 * Create Date: 2010-8-24下午08:24:35 by guoyr  Modified Date: 2010-8-24下午08:24:35 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeQualification(String objId);
	
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
