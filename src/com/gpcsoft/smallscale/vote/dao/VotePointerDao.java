package com.gpcsoft.smallscale.vote.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.vote.domain.VotePointer;

public interface VotePointerDao extends BaseGenericDao<VotePointer> {
	
	/**
	 * Description :  获取主题下的指标
	 * Create Date: 2011-2-21下午02:44:22 by zhaojf  Modified Date: 2011-2-21下午02:44:22 by zhaojf
	 * @param   
	 * @return  
	 */
	public List<VotePointer> findPointOfTemplate(String templateId,String pointerFactor);
	
	/**
	 * Description :  删除主题下的指标
	 * Create Date: 2011-2-22下午02:20:37 by zhaojf  Modified Date: 2011-2-22下午02:20:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeVotePointOfTemplate(String templateId);
	
	/**
	 * Description :  根据编号获取子指标
	 * Create Date: 2011-2-23下午02:20:08 by zhaojf  Modified Date: 2011-2-23下午02:20:08 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<VotePointer> findPointerOfTemplateByCode(String pointerCode);

}
