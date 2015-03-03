package com.gpcsoft.bizplatform.base.evaluate.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.evaluate.dao.QuotaDao;
import com.gpcsoft.bizplatform.base.evaluate.domain.Quota;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class QuotaDaoHibernate extends BaseGenericDaoHibernate<Quota> implements QuotaDao {

	/** 
	 * Description :  根据组织机构信息(OrgInfo)取得评价指标集合
	 * Create Date: 2010-7-21下午04:14:34 by yucy  Modified Date: 2010-7-21下午04:14:34 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Quota> getQuotaLisstByOrgInfo(final Map<String, Object> params) throws Exception {
		
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="from Quota q where q.type in (:types) order by type";
				Query query = session.createQuery(hql);
				
				List<String> types = new ArrayList<String>();
				
				OrgInfo org= (OrgInfo)params.get("org");
				String buyerId = params.get("buyerId") == null?"":params.get("buyerId").toString();
				String supplierId = params.get("supplierId") == null?"":params.get("supplierId").toString();
				String agencyId = params.get("agencyId") == null?"":params.get("agencyId").toString();
				
				//供应商和采购人在项目中互斥
				if(org.getBuyerId()!=null&&org.getObjId().equals(buyerId)){
					types.add(OrganizationEnum.BUYER);
				}
				if(org.getAgencyId()!=null&&!org.getObjId().equals(agencyId)){
					types.add(OrganizationEnum.AGENT);
				}
				if(org.getSupplierId()!=null&&!org.getObjId().equals(supplierId)){
					types.add(OrganizationEnum.SUPPLIER);
				}
				if(org.getSupervisionId()!=null){
					types.add(OrganizationEnum.SUPERVISE);
				}
				query.setParameterList("types", types);
				return query.list();
		}});
		
		return list;
	}

	/** 
	 * Description :  删除指标项(检查可删性)
	 * Create Date: 2010-7-26下午03:43:19 by yucy  Modified Date: 2010-7-26下午03:43:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public int delQuota(final String[] quotaIdArray) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="delete from Quota q where q.objId in (:quotaIds)";
				Query query = session.createQuery(hql);
				query.setParameterList("quotaIds", quotaIdArray);
				return query.executeUpdate();
		}});
	}

	/** 
	 * Description :  (检查可删性)
	 * Create Date: 2010-9-3上午09:46:22 by yucy  Modified Date: 2010-9-3上午09:46:22 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean checkDelQuota(final String[] quotaIdArray) throws Exception {
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="from Score s where s.quotaId in (:quotaIds)";
				Query query = session.createQuery(hql);
				query.setParameterList("quotaIds", quotaIdArray);
				return query.list();
		}});
		return list.size()==0?true:false;
	}
}
