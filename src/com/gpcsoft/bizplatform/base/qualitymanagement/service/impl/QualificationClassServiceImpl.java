package com.gpcsoft.bizplatform.base.qualitymanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.exception.QualificationException;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationClassDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;
import com.gpcsoft.bizplatform.base.qualitymanagement.enumeration.QualificationEnum;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationClassManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationClassService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class QualificationClassServiceImpl extends BaseGenericServiceImpl<QualificationClass> implements QualificationClassService {

	@Autowired(required=true) @Qualifier("qualificationClassManagerImpl")
	private QualificationClassManager qualificationClassManager;
	@Autowired(required=true)  @Qualifier("qualificationDaoHibernate")
	private QualificationDao qualificationDaoHibernate;
	@Autowired(required=true)  @Qualifier("qualificationClassDaoHibernate")
	private QualificationClassDao qualificationClassDaoHibernate;

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationClassService#saveQualificationClass(com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass)
	 */
	public QualificationClass saveQualificationClass(QualificationClass qualificationClass) throws Exception {
		
		
		// 设置拼音简写
		WordToSpell spell = new WordToSpell();
		qualificationClass.setShortSpellName(spell.String2Alpha(qualificationClass.getQualityName())); 
    	

		// 如果是新增，则初始该资质为叶子结点
		if(null == qualificationClass.getObjId() || "".equals(qualificationClass.getObjId())) {
			qualificationClass.setIsLeaf(true);
			
			// 查询父资质用于当前新添加的子资质修改资质编号,并设置编号和排序号
			if(null != qualificationClass.getParent()) {
				Qualification parent = qualificationClassManager.get(qualificationClass.getParent().getObjId());
				int subCount = parent.getSubQualification().size() + 1;
				
				// 编号
				qualificationClass.setQualityCode(parent.getQualityCode() + ((subCount + "").length() > 1 ? subCount + "" : "0" + subCount));
				
				// 排序号
				qualificationClass.setSort(subCount);
				
				// 设置path
				if(parent.getPath() == null) {
					qualificationClass.setPath(parent.getObjId() + "#");
				} else {
					qualificationClass.setPath(parent.getPath() + parent.getObjId() + "#");
				}
			}else {
				
				// 如果资质分类的父目录是null，则取得所有父结点为null的最大的排序号加1做为新的编号和组成编号 
				int sort = qualificationClassDaoHibernate.getQualificationClassMaxSort() + 1;
				
				// 排序号
				qualificationClass.setSort(sort);
				
				// 编号
				qualificationClass.setQualityCode("C" + ((sort +"").length() > 1 ? sort + "" : "0" + sort));
			}
			
			qualificationClass.setObjId(null);
			qualificationClass = qualificationClassManager.save(qualificationClass);
		}else {
			
			// 修改是只修改资质的名称
			qualificationDaoHibernate.updateQualificationName(qualificationClass.getObjId(), qualificationClass.getQualityName());
		
		}
		
		// 保存时将父资质改为非叶子结点
		if(null != qualificationClass.getParent()) {
			qualificationDaoHibernate.updateQualificationIsLeaf(qualificationClass.getParent().getObjId(), false);
		}
		return qualificationClass;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationClassService#removeQualificationClass(java.lang.String)
	 */
	public void removeQualificationClass(String objId) {
		
		// 如果机构资质信息中有引用资质分类，则不能删除，并抛出异常
		if(qualificationClassDaoHibernate.getQualificationClassCountFromOrgQuality(objId) > 0) {
			throw new QualificationException(messageSource.getMessage(QualificationEnum.QUALIFICATION_CAN_NOT_REMOVE));
		} else {
			// 删除时如果该结果的父结点下已经只有1个子结点，则将父资质改为叶子结点
			
			if(qualificationDaoHibernate.getSubQualificationCount(objId) <= 1) {
				Qualification qualification = qualificationDaoHibernate.get(objId);
				// 如果不是顶级节点
				if(null != qualification.getParent()) {
					qualificationDaoHibernate.updateQualificationIsLeaf(qualification.getParent().getObjId(), true);
				}
			}
			// 删除资质
			qualificationDaoHibernate.removeQualification(objId);
		}
	}
	

}
