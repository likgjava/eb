package com.gpcsoft.epp.oppugnrequisition.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.manager.OppugnReplyManager;
import com.gpcsoft.epp.oppugnrequisition.service.OppugnReplyService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;

@Service 
public class OppugnReplyServiceImpl extends BaseGenericServiceImpl<OppugnReply> implements OppugnReplyService {

	@Autowired(required=true) @Qualifier("oppugnReplyManagerImpl")
	private OppugnReplyManager oppugnReplyManager;
	
	@Autowired(required=false)
	private AttachmentManager attachmentManagerImpl;

	/**
	 * 根据质疑获取回复
	 */
	public List<OppugnReply> getReplyByOppId(String oppId) throws Exception {
		logger.debug("\nes OppugnReplyServiceImpl||getReplyByOppId\n");
		return oppugnReplyManager.getReplyByOppId(oppId);
	}
	/**
	 * 
	 * Description :  保存回复对象
	 * Create Date: 2010-9-7上午09:41:39 by shenjianzhuang  Modified Date: 2010-9-7上午09:41:39 by shenjianzhuang
	 * @param oppugnReply
	 * @return
	 * @return  OppugnReply
	 * @Exception 
	 */	
	public OppugnReply saveOppugnReply(OppugnReply oppugnReply) {
		logger.debug("\nes OppugnReplyServiceImpl||saveOppugnReply\n");
		User user = AuthenticationHelper.getCurrentUser();
		oppugnReply.setCreateUser(user);
		oppugnReply.setOppurRemark("答复人："+user.getEmp().getCompany().getName()+" "+user.getEmp().getName());
		oppugnReplyManager.save(oppugnReply);
		attachmentManagerImpl.setIsUsed(oppugnReply.getAttachRelaId(),true);//修改附件引用状态为true
		return oppugnReply;
	}
}
