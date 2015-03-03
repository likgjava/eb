package com.gpcsoft.epp.qualifications.service;

import java.util.List;

import com.gpcsoft.epp.qualifications.domain.QualificationsFile;

public interface QualificationsFileService {

	/**
	 * 查询项目对应的资格预审文件信息
	 * @param projectId
	 * @return
	 * @throws Exception
	 * @author shengn
	 * @date 2011-11-06
	 */
	public List<QualificationsFile> findProjectQualificationsFileInfo(String projectId)throws Exception;
	
	
	/**
	 * 
	 * Description : 保存资格预审文件
	 * Create Date: 2011-12-1下午05:39:11 by chenhongjun  
	 * Modified Date: 2011-12-1下午05:39:11 by chenhongjun
	 *@param qualificationsFile
	 *@return
	 *@throws Exception
	 *下午05:39:11 
	 *QualificationsFile
	 */
	public QualificationsFile saveQualificationFile(QualificationsFile qualificationsFile)throws Exception;
	
}
