package com.gpcsoft.bizplatform.organization.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.bizplatform.organization.dao.OrgQualityDao;
import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class OrgQualityDaoHibernate extends BaseGenericDaoHibernate<OrgQuality> implements OrgQualityDao {

	/** 
	 * Description :  取得以保存的资质（组织内部对象,包括新添加的参数）
	 * Create Date: 2010-8-30上午11:37:11 by yucy  Modified Date: 2010-8-30上午11:37:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getCreateOrUpdateOrgQuality(final String parameter) throws Exception {
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				
				Map<String,Object> resultMap = new HashMap<String, Object>();
				
				OrgQuality orgQuality = get(parameter);
				
				String Detailsql = 
				"select bp.quality_id," +
				" bp.quality_name," +
				" bp.param_type," +
				" bp.level_data," +
				" bp.is_required," +
				" oqd.qualification_detail_id," +
				" oqd.param_value " +
				" from org_qualification oq, base_qualification qp, base_qualification bp " +
				" left join ORG_QUALIFICATION_DETAIL oqd on bp.quality_id = oqd.quality_param_id and oqd.qualification_id = :orgQualityId " +
				" where oq.quality_define_id = qp.quality_id " +
				" and qp.type = '01'" +
				" and oq.qualification_id = :orgQualityId" +
				" and bp.parent_id = qp.quality_id " +
				" order by bp.sort";
				
				Query query = session.createSQLQuery(Detailsql);
				query.setString("orgQualityId", parameter);
				
				List resultList = query.list();
				
				Set<QualificationDetail> qualificationDetailSet = new HashSet<QualificationDetail>();
				List<QualificationDetail> qualificationDetailList = new ArrayList<QualificationDetail>();
				
				for (Object object : resultList) {
					Object[] result = (Object[]) object;
					QualificationDetail qualificationDetail= new QualificationDetail();					
					qualificationDetail.setOrgQuality(orgQuality);
					if(result[5]!=null){
						qualificationDetail.setObjId((String)result[5]);
					}
					if(result[6]!=null){
						qualificationDetail.setParamValue((String)result[6]);
					}
					QualificationParam qualificationParam = new QualificationParam();
					qualificationParam.setObjId((String)result[0]);
					qualificationParam.setQualityName((String)result[1]);
					qualificationParam.setParamType((String)result[2]);
					qualificationParam.setLevel((String)result[3]);
					
					qualificationParam.setIsRequired((result[4]!=null&&!"0".equals((String)result[4].toString()))?true:false);
					
					qualificationDetail.setQualityParam(qualificationParam);
					
					qualificationDetailSet.add(qualificationDetail);
					qualificationDetailList.add(qualificationDetail);				
				}
				
				orgQuality.setQualificationDetailSet(qualificationDetailSet);
				
				resultMap.put("orgQuality", orgQuality);
				resultMap.put("qualificationDetailList", qualificationDetailList);
				
				return resultMap;
			}
		});
	}

	/** 
	 * Description :  取得机构的资质(根据机构id)
	 * Create Date: 2009-4-8下午07:50:19 by yucy  Modified Date: 2009-4-8下午07:50:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<OrgQuality> getOrgQuality(final String objId) throws Exception {
		return (List<OrgQuality>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql = "from OrgQuality q where q.org.objId = :orgId ";
				Query query = session.createQuery(hql);
				query.setString("orgId", objId);
				return query.list();
			}
		});
	}
}
