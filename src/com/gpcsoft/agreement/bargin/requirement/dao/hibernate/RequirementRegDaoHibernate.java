package com.gpcsoft.agreement.bargin.requirement.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.bargin.requirement.dao.RequirementRegDao;
import com.gpcsoft.agreement.bargin.requirement.domain.RequirementReg;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class RequirementRegDaoHibernate extends BaseGenericDaoHibernate<RequirementReg> implements RequirementRegDao {

	/** 
	 * Description :  检查是否报名过
	 * Create Date: 2011-4-1下午03:47:00 by yucy  Modified Date: 2011-4-1下午03:47:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean checkRequirementReged(final Map<String, Object> param)throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " select rg.objId from RequirementReg rg where rg.requirement.objId = :requirementId and rg.regOrg.objId = :orgId ";
					Query query = session.createQuery(Hql);
					query.setParameter("requirementId", param.get("requirementId"));
					query.setParameter("orgId", param.get("orgId"));
					return query.list().size()>0;
				}
		});
	}

	/** 
	 * Description :  更新需求报名的状态
	 * Create Date: 2011-3-11下午05:05:28 by yucy  Modified Date: 2011-3-11下午05:05:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateStatus(final String objIds,final Map<String, Object> param ) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {

				String updateHql = " update RequirementReg r ";
				String columHql = "";
				if(StringUtils.hasLength((String)param.get("auditStatus"))){
					columHql += " set r.auditStatus = '"+(String)param.get("auditStatus")+"' ,r.auditTime = "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
				}
				updateHql += columHql+ " where r.objId in (:objIds)";
				Query query = session.createQuery(updateHql);
				query.setParameterList("objIds", objIds.split(","));
				return query.executeUpdate()>0;
			}
		});
	}

	/** 
	 * Description :  获得报名信息
	 * Create Date: 2011-4-6下午04:22:36 by yucy  Modified Date: 2011-4-6下午04:22:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<RequirementReg> getRequirementRegList(final Map<String, Object> param) throws Exception {
		return (List<RequirementReg>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String requirementIds = (String)param.get("requirement.objId");
				String hql = "from  RequirementReg rg left join fetch rg.regOrg where 1=1 ";
				if(StringUtils.hasLength(requirementIds)){
					hql += " and  rg.requirement.objId  in ( :requirementIds ) ";
				}
				hql += " order  by rg.requirement.objId ";
				Query query = session.createQuery(hql);
				query.setParameterList("requirementIds", requirementIds.split(","));
				return query.list();
			}
		});
	}

	/** 
	 * Description :  校验报名身份证号码的唯一性
	 * Create Date: 2010-11-8下午03:06:40 by yucy  Modified Date: 2010-11-8下午03:06:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean checkedIdCard(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " select r.objId from RequirementReg r where r.idCard = :idCard and r.requirement.objId = :requirementId ";
					Query query = session.createQuery(Hql);
					query.setParameter("idCard",param.get("idCard") );
					query.setParameter("requirementId",param.get("requirementId") );
					return query.list().size()>0?false:true;
				}
		});
	}
}
