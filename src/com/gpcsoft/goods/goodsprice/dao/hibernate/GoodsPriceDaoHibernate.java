package com.gpcsoft.goods.goodsprice.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.goods.goodsprice.enumeration.GoodsPriceEnum;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GoodsPriceDaoHibernate extends BaseGenericDaoHibernate<GoodsPrice> implements GoodsPriceDao {

	/** 
	 * Description :  审核行情
	 * Create Date: 2010-4-16下午02:29:44 by yucy  Modified Date: 2010-4-16下午02:29:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateStopStatus(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String priceId = (String)param.get("priceId");
				String hql = "update GoodsPrice g set g.useStatus= '02' where g.objId in (:priceId )";
				Query query = session.createQuery(hql);
				query.setParameterList("priceId",priceId.split(",") );
				return query.executeUpdate()>0?true:false;
			}
		});
	}

	/** 
	 * Description :  删除行情
	 * Create Date: 2010-4-17上午09:55:50 by yucy  Modified Date: 2010-4-17上午09:55:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean deletePrice(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String priceIds = (String)param.get("priceIds");
				String hql = "delete from GoodsPrice g where g.objId in (:priceIds )";
				String hqlSupplier = "delete from GoodsOptFitPrice gof where gof.goodsPrice.objId in (:priceIds )";

				Query query = session.createQuery(hql);
				query.setParameterList("priceIds",priceIds.split(",") );
				int result1 = query.executeUpdate();
				
				query = session.createQuery(hqlSupplier);
				query.setParameterList("priceIds",priceIds.split(",") );
				int result2 = query.executeUpdate();

				return (result1+result2)>0?true:false;
			}
		});
	}

	/** 
	 * Description : 获得商品的行情 
	 * Create Date: 2010-9-16下午04:17:23 by guoyr  Modified Date: 2010-9-16下午04:17:23 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsPrice> getGoodsPriceList(final Map<String, Object> param) throws Exception{
		return (List<GoodsPrice>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(new Date()));
					StringBuilder sql = new StringBuilder();
					sql.append("select x.* from (");
					sql.append(" select t.goods_price_id,t.dscu_rate,t.prtc_price,t.mrkt_unit_price,t.price_supplier_id,o.org_name,t.district_id ,b.district_name,t.create_time,o.ORG_INFO_ID,t.member_price " +
							" ,(select count(ggp.goods_gift_price_id) from GOODS_GIFT_PRICE ggp where ggp.goods_price_id = t.goods_price_id )"); 
					sql.append(" from goods_price t"); 
					sql.append(" join goods_price_supplier s");
					sql.append(" on t.price_supplier_id = s.goods_price_supplier_id");
					sql.append(" join org_info o");
					sql.append(" on o.org_info_id = s.supplier_id join base_district b on t.district_id = b.district_id");
					sql.append(" where s.is_show = :isShow and");
					sql.append(" o.use_status = :orgUseStatus");
					sql.append(" and o.is_off = :isOff");
					sql.append(" and t.use_status = :priceUseStatus ");
					sql.append(" and s.goods_id = :objId");
					sql.append(" and t.efct_date <= ").append(today); 
					sql.append(" and t.end_date >= ").append(today); 
							      
					sql.append(" order by t.create_time) x");
					sql.append(", (select max(create_time) create_time,price_supplier_id,district_id from goods_price where use_status = :priceUseStatus"); 
					sql.append(" and efct_date <= ").append(today); //sysdate
					sql.append(" and end_date >= ").append(today); 
					sql.append(" group by price_supplier_id,district_id) m");
					
					sql.append(" where x.create_time = m.create_time and x.price_supplier_id = m.price_supplier_id " );
					
					if(StringUtils.hasLength((String)param.get("districtId"))){
						sql.append(" and x.district_id = m.district_id and x.district_id = '"+(String)param.get("districtId")+"'");		//地区条件
					}
					
					//排序
					if(null!=param.get("order")){
						sql.append(" order by x."+param.get("order"));
					}else{
						sql.append(" order by x.prtc_price");
					}
					
					Query query = session.createSQLQuery(sql.toString());
					query.setParameter("objId",(String)param.get("objId"));
					
					query.setParameter("orgUseStatus", OrganizationEnum.USE_VALID);
					query.setParameter("priceUseStatus", GoodsPriceEnum.USE_VALID);
					query.setBoolean("isShow", true);
					query.setBoolean("isOff", true);
					List<String[]> list = query.list();
					List<GoodsPrice> goodsPriceList = new ArrayList<GoodsPrice>();
					for(Object[] obj:list){
						GoodsPrice goodsPrice = new GoodsPrice();
						goodsPrice.setObjId((String)obj[0]);
						goodsPrice.setDscuRate((BigDecimal) obj[1]);
						goodsPrice.setPrtcPrice((BigDecimal) obj[2]);
						goodsPrice.setUnitPrice((BigDecimal) obj[3]);
						OrgInfo supplier = new OrgInfo();
						supplier.setObjId((String) obj[9]);
						supplier.setOrgName((String) obj[5]);
						GoodsPriceSupplier goodsPriceSupplier = new GoodsPriceSupplier();
						goodsPriceSupplier.setSupplier(supplier);
						goodsPrice.setGoodsPriceSupplier(goodsPriceSupplier);
						District district = new District();
						district.setObjId((String) obj[6]);
						district.setName((String) obj[7]);
						goodsPrice.setDistrict(district);
						
						goodsPrice.setMemberPrice((BigDecimal)obj[10]);//会员价
						
						goodsPrice.setOpinion(""+obj[11]);//是否有礼包行情暂存与opinion字段中
						
						goodsPriceList.add(goodsPrice);
					}
					
					return goodsPriceList;
				}
		});
	}
	
	/** 
	 * Description :  取得行情的热门区域和相应的值
	 * Create Date: 2010-10-8下午02:58:53 by yucy  Modified Date: 2010-10-8下午02:58:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getHotTagAndVelue(final Map<String, Object> params)throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String hql = " select g.district.objId, g.district.name,min(g.prtcPrice),max(g.prtcPrice),min(g.memberPrice),max(g.memberPrice) from HotTags t, GoodsPrice g where t.tagsId = g.district.objId and t.tagsType = :tagsType and g.goodsPriceSupplier.goods.objId = :goodsId" +
					" and g.efctDate <= :efctDate"+
					" and g.endDate >= :endDate"+
					" and g.useStatus = :useStatus" +
					" and g.auditStatus = :auditStatus" +
					" group by g.district.name,g.district.objId ";
			Query query = session.createQuery(hql);
			query.setParameter("tagsType", params.get("tagsType"));
			query.setParameter("goodsId", params.get("goodsId"));
			query.setDate("efctDate",new Date());
			query.setDate("endDate",new Date());
			query.setParameter("useStatus", GoodsEnum.USE_VALID);
			query.setParameter("auditStatus", GoodsEnum.PASS_EXAM);
			return query.list();
		}});
	}

	/** 
	 * Description :  取得销售额和销售量的图形
	 * Create Date: 2010-10-9下午05:01:34 by yucy  Modified Date: 2010-10-9下午05:01:34 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ListOrderedMap> getGoodsSalesChartData( final Map<String, Object> param)throws Exception {
		return (List<ListOrderedMap>) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String sql = " select to_char(c.cre_time,'yyyy-MM'), sum(oi.goods_qty),sum(oi.goods_total)"+
						 " from EPS_AGREEMENT_ORDER_ITEM oi,"+
						 " EPS_AGREEMENT_ORDER o,"+
						 " EPS_PUB_CONTRACT c"+
						 " where oi.order_id = o.order_id"+
						 " and c.contract_id = o.contract_id"+
						 " and oi.goods_id = :goodsId"+
						 " and (c.cre_time between :startDate and :endDate )"+
						 " and c.USE_STATUS= '"+getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.pub.domain.Contract.useStatus", "CONFIRM")+"'"+
						 " group by to_char(c.cre_time,'yyyy-MM')";
			
			List<ListOrderedMap> list =   new ArrayList<ListOrderedMap>();
			
			//构造条件(查询当月往前推六个月)
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat f= new SimpleDateFormat("yyyy-MM"); 
			currentDate.set(Calendar.DATE, 1);
			currentDate.add(Calendar.MONTH, -5);
			Date startDate = currentDate.getTime();//计算开始时间
			for (int i = 0; i < 6; i++) {
				ListOrderedMap map = new ListOrderedMap();
				map.put("month", f.format(currentDate.getTime()));
				list.add(map);
				currentDate.add(Calendar.MONTH, 1);
			}
			currentDate.add(Calendar.DATE, -1);
			Date endDate = currentDate.getTime();//计算结束时间
			
			Query query = session.createSQLQuery(sql);
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			query.setParameter("goodsId", (String)param.get("goodsId"));
			//封装数据
			List<Object[]> result = query.list();
			
			for (ListOrderedMap map : list) {
				String  month = (String)map.get("month");
				for(Object[] row:result){
					if(month.equals(row[0])){
						map.put("SellQty", row[1]);
						map.put("SellTotal", row[2]);
					}
				}
				if(map.get("SellQty")==null){
					map.put("SellQty", "0");
				}
				if(map.get("SellTotal")==null){
					map.put("SellTotal", "0");
				}
			}
			return list;
		}});
	}
	
    /* (non-Javadoc)获得提供行情的供应商
     * @see com.gpcsoft.goods.goodsprice.dao.GoodsPriceDao#getProvideSupplierByGoods(java.lang.String)
     */
    @SuppressWarnings("unchecked")
	public List<OrgInfo> getProvideSupplierByGoods(final String goodsIds)  { 
    	return (List<OrgInfo>) getHibernateTemplate().execute(new HibernateCallback(){
    		public Object doInHibernate(Session session) throws HibernateException, SQLException { 
    			String hql = "select gp.goodsPriceSupplier.supplier from GoodsPrice gp where gp.goodsPriceSupplier.goods.objId in (:objIds)";
    			hql += " and gp.efctDate < " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime());
    			hql += " and gp.endDate > " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime());
                Query query = session.createQuery(hql);
                query.setParameterList("objIds", goodsIds.split(","));
                return query.list();
        }});
    }

	/** 
	 * Description :  取的相应区域和相应的值
	 * Create Date: 2010-11-3下午02:24:40 by yucy  Modified Date: 2010-11-3下午02:24:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getHotTagAndVelueByDistrict(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String hql = " select dd.district_id ,dd.district_name,temp.minprice ,temp.maxprice from base_district dd left join "+
			" ( "+
			" select d.district_id, d.district_name,min(p.prtc_price) as minprice,max(p.prtc_price) as maxprice "+
			" from ECP_PUB_TAGS         t, "+
			" goods_price          p, "+
			" goods_price_supplier ps, "+
			" base_district        d "+
			" where t.tags_object_id = p.district_id "+
			" and p.price_supplier_id = ps.goods_price_supplier_id "+
			" and t.tags_object_id = d.district_id "+
			" and ps.goods_id = :goodsId "+
			" and t.tags_object_type = :tagsType "+
			" group by d.district_id ,d.district_name "+
			" ) temp  on temp.district_id like dd.district_id||'%' "+
			" where dd.district_id in (:districtId ) ";
			Query query = session.createSQLQuery(hql);
			query.setParameter("tagsType", param.get("tagsType"));
			query.setParameter("goodsId", param.get("goodsId"));
			query.setParameterList("districtId", ((String)param.get("districtId")).split(","));
			return query.list();
		}});
	}

	/** 
	 * Description :  取得所有行情的区域和值 
	 * Create Date: 2010-11-4上午10:18:11 by yucy  Modified Date: 2010-11-4上午10:18:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllPriceAndValue(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String hql = " select g.district.objId, g.district.name, min(g.prtcPrice), max(g.prtcPrice) from GoodsPrice g where g.goodsPriceSupplier.goods.objId = :goodsId group by g.district.objId, g.district.name ";
			Query query = session.createQuery(hql);
			query.setParameter("goodsId", param.get("goodsId"));
			return query.list();
		}});
	}
}
