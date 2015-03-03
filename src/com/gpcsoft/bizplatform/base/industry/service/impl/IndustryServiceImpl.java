package com.gpcsoft.bizplatform.base.industry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.industry.dao.IndustryDao;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.base.industry.manager.IndustryManager;
import com.gpcsoft.bizplatform.base.industry.service.IndustryService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class IndustryServiceImpl extends BaseGenericServiceImpl<Industry> implements IndustryService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("industryManagerImpl")
	private IndustryManager industryManager;
	
	@Autowired(required=true)  @Qualifier("industryDaoHibernate")
	private IndustryDao industryDaoHibernate;

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.purcategory.service.IndustryService#saveIndustry(com.gpcsoft.bizplatform.base.purcategory.domain.Industry)
	 */
	public Industry saveIndustry(Industry industry) {
		
		// 设置拼音简写
		WordToSpell spell = new WordToSpell();
		industry.setShortSpellName(spell.String2Alpha(industry.getName())); 
		
		if( null == industry.getObjId() || "".equals(industry.getObjId())) {
			industry.setIsLeaf(true);
		}
		industryDaoHibernate.save(industry);
		
		// 保存时将父节点改为非叶子结点
		if(null != industry.getParent()) {
			industryDaoHibernate.updateIndustryIsLeaf(industry.getParent().getObjId(), false);
		}
		return industry;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.purcategory.service.IndustryService#removeIndustry(java.lang.String)
	 */
	public void removeIndustry(String objId) throws Exception {
		try {
			
			// 解决缓存问题 
			Industry industry = industryDaoHibernate.getHibernateTemplate().get(Industry.class, objId);
			
			// 删除时如果该结果的父结点下已经只有1个子结点，则将父资质改为叶子结点
			if(industryDaoHibernate.getSubIndustryCount(objId) <= 1 && null != industry.getParent()) {
				industryDaoHibernate.updateIndustryIsLeaf(industry.getParent().getObjId(), true);
			}
			industryDaoHibernate.remove(industry);
		} catch (Exception e) {
			throw new Exception("该行业已被其它数据引用，不能删除！");
		}
	}
	
	/** 
	 * Description :  根据级别，获得行业
	 * Create Date: 2010-11-16下午04:04:21 by liangxj  Modified Date: 2010-11-16下午04:04:21 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Industry> getIndustryByLevel(Short level) throws Exception{
		
		return industryDaoHibernate.getIndustryByLevel(level);
//		QueryObject<Industry> query = new QueryObjectBase<Industry>();
//		query.setEntityClass(Industry.class);
//		query.getQueryParam().and(new QueryParam("level",QueryParam.OPERATOR_EQ,level));
//		return findByQuery(query);
	}
}
