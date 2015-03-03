package com.gpcsoft.agreement.bargin.bidding.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingEliminateSupplierDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingEliminateSupplier;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;

@Repository
public class BiddingEliminateSupplierDaoHibernate extends BaseGenericDaoHibernate<BiddingEliminateSupplier> implements BiddingEliminateSupplierDao {
	/** 
	 * Description :  判断根据项目ID和轮次ID获取剔除供应商信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean isSupplierEliminated(final String projId, final String turnId, final String supplierId) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from BiddingEliminateSupplier s where 1=1");
				if(StringUtils.hasLength(projId)) {
					hql.append(" and s.project.objId = '").append(projId).append("' ");
				}
				if(StringUtils.hasLength(turnId)) {
					hql.append(" and s.barginTurn.objId = '").append(turnId).append("' ");
				}
				if(StringUtils.hasLength(supplierId)) {
					hql.append(" and s.supplier.objId = '").append(supplierId).append("' ");
				}
				Query query = session.createQuery(hql.toString());
				
				return query.list().size() > 0;
			}
		});
	}
	
	/** 
	 * Description :  获取报名供应商和供应商剔除信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getSignupSupplierAndEliminate(final String projId,final String turnId) throws Exception {
		return (ArrayList<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select r.supplyerid,r.supplyername, ");
				sql.append("s.bargain_turn_id endTurn,s.supplier_id endSupp,s.project_id endProj, ");
				sql.append("s.eliminate_reason endReason from ECP_Tender_Apply_Rec r ");
				sql.append("left join (select * from  EPS_AGREE_ELIMINATE_SUPPLYER es ");
				sql.append("where es.project_id=:projId and es.bargain_turn_id=:turnId order by es.eliminate_time desc) s ");
				sql.append("on r.supplyerid = s.supplier_id");
				Query query = session.createSQLQuery(sql.toString());
				query.setString("projId", projId);
				query.setString("turnId", turnId);
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取剔除的供应商
	 * Create Date: 2011-7-4下午04:40:46 by likg  Modified Date: 2011-7-4下午04:40:46 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<BiddingEliminateSupplier> getEliminateSupplier(final Map<String, Object> param) throws Exception {
		return (ArrayList<BiddingEliminateSupplier>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String projId = (String) param.get("projId"); //项目id
				String turnId = (String) param.get("turnId"); //轮次id
				
				StringBuilder hql = new StringBuilder();
				hql.append("select es from BiddingEliminateSupplier es left join es.project p left join es.barginTurn t where 1=1 ");
				
				//过滤条件
				if(StringUtils.hasLength(projId)) {
					hql.append("and p.objId = '").append(projId).append("' ");
				}
				if(StringUtils.hasLength(turnId)) {
					hql.append("and t.objId = '").append(turnId).append("' ");
				}
				
				Query query = session.createQuery(hql.toString());
				return query.list();
			}
		});
	}
}
