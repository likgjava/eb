package com.gpcsoft.goods.goods.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsChangeDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsChange;

@Repository
public class GoodsChangeDaoHibernate extends BaseGenericDaoHibernate<GoodsChange> implements GoodsChangeDao {

	/** 
	 * Description :  获取变更待审核的商品
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsChange> getGoodsAuditList(String goodsId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from GoodsChange t left join fetch t.goods where 1=1 and t.auditStatus='");
		hql.append(OrganizationEnum.AWAIT_EXAM).append("'");
		if(StringUtils.hasLength(goodsId)) {
			hql.append(" and t.goods.objId =:goodsId");
		}
		Query query = this.getSession().createQuery(hql.toString());
		if(StringUtils.hasLength(goodsId)) {
			query.setString("goodsId", goodsId);
		}
		return query.list();
	}
	
	/** 
	 * Description :  获取变更待审核的商品(分页)
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> getGoodsAuditListPage(Page page,Map<String,Object> params) throws Exception {
		StringBuilder hql = new StringBuilder();
		
		hql.append(" from goods t where t.goods_id in (");
		hql.append(" select g.goods_id from goods_change g where g.audit_status= '").append(OrganizationEnum.AWAIT_EXAM).append("' ");
		hql.append(" group by g.goods_id ) ");
		if(StringUtils.hasLength((String)params.get("productName"))) {
			hql.append(" and t.product_name like '%").append((String)params.get("productName")).append("%'");
		}
		
		Query queryCount = this.getSession().createSQLQuery("select count(*) " + hql.toString());
		
		String queryColumns = " goods_id,PICTURE_ID,product_name,product_code,audit_status,sell_status ";
		Query query = this.getSession().createSQLQuery("SELECT " + queryColumns + hql.toString());
		
		query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		Integer totalRow = ((BigDecimal)queryCount.list().get(0)).intValue();
		List list = query.list();
		Page<Goods> pageData = new Page<Goods>(page.getStart(),totalRow,page.getPageSize(),list );
		
		return pageData;	
	}
}
