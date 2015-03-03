package com.gpcsoft.agreement.management.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AgreementGoodsclassDao;
import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.domain.Area;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

@Repository
public class AgreementGoodsclassDaoHibernate extends BaseGenericDaoHibernate<AgreementGoodsclass> implements AgreementGoodsclassDao {

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsclassDao#removeAgreementGoodsclassAndGoods(java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	public int removeAgreementGoodsclassAndGoods(final Map<String , Object> params) {
		int result = 0;
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String[] agreementGoodsclassIds= (String[]) params.get("agreementGoodsclassIds");
				
				String hql="select gc from  AgreementGoodsclass gc ,AgreementGoodsclass gc2 where gc.brand.objId=gc2.brand.objId and gc.goodsClass.objId = gc2.goodsClass.objId and gc2.agreementSecond.agreement.objId = gc.agreement.objId and gc.objId in (:objIds)";
				Query query = session.createQuery(hql);
				query.setParameterList("objIds",agreementGoodsclassIds);
				return query.list();
		}});
		
		if(list.size()<=0){
			
			//先删除商品
			getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql="delete from AgreementGoods ag where ag.agreementGoodsclass.objId in (:objIds)";
					String[] agreementGoodsclassIds= (String[]) params.get("agreementGoodsclassIds");
					Query query = session.createQuery(hql);
					query.setParameterList("objIds", agreementGoodsclassIds);   
					return query.executeUpdate();
			}});
			//再删除分类
			result = (Integer) getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql="delete from AgreementGoodsclass ag where ag.objId in (:objIds)";
					String[] agreementGoodsclassIds= (String[]) params.get("agreementGoodsclassIds");
					Query query = session.createQuery(hql);
					query.setParameterList("objIds", agreementGoodsclassIds);   
					return query.executeUpdate();
			}});
		}
		return result ;	
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsclassDao#getAuthorizedClass(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public Object getAuthorizedClass(final Map<String, Object> params) {
		
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String brandId =  (String) params.get("brandId");
				String goodsClassId  = (String) params.get("goodsClassId");
				String agreementId = (String)params.get("agreementId");
				
				String hql="select distinct ag.agreementSecond.objId," +
						"ag.agreementSecond.supplyer.orgName," +
						"ag.agreementSecond.area.areaName," +
						"ag.agreementSecond.beginDate," +
						"ag.agreementSecond.endDate"+
				" from  AgreementGoodsclass ag where ag.agreementSecond.agreement.objId=:agreementId and ag.goodsClass.objId =:classId and ag.brand.objId = :brandId and ag.agreementType='01'";
				Query query = session.createQuery(hql);
				query.setParameter("agreementId",agreementId);   
				query.setParameter("brandId",brandId);   
				query.setParameter("classId",goodsClassId);   
				
				return query.list();
		}});
		
		//
		List<AgreementSecond> agreementSecondList = new ArrayList<AgreementSecond>();
		for (Object object : list) {
			Object[] objects =(Object[])object;
			
			AgreementSecond agreementSecond  = new AgreementSecond();
			
			agreementSecond.setObjId((String)objects[0]);//设第一个
			
			OrgInfo supplier = new OrgInfo();
			supplier.setOrgName((String)objects[1]);
			agreementSecond.setSupplyer(supplier);//设第二个 
			
			Area area = new Area();
			area.setAreaName((String)objects[2]);
			agreementSecond.setArea(area);//设第二个 

			agreementSecond.setBeginDate((Date)objects[3]);//设置第四个
			agreementSecond.setEndDate((Date)objects[4]);//设置第五个

			agreementSecondList.add(agreementSecond);
		}
		
		return agreementSecondList;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsclassDao#agreementGoodsclassDaoHibernate(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<AgreementGoodsclass> agreementGoodsclassDaoHibernate(final String agreementId) {
		
		//取出agreementClass
		List list = (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//String hql="select ac from AgreementGoodsclass ac where ac.agreement.objId = :agreementId";
				// t.timestamp<=to_date(ac.agreement.modifyTime,'YYYY-MM-DD')";20020-3342>20020-3342..00000
//				String hql=" select distinct ac.objId,ac.goodsClass.objId,ac.goodsClass.goodsClassName,ac.brand.objId,ac.brand.brandName,sum(case when (to_char(g.productDateIssued,'yyyyMMdd')>to_char(ac.agreement.modifyTime,'yyyyMMdd')) then '01' else '00' end)" +
//						" from AgreementGoodsclass ac," +
//						" Goods g" +
//						" where" +
//						" g.goodsClass.objId = ac.goodsClass.objId and" +
//						" g.goodsBrand.objId = ac.brand.objId and" +
//						" ac.agreement.objId = :agreementId" +
//						" group by ac.objId,ac.goodsClass.objId,ac.goodsClass.goodsClassName,ac.brand.objId,ac.brand.brandName";
				
				String hql="select distinct ag.AGREEMENT_CLASS_ID,"+
                " ag.GOODSCLASS_ID,"+
                " gc.GOODS_CLASS_NAME,"+
                " ag.BRAND_ID,"+
                " gb.BRAND_NAME,"+
                " sum(case when to_char(goods1_.PRODUCT_DATE_ISSUED, 'yyyyMMdd') > to_char(a.MODIFY_TIME, 'yyyyMMdd') then '1' else '0' end) "+
				" from EPS_AGREE_PURCHASE_GOODSCLASS ag  left join GOODS  goods1_ on ag.goodsclass_id = goods1_.goods_class_id"+
				" and ag.brand_id = goods1_.goods_brand_id,"+
				" GOODS_CLASS                   gc,"+
				" GOODS_BRAND                   gb,"+
				" EPS_AGREE_PURCHASE_AGREEMENT  a"+
				" where ag.GOODSCLASS_ID = gc.GOODS_CLASS_ID"+
				" and ag.BRAND_ID = gb.GOODS_BRAND_ID"+
				" and ag.AGREEMENT_ID = a.AGREEMENT_ID"+
				" and ag.AGREEMENT_ID = :agreementId "+
				" group by ag.AGREEMENT_CLASS_ID,"+
				" ag.GOODSCLASS_ID,"+
				" gc.GOODS_CLASS_NAME,"+
				" ag.BRAND_ID,"+
				" gb.BRAND_NAME ";
				Query query = session.createSQLQuery(hql);
				query.setString("agreementId", agreementId);
				//查询结果
				List list = query.list();
				//封装AgreementGoodsclassList
				List<AgreementGoodsclass> agreementGoodsclassList = new ArrayList<AgreementGoodsclass>();
				for(Object object :list){
					Object[] objects = (Object[]) object;
					
					AgreementGoodsclass agreementGoodsclass = new AgreementGoodsclass ();
					agreementGoodsclass.setObjId((String)objects[0]);//设置第一属性
					
					GoodsClass goodsClass = new GoodsClass();
					goodsClass.setObjId((String)objects[1]);
					goodsClass.setGoodsClassName((String)objects[2]);
					agreementGoodsclass.setGoodsClass(goodsClass);//设置第二个属性
					
					GoodsBrand goodsBrand = new GoodsBrand();
					goodsBrand.setObjId((String)objects[3]);
					goodsBrand.setBrandName((String)objects[4]);
					agreementGoodsclass.setBrand(goodsBrand);//设置第三个属性
					
					agreementGoodsclass.setHaveNew(objects[5].toString());//设置第四个属性
					
					agreementGoodsclassList.add(agreementGoodsclass);
				}
				return agreementGoodsclassList;
		}});
		return list;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsclassDao#DelGoodsByClassReAddGoods(com.gpcsoft.eps.agreement.management.domain.AgreementGoodsclass)
	 */
	@SuppressWarnings("unchecked")
	public Integer removeGoodsByClassReAddGoods(final AgreementGoodsclass objectService) {
		
		List SecondGoodslist = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String agreementGoodsclassId = objectService.getObjId();
				String hql="select gc from  AgreementGoodsclass gc ,AgreementGoodsclass gc2 where gc.brand.objId=gc2.brand.objId and gc.goodsClass.objId = gc2.goodsClass.objId and gc2.agreementSecond.agreement.objId = gc.agreement.objId and gc.objId=:objId";
				Query query = session.createQuery(hql);
				query.setString("objId",agreementGoodsclassId);
				return query.list();
		}});
		
		//未授权供货商
		if(SecondGoodslist.size()<=0){
			//删掉原有的协议商品
			getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql="delete from AgreementGoods a where a.agreementGoodsclass.objId  = :agreementClassId"; 
					Query query = session.createQuery(hql);
					query.setString("agreementClassId",objectService.getObjId());   
					return query.executeUpdate();
			}});
			
			//批量取出商品
			List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql="select g.objId from Goods g where g.goodsClass.objId = :classId and g.goodsBrand.objId = :brandId"; 
					Query query = session.createQuery(hql);
					
					query.setString("classId",objectService.getGoodsClass()==null?"": objectService.getGoodsClass().getObjId());   
					
					if(null==objectService.getGoodsClass()){
						query.setString("classId", "");
					}else{
						query.setString("classId",objectService.getGoodsClass().getObjId());   
					}
					if(null==objectService.getBrand()){
						query.setString("brandId", "");   
					}else {
						query.setString("brandId", objectService.getBrand().getObjId());   
					}
					return query.list();
			}});
			
			//agreementGoodsclass agreementType goodsclass brand goods discountRatio
			
			//循环保存
			for(Object object:list){
				String goodsId = (String) object;
				
				AgreementGoods agreementGoods = new  AgreementGoods();
				
				agreementGoods.setAgreementGoodsclass(objectService);//第一个属性
				
				agreementGoods.setAgreementType(objectService.getAgreementType());//第二个属性
				
				agreementGoods.setGoodsClass(objectService.getGoodsClass());//第三个属性
				
				agreementGoods.setBrand(objectService.getBrand());//第四个属性
				
				Goods goods = new Goods();
				goods.setObjId(goodsId);
				agreementGoods.setGoods(goods);//第五个属性 
				
				agreementGoods.setDiscountRatio(objectService.getDiscountRatio());//第六个属性
				
				getHibernateTemplate().save(agreementGoods);
			}
			//保存AgreementGoodsClass
			getHibernateTemplate().save(objectService);
			
			//更新agreementModifyTime
			Agreement agreement= objectService.getAgreement();
			agreement.setModifyTime(new Date());
			getHibernateTemplate().save(agreement);
		}
		return SecondGoodslist.size();
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsclassDao#updateClassAndGoodsDiscountRatio(com.gpcsoft.eps.agreement.management.domain.AgreementGoodsclass)
	 */
	@SuppressWarnings("unchecked")
	public void updateClassAndGoodsDiscountRatio( final AgreementGoodsclass objectService) {
		
		//先更新goods折扣率
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="update AgreementGoods a set a.discountRatio=:discountRatio where a.agreementGoodsclass.objId  = :agreementClassId"; 
				Query query = session.createQuery(hql);
				query.setString("agreementClassId",objectService.getObjId());   
				query.setString("discountRatio",objectService.getDiscountRatio().toString());   
				return query.executeUpdate();
		}});
		//再保存goodsClass
		getHibernateTemplate().save(objectService);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsclassDao#delAuthorizedClass(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public Integer removeAuthorizedClass(final Map<String, Object> params) {
		
		List list =  (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="select ag2.objId from AgreementGoodsclass ag, AgreementGoodsclass ag2" +
						" where ag.objId is not ag2.objId and" +
						" ag.goodsClass.objId = ag2.goodsClass.objId and" +
						" ag.brand.objId = ag2.brand.objId and" +
						" ag2.agreementSecond.agreement.objId = ag.agreement.objId and" +
						" ag2.agreementSecond.objId = :agreementSecondId and" +
						" ag.objId=:agreementGoodsClassId"; 
				Query query = session.createQuery(hql);
				query.setString("agreementSecondId", (String)params.get("agreementSecondId"));
				query.setString("agreementGoodsClassId", (String)params.get("agreementGoodsClassId"));
				return query.list();
		}});
		
		for (Object object : list) {
		
			String SecondClassId = (String)object;
			
			AgreementGoodsclass SecondClass = getHibernateTemplate().get(AgreementGoodsclass.class, SecondClassId);
			
			getHibernateTemplate().delete(SecondClass);
			
		}
		
		return list.size();
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.AgreementGoodsclassDao#saveOrUpDateRow(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public void saveOrUpDateRow(final Map<String, Object> params) {
		
		//是否新增
		if(null!=params.get("objId")){
			
		}else {
			//先保存分类
			AgreementGoodsclass agreementGoodsclass = new AgreementGoodsclass();
			GoodsClass goodsClass = new GoodsClass();
			goodsClass.setObjId((String)params.get("goodsClassId"));
			GoodsBrand brand = new GoodsBrand();
			brand.setObjId((String)params.get("brandId"));
			Agreement agreement = new Agreement();
			agreement.setObjId((String)params.get("agreementId"));
			BigDecimal discountRatio = new BigDecimal((String)params.get("discountRatio")); 
			
			agreementGoodsclass.setBrand(brand);
			agreementGoodsclass.setGoodsClass(goodsClass);
			agreementGoodsclass.setAgreementType((String)params.get("agreementType"));
			agreementGoodsclass.setDiscountRatio(discountRatio);
			agreementGoodsclass.setAgreement(agreement);
			
			getHibernateTemplate().save(agreementGoodsclass);
			
			//批量取出商品
			List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql="select g.objId from Goods g where g.goodsClass.objId=:goodsClassId and g.goodsBrand.objId=:brandId";
					Query query = session.createQuery(hql);
					query.setString("goodsClassId", (String)params.get("goodsClassId"));   
					query.setString("brandId", (String)params.get("brandId"));   
					return query.list();
			}});
			//循环保存商品
			for (Object object : list) {
				AgreementGoods agreementGoods = new  AgreementGoods();

				String goodsId = (String)object;
				Goods goods = new Goods();
				goods.setObjId(goodsId);
				
				agreementGoods.setAgreementGoodsclass(agreementGoodsclass);//
				agreementGoods.setBrand(brand);
				agreementGoods.setGoodsClass(goodsClass);
				agreementGoods.setAgreementType((String)params.get("agreementType"));
				agreementGoods.setDiscountRatio(discountRatio);
				agreementGoods.setGoods(goods);
				
				getHibernateTemplate().save(agreementGoods);//保存商品
			}
			
		}
	}

}
