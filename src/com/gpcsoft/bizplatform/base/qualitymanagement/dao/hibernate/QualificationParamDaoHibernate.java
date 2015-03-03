package com.gpcsoft.bizplatform.base.qualitymanagement.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationParamDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class QualificationParamDaoHibernate extends BaseGenericDaoHibernate<QualificationParam> implements QualificationParamDao {

	/** 
	 * Description : 取得资质参数  根据资质定义
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<QualificationParam> getParamByDefine(final Map<String, Object> param) throws Exception {
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String qualificationDefineId = (String)param.get("qualificationDefineId");
				Assert.notNull(qualificationDefineId);
				String hql="from QualificationParam p where p.parent.objId = :qualificationDefineId order by p.sort";
				Query query = session.createQuery(hql);
				query.setParameter("qualificationDefineId", qualificationDefineId);
				return query.list();
		}});
		return list;
	}

	/** 
	 * Description : 查询资质信息详细中引用资质参数的个数 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getQualificationParamCountFromQualificationDetail(String objId) {
		String hql = "select count(objId) from QualificationDetail where qualityParam.objId = '" + objId + "'";
		List<Object> list = getHibernateTemplate().find(hql);
		
		return (Long) list.get(0);
	}

	/** 
	 * Description : 取得资质参数  根据资质分类
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<QualificationParam> getParamByClass(final Map<String, Object> param) throws Exception {
		List<QualificationParam> list = (List<QualificationParam>) getHibernateTemplate().execute(
				new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String qualityClassId = (String)param.get("qualityClassId");
				String qualityClassCode = (String)param.get("qualityClassCode");
				StringBuffer hql = new StringBuffer();
				hql.append("from QualificationParam p where p.parent.parent.type='00'");
				if(StringUtils.hasLength(qualityClassId)){
					hql.append(" and p.parent.parent.objId =:qualityClassId ");
				}
				if(StringUtils.hasLength(qualityClassCode)){
					hql.append(" and p.parent.parent.qualityCode =:qualityClassCode ");
				}
				hql.append(" order by p.sort");
				Query query = session.createQuery(hql.toString());
				
				if(StringUtils.hasLength(qualityClassId)){
					query.setParameter("qualityClassId", qualityClassId);
				}
				if(StringUtils.hasLength(qualityClassCode)){
					query.setParameter("qualityClassCode", qualityClassCode);
				}
				return query.list();
		}});
		return list;
	}
}
