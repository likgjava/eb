package com.gpcsoft.serve.hotel.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.cms.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.serve.hotel.dao.MeetingRoomDao;
import com.gpcsoft.serve.hotel.domain.MeetingRoom;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class MeetingRoomDaoHibernate extends BaseGenericDaoHibernate<MeetingRoom> implements MeetingRoomDao {

	/** 
	 * Description :  验证会议室编号的唯一性
	 * Create Date: 2010-12-8下午04:13:18 by guoyr  Modified Date: 2010-12-8下午04:13:18 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean isUnique(final String objId,final String hotelId,final String meetingRoomCode){
			return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {	
					StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from MeetingRoom  where meetingRoomCode =:meetingRoomCode");
					sb.append(" and hotel.objId =:hotelId");
					if(StringUtils.hasLength(objId)){
						sb.append(" and objId <>:objId");
					}
					Query query = session.createQuery(sb.toString());
					query.setString("meetingRoomCode", meetingRoomCode);
					query.setString("hotelId", hotelId);
					if(StringUtils.hasLength(objId)){
						query.setString("objId", objId);
					}
					List<Long>  list =  query.list();
					boolean flag = true;
					if(list.get(0) > 0){
						flag = false;
					}
					return flag;
				}
				
			});
		}
	
	/** 
	 * Description : 将会议室的类型和人数冗余到酒店里，便于检索 
	 * Create Date: 2010-12-9下午04:17:44 by guoyr  Modified Date: 2010-12-9下午04:17:44 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public String updateMeetingRoomType(final String hotelId){
		return (String)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select meetingRoomType,meetingNumRang from MeetingRoom  where hotel.objId =:hotelId");
				Query query = session.createQuery(sb.toString());
				query.setString("hotelId", hotelId);
				List<Object[]>  list =  query.list();
				StringBuffer meetingRoomTypeBuf = new StringBuffer();
				
				for(Object[] mrType : list){
					if(null == mrType[0]) continue;
					meetingRoomTypeBuf.append((String)mrType[0] + "-" + (String)mrType[1] + ",");
				}
				
				String meetingRoomType = meetingRoomTypeBuf.toString();
				if(!meetingRoomType.equals("")){
					meetingRoomType = meetingRoomType.substring(0,meetingRoomType.lastIndexOf(","));
					StringBuffer updateType= new StringBuffer("update Hotel set meetingRoomType =:meetingRoomType  where objId =:objId");
					Query updateTypeQuery = session.createQuery(updateType.toString());
					updateTypeQuery.setString("meetingRoomType", meetingRoomType);
					updateTypeQuery.setString("objId", hotelId);
					updateTypeQuery.executeUpdate();
				}
				return meetingRoomType;
			}
			
		});
	}
}
