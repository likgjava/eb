package com.gpcsoft.agreement.management.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AgreementSecondDao;
import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class AgreementSecondDaoHibernate extends BaseGenericDaoHibernate<AgreementSecond> implements AgreementSecondDao {

	@SuppressWarnings("unchecked")
	public Integer saveAgreementSecond(final Map<String, Object> params) {
		int result = 0;
		
		
		AgreementSecond agreementSecond = (AgreementSecond) params.get("agreementSecond");
		
		//先保存二级协议主体
		getHibernateTemplate().save(agreementSecond);
		
		//批量取出类别
		List listClass = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String Hql = "Select agc from AgreementGoodsclass agc where agc.objId in (:objIds)";
				Query query = session.createQuery(Hql);
				String goodsClassId = (String) params.get("goodsClassId");
				query.setParameterList("objIds", goodsClassId.split(","));
				return query.list();
		}});
		
		//批量取出类别下的商品
		List listClassGoods = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String Hql = "Select ag from AgreementGoods ag where ag.agreementGoodsclass.objId in (:objIds)";
				Query query = session.createQuery(Hql);
				String goodsClassId = (String) params.get("goodsClassId");
				query.setParameterList("objIds", goodsClassId.split(","));
				return query.list();
		}});
		
		//批量取出协议下的单品
		List listGoods = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String Hql = "Select ag from AgreementGoods ag where ag.objId in (:objIds)";
				Query query = session.createQuery(Hql);
				String goodsIds = (String) params.get("goodsIds");
				query.setParameterList("objIds", goodsIds.split(","));
				return query.list();
		}});
		
		//批量保存二级协议类别以及其下的商品
		for(Object object :listClass){
			AgreementGoodsclass AgreementGoodsclass = (AgreementGoodsclass) object;
			
			AgreementGoodsclass  agreementGoodsclassSecond  = new AgreementGoodsclass();
			//设置属性
			agreementGoodsclassSecond.setAgreementSecond(agreementSecond);
			agreementGoodsclassSecond.setAgreementType("01");
			agreementGoodsclassSecond.setBrand(AgreementGoodsclass.getBrand());
			agreementGoodsclassSecond.setGoodsClass(AgreementGoodsclass.getGoodsClass());
			agreementGoodsclassSecond.setDiscountRatio(AgreementGoodsclass.getDiscountRatio());
			getHibernateTemplate().save(agreementGoodsclassSecond);
			
			//与商品匹配
			for(Object o:listClassGoods){
				AgreementGoods agreementGoods = (AgreementGoods)o;
				if(agreementGoods.getAgreementGoodsclass().getObjId().equals(AgreementGoodsclass.getObjId())){
					AgreementGoods goods = new AgreementGoods();
					goods.setAgreementGoodsclass(agreementGoodsclassSecond);
					goods.setAgreementType("01");
					goods.setBrand(agreementGoods.getBrand());
					goods.setGoodsClass(agreementGoods.getGoodsClass());
					goods.setGoods(agreementGoods.getGoods());
					getHibernateTemplate().save(goods);
				}
			}
		}
		
		//批量保存二级协议类别的单品
		for(Object  o: listGoods){
			AgreementGoods goods = (AgreementGoods) o;
			
			AgreementGoods goodsSecond = new AgreementGoods();
			
			goodsSecond.setAgreementSecond(agreementSecond);
			goodsSecond.setAgreementType("01");
			goodsSecond.setBrand(goods.getBrand());
			goodsSecond.setGoodsClass(goods.getGoodsClass());
			goodsSecond.setGoods(goods.getGoods());
			
			getHibernateTemplate().save(goodsSecond);
		}
		
		return result;
	}

}
