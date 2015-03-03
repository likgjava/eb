package com.gpcsoft.bizplatform.base.qualitymanagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.exception.QualificationException;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDefineDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;
import com.gpcsoft.bizplatform.base.qualitymanagement.enumeration.QualificationEnum;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationClassManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationDefineManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationDefineService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class QualificationDefineServiceImpl extends BaseGenericServiceImpl<QualificationDefine> implements QualificationDefineService {

	@Autowired(required=true) @Qualifier("qualificationDefineDaoHibernate")
	private QualificationDefineDao qualificationDefineDao;
	@Autowired(required=true) @Qualifier("qualificationClassManagerImpl")
	private QualificationClassManager qualificationClassManager;
	@Autowired(required=true) @Qualifier("qualificationDefineManagerImpl")
	private QualificationDefineManager qualificationDefineManager;
	@Autowired(required=true)  @Qualifier("qualificationDaoHibernate")
	private QualificationDao qualificationDaoHibernate;
	@Autowired(required=true)  @Qualifier("qualificationDefineDaoHibernate")
	private QualificationDefineDao qualificationDefineDaoHibernate;
	
	/** 
	 * Description :  取得资质定义 根据资质分类
	 * Create Date: 2010-7-30下午02:47:57 by yucy  Modified Date: 2010-7-30下午02:47:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationDefine> getDefineByClass(Map<String, Object> param)
			throws Exception {
		return qualificationDefineDao.getDefineByClass(param);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationDefineService#saveQualificationDefine(com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine)
	 */
	public QualificationDefine saveQualificationDefine(
			QualificationDefine qualificationDefine) throws Exception {
		qualificationDefine.getParent();
		
		// 设置拼音简写
		WordToSpell spell = new WordToSpell();
		qualificationDefine.setShortSpellName(spell.String2Alpha(qualificationDefine.getQualityName())); 
    	
		// 如果是新增，则初始该资质为叶子结点
		if(null == qualificationDefine.getObjId() || "".equals(qualificationDefine.getObjId())) {
			qualificationDefine.setIsLeaf(true);
			
			// 查询父资质用于当前新添加的子资质修改资质编号
			QualificationClass parent = qualificationClassManager.get(qualificationDefine.getParent().getObjId());
			
			// 获取该父点下所有子结点的个数
			int subCount = parent.getSubQualification().size() + 1;
			
			// 资质定义的父结点为资质分类，所以替换资质分类的编号C_为D_
			qualificationDefine.setQualityCode(parent.getQualityCode().replaceFirst("C", "D") + ((subCount + "").length() > 1 ? subCount + "" : "0" + subCount));
			
			// 排序号
			qualificationDefine.setSort(subCount);
			
			// 设置path
			if(parent.getPath() == null) {
				qualificationDefine.setPath(parent.getObjId() + "#");
			} else {
				qualificationDefine.setPath(parent.getPath() + parent.getObjId() + "#");
			}
			qualificationDefine.setObjId(null);
			qualificationDefine = qualificationDefineManager.save(qualificationDefine);
		}else {
			
			// 修改是只修改资质的名称
			qualificationDaoHibernate.updateQualificationName(qualificationDefine.getObjId(), qualificationDefine.getQualityName());
		}
		
		// 保存时将父资质改为非叶子结点
		if(null != qualificationDefine.getParent()) {
			qualificationDaoHibernate.updateQualificationIsLeaf(qualificationDefine.getParent().getObjId(), false);
		}
		return qualificationDefine;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationDefineService#removeQualificationDefine(java.lang.String)
	 */
	public void removeQualificationDefine(String objId) {
		
		// 如果机构资质信息中有引用资质参数定义，则不能删除，并抛出异常
		if(qualificationDefineDaoHibernate.getQualificationDefineCountFromOrgQuality(objId) > 0) {
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
