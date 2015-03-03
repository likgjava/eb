package com.gpcsoft.goods.goodsclass.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;

@Repository
public class GoodsClassParamDaoHibernate extends BaseGenericDaoHibernate<GoodsClassParam> implements GoodsClassParamDao {

	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.dao.GoodsClassParamDao#getGoodsClassParamCountFromGoodsParam(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Long getGoodsClassParamCountFromGoodsParam(String objId) {
		String hql = "select count(objId) from GoodsParam where goodsClassParam.objId = ?";
		List<Object> list = getHibernateTemplate().find(hql, objId);
		
		return (Long) list.get(0);
	}
}
