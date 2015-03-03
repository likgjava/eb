package com.gpcsoft.goods.goodsprice.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceSupplierDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GoodsPriceSupplierDaoHibernate extends BaseGenericDaoHibernate<GoodsPriceSupplier> implements GoodsPriceSupplierDao {

	/** 
	 * Description :  是否有行情供应商
	 * Create Date: 2010-4-15下午03:05:31 by yucy  Modified Date: 2010-4-15下午03:05:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public GoodsPriceSupplier getgoodsPriceSupplierBySupplier(final Map<String, Object> param)throws Exception {
		return (GoodsPriceSupplier) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = "from GoodsPriceSupplier gps where gps.supplier.objId = :objId and gps.goods.objId = :goodsId";
					Query query = session.createQuery(Hql);
					query.setParameter("objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId() );
					query.setParameter("goodsId",(String)param.get("goodsId") );
					List list = query.list();
					return list.size()>0?list.get(0):null;
				}
		});
	}

	/** 
	 * Description :  显示供应商行情/禁止显示供应商行情
	 * Create Date: 2010-4-17上午12:31:27 by yucy  Modified Date: 2010-4-17上午12:31:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateShowStatus( final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = " update GoodsPriceSupplier gps set gps.isShow = :showType where gps.objId = :goodsPriceSupplierId ";
					Query query = session.createQuery(Hql);
					query.setBoolean("showType", ("true".equals((String)param.get("showType"))?false:true));
					query.setParameter("goodsPriceSupplierId",(String)param.get("goodsPriceSupplierId") );
					return query.executeUpdate()>0?true:false;
				}
		});
	}
	
	/** 
	 * Description :  判断供应商是否具有商圈会员角色
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean isMember(Map<String,Object> param) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select m.businessmember_id from ecp_business_member m,org_info o ");
		sql.append(" where m.org_info_id=o.org_info_id and o.company_id in ( ");
		sql.append(" select arr.org_id from auth_org_role arr,auth_role r ");
		sql.append(" where arr.rol_id=r.rol_id and r.rol_type='");
		sql.append(OrganizationEnum.ROLE_BUSINESS_MEMBER).append("' )");
		sql.append(" and m.org_info_id = '").append(param.get("orgInfoId").toString()).append("'");

		Query query = this.getSession().createSQLQuery(sql.toString());

		List<GoodsPriceSupplier> list=query.list();
	
		if(list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/** 
	 * Description :  获取商品列表
	 * Create Date: 2011-2-23上午10:50:45 by likg  Modified Date: 2011-2-23上午10:50:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> getGoodsList(final Page<Goods> page, final Map<String, Object> param) throws Exception {
		return (Page<Goods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Object orgInfoId = param.get("orgInfoId"); //报价供应商的机构id
				Object goodsClassCode = param.get("goodsClassCode"); //商品分类编号
				
				String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(new Date()));
				String sql = "select distinct s.goods_id,g.picture_id,g.product_name from goods_price t,goods_price_supplier s,goods g where 1=1 ";
				sql += "and "+today+" between t.efct_date and t.end_date ";
				if(orgInfoId!=null && StringUtils.hasLength(orgInfoId.toString())){
					sql += "and s.supplier_id = '"+orgInfoId.toString()+"' ";
				}
				if(goodsClassCode!=null && StringUtils.hasLength(goodsClassCode.toString())){
					sql += "and g.goods_class_id like '%"+goodsClassCode.toString()+"%' ";
				}
				sql += "and s.goods_id=g.goods_id ";
				Query query = session.createSQLQuery(sql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				page.setData(query.list());
				
				//查询总数
				query = session.createSQLQuery(" select count(*) from ("+sql+")");
				page.setTotalRowNum(((BigDecimal)query.list().get(0)).longValue());
				
				return page;
			}
		});
	}

	/** 
	 * Description :  获取供应商报价的商品分类列表
	 * Create Date: 2011-2-23上午10:50:45 by likg  Modified Date: 2011-2-23上午10:50:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> getGoodsClassList(final Map<String, Object> param) throws Exception {
		return (List<GoodsClass>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Object orgInfoId = param.get("orgInfoId"); //报价供应商的机构id
				
				String preHql = "select gps.goods.goodsClass from GoodsPriceSupplier gps where 1=1 ";
				StringBuilder hql = new StringBuilder();
				
				if(orgInfoId!=null && StringUtils.hasLength(orgInfoId.toString())){
					hql.append(" and gps.supplier.objId = '").append(orgInfoId.toString()).append("'");
				}
				
				Query query = session.createQuery(preHql + hql);
				
				return query.list();
			}
		});
	}
}
