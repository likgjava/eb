package com.gpcsoft.smallscale.vote.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedThingDao;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class VoteAssessedThingDaoHibernate extends BaseGenericDaoHibernate<VoteAssessedThing> implements VoteAssessedThingDao {

	/**
	 * Description :  获取主题下的评选单位
	 * Create Date: 2011-2-21下午02:59:48 by zhaojf  Modified Date: 2011-2-21下午02:59:48 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<VoteAssessedThing> findAssessedThingOfTemplate(final String templateId) {
		return (List<VoteAssessedThing>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" select vat from VoteAssessedThing vat where vat.voteTemplate.objId = :templateObjId order by vat.isRecommended desc, vat.createTime ");
				Query query = session.createQuery(hql.toString());
				query.setString("templateObjId", templateId);
				return query.list();
			}
		});
	}

	/**
	 * Description :  删除主题下的评选单位
	 * Create Date: 2011-2-22下午02:29:36 by zhaojf  Modified Date: 2011-2-22下午02:29:36 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void removeAssessedThingOfTemplate(final String templateId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from VoteAssessedThing vat where vat.voteTemplate.objId = :templateObjId");
				Query query = session.createQuery(hql.toString());
				query.setString("templateObjId", templateId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 是否推荐(isRecommended)、审核
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateIsRecommendedStatus(final Map<String, Object> params) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String useStatus = (String) params.get("status");
				Boolean flag = true;
				if("useStatus".equals(useStatus)){
					String preHql = "update VoteAssessedThing vt set vt.useStatus = '01' where vt.objId = :voteAssessedThingId";
					Query query = session.createQuery(preHql);
					query.setParameter("voteAssessedThingId",(String)params.get("voteAssessedThingId") );
					flag = query.executeUpdate()>0?true:false;
				}else{
					String Hql = " update VoteAssessedThing vt set vt.isRecommended";
					Hql = Hql + " = :isStatusVale  where vt.objId = :voteAssessedThingId ";
					Query query = session.createQuery(Hql);
					query.setBoolean("isStatusVale", ("true".equals((String)params.get("isStatusVale"))?false:true));
					query.setParameter("voteAssessedThingId",(String)params.get("voteAssessedThingId") );
					flag = query.executeUpdate()>0?true:false;
				}
				
				return flag;
			}
		});
	}

	/**
	 * 判断是否存在参选对象
	 */
	@SuppressWarnings("unchecked")
	public Boolean isExitAttender(final Map<String, Object> params) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String templateId = (String) params.get("templateId");
			String attender = (String) params.get("attender");
			StringBuffer sb= new StringBuffer("select count(vat.objId) from VoteAssessedThing vat where vat.voteTemplate.objId = :templateId and vat.attender = :attender");
			
			Query query = session.createQuery(sb.toString());
			query.setString("templateId", templateId);
			query.setString("attender", attender);
			List<Long>  list =  query.list();
			boolean flag = false;
			if(list.get(0) > 0){
				flag = true;
			}
			return flag;
		}
			
		});
	}

	/**
	 * 根据所属机构和品牌名称获取品牌对象
	 */
	@SuppressWarnings("unchecked")
	public GoodsBrand getGoodsBrandByNameAndBelongs(final String belongsId,final String brandName) {
		return (GoodsBrand) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from GoodsBrand b where b.belongsId = :belongsId and b.brandName = :brandName order by b.createTime desc");
				Query query = session.createQuery(hql.toString());
				query.setString("belongsId", belongsId);
				query.setString("brandName", brandName);
				return query.list().size()>0?query.list().get(0):new GoodsBrand();
			}
		});
	}

	/**
	 * 设置排序号
	 */
	@SuppressWarnings("unchecked")
	public Boolean setNumSort(final Map<String, Object> params) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String operClass = (String) params.get("operClass");
			String stringId = (String) params.get("stringId");
			Integer numSort = (Integer) params.get("numSort");//排序号
			StringBuilder hql = new StringBuilder();
			if("VoteAssessedThing".equals(operClass)){//参选对象domain
				hql.append("update VoteAssessedThing vat set vat.assessSort = :numSort where vat.objId = :stringId");
			}
			if("VoteAssesseExpert".equals(operClass)){//评审专家domain
				hql.append("update VoteAssesseExpert t set t.expertSort = :numSort where t.objId = :stringId");
			}
			Query query = session.createQuery(hql.toString());
			query.setLong("numSort", numSort);
			query.setString("stringId", stringId);
			query.executeUpdate();
			return true;
			}
		});
	}
}
