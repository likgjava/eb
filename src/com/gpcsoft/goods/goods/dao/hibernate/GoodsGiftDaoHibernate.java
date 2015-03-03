package com.gpcsoft.goods.goods.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.dao.GoodsGiftDao;
import com.gpcsoft.goods.goods.domain.GoodsGift;

@Repository
public class GoodsGiftDaoHibernate extends BaseGenericDaoHibernate<GoodsGift> implements GoodsGiftDao {

}
