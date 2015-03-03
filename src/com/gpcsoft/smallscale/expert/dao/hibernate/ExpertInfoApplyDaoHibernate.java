package com.gpcsoft.smallscale.expert.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.expert.dao.ExpertInfoApplyDao;
import com.gpcsoft.smallscale.expert.domain.ExpertInfoApply;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class ExpertInfoApplyDaoHibernate extends BaseGenericDaoHibernate<ExpertInfoApply> implements ExpertInfoApplyDao {

	/** 
	 * Description :  获取专家申请列表
	 * Create Date: 2011-1-6上午11:06:15 by likg  Modified Date: 2011-1-6上午11:06:15 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ExpertInfoApply> getApplyExpertList(String expertObjId, String applyType) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from ExpertInfoApply t left join fetch t.expertInfo where 1=1 ");
		if(StringUtils.hasLength(expertObjId)) {
			hql.append(" and t.expertInfo.objId = :expertObjId");
		}
		if(StringUtils.hasLength(applyType)) {
			hql.append(" and t.applyType = :applyType");
		}
		
		Query query = this.getSession().createQuery(hql.toString());
		if(StringUtils.hasLength(expertObjId)) {
			query.setString("expertObjId", expertObjId);
		}
		if(StringUtils.hasLength(applyType)) {
			query.setString("applyType", applyType);
		}
		return query.list();
	}

}
