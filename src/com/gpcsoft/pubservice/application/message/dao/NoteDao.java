package com.gpcsoft.pubservice.application.message.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.message.domain.Note;

public interface NoteDao extends BaseGenericDao<Note> {
	
	/**
	 * Description :  设置指定objId 留言/咨询的阅读状态为指定的状态readStatus
	 * Create Date: 2010-10-22上午05:52:12 by zhaojf  Modified Date: 2010-10-22上午05:52:12 by zhaojf
	 * @param   objId 主键, readStatus指定的阅读状态(0：未读, 1：已读)
	 * @return  
	 * @Exception
	 */
	public void setReadStatusIsRead(String objId,String readStatus) throws Exception;
}
