package com.gpcsoft.pubservice.application.message.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.pubservice.application.message.domain.Note;

public interface NoteManager extends BaseManager<Note> {
	
	/**
	 * Description :  读取指定路径path文件夹下的所有文件
	 * Create Date: 2010-10-22上午06:23:23 by zhaojf  Modified Date: 2010-10-22上午06:23:23 by zhaojf
	 * @param   list 结果列表, path 路径
	 * @return  
	 * @Exception
	 */
	public List<String> initAvatar(List<String> list,String path) throws Exception;
}
