package com.gpcsoft.bizplatform.base.qualitymanagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.exception.QualificationException;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationParamDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.bizplatform.base.qualitymanagement.enumeration.QualificationEnum;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationDefineManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationParamManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationParamService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class QualificationParamServiceImpl extends BaseGenericServiceImpl<QualificationParam> implements QualificationParamService {

	@Autowired(required=true) @Qualifier("qualificationParamDaoHibernate")
	private QualificationParamDao qualificationParamDao;
	
	/** 
	 * Description : 取得资质参数  根据资质定义
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationParam> getParamByDefine(Map<String, Object> param) throws Exception{
		return qualificationParamDao.getParamByDefine(param);
	}
	@Autowired(required=true) @Qualifier("qualificationDefineManagerImpl")
	private QualificationDefineManager qualificationDefineManager;
	@Autowired(required=true) @Qualifier("qualificationParamManagerImpl")
	private QualificationParamManager qualificationParamManager;
	@Autowired(required=true)  @Qualifier("qualificationDaoHibernate")
	private QualificationDao qualificationDaoHibernate;
	@Autowired(required=true)  @Qualifier("qualificationParamDaoHibernate")
	private QualificationParamDao qualificationParamDaoHibernate;

	
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationParamService#saveQualificationParam(com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam)
	 */
	public QualificationParam saveQualificationParam(
			QualificationParam qualificationParam) throws Exception {
		
		// 设置拼音简写
		WordToSpell spell = new WordToSpell();
		qualificationParam.setShortSpellName(spell.String2Alpha(qualificationParam.getQualityName())); 
		
		// 如果是新增，则初始该资质为叶子结点,并设置编号
		if(null == qualificationParam.getObjId() || "".equals(qualificationParam.getObjId())) {
			qualificationParam.setIsLeaf(true);
			
			// 查询父资质用于当前新添加的子资质修改资质编号
			QualificationDefine parent = qualificationDefineManager.get(qualificationParam.getParent().getObjId());
			
			// 获取该父点下所有子结点的个数
			int subCount = parent.getSubQualification().size() + 1;
			
			// 资质定义的父结点为资质分类，所以替换资质分类的编号D_为P_
			qualificationParam.setQualityCode(parent.getQualityCode().replaceFirst("D", "P") + ((subCount + "").length() > 1 ? subCount + "" : "0" + subCount));
			
			// 排序号
			qualificationParam.setSort(subCount);
			
			// 设置path
			if(parent.getPath() == null) {
				qualificationParam.setPath(parent.getObjId() + "#");
			} else {
				qualificationParam.setPath(parent.getPath() + parent.getObjId() + "#");
			}
			qualificationParam.setObjId(null);
			qualificationParamManager.save(qualificationParam);
		}else {
			
			// 修改是只修改资质的名称
			qualificationDaoHibernate.updateQualificationName(qualificationParam.getObjId(), qualificationParam.getQualityName());
		}
		
		// 保存时将父资质改为非叶子结点
		if(null != qualificationParam.getParent().getObjId()) {
			qualificationDaoHibernate.updateQualificationIsLeaf(qualificationParam.getParent().getObjId(), false);
		}
		return qualificationParam;
	}
	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationParamService#removeQualificationParam(java.lang.String)
	 */
	public void removeQualificationParam(String objId) {
		
		// 如果机构资质详细信息中有引用资质参数，则不能删除，并抛出异常
		if(qualificationParamDaoHibernate.getQualificationParamCountFromQualificationDetail(objId) > 0) {
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
	
	/** 
	 * Description : 取得资质参数  根据资质分类
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationParam> getParamByClass(Map<String, Object> param) throws Exception {
		return qualificationParamDaoHibernate.getParamByClass(param);
	}
}
