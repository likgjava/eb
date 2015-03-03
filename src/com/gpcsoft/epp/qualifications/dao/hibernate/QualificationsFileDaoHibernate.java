package com.gpcsoft.epp.qualifications.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.qualifications.dao.QualificationsFileDao;
import com.gpcsoft.epp.qualifications.domain.QualificationsFile;

import org.springframework.stereotype.Repository;

@Repository
public class QualificationsFileDaoHibernate extends BaseGenericDaoHibernate<QualificationsFile> implements QualificationsFileDao {

	/**
	 * 根据项目ID获得预审资格文件对象信息集合
	 * @param projectId 项目ID
	 * @return
	 * @throws Exception
	 * @author shengn
	 * @date 2011-11-06
	 */
	@SuppressWarnings("unchecked")
	public List<QualificationsFile> getQualificationsFileListByPrjId(String projectId) throws Exception{
		return this.getHibernateTemplate().find("from QualificationsFile f where f.project.objId=?", projectId);
	}
}
