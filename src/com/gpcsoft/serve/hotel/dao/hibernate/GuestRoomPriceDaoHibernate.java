package com.gpcsoft.serve.hotel.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.serve.hotel.dao.GuestRoomPriceDao;
import com.gpcsoft.serve.hotel.domain.GuestRoomPrice;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GuestRoomPriceDaoHibernate extends BaseGenericDaoHibernate<GuestRoomPrice> implements GuestRoomPriceDao {

	/** 
	 * Description :  获取客房当天的协议价
	 * Create Date: 2010-12-13下午03:54:09 by likg  Modified Date: 2010-12-13下午03:54:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getTodayAgreePrice(final String guestRoomId) throws Exception {
		return (BigDecimal) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				
				StringBuilder hql = new StringBuilder();
				hql.append(" select grp from GuestRoomPrice grp left join grp.guestRoom gr ");
				hql.append(" where gr.objId = '").append(guestRoomId).append("' ");
				hql.append(" and grp.startTime <= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getToday()));;
				hql.append(" and grp.endTime >= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getToday()));;
				hql.append(" order by grp.createTime desc ");
				
				
				Query query = session.createQuery(hql.toString());
				
				GuestRoomPrice guestRoomPrice = null;
				List<GuestRoomPrice> guestRoomPriceList = query.list();
				if(guestRoomPriceList!=null && guestRoomPriceList.size()>0){
					guestRoomPrice = guestRoomPriceList.get(0);
				}
				
				return (guestRoomPrice==null ? new BigDecimal(0) : guestRoomPrice.getAgreePrice());
			}
		});
	}

}
