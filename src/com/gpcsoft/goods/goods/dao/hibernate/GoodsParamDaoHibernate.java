package com.gpcsoft.goods.goods.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.dao.GoodsParamDao;
import com.gpcsoft.goods.goods.domain.GoodsParam;

@Repository
public class GoodsParamDaoHibernate extends BaseGenericDaoHibernate<GoodsParam> implements GoodsParamDao {

}
