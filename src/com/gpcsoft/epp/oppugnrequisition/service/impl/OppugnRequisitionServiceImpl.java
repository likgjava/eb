package com.gpcsoft.epp.oppugnrequisition.service.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.oppugnrequisition.dao.OppugnRequisitionDao;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnRequisition;
import com.gpcsoft.epp.oppugnrequisition.manager.OppugnRequisitionManager;
import com.gpcsoft.epp.oppugnrequisition.service.OppugnRequisitionService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;

@Service 
public class OppugnRequisitionServiceImpl extends BaseGenericServiceImpl<OppugnRequisition> implements OppugnRequisitionService {

	@Autowired(required=true) @Qualifier("oppugnRequisitionManagerImpl")
	private OppugnRequisitionManager oppugnRequisitionManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;

	@Autowired(required=true)  @Qualifier("oppugnRequisitionDaoHibernate")
	private OppugnRequisitionDao oppugnRequisitionDaoHibernate;
	
	@Autowired(required=false)
	private AttachmentManager attachmentManagerImpl;
	
	/** 
	 * Description :   保存质疑
	 * Create Date: 2010-12-1上午10:29:31 by yangx  Modified Date: 2010-12-1上午10:29:31 by yangx
	 * @param   projectId:项目Id,oppugnRequisition:质疑对象
	 * @return  
	 * @Exception   
	 */
	public void saveOppugnRequisition(String projectId,OppugnRequisition oppugnRequisition) {
		logger.debug("\nes OppugnRequisitionServiceImpl||saveOppugnRequisition\n");
		Project project=projectManager.get(projectId);
		if(project!=null){
			oppugnRequisition.setProject(project);
		}
		oppugnRequisitionManager.saveOppugnRequisition(oppugnRequisition);
		attachmentManagerImpl.setIsUsed(oppugnRequisition.getAttachRelaId(),true);//修改附件引用状态为true
	}

	/**
	 * 
	 * Description :  根据质疑Id获取质疑对象 
	 * Create Date: 2010-9-7上午09:08:16 by shenjianzhuang  Modified Date: 2010-9-7上午09:08:16 by shenjianzhuang
	 * @return
	 * @return  OppugnRequisition
	 * @Exception 
	 */	
	public OppugnRequisition getOppugnRequisition(String objId) {
		logger.debug("\nes OppugnRequisitionServiceImpl||getOppugnRequisition\n");
		return oppugnRequisitionDaoHibernate.get(objId);
	}
	/**
	 * 
	 * Description :  根据质疑Id删除质疑对象 处理附件使用状态 
	 * Create Date: 2010-9-7上午09:08:16 by shenjianzhuang  Modified Date: 2010-9-7上午09:08:16 by shenjianzhuang
	 * @return
	 * @return  OppugnRequisition
	 * @Exception 
	 */	
	public OppugnRequisition deleteOppugnRequisition(String objId) {
		logger.debug("\nes OppugnRequisitionServiceImpl||deleteOppugnRequisition\n");
		OppugnRequisition o = oppugnRequisitionManager.get(objId);
		attachmentManagerImpl.setIsUsed(o.getAttachRelaId(),false);
		Set<OppugnReply> replys = o.getReplys();
		for (Iterator<OppugnReply> iterator = replys.iterator(); iterator.hasNext();) {
			OppugnReply object = iterator.next();
			attachmentManagerImpl.setIsUsed(object.getAttachRelaId(),false);
		}
		attachmentManagerImpl.remove(objId, OppugnRequisition.class);
		return o;
	}
}
