package com.gpcsoft.smallscale.pointmall.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.smallscale.pointmall.dao.VirtualGiftRecordDao;
import com.gpcsoft.smallscale.pointmall.domain.VirtualGiftRecord;

@Repository
public class VirtualGiftRecordDaoHibernate extends BaseGenericDaoHibernate<VirtualGiftRecord> implements VirtualGiftRecordDao {

	/** 
	 * Description :   获得记录列表
	 * Create Date: 2011-1-16下午11:43:43 by yucy  Modified Date: 2011-1-16下午11:43:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<VirtualGiftRecord> getVirtualListByGiftId(final String objId) throws Exception {
		return (List<VirtualGiftRecord>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = " from VirtualGiftRecord where gift.objId = :objId and dealStatus=:dealStatus ";
					Query query = session.createQuery(Hql);
					query.setParameter("objId",objId );
					query.setParameter("dealStatus",SmallscaleEnum.DEAL_STATUS_RECEIVE );
					return query.list();
				}
		});
	}

}
