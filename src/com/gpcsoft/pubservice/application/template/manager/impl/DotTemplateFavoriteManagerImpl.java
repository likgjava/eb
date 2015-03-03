package com.gpcsoft.pubservice.application.template.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.template.dao.DotTemplateFavoriteDao;
import com.gpcsoft.pubservice.application.template.manager.DotTemplateFavoriteManager;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateFavorite;

@Repository
public class DotTemplateFavoriteManagerImpl extends BaseManagerImpl<DotTemplateFavorite> implements DotTemplateFavoriteManager {

	@Autowired(required=true)  @Qualifier("dotTemplateFavoriteDaoHibernate")
	private DotTemplateFavoriteDao dotTemplateFavoriteDaoHibernate;

	/** 
	 * Description :  根据参数获取范本收藏列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  orgInfoId:收藏机构的Id  userId:收藏人的Id
	 * @return  
	 * @Exception   
	 */
	public List<DotTemplateFavorite> getTemplateFavoriteList(Map<String, Object> param) throws Exception {
		return dotTemplateFavoriteDaoHibernate.getTemplateFavoriteList(param);
	}
}
