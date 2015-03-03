package com.gpcsoft.epp.projrule.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projrule.dao.MeetRoomDao;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.MeetRoom;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
@SuppressWarnings("unchecked")
public class MeetRoomDaoHibernate extends BaseGenericDaoHibernate<MeetRoom>
		implements MeetRoomDao {

	/**
	 * 
	 * Description :根据标评室得到预定时间 Create Date: 2010-5-4下午04:40:01 by liuke
	 * Modified Date: 2010-5-4下午04:40:01 by liuke
	 * 
	 * @param
	 * @return List
	 * @Exception
	 */
	public List getPresetTimeByMeetRoomId(String roomId) {
		List list = this.findbyHql(
				"from BidRoom bidroom where bidroom.meetRoom.objId =?", roomId);
		return list;
	}

	/**
	 * 
	 * Description : 得到全部预定时间 Create Date: 2010-5-5下午01:36:40 by liuke Modified
	 * Date: 2010-5-5下午01:36:40 by liuke
	 * 
	 * @param
	 * @return List
	 * @Exception
	 */
	public List getPresetTimeByDate(final String startDate ,final String endDate) {
		List list =	(List) getHibernateTemplate().execute(new HibernateCallback(){
		public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append(" select t.bid_room_id, t.start_date,m.meet_room_id,p.tenderid,p.tenderno,p.tendername,t.USE_STATUS,t.TIME_BUCKET from ecp_bid_room t  ");
				sql.append(" left join ecp_tender_project p on t.proj_pack_id = p.tenderid ");
				sql.append(" left join ecp_meet_room m on t.meeting_room_id = m.meet_room_id ");
				sql.append(" where t.start_date >= "
						+ UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(startDate)
						+ " and t.end_date <= "
						+ UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(endDate));
				return 	session.createSQLQuery(sql.toString()).list();
			}});
			List bidRoomList = new ArrayList();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				BidRoom br = new BidRoom();
				br.setObjId(obj[0].toString());
				br.setStartDate((Date)obj[1]);
				MeetRoom mr = new MeetRoom();
				mr.setObjId(obj[2].toString());
				br.setMeetRoom(mr);
				Project p = new Project();
				if (obj[3]!=null&&!"".equals(obj[3])) {
					p.setObjId(obj[3].toString());
				}
				if (obj[4]!=null&&!"".equals(obj[4])) {
					p.setProjCode(obj[4].toString());
				}
				if (obj[5]!=null&&!"".equals(obj[5])) {
					p.setProjName(obj[5].toString());
				}
				if (obj[6]!=null&&!"".equals(obj[6])) {
					br.setUseStatus(obj[6].toString());
				}
				if (obj[7]!=null&&!"".equals(obj[7])) {
					br.setTimeBucket(obj[7].toString());
				}
				br.setProject(p);
				bidRoomList.add(br);
			}

		return bidRoomList;
	}

	/**
	 * 
	 * Description : 根据标评室名称和时间得到预定时间对象 Create Date: 2010-5-5下午04:28:32 by liuke
	 * Modified Date: 2010-5-5下午04:28:32 by liuke
	 * 
	 * @param String
	 *            roomName, String startdate
	 * @return List
	 * @Exception
	 */
	public List getMeetRoomsByObjId(String objId) {
		List list = this.findbyHql(
				"from MeetRoom meetroom where meetroom.objId =?", objId);
		return list;
	}

	/**
	 * 
	 * Description : 新增预定时间对象 Create Date: 2010-5-6上午09:48:01 by liuke Modified
	 * Date: 2010-5-6上午09:48:01 by liuke
	 * 
	 * @param BidRoom
	 *            bidroom
	 * @return void
	 * @Exception
	 */
	public BidRoom savePresetTime(BidRoom bidroom) {
		this.getHibernateTemplate().save(bidroom);
		return bidroom;
	}

	/**
	 * 
	 * Description : 通过包组Id删除原有的预定时间记录 Create Date: 2010-5-29下午01:30:52 by liuke
	 * Modified Date: 2010-5-29下午01:30:52 by liuke
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	public void removePresetTimeByProjPackId(final String projPackId,
			final String roomType) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql
						.append("delete from ecp_bid_room br where br.bid_room_id in ( ");
				sql
						.append("select t.bid_room_id from ecp_bid_room t left join ecp_tender_project p on t.proj_pack_id = p.tenderid ");
				sql
						.append("left join ecp_meet_room m on t.meeting_room_id = m.meet_room_id where p.tenderid =:projPackId and m.room_type =:roomType)");
				Query query = session.createSQLQuery(sql.toString()).setString(
						"projPackId", projPackId).setString("roomType",
						roomType);
				query.executeUpdate();
				return null;
			}
		});
	}

	/**
	 * 
	 * Description :根据标评室ID和预定时间得到项目信息 Create Date: 2010-5-31下午02:48:35 by liuke
	 * Modified Date: 2010-5-31下午02:48:35 by liuke
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	public BidRoom getBidRoomByRoomIdAndTime(String roomId, String startTime,
			String timeBucket) {
		List list = this
				.findbyHql(
						"from BidRoom bidRoom where bidRoom.meetRoom.objId=? and to_char(bidRoom.startDate,'yyyy-mm-dd')=? and bidRoom.timeBucket=?",
						roomId, startTime, timeBucket);
		if (list.size() > 0) {
			BidRoom bidRoom = (BidRoom) list.get(0);
			return bidRoom;
		}
		return null;
	}

	/**
	 * 
	 * Description :通过预定时间主键删除预定时间 Create Date: 2010-5-31下午02:48:35 by liuke
	 * Modified Date: 2010-5-31下午02:48:35 by liuke
	 * 
	 * @param
	 * @return
	 * @return
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void removePresetTimeByojbId(final String objId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete from BidRoom br where br.objId = '"
						+ objId + "'";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});

	}

	/**
	 * 
	 * Description :根据评标室ID删除预定时间信息 Create Date: 2010-11-12上午11:20:02 by liuke
	 * Modified Date: 2010-11-12上午11:20:02 by liuke
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	public void removePresetTimeByMeetRoomId(final String meetRoomId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete from BidRoom br where br.meetRoom.objId = '"
						+ meetRoomId + "'";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});
	}

	/**
	 * 
	 * FuncName: getMeetRoomListByRoomType Description : 根据房间类型查询标评室
	 * 
	 * @param
	 * @param roomType
	 * @return List<MeetRoom>
	 * @author: liuke
	 * @Create Date:2011-1-17 上午10:28:49
	 * @Modifier: liuke
	 * @Modified Date:2011-1-17 上午10:28:49
	 */
	public List<MeetRoom> getMeetRoomListByRoomType(String roomType) {
		return this
				.findbyHql("from MeetRoom m where m.roomType = ? ", roomType);
	}

	/**
	 * Description:根据项目ID获取BidRoom
	 * 
	 * @param projectId
	 * @return Created at 2011-4-25 15:32 by zhouzhanghe
	 */
	public List<BidRoom> getBidRoomByProjectId(String projectId) {
		List list = this.findbyHql(
				"from BidRoom bidroom where bidroom.project.objId =?",
				projectId);
		return list;
	}
	
	/**
	 * FuncName: getBidRooms
	 * Description :  查找到某评标室某时间段的预订信息
	 * @param 
	 * @param meetroomId
	 * @param startDate
	 * @param endDate
	 * @return List<BidRoom>
	 * @author: shenjz
	 * @Create Date:2011-6-20  上午11:48:51
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-20  上午11:48:51
	 */
	public List<BidRoom> getBidRooms(String meetroomId, String startDate,
			String endDate) {
		List list = this
				.findbyHql(
						"from BidRoom bidroom where bidroom.meetRoom.objId =? " +
						"and " +
						"bidroom.startDate>=to_date('"+ startDate+ "','yyyy-mm-dd hh24:mi:ss') " +
						"and " +
						"bidroom.endDate<=to_date('"+ endDate + "','yyyy-mm-dd hh24:mi:ss')",
						meetroomId);
		return list;
	}
	
	/** 
	 * FuncName : getBidRoomByProjectIdAndRoomType
	 * Description :  根据
	 * Create Date: 2011-10-24下午05:35:10 by yangx  
	 * Modified Date: 2011-10-24下午05:35:10 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List getBidRoomByProjectIdAndRoomType(String projectId,String type){
		return this.findbyHql("from BidRoom b where b.project.objId = '"+projectId+"' and b.meetRoom.roomType='"+type+"'");
	}
}
