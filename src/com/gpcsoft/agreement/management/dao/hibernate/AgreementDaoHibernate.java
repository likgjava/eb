package com.gpcsoft.agreement.management.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AgreementDao;
import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class AgreementDaoHibernate extends BaseGenericDaoHibernate<Agreement> implements AgreementDao {

	/** 
	 * Description :  取得供应商可以录入行情的区间  
	 * Create Date: 2010-9-26下午05:20:12 by yucy  Modified Date: 2010-9-26下午05:20:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistrictByOrg( final Map<String, Object> param)throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(new Date()));
					String goodsId = (String)param.get("goodsId");
					String orgId = (String)param.get("orgId");
					String Hql ="   select distinct d.district_id ,d.district_name "+
					"  from EPS_AGREE_PURCHASE_GOODS epg"+
					"  left join EPS_AGREE_PURCHASE_GOODSCLASS eapc on epg.agreement_class_id = eapc.agreement_class_id"+
					"  left join EPS_AGREE_PURCHASE_AGREEMENT epa on epg.agreement_id = epa.agreement_id or eapc.agreement_id = epa.agreement_id "+
					"  , EPS_AGREEMENT_PERIOD eap "+
					"  , eps_agreement_area eaa "+
					"  , base_district d"+
					"  where "+
					"  epa.period_id = eap.period_id  and instr( epa.area_ids ,eaa.area_id ) >0 and eaa.area_codes = d.district_code "+
					"  and  epa.supplyer_id = :orgId and epg.Goods_Id = :goodsId"+
					"  and  "+ today +" between eap.begin_date and eap.end_date ";
					Query query = session.createSQLQuery(Hql);
					query.setParameter("orgId",orgId );
					query.setParameter("goodsId",goodsId );
					return query.list();
				}
		});
	}
}
