package com.gpcsoft.epp.noticemanage.service;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.noticemanage.domain.NoticeAffirmRecord;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.srplatform.auth.domain.User;

public interface NoticeService extends BaseGenericService<Notice> {

	/** 
	 * Description :  保存通知书以及通知书内容
	 * Create Date: 2010-9-19下午04:46:19 by yangx  Modified Date: 2010-9-19下午04:46:19 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Notice saveNoticeAndAttachment(Notice notice);
	
	/**
	 * 
	 * Description :  保存供应商确认
	 * Create Date: 2010-5-28上午01:31:11 by yemx  Modified Date: 2010-5-28上午01:31:11 by yemx
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Notice saveNoticeAffirm(String noticeId,NoticeAffirmRecord noticeAffirmRecord);
	
	/** 
	 * Description :  创建或得到一条通知书
	 * Create Date: 2010-6-28下午02:02:02 by wangcl  Modified Date: 2010-6-28下午02:02:02 by wangcl
	 * @param   winnerId 中标结果id
	 * @param   subProjectId 标段id
	 * @return  
	 * @Exception   
	 */
	public Notice getNoticeBySelllerIdAndSubProjectId(String selllerId,String subProjectId) throws Exception;
	
	/** 
	 * Description :  提交通知书
	 * Create Date: 2010-6-28下午05:33:30 by wangcl  Modified Date: 2010-6-28下午05:33:30 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Notice saveSubmitNoticeFile(Notice notice);
	
	/** 
	 * Description :  根据项目Id、供应商Id查询待确认的通知书
	 * Create Date: 2010-8-30上午11:57:47 by yangx  Modified Date: 2010-8-30上午11:57:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Notice> getListBySupplier(String projectId,String orgInfoId);
	
	/** 
	 * Description :  根据id获取通知书 加载附件内容
	 * Create Date: 2010-7-12下午01:41:16 by wangcl  Modified Date: 2010-7-12下午01:41:16 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Notice getNotice(String id);
	
	/** 
	 * Description :  根据项目Id和采购人获取通知书
	 * Create Date: 2010-8-1上午11:59:22 by yangx  Modified Date: 2010-8-1上午11:59:22 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Notice> getListByBuyer(String projectId,User user);
	
	/**
	 * @Description: 根据包件ID获取所有申报书条目
	 * @param subProjectId 包件ID
	 * @return List<SubProjectMTaskPlanSub>
	 * @throws Exception
	 * @Create Date 2010-7-19 上午11:03:40 By wanghz
	 */
	public List<SubProjectMTaskPlanSub> getSubProjectMTaskPlanSubBySubProjectId(String subProjectId)throws EsException;
	
	
	
	/**
	 * FuncName: getNoticeListByQueryObject
	 * Description :  根据QueryObject对象查询通知书列表
	 * @param 
	 * @param queryObject void
	 * @author: liuke
	 * @Create Date:2011-1-28  下午04:52:29
	 * @Modifier: liuke
	 * @Modified Date:2011-1-28  下午04:52:29
	 */
	public List getNoticeListByQueryObject(QueryObject queryObject);
	
	
	
	
	/**
	 * FuncName: getNoticeListByQueryObject
	 * Description :  根据QueryObject对象查询通知书列表
	 * @param 
	 * @param queryObject void
	 * @author: liuke
	 * @Create Date:2011-1-28  下午04:52:29
	 * @Modifier: liuke
	 * @Modified Date:2011-1-28  下午04:52:29
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
	
	/**
	 * FuncName: finishBuyResultNotice
	 * Description :  完成发送结果通知书
	 * @param 
	 * @param projectId
	 * @return WorkgMember
	 * @author: zhangsh
	 * @Create Date:2011-11-16  上午11:45:26
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-16  上午11:45:26
	 */
	public Notice finishBuyResultNotice(String projectId) throws Exception;
}
