package com.gpcsoft.epp.inviterollrequ.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.inviterollrequ.dao.InrqDetailDao;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.manager.InrqDetailManager;
import com.gpcsoft.epp.inviterollrequ.service.InrqDetailService;

@Service 
public class InrqDetailServiceImpl extends BaseGenericServiceImpl<InrqDetail> implements InrqDetailService {

	@Autowired(required=true) @Qualifier("inrqDetailManagerImpl")
	private InrqDetailManager inrqDetailManager;

	@Autowired(required=true)  @Qualifier("inrqDetailDaoHibernate")
	private InrqDetailDao inrqDetailDaoHibernate;
	
	/**
	 * 
	 * Description :得到供应商邀请函数量  
	 * Create Date: 2010-8-26下午03:44:48 by liuke  Modified Date: 2010-8-26下午03:44:48 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getInrqDetailNumByQueryObject(QueryObject queryObject) {
		logger.debug("\n InrqDetailServiceImpl||getInrqDetailNumByQueryObject\n");
		return inrqDetailDaoHibernate.getInrqDetailNumByQueryObject(queryObject);
	}
	
	/**
	 * 
	 * Description :得到单一来源邀请函列表 
	 * Create Date: 2010-8-26下午05:15:37 by liuke  Modified Date: 2010-8-26下午05:15:37 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public Page<Inviterollrequ> getInrqDetailByQueryObject(QueryObject queryObject,Page<InrqDetail> page) {
		logger.debug("\n InrqDetailServiceImpl||getInrqDetailByQueryObject\n");
		return inrqDetailDaoHibernate.getInrqDetailByQueryObject(queryObject, page);
	}

	
	/** 
	 * Description :  根据项目Id获取邀请函内容 
	 * Create Date: 2010-8-28下午03:16:15 by yangx  Modified Date: 2010-8-28下午03:16:15 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public InrqDetail getInrqDetailContentByProjectId(String projectId){
		logger.debug("\n InrqDetailServiceImpl||getInrqDetailContentByProjectId\n");
		InrqDetail inrqDetail = inrqDetailDaoHibernate.getInrqDetailByProjectIdAndSupplierId(projectId);
		if (inrqDetail!=null) {
			String path=messageSource.getMessage("uploadUrl");
			String attachPath = inrqDetail.getInrqDContent().getPath()==null?"":inrqDetail.getInrqDContent().getPath();
			String contents = FileUtils.readFileByLines(path+attachPath+inrqDetail.getInrqDContent().getFileName(), "UTF-8");
			inrqDetail.setContents(contents);
		}
		return inrqDetail;
	}

	/** 
	 * Description : 根据项目Id获取录入的供应商 
	 * Create Date: 2010-8-27下午05:51:23 by yangx  Modified Date: 2010-8-27下午05:51:23 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<InrqDetail> getInrqDetailByProjectId(String projectId){
		logger.debug("\n InrqDetailServiceImpl||getInrqDetailByProjectId\n");
		return inrqDetailDaoHibernate.getInrqDetailByProjectId(projectId);
	}
	
	/** 
	 * Description :  根据邀请函objId查询邀请函
	 * Create Date: 2010-8-28下午02:59:47 by yangx  Modified Date: 2010-8-28下午02:59:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public InrqDetail getInrqDetailByObjId(String objId){
		logger.debug("\n InrqDetailServiceImpl||getInrqDetailByObjId\n");
		InrqDetail inrqDetail = this.get(objId);
		String path=messageSource.getMessage("uploadUrl");
		String attachPath = inrqDetail.getInrqDContent().getPath()==null?"":inrqDetail.getInrqDContent().getPath();
		String contents = FileUtils.readFileByLines(path+attachPath+inrqDetail.getInrqDContent().getFileName(), "UTF-8");
		inrqDetail.setContents(contents);
		return inrqDetail;
	}
	/**
	 * Description :  修改邀请函对象
	 * Create Date: 2010-10-15下午04:01:03 by shenjianzhuang  Modified Date: 2010-10-15下午04:01:03 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  InrqDetail
	 * @Exception		 
	 */	  
	public InrqDetail updateInrqDetail(String objId,String contents){
		logger.debug("\n InrqDetailServiceImpl||updateInrqDetail\n");
		InrqDetail inrqDetail = this.get(objId);
		String path=messageSource.getMessage("uploadUrl");
		String attachPath = inrqDetail.getInrqDContent().getPath()==null?"":inrqDetail.getInrqDContent().getPath();
		FileUtils.writerFile(path+attachPath+inrqDetail.getInrqDContent().getFileName(),contents);
		inrqDetailManager.save(inrqDetail);
		return null;
	}
}
