package com.gpcsoft.smallscale.groupbuying.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.smallscale.groupbuying.dao.GroupBuyingClassDao;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyingClass;

@Repository
public class GroupBuyingClassDaoHibernate extends BaseGenericDaoHibernate<GroupBuyingClass> implements GroupBuyingClassDao {

	/** 
	 * Description :  获取最小排序序号
	 * Create Date: 2011-6-28上午09:34:16 by likg  Modified Date: 2011-6-28上午09:34:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getMinSort() throws Exception {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				//查询语句
				String hql = "select min(gc.sort) from GroupBuyingClass gc ";
				Query query = session.createQuery(hql);
				Long minSort = (Long) query.uniqueResult();
				if(minSort == null) {
					minSort = 0L;
				}
				
				return minSort;
			}
		});
	}

	/** 
	 * Description :  修改团购分类的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的团购分类的Id	isToUp:排序方向
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateSort(final String objId, final boolean isToUp) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//获取要排序的团购分类
				GroupBuyingClass gbc1 = get(objId);
				Long sort = gbc1.getSort();
				
				StringBuilder hql = new StringBuilder();
				hql.append("from GroupBuyingClass gc where 1=1 ");
				
				//上移
				if(isToUp) {
					hql.append(" and gc.sort < ").append(sort);
					hql.append(" order by gc.sort desc ");
				}
				//下移
				else {
					hql.append(" and gc.sort > ").append(sort);
					hql.append(" order by gc.sort asc ");
				}
				
				//交换两对象的序号
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(0).setMaxResults(1);
				GroupBuyingClass gbc2 = (GroupBuyingClass) query.uniqueResult();
				gbc1.setSort(gbc2.getSort());
				gbc2.setSort(sort);
				
				return null;
			}	
		});
	}

	/** 
	 * Description :  获取团购分组
	 * Create Date: 2011-11-10下午04:46:04 by yucy  Modified Date: 2011-11-10下午04:46:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GroupBuyingClass> getBuyingClassList() throws Exception {
		return (List<GroupBuyingClass>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = " select gbc.GROUP_BUYING_CLASS_ID,gbc.NAME ,gbc.goods_class_id from eps_group_buying_class gbc order by gbc.sort ";
				Query query = session.createSQLQuery(sql);
				List<GroupBuyingClass> dateList = new ArrayList<GroupBuyingClass>();
				for( Object[] objects: (List<Object[]>) query.list()){
					GroupBuyingClass groupBuyingClass = new GroupBuyingClass();
					groupBuyingClass.setObjId((String)objects[0]);
					groupBuyingClass.setName((String)objects[1]);
					GoodsClass goodsClass = new GoodsClass();
					goodsClass.setObjId((String)objects[2]);
					groupBuyingClass.setGoodsClass(goodsClass);
					dateList.add(groupBuyingClass);
				}
				return dateList;
			}	
		});
	}

}
