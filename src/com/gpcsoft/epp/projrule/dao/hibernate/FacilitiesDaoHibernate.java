
package com.gpcsoft.epp.projrule.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.projrule.dao.FacilitiesDao;
import com.gpcsoft.epp.projrule.domain.Facilities;

/**
 * @author shenjz
 *
 */
@Repository
public class FacilitiesDaoHibernate extends BaseGenericDaoHibernate<Facilities> implements FacilitiesDao {

		
	/**
	 * FuncName: getFacilitiesList
	 * Description :  创建或修改对象
	 * @param 
	 * @param meetRoomId
	 * @return List<Facilities>
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午07:27:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午07:27:16
	 */
	public List<Facilities> getFacilitiesList(String meetRoomId){
		return this.findbyHql("from Facilities f where f.meetRoom.objId = '"+meetRoomId+"'");
	}
	
	/**
	 * FuncName: getFacilitiesList
	 * Description :  删除一个评标室所有设备
	 * @param 
	 * @param meetRoomId
	 * @return List<Facilities>
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午07:27:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午07:27:16
	 */
	@SuppressWarnings("unchecked")
	public void deleteFacilitiesList(final String meetRoomId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="delete from Facilities f where f.meetRoom.objId = '"+meetRoomId+"'";
				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
	}
}
