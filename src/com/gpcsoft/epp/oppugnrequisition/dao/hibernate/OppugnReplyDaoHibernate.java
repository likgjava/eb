package com.gpcsoft.epp.oppugnrequisition.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.oppugnrequisition.dao.OppugnReplyDao;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;

import org.springframework.stereotype.Repository;

@Repository
public class OppugnReplyDaoHibernate extends BaseGenericDaoHibernate<OppugnReply> implements OppugnReplyDao {
	/** 
	 * Description :  根据质疑获取回复
	 * Create Date: 2010-8-17下午03:17:48 by yangx  Modified Date: 2010-8-17下午03:17:48 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<OppugnReply> getReplyByOppId(String oppId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from OppugnReply t where t.oppugnRequisition.objId = '"+oppId+"'");
		hql.append(" order by t.createTime desc");
		return this.getHibernateTemplate().find(hql.toString());
	}
}
