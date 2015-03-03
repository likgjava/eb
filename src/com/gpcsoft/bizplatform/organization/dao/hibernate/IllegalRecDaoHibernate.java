package com.gpcsoft.bizplatform.organization.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.IllegalRecDao;
import com.gpcsoft.bizplatform.organization.domain.IllegalRec;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;

@Repository
public class IllegalRecDaoHibernate extends BaseGenericDaoHibernate<IllegalRec> implements IllegalRecDao {

	/** 
	 * Description :  获取违法信息
	 * Create Date: 2011-7-13上午12:35:44 by yucy  Modified Date: 2011-7-13上午12:35:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<IllegalRec> getIllegalRec(final Map<String, Object> param)  throws Exception {
		return (List<IllegalRec>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Object orgInfoId = param.get("orgInfoId"); //被评价机构的id
				Boolean isShow = (Boolean) param.get("isShow"); //是否显示
				String hql = "from IllegalRec rec where 1=1 ";
				//如果有机构id
				if(orgInfoId!=null && StringUtils.hasLength(orgInfoId.toString())){
					hql += " and rec.org.objId = '"+orgInfoId.toString()+"'";
				}
				//过滤"是否显示"的状态
				if(isShow != null) {
					hql += " and rec.isShow = :isShow ";
				}
				
				Query query = session.createQuery(hql);
				if(isShow != null) {
					query.setParameter("isShow", isShow);
				}
				return query.list();
			}
		});
	}

}
