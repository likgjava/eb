package com.gpcsoft.epp.qualifications.dao;

import java.util.List;

import com.gpcsoft.epp.qualifications.domain.QualificationsFile;

public interface QualificationsFileDao {

	/**
	 * 根据项目ID获得预审资格文件对象信息集合
	 * @param projectId 项目ID
	 * @return
	 * @throws Exception
	 * @author shengn
	 * @date 2011-11-06
	 */
	public List<QualificationsFile> getQualificationsFileListByPrjId(String projectId) throws Exception;
}
