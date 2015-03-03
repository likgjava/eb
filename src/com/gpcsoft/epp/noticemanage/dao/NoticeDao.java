package com.gpcsoft.epp.noticemanage.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.noticemanage.domain.NoticeAffirmRecord;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.srplatform.auth.domain.User;

public interface NoticeDao extends BaseGenericDao<Notice> {

	/**
	 * @Description: 根据包件ID获取所有申报书条目
	 * @param subProjectId 包件ID
	 * @return List<SubProjectMTaskPlanSub>
	 * @throws Exception
	 * @Create Date 2010-7-19 上午11:03:40 By wanghz
	 */
	public List<SubProjectMTaskPlanSub> getSubProjectMTaskPlanSubBySubProjectId(String subProjectId)throws EsException;
	/** 
	 * Description :  根据项目Id和采购人获取通知书
	 * Create Date: 2010-8-1上午11:59:22 by yangx  Modified Date: 2010-8-1上午11:59:22 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Notice> getListByBuyer(String projectId,String orgInfoId);
	
	/** 
	 * Description :  根据项目Id、供应商Id查询待确认的通知书
	 * Create Date: 2010-8-30上午11:57:47 by yangx  Modified Date: 2010-8-30上午11:57:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Notice> getListBySupplier(String projectId,String orgInfoId);
	
	/**
	 * FuncName: getListByProjectId
	 * Description :  根据项目id查询所有通知书
	 * @param 
	 * @param projectId
	 * @return List<Notice>
	 * @author: zhangsh
	 * @Create Date:2011-11-17  上午09:05:26
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-17  上午09:05:26
	 */
	public List<Notice> getListByProjectId(String projectId);
	
	/**
	 * FuncName: getNoticeListByQueryObject
	 * Description : 根据QueryObject对象查询通知书列表
	 * @param 
	 * @param queryObject
	 * @return List
	 * @author: liuke
	 * @Create Date:2011-1-28  下午04:59:19
	 * @Modifier: liuke
	 * @Modified Date:2011-1-28  下午04:59:19
	 */
	public List getNoticeListByQueryObject(QueryObject queryObject);
	
	
	
	
	
	
	/**
	 * FuncName: getNoticePageByQueryObject
	 * Description : 根据QueryObject对象查询通知书Page列表
	 * @param 
	 * @param queryObject
	 * @param page
	 * @return Page
	 * @author: liuke
	 * @Create Date:2011-1-30  上午09:36:00
	 * @Modifier: liuke
	 * @Modified Date:2011-1-30  上午09:36:00
	 */
	public Page getNoticePageByQueryObject(QueryObject queryObject,Page page);
	
	/**
	 * FuncName: getNotice
	 * Description : 根据项目id和供应商id获取通知书
	 * @param 
	 * @return Notice
	 * @author: shenjz
	 * @Create Date:2011-3-31  下午08:18:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-31  下午08:18:31
	 */
	public Notice getNoticeBy(String projectId);
}
