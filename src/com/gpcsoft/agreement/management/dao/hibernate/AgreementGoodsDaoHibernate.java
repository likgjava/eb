package com.gpcsoft.agreement.management.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AgreementGoodsDao;
import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.domain.Area;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class AgreementGoodsDaoHibernate extends BaseGenericDaoHibernate<AgreementGoods> implements AgreementGoodsDao {

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsDao#getAuthorizedGoods(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getAuthorizedGoods(final Map<String, Object> params) {
		
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String goodsId =  (String) params.get("goodsId");
				String agreementId = (String)params.get("agreementId");
				
				String hql=" select distinct ag.agreementSecond.objId," +
								"ag.agreementSecond.supplyer.orgName," +
								"ag.agreementSecond.area.areaName," +
								"ag.agreementSecond.beginDate," +
								"ag.agreementSecond.endDate"+
						" from AgreementGoods ag where" +
						" ag.agreementType='01'" +
						" and ag.goods.objId=:goodsId" +
						" and ag.agreementSecond.agreement.objId=:agreementId";
				Query query = session.createQuery(hql);
				query.setParameter("goodsId",goodsId);   
				query.setParameter("agreementId",agreementId);   
				
				return query.list();
		}});
		
		List<AgreementSecond> agreementSecondList = new ArrayList<AgreementSecond>();
		for(Object object: list){
			Object[] objects =(Object[])object;
			
			AgreementSecond agreementSecond  = new AgreementSecond();
			
			agreementSecond.setObjId((String)objects[0]);//设第一个
			
			OrgInfo supplier = new OrgInfo();
			supplier.setOrgName((String)objects[1]);
			agreementSecond.setSupplyer(supplier);//设第二个 
			
			Area area = new Area();
			area.setAreaName((String)objects[2]);
			agreementSecond.setArea(area);//设第三个 

			agreementSecond.setBeginDate((Date)objects[3]);//设置第四个
			agreementSecond.setEndDate((Date)objects[4]);//设置第五个

			agreementSecondList.add(agreementSecond);
			
		}
		
		return agreementSecondList;
	}

	@SuppressWarnings("unchecked")
	public Integer removeAuthorizedGoods(final Map<String, Object> params) {
		
		List list =  (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="select ag2.objId from AgreementGoods ag, AgreementGoods ag2" +
						" where ag.objId is not ag2.objId and" +
						" ag.goodsClass.objId = ag2.goodsClass.objId and" +
						" ag.brand.objId = ag2.brand.objId and" +
						" ag.goods.objId = ag2.goods.objId and" +
						" ag2.agreementSecond.agreement.objId = ag.agreement.objId and" +
						" ag2.agreementSecond.objId = :agreementSecondId and" +
						" ag.objId=:agreementGoodsId"; 
				Query query = session.createQuery(hql);
				query.setString("agreementSecondId", (String)params.get("agreementSecondId"));
				query.setString("agreementGoodsId", (String)params.get("agreementGoodsId"));
				return query.list();
		}});
		
		for (Object object : list) {
		
			String secondGoodsId = (String)object;
			
			AgreementGoods secondGoods = getHibernateTemplate().get(AgreementGoods.class, secondGoodsId);
			
			getHibernateTemplate().delete(secondGoods);
			
		}
		
		return list.size();
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsDao#removeGoods(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public Integer removeGoods(final Map<String, Object> params) {
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String[] goodsIds= (String[]) params.get("goodsIds");
				String hql="select ag.objId from  AgreementGoods ag ,AgreementGoods ag2 where ag.brand.objId=ag2.brand.objId and ag.goodsClass.objId = ag2.goodsClass.objId and ag2.agreementSecond.agreement.objId = ag.agreement.objId and ag.objId in (:objIds)";
				Query query = session.createQuery(hql);
				query.setParameterList("objIds",goodsIds);
				return query.list();
		}});
		
		if(list.size()<=0){
			String[] goodsIds= (String[]) params.get("goodsIds");
			for (String goodsId : goodsIds) {
				getHibernateTemplate().delete(getHibernateTemplate().get(AgreementGoods.class, goodsId));
			}
		}
		return list.size();
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsDao#getBrandSelectParam(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getBrandSelectParam(final Map<String, Object> params) {
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String goodsClassId = (String) params.get("goodsClassId");
				String goodsBrandId = (String) params.get("goodsBrandId");
				String sql = "";
				if(null!=goodsClassId&&null==goodsBrandId){
					sql="Select Gbc.Goods_Brand_Id, Gb.Brand_Name From Goods_Brand_To_Class Gbc, Goods_Brand Gb Where Gbc.Goods_Brand_Id = Gb.Goods_Brand_Id And Gbc.Goods_Class_Id='"+goodsClassId+"'";
				}else{
					sql="Select Gb.Goods_Class_Id ,gc.Goods_Class_Name From Goods_Brand_To_Class Gb, Goods_Class gc Where Gb.Goods_Class_Id = gc.Goods_Class_Id And Gb.Goods_Brand_Id='"+goodsBrandId+"'";
				}
				Query query =  session.createSQLQuery(sql);
				return query.list();
		}});
		return list;
	}

	/** 
	 * Description :   获取行情商品展示的列表数据(协议供货)
	 * Create Date: 2009-4-13上午11:04:42 by yucy  Modified Date: 2009-4-13上午11:04:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> getPriceGoodsListByAgree(final Page<Goods> page,final Map<String, Object> param) throws Exception{
			return (Page<Goods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
				//查询条件
				String whereHql  = " and aa.supplyer_id = :supplierId and g.manger_status = :useStatus and g.sell_status = :sellStatus ";
				if(null!=param.get("productName")&&!"".equals((String)param.get("productName"))){
					whereHql += " and g.product_name like '%"+(String)param.get("productName")+"%'";
				}
				if(null!=param.get("brandName")&&!"".equals((String)param.get("brandName"))){
					whereHql += " and gb.brand_name like '%"+(String)param.get("brandName")+"%'";
				}
				if(null!=param.get("className")&&!"".equals((String)param.get("className"))){
					whereHql += " and c.goods_class_name like '%"+(String)param.get("className")+"%'";
				}
				
				//排序条件()
				String orderHql = "";
				if("productName".equals((String)param.get("order"))){
					orderHql = " order by g.product_name " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
				}else if("productCode".equals((String)param.get("order"))){
					orderHql = " order by g.product_code " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
				}else if("goodsBrand.brandName".equals((String)param.get("order"))){
					orderHql = " order by gb.brand_name " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
				}else if("goodsClass.goodsClassName".equals((String)param.get("order"))){
					orderHql = " order by c.goods_class_name " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
				}else if("remark".equals((String)param.get("order"))){
					orderHql = " order by countPrice " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
				}
				
				String reslutSql = "select * from " +
					"(select distinct g.goods_id,g.product_name,g.picture_id,g.product_code,gb.brand_name, "+
					"(select count(gp.GOODS_PRICE_ID) from GOODS_PRICE gp, GOODS_PRICE_SUPPLIER gps "+
					"where gp.PRICE_SUPPLIER_ID = gps.GOODS_PRICE_SUPPLIER_ID "+
					"and gps.GOODS_ID = g.GOODS_ID "+
					"and gp.USE_STATUS = :useStatus "+
					"and gps.SUPPLIER_ID = :supplierId "+
					"and gp.EFCT_DATE <= :currentDate "+
                    "and gp.END_DATE >= :currentDate) as countPrice, "+
                    "c.goods_class_name "+
                    "from goods g, "+
                    "GOODS_BRAND gb, "+
                    "goods_class c, "+
                    "EPS_AGREE_PURCHASE_GOODS ag, "+
                    "EPS_AGREE_PURCHASE_GOODSCLASS agc, "+
                    "EPS_AGREE_PURCHASE_AGREEMENT aa "+
                    "where g.goods_brand_id = gb.goods_brand_id and g.goods_id = ag.goods_id and g.goods_class_id = c.goods_class_id "+
                    "and ((ag.agreement_class_id = agc.agreement_class_id and agc.agreement_id = aa.agreement_id) "+
                    "or (ag.agreement_id is not null and  ag.agreement_id = aa.agreement_id)) "+
                    whereHql+orderHql+")";
			
				String countSql = " select count(distinct g.goods_id) "+
					"from goods g,GOODS_BRAND gb,goods_class c,EPS_AGREE_PURCHASE_GOODS ag,EPS_AGREE_PURCHASE_GOODSCLASS agc,EPS_AGREE_PURCHASE_AGREEMENT aa "+
					"where g.goods_brand_id = gb.goods_brand_id and g.goods_id = ag.goods_id and g.goods_class_id = c.goods_class_id "+
					"and ((ag.agreement_class_id = agc.agreement_class_id and agc.agreement_id = aa.agreement_id) or (ag.agreement_id is not null and  ag.agreement_id = aa.agreement_id)) "+
					whereHql;
					
					//查询结果
					Query query = session.createSQLQuery(reslutSql);
					query.setParameter("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
					query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
					query.setDate("currentDate", new Date());
					query.setParameter("useStatus",GoodsEnum.USE_VALID );//有效
					query.setParameter("sellStatus",GoodsEnum.SELL_START );//启卖

					List<Goods> goodsList = new ArrayList<Goods>();
					for (Object object : query.list()) {
						Object[] objects = (Object[])object;
						Goods goods = new Goods();
						goods.setObjId((String)objects[0]);
						goods.setProductName((String)objects[1]);
						goods.setPicture((String)objects[2]);
						goods.setProductCode((String)objects[3]);
						
						GoodsBrand goodsBrand = new GoodsBrand();
						goodsBrand.setBrandName((String)objects[4]);
						goods.setGoodsBrand(goodsBrand);
						
						goods.setRemark(""+objects[5]);
						
						GoodsClass goodsClass = new GoodsClass();
						goodsClass.setGoodsClassName(""+objects[6]);
						goods.setGoodsClass(goodsClass);
						
						goodsList.add(goods);
					}
					page.setData(goodsList);
					
					//查询记录数
					query = session.createSQLQuery(countSql);
					query.setParameter("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
					query.setParameter("useStatus",GoodsEnum.USE_VALID );//有效
					query.setParameter("sellStatus",GoodsEnum.SELL_START );//启卖
					page.setTotalRowNum(((BigDecimal)query.list().get(0)).longValue());
					return page;
				}
		});
	}

	/** 
	 * Description :  获取行情商品协议数据(折扣率，单价，协议价等等)
	 * Create Date: 2011-12-6下午02:45:02 by yucy  Modified Date: 2011-12-6下午02:45:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public AgreementGoods getAgreementGoodsByParam(final Map<String, Object> param)throws Exception {
		return (AgreementGoods) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String goodsId = (String) param.get("goodsId");
				String orgId = (String) param.get("orgId");
				String sql = " select apg1.* "+
							  " from eps_agree_purchase_goods apg1 ,eps_agree_purchase_agreement apa1"+
							  " where  apg1.agreement_id = apa1.agreement_id"+
							  " and apa1.supplyer_id = '"+orgId+"'"+
							  " and apg1.goods_id = '"+goodsId+"'"+
							  " union "+
							  " select apg2.* from "+
							  " eps_agree_purchase_goods apg2 ,"+
							  " eps_agree_purchase_goodsclass apc,"+
							  " eps_agree_purchase_agreement apa2"+
							  " where apg2.agreement_class_id = apc.agreement_class_id"+
							  " and apc.agreement_id = apa2.agreement_id"+
							  " and apa2.supplyer_id = '"+orgId+"'"+
							  " and apg2.goods_id = '"+goodsId+"' ";
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.addEntity(AgreementGoods.class);
				return sqlQuery.uniqueResult();
		}});
	}

	/** 
	 * Description :  获取行情商品列表
	 * Create Date: 2011-12-6下午02:45:02 by yucy  Modified Date: 2011-12-6下午02:45:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<AgreementGoods> getAgreementGoodsListByParam(final Map<String, Object> param) throws Exception {
		return (List<AgreementGoods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String agreementId = (String) param.get("agreementId");
				String agreementClassId = (String) param.get("agreementClassId");
				String sql = " select apg.* from eps_agree_purchase_goods apg where 1=1 ";
				if(StringUtils.hasLength(agreementId)){
					sql += " and apg.agreement_id = '"+agreementId+"'";
				}
				if(StringUtils.hasLength(agreementClassId)){
					sql += " and apg.agreement_class_id = '"+agreementClassId+"'";
				}
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.addEntity(AgreementGoods.class);
				return sqlQuery.list();
		}});
	}
}
