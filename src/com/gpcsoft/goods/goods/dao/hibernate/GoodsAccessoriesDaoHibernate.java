package com.gpcsoft.goods.goods.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.dao.GoodsAccessoriesDao;
import com.gpcsoft.goods.goods.domain.GoodsAccessories;

@Repository
public class GoodsAccessoriesDaoHibernate extends BaseGenericDaoHibernate<GoodsAccessories> implements GoodsAccessoriesDao {

}
