package com.gpcsoft.agreement.bargin.invitation.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.invitation.service.InvitationListService;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.bulletin.dao.BulletinDao;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.inviterollrequ.service.impl.InrqDetailServiceImpl;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;

@Service 
public class InvitationListServiceImpl extends InrqDetailServiceImpl implements InvitationListService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	
	@Autowired(required=true)  @Qualifier("bulletinDaoHibernate")
	private BulletinDao bulletinDaoHibernate;
	
	@Autowired(required=true) @Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;

	/** 
	 * Description : 保存公告
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param   
	 * @return  
	 * @throws IOException 
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreement(Bulletin bulletin) throws IOException {
		/** 公告文件的存储路径 */
		Properties properties = new Properties();
        InputStream in =InvitationListServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
        properties.load(in);
        String path = properties.getProperty("bulletinUrl") ;
		
		Attachment attachment =null;
		if(null != bulletin.getContent() && null != bulletin.getContent().getObjId()){
			attachment = attachmentManager.get(bulletin.getContent().getObjId());
		}else {
			attachment = new Attachment();
			attachment.setFileName(java.util.UUID.randomUUID().toString().replace('-', '_'));
		}
		
		if(null == bulletin.getCreateTime()){
			bulletin.setCreateTime(new Date());
		}
//		attachment.setPath(path);
		attachment.setViewName(bulletin.getBullTitle());
		attachment.setCreateTime(bulletin.getCreateTime());
		attachmentManager.save(attachment);
		bulletin.setContent(attachment);
		bulletinDaoHibernate.save(bulletin);
		
		// 修改项目过程状态
		if(null != bulletin.getProject() && StringUtils.hasLength(bulletin.getProject().getObjId()) && StringUtils.hasLength(bulletin.getProject().getProjProcessStatus())){
			projectManager.saveProjProcessStatus(bulletin.getProject().getObjId(), bulletin.getProject().getProjProcessStatus());
		}
		// 把公告内容写到指定文件中 
		FileUtil.writerFile(path+attachment.getFileName(),bulletin.getBullContents(),"UTF-8");
		return bulletin;
	}
	

	
}
