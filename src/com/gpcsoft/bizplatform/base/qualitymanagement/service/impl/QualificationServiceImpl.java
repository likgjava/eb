package com.gpcsoft.bizplatform.base.qualitymanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class QualificationServiceImpl extends BaseGenericServiceImpl<Qualification> implements QualificationService {

	@Autowired(required=true) @Qualifier("qualificationManagerImpl")
	private QualificationManager qualificationManager;
	@Autowired(required=true)  @Qualifier("qualificationDaoHibernate")
	private QualificationDao qualificationDaoHibernate;


	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationService#saveQualification(com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification)
	 */
	public Qualification saveQualification(Qualification qualification) {
		return qualificationManager.save(qualification);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationService#updateSort(java.lang.String, java.lang.String)
	 */
	public void updateSort(String sourceObjId, String targetObjId) {
		Qualification sourceQualification = qualificationManager.get(sourceObjId);
		Qualification targetQualification = qualificationManager.get(targetObjId);
		Integer tempSortInteger = sourceQualification.getSort();
		sourceQualification.setSort(targetQualification.getSort());
		targetQualification.setSort(tempSortInteger);
		
	}
	
	/** 
	 * Description : 修改资质的名称 
	 * Create Date: 2010-11-16上午11:10:25 by guoyr  Modified Date: 2010-11-16上午11:10:25 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateQualificationName(String objId, String qualityName){
		qualificationDaoHibernate.updateQualificationName(objId, qualityName);
	}

	/** 
	 * Description :  获取下级资质的列表
	 * Create Date: 2010-11-18上午10:37:51 by guoyr  Modified Date: 2010-11-18上午10:37:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Qualification> getSubQualificationList(String parentId){
		return qualificationDaoHibernate.getSubQualificationList(parentId);
	}
	
	/** 
	 * Description : 根据资质的编号获得该资质
	 * Create Date: 2010-11-18上午10:28:16 by guoyr  Modified Date: 2010-11-18上午10:28:16 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Qualification getQualificationByCode(String qualityCode){
		return qualificationDaoHibernate.getQualificationByCode(qualityCode);
	}
	
	/** 
	 * Description : 判断资质名称在父节点下是不是唯一 
	 * Create Date: 2010-11-25上午10:23:13 by guoyr  Modified Date: 2010-11-25上午10:23:13 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String qualityName, String objId, String parentId ){
		return qualificationDaoHibernate.isUnique(qualityName, objId, parentId);
	}

}
