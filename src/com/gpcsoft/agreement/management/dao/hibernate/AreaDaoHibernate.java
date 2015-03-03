package com.gpcsoft.agreement.management.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AreaDao;
import com.gpcsoft.agreement.management.domain.Area;
import com.gpcsoft.agreement.management.enumeration.AgreementEnum;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class AreaDaoHibernate extends BaseGenericDaoHibernate<Area> implements AreaDao {

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AreaDao#removeArea(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Integer removeArea(final String areaId) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				int match = areaId.indexOf("0000")>0?6:( areaId.indexOf("00")>0?8:10 );
				String matchStr = areaId.substring(0, match);
				String sql="select area.area_id from eps_agree_purchase_agreement agreement, eps_agreement_area area where area.area_id = agreement.area_id and area.area_id like'"+
				matchStr+"%' union all select area.area_id from eps_agreement_purchase_second agreementsecond, eps_agreement_area area where area.area_id = agreementsecond.area_id and area.area_id like '"+matchStr+"%'";
				Query query = session.createSQLQuery(sql);
				if(query.list().size()<=0){
					String deleteSql = " update eps_agreement_area a set a.area_is_valid = '" + AgreementEnum.AREA_DELETED +"' where a.area_id like '"+matchStr+"%' ";
					query = session.createSQLQuery(deleteSql);
					return query.executeUpdate();
				}
				return 0;
		}});
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AreaDao#getAreaInfo(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getAreaInfo(final Map<String, Object> params) {
		Map<String, Object> AreaInfo = new HashMap<String, Object>();
		Area area = getHibernateTemplate().get(Area.class, (String)params.get("objId"));
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql="Select area.Area_Id From Eps_Agree_Purchase_Agreement Agreement, Eps_Agreement_Area Area Where Area.Area_Id = Agreement.Area_Id And area.Area_Id='"+
				(String)params.get("objId")+"' Union All Select area.Area_Id From eps_agreement_purchase_second Agreementsecond, Eps_Agreement_Area Area Where Area.Area_Id = Agreementsecond.Area_Id And area.Area_Id='"+(String)params.get("objId")+"'";
				Query query = session.createSQLQuery(sql);
				return query.list();
		}});
		
		boolean isModify = false;
		boolean isDelete = false;

		if(list.size()<=0){
			isModify = true;
			isDelete = true;
		}
		AreaInfo.put("area", area);
		AreaInfo.put("isModify", isModify);
		AreaInfo.put("isDelete", isDelete);
		return AreaInfo;
	}

	public Object getAreaTreeById(Map<String, Object> params) {
		String id = (String)params.get("id");
		String isValid = (String)params.get("isValid");
		String hql;
		if(id==null){
			hql=" from Area where parent is null "+ (isValid!=null?"and isValid!= '"+isValid+"'":"") +" order by sort";
		}
		else{
			hql=" from Area where parent.objId ='"+id+"' "+ (isValid!=null?"and isValid!= '"+isValid+"'":"") +" order by sort";
		}
		return getHibernateTemplate().find(hql);	
	}

	/** 
	 * Description :  根据是否有效取到区间和区域信息
	 * Create Date: 2011-12-1下午02:24:38 by yucy  Modified Date: 2011-12-1下午02:24:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAreaList(final String isValid) throws Exception {
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String sql=" select  a.area_id, d.district_id from eps_agreement_area a, base_district d where a.area_codes = d.district_code and a.area_is_valid = '" + isValid + "' ";
				Query query = session.createSQLQuery(sql);
				return query.list();
		}});
	}

	/** 
	 * Description :  保存区间（批量）
	 * Create Date: 2011-12-2上午11:39:01 by yucy  Modified Date: 2011-12-2上午11:39:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean saveAreaBatch(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				int result = 0;
				
				String validArea = (String)param.get("validArea");
				String invalidArea = (String)param.get("invalidArea");
				
				List<String> validUpdateArea = new ArrayList<String>();
				List<String> invalidUpdateArea = new ArrayList<String>();
				
				//先排除已经保存过的
				String ruleOut=" select aa.area_codes from eps_agreement_area aa where aa.area_codes in ( :saveArea ) ";
				Query query = session.createSQLQuery(ruleOut);
				query.setParameterList("saveArea", (validArea + ","+invalidArea).split(",") );
				for( String saveAreaId:(List<String>)query.list()){
					if( StringUtils.hasLength(validArea) ){
						//如果数据中已经存在,且这次需要启用,状态置为1
						if(validArea.indexOf(saveAreaId)>=0 )	validUpdateArea.add(saveAreaId);
						validArea = validArea.replace(","+saveAreaId, "").replace(saveAreaId+",", "").replace(saveAreaId, "");
					}
					if( StringUtils.hasLength(invalidArea) ){
						//如果数据中已经存在,且这次不启用,状态置为0
						if(invalidArea.indexOf(saveAreaId)>=0 )	invalidUpdateArea.add(saveAreaId);
						invalidArea = invalidArea.replace(","+saveAreaId, "").replace(saveAreaId+",", "").replace(saveAreaId, "");
					}
				}
				if(validUpdateArea.size()>0){
					String validUpdateAreaSql = " update eps_agreement_area a set a.area_is_valid = '"+AgreementEnum.AREA_VALID+"' where a.area_codes in (:validUpdateArea ) ";
					query = session.createSQLQuery(validUpdateAreaSql);;
					query.setParameterList("validUpdateArea",validUpdateArea );
					result += query.executeUpdate();
				}
				if(invalidUpdateArea.size()>0){
					String invalidUpdateAreaSql = " update eps_agreement_area a set a.area_is_valid = '"+AgreementEnum.AREA_INVALID+"' where a.area_codes in (:invalidUpdateArea ) ";
					query = session.createSQLQuery(invalidUpdateAreaSql);;
					query.setParameterList("invalidUpdateArea",invalidUpdateArea );
					result += query.executeUpdate();
				}
				
				if(StringUtils.hasLength(validArea)){
					String validAreaSql=" insert into eps_agreement_area a " +
					" select 'AREA'||d.district_id," +
					"d.district_name," +
					"d.district_code," +
					"d.district_name," +
					"case when d.district_parent_id is null then null else 'AREA'||d.district_parent_id end," +
					"d.district_name," +
					"d.sort,d.is_leaf," +
					"'1' from base_district d where d.district_id in ( :validArea ) ";
					query = session.createSQLQuery(validAreaSql);
					query.setParameterList("validArea", validArea.split(","));
					result += query.executeUpdate();
				}
				if(StringUtils.hasLength(invalidArea)){
					String invalidAreaSql=" insert into eps_agreement_area a " +
					" select 'AREA'||d.district_id," +
					"d.district_name," +
					"d.district_code," +
					"d.district_name," +
					"case when d.district_parent_id is null then null else 'AREA'||d.district_parent_id end," +
					"d.district_name," +
					"d.sort,d.is_leaf," +
					"'0' from base_district d where d.district_id in ( :invalidArea ) ";
					query = session.createSQLQuery(invalidAreaSql);
					query.setParameterList("invalidArea", invalidArea.split(","));
					result += query.executeUpdate();
				}
				//整理一遍isleaf属性
				if(result>0){
					String updateIsleaf=" update eps_agreement_area a set a.area_is_leaf = (select (case when count(aa.area_id) >0 then '0' else '1' end)   from eps_agreement_area aa where aa.parent_id = a.area_id) ";
					query = session.createSQLQuery(updateIsleaf);
					result += query.executeUpdate();
				}
				return result > 0;
		}});
	}
}
