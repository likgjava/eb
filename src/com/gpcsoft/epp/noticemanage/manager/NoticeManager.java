package com.gpcsoft.epp.noticemanage.manager;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;

public interface NoticeManager extends BaseManager<Notice> {

	/**
	 * Description :  根据查询条件获取通知书
	 * Create Date: 2010-5-19上午11:19:58 by yemx  Modified Date: 2010-5-19上午11:19:58 by yemx
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Notice getNoticeByQueryObject(QueryObject queryObject);
	
	/** 
	 * Description :  根据供应商和标段的一条通知书
	 * Create Date: 2010-6-28下午02:08:25 by wangcl  Modified Date: 2010-6-28下午02:08:25 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Notice getNoticeBySellerAndSubProjectId(String sellerId,String subProjectId);
	
	/**
	 * @Description: 根据包件ID获取所有申报书条目
	 * @param subProjectId 包件ID
	 * @return List<SubProjectMTaskPlanSub>
	 * @throws Exception
	 * @Create Date 2010-7-19 上午11:03:40 By wanghz
	 */
	public List<SubProjectMTaskPlanSub> getSubProjectMTaskPlanSubBySubProjectId(String subProjectId)throws EsException;
	
	/**
	 * FuncName: getListByProjectId
	 * Description :  根据项目Id获取中标通知书
	 * @param 
	 * @param projectId
	 * @return List<Notice>
	 * @author: zhangsh
	 * @Create Date:2011-11-17  上午09:52:12
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-17  上午09:52:12
	 */
	public List<Notice> getListByProjectId(String projectId);
}
