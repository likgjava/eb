package com.gpcsoft.epp.noticemanage.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.noticemanage.dao.NoticeDao;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.noticemanage.domain.NoticeAffirmRecord;
import com.gpcsoft.epp.noticemanage.manager.NoticeManager;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class NoticeManagerImpl extends BaseManagerImpl<Notice> implements NoticeManager {

	@Autowired(required=true)  @Qualifier("noticeDaoHibernate")
	private NoticeDao noticeDaoHibernate;

	/**
	 * 根据查询条件获取通知书
	 */
	public Notice getNoticeByQueryObject(QueryObject queryObject) {
		List list = noticeDaoHibernate.findByQuery(queryObject);
		if(null != list && !list.isEmpty()){
			return (Notice)list.get(0);
		}else{
			return null;
		}
	}

	public Notice getNoticeBySellerAndSubProjectId(String sellerId,
			String subProjectId) {
		List<Notice> list = noticeDaoHibernate.findbyHql("select t from Notice t where t.receiveOrg.objId=? and t.project.objId = ?",
				sellerId,subProjectId);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<SubProjectMTaskPlanSub> getSubProjectMTaskPlanSubBySubProjectId(String subProjectId) throws EsException {
		return noticeDaoHibernate.getSubProjectMTaskPlanSubBySubProjectId(subProjectId);
	}

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
	public List<Notice> getListByProjectId(String projectId){
		return noticeDaoHibernate.getListByProjectId(projectId);
	}
}
