package com.gpcsoft.pubservice.application.message.manager.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.pubservice.application.message.dao.NoteDao;
import com.gpcsoft.pubservice.application.message.manager.NoteManager;
import com.gpcsoft.pubservice.application.message.domain.Note;

@Repository
public class NoteManagerImpl extends BaseManagerImpl<Note> implements NoteManager {

	@Autowired(required=true)  @Qualifier("noteDaoHibernate")
	private NoteDao noteDaoHibernate;

	public NoteDao getNoteDaoHibernate() {
		return noteDaoHibernate;
	}

	public void setNoteDaoHibernate(NoteDao noteDaoHibernate) {
		this.noteDaoHibernate = noteDaoHibernate;
	}

	/**
	 * Description :  读取指定路径path文件夹下的所有文件
	 * Create Date: 2010-10-22上午06:23:23 by zhaojf  Modified Date: 2010-10-22上午06:23:23 by zhaojf
	 * @param   list 结果列表, path 路径
	 * @return  
	 * @Exception
	 */
	public List<String> initAvatar(List<String> list, String path) throws Exception{
		path = path.replace("/", File.separator);
		File root = FileUtil.getDirectory(path);
		for(File file : root.listFiles()){
			if(file.getName().endsWith(".svn")){
				continue;
			}
			if(file.isDirectory()){
				initAvatar(list,file.getPath());
			}
			//String fileName = file.getPath();
			//fileName = fileName.replace(File.separator, "/");
			//list.add(fileName.substring(fileName.indexOf("webapp")+7));
			
			String fileName = file.getName();
			list.add(fileName);
		}
		return list;
	}

}
