package com.gpcsoft.pubservice.application.message.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.message.domain.Note;

public interface NoteService extends BaseGenericService<Note> {
	
	/**
	 * Description :  设置指定objId 留言/咨询的阅读状态为指定的状态readStatus
	 * Create Date: 2010-10-22上午05:52:12 by zhaojf  Modified Date: 2010-10-22上午05:52:12 by zhaojf
	 * @param   objId 主键, readStatus指定的阅读状态(0：未读, 1：已读)
	 * @return  
	 * @Exception
	 */
	public void setReadStatusIsRead(String objId,String readStatus) throws Exception;
	
	/**
	 * Description :  读取指定路径path文件夹下的所有文件
	 * Create Date: 2010-10-22上午06:23:23 by zhaojf  Modified Date: 2010-10-22上午06:23:23 by zhaojf
	 * @param   list 结果列表, path 路径
	 * @return  
	 * @Exception
	 */
	public List<String> initAvatar(List<String> list, String path) throws Exception;
}
