package com.gpcsoft.epp.bulletin.manager;

import com.gpcsoft.epp.bulletin.domain.PubLishBulModel;

public interface PublishBulletin{
	
	/** 
	 * Description :  对外发布公告
	 * Create Date: 2010-11-24上午11:11:34 by yangx  Modified Date: 2010-11-24上午11:11:34 by yangx
	 * @param   pubLishBulModel:对外发布公告的数据对象
	 * @return  PubLishBulModel
	 * @Exception   
	 */
	public PubLishBulModel publishBulletin(PubLishBulModel pubLishBulModel);
}
