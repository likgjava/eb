package com.gpcsoft.agreement.order.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderGoodsGiftDao;
import com.gpcsoft.agreement.order.domain.OrderGoodsGift;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class OrderGoodsGiftDaoHibernate extends BaseGenericDaoHibernate<OrderGoodsGift> implements OrderGoodsGiftDao {

}
