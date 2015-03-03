package com.gpcsoft.epp.qualifications.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.qualifications.dao.QualificationsFileDao;
import com.gpcsoft.epp.qualifications.domain.QualificationsFile;
import com.gpcsoft.epp.qualifications.service.QualificationsFileService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;


@Service 
public class QualificationsFileServiceImpl extends BaseGenericServiceImpl<QualificationsFile> implements QualificationsFileService {
	@Autowired(required=true)  @Qualifier("qualificationsFileDaoHibernate")
	private QualificationsFileDao qualificationsFileDao;
	
	/**
	 * 查询项目对应的资格预审文件信息
	 * @param projectId
	 * @return
	 * @throws Exception
	 * @author shengn
	 * @date 2011-11-06
	 */
	public List<QualificationsFile> findProjectQualificationsFileInfo(String projectId)throws Exception{
		return qualificationsFileDao.getQualificationsFileListByPrjId(projectId);
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-12-1下午05:39:46 by chenhongjun  
	 *  Modified Date: 2011-12-1下午05:39:46 by chenhongjun 
	 * @see com.gpcsoft.epp.qualifications.service.QualificationsFileService#saveQualificationFile(com.gpcsoft.epp.qualifications.domain.QualificationsFile)
	 *
	 */
	public QualificationsFile saveQualificationFile(
			QualificationsFile qualificationsFile) throws Exception {
		String projectId=qualificationsFile.getProject().getObjId();
		qualificationsFile.setParentBizId(projectId);
		qualificationsFile.setUser(AuthenticationHelper.getCurrentUser());
		return qualificationsFile;
	}
	
}
