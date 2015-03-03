package com.gpcsoft.epp.common.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.common.dao.FactortypeDeDao;
import com.gpcsoft.epp.common.domain.FactortypeDe;
import com.gpcsoft.epp.common.exception.EsException;

@Repository
public class FactortypeDeDaoHibernate extends BaseGenericDaoHibernate<FactortypeDe> implements FactortypeDeDao {

	/**
	 * @Description: 根据指标分类ID,获取下级指标分类记录条数
	 * @param 
	 * @return Integer
	 * @throws Exception
	 * @Create Date 2010-8-10 下午03:44:05 By wanghz
	 */
	public Integer getsubFactortypeDes(final String factortypeId)throws EsException{
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Integer doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = "select count(f.objId) from FactortypeDe as f where f.parent.objId = ? ";
				Long num = (Long) session.createQuery(hql).setString(0, factortypeId).list().get(0);
				return Integer.parseInt(num+"");
			}
		});
	}
}
