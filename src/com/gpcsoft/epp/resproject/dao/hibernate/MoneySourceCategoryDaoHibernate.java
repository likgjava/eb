package com.gpcsoft.epp.resproject.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.resproject.dao.MoneySourceCategoryDao;
import com.gpcsoft.epp.resproject.domain.MoneySourceCategory;

@Repository
public class MoneySourceCategoryDaoHibernate  extends BaseGenericDaoHibernate<MoneySourceCategory> implements MoneySourceCategoryDao {

	/**
	 * 查询资金来源类型集合
	 * @param type 代理机构ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MoneySourceCategory> getMoneySourceCategoryList(String type)throws Exception{
		return this.getHibernateTemplate().find(" from MoneySourceCategory t where t.type='"+type+"'");
	}
	
}
