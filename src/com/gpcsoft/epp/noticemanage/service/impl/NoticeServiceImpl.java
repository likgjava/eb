package com.gpcsoft.epp.noticemanage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.buyresult.dao.BuyWinnerDao;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.FileVirtualPathEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.noticemanage.dao.NoticeDao;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.noticemanage.domain.NoticeAffirmRecord;
import com.gpcsoft.epp.noticemanage.domain.NoticeStatusEnum;
import com.gpcsoft.epp.noticemanage.manager.NoticeManager;
import com.gpcsoft.epp.noticemanage.service.NoticeService;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class NoticeServiceImpl extends BaseGenericServiceImpl<Notice> implements NoticeService {

	@Autowired(required=true) @Qualifier("noticeManagerImpl")
	private NoticeManager noticeManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true)  @Qualifier("noticeDaoHibernate")
	private NoticeDao noticeDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("buyWinnerDaoHibernate")
	private BuyWinnerDao buyWinnerDaoHibernate;
	
	/** 
	 * Description :  保存通知书以及通知书内容
	 * Create Date: 2010-9-19下午04:46:19 by yangx  Modified Date: 2010-9-19下午04:46:19 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Notice saveNoticeAndAttachment(Notice notice){
		logger.debug("\nes NoticeServiceImpl||saveNoticeAndAttachment\n");
		String path=messageSource.getMessage("uploadUrl")+FileVirtualPathEnum.BUYRESULTNOTICE;//创建附件对象
		FileUtil.mkdirs(path);
		String saveName=java.util.UUID.randomUUID().toString();
		FileUtils.writerFile(path+saveName,notice.getContents());
		Attachment atta = new Attachment();
		atta.setPath(FileVirtualPathEnum.BUYRESULTNOTICE);
		atta.setFileName(saveName);
		atta.setViewName(notice.getNoticeTitle());
		atta.setCreateTime(new Date());
		noticeManager.save(atta,Attachment.class);
		notice.setNoticeContent(atta);
		return noticeManager.save(notice);
	}
	
	/**
	 * 保存供应商确认
	 */
	public Notice saveNoticeAffirm(String noticeId,NoticeAffirmRecord noticeAffirmRecord) {
		logger.debug("\nes NoticeServiceImpl||saveNoticeAffirm\n");
		Notice notice=noticeManager.get(noticeId);
		noticeAffirmRecord.setNotice(notice);
		noticeManager.save(noticeAffirmRecord,NoticeAffirmRecord.class);
		notice.setReceiveStatus(NoticeStatusEnum.RECEVICESTATUS_YES);
		noticeManager.save(notice);
		return notice;
	}

	/** 
	 * Description :  创建或得到一条通知书
	 * Create Date: 2010-6-28下午02:02:02 by wangcl  Modified Date: 2010-6-28下午02:02:02 by wangcl
	 * @param   winnerId 中标结果id
	 * @param   subProjectId 标段id
	 * @return  
	 * @Exception   
	 */
	public Notice getNoticeBySelllerIdAndSubProjectId(String selllerId,String subProjectId) throws Exception {
		logger.debug("\nes NoticeServiceImpl||getNoticeBySelllerIdAndSubProjectId\n");
		Notice notice = noticeManager.getNoticeBySellerAndSubProjectId(selllerId, subProjectId);
		if (notice!=null) {
			String path=messageSource.getMessage("uploadUrl");
			String contents = FileUtils.readFileByLines(path+notice.getNoticeContent().getPath()+notice.getNoticeContent().getFileName(), "UTF-8");
			notice.setContents(contents);
		}
		return notice;
	}
	/** 
	 * Description :  根据通知书Id查看通知书
	 * Create Date: 2010-8-1下午01:49:44 by yangx  Modified Date: 2010-8-1下午01:49:44 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Notice getNotice(String id){
		logger.debug("\nes NoticeServiceImpl||getNotice\n");
		Notice notice = this.get(id);
		String path=messageSource.getMessage("uploadUrl");
		String contents = FileUtils.readFileByLines(path+notice.getNoticeContent().getPath()+notice.getNoticeContent().getFileName(), "UTF-8");
		notice.setContents(contents);
		return notice;
	}

	/** 
	 * Description :  根据项目Id、供应商Id查询待确认的通知书
	 * Create Date: 2010-8-30上午11:57:47 by yangx  Modified Date: 2010-8-30上午11:57:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Notice> getListBySupplier(String projectId,String orgInfoId) {
		logger.debug("\nes NoticeServiceImpl||getListBySupplier\n");
		return noticeDaoHibernate.getListBySupplier(projectId,orgInfoId);
	}
	/** 
	 * Description :  根据项目Id和采购人获取通知书
	 * Create Date: 2010-8-1上午11:59:22 by yangx  Modified Date: 2010-8-1上午11:59:22 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Notice> getListByBuyer(String projectId,User user){
		logger.debug("\nes NoticeServiceImpl||getListByBuyer\n");
		OrgInfo orgInfo= (OrgInfo)user.getOrgInfo();
		return noticeDaoHibernate.getListByBuyer(projectId,orgInfo.getObjId());
	}

	/** 
	 * Description :  提交通知书
	 * Create Date: 2010-6-28下午05:33:30 by wangcl  Modified Date: 2010-6-28下午05:33:30 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Notice saveSubmitNoticeFile(Notice notice) {
		logger.debug("\nes NoticeServiceImpl||saveSubmitNoticeFile\n");
		noticeManager.save(notice);
		/**
		 * 更新包件状态
		 * Add date 2010-07-30 By wanghz
		 */
		/*
		 * 已将改变状态操作移至决策类里  noted by zhouzhanghe at 2011.3.10 17:43
		 * projectManager.saveProjProcessStatus(notice.getProject().getObjId(), ProjProcessStatusEnum.DRAFT_RESULT_NOTICES);
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(notice.getProject().getObjId());//项目规则
		*//** 保存项目实施状态 *//*
		if(projectRule!=null&&projectRule.getIsDividePack()!=null&&!projectRule.getIsDividePack()){//项目不分包
			projectManager.saveProjProcessStatus(notice.getProject().getObjId(), ProjProcessStatusEnum.DRAFT_RESULT_NOTICES);	
		}else{
			projectManager.saveProjProcessStatus(notice.getProject().getParentId(), ProjProcessStatusEnum.DRAFT_RESULT_NOTICES);
		}*/
		User user = AuthenticationHelper.getCurrentUser();
		notice.setUser(user);
		
		Project project = projectManager.get(notice.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		notice.setParentBizId(parentBizId);
		notice.setUser(user);
		return notice;
	}
	
	/**
	 * @Description: 根据包件ID获取所有申报书条目
	 * @param subProjectId 包件ID
	 * @return List<SubProjectMTaskPlanSub>
	 * @throws Exception
	 * @Create Date 2010-7-19 上午11:03:40 By wanghz
	 */
	public List<SubProjectMTaskPlanSub> getSubProjectMTaskPlanSubBySubProjectId(String subProjectId)throws EsException{
		logger.debug("\nes NoticeServiceImpl||getSubProjectMTaskPlanSubBySubProjectId\n");
		return noticeManager.getSubProjectMTaskPlanSubBySubProjectId(subProjectId);
	}

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
	public List getNoticeListByQueryObject(QueryObject queryObject) {
		logger.debug("\nes NoticeServiceImpl||getNoticeListByQueryObject\n");
		return noticeDaoHibernate.getNoticeListByQueryObject(queryObject);
	}

	
	
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
	public Page getNoticePageByQueryObject(QueryObject queryObject, Page page) {
		return noticeDaoHibernate.getNoticePageByQueryObject(queryObject, page);
	}
	
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
	public Notice getNoticeBy(String projectId){
		return noticeDaoHibernate.getNoticeBy(projectId);
	}
	
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
	public Notice finishBuyResultNotice(String projectId) throws Exception{ 
		/**
		 *1.查询项目下所有通知书
		 *2.判断是否有未保存的通知书 
		 *3.有则抛出异常提示信息
		 *4.没有则返回
		 */
		
		List<BuyWinner> buyWinnerlist  = buyWinnerDaoHibernate.getBuyWinnerByProjectId(projectId);
		List<Notice> noticeList = noticeDaoHibernate.getListByProjectId(projectId);
		Notice notice = new Notice();
		if(buyWinnerlist.size() == noticeList.size()){
			notice.setParentBizId(projectId);
			notice.setUser(AuthenticationHelper.getCurrentUser());
		}else{
			throw new EsException(messageSource.getMessage("es.sendnotice"));
		}
    	return notice;
	};
}

