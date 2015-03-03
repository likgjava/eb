package com.gpcsoft.epp.projrule.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.projrule.dao.MeetRoomDao;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.MeetRoom;
import com.gpcsoft.epp.projrule.manager.MeetRoomManager;

@Repository
public class MeetRoomManagerImpl extends BaseManagerImpl<MeetRoom> implements MeetRoomManager {

	@Autowired(required=true)  @Qualifier("meetRoomDaoHibernate")
	private MeetRoomDao meetRoomDaoHibernate;
  
	/**
	  * 
	  * Description :得到全部预定时间  
	  * Create Date: 2010-5-5下午01:39:12 by liuke  Modified Date: 2010-5-5下午01:39:12 by liuke
	  * @param   
	  * @return  List
	  * @Exception
	  */
	public List getPresetTimeByDate(String startDate,String endDate) {
		List list = meetRoomDaoHibernate.getPresetTimeByDate(startDate,endDate);
		return list;
	}

	
	 /**
	  * 
	  * Description :  通过主键得到标评室信息 
	  * Create Date: 2010-5-5下午05:44:22 by liuke  Modified Date: 2010-5-5下午05:44:22 by liuke
	  * @param   String objId
	  * @return  List
	  * @Exception
	  */
	public List getMeetRoomsByObjId(String objId) {
		List list = meetRoomDaoHibernate.getMeetRoomsByObjId(objId);
		return list;
	}

	 
	 /**
     * 
     * Description : 新增预定时间对象  
     * Create Date: 2010-5-6上午09:48:01 by liuke  Modified Date: 2010-5-6上午09:48:01 by liuke
     * @param   BidRoom bidroom
     * @return  void
     * @Exception
     */
	public BidRoom savePresetTime(BidRoom bidroom) {
		return meetRoomDaoHibernate.savePresetTime(bidroom);
	}
     
	
	 /**
	  * 
	  * Description : 通过包组Id删除原有的预定时间记录 
	  * Create Date: 2010-5-29下午01:30:52 by liuke  Modified Date: 2010-5-29下午01:30:52 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	public void removePresetTimeByProjPackId(String projPackId,String roomType) {
		meetRoomDaoHibernate.removePresetTimeByProjPackId(projPackId,roomType);
	}
	
	/**
	  * 
	  * Description :根据标评室ID和预定时间得到项目信息  
	  * Create Date: 2010-5-31下午02:48:35 by liuke  Modified Date: 2010-5-31下午02:48:35 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	public BidRoom getBidRoomByRoomIdAndTime(String roomId, String startTime,
			String timeBucket) {
		return meetRoomDaoHibernate.getBidRoomByRoomIdAndTime(roomId, startTime, timeBucket);
	}

	 /**
	  * Description:根据项目获取BidRoom
	  * @param projectId
	  * @return
	  * Created at 2011-4-25 15:32 by zhouzhanghe
	  */
	public List<BidRoom> getBidRoomByProjectId(String projectId) {
		return meetRoomDaoHibernate.getBidRoomByProjectId(projectId);
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
	@SuppressWarnings("unchecked")
	public BidRoom getBidRoomByProjectIdAndRoomType(String projectId,String type){
		List<BidRoom> bidRoomList = meetRoomDaoHibernate.getBidRoomByProjectIdAndRoomType(projectId,type);
		return (bidRoomList!=null&&bidRoomList.size()>0)?bidRoomList.get(0):null;
	}
	
}
