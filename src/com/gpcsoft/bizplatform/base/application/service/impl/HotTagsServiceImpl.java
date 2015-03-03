package com.gpcsoft.bizplatform.base.application.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.application.dao.HotTagsDao;
import com.gpcsoft.bizplatform.base.application.domain.HotTags;
import com.gpcsoft.bizplatform.base.application.manager.HotTagsManager;
import com.gpcsoft.bizplatform.base.application.service.HotTagsService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class HotTagsServiceImpl extends BaseGenericServiceImpl<HotTags> implements HotTagsService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("hotTagsManagerImpl")
	private HotTagsManager hotTagsManager;
	
	@Autowired(required=true) @Qualifier("hotTagsDaoHibernate")
	private HotTagsDao hotTagsDaoHibernate;

	/** 
	 * Description :  取得热门标签
	 * Create Date: 2010-10-7下午05:29:16 by yucy  Modified Date: 2010-10-7下午05:29:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<HotTags> getHotTagsList(Map<String, Object> params)throws Exception {
		return hotTagsDaoHibernate.getHotTagsList(params);
	}

}
