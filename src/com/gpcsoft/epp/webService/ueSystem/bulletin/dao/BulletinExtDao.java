package com.gpcsoft.epp.webService.ueSystem.bulletin.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.bulletin.domain.Bulletin;

public interface BulletinExtDao extends BaseGenericDao<Bulletin> {

	/** 
	 * Description :  获取公告列表
	 * Create Date: 2011-3-3下午06:07:54 by yangx  Modified Date: 2011-3-3下午06:07:54 by yangx
	 * @param   bulletintype:公告类型,startTime:开始时间,endTime:结束时间,purareacode:采购区域,purcategorycode:采购品目编号,bulletinnum,获取记录条数
	 * @return  String：拼装好的XML字符串
	 * @Exception   
	 */
	public List<Bulletin> getBulletinList(String bulletintype,String startTime,String endTime,String purareacode,String purcategorycode,String bulletinnum,String keyword) throws Exception;
	
	/**
	 * 根据sql语句查询数据
	 * @param sql sql语句
	 * @param params 参数
	 * @return
	 * @author zhouzhanghe at 2011.3.15 15:13
	 */
	public List getResultBySql(String sql);
}
