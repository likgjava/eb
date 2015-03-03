package com.gpcsoft.smallscale.groupbuying.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.smallscale.groupbuying.dao.GroupBuyingDao;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GroupBuyingDaoHibernate extends BaseGenericDaoHibernate<GroupBuying> implements GroupBuyingDao {

	/** 
	 * Description :  获取团购列表数据
	 * Create Date: 2011-6-23下午06:54:06 by likg  Modified Date: 2011-6-23下午06:54:06 by likg
	 * @param   page:分页信息		param:查询参数
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<GroupBuying> getGroupBuyingList(final Page<GroupBuying> page, final Map<String, Object> param) throws Exception {
		return (Page<GroupBuying>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String goodsClassId = (String) param.get("goodsClassId"); //商品分类id
				
//				String queryHql = " from GroupBuying gb join gb.goodsClass gc where 1=1 ";
//				String orderHql = " order by gb.createTime desc ";
//				StringBuilder hql = new StringBuilder();
//				hql.append(" and gb.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
//				hql.append(" and gb.startTime < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
//				hql.append(" and gb.endTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
//				
//				//根据商品分类过滤
//				if(StringUtils.hasLength(goodsClassId)) {
//					hql.append(" and gc.objId = '").append(goodsClassId).append("' ");
//				}
//				
//				Query query = session.createQuery("select gb" + queryHql + hql + orderHql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//		
//				page.setData(query.list());
//				
//				//查询总数
//				String hqlCount = "select count(gb.objId) ";
//				query = session.createQuery(hqlCount + queryHql + hql);
//				page.setTotalRowNum((Long) query.list().get(0));
//				return page;
				
				String column = " select gb.group_buying_id,"+
								" gb.name,"+
								" gb.picture,"+
								" gb.market_price,"+
								" gb.group_price," +
								" gb.group_desc";
				//连接表
				String table = " from eps_group_buying gb, goods_class c"+
				" where c.goods_class_id = gb.goods_class_id";
				
				//选择条件
				StringBuilder condition = new StringBuilder();
				
				condition.append(" and gb.use_status = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				condition.append(" and gb.start_time < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				condition.append(" and gb.end_time > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				
				//根据商品分类过滤
				if(StringUtils.hasLength(goodsClassId)) {
					condition.append(" and c.goods_class_id = '").append(goodsClassId).append("' ");
				}
				
				//排序方式
				String orderSql = " order by gb.CREATE_TIME desc ";
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderSql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<GroupBuying> dataList = new ArrayList<GroupBuying>();
				
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					GroupBuying groupBuying = new GroupBuying();
					groupBuying.setObjId((String)objects[0]);
					groupBuying.setName((String)objects[1]);
					groupBuying.setPicture((String)objects[2]);
					groupBuying.setMarketPrice((BigDecimal)objects[3]);
					groupBuying.setGroupPrice((BigDecimal)objects[4]);
					groupBuying.setDesc((String)objects[5]);
					dataList.add(groupBuying);
				}
				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(gb.group_buying_id) "+table + condition.toString());
				page.setTotalRowNum(new Long(query.list().get(0).toString()));
				return page;
			}
		});
	}

}
